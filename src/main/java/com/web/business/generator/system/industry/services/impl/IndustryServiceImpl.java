package com.web.business.generator.system.industry.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;
import com.web.common.util.spring.PageTool;

import com.web.business.generator.system.industry.model.Industry;
import com.web.business.generator.system.industry.dao.IndustryMapper;
import com.web.business.generator.system.industry.services.IIndustryService;

@Service
public class IndustryServiceImpl implements IIndustryService {
	@Resource
	private IndustryMapper industryMapper;
	/**
	 * 根据主键删除
	 * 
	 * @param industId
	 * @return
	 */
	public Integer deleteByPrimaryKey(String industId) throws Exception {
		int result = -1;
		result = industryMapper.deleteByPrimaryKey(industId);
		return result;
	}
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public Integer deleteBatchByPrimaryKey(String industId) throws Exception{
		int result = -1;
		result = industryMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(industId));
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(Industry record) throws Exception{
		int result = -1;
		result = industryMapper.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param industId
	 * @return
	 */
	public Industry selectByPrimaryKey(String industId) throws Exception{
		return industryMapper.selectByPrimaryKey(industId);
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(Industry record) throws Exception{
		int result = -1;
		result = industryMapper.updateByPrimaryKeySelective(record);
		return result;
	}
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<Industry> selectIndustryByPage(Industry record, Integer page, Integer pageSize) throws Exception{
		List<Industry> list = new ArrayList<Industry>();
    	
    	int start = (page != null ? page : 1);
    		int max = pageSize != null ? pageSize : 10;
             record.setStart((start-1)*pageSize);
            record.setMax(max);
		// 查询分页
		list = industryMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = industryMapper.selectCountByProperty(record);
		PageTool<Industry> pageTool = new PageTool<>( page, count, list, pageSize);
        return pageTool;
		
	}
	 
	/**
	 * 查询所有数据
	 */
	 
	public List<Industry> selectByPrimaryList() {
		// TODO Auto-generated method stub
		
		
		List<Industry> induList = industryMapper.selectByPrimaryList();
		
		
		return induList;
	}
}