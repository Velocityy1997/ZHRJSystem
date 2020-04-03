package com.web.business.generator.monitor.baudInfo.services.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.impl.conn.SingleClientConnManager;
import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;

import com.web.common.util.spring.PageTool;

import com.web.business.generator.monitor.baudInfo.model.BaudInfo;
import com.web.business.generator.monitor.baudInfo.dao.BaudInfoMapper;
import com.web.business.generator.monitor.baudInfo.services.IBaudInfoService;
import com.web.business.generator.util.TimeUtil;

@Service
public class BaudInfoServiceImpl implements IBaudInfoService {
	@Resource
	private BaudInfoMapper baudInfoMapper;
	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteByPrimaryKey(String id) throws Exception {
		int result = -1;
		result = baudInfoMapper.deleteByPrimaryKey(id);
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
		result = baudInfoMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(id));
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(BaudInfo record) throws Exception{
		int result = -1;
		result = baudInfoMapper.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	public BaudInfo selectByPrimaryKey(String id) throws Exception{
		return baudInfoMapper.selectByPrimaryKey(id);
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(BaudInfo record) throws Exception{
		int result = -1;
		result = baudInfoMapper.updateByPrimaryKeySelective(record);
		return result;
	}
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<BaudInfo> selectBaudInfoByPage(BaudInfo record, Integer page, Integer pageSize) throws Exception{
		List<BaudInfo> list = new ArrayList<BaudInfo>();
    	
    	int start = (page != null ? page : 1);
    		int max = pageSize != null ? pageSize : 10;
             record.setStart((start-1)*pageSize);
            record.setMax(max);
		// 查询分页
		list = baudInfoMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = baudInfoMapper.selectCountByProperty(record);
		PageTool<BaudInfo> pageTool = new PageTool<>( page, count, list, pageSize);
        return pageTool;
		
	}

	/**
	 * 获取电量和信号
	 * @return
	 */
	public Map<String, String> getBaudList() {
		// TODO Auto-generated method stub
		List<BaudInfo> list = new ArrayList<BaudInfo>();
		Map<String, String> map = new HashMap<String,String>();
 		try {
			list = baudInfoMapper.getBaudList();
			
			String batteryStatus = list.get(0).getBatteryStatus();
			String icStatus = list.get(0).getIcStatus();
			
			String hardwardStatus = list.get(0).getHardwardStatus();
			
			String instationStatus = list.get(0).getInstationStatus();
			
			String baudsInfo = list.get(0).getBaudsInfo();
			map.put("icStatus", ""+ icStatus);	
			map.put("hardwardStatus", ""+ hardwardStatus);	
			map.put("instationStatus", ""+ instationStatus);	
			map.put("batteryStatus", batteryStatus);
			map.put("baudsInfo", baudsInfo);
			
			Timestamp currentTime = new Timestamp(new Date().getTime());
			//2019-05-26
			//17-14-55
			//map.put("date", TimeUtil.getDate(new Timestamp(list.get(0).getBdTime().getTime())));
			//map.put("time", TimeUtil.getTime(new Timestamp(list.get(0).getBdTime().getTime())));
			Date date = new Date();
			
			String dateStr =  "yyyy-MM-dd";
			String timeStr =  "HH:mm:ss";
			
			SimpleDateFormat sdfDate = new SimpleDateFormat(dateStr);
			SimpleDateFormat sdfTime = new SimpleDateFormat(timeStr);
			
			map.put("date", sdfDate.format(date));
			map.put("time", sdfTime.format(date));
			map.put("serviceState", getMonitorServiceStatus()+"");	
			
			if(getMonitorServiceStatus() == 1)
			{
				map.put("rhptState", 1+"");	
			}else{
				map.put("rhptState", 0+"");	
			}			
			
			int status = 0;
			if(icStatus.equals("1") || hardwardStatus.equals("1") || hardwardStatus.equals("1") 
					|| getMonitorServiceStatus()==1)
			{
				status = 1;
				
			}
			map.put("status",status+"");	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return map;
	}
	 
	public int getMonitorServiceStatus() {
		// TODO Auto-generated method stub
		List<BaudInfo> list = new ArrayList<BaudInfo>();
		list = baudInfoMapper.getBaudList();
		int result=0;
		Timestamp currentTime = new Timestamp(new Date().getTime());
		Timestamp updateTime = new Timestamp(list.get(0).getUpdateTime().getTime());
		long nn = currentTime.getTime()-updateTime.getTime();

		if(currentTime.getTime()-updateTime.getTime()>20*1000)
		{
			result = 1;
		}
		
		return result;

	}
	
}