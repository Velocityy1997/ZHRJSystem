package com.web.business.generator.util;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.business.generator.comm.historyTask.dao.HistoryTaskMapper;
import com.web.business.generator.comm.historyTask.model.HistoryTask;
import com.web.business.generator.comm.message.dao.MessageMapper;
import com.web.business.generator.comm.message.model.Message;
import com.web.business.generator.comm.message.services.impl.MessageServiceImpl;
import com.web.business.generator.system.terminal.services.ITerminalService;
import com.web.business.generator.system.user.services.IUserService;
import com.web.business.generator.system.zhj.dao.ZhjMapper;
import com.web.business.generator.util.task.TaskType;

/**
 * 短消息处理
 * @author 柳权
 * 2019年8月7日
 */

@Service("messageHandler")
public class MessageHandler implements IProcessService{
	
	
	@Autowired
	private MessageServiceImpl messageServiceImpl;
	
	

	@Transactional
	public boolean onReceiveTaskResult(HistoryTask task) {
		// TODO Auto-generated method stub
		
		try {
			Message mesModel = new Message();
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			mesModel.setId(uuid);
			mesModel.setContent(task.getContent());
			mesModel.setCreateTime(task.getEndTime());
			mesModel.setSender(task.getSender());
			mesModel.setReceiver(task.getReceiver());
			mesModel.setSendType(Integer.valueOf(task.getSendType()));
			mesModel.setUserIp(task.getUserIp());
			mesModel.setUserName(task.getUserName());
			
			if(task.getSuccess()) {
				mesModel.setMessageState(1);
			}else {
				mesModel.setMessageState(0);
			}
			
			int taskType = Integer.valueOf(task.getType());
			if(taskType == TaskType.BROAST) {
				mesModel.setType(0);//0：发送消息，1：接受消息，2：短信通播，3：定位
				mesModel.setReceiverState(1);
			}else if(taskType  == TaskType.SEND_MESSAGE){
				mesModel.setType(1);
				mesModel.setReceiverState(1);
			}else if (taskType== TaskType.REV_MESSAGE) {
				mesModel.setType(2);
				if (!task.getSuccess()) //失败
				{				
					return true;
				}else{                  //成功
					mesModel.setReceiverState(0);
				}
				
			}
			
			
			messageServiceImpl.insertSelective(mesModel);
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			  e.printStackTrace();
			System.out.println("解析任务出错");
		}
		return true;
	}



	public MessageServiceImpl getMessageServiceImpl() {
		return messageServiceImpl;
	}



	public void setMessageServiceImpl(MessageServiceImpl messageServiceImpl) {
		this.messageServiceImpl = messageServiceImpl;
	}

	
	
	
	

}
