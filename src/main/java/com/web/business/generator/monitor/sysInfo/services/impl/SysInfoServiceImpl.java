package com.web.business.generator.monitor.sysInfo.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;
import com.web.common.util.spring.PageTool;

import com.web.business.generator.monitor.sysInfo.model.SysInfo;
import com.web.business.generator.monitor.sysInfo.dao.SysInfoMapper;
import com.web.business.generator.monitor.sysInfo.services.ISysInfoService;

@Service
public class SysInfoServiceImpl implements ISysInfoService {
	@Resource
	private SysInfoMapper sysInfoMapper;
	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteByPrimaryKey(String id) throws Exception {
		int result = -1;
		result = sysInfoMapper.deleteByPrimaryKey(id);
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
		result = sysInfoMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(id));
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(SysInfo record) throws Exception{
		int result = -1;
		result = sysInfoMapper.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	public SysInfo selectByPrimaryKey(String id) throws Exception{
		return sysInfoMapper.selectByPrimaryKey(id);
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(SysInfo record) throws Exception{
		int result = -1;
		result = sysInfoMapper.updateByPrimaryKeySelective(record);
		return result;
	}
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<SysInfo> selectSysInfoByPage(SysInfo record, Integer page, Integer pageSize) throws Exception{
		List<SysInfo> list = new ArrayList<SysInfo>();
    	
    	int start = (page != null ? page : 1);
    		int max = pageSize != null ? pageSize : 10;
//             record.setStart((start-1)*pageSize);
//            record.setMax(max);
		// 查询分页
		list = sysInfoMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = sysInfoMapper.selectCountByProperty(record);
		PageTool<SysInfo> pageTool = new PageTool<>( page, count, list, pageSize);
        return pageTool;
		
	}

	//更新通讯频率
	@Override
	public int updateFrequency(Integer num) {

		int result = -1;
		result = sysInfoMapper.updateFrequency(num);

		if (result > 0) {
			result = 1;//修改成功
		} else {
			result = 0;//修改失败
		}

		return result;
	}

	@Override
	public int updatePort(String port, Integer rate) {
		int result = -1;
		result = sysInfoMapper.updatePort(port,rate);

		if (result > 0) {
			result = 1;//修改成功
		} else {
			result = 0;//修改失败
		}

		return result;
	}

	public Integer updateInfo(SysInfo model)  throws Exception{
		// TODO Auto-generated method stub
		int result = -1;
		result = sysInfoMapper.updateInfo(model);
		return result;
		
	}

	/**
	 * 根据当前指挥机号查询其设置信息
	 * @param cardNum
	 * @return
	 */
	public Map<String, String> getInfo(String cardNum) {
		// TODO Auto-generated method stub
		SysInfo model = new SysInfo();
		Map<String, String> mapInfo = new HashMap<String, String>();
 		model = sysInfoMapper.getInfo(cardNum);
 		mapInfo.put("bdtimeFrequence", String.valueOf(model.getBdtimeFrequence()));
 		mapInfo.put("baudRate", String.valueOf(model.getBaudRate()));
 		mapInfo.put("port", model.getPort());
 		mapInfo.put("zhjNum", model.getZhjNum());
		return mapInfo;
	}
	 

}