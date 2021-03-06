package com.web.business.generator.comm.message.services.impl;

import java.util.ArrayList;
import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shara.common.util.page.PageUtil;


import com.web.business.generator.comm.message.model.Message;
import com.web.business.generator.comm.message.model.MessageModel;
import com.web.business.generator.comm.message.dao.MessageMapper;
import com.web.business.generator.comm.message.services.IMessageService;


@Service
public class MessageServiceImpl implements IMessageService {
	@Resource
	private MessageMapper messageMapper;
	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteByPrimaryKey(String id) throws Exception {
		int result = -1;
		result = messageMapper.deleteByPrimaryKey(id);
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
		 try {
			 int r  = messageMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(id));
			 if(r > 0 ) {
				 result.setMessage("删除成功");
				 result.setSuccess(true);
			 }else {
				 result.setMessage("删除失败");
				 result.setSuccess(false);
				 }
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			
		}
		return result;
	}
	
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	public Message selectByPrimaryKey(String id) throws Exception{
		return messageMapper.selectByPrimaryKey(id);
	}
	
    
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<MessageModel> selectMessageByPage(Message record, Integer page, Integer pageSize) throws Exception{
		List<Message> list = new ArrayList<Message>();
		PageHelper.startPage(page, pageSize);
		// 查询分页
		list = messageMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = messageMapper.selectCountByProperty(record);
		PageInfo<Message> pageInfo = new PageInfo<Message>(list);
		List<MessageModel> mesList = getMesList(list);
		long i = pageInfo.getTotal();
		PageTool<MessageModel> pageTool = new PageTool<MessageModel>( page, count, mesList, pageSize);
        return pageTool;
		
	}
	
	public List<MessageModel> getMesList(List<Message> list){
		List<MessageModel> mesModelList  =  new ArrayList<MessageModel>();
		for(Message message : list) {
			MessageModel mesModel = new MessageModel();
			mesModel.setMesId(message.getId());
			mesModel.setMesContent(message.getContent());
			mesModel.setMesCreateTime(message.getCreateTime());
			mesModel.setMesReceiver(message.getReceiver());
			mesModel.setMesSender(message.getSender());		
			
			int type = message.getType();
			if(type == 0) {
				mesModel.setMesType("短信通播");
			}else if(type == 1 ) {
				mesModel.setMesType("发送");
			}else if(type == 2) {
				mesModel.setMesType("接收");
			}else if(type == 3 ) {
				mesModel.setMesType("定位");
			}
			
			mesModelList.add(mesModel);
		}
 		
		return mesModelList;
		
	}
	
	//清空所有信息


	@Override
	public RestResponse deleteAll() throws Exception {
		RestResponse result = new RestResponse();
		try {
			int r = messageMapper.deleteAll();
			
			if(r > 0 ) {
				 result.setMessage("清空成功");
				 result.setSuccess(true);
			 }else {
				 result.setMessage("清空失败");
				 result.setSuccess(false);
				 }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}	
				
		return result;
	}

	@Override
	public Integer insertSelective(Message record) throws Exception {
		// TODO Auto-generated method stub
		int result = -1;
		result = messageMapper.insertSelective(record);
		return result;
	}

	@Override
	public Integer updateByPrimaryKeySelective(Message record) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	 

}