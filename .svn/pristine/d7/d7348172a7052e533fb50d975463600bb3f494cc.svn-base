package com.web.business.generator.comm.taskList.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.business.generator.comm.taskList.model.TaskList;
@Mapper
public interface TaskListMapper {

	 
     /*****
     *根据主键查询
     ****/
     TaskList selectByPrimaryKey(String id);
	
	 /****
	 *根据主键删除
	 ****/
     int deleteByPrimaryKey(String id);
     
     /****
 	 *清空任务列表
 	 ****/
      int clearTaskList();
     
     
     /****
	 *根据主键批量删除
	 ****/
     int deleteBatchByPrimaryKey(List<String> list);
     
     /****
	 *新增
	 ****/
     int insertSelective(TaskList record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<TaskList> selectByPropertyByPage(TaskList record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(TaskList record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(TaskList record);
}