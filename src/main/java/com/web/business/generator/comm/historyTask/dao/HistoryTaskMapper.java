package com.web.business.generator.comm.historyTask.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.business.generator.comm.historyTask.model.HistoryTask;
import com.web.common.util.spring.RestResponse;
@Mapper
public interface HistoryTaskMapper {

	 
     /*****
     *根据主键查询
     ****/
     HistoryTask selectByPrimaryKey(String id);
	
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
     int insertSelective(HistoryTask record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<HistoryTask> selectByPropertyByPage(HistoryTask record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(HistoryTask record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(HistoryTask record);
	 
	 
	 /****
	     *删除所有数据
		 ****/
	 int deleteAll();

	/**
	 * 获取未处理历史信息.
	 *           
	 * @return 未处理集合
	 */
	 List<HistoryTask> findUnProcessedTask();

	boolean insertData(@Param("id")String id, @Param("recordState")int recordState,@Param("type")String type);
	
	
	 
}