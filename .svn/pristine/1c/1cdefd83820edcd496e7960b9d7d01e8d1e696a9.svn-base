package com.web.business.generator.comm.blackList.services.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.web.business.generator.comm.blackList.model.BlackList;
import com.web.business.generator.comm.blackList.model.BlackListModel;
import com.web.business.generator.comm.blackList.dao.BlackListMapper;
import com.web.business.generator.comm.blackList.services.IBlackListService;
import com.web.business.generator.comm.preMessage.model.PreMessage;
import com.web.business.generator.system.user.model.User;

@Service
public class BlackListServiceImpl implements IBlackListService {
	@Resource
	private BlackListMapper blackListMapper;
	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteByPrimaryKey(String id) throws Exception {
		int result = -1;
		result = blackListMapper.deleteByPrimaryKey(id);
		return result;
	}
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public RestResponse deleteBatchByPrimaryKey(String id) throws Exception{
		RestResponse result = new RestResponse();
		List idList =  PageUtil.getIdsForList(id);
		
		int r = blackListMapper.deleteBatchByPrimaryKey(idList);
		if(r > 0) {
			result.setMessage("北斗卡删除成功");
			result.setSuccess(true);
			
		}else {
			result.setMessage("北斗卡删除失败");
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public RestResponse insertSelective(BlackList record) throws Exception{
		RestResponse result = new RestResponse(); 
		String simId = record.getSimId();
		List  simList  =  blackListMapper.selectBySimId(simId);
		if(simList != null && simList.size() > 0) {
			
				result.setMessage("北斗卡已存在！");
				result.setSuccess(false);
				return result;

		}
		
		int r  = blackListMapper.insertSelective(record);
		
        if (r > 0) {
            result.setSuccess(true).setMessage("操作成功");
        } else {
           result.setSuccess(false).setMessage("操作失败");
        }
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	public BlackList selectByPrimaryKey(String id) throws Exception{
		return blackListMapper.selectByPrimaryKey(id);
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public RestResponse updateByPrimaryKeySelective(BlackList record) throws Exception{
		
		RestResponse result = new RestResponse(); 
		Timestamp ts = new Timestamp(new Date().getTime());
		String simId = record.getSimId();
		List<BlackList> simIdList = selectBySimId(simId);
		record.setCreateDate(ts);
		if(simIdList != null && simIdList.size() >0 ) {
			if(!(( simIdList.get(0)).getId()).equals(record.getId())) {
				result.setMessage("卡号已存在！");
				result.setSuccess(false);
				return result;
			}
		}
		
		int r  = blackListMapper.updateByPrimaryKeySelective(record);
		if(r >0 ) {
			result.setMessage("卡号修改成功");
			result.setSuccess(true);
		}else {
			result.setMessage("卡号修改失败");
			result.setSuccess(false);
		}
		return result;
	}
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<BlackListModel> selectBlackListByPage(BlackList record, Integer page, Integer pageSize) throws Exception{
		
		List<BlackList> list = new ArrayList<BlackList>();
		PageHelper.startPage(page, pageSize);
		// 查询分页
		list = blackListMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = blackListMapper.selectCountByProperty(record);
		PageInfo<BlackList> pageInfo = new PageInfo<BlackList>(list);
		List<BlackListModel> listBlack  = getBlackList(list);
		long i = pageInfo.getTotal();
  		PageTool<BlackListModel> pageTool = new PageTool<BlackListModel>( page, count, listBlack, pageSize);
        return pageTool;
		
	}
	 
	
	//根据SIMID 进行查询
		public List<BlackList> selectBySimId(String simId ){
			List<BlackList> list = blackListMapper.selectBySimId(simId);
			return list;
		}
		
	
		
	public List<BlackListModel> getBlackList(List<BlackList> list){
		List<BlackListModel> listBlack = new ArrayList<BlackListModel>();
		for(BlackList blackList: list) {
			BlackListModel blaList = new BlackListModel();
			blaList.setBlackSimId(blackList.getSimId());
			blaList.setBlackId(blackList.getId());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strdate = format.format(blackList.getCreateDate());
			blaList.setCreatTime(strdate);
			blaList.setRemark(blackList.getRemark());
			listBlack.add(blaList);
			
			
		}
		return listBlack;
		
	}
		

}