package com.web.business.generator.comm.preMessage.services;

import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;

import java.util.List;

import com.web.business.generator.comm.preMessage.model.PreMessage;
import com.web.business.generator.comm.preMessage.model.PreMessageModel;
import com.web.business.generator.system.terminal.model.Terminal;

public interface IPreMessageService {

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
	RestResponse insertSelective(PreMessage record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	PreMessage selectByPrimaryKey(String id) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	RestResponse updateByPrimaryKeySelective(PreMessage record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<PreMessageModel> selectPreMessageByPage(PreMessage record, Integer page, Integer pageSize) throws Exception;

	/**
	 * 预置电文全查
	 * @return
	 * @throws Exception
	 */
	 List<PreMessage> selectAll()throws Exception;
	
}