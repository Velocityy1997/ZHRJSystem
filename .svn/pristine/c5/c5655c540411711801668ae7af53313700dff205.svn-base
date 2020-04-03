package com.web.business.generator.system.order.services;

import com.web.common.util.spring.PageTool;
import com.web.business.generator.system.order.model.Order;

public interface IOrderService {

	/**
	 * 根据主键删除
	 * 
	 * @param orderId
	 * @return
	 */
	Integer deleteByPrimaryKey(String orderId) throws Exception;
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	Integer deleteBatchByPrimaryKey(String orderId) throws Exception;
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	Integer insertSelective(Order record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param orderId
	 * @return
	 */
	Order selectByPrimaryKey(String orderId) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(Order record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<Order> selectOrderByPage(Order record, Integer page, Integer pageSize) throws Exception;
	 

}