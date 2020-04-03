package com.web.business.generator.comm.historyTask.services;

import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;

import java.util.List;

import com.web.business.generator.comm.historyTask.model.HistoryModel;
import com.web.business.generator.comm.historyTask.model.HistoryTask;

public interface IHistoryTaskService {

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
	RestResponse deleteBatchByPrimaryKey(String id) throws Exception;
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	Integer insertSelective(HistoryTask record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	HistoryTask selectByPrimaryKey(String id) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(HistoryTask record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<HistoryModel> selectHistoryTaskByPage(HistoryTask record, Integer page, Integer pageSize) throws Exception;
	/**
	 * 清除所有历史任务
	 * @return
	 * @throws Exception
	 */
	RestResponse deleteAll() throws Exception;

	
	 

	/**
	 * 获取未处理历史信息.
	 *           
	 * @return 未处理集合
	 */
	List<HistoryTask> findUnProcessedTask();
	
}