package com.web.business.main.controller;

import java.util.List;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shara.common.util.page.Json;
import com.web.business.common.menu.model.MenuBean;
import com.web.business.common.menu.services.impl.MenuServiceImpl;
import com.web.business.system.user.model.LoginInformation;
import com.web.business.system.user.model.SystemUser;
import com.web.business.system.user.services.impl.SystemUserServiceImpl;
import com.web.common.util.error.ErrorCodes;
import com.web.common.util.error.LoginErrorCodes;

/**
 * 主模块 --登录入口、主界面跳转等功能
 * @author 
 * @date 
 *
 */
@Controller
public class IndexController {
	/**
	 * 登录错误码
	 */
	// 用户名错误
	public static final String USERNAME_ERROR = "1";
	// 密码错误
	public static final String PASSWORD_ERROR = "2";
	// 校验码错误
	public static final String VALIDATION_ERROR = "3";

	// 系统管理/用户管理service层
	@Resource
	private SystemUserServiceImpl systemUserServiceImpl;
	@Resource
	private MenuServiceImpl menuServiceImpl;
	
	/**
	 * 跳转登录页
	 * @param request
	 * @param systemUser
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	/**
	 * 登录方法
	 * @author 
	 * @date  
	 * @return 系统管理主界面
	 */
	@ResponseBody
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, String systemUser) {
		/*
		 * SystemUser systemUser2 = null; try { SystemUser systemUser1 =
		 * JSON.parseObject(systemUser, SystemUser.class); // 根据密码登录,返回用户对象，判断密码和用户名是否正确
		 * systemUser2 = systemUserServiceImpl.selectUserByPwd(systemUser1); if
		 * (systemUser2 != null) { // 登录后加入登录缓存 1.一级菜单缓存 2.二级菜单缓存 // 动态获取一级菜单
		 * List<MenuBean> menus = menuServiceImpl.selectMenusByUser(systemUser2);
		 * 
		 * LoginInformation loginInformation = new LoginInformation();
		 * loginInformation.setSystemUser(systemUser2);
		 * loginInformation.setMenus(menus);
		 * loginInformation.setSessionId(request.getSession().getId()); //登录信息存入redis
		 * //LoginInformationCache.instance().addOrUpdate(loginInformation);
		 * request.getSession().setAttribute("loginInformation", loginInformation); //
		 * 登录成功跳转系统管理主界面 return "main"; } else { // 用户名错误 return
		 * LoginErrorCodes.USERNAME_ERROR; } } catch (Exception e) { // 异常返回异常界面
		 * e.printStackTrace(); return ErrorCodes.EXCEPTION_ERROR; }
		 */
		return "main";
	}
	
	/**
	 * 登录成功跳转
	 * @author 
	 * @date  
	 * @return 系统管理主界面
	 */
	@RequestMapping(value = "main_index", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String main_index(HttpServletRequest request, String account) {
		//查询登录信息
		LoginInformation loginInformation = (LoginInformation)request.getSession().getAttribute("loginInformation");
//		LoginInformation loginInformation = LoginInformationCache.instance().getById(request.getSession().getId());
		if(loginInformation != null) {
			request.setAttribute("menus", loginInformation.getMenus());
			return "common/main/jsp/top_left";
		}else {
			return "index";
		}
		//return "redirect:/main_top?mtype=1";
			//	return "redirect:/left_main";
	}
	/**
	 * 跳转登录页
	 * @param request
	 * @param systemUser
	 * @return
	 */
	@RequestMapping("/main_top")
	public String main_top(HttpServletRequest request) {
		LoginInformation loginInformation = (LoginInformation)request.getSession().getAttribute("loginInformation");
//		LoginInformation loginInformation = LoginInformationCache.instance().getById(request.getSession().getId());
		request.setAttribute("menus", loginInformation.getMenus());
		return "common/main/jsp/main_top";
	}
	/**
	 * 登录成功跳转
	 * @author 
	 * @date  
	 * @return 系统管理主界面
	 */
	@RequestMapping(value = "main_left", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String main_left(HttpServletRequest request, String account) {
		//查询登录信息
		LoginInformation loginInformation = (LoginInformation)request.getSession().getAttribute("loginInformation");
//		LoginInformation loginInformation = LoginInformationCache.instance().getById(request.getSession().getId());
		if(loginInformation == null) {
			return "index";
		}
		request.setAttribute("menus", loginInformation.getMenus());
		SystemUser systemUser1 = loginInformation.getSystemUser();
		// 动态获取一级菜单
		try {
			List<MenuBean> menuschild = menuServiceImpl.selectChildMenusByUsers(systemUser1,request.getParameter("mtype"));
			List<MenuBean> menus = loginInformation.getMenus();
			List<MenuBean> menus1 = new ArrayList<MenuBean>();
			for (MenuBean menuBean : menus) {
				if(menuBean.getId().equals(request.getParameter("mtype"))){
					menuBean.setChildren(menuschild);
					menus1.add(menuBean);
					break;
				}
			}
			request.setAttribute("menuschild", menus1);
		} catch (Exception e) {
			//  异常返回异常界面
			e.printStackTrace();
			return ErrorCodes.EXCEPTION_ERROR;
		}
		return "common/main/jsp/main_left";
	}
	/**
	 * 登录成功跳转
	 * @author 
	 * @date  
	 * @return 系统管理主界面
	 */
	@RequestMapping(value = "left_main", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String left_main(HttpServletRequest request, String account) {
		//查询登录信息
		LoginInformation loginInformation = (LoginInformation)request.getSession().getAttribute("loginInformation");
//		LoginInformation loginInformation = LoginInformationCache.instance().getById(request.getSession().getId());
		request.setAttribute("menus", loginInformation.getMenus());
		SystemUser systemUser1 = loginInformation.getSystemUser();
		// 动态获取一级菜单
		try {
			List<MenuBean> menus = loginInformation.getMenus();
			List<MenuBean> menus1 = new ArrayList<MenuBean>();
			for (MenuBean menuBean : menus) {
				menuBean.setChildren(menuServiceImpl.selectChildMenusByUsers(systemUser1, menuBean.getId()));
				menus1.add(menuBean);
			}
			request.setAttribute("menuschild", menus1);
		} catch (Exception e) {
			//  异常返回异常界面
			e.printStackTrace();
			return ErrorCodes.EXCEPTION_ERROR;
		}
		return "common/main/jsp/left_main";
	}

	/**
	 * 修改密码方法
	 * @author  
	 * @date  
	 * @return 系统管理主界面
	 */
	@ResponseBody
	@RequestMapping(value = "modifyPWD", method = RequestMethod.POST)
	public String modifyPWD(HttpServletRequest request, String systemUser) {
		Json result = new Json();
		try {
			SystemUser systemUser1 = JSON.parseObject(systemUser, SystemUser.class);
			//查询登录信息
			LoginInformation loginInformation = (LoginInformation)request.getSession().getAttribute("loginInformation");
//			LoginInformation loginInformation = LoginInformationCache.instance().getById(request.getSession().getId());
			//取出用户登录信息
			SystemUser systemUser3 = loginInformation.getSystemUser();
			List<MenuBean> menus = loginInformation.getMenus();
			if (systemUser1.getOldPassword().equals(systemUser3.getPassword())) {
				systemUser3.setPassword(systemUser1.getPassword());
				//修改密码
				result = systemUserServiceImpl.updatePasswordByPrimaryKeySelective(systemUser3);
				//登录信息存入redis
				LoginInformation loginInformation1 = new LoginInformation();
				loginInformation1.setSystemUser(systemUser3);
				loginInformation1.setMenus(menus);
				loginInformation1.setSessionId(request.getSession().getId());
				request.getSession().setAttribute("loginInformation",loginInformation1);

//				LoginInformationCache.instance().addOrUpdate(loginInformation1);
				return JSONObject.toJSONString(result);
			} 
			else {
				// 用户名错误
				return LoginErrorCodes.USERNAME_ERROR;
			}
		} catch (Exception e) {
			//  异常返回异常界面
			result.setMsg(Json.EXCEPTION);
			result.setSuccess(false);
			e.printStackTrace();
			return ErrorCodes.EXCEPTION_ERROR;
		}
	}
	
	/**
	 * 用户注销
	 * @author 
	 * @date  
	 * @return 系统管理主界面
	 */
	@RequestMapping(value = "cancellation", method = RequestMethod.POST)
	public String cancellation(HttpServletRequest request, String account) {
		try {
			//删除redis数据
			request.getSession().removeAttribute("loginInformation");
//			LoginInformationCache.instance().delete(request.getSession().getId());
		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
		 return "index";
	}
	/**
	 * 用户登陆
	 * @author 
	 * @date  
	 * @return 系统管理主界面
	 */
	@RequestMapping(value = "index", method = RequestMethod.POST)
	public String index(HttpServletRequest request, String account) {
		 return "index";
	}
}
