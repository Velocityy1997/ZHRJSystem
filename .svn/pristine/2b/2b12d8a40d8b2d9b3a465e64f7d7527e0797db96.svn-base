package com.web.business.system.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shara.common.util.page.Json;
import com.shara.common.util.page.PageUtil;
import com.shara.common.util.page.ValidateUtil;
import com.web.business.cache.redis.LoginInformationCache;
import com.web.business.system.user.model.LoginInformation;
import com.web.business.system.user.model.SystemUser;
import com.web.business.system.user.model.SystemUserRoleRa;
import com.web.business.system.user.services.impl.SystemUserServiceImpl;
import com.web.common.util.spring.RequestUtil;
/**
 * 用户管理模块 
 * @author 
 * @date 
 */
@Controller
public class SystemUserController{
	// 系统管理/用户管理service层
	@Resource
	private SystemUserServiceImpl systemUserServiceImpl;
	/**
	 * 添加用户跳转
	 * @author 
	 * @date 
	 * @return 添加用户界面
	 */
	@RequestMapping(value = "/systemUser_add", method = RequestMethod.GET)
	public String systemUserAddIndex(HttpServletRequest request) {	
		return "/business/system/user/jsp/add_systemUser";
	}

	/**
     * 编辑用户跳转
	 * @author 
	 * @date 
	 * @return 编辑用户界面
	 */
	@RequestMapping(value = "/systemUser_edit", method = RequestMethod.GET)
	public String systemUserIndex(HttpServletRequest request) {
		long key = Long.parseLong(request.getParameter("userId"));
		SystemUser systemUser;
		SystemUserRoleRa systemUserRoleRa;
		try {
			//根据主键查询用户
			systemUser = systemUserServiceImpl.selectByPrimaryKeyByRole(key);
			request.setAttribute("systemUser", systemUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/business/system/user/jsp/edit_systemUser";
	}

	/**
	 * 查询用户
	 * @author 
	 * @date 
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/systemUser/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String listSystemUser(HttpServletRequest request, String pagination, String systemUser) {
		String json = "";
		try {
			SystemUser systemUserO = new SystemUser();
			// 判断是否有查询条件
			if (ValidateUtil.isNullAndIsStr(systemUser)) {
				 systemUserO = JSON.parseObject(systemUser, SystemUser.class);
			}
			
			// 条件+分页查询
			json = systemUserServiceImpl.selectSystemUserByPage(systemUserO,PageUtil.getPageBean(request));
		} catch (Exception e) {
			Json result = new Json();
			result.setMsg(Json.EXCEPTION);
			result.setSuccess(false);
			json = JSONObject.toJSONString(result);
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 查询用户(只查询未逻辑删除且对应该用户类型的记录)
	 * @author 
	 * @date 
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/systemUser/list/notDel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String listSystemUserNotDel(HttpServletRequest request, String pagination, String systemUser) {
		String json = "";
		try {
			SystemUser systemUserO = new SystemUser();
			// 判断是否有查询条件
			if (ValidateUtil.isNullAndIsStr(systemUser)) {
				 systemUserO = JSON.parseObject(systemUser, SystemUser.class);
			}
			//查询登录信息
			LoginInformation loginInformation = (LoginInformation)request.getSession().getAttribute("loginInformation");
//			LoginInformation loginInformation = LoginInformationCache.instance().getById(request.getSession().getId());
			//取出用户登录信息
			SystemUser systemUser1 = loginInformation.getSystemUser();
			//添加条件（用户类型，是否已删除）
			systemUserO.setDelFlag(systemUser1.getDelFlag());
			// 条件+分页查询
			json = systemUserServiceImpl.selectSystemUserByPageByOthers(systemUserO,PageUtil.getPageBean(request));
		} catch (Exception e) {
			Json result = new Json();
			result.setMsg(Json.EXCEPTION);
			result.setSuccess(false);
			json = JSONObject.toJSONString(result);
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 查询用户 与角色表关联(只查询未逻辑删除且对应该用户类型的记录)
	 * @author 
	 * @date 
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/systemUser/list/notDelRole", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String listSystemUserNotDelByRole(HttpServletRequest request, String pagination, String systemUser) {
		String json = "";
		try {
			SystemUser systemUserO = new SystemUser();
			// 判断是否有查询条件
			if (ValidateUtil.isNullAndIsStr(systemUser)) {
				 systemUserO = JSON.parseObject(systemUser, SystemUser.class);
			}
			//查询登录信息
			LoginInformation loginInformation = (LoginInformation)request.getSession().getAttribute("loginInformation");
//			LoginInformation loginInformation = LoginInformationCache.instance().getById(request.getSession().getId());
			//取出用户登录信息
			SystemUser systemUser1 = loginInformation.getSystemUser();
			//添加条件（用户类型，是否已删除）
			systemUserO.setDelFlag(systemUser1.getDelFlag());
			if(loginInformation.getSystemUser().getCreateUserId().longValue() != loginInformation.getSystemUser().getUserId().longValue()){
				systemUserO.setCreateUserId(loginInformation.getSystemUser().getCreateUserId());	
			}
			systemUserO.setUserId(loginInformation.getSystemUser().getUserId());
			// 条件+分页查询
			json = systemUserServiceImpl.selectSystemUserByPageByOthersByRole(systemUserO,PageUtil.getPageBean(request));
		} catch (Exception e) {
			Json result = new Json();
			result.setMsg(Json.EXCEPTION);
			result.setSuccess(false);
			json = JSONObject.toJSONString(result);
			e.printStackTrace();
		}
		return json;
	}

	/**
                * 添加用户
	 * @author 
	 * @date 
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/systemUser/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String addSystemUser(HttpServletRequest request, String systemUser) {
		Json result = new Json();
		Json resultO = new Json();
		try {
			SystemUser systemUserO = JSON.parseObject(systemUser, SystemUser.class);
			//查询登录信息
			LoginInformation loginInformation = (LoginInformation)request.getSession().getAttribute("loginInformation");
//			LoginInformation loginInformation = LoginInformationCache.instance().getById(request.getSession().getId());
			//取出用户登录信息
			SystemUser systemUser1 = loginInformation.getSystemUser();
			//添加条件（用户类型，是否已删除）
			systemUserO.setDelFlag(systemUser1.getDelFlag());
			systemUserO.setCreateUserId(loginInformation.getSystemUser().getUserId());
			//根据对象添加
			result = systemUserServiceImpl.insertSelective(systemUserO);
		} catch (Exception e) {
			result.setMsg(Json.EXCEPTION);
			result.setSuccess(false);
			e.printStackTrace();
		}
		return JSONObject.toJSONString(result);
	}

	/**
	 * 编辑用户信息
	 * @author 
	 * @date 
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/systemUser/edit", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String editSystemUser(HttpServletRequest request, String systemUser) {
		Json result = new Json();
		SystemUser systemUserO = JSON.parseObject(systemUser, SystemUser.class);
		try {
			//根据对象修改
			result = systemUserServiceImpl.updateByPrimaryKeySelective(systemUserO);
			//查询登录信息
			LoginInformation loginInformation = (LoginInformation)request.getSession().getAttribute("loginInformation");
//			LoginInformation loginInformation = LoginInformationCache.instance().getById(request.getSession().getId());
			//取出用户登录信息
			SystemUser systemUser1 = loginInformation.getSystemUser();
		} catch (Exception e) {
			result.setMsg(Json.EXCEPTION);
			result.setSuccess(false);
			e.printStackTrace();
		}
		return JSONObject.toJSONString(result);
	}
	
	/**
	 * 重置密码
	 * @author 
	 * @date 
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/systemUserId/edit", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String editSystemUserId(HttpServletRequest request, String systemUser) {
		Json result = new Json();
		SystemUser systemUserO = JSON.parseObject(systemUser, SystemUser.class);
		try {
			//根据对象修改
			result = systemUserServiceImpl.updateByUserId(systemUserO);
		} catch (Exception e) {
			result.setMsg(Json.EXCEPTION);
			result.setSuccess(false);
		}
		return JSONObject.toJSONString(result);
	}

	/**
	 * 删除用户
     * @author 
	 * @date 
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/systemUser/del", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String delSystemUser(HttpServletRequest request) {
		Json result = new Json();
		String key = request.getParameter("userId");
		try {
			//根据主键删除
			result = systemUserServiceImpl.deleteBatchByPrimaryKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(Json.EXCEPTION);
			result.setSuccess(false);
			e.printStackTrace();
		}
		return JSONObject.toJSONString(result);
	}
	

	/**
	 * 删除用户(逻辑删除)
     * @author 
	 * @date 
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/systemUser/delLogic", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public String delLogicSystemUser(HttpServletRequest request) {
		Json result = new Json();
		String key = request.getParameter("userId");
		try {
			//根据主键删除
			result = systemUserServiceImpl.deleteLogicBatchByPrimaryKey(key);
			//查询登录信息
			LoginInformation loginInformation = (LoginInformation)request.getSession().getAttribute("loginInformation");
//			LoginInformation loginInformation = LoginInformationCache.instance().getById(request.getSession().getId());
			//取出用户登录信息
			SystemUser systemUser1 = loginInformation.getSystemUser();
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(Json.EXCEPTION);
			result.setSuccess(false);
			e.printStackTrace();
		}
		return JSONObject.toJSONString(result);
	}
	

	/**
	 * 获取
     * @author 
	 * @date 
	 * @return 详情页面
	 */
	@RequestMapping(value = "/systemUser/get", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String getSystemUser(HttpServletRequest request) {
		long key = Long.parseLong(request.getParameter("userId"));
		SystemUser systemUser;
		try {
			//根据主键查询
			systemUser = systemUserServiceImpl.selectByPrimaryKey(key);
			request.setAttribute("systemUser", systemUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/modules/system/user/jsp/detail";
	}

}