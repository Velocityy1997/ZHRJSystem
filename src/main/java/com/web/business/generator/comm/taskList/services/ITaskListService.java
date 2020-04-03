package com.web.business.generator.comm.taskList.services;

import com.web.common.util.spring.PageTool;
import com.web.business.generator.comm.route.model.Route;
import com.web.business.generator.comm.taskList.model.TaskList;
import com.web.business.generator.comm.taskList.model.TaskListModel;

public interface ITaskListService {

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteByPrimaryKey(String id) throws Exception;
	
	/**
	 * 清空任务列表
	 * 
	 * @param id
	 * @return
	 */
	Integer clearTaskList() throws Exception ;
	
	String sendYLWZTask(String user, String ip, String sender,
			String receiver, String[] terminalId);
	
	
	String sendBDNACTask(String user, String ip, String sender,
			String receiver, Route rote);
		
	
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
	Integer insertSelective(TaskList record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	TaskList selectByPrimaryKey(String id) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(TaskList record) throws Exception;
	

}