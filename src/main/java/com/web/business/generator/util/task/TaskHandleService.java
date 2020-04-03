package com.web.business.generator.util.task;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.business.generator.comm.historyTask.model.HistoryTask;
import com.web.business.generator.util.IProcessService;


@Service("taskHandleService")
public class TaskHandleService implements IProcessService {
	
	
	/**
	 * 接收消息任务处理类
	 */
	
	private IProcessService iProcessSerice;	
	
	public boolean onReceiveTaskResult(HistoryTask task) {
		System.out.println("正在处理任务" + task);
		boolean flagStatus = false;
		switch (Integer.valueOf(task.getType())) {
		//发送消息
		case TaskType.SEND_MESSAGE:
		//接收消息	
		case TaskType.REV_MESSAGE:
		//通波
		case TaskType.BROAST:
			flagStatus = iProcessSerice.onReceiveTaskResult(task);
			break;	
		default:
			flagStatus = true;
			break;
		}
		return flagStatus;
	}
	
	
	public IProcessService getiProcessSerice() {
		return iProcessSerice;
	}
	@Resource(name = "messageHandler")
	public void setiProcessSerice(IProcessService iProcessSerice) {
		this.iProcessSerice = iProcessSerice;
	}

	
}
