package com.web.business.generator.comm.message.services;

import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;
import com.web.business.generator.comm.message.model.Message;
import com.web.business.generator.comm.message.model.MessageModel;

public interface IMessageService {

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
	Integer insertSelective(Message record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	Message selectByPrimaryKey(String id) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(Message record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<MessageModel> selectMessageByPage(Message record, Integer page, Integer pageSize) throws Exception;


	/**
     * 
     * 清空所有信息 
     */
	RestResponse deleteAll() throws Exception;
}