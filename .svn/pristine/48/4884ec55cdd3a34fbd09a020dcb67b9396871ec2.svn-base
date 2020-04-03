package com.web.business.generator.system.logInfo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.business.generator.system.logInfo.model.LogInfo;
@Mapper
public interface LogInfoMapper {

	 
     /*****
     *根据主键查询
     ****/
     LogInfo selectByPrimaryKey(String logId);
	
	 /****
	 *根据主键删除
	 ****/
     int deleteByPrimaryKey(String logId);
     
     /****
	 *根据主键批量删除
	 ****/
     int deleteBatchByPrimaryKey(List<String> list);
     
     /****
	 *新增
	 ****/
     int insertSelective(LogInfo record);
     
     /****
	 *根据属性分页查询
	 *管理员
	 ****/
     List<LogInfo> selectByPropertyByAdminPage(LogInfo record);
	 
     
     /****
 	 *根据属性分页查询
 	 *普通用户
 	 ****/
      List<LogInfo> selectByPropertyByNormalPage(String userName);
     
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(LogInfo record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByNormalProperty(LogInfo record);//普通用户
	 int selectCountByAdminProperty(LogInfo record);//管理员统计

	 /**
	  * 清空所有日志
	  * @param record
	  * @return
	  */
	 
	int deleteAllLog();
}