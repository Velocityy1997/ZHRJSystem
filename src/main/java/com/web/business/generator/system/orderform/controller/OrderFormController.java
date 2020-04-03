package com.web.business.generator.system.orderform.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;


import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import com.web.business.generator.system.orderform.model.OrderForm;
import com.web.business.generator.system.orderform.model.OrderModel;
import com.web.business.generator.system.orderform.services.impl.OrderFormServiceImpl;

@Api(description = "orderFormAPI", tags = "orderFormAPI")
@Controller
@RequestMapping("/orderForm")
public class OrderFormController{
	@Resource
	private OrderFormServiceImpl orderFormServiceImpl;
	
/**
	 * 查询Jqgrid
	 * @author 
	 * @date 
	 * @return json
	 */
	@ApiOperation(value = "查询Jqgrid..", notes = "查询Jqgrid..")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<OrderModel> list(HttpServletRequest request, String pagination, String orderForm) {
		RestResponse result = new RestResponse();
		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		/*
		 * String page = "1"; String rows = "10";
		 */
		String cardNum = request.getParameter("queryCardNum");
		String orderNum = request.getParameter("queryOrderNum");
		PageTool<OrderModel> pageInfo = null;
		try {
			OrderForm orderFormBean = new OrderForm();
			if((!cardNum.equals("")) &&(cardNum != null)  ) {
				orderFormBean.setCardNum(cardNum);
			}
			if((!orderNum.equals("")) && (orderNum != null)) {
				orderFormBean.setOrderNum(orderNum);
			}
			pageInfo =  orderFormServiceImpl.selectOrderFormByPage( orderFormBean,Integer.valueOf(page),Integer.valueOf(rows));
		} catch (Exception e) {
			result.setSuccess(false).setMessage("操作失败");
			e.printStackTrace();
		}
		return pageInfo;
	}
	 
}