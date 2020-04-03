package com.web.business.generator.comm.currentTask.services.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;
import com.web.business.generator.comm.currentTask.model.CurrentTask;
import com.web.business.generator.comm.currentTask.dao.CurrentTaskMapper;
import com.web.business.generator.comm.currentTask.services.ICurrentTaskService;

@Service
public class CurrentTaskServiceImpl implements ICurrentTaskService {
	@Resource
	private CurrentTaskMapper currentTaskMapper;
	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteByPrimaryKey(String id) throws Exception {
		int result = -1;
		result = currentTaskMapper.deleteByPrimaryKey(id);
		return result;
	}
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public Integer deleteBatchByPrimaryKey(String id) throws Exception{
		int result = -1;
		result = currentTaskMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(id));
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(CurrentTask record) throws Exception{
		int result = -1;
		result = currentTaskMapper.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	public CurrentTask selectByPrimaryKey(String id) throws Exception{
		return currentTaskMapper.selectByPrimaryKey(id);
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(CurrentTask record) throws Exception{
		int result = -1;
		result = currentTaskMapper.updateByPrimaryKeySelective(record);
		return result;
	}
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<CurrentTask> selectCurrentTaskByPage(CurrentTask record, Integer page, Integer pageSize) throws Exception{
		List<CurrentTask> list = new ArrayList<CurrentTask>();
    	
    	int start = (page != null ? page : 1);
    		int max = pageSize != null ? pageSize : 10;
             record.setStart((start-1)*pageSize);
            record.setMax(max);
		// 查询分页
		list = currentTaskMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = currentTaskMapper.selectCountByProperty(record);
		PageTool<CurrentTask> pageTool = new PageTool<>( page, count, list, pageSize);
        return pageTool;
		
	}
	 

}