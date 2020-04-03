package com.web.business.generator.monitor.position.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.business.generator.monitor.position.model.Position;
@Mapper
public interface PositionMapper {

	 
     /*****
      *根据主键查询
     ****/
     Position selectByPrimaryKey(String id);
     
     
     /*****
      *根据主键查询最新位置
     ****/
     Position selectNewPositionById(String id);
     
     /*****
      *查询所有位置信息
     ****/
     List<Position> selectAll();
     
     
     /*****
      *查询所有位置信息
     ****/
     List<String> selectIds();
     
     
     /*****
      *根据终端id查询----通用
     ****/
     List<Position>  selectByTermianlId(String Terminal_Ids);
     
     
     /*****
               *根据终端id和时间查询
      ****/
     List<Position> selectByIdAndTime(@Param("Terminal_Id")String  Terminal_Id,@Param("startTime") String startTime,@Param("endTime") String endTime);
     
	
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
     int insertSelective(Position record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<Position> selectByPropertyByPage(Position record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(Position record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(Position record);
}