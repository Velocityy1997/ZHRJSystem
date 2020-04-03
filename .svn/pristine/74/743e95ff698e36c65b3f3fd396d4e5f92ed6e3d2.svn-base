package com.web.business.generator.monitor.sysInfo.dao;

import com.web.business.generator.monitor.sysInfo.model.SysInfo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface SysInfoMapper {

	 
     /*****
     *根据主键查询
     ****/
     SysInfo selectByPrimaryKey(String id);
	
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
     int insertSelective(SysInfo record);
     
     /****
	 *根据属性分页查询
	 ****/
     List<SysInfo> selectByPropertyByPage(SysInfo record);
	 
	 /****
     *根据主键更新记录
	 ****/
	 int updateByPrimaryKeySelective(SysInfo record);
	 /**
	  * 分页总数
	  * @param record
	  * @return
	  */
	 int selectCountByProperty(SysInfo record);

	 
	 /**
	  * 系统设置更新端口、串口设置
	  * @param model
	  * @return
	  */
	int updateInfo(SysInfo model);

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

	
	/**
	 * 获取当前指挥机配置信息
	 * @param cardNum
	 * @return
	 */
	SysInfo getInfo(String cardNum);
}