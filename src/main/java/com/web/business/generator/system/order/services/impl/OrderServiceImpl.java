package com.web.business.generator.system.order.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;
import com.web.common.util.spring.PageTool;

import com.web.business.generator.system.order.model.Order;
import com.web.business.generator.system.order.dao.OrderMapper;
import com.web.business.generator.system.order.services.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {
	@Resource
	private OrderMapper orderMapper;
	/**
	 * 根据主键删除
	 * 
	 * @param orderId
	 * @return
	 */
	public Integer deleteByPrimaryKey(String orderId) throws Exception {
		int result = -1;
		result = orderMapper.deleteByPrimaryKey(orderId);
		return result;
	}
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public Integer deleteBatchByPrimaryKey(String orderId) throws Exception{
		int result = -1;
		result = orderMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(orderId));
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(Order record) throws Exception{
		int result = -1;
		result = orderMapper.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param orderId
	 * @return
	 */
	public Order selectByPrimaryKey(String orderId) throws Exception{
		return orderMapper.selectByPrimaryKey(orderId);
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(Order record) throws Exception{
		int result = -1;
		result = orderMapper.updateByPrimaryKeySelective(record);
		return result;
	}
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<Order> selectOrderByPage(Order record, Integer page, Integer pageSize) throws Exception{
		List<Order> list = new ArrayList<Order>();
    	
    	int start = (page != null ? page : 1);
    		int max = pageSize != null ? pageSize : 10;
            // record.setStart((start-1)*pageSize);
            //record.setMax(max);
		// 查询分页
		list = orderMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = orderMapper.selectCountByProperty(record);
		PageTool<Order> pageTool = new PageTool<>( page, count, list, pageSize);
        return pageTool;
		
	}
	 

}