package com.web.business.generator.system.orderform.services;

import com.web.common.util.spring.PageTool;
import com.web.business.generator.system.orderform.model.OrderForm;
import com.web.business.generator.system.orderform.model.OrderModel;

public interface IOrderFormService {

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
	Integer insertSelective(OrderForm record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param orderId
	 * @return
	 */
	OrderForm selectByPrimaryKey(String orderId) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(OrderForm record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<OrderModel> selectOrderFormByPage(OrderForm record, Integer page, Integer pageSize) throws Exception;
	 

}