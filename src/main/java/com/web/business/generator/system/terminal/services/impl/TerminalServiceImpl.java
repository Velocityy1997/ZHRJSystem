package com.web.business.generator.system.terminal.services.impl;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.business.generator.system.area.dao.AreaMapper;
import com.web.business.generator.system.area.model.Area;
import com.web.business.generator.system.industry.dao.IndustryMapper;
import com.web.business.generator.system.industry.model.Industry;
import com.web.business.generator.system.terminal.dao.TerminalMapper;
import com.web.business.generator.system.terminal.model.Terminal;
import com.web.business.generator.system.terminal.model.TerminalModel;
import com.web.business.generator.system.terminal.services.ITerminalService;
import com.web.business.generator.system.user.model.User;
import com.web.business.generator.util.isNull.IsNullUtil;
import com.web.business.generator.util.tree.TreeDataInfo;
import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;

import javassist.expr.NewArray;
import net.bytebuddy.build.Plugin.Engine.PoolStrategy.Eager;

import com.alibaba.druid.sql.visitor.functions.Isnull;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shara.common.util.page.PageUtil;

import com.web.common.util.spring.PageTool;

@Service
public class TerminalServiceImpl implements ITerminalService {
	@Resource
	private TerminalMapper terminalMapper;

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
		result = terminalMapper.deleteByPrimaryKey(id);
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
		List l = PageUtil.getIdsForList(id);
		result = terminalMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(id));
		return result;
	}

	/**
	 * 新增
	 * 
	 * @param record
	 * @return
	 */
	public RestResponse insertSelective(Terminal record) throws Exception {
		RestResponse result = new RestResponse();
		String name = record.getName();
		String cardNum = record.getCardNum();
		List namelist = selectByTerminalName(name);
		try {
			if (namelist != null && namelist.size() > 0) {
				result.setMessage("终端名称已存在！");
				result.setSuccess(false);
				return result;
			}
			List cardlist = selectBycardNum(cardNum);
			if (cardlist != null && cardlist.size() > 0) {
				result.setMessage("终端卡号已存在！");
				result.setSuccess(false);
				return result;
			}
			int r = terminalMapper.insertSelective(record);
			if (r > 0) {
				result.setSuccess(true).setMessage("终端增加成功");
			} else {
				result.setSuccess(false).setMessage("终端增加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 根据终端名称查询终端
	public List<Terminal> selectByTerminalName(String name) {
		List<Terminal> list = terminalMapper.selectByTerminalName(name);
		return list;
	}

	// 根据卡号查询终端
	public List<Terminal> selectBycardNum(String cardNum) {
		List<Terminal> list = terminalMapper.selectBycardNum(cardNum);
		return list;
	}
	
	// 全查终端
		public List<Terminal> selectAll(Terminal terminal) {
			List<Terminal> list = terminalMapper.selectAll(terminal);
			return list;
		}

	/**
	 * 根据主键查询对象
	 * 
	 * @param id
	 * @return
	 */
	public Terminal selectByPrimaryKey(String id) throws Exception {
		return terminalMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改
	 * 
	 * @param record
	 * @return
	 */
	public RestResponse updateByPrimaryKeySelective(Terminal record) throws Exception {
		RestResponse result = new RestResponse();
		String name = record.getName();
		String cardNum = record.getCardNum();
		List<Terminal> namelist = selectByTerminalName(name);
		if (namelist != null && namelist.size() > 0) {
			if (!((namelist.get(0).getId()).equals(record.getId()))) {
				result.setMessage("终端名称已存在！");
				result.setDescription("终端修改失败");
				result.setSuccess(false);
				return result;
			}
		}
		List<Terminal> cardlist = selectBycardNum(cardNum);
		if (cardlist != null && cardlist.size() > 0) {
			if (!((cardlist.get(0).getId()).equals(record.getId()))) {
				result.setMessage("终端卡号已存在！");
				result.setDescription("终端修改失败");

				result.setSuccess(false);
				return result;
			}
		}
		int r = terminalMapper.updateByPrimaryKeySelective(record);
		if (r > 0) {
			result.setSuccess(true).setMessage("终端修改成功");
		} else {
			result.setSuccess(false).setMessage("终端修改失败");
		}
		return result;
	}

	/**
	 * 分页查询数据列表
	 * 
	 * @param record
	 * @param page
	 * @return
	 */
	public PageTool<TerminalModel> selectTerminalByPage(Terminal record, User user, Integer page, Integer pageSize)
			throws Exception {

		List<Terminal> list = new ArrayList<Terminal>();
		List<Terminal> listFullAreaTerminals = new ArrayList<Terminal>(); // 拼好省市区字段信息的数据
		List<Terminal> listAreaInfoTerminals = new ArrayList<Terminal>(); // 加入加入省市区对应的id,name,level
		List<Terminal> listFinalTerminals = new ArrayList<Terminal>(); // 加入加入省市区对应的id,name,level
		//
		// 根据用户类型进行分页查询
		Integer userType = user.getType();
		Integer level = null;
		if (userType == 1) {
			// 管理员--1
			PageHelper.startPage(page, pageSize);
			list = terminalMapper.selectByPropertyByAdminPage(record);
			String areaID = null;
			String industryID = null;

			// 1.加入区域信息全称
			// 1.1区域全称
			listFullAreaTerminals = getAllZoneInfoByList(list);

			// 查询总数
			int count = terminalMapper.selectCountByProperty(record);
			List<TerminalModel> moselList = getTerminalModelList(listFullAreaTerminals);
			PageTool<TerminalModel> pageTool = new PageTool<TerminalModel>(page, count, moselList, pageSize);
			return pageTool;
		} else {
			// 普通用户---0
			// 1.用户对应的区域id
			String userAreaId = user.getUserArea();
			PageTool<TerminalModel> pageTool = null;

			// 2.根据用户的区域id查找下级区域的id,存入list中
			/*
			 * List<Area> sonAreas = areaMapper.selectById(userAreaId); List<String>
			 * sonAreaIds = new ArrayList<>();
			 */

			// 2.备用方法
			// 2.1 根据父id去市id，根据市id查区id
			List<String> cityIds = new ArrayList<>(); // 市id
			List<String> countyIds = new ArrayList<>(); // 区id

			List<String> allIds = new ArrayList<>(); // 市id

			// 2.2 根据用户的区域ID查第一级下属id
			Area userArea = areaMapper.selectByPrimaryKey(userAreaId);
			// Zhj zhj = zhjMapper.selectByPrimaryKey("234234235455");
			if (userArea == null) {
				// 暂时结束程序，第二次查用户区域为空
				return null;

			} else {
				level = userArea.getLevel();

			}

			if (level == null) {
				// 用户区域级别信息为空
				return null;

			} else {
				if (level == 0) {
					// 省级用户
					// 查下属市
					cityIds = areaMapper.selectSonIdsByParentId(userAreaId);

					// 保存市id
					allIds.addAll(cityIds);
					for (String cityId : cityIds) {
						List<String> countyIdsTemp = new ArrayList<>(); // 临时存储孙子id
						// 查下属区
						countyIdsTemp = areaMapper.selectSonIdsByParentId(cityId);
						allIds.addAll(countyIdsTemp);
					}

					
					// 加入当前省的id
					allIds.add(userAreaId);

					
					list = terminalMapper.selectTerminalByIdList(record, allIds);

					// 1.1区域全称
					listFullAreaTerminals = getAllZoneInfoByList(list);
					
					pageTool = getPageTool(listFullAreaTerminals, page, pageSize);

				} else if (level == 1) {
					// 市级用户
					// 查下属区
					countyIds = areaMapper.selectSonIdsByParentId(userAreaId);

					// 添加当前市id
					countyIds.add(userAreaId);

					list = terminalMapper.selectTerminalByIdList(record, countyIds);

					// 1.1区域全称
					listFullAreaTerminals = getAllZoneInfoByList(list);
					
					pageTool = getPageTool(listFullAreaTerminals, page, pageSize);

				} else {
					// 区级用户
					countyIds.add(userAreaId);
					list = terminalMapper.selectTerminalByIdList(record, countyIds);

					// 1.1区域全称
					listFullAreaTerminals = getAllZoneInfoByList(list);
					
					pageTool = getPageTool(listFullAreaTerminals, page, pageSize);
				}
			}

			return pageTool;
		}

	}

	// 转换为model，用于前台展示
	public List<TerminalModel> getTerminalModelList(List<Terminal> list) {
		List<TerminalModel> modelList = new ArrayList<TerminalModel>();
		for (Terminal terminal : list) {
			TerminalModel model = new TerminalModel();
			model.setId(terminal.getId());
			model.setName(terminal.getName());
			model.setCardNum(terminal.getCardNum());
			model.setTerArea(terminal.getTerArea());
			model.setAreaName(terminal.getAreaName());
			model.setTerIndustry(terminal.getTerIndustry());
			model.setIndustryName(terminal.getIndustryName());
			model.setProvinceId(terminal.getProvinceId());
			model.setProvinceName(terminal.getProvinceName());
			model.setCityId(terminal.getCityId());
			model.setCityName(terminal.getCityName());
			model.setZoneId(terminal.getZoneId());
			model.setZoneName(terminal.getZoneName());
			if (terminal.getPositionState() !=null) {
				if (terminal.getPositionState() == 1) {
					model.setPositionState("1");
				} else {
					model.setPositionState("0");
				}
			}/*else {
				model.setPositionState("1");
			}*/
			
			if (terminal.getType() == 1) {
				model.setType("下属终端");
			} else {
				model.setType("区外终端");
			}
			/*
			 * if(terminal.getPosition()!= null) {
			 * model.setLatitude(String.valueOf(terminal.getPosition().getLatitude()));
			 * model.setLongitude(String.valueOf(terminal.getPosition().getLongitude()));
			 * model.setLocateTime(terminal.getPosition().getCreatedate().toLocaleString());
			 * }
			 */
			model.setRemark(terminal.getRemark());
			modelList.add(model);
		}
		return modelList;
	}

	public List<Terminal> getterminalList() {
		List<Terminal> list = terminalMapper.getterminalList();
		return list;
	}

	/**
	 * 给终端的区域拼接省市区全称
	 * 
	 * @param list
	 * @return
	 */
	public List<Terminal> getAllZoneInfoByList(List<Terminal> list) {

		String thirdLevelParentId = null; // 用户当前区域的上级id 第三级区域的父id
		String secondLevelParentId = null; // 第二级区域的父id
		String firstLevelParentId = null; // 第一级区域的父id
		Boolean tag = false;
		String areaId = null; // 用户当前区域的id
		try {
			for (Terminal terminal : list) {
				if(terminal.getTerIndustry() != null) {
					Industry industry = industryMapper.selectByPrimaryKey(terminal.getTerIndustry());
					if(industry != null) {
						terminal.setIndustryName(industry.getIndustName());
					}
				}
				areaId = terminal.getTerArea();
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

	/**
	 * 根据终端Id查所在省市区的名称及id
	 * 
	 * @param list
	 * @return
	 */
	public List<Terminal> getAreaNameIdByList(List<Terminal> list) {

		List<Terminal> listReady = new ArrayList<>();

		String thirdLevelParentId = null; // 用户当前区域的上级id 第三级区域的父id
		String secondLevelParentId = null; // 第二级区域的父id

		boolean tag = false; // 判空标志位

		try {
			for (Terminal terminal : list) {

				String areaId = terminal.getTerArea();

				// 判断区域是否为空
				tag = IsNullUtil.isStrNull(areaId);

				if (!tag) {
					// 不为空进行查询
					Area thirdLevelArea = (Area) areaMapper.selectByPrimaryKey(areaId);
					tag = IsNullUtil.isObjectNull(thirdLevelArea);
					if (!tag) {
						thirdLevelParentId = thirdLevelArea.getParentId();

						// 判断父id不为空
						tag = IsNullUtil.isStrNull(thirdLevelParentId);

						if (!tag) {
							if (thirdLevelParentId.equals("0")) {
								// 用户所在区域最小级别为省
								// areaName 已经为中文
								// 陕西省
								terminal.setProvinceName(thirdLevelArea.getAreaName());
								terminal.setProvinceId(thirdLevelArea.getAreaId());

							} else {
								// 省 市
								Area secondLevelArea = (Area) areaMapper
										.selectByPrimaryKey(thirdLevelArea.getParentId().toString());
								secondLevelParentId = secondLevelArea.getParentId();

								if (secondLevelParentId.equals("0")) {
									// 封装数据 市
									terminal.setCityId(thirdLevelArea.getAreaId());
									terminal.setCityName(thirdLevelArea.getAreaName());

									// 省
									terminal.setProvinceId(secondLevelArea.getAreaId());
									terminal.setProvinceName(secondLevelArea.getAreaName());

								} else {
									// 最小级别为区县
									Area firstLevelArea = (Area) areaMapper
											.selectByPrimaryKey(secondLevelArea.getParentId().toString());

									// 封装数据 市
									terminal.setProvinceName(secondLevelArea.getAreaName());
									terminal.setProvinceId(secondLevelArea.getAreaId());

									// 省
									terminal.setProvinceName(firstLevelArea.getAreaName());
									terminal.setProvinceId(firstLevelArea.getAreaId());

									// 区县
									terminal.setZoneId(thirdLevelArea.getAreaId());
									terminal.setZoneName(thirdLevelArea.getAreaName());

								}
							}
						}

					}
				}

				listReady.add(terminal);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listReady;

	}

	/**
	 * 根据终端行业id来查终端所在的行业名称及id
	 * 
	 * @param list
	 * @return
	 */
	public List<Terminal> getIndustyNameIdByList(List<Terminal> list) {

		List<Terminal> listReady = new ArrayList<>();
		String industryId = null;
		boolean tag = false;

		try {
			for (Terminal terminal : list) {

				industryId = terminal.getTerIndustry();
				tag = IsNullUtil.isStrNull(industryId);
				if (!tag) {
					Industry industry = industryMapper.selectByPrimaryKey(industryId);

					if (industry != null) {
						terminal.setIndustryName(industry.getIndustName());
					}
				} else {
					terminal.setIndustryName("行业未选择");
				}

				listReady.add(terminal);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listReady;

	}

	/**
	 * 普通用户进行分页查询
	 * 
	 * @param list
	 * @param page     当前页
	 * @param pageSize 每页数据条数
	 * @return
	 */
	public PageTool<TerminalModel> getPageTool(List<Terminal> list, int page, int pageSize) {

		PageTool<TerminalModel> pageTool = null;

		// 转换为 TerminalModel
		List<TerminalModel> terminalModelList = getTerminalModelList(list);

		// 进行分页
		int sum = terminalModelList.size(); // 集合的长度
		List<TerminalModel> terminalPageList = new ArrayList<>(); // 存储分页计算后的集合

		// 进行分页
		// 15条 ，pageSize=10 page=1
		if (list.size() < pageSize) {
			// 数据小于10条不用分页
			pageTool = new PageTool<>(page, sum, terminalModelList, pageSize);

		} else {
			// 数据大于10条
			// 页数
			if (page == 1) {
				// 第一页
				pageTool = new PageTool<>(page, sum, terminalModelList, pageSize);

			} else {
				// 第二页及以上，得进行截取原来的集合 15
				// 第二页及以上，得进行截取原来的集合 15
				// 去掉上一页的数据
				int startIndex = (page - 1) * pageSize; // 起始坐标 0 10
				int endIndex = startIndex + 10; // 终止坐标，每次取10条 20

				if (list.size() < endIndex && list.size() == endIndex) {
					terminalPageList = terminalModelList.subList(startIndex, endIndex + 1);// 包含起始，不包含结尾坐标

				} else {
					// 集合长度大于终止坐标 25 10 20
					if (list.size() < endIndex) {
						terminalPageList = terminalModelList.subList(startIndex, list.size());// 包含起始，不包含结尾坐标

					} else {
						terminalPageList = terminalModelList.subList(startIndex, startIndex + 10);// 包含起始，不包含结尾坐标

					}

				}

				pageTool = new PageTool<>(page, sum, terminalPageList, pageSize);

			}

		}

		return pageTool;

	}

	/**
	 * 根据终端 list获取所有区域list
	 * 
	 * @param list
	 * @return
	 */
	public List<Area> getAreasByTerminalList(List<Terminal> list) {

		List<Area> areas = new ArrayList<>();
		List<String> areaIds = new ArrayList<>();// 存区域id
		String areaId = null;
		
		for (Terminal terminal : list) {
			
			areaId = terminal.getTerArea();
			
			if (!areaIds.contains(areaId)) {
				areaIds.add(areaId);

			}
		}

		// 根据区域id集合查区域
		try {
			if(areaIds.size() > 0) {
			areas = areaMapper.selectAreaByIds(areaIds);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return areas;

	}

	/**
	 * 生成区域节点
	 * 根据区域list和级别返回 对应的省市区 list区域节点
	 * 
	 * @param areas  区域集合
	 * @param level  区域级别:0省，1市，2区
	 * @param zoneFlag  1--区内，0--区外（区内外标志）
	 * @return
	 */
	public List<TreeDataInfo> getZoneNodeList(List<Area> areas, Integer level, String zoneFlag) {

		String zoneNum = zoneFlag;// 1--区内，0--区外
		List<TreeDataInfo> zoneList = new ArrayList<>();
		String parentId = null;
		String areaId = null;
		Long terminalSum = null;
		boolean tag = false;
		
		List<String> areaIds = new ArrayList<>();
		
		if (zoneNum.equals("1")) {
			// 区内
			for (Area area : areas) {
				areaId = area.getAreaId();
				Integer areaLevel = area.getLevel();
				tag = IsNullUtil.isIntegerNull(areaLevel);
				
				if (!tag) {
					//用户区域级别不为空
					TreeDataInfo areaNode = new TreeDataInfo();

					if (level == areaLevel) {
						if (level == 2) {
							// 区
							areaNode.setId(area.getAreaId());
							areaNode.setName(area.getAreaName());
							areaNode.setParent(true);
							areaNode.setIcon("");
							areaNode.setIconOpen("");
							areaNode.setIconClose("");
							parentId = area.getParentId();
							tag = IsNullUtil.isStrNull(parentId);
							if (!tag) {
								//父id不为空
								areaNode.setpId(parentId);
							}
							//区下属终端
							terminalSum = terminalMapper.sumTerminalByAreaId(areaId);
							areaNode.setSonNodesSum(terminalSum);
							
						} else if (level == 1) {
							// 市
							areaNode.setId(area.getAreaId());
							areaNode.setName(area.getAreaName());
							areaNode.setParent(true);
							areaNode.setIcon("");
							areaNode.setIconOpen("");
							areaNode.setIconClose("");
							parentId = area.getParentId();
							tag = IsNullUtil.isStrNull(parentId);
							if (!tag) {
								//父id不为空
								areaNode.setpId(parentId);
							}
							
							//市下属区域
							areaIds = areaMapper.selectSonIdListByParentId(areaId);
							areaIds.add(areaId);
							//市及下属区的下属终端
							terminalSum = terminalMapper.sumTerminalByAreaIds(areaIds);
							areaNode.setSonNodesSum(terminalSum);

						} else {
							// 省
							areaNode.setId(area.getAreaId());
							areaNode.setName(area.getAreaName());
							areaNode.setParent(true);
							areaNode.setpId("inZoneNode");
							areaNode.setIcon("");
							areaNode.setIconOpen("");
							areaNode.setIconClose("");
							
							//省下属区域
							areaIds = areaMapper.selectSonIdListByParentId(areaId);
							areaIds.add(areaId);
							//省及下属区域的下属终端
							terminalSum = terminalMapper.sumTerminalByAreaIds(areaIds);
							areaNode.setSonNodesSum(terminalSum);

						}
						zoneList.add(areaNode);
					}
				
				}

			}
		} else {
			// 区外
			for (Area area : areas) {
				areaId = area.getAreaId();
				Integer areaLevel = area.getLevel();
				tag = IsNullUtil.isIntegerNull(areaLevel);
				
				if (!tag) {
					//用户区域级别不为空
					TreeDataInfo areaNode = new TreeDataInfo();

					if (level == areaLevel) {
						if (level == 2) {
							// 区
							areaNode.setId(area.getAreaId());
							areaNode.setName(area.getAreaName());
							areaNode.setParent(true);
							areaNode.setIcon("");
							areaNode.setIconOpen("");
							areaNode.setIconClose("");
							parentId = area.getParentId();
							tag = IsNullUtil.isStrNull(parentId);
							if (!tag) {
								//父id不为空
								areaNode.setpId(parentId);
							}
							
							//区下属终端
							terminalSum = terminalMapper.sumTerminalByAreaId(areaId);
							areaNode.setSonNodesSum(terminalSum);
							

						} else if (level == 1) {
							// 市
							areaNode.setId(area.getAreaId());
							areaNode.setName(area.getAreaName());
							areaNode.setParent(true);
							areaNode.setIcon("");
							areaNode.setIconOpen("");
							areaNode.setIconClose("");
							parentId = area.getParentId();
							tag = IsNullUtil.isStrNull(parentId);
							if (!tag) {
								//父id不为空
								areaNode.setpId(parentId);
							}
							
							//市下属区域
							areaIds = areaMapper.selectSonIdListByParentId(areaId);
							areaIds.add(areaId);
							//市及下属区的下属终端
							terminalSum = terminalMapper.sumTerminalByAreaIds(areaIds);
							areaNode.setSonNodesSum(terminalSum);

						} else {
							// 省
							areaNode.setId(area.getAreaId());
							areaNode.setName(area.getAreaName());
							areaNode.setParent(true);
							areaNode.setpId("outZoneNode");
							areaNode.setIcon("");
							areaNode.setIconOpen("");
							areaNode.setIconClose("");
							
							//省下属区域
							areaIds = areaMapper.selectSonIdListByParentId(areaId);
							areaIds.add(areaId);
							
							//省及下属区域的下属终端
							terminalSum = terminalMapper.sumTerminalByAreaIds(areaIds);
							areaNode.setSonNodesSum(terminalSum);

						}
						zoneList.add(areaNode);
					}
				
				}

			}

		}
		return zoneList;
	}
	
	/**
	 * 查找对应区域的下属终端集合
	 * 根据终端list，省市区id;返回zoneNodeList
	 * @param terminals  终端
	 * @param areaNodes  区域
	 * @param zoneFlag   1--区内，0--区外（区内外标志）
	 * @return
	 */
	public List<TreeDataInfo> getTerminalNodeList(List<Terminal> terminals,List<TreeDataInfo> areaNodes,String zoneFlag) {
		
		List<TreeDataInfo> zoneNodeList = new ArrayList<>(); //结果集合
		
		Integer terminalType = null; //终端类型
		String terminalAreaId = null;//终端所在区域id
		String terminalId = null;//终端id
		String terminalName = null;
		boolean tag = false;
		
		if (zoneFlag.equals("1")) {
			//区内
			for (Terminal terminal : terminals) {
				
				terminalId = terminal.getId();
				terminalType = terminal.getType();
				
				
				tag = IsNullUtil.isIntegerNull(terminalType);
				
				if (!tag) {
					//类型不为空
					if (terminalType ==1) {
						
						//区内终端
						terminalAreaId = terminal.getTerArea();
						terminalName = terminal.getName();
						tag = IsNullUtil.isStrNull(terminalAreaId);
						
						if (!tag) {
							
							//终端的区域id不为空
							String areaId = null;
							for (TreeDataInfo areaNode : areaNodes) {
								
								areaId = areaNode.getId();
								
								if (terminalAreaId.equals(areaId)) {
									//当前区域的下属终端
									//为当前节点设置节点属性
									TreeDataInfo terminalNode = new TreeDataInfo();//终端节点
									terminalNode.setId(terminalId);//终端节点id
									terminalNode.setTerminalId(terminalId);
									terminalNode.setCard(terminal.getCardNum());
									terminalNode.setParent(true);
									terminalNode.setpId(areaId);//当前区域为父
									terminalNode.setIcon("");
									terminalNode.setIconOpen("");
									terminalNode.setIconClose("");
									terminalNode.setType("inzone");
									tag = IsNullUtil.isStrNull(terminalName);
									
									if (!tag) {
										terminalNode.setName(terminalName+"("+terminal.getCardNum()+")");

									}
									
									zoneNodeList.add(terminalNode);

								}
							}
							
						}
						
					}
					
					
				}
				
				
			}
			
		}else {
			//区外
			for (Terminal terminal : terminals) {
				terminalType = terminal.getType();
				terminalId = terminal.getId();
				tag = IsNullUtil.isIntegerNull(terminalType);
				
				if (!tag) {
					//类型不为空
					if (terminalType ==0) {
						
						//区外终端
						terminalAreaId = terminal.getTerArea();
						terminalName = terminal.getName();
						tag = IsNullUtil.isStrNull(terminalAreaId);
						
						if (!tag) {
							
							//终端的区域id不为空
							String areaId = null;
							for (TreeDataInfo areaNode : areaNodes) {
								
								areaId = areaNode.getId();
								
								if (terminalAreaId.equals(areaId)) {
									//当前区域的下属终端
									//为当前节点设置节点属性
									TreeDataInfo terminalNode = new TreeDataInfo();//终端节点
									terminalNode.setId(terminalId);//终端节点id
									terminalNode.setTerminalId(terminalId);
									terminalNode.setCard(terminal.getCardNum());
									terminalNode.setParent(true);
									terminalNode.setpId(areaId);//当前区域指为父
									terminalNode.setIcon("");
									terminalNode.setIconOpen("");
									terminalNode.setIconClose("");
									terminalNode.setType("outzone");
									tag = IsNullUtil.isStrNull(terminalName);
									if (!tag) {
										terminalNode.setName(terminalName+"("+terminal.getCardNum()+")");

									}
									zoneNodeList.add(terminalNode);

								}
							}
							
						}
						
					}
					
					
				}
				
				
			}
			
		
			
		}
		return zoneNodeList;
	}
	
	
	/**
	 * 统计本级及下属区域的终端总数
	 * @param list  区域节点的集合
	 * @param zoneNum  1:区内。0：区外
	 * @return
	 */
	public List<TreeDataInfo> countByIdAndType(List<TreeDataInfo>list,String zoneNum) {
		
		List<TreeDataInfo> resultList=  new ArrayList<>();
		List<String> areaIds = new ArrayList<>();
		Long sum = 0L;
		
		for (TreeDataInfo treeDataInfo : list) {
			
			//注意传入的id是否是加工后的，加入区内外标志位
			areaIds = areaMapper.selectSonIdListByParentId(treeDataInfo.getId());
			
			if (areaIds.size() > 0) {
				areaIds.add(treeDataInfo.getId());
				sum = terminalMapper.sumTerminalByAreaIdAndType(areaIds,zoneNum);
				treeDataInfo.setName(treeDataInfo.getName()+"["+String.valueOf(sum)+"]");
				treeDataInfo.setSonNodesSum(sum);
				resultList.add(treeDataInfo);
				
			}else {
				areaIds.add(treeDataInfo.getId());
				sum = terminalMapper.sumTerminalByAreaIdAndType(areaIds,zoneNum);
				treeDataInfo.setName(treeDataInfo.getName()+"["+String.valueOf(sum)+"]");
				treeDataInfo.setSonNodesSum(sum);
				resultList.add(treeDataInfo);
			}
			
		}
		
		return resultList;
	}
	
	
	/**
	 * 统计本级及下属区域的终端
	 * @param list  区域节点的集合
	 * @param zoneNum  1:区内。0：区外
	 * @return
	 */
	public List<Terminal> getTerminalByAreaId(String id,Terminal terminal) {

		List<Terminal> resultList = new ArrayList<>();
		
		List<String> areaIds = new ArrayList<>();
		
		
			//普通用户
			// 注意传入的id是否是加工后的，加入区内外标志位
			areaIds = areaMapper.selectSonIdListByParentId(id);

			areaIds.add(id);
			resultList = terminalMapper.selectTerminalByAreaIds(areaIds,terminal);
		
		    return resultList;
	}

	@Override
	public List<Terminal> selectByCardNum(String CARD_NUM) {
		return terminalMapper.selectByCardNum(CARD_NUM);
	}
	
	
	/**
	 * 组装无区域的  区内外终端节点
	 * @param list
	 * @param pid  父节点id
	 * @param type:终端的类型：inzone,outzone
	 * @return
	 */
	public List<TreeDataInfo> makeNoAreaTerNode(List<Terminal> list,String pid,String type) {
		
		List<TreeDataInfo> terNodeList = new ArrayList<TreeDataInfo>();
		
		for (Terminal terminal : list) {
			
			TreeDataInfo terminalNode = new TreeDataInfo();//终端节点
			
			terminalNode.setId(terminal.getId());//终端节点id
			terminalNode.setTerminalId(terminal.getId());
			terminalNode.setCard(terminal.getCardNum());
			terminalNode.setParent(true);
			terminalNode.setpId(pid);//当前区域为父id:下属终端/非下属终端节点
			terminalNode.setIcon("");
			terminalNode.setIconOpen("");
			terminalNode.setIconClose("");
			//terminalNode.setType("inzone");//类型
			//terminalNode.setType("outzone");//类型
			terminalNode.setType(type);//类型
			boolean tag = IsNullUtil.isStrNull(terminal.getName());
			
			if (!tag) {
				terminalNode.setName(terminal.getName()+"("+terminal.getCardNum()+")");

			}
			
			terNodeList.add(terminalNode);
			
		}
		
		
		return terNodeList;
		
		
	}
	
	
	
}





