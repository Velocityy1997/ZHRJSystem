package com.web.business.generator.system.industry.services;

import com.web.common.util.spring.PageTool;
import com.web.business.generator.system.industry.model.Industry;

public interface IIndustryService {

	/**
	 * 根据主键删除
	 * 
	 * @param industId
	 * @return
	 */
	Integer deleteByPrimaryKey(String industId) throws Exception;
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	Integer deleteBatchByPrimaryKey(String industId) throws Exception;
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	Integer insertSelective(Industry record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param industId
	 * @return
	 */
	Industry selectByPrimaryKey(String industId) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(Industry record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<Industry> selectIndustryByPage(Industry record, Integer page, Integer pageSize) throws Exception;
	 
	
}