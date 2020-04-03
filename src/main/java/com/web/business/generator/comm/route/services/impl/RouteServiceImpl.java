package com.web.business.generator.comm.route.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;
import com.web.common.util.spring.PageTool;

import com.web.business.generator.comm.route.model.Route;
import com.web.business.generator.comm.route.dao.RouteMapper;
import com.web.business.generator.comm.route.services.IRouteService;

@Service
public class RouteServiceImpl implements IRouteService {
	@Resource
	private RouteMapper routeMapper;
	/**
	 * 根据主键删除
	 * 
	 * @param routeId
	 * @return
	 */
	public Integer deleteByPrimaryKey(String routeId) throws Exception {
		int result = -1;
		result = routeMapper.deleteByPrimaryKey(routeId);
		return result;
	}
	
	@Override
	public Integer deleteByName(String name) throws Exception {
		int result = -1;
		result = routeMapper.deleteByName(name);
		return result;
	}
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public Integer deleteBatchByPrimaryKey(String routeId) throws Exception{
		int result = -1;
		result = routeMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(routeId));
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(Route record) throws Exception{
		int result = -1;
		result = routeMapper.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param routeId
	 * @return
	 */
	public Route selectByPrimaryKey(String routeId) throws Exception{
		return routeMapper.selectByPrimaryKey(routeId);
	}
	
	public Route selectByName(String name){
		return routeMapper.selectByName(name);
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(Route record) throws Exception{
		int result = -1;
		result = routeMapper.updateByPrimaryKeySelective(record);
		return result;
	}
	
	
	public Integer updateByRouteName(String routeName,Route route) throws Exception{
		int result = -1;
		result = routeMapper.updateByName(routeName,route);
		return result;
	}
	
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<Route> selectRouteByPage(Route record, Integer page, Integer pageSize) throws Exception{
		List<Route> list = new ArrayList<Route>();
    	
    	int start = (page != null ? page : 1);
    		int max = pageSize != null ? pageSize : 10;
             record.setStart((start-1)*pageSize);
            record.setMax(max);
		// 查询分页
		list = routeMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = routeMapper.selectCountByProperty(record);
		PageTool<Route> pageTool = new PageTool<>( page, count, list, pageSize);
        return pageTool;
		
	}

	@Override
	public List<Route> selectAll() throws Exception {
		return routeMapper.selectAll();
	}


	 

}