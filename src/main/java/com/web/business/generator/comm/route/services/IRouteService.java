package com.web.business.generator.comm.route.services;

import com.web.common.util.spring.PageTool;

import java.util.List;

import com.web.business.generator.comm.route.model.Route;

public interface IRouteService {

	/**
	 * 根据主键删除
	 * 
	 * @param routeId
	 * @return
	 */
	Integer deleteByPrimaryKey(String routeId) throws Exception;
	
	Integer deleteByName(String name)throws Exception;
	
	public Route selectByName(String name);
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	Integer deleteBatchByPrimaryKey(String routeId) throws Exception;
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	Integer insertSelective(Route record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param routeId
	 * @return
	 */
	Route selectByPrimaryKey(String routeId) throws Exception;
	
	/*****
     *全查
     ****/
     List<Route> selectAll()throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(Route record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<Route> selectRouteByPage(Route record, Integer page, Integer pageSize) throws Exception;
	 

}