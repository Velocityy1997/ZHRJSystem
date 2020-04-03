package com.web.business.generator.system.area.services;

import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;

import java.util.List;

import com.web.business.generator.system.area.model.Area;
import com.web.business.generator.system.user.model.User;

public interface IAreaService {

	/**
	 * 根据主键删除
	 * 
	 * @param industId
	 * @return
	 */
	Integer deleteByPrimaryKey(String areaId) throws Exception;
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	Integer deleteBatchByPrimaryKey(String areaId) throws Exception;
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	Integer insertSelective(Area record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param industId
	 * @return
	 */
	Area selectByPrimaryKey(String areaId) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(Area record,User user) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<Area> selectAreaByPage(Area record, Integer page, Integer pageSize) throws Exception;
	 
	/**
	 * 查询所有区域
	 * @return
	 */
	List<Area> selectAll();
	
	 /*****
	* 根据父id递归查找子节点
	****/
	List<Area> selectById(String id);
	
	 /**\
	  * 根据父级id查询父级区域名称
	  * @param parentid
	  * @return
	  */
	String selectParentNameById(String parentid);
	
	/**
	 * 查询所有的省份信息
	 * @return
	 */
	List<Area> selectAllProvince();
	
	/**
	 * 根据省查询所有市信息
	 * @return
	 */
	List<Area> selectAllCity(String provinceId);
	
	/**
	 * 根据市查询所有下属区县信息
	 * @return
	 */
	List<Area> selectAllZone(String cityId);
	
	/**
	 * 查上级省市信息，封装在area类中
	 * @param areaId
	 * @return
	 */
	Area selectParentAreaInfoById(String areaId);
	
	
	
	/**
	 * 根据区域名来查询是否已经存在区域
	 * @param name
	 * @return
	 */
	Area selectAreaByName(String name);
	
	
	
	
	/**
	 * 根据区域名来查询是否已经存在区域
	 * @param name
	 * @return
	 */
	List<Area> selectAreaName(String areaName);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}