package com.web.business.system.user.dao.mysql.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.web.business.system.user.model.SystemUser;

/**
 * 用户管理dao层接口
 * @author 
 * @date 
 */
@Mapper
public interface SystemUserMapper {

	/**
	 * 根据主键查询用户
	 * @author 
	 * @date  
	 * @param userId
	 * @return StstemUser
	 */
     SystemUser selectByPrimaryKey(Long userId);
     
 	/**
 	 * 根据主键与角色表关联查询查询用户
 	 * @author 
 	 * @date  
 	 * @param userId
 	 * @return StstemUser
 	 */
      SystemUser selectByPrimaryKeyByRole(Long userId);

 	/**
 	 * 根据主键删除用户（物理删除）
 	 * @author 
 	 * @date  
 	 * @param userId
 	 * @return int
 	 */
     int deleteByPrimaryKey(Long userId);
     
 	/**
  	 * 根据主键删除用户（逻辑删除）
  	 * @author 
  	 * @date  
  	 * @param userId
  	 * @return int
  	 */
      int deleteLogicByPrimaryKey(Long userId);
     
  	/**
  	 * 根据主键集合删除用户（物理删除）
  	 * @author 
  	 * @date  
  	 * @param list
  	 * @return int
  	 */
     int deleteBatchByPrimaryKey(List<String> list);
     
   	/**
   	 * 根据主键集合删除用户（逻辑删除）
   	 * @author 
   	 * @date  
   	 * @param list
   	 * @return int
   	 */
      int deleteLogicBatchByPrimaryKey(List<String> list);
     
  	/**
  	 * 添加用户
  	 * @author 
  	 * @date  
  	 * @param userId
  	 * @return int
  	 */
     int insertSelective(SystemUser record);
     
   	/**
   	 * 根据属性分页查询
   	 * @author 
   	 * @date  
   	 * @param map
   	 * @return List<SystemUser>
   	 */
     List<SystemUser> selectByPropertyByPage(Map map);
     
    /**
     * 根据属性分页查询(只查询未逻辑删除且对应该用户类型的记录)
     * @author 
     * @date  
     * @param map
     * @return List<SystemUser>
     */
     List<SystemUser> selectByPropertyByPageByOthers(Map map);
     
     /**
      * 根据属性与角色表联查 分页查询(只查询未逻辑删除且对应该用户类型的记录)
      * @author 
      * @date  
      * @param map
      * @return List<SystemUser>
      */
     List<SystemUser> selectByPropertyByPageByOthersByRole(Map map);
     
   	/**
     * 根据属查询记录条数
     * @author 
     * @date  
     * @param map
     * @return int
     */ 
	 int selectCountByProperty(Map map); 
	 
	   	/**
	     * 根据属查询记录条数(只查询未逻辑删除且对应该用户类型的记录)
	     * @author 
	     * @date  
	     * @param map
	     * @return int
	     */
	 int selectCountByPropertyByOthers(Map map); 
	 

	 /**
	  * 根据主键编辑用户信息
	  * @author 
	  * @date  
	  * @param record
	  * @return int
	  */
	 int updateByPrimaryKeySelective(SystemUser record);
	 
	 /**
	  * 根据用户名编辑用户信息
	  * @author 
	  * @date  
	  * @param record
	  * @return int
	  */
	 int updateByUserNameSelective(SystemUser record);
	 
	 /**
	  * 根据主键重置密码
	  * @author 
	  * @date  
	  * @param record
	  * @return int
	  */
	 int updateByUserId(SystemUser record);

	 /**
	  * 根据用户名密码查询用户
	  * @author 
	  * @date  
	  * @param record
	  * @return SystemUser
	  */
	SystemUser selectUserByPwd(SystemUser record);
	

	 /**
	  * 审核前查询用户
	  * @author 
	  * @date  
	  * @param record
	  * @return SystemUser
	  */
	SystemUser selectUserByUserName(SystemUser record);

	
}