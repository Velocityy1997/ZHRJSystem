package com.web.business.generator.system.logInfo.services;

import com.web.common.util.spring.PageTool;

import com.web.business.generator.system.logInfo.model.LogInfo;
import com.web.business.generator.system.logInfo.model.LogInfoModel;
import com.web.business.generator.system.user.model.User;

public interface ILogInfoService {

	/**
	 * 根据主键删除
	 * 
	 * @param logId
	 * @return
	 */
	Integer deleteByPrimaryKey(String logId) throws Exception;
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	Integer deleteBatchByPrimaryKey(String logId) throws Exception;
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	Integer insertSelective(LogInfo record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param logId
	 * @return
	 */
	LogInfo selectByPrimaryKey(String logId) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(LogInfo record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<LogInfoModel> selectLogInfoByPage(LogInfo record, User userInfo, Integer page, Integer pageSize) throws Exception;
	 

	
	/**
	  * 清空所有日志
	  * @param record
	  * @return
	  */
	 
	Integer deleteAllLog() throws Exception;
}