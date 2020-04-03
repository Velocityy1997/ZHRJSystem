package com.web.business.generator.system.orderform.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shara.common.util.page.PageUtil;
import com.web.common.util.spring.PageTool;

import com.web.business.generator.system.orderform.model.OrderForm;
import com.web.business.generator.system.orderform.model.OrderModel;
import com.web.business.generator.system.bdcard.model.BdCard;
import com.web.business.generator.system.orderform.dao.OrderFormMapper;
import com.web.business.generator.system.orderform.services.IOrderFormService;

@Service
public class OrderFormServiceImpl implements IOrderFormService {
	@Resource
	private OrderFormMapper orderFormMapper;
	/**
	 * 根据主键删除
	 * 
	 * @param orderId
	 * @return
	 */
	public Integer deleteByPrimaryKey(String orderId) throws Exception {
		int result = -1;
		result = orderFormMapper.deleteByPrimaryKey(orderId);
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
		result = orderFormMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(orderId));
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(OrderForm record) throws Exception{
		int result = -1;
		result = orderFormMapper.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param orderId
	 * @return
	 */
	public OrderForm selectByPrimaryKey(String orderId) throws Exception{
		return orderFormMapper.selectByPrimaryKey(orderId);
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(OrderForm record) throws Exception{
		int result = -1;
		result = orderFormMapper.updateByPrimaryKeySelective(record);
		return result;
	}
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<OrderModel> selectOrderFormByPage(OrderForm record, Integer page, Integer pageSize) throws Exception{
		List<OrderForm> list = new ArrayList<OrderForm>();
		PageHelper.startPage(page, pageSize);
    	
		// 查询分页
		list = orderFormMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = orderFormMapper.selectCountByProperty(record);
		PageInfo<OrderForm> pageInfo = new PageInfo<OrderForm>(list);
		List<OrderModel> orderList = getOrderList(list);
		long i = pageInfo.getTotal();
		PageTool<OrderModel> pageTool = new PageTool<OrderModel>(page, count, orderList, pageSize);
        return pageTool;
		
	}

	private List<OrderModel> getOrderList(List<OrderForm> list) {
		// TODO Auto-generated method stub
		List<OrderModel> modelList = new ArrayList<OrderModel>();
		for(OrderForm orderForm : list) {
			OrderModel model  = new OrderModel();
			model.setCardNum(orderForm.getCardNum());
			model.setInvestDate(orderForm.getInvestDate());
			model.setInvestTime(orderForm.getInvestTime());
			model.setOrderNum(orderForm.getOrderNum());
			model.setBalance(orderForm.getBalance());
			modelList.add(model);
		}
		
		return modelList;
	}
	 

}