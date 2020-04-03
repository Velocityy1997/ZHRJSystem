package com.web.business.generator.system.zhj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.business.generator.system.terminal.model.Terminal;
import com.web.business.generator.system.zhj.model.Zhj;
@Mapper
public interface ZhjMapper {

	 
     /*****
     *根据主键查询 
     ****/
     Zhj selectByPrimaryKey(String id);
     
     /*****
      *根据指挥机名字查询 
      ****/
      Zhj selectByName(String name);
      
      /*****
       *根据指挥机卡号查询 
       ****/
       Zhj selectByCardNum(String cardNum);
	
	 /****
	 *根据主键删除
	 ****/
     int deleteByPrimaryKey(String id);
     
     /****
	 *根据主键批量删除
	 ****/
     int deleteBatchByPrimaryKey(List<String> list);
     
     /****
	 *新增
	 ****/
     int insertSelective(Zhj record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<Zhj> selectByPropertyByPage(Zhj record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(Zhj record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(Zhj record);
	 /*
	  * 得到当前指挥机
	  */
	 Zhj getCurrentZhj();
	 
	 
	 /****
	 * 根据指挥机id列表来查询指挥机
	* 同时进行模糊查询
		 ****/
	List<Zhj> selectZhjByCommonUser(@Param("zhj") Zhj zhj,@Param("areaIdList") String areaIdList);
	 
	/**
	  * 根据区域id集合来遍历查找下属指挥机
	  * @param areaIds
	  * @return
	  */
	 Long selectZhjByAreaIds(List<String> areaIds);
	
	 
}