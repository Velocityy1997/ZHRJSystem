package com.web.business.generator.system.user.services;

import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.web.business.generator.system.user.model.User;
import com.web.business.generator.system.user.model.UserModel;

public interface IUserService {

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteByPrimaryKey(String id) throws Exception;
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public RestResponse deleteBatchByPrimaryKey(String id,User loginInfo) throws Exception;
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	 public RestResponse insertSelective(User record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	User selectByPrimaryKey(String id) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	public  RestResponse updateByPrimaryKeySelective(User record,HttpSession session) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<UserModel> selectUserByPage(String queryName , String queryArea , User record, Integer page, Integer pageSize) throws Exception;
	
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public List<User> findUserByNameAndPassword(String userName ,String password);
	
	
	/**
	 *根据用户Id查所在省市区的名称及id
	 * @param userId
	 * @return
	 */
	public Map<String,Map<String, String>> getAreaInfoByUserId(String userId);

	
	
}