package com.web.business.generator.monitor.position.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;

import com.web.common.util.spring.PageTool;

import com.web.business.generator.monitor.position.model.Position;
import com.web.business.generator.monitor.position.dao.PositionMapper;
import com.web.business.generator.monitor.position.services.IPositionService;

@Service
public class PositionServiceImpl implements IPositionService {
	@Resource
	private PositionMapper positionMapper;
	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteByPrimaryKey(String id) throws Exception {
		int result = -1;
		result = positionMapper.deleteByPrimaryKey(id);
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
		result = positionMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(id));
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(Position record) throws Exception{
		int result = -1;
		result = positionMapper.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	public Position selectByPrimaryKey(String id) throws Exception{
		return positionMapper.selectByPrimaryKey(id);
	}
	
	 /*****
           *根据终端id和时间查询
     ****/
	public List<Position> selectByIdAndTime(String  Terminal_Id, String startTime,String endTime) throws Exception{
		return positionMapper.selectByIdAndTime(Terminal_Id,startTime,endTime);
	}
	

	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(Position record) throws Exception{
		int result = -1;
		result = positionMapper.updateByPrimaryKeySelective(record);
		return result;
	}
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<Position> selectPositionByPage(Position record, Integer page, Integer pageSize) throws Exception{
		List<Position> list = new ArrayList<Position>();
    	
    	int start = (page != null ? page : 1);
    		int max = pageSize != null ? pageSize : 10;
//             record.setStart((start-1)*pageSize);
//            record.setMax(max);
		// 查询分页
		list = positionMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = positionMapper.selectCountByProperty(record);
		PageTool<Position> pageTool = new PageTool<>( page, count, list, pageSize);
        return pageTool;
		
	}

	
	/**
	 * 根据终端id查询对象
	 */
	@Override
	public List<Position> selectByTerminalId(String terminalId) throws Exception {
		
		return positionMapper.selectByTermianlId(terminalId);
	}

	@Override
	public List<Position> selectAll() throws Exception {
		return positionMapper.selectAll();
	}
	
	public List<String> getAllIds () {
		return positionMapper.selectIds();
	}
	
	public Position getNewById (String id) {
		return positionMapper.selectNewPositionById(id);
	}
	
	 

}