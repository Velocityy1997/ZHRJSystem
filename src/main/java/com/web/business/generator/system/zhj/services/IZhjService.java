package com.web.business.generator.system.zhj.services;

import com.web.common.util.spring.PageTool;

import java.util.List;

import com.web.business.generator.system.user.model.User;
import com.web.business.generator.system.zhj.model.Zhj;
import com.web.business.generator.system.zhj.model.ZhjModel;

public interface IZhjService {

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
	Integer insertSelective(Zhj record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	ZhjModel selectByPrimaryKey(String id) throws Exception;
	
	/*****
     *根据指挥机名字查询 
     ****/
     Zhj selectByName(String name);
     
     /*****
      *根据指挥机卡号查询 
      ****/
      Zhj selectByCardNum(String cardNum);
	
	/*
	 * 获取当前指挥机
	 */
	Zhj getCurrentZhj() throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(Zhj record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<ZhjModel> selectZhjByPage(Zhj record, Integer page, Integer pageSize,User user) throws Exception;
	 

}