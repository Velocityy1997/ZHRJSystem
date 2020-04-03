package com.web.business.system.user.model;

import java.io.Serializable;
import java.util.List;

import com.web.business.common.menu.model.MenuBean;


public class LoginInformation implements Serializable{
	/**
	 * 用户登录信息实体类
	 * @author  
	 */
	private static final long serialVersionUID = 1L;
	
    private SystemUser systemUser;//当前登录用户对象
    
    private List<MenuBean> menus;//目录对象
    
    private String sessionId;
    
    public LoginInformation() {
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<MenuBean> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuBean> menus) {
		this.menus = menus;
	}

}
