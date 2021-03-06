package com.web.business.generator.system.user.services.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;

import net.sf.jsqlparser.statement.select.Join;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shara.common.util.page.PageUtil;
import com.shara.common.util.page.ValidateUtil;

import com.web.common.util.spring.PageTool;

import com.web.business.generator.system.user.model.User;
import com.web.business.generator.system.user.model.UserModel;
import com.web.business.generator.system.area.dao.AreaMapper;
import com.web.business.generator.system.area.model.Area;
import com.web.business.generator.system.area.services.impl.AreaServiceImpl;
import com.web.business.generator.system.industry.dao.IndustryMapper;
import com.web.business.generator.system.industry.model.Industry;
import com.web.business.generator.system.user.dao.UserMapper;
import com.web.business.generator.system.user.services.IUserService;
import com.web.business.generator.util.AnalysisService;
import com.web.business.generator.util.isNull.IsNullUtil;

@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper userMapper;

	@Resource
	private AreaMapper areaMapper;

	@Resource
	private IndustryMapper industryMapper;
	
	@Resource
	private AreaServiceImpl areaServiceImpl;

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteByPrimaryKey(String id) throws Exception {
		int result = -1;
		result = userMapper.deleteByPrimaryKey(id);
		return result;
	}

	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public RestResponse deleteBatchByPrimaryKey(String id, User loginInfo) throws Exception {
		RestResponse result = new RestResponse();
		List userId = PageUtil.getIdsForList(id);
		int r = userMapper.deleteBatchByPrimaryKey(userId);
		if (r > 0) {
			result.setMessage("删除成功");
			result.setSuccess(true);
			return result;
		} else {
			result.setMessage("删除失败");
			result.setSuccess(false);
			return result;
		}
	}

	/**
	 * 新增
	 * 
	 * @param record
	 * @return
	 */
	public RestResponse insertSelective(User record) throws Exception {
		RestResponse result = new RestResponse();
		String userName = record.getName();
		List listUser = userMapper.selectByUserName(userName);
		if (listUser != null && listUser.size() > 0) {
			result.setMessage("用户名存在，请修改");
			result.setSuccess(false);
			return result;
		}
		int r = userMapper.insertSelective(record);
		if (r > 0) {
			result.setSuccess(true).setMessage("用户增加成功");
		} else {
			result.setSuccess(false).setMessage("用户增加失败");
		}
		return result;

	}

	/**
	 * 根据主键查询对象
	 * 
	 * @param id
	 * @return
	 */
	public User selectByPrimaryKey(String id) throws Exception {
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param record
	 * @return
	 */
	public RestResponse updateByPrimaryKeySelective(User record, HttpSession session) throws Exception {
		RestResponse result = new RestResponse();
		User userBean = (User) session.getAttribute("loginInfo");
		String userLogin = userBean.getName();
		String userName = record.getName();
		List<User> nameList = selectByUserName(userName);
		if (nameList != null && nameList.size() > 0) {
			if (!((nameList.get(0)).getId()).equals(record.getId())) {
				result.setMessage("用户名已存在！");
				result.setSuccess(false);
				return result;
			}

		}

		/*
		 * String userPhone = record.getPhone(); List<User> phoneList =
		 * selectByUserPhone(userPhone); if (phoneList != null && phoneList.size() > 0)
		 * { if (!((phoneList.get(0)).getId()).equals(record.getId())) {
		 * result.setMessage("号码已存在！"); result.setSuccess(false); return result; }
		 * 
		 * }
		 */

		User userInfo = selectByPrimaryKey(record.getId());
		userInfo.setName(record.getName());
		userInfo.setPhone(record.getPhone());
		userInfo.setUserArea(record.getUserArea());
		userInfo.setUserIndustry(record.getUserIndustry());
		userInfo.setLastIp(record.getLastIp());
		
		int r = userMapper.updateByPrimaryKeySelective(userInfo);
		
		if (userLogin.equals(userName)) {
			AnalysisService.userFlag = 1;
			session.setAttribute("loginInfo", userInfo);// 修改用户本身要同时更新Session
		}

		if (r > 0) {
			result.setSuccess(true).setMessage("用户修改成功");
		} else {
			result.setSuccess(false).setMessage("用户修改失败");
		}
		return result;
	}

	/**
	 * 查询
	 * 
	 * @param userQuery
	 * @param record
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	/*
	 * public PageTool<UserModel> selectUserSearchByPage(List<User> userQuery, User
	 * record, Integer page, Integer pageSize) throws Exception {
	 * 
	 * List<User> list = new ArrayList<User>(); // 初次从数据库查出来的数据 List<User> listReady
	 * = new ArrayList<User>(); // 拼好省市区字段信息的数据 List<User> listAreaInfoUsers = new
	 * ArrayList<User>(); // 加入加入省市区对应的id,name,level
	 * 
	 * Map<String, Map<String, String>> areaData = new HashMap<>();
	 * 
	 * PageHelper.startPage(page, pageSize); User model = new User();
	 * 
	 * try { if (record.getType() == 1) { String idStr = null; for (User user :
	 * userQuery) { String areaName = user.getAreaName(); if (areaName == null) {
	 * model.setAreaName(areaName); } else { List<String> areaIds =
	 * areaMapper.selectByIds(areaName); String idStrTemp = ""; for (String id :
	 * areaIds) { idStrTemp += id + ","; } idStr = idStrTemp.substring(0,
	 * idStrTemp.length() - 1); }
	 * 
	 * model.setPhone(user.getPhone()); model.setName(user.getName()); }
	 * 
	 * // 管理员查询所有区域 list = userMapper.selectByPropertyBySearchAdminPage(model,
	 * idStr);
	 * 
	 * // 加入省市区对应的id,name,level for (User user : list) {
	 * 
	 * // 根据用户id查对应省市区信息 areaData = getAreaInfoByUserId(user.getId());
	 * 
	 * // 存入map user.setAreaInfoMap(areaData);
	 * 
	 * // 将map中的省市区name,id拿出来给user // 判断字段非空再赋值 if (areaData != null) { if
	 * (areaData.get("province") != null) {
	 * user.setProvinceId(areaData.get("province").get("id"));
	 * user.setProvinceName(areaData.get("province").get("name")); } if
	 * (areaData.get("city") != null) {
	 * user.setCityId(areaData.get("city").get("id"));
	 * user.setCityName(areaData.get("city").get("name")); } if
	 * (areaData.get("zone") != null) {
	 * user.setZoneId(areaData.get("zone").get("id"));
	 * user.setZoneName(areaData.get("zone").get("name"));
	 * 
	 * } }
	 * 
	 * listAreaInfoUsers.add(user); }
	 * 
	 * // 拼接省市区县 名称 listReady = getAllZoneInfoByList(listAreaInfoUsers);
	 * 
	 * } else {
	 * 
	 * // 根据UserArea 查询其所有下级 List<String> sonAreaIds =
	 * areaMapper.selectSonIdListByParentId(record.getUserArea());
	 * sonAreaIds.add(record.getUserArea());// 将当前用户的区域添加 // 根据区域id集合来查用户 String
	 * idStrTemp = ""; for (String id : sonAreaIds) { idStrTemp += id + ","; }
	 * String idStr = idStrTemp.substring(0, idStrTemp.length() - 1); // list =
	 * userMapper.selectByPropertyByPage(record); list =
	 * userMapper.selectByPropertyByPageList(record, idStr);
	 * 
	 * // 加入省市区对应的id,name,level for (User user : list) {
	 * 
	 * // 根据用户id查对应省市区信息 areaData = getAreaInfoByUserId(user.getId());
	 * user.setAreaInfoMap(areaData);
	 * 
	 * // 将map中的省市区name,id拿出来给user // 判断字段非空再赋值 if (areaData.get("province") !=
	 * null) { user.setProvinceId(areaData.get("province").get("id"));
	 * user.setProvinceName(areaData.get("province").get("name")); } if
	 * (areaData.get("city") != null) {
	 * user.setCityId(areaData.get("city").get("id"));
	 * user.setCityName(areaData.get("city").get("name")); } if
	 * (areaData.get("zone") != null) {
	 * user.setZoneId(areaData.get("zone").get("id"));
	 * user.setZoneName(areaData.get("zone").get("name"));
	 * 
	 * } // user.setProvinceName(areaData.get(key)); listAreaInfoUsers.add(user); }
	 * 
	 * // 拼接省市区县 名称 listReady = getAllZoneInfoByList(listAreaInfoUsers);
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * // 根据用户类型查询总数返回前台 if (record.getType() == 1) { PageInfo<User> pageInfo = new
	 * PageInfo<User>(list); int count =
	 * userMapper.selectCountByAdminProperty(record); // 转化为areaModel
	 * List<UserModel> userList = getUserList(listReady); long i =
	 * pageInfo.getTotal(); PageTool<UserModel> pageTool = new
	 * PageTool<UserModel>(page, count, userList, pageSize); return pageTool; } else
	 * { PageInfo<User> pageInfo = new PageInfo<User>(list); int count =
	 * userMapper.selectCountByUserProperty(record); // 转化为areaModel List<UserModel>
	 * userList = getUserList(listReady); long i = pageInfo.getTotal();
	 * PageTool<UserModel> pageTool = new PageTool<UserModel>(page, count, userList,
	 * pageSize); return pageTool; }
	 * 
	 * }
	 */

	/**
	 * 分页查询数据列表
	 * 
	 * @param record
	 * @param page
	 * @return
	 */
	public PageTool<UserModel> selectUserByPage( String queryName,String queryArea, User record, Integer page, Integer pageSize) throws Exception {

		List<User> list = new ArrayList<User>(); // 初次从数据库查出来的数据
		List<User> listReady = new ArrayList<User>(); // 拼好省市区字段信息的数据
		List<User> listAreaInfoUsers = new ArrayList<User>(); // 加入加入省市区对应的id,name,level

		Map<String, Map<String, String>> areaData = new HashMap<>();

		
		User userQuery = new User();
		
		
		try {
			if (record.getType() == 1) {
				String idStr = "";
				List<String> sonIds = new ArrayList<String>();				
				userQuery.setName(queryName);
				List<Area> queryAreaList = areaMapper.selectByName(queryArea);
				//模糊查询区域及下属区域
				if(queryAreaList != null && queryAreaList.size() > 0) {
					for(Area area:queryAreaList) {
						sonIds.add(area.getAreaId());
					}
					
				}
				PageHelper.startPage(page, pageSize);
				list = userMapper.selectByUserNameAndAreaIds(queryName,sonIds);
				// 加入省市区对应的id,name,level
				for (User user : list) {

					// 根据用户id查对应省市区信息
					areaData = getAreaInfoByUserId(user.getId());

					// 存入map
					user.setAreaInfoMap(areaData);

					// 将map中的省市区name,id拿出来给user
					// 判断字段非空再赋值
					user.setProvinceId("");
					user.setProvinceName("");
					user.setCityId("");
					user.setCityName("");
					user.setZoneId("");
					user.setZoneName("");
					if (areaData != null) {
						if (areaData.get("province") != null) {
							user.setProvinceId(areaData.get("province").get("id"));
							user.setProvinceName(areaData.get("province").get("name"));
						}
						if (areaData.get("city") != null) {
							user.setCityId(areaData.get("city").get("id"));
							user.setCityName(areaData.get("city").get("name"));
						}
						if (areaData.get("zone") != null) {
							user.setZoneId(areaData.get("zone").get("id"));
							user.setZoneName(areaData.get("zone").get("name"));

						}
					}

					listAreaInfoUsers.add(user);
				}

				// 拼接省市区县 名称
				listReady = getAllZoneInfoByList(listAreaInfoUsers);

			} else {
				String areaStrId = null;
				List<String> areaIds  = areaServiceImpl.getSonAreasByAreaName(record , queryArea);
				
				PageHelper.startPage(page, pageSize);
				list = userMapper.selectByUserNameAndAreaIds(queryName,areaIds);
				// 加入省市区对应的id,name,level
				for (User user : list) {

					// 根据用户id查对应省市区信息
					areaData = getAreaInfoByUserId(user.getId());
					user.setAreaInfoMap(areaData);

					// 将map中的省市区name,id拿出来给user
					// 判断字段非空再赋值
					user.setProvinceId("");
					user.setProvinceName("");
					user.setCityId("");
					user.setCityName("");
					user.setZoneId("");
					user.setZoneName("");
					if (areaData.get("province") != null) {
						user.setProvinceId(areaData.get("province").get("id"));
						user.setProvinceName(areaData.get("province").get("name"));
					}
					if (areaData.get("city") != null) {
						user.setCityId(areaData.get("city").get("id"));
						user.setCityName(areaData.get("city").get("name"));
					}
					if (areaData.get("zone") != null) {
						user.setZoneId(areaData.get("zone").get("id"));
						user.setZoneName(areaData.get("zone").get("name"));
					}
					// user.setProvinceName(areaData.get(key));
					listAreaInfoUsers.add(user);
				}

				// 拼接省市区县 名称
				listReady = getAllZoneInfoByList(listAreaInfoUsers);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 根据用户类型查询总数返回前台
		if (record.getType() == 1) {
			PageInfo<User> pageInfo = new PageInfo<User>(list);
			int count = userMapper.selectCountByAdminProperty(record);
			//int count =list.size();
			// 转化为areaModel
			List<UserModel> userList = getUserList(listReady);
			long i = pageInfo.getTotal();
			PageTool<UserModel> pageTool = new PageTool<UserModel>(page, count, userList, pageSize);
			return pageTool;
		} else {
			PageInfo<User> pageInfo = new PageInfo<User>(list);
			int count = userMapper.selectCountByUserProperty(record);
			// 转化为areaModel
			List<UserModel> userList = getUserList(listReady);
			long i = pageInfo.getTotal();
			PageTool<UserModel> pageTool = new PageTool<UserModel>(page, count, userList, pageSize);
			return pageTool;
		}

	}

	public List<UserModel> getUserList(List<User> list) {
		List<UserModel> modelList = new ArrayList<UserModel>();
		for (User user : list) {
			UserModel userModel = new UserModel();
			userModel.setAreaName(user.getAreaName());
			userModel.setIndustryName(user.getIndustryName());
			userModel.setIndustryId(user.getUserIndustry());
			userModel.setUserName(user.getName());
			userModel.setUserPhone(user.getPhone());
			userModel.setUserID(user.getId());
			userModel.setAreaInfoMap(user.getAreaInfoMap());
			userModel.setProvinceId(user.getProvinceId());
			userModel.setProvinceName(user.getProvinceName());
			userModel.setCityId(user.getCityId());
			userModel.setCityName(user.getCityName());
			userModel.setZoneId(user.getZoneId());
			userModel.setZoneName(user.getZoneName());
			if (user.getType() != null) {
				if (user.getType() == 1) {
					userModel.setUserType("管理员");
				} else {
					userModel.setUserType("普通用户");
				}
			}

			userModel.setUserRemark(user.getRemark());
			modelList.add(userModel);
		}

		return modelList;
	}

	/**
	 * 根据用户名和密码查询用户
	 */
	public List<User> findUserByNameAndPassword(String userName, String password) {

		List<User> list = userMapper.findUserByNameAndPassword(userName, password);
		return list;
	}

	/**
	 * 重置用户密码
	 * 
	 * @param record
	 * @return
	 */

	public RestResponse updateByPrimaryPwd(User record) throws Exception {
		RestResponse result = new RestResponse();
		int r = userMapper.updateByPrimaryKeySelective(record);
		if (r > 0) {
			result.setSuccess(true).setMessage("重置用户密码成功");
		} else {
			result.setSuccess(false).setMessage("重置用户密码失败");
		}

		return result;
	}

	/**
	 * 修改用户密码
	 * 
	 * @param record
	 * @return
	 */
	public RestResponse updateByPrimaryChangePwd(User record, HttpSession session) throws Exception {
		RestResponse result = new RestResponse();
		int r = userMapper.updateByPrimaryKeySelective(record);
		if (r > 0) {
			result.setSuccess(true).setMessage("修改用户密码成功");
			AnalysisService.userFlag = 1;
			session.setAttribute("loginInfo", record);// 修改用户本身要同时更新Session
		} else {
			result.setSuccess(false).setMessage("修改用户密码失败");
		}

		return result;
	}

	// 根据用户名进行查询
	public List<User> selectByUserName(String userName) {
		List<User> list = userMapper.selectByUserName(userName);
		return list;
	}

	// 根据电话号码进行查询
	public List<User> selectByUserPhone(String userPhone) {
		List<User> list = userMapper.selectByUserPhone(userPhone);
		return list;
	}

	/**
	 * 拼接用户区域名全称
	 * @param list
	 * @return
	 */
	public List<User> getAllZoneInfoByList(List<User> list) {
		
		String thirdLevelParentId = null; // 用户当前区域的上级id 第三级区域的父id
		String secondLevelParentId = null; // 第二级区域的父id

		String areaId = null; // 用户当前区域的id

		boolean tag = false;

		try {
			for (User user : list) {
				areaId = user.getUserArea();
				tag = IsNullUtil.isStrNull(areaId);
				if (!tag) {

					// 根据用户所在的区域id查父级id
					// 第三级别区域：区县
					Area thirdLevelArea = (Area) areaMapper.selectByPrimaryKey(areaId);
					thirdLevelParentId = thirdLevelArea.getParentId();
					tag = IsNullUtil.isStrNull(thirdLevelParentId);
					if (!tag) {
						if (thirdLevelParentId.equals("0")) {
							// 用户所在区域最小级别为省
							// areaName 已经为中文
							// 陕西省
							user.setAreaName(thirdLevelArea.getAreaName());
							
						} else {
							// 存在上级区域
							String userAreaName = null;
							secondLevelParentId = thirdLevelArea.getParentId();
							tag = IsNullUtil.isStrNull(secondLevelParentId);
							if (!tag) {
								// 拼接区域名
								Area secondLevelArea = (Area) areaMapper
										.selectByPrimaryKey(secondLevelParentId);
								secondLevelParentId = secondLevelArea.getParentId();
								
								tag = IsNullUtil.isStrNull(secondLevelParentId);
								
								if (!tag) {
									if (secondLevelParentId.equals("0")) {
										// 目前最小级别为市
										userAreaName = secondLevelArea.getAreaName() + thirdLevelArea.getAreaName();
										// 陕西省西安市
										user.setAreaName(userAreaName);

									} else {
										// 最小级别为区县
										Area firstLevelArea = (Area) areaMapper
												.selectByPrimaryKey(secondLevelParentId);
										userAreaName = firstLevelArea.getAreaName() + secondLevelArea.getAreaName()
												+ thirdLevelArea.getAreaName();

										// 陕西省西安市长安区
										user.setAreaName(userAreaName);

									}
								}
								
							

							}else {
								user.setAreaName(thirdLevelArea.getAreaName()+"(上级区域未指定)");

							}
						}
					}else {
						user.setAreaName(thirdLevelArea.getAreaName()+"(上级区域未指定)");

					}
				}else {
					// 当前用户所在区域为 空，结束本次循环
					user.setAreaName("当前用户所在区域未指定");

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 根据用户Id查所在省市区的名称及id
	@Override
	public Map<String, Map<String, String>> getAreaInfoByUserId(String userId) {

		Map<String, Map<String, String>> data = new HashMap<>();

		Map<String, String> province = new HashMap<>(); // 省
		Map<String, String> city = new HashMap<>(); // 市
		Map<String, String> zone = new HashMap<>(); // 区县

		String thirdLevelParentId = null; // 用户当前区域的上级id 第三级区域的父id
		String secondLevelParentId = null; // 第二级区域的父id
		String firstLevelParentId = null; // 第一级区域的父id

		boolean tag = false;
		String parentId = null;

		User user = null;
		try {
			user = userMapper.selectByPrimaryKey(userId);
			tag = IsNullUtil.isObjectNull(user);
			if (!tag) {
				// 不为空

				String areaId = user.getUserArea();
				tag = IsNullUtil.isStrNull(areaId);
				if (!tag) {
					// 用户区域不为空
					Area thirdLevelArea = (Area) areaMapper.selectByPrimaryKey(areaId);
					thirdLevelParentId = thirdLevelArea.getParentId();
					tag = IsNullUtil.isStrNull(thirdLevelParentId);

					if (!tag) {
						if (thirdLevelParentId.equals("0")) {
							// 用户所在区域最小级别为省
							// areaName 已经为中文
							// 陕西省
							province.put("name", thirdLevelArea.getAreaName());
							province.put("id", thirdLevelArea.getAreaId());
							province.put("level", thirdLevelArea.getLevel().toString());

							data.put("province", province);

						} else {
							// 省 市
							Area secondLevelArea = (Area) areaMapper.selectByPrimaryKey(thirdLevelParentId);
							secondLevelParentId = secondLevelArea.getParentId();
							tag = IsNullUtil.isStrNull(secondLevelParentId);
							if (!tag) {
								if (secondLevelParentId.equals("0")) {
									// 封装数据
									province.put("name", secondLevelArea.getAreaName());
									province.put("id", secondLevelArea.getAreaId());
									province.put("level", secondLevelArea.getLevel().toString());

									city.put("name", thirdLevelArea.getAreaName());
									city.put("id", thirdLevelArea.getAreaId());
									city.put("level", thirdLevelArea.getLevel().toString());

									data.put("province", province);
									data.put("city", city);

								} else {
									// 最小级别为区县
									Area firstLevelArea = (Area) areaMapper.selectByPrimaryKey(secondLevelParentId);

									// 封装数据
									zone.put("name", thirdLevelArea.getAreaName());
									zone.put("id", thirdLevelArea.getAreaId());
									zone.put("level", thirdLevelArea.getLevel().toString());

									city.put("name", secondLevelArea.getAreaName());
									city.put("id", secondLevelArea.getAreaId());
									city.put("level", secondLevelArea.getLevel().toString());

									province.put("name", firstLevelArea.getAreaName());
									province.put("id", firstLevelArea.getAreaId());
									province.put("level", firstLevelArea.getLevel().toString());

									data.put("province", province);
									data.put("city", city);
									data.put("zone", zone);

								}
							}

						}
					}

				} else {
					// 用户对应的区域id为空
					return null;
				}
			} else {
				// 为空
				// 当前id对应的用户在数据库不存在
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;

	}

	
	


	


	
}