package com.web.business.generator.system.terminal.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.business.generator.system.terminal.model.Terminal;

@Mapper
public interface TerminalMapper {

	/*****
	 * 根据主键查询
	 ****/
	Terminal selectByPrimaryKey(String id);
	
	/*****
	 * 根据卡号查终端
	 ****/
	List<Terminal> selectByCardNum(String CARD_NUM);

	/****
	 * 根据主键删除
	 ****/
	int deleteByPrimaryKey(String id);

	/****
	 * 根据主键批量删除
	 ****/
	int deleteBatchByPrimaryKey(List<String> list);

	/****
	 * 新增
	 ****/
	int insertSelective(Terminal record);

	/****
	 * 根据属性分页查询 管理员
	 ****/
	List<Terminal> selectByPropertyByAdminPage(Terminal record);

	/**
	 * 根据属性分页查询 普通用户
	 */

	List<Terminal> selectByPropertyByNomalPage(Terminal record);

	/****
	 * 根据主键更新记录
	 ****/
	int updateByPrimaryKeySelective(Terminal record);

	/**
	 * 分页总数
	 * 
	 * @param record
	 * @return
	 */
	int selectCountByProperty(Terminal record);

	List<Terminal> getterminalList();

	List<Terminal> selectByTerminalName(String name);

	List<Terminal> selectBycardNum(String cardNum);
	
	List<Terminal> selectAll(Terminal terminal);

	/****
	 * 根据终端id列表来查询终端
	 * 同时进行模糊查询
	 ****/
	List<Terminal> selectTerminalByIdList(@Param("terminal") Terminal terminal,@Param("idList") List<String> list);

	/**
	  * 根据区域id集合统计下属终端总数
	  * @param areaIds
	  * @return
	  */
	 Long sumTerminalByAreaIds(List<String> areaIds);
	 
	 
	 /**
	  * 根据区域id来统计下属终端总数
	  * @param areaIds
	  * @return
	  */
	 Long sumTerminalByAreaId(String areaId);
	 
	 /**
	  * 根据区域id来统计区内下属终端总数
	  * @param areaIds
	  * @return
	  */
	 Long sumInZoneTerminalByAreaId(String areaId);
	 
	 /**
	  * 根据区域id来统计区外下属终端总数
	  * @param areaIds
	  * @return
	  */
	 Long sumOutZoneTerminalByAreaId(String areaId);
	 
	 
	 /**
	  * 根据区域id集合查下属终端集合
	  * @param areaIds
	  * @return
	  */
	 List<Terminal> selectTerminalByAreaIds(List<String> areaIds,Terminal terminal);
	 
	 /**
	  * 根据区域id查下属终端集合
	  * @param areaIds
	  * @return
	  */
	 List<Terminal> selectTerminalByAreaId(String areaId);
	 
	 
	 /**
	  * 根据区域id来统计下属终端总数
	  * @param areaIds
	  * @return
	  */
	 Long sumTerminalByAreaIdAndType(@Param("list")List<String> areaIds ,@Param("type")String type);
	 
	
	
}