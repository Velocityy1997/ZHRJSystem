package com.web.business.common.menu.dao.mysql.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.business.common.menu.model.MenuBean;


/**
 * 菜单接口
 * @author  
 *
 */
@Mapper
public interface MenuMapper {
	 /**
     * 根据当前用户获取权限菜单树一级
     * @param account
     * @return
     */
	List<MenuBean> selectMenusByUser(Map<String,String> map);
	
	/**
	 * 根据一级菜单获取二级菜单
	 * @author 
	 * @date 
	 */
	List<MenuBean> selectChildMenusByUser(Map<String,String> map);
}
