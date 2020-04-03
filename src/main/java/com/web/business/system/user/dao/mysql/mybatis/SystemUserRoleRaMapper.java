package com.web.business.system.user.dao.mysql.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.business.system.user.model.SystemUserRoleRa;

/**
 * 用户--角色管理dao层接口
 * @author 
 * @date 
 */
@Mapper
public interface SystemUserRoleRaMapper { 
  	/**
  	 * 批量添加关系(用户--角色)
  	 * @author 
  	 * @date  
  	 * @return int
  	 */
     int insertSelectiveFromUserRole(List<SystemUserRoleRa> list);
     
     
    /**
     * 根据属性分页查询
     * @author 
     * @date  
     * @param map
     * @return List<SystemUserRoleRa>
     */
      List<SystemUserRoleRa> selectByPropertyByPageFromUserRole(Map map);
      
  	/**
     * 根据属查询记录条数
     * @author 
     * @date  
     * @param map
     * @return int
     */ 
  	 int selectCountByPropertyFromUserRole(Map map); 
  	 
 	/**
 	 * 根据userId查询 用户---角色
 	 * @author 
 	 * @date  
 	 * @param userId
 	 * @return StstemUser
 	 */
  	SystemUserRoleRa selectByPrimaryKeyFromUserRole(Long userId);
  	
  	/**
 	 * 根据userId删除用户--角色（物理删除）
 	 * @author 
 	 * @date  
 	 * @param userId
 	 * @return int
 	 */
     int deleteByPrimaryKeyByRole(Long userId);

}
