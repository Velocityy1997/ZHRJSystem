package com.web.business.generator.system.zhj.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;
import com.web.common.util.spring.PageTool;

import com.web.business.generator.system.zhj.model.Zhj;
import com.web.business.generator.system.zhj.model.ZhjModel;
import com.web.business.generator.comm.historyTask.model.HistoryModel;
import com.web.business.generator.comm.historyTask.model.HistoryTask;
import com.web.business.generator.system.area.dao.AreaMapper;
import com.web.business.generator.system.area.model.Area;
import com.web.business.generator.system.industry.dao.IndustryMapper;
import com.web.business.generator.system.industry.model.Industry;
import com.web.business.generator.system.terminal.model.Terminal;
import com.web.business.generator.system.terminal.model.TerminalModel;
import com.web.business.generator.system.user.model.User;
import com.web.business.generator.system.zhj.dao.ZhjMapper;
import com.web.business.generator.system.zhj.services.IZhjService;
import com.web.business.generator.util.isNull.IsNullUtil;

@Service
public class ZhjServiceImpl implements IZhjService {
	@Resource
	private ZhjMapper zhjMapper;
	
	@Resource
	private AreaMapper areaMapper;

	@Resource
	private IndustryMapper industryMapper;
	
	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteByPrimaryKey(String id) throws Exception {
		int result = -1;
		result = zhjMapper.deleteByPrimaryKey(id);
		return result;
	}

	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public Integer deleteBatchByPrimaryKey(String id) throws Exception {
		int result = -1;
		result = zhjMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(id));
		return result;
	}

	/**
	 * 新增
	 * 
	 * @param record
	 * @return
	 */
	public Integer insertSelective(Zhj record) throws Exception {
		int result = -1;
		result = zhjMapper.insertSelective(record);
		return result;
	}

	/**
	 * 根据主键查询对象
	 * 
	 * @param id
	 * @return
	 */
	public ZhjModel selectByPrimaryKey(String id) throws Exception {
		List<Zhj> list = new ArrayList<Zhj>();
		List<ZhjModel> listNew = new ArrayList<ZhjModel>();
		Zhj zhj = new Zhj();
		zhj = zhjMapper.selectByPrimaryKey(id);
		list.add(zhj);
		listNew = getZhjList(list);

		return listNew.get(0);
	}

	/**
	 * 修改
	 * 
	 * @param record
	 * @return
	 */
	public Integer updateByPrimaryKeySelective(Zhj record) throws Exception {
		int result = -1;
		result = zhjMapper.updateByPrimaryKeySelective(record);
		return result;
	}

	/**
	 * 分页查询数据列表
	 * 
	 * @param record
	 * @param page
	 * @return
	 */
	public PageTool<ZhjModel> selectZhjByPage(Zhj record, Integer page, Integer pageSize, User user) throws Exception {
		
		List<ZhjModel> listNew = new ArrayList<ZhjModel>();

		int start = (page != null ? page : 1);
		int max = pageSize != null ? pageSize : 10;
		record.setStart((start - 1) * pageSize);
		record.setMax(max);
		
		PageTool<ZhjModel> pageTool = null;
		
		// 1.分角色
		Integer userType = user.getType();
		if (userType == 1) {
			// 1.1管理员
			// 查询分页
			List<Zhj> list = new ArrayList<Zhj>();
			list = zhjMapper.selectByPropertyByPage(record);
			List<Zhj> listFullAreaTerminals = getAllZoneInfoByList(list);
			// 数据转换
			listNew = getZhjList(listFullAreaTerminals);
			
			// 查询总数
			int count = zhjMapper.selectCountByProperty(record);
			 pageTool= new PageTool<>(page, count, listNew, pageSize);
			
		} else if (userType == 0) {
			// 1.2 普通用户
			List<Zhj> list = new ArrayList<Zhj>();
			
			// 1.2.1 根据用户的区域查下属区域，保存为字符串-----“1，2，3，4，5，6”
			List<String> sonAreaIds = areaMapper.selectSonIdListByParentId(user.getUserArea());
			sonAreaIds.add(user.getUserArea());//添加用户所在区域的id
			
			// 根据区域id集合来查终端
			String idStrTemp = "";

			// 拼接id为字符串： 1，2，3，4 方便dao中FIND_IN_SET遍历
			for (String id : sonAreaIds) {
				idStrTemp += id + ",";
			}			
			// 去掉最后一个 “,”
			String idStr = idStrTemp.substring(0, idStrTemp.length() - 1);			
			// 1.2.2 查询
			list = zhjMapper.selectZhjByCommonUser(record,idStr); 	
			List<Zhj> listFullAreaTerminals = getAllZoneInfoByList(list);
			// 1.2.3 分页
			pageTool = getPageTool(listFullAreaTerminals, page, pageSize);			
			
		} else {
			// 用户类型参数为空
			//return null;			
		}				
		return pageTool;
	}

	public Zhj getCurrentZhj() {
		Zhj zhj = zhjMapper.getCurrentZhj();
		return zhj;
	}

	/**
	 * 给终端的区域拼接省市区全称
	 * 
	 * @param list
	 * @return
	 */
	public List<Zhj> getAllZoneInfoByList(List<Zhj> list) {

		String thirdLevelParentId = null; // 用户当前区域的上级id 第三级区域的父id
		String secondLevelParentId = null; // 第二级区域的父id
		String firstLevelParentId = null; // 第一级区域的父id
		Boolean tag = false;
		String areaId = null; // 用户当前区域的id
		try {
			for (Zhj terminal : list) {
				if(terminal.getZhjindustry() != null) {
					Industry industry = industryMapper.selectByPrimaryKey(terminal.getZhjindustry());
					if(industry != null) {
						terminal.setIndustryName(industry.getIndustName());
					}
				}
							
				areaId = terminal.getZhjArea();
				tag = IsNullUtil.isStrNull(areaId);
				if (!tag) {
					// 根据用户所在的区域id查父级id
					// 第三级别区域：区县
					Area thirdLevelArea = (Area) areaMapper.selectByPrimaryKey(areaId);
					tag = IsNullUtil.isObjectNull(thirdLevelArea);
					if (!tag) {
						// 不为空
						thirdLevelParentId = thirdLevelArea.getParentId();

						tag = IsNullUtil.isStrNull(thirdLevelParentId);

						if (!tag) {
							if (thirdLevelParentId.equals("0")) {
								// 用户所在区域最小级别为省
								// areaName 已经为中文
								// 陕西省
								terminal.setAreaName(thirdLevelArea.getAreaName());
								terminal.setProvinceName(thirdLevelArea.getAreaName());
								terminal.setProvinceId(thirdLevelArea.getAreaId());
							} else {
								// 存在上级区域
								String terminalAreaName = null;
								secondLevelParentId = thirdLevelArea.getParentId();

								// 判断不为空
								tag = IsNullUtil.isStrNull(secondLevelParentId);

								if (!tag) {
									// 不为空
									// 拼接区域名
									Area secondLevelArea = (Area) areaMapper
											.selectByPrimaryKey(thirdLevelArea.getParentId().toString());
									secondLevelParentId = secondLevelArea.getParentId();

									tag = IsNullUtil.isStrNull(secondLevelParentId);
									if (!tag) {
										if (secondLevelParentId.equals("0")) {
											// 目前最小级别为市
											terminalAreaName = secondLevelArea.getAreaName()
													+ thirdLevelArea.getAreaName();

											// 陕西省西安市
											terminal.setAreaName(terminalAreaName);
											terminal.setProvinceName(secondLevelArea.getAreaName());
											terminal.setProvinceId(secondLevelArea.getAreaId());
											terminal.setCityName(thirdLevelArea.getAreaName());
											terminal.setCityId(thirdLevelArea.getAreaId());

										} else {
											// 最小级别为区县
											Area firstLevelArea = (Area) areaMapper
													.selectByPrimaryKey(secondLevelArea.getParentId().toString());
											terminalAreaName = firstLevelArea.getAreaName()
													+ secondLevelArea.getAreaName() + thirdLevelArea.getAreaName();

											// 陕西省西安市长安区
											terminal.setAreaName(terminalAreaName);
											terminal.setProvinceName(firstLevelArea.getAreaName());
											terminal.setProvinceId(firstLevelArea.getAreaId());
											terminal.setCityName(secondLevelArea.getAreaName());
											terminal.setCityId(secondLevelArea.getAreaId());
											terminal.setZoneName(thirdLevelArea.getAreaName());
											terminal.setZoneId(thirdLevelArea.getAreaId());
										}
									}
								}
							}
						}

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	/*****
	 * 根据指挥机名字查询
	 ****/
	@Override
	public Zhj selectByName(String name) {
		return zhjMapper.selectByName(name);
	}

	/*****
	 * 根据指挥机卡号查询
	 ****/
	@Override
	public Zhj selectByCardNum(String cardNum) {
		return zhjMapper.selectByCardNum(cardNum);
	}

	/**
	 * 将正常数据库查出来的指挥机的参数换成中文，采用遍历和ZhjModel
	 * 
	 * @param list
	 * @return
	 */
	public List<ZhjModel> getZhjList(List<Zhj> list) {
		List<ZhjModel> changedList = new ArrayList<ZhjModel>();

		for (Zhj zhj : list) {
			ZhjModel zhjModel = new ZhjModel();
			zhjModel.setId(zhj.getId());
			zhjModel.setName(zhj.getName());
			// 类型
			if (zhj.getType() != null) {
				if (zhj.getType() == 0) {
					zhjModel.setType("普通指挥机");
				} else if (zhj.getType() == 1) {
					zhjModel.setType("中心指挥机");
				} else {
					zhjModel.setType("类型未设置");
				}
			} else {
				zhjModel.setType("类型未设置");
			}

			// 区号
			if (zhj.getZone() != null) {
				if (zhj.getZone() > 0 && zhj.getZone() < 6) {
					zhjModel.setZone("超级指挥机区号 ");
				} else {
					zhjModel.setZone("普通指挥机区号 ");
				}
			} else {
				zhjModel.setZone("区号未设置");
			}

			// 服务状态
			if (zhj.getStatus() != null) {
				if (zhj.getStatus() == 0) {
					zhjModel.setStatus("停止");
				} else if (zhj.getStatus() == 1) {
					zhjModel.setStatus("忙碌");
				} else {
					zhjModel.setStatus("空闲");
				}

			}

			zhjModel.setCardNum(zhj.getCardNum());
			// 通信等级
			if (zhj.getTxlevel() != null) {
				zhjModel.setTxlevel(zhj.getTxlevel().toString());

			}
			if (zhj.getBrocastAdd() != null) {
				zhjModel.setBrocastAdd(zhj.getBrocastAdd());

			}
			if (zhj.getFrequency() != null) {
				zhjModel.setFrequency(zhj.getFrequency().toString());

			}
			if (zhj.getEncryptflag() != null) {
				zhjModel.setEncryptflag(zhj.getEncryptflag().toString());

			}
			if (zhj.getRemark() != null) {
				zhjModel.setRemark(zhj.getRemark());
			}
			if (zhj.getNumInZone() != null) {
				zhjModel.setNumInZone(zhj.getNumInZone().toString());

			}
			if (zhj.getUsercount() != null) {
				zhjModel.setUsercount(zhj.getUsercount().toString());

			}
			if (zhj.getIscurrent() != null) {
				// 是否是指挥机
				if (zhj.getIscurrent() == 0) {
					zhjModel.setIscurrent("是");
				} else {
					zhjModel.setIscurrent("否");
				}
			}
			if (zhj.getUserFeature() != null) {
				zhjModel.setUserFeature(zhj.getUserFeature().toString());

			}
			if (zhj.getTxlength() != null) {
				zhjModel.setTxlength(zhj.getTxlength().toString());
			}
			if (zhj.getPosition() != null) {
				zhjModel.setPosition(zhj.getPosition());

			}
			if (zhj.getZhjArea() != null) {
				zhjModel.setZhjArea(zhj.getZhjArea());

			}
			if (zhj.getZhjindustry() != null) {
				zhjModel.setZhjindustry(zhj.getZhjindustry());

			}
			if (zhj.getAreaName() != null) {
				zhjModel.setAreaName(zhj.getAreaName());
			}
			if (zhj.getIndustryName() != null) {
				zhjModel.setIndustryName(zhj.getIndustryName());
			}
			zhjModel.setProvinceId(zhj.getProvinceId());
			zhjModel.setProvinceName(zhj.getProvinceName());
			zhjModel.setCityId(zhj.getCityId());
			zhjModel.setCityName(zhj.getCityName());
			
			zhjModel.setZoneId(zhj.getZoneId());
			zhjModel.setZoneName(zhj.getZoneName());
			
			changedList.add(zhjModel);

		}
		return changedList;
	}
	
	
	/**
	 *  普通用户进行分页查询
	 * @param list
	 * @param page 当前页
	 * @param pageSize  每页数据条数
	 * @return
	 * by gyd
	 */
	public PageTool<ZhjModel> getPageTool(List<Zhj> list,int page,int pageSize) {
		
		PageTool<ZhjModel> pageTool = null;
		
		// 转换为 TerminalModel
		List<ZhjModel> zhjModelList = getZhjList(list);

		
		// 进行分页
		int sum = zhjModelList.size();  //集合的长度
		List<ZhjModel> zhjPageList = new ArrayList<>();  // 存储分页计算后的集合
		
		//进行分页
		// 15条  ，pageSize=10  page=1
		if (list.size() < pageSize) {
			// 数据小于10条不用分页
			pageTool = new PageTool<>(page, sum, zhjModelList, pageSize);

		}else {
			// 数据大于10条
			// 页数
			if (page == 1) {
				// 第一页
				pageTool = new PageTool<>(page, sum, zhjModelList, pageSize);
				
			}else {
				// 第二页及以上，得进行截取原来的集合   15
					// 第二页及以上，得进行截取原来的集合   15
					// 去掉上一页的数据
					int startIndex = (page-1)*pageSize; // 起始坐标   0  10
					int endIndex = startIndex+10;   // 终止坐标，每次取10条  20
					
					if (list.size() <endIndex && list.size() == endIndex) {
						zhjPageList = zhjModelList.subList(startIndex, endIndex+1);//包含起始，不包含结尾坐标

					}else {
						// 集合长度大于终止坐标   25                    10           20
						if(list.size() < endIndex) {
							zhjPageList = zhjModelList.subList(startIndex, list.size());//包含起始，不包含结尾坐标

						}else {
							zhjPageList = zhjModelList.subList(startIndex, startIndex+10);//包含起始，不包含结尾坐标

						}
					
					}
					
					pageTool = new PageTool<>(page, sum, zhjPageList, pageSize);
					
			}
			
			
		}
		
		return pageTool;
		
		
	}
	
	
	
	
	
	
	

}