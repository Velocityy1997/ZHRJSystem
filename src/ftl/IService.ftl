package ${packagePath};

import com.web.common.util.spring.PageTool;
import ${className};

public interface ${simpleIServiceName} {

	/**
	 * 根据主键删除
	 * 
	 * @param ${pid}
	 * @return
	 */
	Integer deleteByPrimaryKey(<#if 'Long' = pidType>Long<#else>String</#if> ${pid}) throws Exception;
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	Integer deleteBatchByPrimaryKey(String ${pid}) throws Exception;
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	Integer insertSelective(${simpleclassName} record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param ${pid}
	 * @return
	 */
	${simpleclassName} selectByPrimaryKey(<#if 'Long' = pidType>Long<#else>String</#if> ${pid}) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(${simpleclassName} record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<${simpleclassName}> select${simpleclassName}ByPage(${simpleclassName} record, Integer page, Integer pageSize) throws Exception;
	 

}