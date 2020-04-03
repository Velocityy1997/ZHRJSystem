package com.web.business.system.user.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shara.common.util.page.Json;
import com.shara.common.util.page.PageUtil;
import com.shara.common.util.page.ValidateUtil;
import com.web.business.system.user.dao.mysql.mybatis.SystemUserMapper;
import com.web.business.system.user.dao.mysql.mybatis.SystemUserRoleRaMapper;
import com.web.business.system.user.model.SystemUser;
import com.web.business.system.user.model.SystemUserRoleRa;
import com.web.business.system.user.services.ISystemUserService;

/**
 * 用户管理services层接口实现
 * 
 * @author
 * @date
 */
@Service
public class SystemUserServiceImpl implements ISystemUserService {
	@Resource
	// 系统管理/用户管理dao层
	private SystemUserMapper systemUserMapper;
	@Resource
	// 系统管理/用户--角色管理dao层
	private SystemUserRoleRaMapper systemUserRoleRaMapper;

	/**
	 * 根据主键删除(物理删除)
	 * 
	 * @author
	 * @date
	 * @param userId
	 * @return
	 */
	public Json deleteByPrimaryKey(Long userId) throws Exception {
		Json json = new Json();
		int result = -1;
		// 通过主键删除
		result = systemUserMapper.deleteByPrimaryKey(userId);
		// 判断删除是否成功
		if (result > 0) {
			json.setSuccess(true);
			json.setMsg(Json.DEL_SUCCESS);
		} else {
			json.setSuccess(false);
			json.setMsg(Json.DEL_FAIL);
		}
		return json;
	}

	/**
	 * 根据主键删除(逻辑删除)
	 * 
	 * @author
	 * @date
	 * @param userId
	 * @return
	 */

	public Json deleteLogicByPrimaryKey(Long userId) throws Exception {
		Json json = new Json();
		int result = -1;
		// 通过主键删除
		result = systemUserMapper.deleteLogicByPrimaryKey(userId);
		// 判断删除是否成功
		if (result > 0) {
			json.setSuccess(true);
			json.setMsg(Json.DEL_SUCCESS);
		} else {
			json.setSuccess(false);
			json.setMsg(Json.DEL_FAIL);
		}
		return json;
	}

	/**
	 * 根据主键批量删除
	 * 
	 * @author
	 * @date
	 * @param userId
	 * @return json
	 */
	public Json deleteBatchByPrimaryKey(String userId) throws Exception {
		Json json = new Json();
		int result = -1;
		// 通过主键集合删除
		result = systemUserMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(userId));
		// 判断删除是否成功
		if (result > 0) {
			json.setSuccess(true);
			json.setMsg(Json.DEL_SUCCESS);
		} else {
			json.setSuccess(false);
			json.setMsg(Json.DEL_FAIL);
		}
		return json;
	}

	/**
	 * 根据主键批量删除(逻辑删除)
	 * 
	 * @author
	 * @date
	 * @param userId
	 * @return json
	 */
	public Json deleteLogicBatchByPrimaryKey(String userId) throws Exception {
		Json json = new Json();
		int result = -1;
		// 通过主键集合删除
		result = systemUserMapper.deleteLogicBatchByPrimaryKey(PageUtil.getIdsForList(userId));
		List<String> list = PageUtil.getIdsForList(userId);
		int result2 = -1;
		for (String userIds : list) {
			// 通过userId删除用户-对象关系
			result2 = systemUserRoleRaMapper.deleteByPrimaryKeyByRole(Long.parseLong(userIds));
			if (result2 == 0 || result2 < 0) {
				result2 = -1;
				break;
			}
		}
		// 判断删除是否成功
		if (result > 0 && result2 > 0) {
			json.setSuccess(true);
			json.setMsg(Json.DEL_SUCCESS);
		} else {
			json.setSuccess(false);
			json.setMsg(Json.DEL_FAIL);
		}
		return json;
	}

	/**
	 * 添加用户
	 * 
	 * @author
	 * @date
	 * @param userId
	 * @return json
	 */
	public Json insertSelective(SystemUser record) throws Exception {
		Json json = new Json();
		int result = -1;
		Map<String, Object> conMap = new HashMap<String, Object>();

		// 1.判断用户名是否重复
		/*
		 * if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserId()))) {
		 * conMap.put("userId", record.getUserId()); }
		 */
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserName()))) {
			conMap.put("userName", record.getUserName());
		}
		/*
		 * if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getPassword()))) {
		 * conMap.put("password", record.getPassword()); } if
		 * (ValidateUtil.isNullAndIsStr(String.valueOf(record.getRealName()))) {
		 * conMap.put("realName", record.getRealName()); }
		 * 
		 * if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getDepartment()))) {
		 * conMap.put("department", record.getDepartment()); }
		 * 
		 * 
		 * if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserNumber()))) {
		 * conMap.put("userNumber", record.getUserNumber()); }
		 * 
		 * if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getPhone()))) {
		 * conMap.put("phone", record.getPhone()); } if
		 * (ValidateUtil.isNullAndIsStr(String.valueOf(record.getEmail()))) {
		 * conMap.put("email", record.getEmail()); }
		 */
