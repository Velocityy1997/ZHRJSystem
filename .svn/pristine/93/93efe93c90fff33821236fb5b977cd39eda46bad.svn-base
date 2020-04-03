package com.web.business.common.menu.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shara.common.util.page.Json;
import com.web.business.cache.redis.LoginInformationCache;
import com.web.business.common.menu.services.impl.MenuServiceImpl;
import com.web.business.system.user.model.LoginInformation;
import com.web.business.system.user.model.SystemUser;

/**
 * 菜单树功能
 * 
 * @author 
 * @date 
 * 
 */
@Controller
public class MenuController {
	@Resource
	private MenuServiceImpl menuServiceImpl;
	/**
	 * 获取二级菜单
	 * 
	 * @author 
	 * @date 
	 * @return 系统管理主界面
	 */
	@ResponseBody
	@RequestMapping(value = "/common/menu_childs", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String menuChilds(HttpServletRequest request, String pid) {
		String json = "";
			try { 
				//查询登录信息
				LoginInformation loginInformation = (LoginInformation)request.getSession().getAttribute("loginInformation");
//				LoginInformation loginInformation = LoginInformationCache.instance().getById(request.getSession().getId());
				// 根据类型跳转相应弹出界面
				SystemUser systemUser = loginInformation.getSystemUser();
				json =menuServiceImpl.selectChildMenusByUser(systemUser, pid);
			} catch (Exception e) {
				Json result = new Json();
				result.setMsg(Json.EXCEPTION);
				result.setSuccess(false);
				json = JSONObject.toJSONString(result);
				e.printStackTrace();
			}
			return json;
	}
}
