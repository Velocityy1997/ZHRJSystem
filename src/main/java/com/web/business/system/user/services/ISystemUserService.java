package com.web.business.system.user.services;

import java.util.List;
import java.util.Map;

import com.shara.common.util.page.Json;
import com.shara.common.util.page.PageUtil;
import com.web.business.system.user.model.SystemUser;

/**
 * 用户管理services层接口
 * @author 
 * @date 
 */
public interface ISystemUserService {

	/**
	 * 根据主键删除(物理删除)
	 * @author 
	 * @date 
	 * @param userId
	 * @return 
	 */
	Json deleteByPrimaryKey(Long userId) throws Exception;
	
	/**
	 * 根据主键删除(逻辑删除)
	 * @author 
	 * @date 
	 * @param userId
	 * @return 
	 */
	Json deleteLogicByPrimaryKey(Long userId) throws Exception;
	
	/**
	 * 根据主键批量删除
	 * @author 
	 * @date  
	 * @param userId
	 * @return json
	 */
	Json deleteBatchByPrimaryKey(String userId) throws Exception;
	
	/**
	 * 根据主键批量删除(逻辑删除)
	 * @author 
	 * @date  
	 * @param userId
	 * @return json
	 */
	Json deleteLogicBatchByPrimaryKey(String userId) throws Exception;
	
	/**
	 * 添加用户
	 * @author 
	 * @date  
	 * @param userId
	 * @return json
	 */
	Json insertSelective(SystemUser record) throws Exception;
	
	/**
	 * 根据主键查询用户
	 * @author 
	 * @date  
	 * @param userId
	 * @return SystemUser
	 */
	SystemUser selectByPrimaryKey(Long userId) throws Exception;
	
    /**
     * 编辑用户
     * @author 
	 * @date  
     * @param record
     * @return json
     */
	Json updateByPrimaryKeySelective(SystemUser record) throws Exception;
	
    /**
     * 根据用户名编辑用户
     * @author 
	 * @date  
     * @param record
     * @return json
     */
	Json updateByUserNameSelective(SystemUser record) throws Exception;
	
    /**
     * 根据主键重置密码
     * @author 
	 * @date  
     * @param record
     * @return json
     */
	Json updateByUserId(SystemUser record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @author 
	 * @date  
     * @param record
     * @param page
     * @return String
     */
	String selectSystemUserByPage(SystemUser record, PageUtil page) throws Exception;
	
    /**
     * 分页查询数据列表(只查询未逻辑删除且对应该用户类型的记录)
     * @author 
	 * @date  
     * @param record
     * @param page
     * @return String
     */
	String selectSystemUserByPageByOthers(SystemUser record, PageUtil page) throws Exception;
	
	  /**
     * 分页查询数据列表 与角色表关联(只查询未逻辑删除且对应该用户类型的记录)
     * @author 
	 * @date  
     * @param record
     * @param page
     * @return String
     */
	String selectSystemUserByPageByOthersByRole(SystemUser record, PageUtil page) throws Exception;
	
	
	 /**
	  * 根据用户名密码查询用户
	  * @author 
	  * @date  
	  * @param systemUser
	  * @return SystemUser
	  */
	SystemUser selectUserByPwd(SystemUser systemUser) throws Exception;
	
	
}