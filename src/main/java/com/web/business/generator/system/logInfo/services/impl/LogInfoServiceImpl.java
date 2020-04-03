package com.web.business.generator.system.logInfo.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shara.common.util.page.PageUtil;
import com.web.common.util.spring.PageTool;

import com.web.business.generator.system.logInfo.model.LogInfo;
import com.web.business.generator.system.logInfo.model.LogInfoModel;
import com.web.business.generator.system.area.dao.AreaMapper;
import com.web.business.generator.system.area.model.Area;
import com.web.business.generator.system.logInfo.dao.LogInfoMapper;
import com.web.business.generator.system.logInfo.services.ILogInfoService;
import com.web.business.generator.system.user.model.User;

@Service
public class LogInfoServiceImpl implements ILogInfoService {
	@Resource
	private LogInfoMapper logInfoMapper;
	
	@Resource
	private AreaMapper areaMapper;
	
	/**
	 * 根据主键删除
	 * 
	 * @param logId
	 * @return
	 */
	public Integer deleteByPrimaryKey(String logId) throws Exception {
		int result = -1;
		result = logInfoMapper.deleteByPrimaryKey(logId);
		return result;
	}
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public Integer deleteBatchByPrimaryKey(String logId) throws Exception{
		int result = -1;
		result = logInfoMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(logId));
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(LogInfo record) throws Exception{
		int result = -1;
		result = logInfoMapper.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param logId
	 * @return
	 */
	public LogInfo selectByPrimaryKey(String logId) throws Exception{
		return logInfoMapper.selectByPrimaryKey(logId);
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(LogInfo record) throws Exception{
		int result = -1;
		result = logInfoMapper.updateByPrimaryKeySelective(record);
		return result;
	}
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<LogInfoModel> selectLogInfoByPage(LogInfo record, User userInfo , Integer page, Integer pageSize) throws Exception{
		List<LogInfo> list = new ArrayList<LogInfo>();
		PageHelper.startPage(page, pageSize);
    	// 根据用户类型及所属区域进行分页
		int type = userInfo.getType();
		String userName = userInfo.getName();
		if(type == 1 ) {
			//管理员分页
			list = logInfoMapper.selectByPropertyByAdminPage(record);
			// 查询总数
			int count = logInfoMapper.selectCountByAdminProperty(record);
			PageInfo<LogInfo> pageInfo = new PageInfo<LogInfo>(list);
			List<LogInfoModel> logList = getLogList(list);
			long i = pageInfo.getTotal();
			PageTool<LogInfoModel> pageTool = new PageTool<LogInfoModel>(page, count, logList, pageSize);
	        
			return pageTool;
		}else {
			//普通用户根据用户名查新进行分页
			list = logInfoMapper.selectByPropertyByNormalPage(userName);
			// 查询总数
			int count = logInfoMapper.selectCountByNormalProperty(record);
			PageInfo<LogInfo> pageInfo = new PageInfo<LogInfo>(list);
			List<LogInfoModel> logList = getLogList(list);
			long i = pageInfo.getTotal();
			PageTool<LogInfoModel> pageTool = new PageTool<LogInfoModel>(page, count, logList, pageSize);			
			return pageTool;
			
		}     
	
	}
	
	 /**
	  * 清空所有日志
	  * @param record
	  * @return
	  */

	@Override
	public Integer deleteAllLog() throws Exception {
		int result = -1;
		result = logInfoMapper.deleteAllLog();
		// TODO Auto-generated method stub
		return result;
	}

	public List<LogInfoModel> getLogList(List<LogInfo> list){
		
		List<LogInfoModel> modelList = new ArrayList<LogInfoModel>();
		for(LogInfo log : list) {
			LogInfoModel  logModel= new LogInfoModel();
			logModel.setLogId(log.getLogId());
			logModel.setLogType(log.getLogType());
			logModel.setLogTime(log.getLogTime());
			logModel.setContent(log.getContent());
			logModel.setResult(log.getResult());
			logModel.setUserIp(log.getUserIp());
			logModel.setUserName(log.getUserName());
			modelList.add(logModel);
			
		}
		return modelList;
		
	}

	

}