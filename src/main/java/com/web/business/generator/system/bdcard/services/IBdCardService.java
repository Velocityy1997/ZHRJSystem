package com.web.business.generator.system.bdcard.services;

import com.web.common.util.spring.PageTool;
import com.web.business.generator.system.bdcard.model.BdCard;
import com.web.business.generator.system.bdcard.model.BdCardModel;

public interface IBdCardService {

	/**
	 * 根据主键删除
	 * 
	 * @param cardId
	 * @return
	 */
	Integer deleteByPrimaryKey(String cardId) throws Exception;
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	Integer deleteBatchByPrimaryKey(String cardId) throws Exception;
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	Integer insertSelective(BdCard record) throws Exception;
	
	/**
	 * 根据主键查询对象
	 * @param cardId
	 * @return
	 */
	BdCard selectByPrimaryKey(String cardId) throws Exception;
	
    /**
     * 修改
     * @param record
     * @return
     */
	Integer updateByPrimaryKeySelective(BdCard record) throws Exception;
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	PageTool<BdCardModel> selectBdCardByPage(BdCard record, Integer page, Integer pageSize) throws Exception;
	 

}