//    		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserType()))) {
//			  conMap.put("userType", record.getUserType());
//			}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getDelFlag()))) {
			conMap.put("delFlag", record.getDelFlag());
		}
		/*
		 * if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getCreateTime()))) {
		 * conMap.put("createTime", record.getCreateTime()); }
		 */
		// 存在条件判断
		List<SystemUser> list = systemUserMapper.selectByPropertyByPage(conMap);
		int isExist = 0;
		if (null != list && list.size() > 0) {
			isExist = list.size();
		}

		/**
		 * 需要封装返回json类
		 */
		if (isExist > 0) {
			json.setSuccess(false);
			json.setMsg("用户名已经存在！");

		} else {

			// 2.数据校验
			// 2.1 非空
			String password = new String();
			String realName = new String();
			String phone = new String();
			String email = new String();

			password = record.getPassword();
			realName = record.getRealName();
			phone = record.getPhone();
			email = record.getEmail();

			// 2.2合法
			if ((password == null || password.equals("")) || (realName == null || realName.equals(""))
					|| (phone == null || phone.equals("")) || (email == null || email.equals(""))) {
				json.setMsg("存在空数据，请检查！");
				return json;
				
			} else {
				
				//校验数据
				// 1.电话
				String phoneRegex ="^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
				String emailRegex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"; 
				// 2.邮箱
				if (phone.matches(phoneRegex)) {
					if (email.matches(emailRegex)) {
						//两个都合法
						result = systemUserMapper.insertSelective(record);

						// 获取用户角色关系集合
						List<SystemUserRoleRa> systemUserRoleRaList = getUserRoleRaList(record);
						int result1 = -1;

						// 批量增加
						result1 = systemUserRoleRaMapper.insertSelectiveFromUserRole(systemUserRoleRaList);

						if (result > 0 && result1 > 0) {
							json.setSuccess(true);
							json.setMsg(Json.ADD_SUCCESS);
						} else {
							json.setSuccess(false);
							json.setMsg(Json.ADD_FAIL);
						}
						
					}else {
						//邮箱非法
						json.setMsg("邮箱存在异常，请检查！");
						return json;
					}
					
				}else {
					//手机号非法
					json.setMsg("手机号存在异常，请检查！");
					return json;
				}
				
				
			}

		}
		return json;
	}

	/**
	 * 获取用户角色关系集合
	 * 
	 * @param record
	 * @return
	 */
	private List<SystemUserRoleRa> getUserRoleRaList(SystemUser record) {
		// ---------- UserRoleRa ---------------------
		Long userId = record.getUserId();
		List<String> roleIdList = PageUtil.getIdsForList(record.getRoleId());
		List<SystemUserRoleRa> systemUserRoleRaList = new ArrayList<SystemUserRoleRa>();
		SystemUserRoleRa systemUserRoleRa = new SystemUserRoleRa();

		// 封装systemUserRoleRa对象
		// 1.数据校验判空
		// 2.若没有角色，预置普通管理员角色
		if (roleIdList.get(0).equals("")) {
			// 没有配置角色
			// 默认配置为管理员
			systemUserRoleRa.setRoleId(new Long(1));
			systemUserRoleRa.setUserId(userId);
			systemUserRoleRaList.add(systemUserRoleRa);
		} else {
			// 有角色
			for (String roleId : roleIdList) {
				if (roleId != null && !roleId.equals("")) {
					systemUserRoleRa.setRoleId(Long.parseLong(roleId));
					systemUserRoleRa.setUserId(userId);
					systemUserRoleRaList.add(systemUserRoleRa);
				}

			}
		}

		// ---------- UserRoleRa ---------------------

		return systemUserRoleRaList;
	}

	/**
	 * 根据主键查询用户
	 * 
	 * @author
	 * @date
	 * @param userId
	 * @return StstenUser
	 */
	public SystemUser selectByPrimaryKey(Long userId) throws Exception {
		return systemUserMapper.selectByPrimaryKey(userId);
	}

	/**
	 * 根据主键关联角色查询用户
	 * 
	 * @author
	 * @date
	 * @param userId
	 * @return StstenUser
	 */
	public SystemUser selectByPrimaryKeyByRole(Long userId) throws Exception {
		return systemUserMapper.selectByPrimaryKeyByRole(userId);
	}

	/**
	 * 编辑用户（包括编辑用户所属角色）
	 * 
	 * @author
	 * @date
	 * @param record
	 * @return json
	 */
	public Json updateByPrimaryKeySelective(SystemUser record) throws Exception {
		Json json = new Json();
		int result = -1;
		Map<String, Object> conMap = new HashMap<String, Object>();
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserName()))) {
			conMap.put("userName", record.getUserName());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getDelFlag()))) {
			conMap.put("delFlag", record.getDelFlag());
		}
		// 存在条件判断
		List<SystemUser> list = systemUserMapper.selectByPropertyByPage(conMap);
		int isExist = 0;
		if (null != list && list.size() > 0) {
			isExist = list.size();
		}
		SystemUser sysUser = systemUserMapper.selectByPrimaryKey(record.getUserId());
		if (sysUser.getUserName().equals(record.getUserName())) {
			isExist = 0;
		}
		if (isExist > 0) {
			json.setSuccess(false);
			json.setMsg("用户名已经存在!");
		} else {
			// 通过对象编辑
			result = systemUserMapper.updateByPrimaryKeySelective(record);
			// 通过userId删除用户-对象关系
			systemUserRoleRaMapper.deleteByPrimaryKeyByRole(record.getUserId());
			// 获取用户角色关系集合
			List<SystemUserRoleRa> systemUserRoleRaList = getUserRoleRaList(record);
			// 批量添加用户-角色关系
			int result2 = -1;
			result2 = systemUserRoleRaMapper.insertSelectiveFromUserRole(systemUserRoleRaList);
			if (result > 0 && result2 > 0) {
				json.setSuccess(true);
				json.setMsg(Json.UPDATE_SUCCESS);
			} else {
				json.setSuccess(false);
				json.setMsg(Json.UPDATE_FAIL);
			}
		}

		return json;
	}

	/**
	 * 根据用户名编辑用户
	 * 
	 * @author
	 * @date
	 * @param record
	 * @return json
	 */
	public Json updateByUserNameSelective(SystemUser record) throws Exception {
		Json json = new Json();
		int result = -1;
		// 通过对象编辑
		result = systemUserMapper.updateByUserNameSelective(record);
		if (result > 0) {
			json.setSuccess(true);
			json.setMsg(Json.UPDATE_SUCCESS);
		} else {
			json.setSuccess(false);
			json.setMsg(Json.UPDATE_FAIL);
		}
		return json;
	}

	/**
	 * 根据主键重置密码
	 * 
	 * @author
	 * @date
	 * @param record
	 * @return json
	 */
	public Json updateByUserId(SystemUser record) throws Exception {
		Json json = new Json();
		int result = -1;
		// 通过对象编辑
		result = systemUserMapper.updateByUserId(record);
		if (result > 0) {
			json.setSuccess(true);
			json.setMsg("密码重置成功!");
		} else {
			json.setSuccess(false);
			json.setMsg(Json.UPDATE_FAIL);
		}
		return json;
	}

	/**
	 * 分页查询数据列表
	 * 
	 * @author
	 * @date
	 * @param record
	 * @param page
	 * @return String
	 */
	public String selectSystemUserByPage(SystemUser record, PageUtil page) throws Exception {
		List<SystemUser> list = new ArrayList<SystemUser>();
		Map<String, Object> maps = new HashMap<String, Object>();
		// 判断条件
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserId()))) {
			maps.put("userId", record.getUserId());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserName()))) {
			maps.put("userName", record.getUserName());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getPassword()))) {
			maps.put("password", record.getPassword());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getRealName()))) {
			maps.put("realName", record.getRealName());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getDepartmentId()))) {
			maps.put("departmentId", record.getDepartmentId());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserNumber()))) {
			maps.put("userNumber", record.getUserNumber());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getPhone()))) {
			maps.put("phone", record.getPhone());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getEmail()))) {
			maps.put("email", record.getEmail());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getDelFlag()))) {
			maps.put("delFlag", record.getDelFlag());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getCreateTime()))) {
			maps.put("createTime", record.getCreateTime());
		}
		// 起始分页
		maps.put("startindex", page.getStartIndex());
		// 结束分页
		maps.put("maxindex", page.getMaxIndex());
		// 查询分页
		list = systemUserMapper.selectByPropertyByPage(maps);
		// 查询总数
		int count = systemUserMapper.selectCountByProperty(maps);
		// 公共类 - 返回分页json对象
		return PageUtil.getResultJsonArray(list, count, page.getPage());
	}

	/**
	 * 分页查询数据列表(只查询未逻辑删除且对应该用户类型的记录)
	 * 
	 * @author
	 * @date
	 * @param record
	 * @param page
	 * @return String
	 */
	public String selectSystemUserByPageByOthers(SystemUser record, PageUtil page) throws Exception {
		List<SystemUser> list = new ArrayList<SystemUser>();
		Map<String, Object> maps = new HashMap<String, Object>();
		// 判断条件
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserId()))) {
			maps.put("userId", record.getUserId());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserName()))) {
			maps.put("userName", record.getUserName());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getPassword()))) {
			maps.put("password", record.getPassword());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getRealName()))) {
			maps.put("realName", record.getRealName());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getDepartmentId()))) {
			maps.put("departmentId", record.getDepartmentId());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserNumber()))) {
			maps.put("userNumber", record.getUserNumber());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getPhone()))) {
			maps.put("phone", record.getPhone());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getEmail()))) {
			maps.put("email", record.getEmail());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getDelFlag()))) {
			maps.put("delFlag", record.getDelFlag());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getCreateTime()))) {
			maps.put("createTime", record.getCreateTime());
		}
		// 起始分页
		maps.put("startindex", page.getStartIndex());
		// 结束分页
		maps.put("maxindex", page.getMaxIndex());
		// 查询分页
		list = systemUserMapper.selectByPropertyByPageByOthers(maps);
		// 查询总数
		int count = systemUserMapper.selectCountByPropertyByOthers(maps);
		// 公共类 - 返回分页json对象
		return PageUtil.getResultJsonArray(list, count, page.getPage());
	}

	/**
	 * 分页查询数据列表 与角色表关联(只查询未逻辑删除且对应该用户类型的记录)
	 * 
	 * @author
	 * @date
	 * @param record
	 * @param page
	 * @return String
	 */
	public String selectSystemUserByPageByOthersByRole(SystemUser record, PageUtil page) throws Exception {
		List<SystemUser> list = new ArrayList<SystemUser>();
		Map<String, Object> maps = new HashMap<String, Object>();
		// 判断条件
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserId()))) {
			maps.put("userId", record.getUserId());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserName()))) {
			maps.put("userName", record.getUserName());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getPassword()))) {
			maps.put("password", record.getPassword());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getRealName()))) {
			maps.put("realName", record.getRealName());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getDepartmentId()))) {
			maps.put("departmentId", record.getDepartmentId());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getUserNumber()))) {
			maps.put("userNumber", record.getUserNumber());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getPhone()))) {
			maps.put("phone", record.getPhone());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getEmail()))) {
			maps.put("email", record.getEmail());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getDelFlag()))) {
			maps.put("delFlag", record.getDelFlag());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getCreateTime()))) {
			maps.put("createTime", record.getCreateTime());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getCorpId()))) {
			maps.put("corpId", record.getCorpId());
		}
		if (ValidateUtil.isNullAndIsStr(String.valueOf(record.getCreateUserId()))) {
			maps.put("createUserId", record.getCreateUserId());
		}
		// 起始分页
		maps.put("startindex", page.getStartIndex());
		// 结束分页
		maps.put("maxindex", page.getMaxIndex());
		// 查询分页
		list = systemUserMapper.selectByPropertyByPageByOthersByRole(maps);
		list.get(0).getRoleName();
		// 查询总数
		int count = systemUserMapper.selectCountByPropertyByOthers(maps);
		// 公共类 - 返回分页json对象
		return PageUtil.getResultJsonArray(list, count, page.getPage());
	}

	/**
	 * 根据用户名密码查询用户
	 * 
	 * @author
	 * @date
	 * @param systemUser
	 * @return SystemUser
	 */
	public SystemUser selectUserByPwd(SystemUser systemUser) {
		return systemUserMapper.selectUserByPwd(systemUser);
	}

	/**
	 * 修改密码
	 * 
	 * @author
	 * @date
	 * @param record
	 * @return json
	 */
	public Json updatePasswordByPrimaryKeySelective(SystemUser record) throws Exception {
		Json json = new Json();
		int result = -1;
		// 通过对象编辑
		result = systemUserMapper.updateByPrimaryKeySelective(record);

		if (result > 0) {
			json.setSuccess(true);
			json.setMsg(Json.UPDATE_SUCCESS);
		} else {
			json.setSuccess(false);
			json.setMsg(Json.UPDATE_FAIL);
		}
		return json;
	}

}