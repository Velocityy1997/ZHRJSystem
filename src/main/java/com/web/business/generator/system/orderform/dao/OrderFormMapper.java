package com.web.business.generator.system.orderform.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.business.generator.system.orderform.model.OrderForm;
@Mapper
public interface OrderFormMapper {

	 
     /*****
     *根据主键查询
     ****/
     OrderForm selectByPrimaryKey(String orderId);
	
	 /****
	 *根据主键删除
	 ****/
     int deleteByPrimaryKey(String orderId);
     
     /****
	 *根据主键批量删除
	 ****/
     int deleteBatchByPrimaryKey(List<String> list);
     
     /****
	 *新增
	 ****/
     int insertSelective(OrderForm record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<OrderForm> selectByPropertyByPage(OrderForm record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(OrderForm record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(OrderForm record);
}