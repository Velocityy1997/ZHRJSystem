package com.web.business.generator.monitor.sysInfo.services;

import com.web.common.util.spring.PageTool;
import com.web.business.generator.monitor.sysInfo.model.SysInfo;

public interface ISysInfoService {

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
	Integer insertSelective(SysInfo record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	SysInfo selectByPrimaryKey(String id) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(SysInfo record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<SysInfo> selectSysInfoByPage(SysInfo record, Integer page, Integer pageSize) throws Exception;


    /**
     * 更新通信频率
     * @param num
     * @return
     */
    int updateFrequency(Integer num);

	/**
	 * 串口设置
	 * @param port
	 * @param rate
	 * @return
	 */
	int updatePort(String port,Integer rate);



}