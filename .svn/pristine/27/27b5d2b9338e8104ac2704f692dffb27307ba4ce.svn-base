package com.web.business.generator.monitor.position.services;

import com.web.common.util.spring.PageTool;

import java.util.List;

import com.web.business.generator.monitor.position.model.Position;

public interface IPositionService {

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
	Integer insertSelective(Position record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	Position selectByPrimaryKey(String id) throws Exception;
	
	/**
	 * 根据终端id查询对象
	 * @param id
	 * @return
	 */
	List<Position> selectByTerminalId(String terminalId) throws Exception;
	
    /*****
     *查询所有位置信息
    ****/
    List<Position> selectAll() throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(Position record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<Position> selectPositionByPage(Position record, Integer page, Integer pageSize) throws Exception;
	 

}