package com.web.business.generator.system.role.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;
import com.web.common.util.spring.PageTool;

import com.web.business.generator.system.role.model.Role;
import com.web.business.generator.system.role.dao.RoleMapper;
import com.web.business.generator.system.role.services.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	@Resource
	private RoleMapper roleMapper;
	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteByPrimaryKey(String id) throws Exception {
		int result = -1;
		result = roleMapper.deleteByPrimaryKey(id);
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
		result = roleMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(id));
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(Role record) throws Exception{
		int result = -1;
		result = roleMapper.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	public Role selectByPrimaryKey(String id) throws Exception{
		return roleMapper.selectByPrimaryKey(id);
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(Role record) throws Exception{
		int result = -1;
		result = roleMapper.updateByPrimaryKeySelective(record);
		return result;
	}
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<Role> selectRoleByPage(Role record, Integer page, Integer pageSize) throws Exception{
		List<Role> list = new ArrayList<Role>();
    	
    	int start = (page != null ? page : 1);
    		int max = pageSize != null ? pageSize : 10;
//             record.setStart((start-1)*pageSize);
//            record.setMax(max);
		// 查询分页
		list = roleMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = roleMapper.selectCountByProperty(record);
		PageTool<Role> pageTool = new PageTool<>( page, count, list, pageSize);
        return pageTool;
		
	}
	 

}