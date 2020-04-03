package com.web.business.generator.comm.blackList.services;

import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;
import com.web.business.generator.comm.blackList.model.BlackList;
import com.web.business.generator.comm.blackList.model.BlackListModel;

public interface IBlackListService {

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
	public  RestResponse deleteBatchByPrimaryKey(String id) throws Exception;
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	 public RestResponse insertSelective(BlackList record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	BlackList selectByPrimaryKey(String id) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	public  RestResponse updateByPrimaryKeySelective(BlackList record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<BlackListModel> selectBlackListByPage(BlackList record, Integer page, Integer pageSize) throws Exception;
	 

}