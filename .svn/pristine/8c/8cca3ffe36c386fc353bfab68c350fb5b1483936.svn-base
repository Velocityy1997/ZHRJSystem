package com.web.business.generator.comm.preMessage.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.business.generator.comm.preMessage.model.PreMessage;
import com.web.business.generator.system.terminal.model.Terminal;
import com.web.common.util.spring.RestResponse;
@Mapper
public interface PreMessageMapper {

	 
     /*****
     *根据主键查询
     ****/
     PreMessage selectByPrimaryKey(String id);
	
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
     int insertSelective(PreMessage record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<PreMessage> selectByPropertyByPage(PreMessage record);
     
     
	 
	 /****
     *根据主键更新记录
	 ****/
	 int  updateByPrimaryKeySelective(PreMessage record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(PreMessage record);
	 
	 List<PreMessage> getPreList();
	 
	 List<PreMessage> selectAll();
	 
	 
	 
	 /**
		 * 根据content查询对象
		 * @param content
		 * @return
		 */
     List<PreMessage> selectByPreContent(String content);
	 
}