package com.web.business.generator.system.role.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.business.generator.system.role.model.Role;
@Mapper
public interface RoleMapper {

	 
     /*****
     *根据主键查询
     ****/
     Role selectByPrimaryKey(String id);
	
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
     int insertSelective(Role record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<Role> selectByPropertyByPage(Role record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(Role record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(Role record);
}