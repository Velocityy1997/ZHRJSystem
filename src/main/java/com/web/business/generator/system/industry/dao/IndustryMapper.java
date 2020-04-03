package com.web.business.generator.system.industry.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.business.generator.system.industry.model.Industry;
@Mapper
public interface IndustryMapper {

	 
     /*****
     *根据主键查询
     ****/
     Industry selectByPrimaryKey(String industId);
	
	 /****
	 *根据主键删除
	 ****/
     int deleteByPrimaryKey(String industId);
     
     /****
	 *根据主键批量删除
	 ****/
     int deleteBatchByPrimaryKey(List<String> list);
     
     /****
	 *新增
	 ****/
     int insertSelective(Industry record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<Industry> selectByPropertyByPage(Industry record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(Industry record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(Industry record);
	 
	 
	 /**
	  * 查询所有数据
	  * 
	  */
	 
	 List<Industry> selectByPrimaryList ();

	 Industry selectById(String industName);
	 
}