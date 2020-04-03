package com.web.business.generator.monitor.baudInfo.services;

import com.web.common.util.spring.PageTool;
import com.web.business.generator.monitor.baudInfo.model.BaudInfo;

public interface IBaudInfoService {

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
	Integer insertSelective(BaudInfo record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	BaudInfo selectByPrimaryKey(String id) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(BaudInfo record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<BaudInfo> selectBaudInfoByPage(BaudInfo record, Integer page, Integer pageSize) throws Exception;
	 

}