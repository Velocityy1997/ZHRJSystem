package com.web.business.generator.comm.currentTask.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.business.generator.comm.currentTask.model.CurrentTask;
@Mapper
public interface CurrentTaskMapper {

	 
     /*****
     *根据主键查询
     ****/
     CurrentTask selectByPrimaryKey(String id);
	
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
     int insertSelective(CurrentTask record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<CurrentTask> selectByPropertyByPage(CurrentTask record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(CurrentTask record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(CurrentTask record);
}