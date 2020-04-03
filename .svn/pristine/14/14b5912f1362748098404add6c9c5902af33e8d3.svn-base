package com.web.business.common.menu.services;

import java.util.List;

import com.web.business.common.menu.model.MenuBean;
import com.web.business.system.user.model.SystemUser;



/**
 * 菜单结构service层接口
 * 
 * @author  
 * 
 */
public interface IMenuService {
	 /**
     * 根据当前用户获取菜单
     * @param account
     * @return
     */
	List<MenuBean> selectMenusByUser(SystemUser systemUser) throws Exception;
	 /**
     * 根据当前用户获取菜单
     * @param account
     * @return
     */
	String selectChildMenusByUser(SystemUser systemUser,String pid) throws Exception;
	
	/**
	 * 根据当前用户获取菜单
	 * @param account
	 * @return
	 */
	List<MenuBean> selectChildMenusByUsers(SystemUser systemUser,String pid) throws Exception; 
}
