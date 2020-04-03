package com.web.business.generator.comm.blackList.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.business.generator.comm.blackList.model.BlackList;
import com.web.business.generator.system.user.model.User;
@Mapper
public interface BlackListMapper {

	 
     /*****
     *根据主键查询
     ****/
     BlackList selectByPrimaryKey(String id);
	
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
     int insertSelective(BlackList record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<BlackList> selectByPropertyByPage(BlackList record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(BlackList record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(BlackList record);
	 
	 
	 
	 List<BlackList> selectBySimId(String simId);
	 
}