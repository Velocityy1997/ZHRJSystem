package com.web.business.generator.system.terminal.services;

import com.web.business.generator.system.area.model.Area;
import com.web.business.generator.system.terminal.model.Terminal;
import com.web.business.generator.system.terminal.model.TerminalModel;
import com.web.business.generator.system.user.model.User;
import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;

import java.util.List;

public interface ITerminalService {

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteByPrimaryKey(String id) throws Exception;
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	Integer deleteBatchByPrimaryKey(String id) throws Exception;
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public RestResponse insertSelective(Terminal record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	Terminal selectByPrimaryKey(String id) throws Exception;
	

	/*****
	 * 根据卡号查终端
	 ****/
	List<Terminal> selectByCardNum(String CARD_NUM);
	
    /**
     * 修改
     * @param record
     * @return
     */
	public RestResponse updateByPrimaryKeySelective(Terminal record) throws Exception;
	
    /**
	 * 分页查询数据列表
	 * 
	 * @param record
	 * @param page
	 * @return
	 */
	PageTool<TerminalModel> selectTerminalByPage(Terminal record,  User  user ,Integer page, Integer pageSize) throws Exception;
	
	/*
	 * 获取所有终端列表
	 */
	List<Terminal> getterminalList() throws Exception;
	
	
	List<Terminal> selectAll(Terminal terminal);
	
	
	
	
	
	
	
	
	
	
	
}