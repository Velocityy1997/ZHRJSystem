package com.web.business.generator.comm.message.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.business.generator.comm.message.model.Message;
@Mapper
public interface MessageMapper {

	 
     /*****
     *根据主键查询
     ****/
     Message selectByPrimaryKey(String id);
	
	 /****
	 *根据主键删除
	 ****/
     int deleteByPrimaryKey(String id);
     
     /****
	 *根据主键批量删除
	 ****/
     int deleteBatchByPrimaryKey(List<String> list);
     
     /****
	 *新增
	 ****/
     int insertSelective(Message record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<Message> selectByPropertyByPage(Message record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(Message record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(Message record);
	 
	 
	 
	 /****
	     *删除所有数据
		 ****/
	 int deleteAll();
}