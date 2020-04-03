package ${packagePath};

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import ${className};
@Mapper
public interface ${simpleMapperName} {

	 
     /*****
     *根据主键查询
     ****/
     ${simpleclassName} selectByPrimaryKey(<#if 'Long' = pidType>Long<#else>String</#if> ${pid});
	
	 /****
	 *根据主键删除
	 ****/
     int deleteByPrimaryKey(<#if 'Long' = pidType>Long<#else>String</#if> ${pid});
     
     /****
	 *根据主键批量删除
	 ****/
     int deleteBatchByPrimaryKey(List<String> list);
     
     /****
	 *新增
	 ****/
     int insertSelective(${simpleclassName} record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<${simpleclassName}> selectByPropertyByPage(${simpleclassName} record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(${simpleclassName} record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(${simpleclassName} record);
}