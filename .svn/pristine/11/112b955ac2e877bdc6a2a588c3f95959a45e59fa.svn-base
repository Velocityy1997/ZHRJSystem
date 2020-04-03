package com.web.business.generator.monitor.baudInfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.web.business.generator.monitor.baudInfo.model.BaudInfo;
@Mapper
public interface BaudInfoMapper {

	 
     /*****
     *根据主键查询
     ****/
     BaudInfo selectByPrimaryKey(String id);
	
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
     int insertSelective(BaudInfo record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<BaudInfo> selectByPropertyByPage(BaudInfo record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(BaudInfo record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(BaudInfo record);

	 
	 /**
	  * 查询电量及信号
	  * @return
	  */
	List<BaudInfo> getBaudList();
}