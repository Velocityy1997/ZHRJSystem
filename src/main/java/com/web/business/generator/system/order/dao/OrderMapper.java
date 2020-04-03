package com.web.business.generator.system.order.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.business.generator.system.order.model.Order;
@Mapper
public interface OrderMapper {

	 
     /*****
     *根据主键查询
     ****/
     Order selectByPrimaryKey(String orderId);
	
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
     int insertSelective(Order record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<Order> selectByPropertyByPage(Order record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(Order record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(Order record);
}