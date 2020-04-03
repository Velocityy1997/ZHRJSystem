package ${packagePath};

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;
import com.web.common.util.spring.PageTool;

import ${className};
import ${javaMapperName};
import ${iServiceName};

@Service
public class ${simpleServiceImplName} implements ${simpleIServiceName} {
	@Resource
	private ${simpleMapperName} ${simpleMapperName?uncap_first};
	/**
	 * 根据主键删除
	 * 
	 * @param ${pid}
	 * @return
	 */
	public Integer deleteByPrimaryKey(<#if 'Long' = pidType>Long<#else>String</#if> ${pid}) throws Exception {
		int result = -1;
		result = ${simpleMapperName?uncap_first}.deleteByPrimaryKey(id);
		return result;
	}
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public Integer deleteBatchByPrimaryKey(String ${pid}) throws Exception{
		int result = -1;
		result = ${simpleMapperName?uncap_first}.deleteBatchByPrimaryKey(PageUtil.getIdsForList(${pid}));
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(${simpleclassName} record) throws Exception{
		int result = -1;
		result = ${simpleMapperName?uncap_first}.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param ${pid}
	 * @return
	 */
	public ${simpleclassName} selectByPrimaryKey(<#if 'Long' = pidType>Long<#else>String</#if> ${pid}) throws Exception{
		return ${simpleMapperName?uncap_first}.selectByPrimaryKey(${pid});
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(${simpleclassName} record) throws Exception{
		int result = -1;
		result = ${simpleMapperName?uncap_first}.updateByPrimaryKeySelective(record);
		return result;
	}
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<${simpleclassName}> select${simpleclassName}ByPage(${simpleclassName} record, Integer page, Integer pageSize) throws Exception{
		List<${simpleclassName}> list = new ArrayList<${simpleclassName}>();
    	
    	int start = (page != null ? page : 1);
    		int max = pageSize != null ? pageSize : 10;
             record.setStart((start-1)*pageSize);
            record.setMax(max);
		// 查询分页
		list = ${simpleMapperName?uncap_first}.selectByPropertyByPage(record);
		// 查询总数
		int count = ${simpleMapperName?uncap_first}.selectCountByProperty(record);
		PageTool<${simpleclassName}> pageTool = new PageTool<>( page, count, list, pageSize);
        return pageTool;
		
	}
	 

}