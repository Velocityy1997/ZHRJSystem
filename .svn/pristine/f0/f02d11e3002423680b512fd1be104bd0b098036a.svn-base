package com.web.business.generator.comm.currentTask.services;

import com.web.common.util.spring.PageTool;
import com.web.business.generator.comm.currentTask.model.CurrentTask;

public interface ICurrentTaskService {

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
	Integer deleteBatchByPrimaryKey(String id) throws Exception;
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	Integer insertSelective(CurrentTask record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	CurrentTask selectByPrimaryKey(String id) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(CurrentTask record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<CurrentTask> selectCurrentTaskByPage(CurrentTask record, Integer page, Integer pageSize) throws Exception;
	 

}