package com.web.business.generator.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.common.annotations.Beta;
import com.web.business.generator.comm.historyTask.model.HistoryTask;
import com.web.business.generator.comm.historyTask.services.IHistoryTaskService;
import com.web.business.generator.comm.historyTask.services.impl.HistoryTaskServiceImpl;
import com.web.business.generator.system.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;



@Component
public class AnalysisService {

	
	@Resource
	private HistoryTaskServiceImpl historyTaskServiceImpl;
	@Resource(name="taskHandleService")
	private IProcessService processService;
	
	private ExecutorService taskPool = Executors.newCachedThreadPool();
	private boolean flag = false;
	public static List<String> taskIds = new ArrayList<String>();
	public static List<User> loginUserList = new ArrayList<User>();
	
	public static List<HttpSession> allSessions = new ArrayList<HttpSession>();
	
	public static int userFlag = 0;
	
	//开启服务
	public void startAnalysisService() {
		flag = true;
		taskPool.execute(mRunnable);
		//taskPool.execute(mRunnable1);
		System.out.println("处理服务开启>>>>>>>>");
	}
	
	public void stopAnalysisService() {
		flag = true ;
		taskPool.execute(mRunnable);
		//taskPool.execute(mRunnable1);
		System.out.println("处理服务销毁>>>>>>>>");
	}
	
	
	
	
	
	
	/**
	 * 测试数据
	 */
	private Runnable mRunnable1 = new Runnable() {
		public void run() {
			while (flag) {
				try {
					
					System.out.println("插入数据++++++++++++++++++++++++");
					//获取未处理完的历史任务
					for(int  i = 0 ; i < 10; i++) {
						HistoryTask model = new HistoryTask();
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						int recordState = 1;
						String type = "101";
						
						model.setId(uuid);
						model.setType(type);
						model.setSendType(0);
						model.setRecordState(recordState);
						
						Integer status = historyTaskServiceImpl.insertSelective(model);	
					}
					
					Thread.sleep(5000);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	};
	
	//任务启动处理历史任务线程
	private Runnable mRunnable = new Runnable() {
		public void run() {
			while (flag) {
				try {
					
					/*
					 * HistoryTask model = new HistoryTask(); for(int i = 0 ; i < 100; i++) { String
					 * uuid = UUID.randomUUID().toString().replaceAll("-", ""); int recordState = 1;
					 * boolean status =historyTaskServiceImpl.InsertData(uuid,recordState); }
					 */
					System.out.println("获取未处理完的历史任务++++++++++++++++++++++++");
					//获取未处理完的历史任务
					List<HistoryTask> taskDate = historyTaskServiceImpl.findUnProcessedTask();
					for(HistoryTask task : taskDate) {
						boolean status = processService.onReceiveTaskResult(task);//消息处理
						{
							task.setRecordState(2);
							historyTaskServiceImpl.updateByPrimaryKeySelective(task);
						}
						
					}
					
					Thread.sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	};
	
	
	
	
	
	


	
	public HistoryTaskServiceImpl getHistoryTaskServiceImpl() {
		return historyTaskServiceImpl;
	}

	public void setHistoryTaskServiceImpl(HistoryTaskServiceImpl historyTaskServiceImpl) {
		this.historyTaskServiceImpl = historyTaskServiceImpl;
	}

	public IProcessService getProcessService() {
		return processService;
	}
	public void setProcessService(IProcessService processService) {
		this.processService = processService;
	}
	public static List<String> getTaskIds() {
		return taskIds;
	}
	public static void setTaskIds(List<String> taskIds) {
		AnalysisService.taskIds = taskIds;
	}

	public static List<User> getLoginUserList() {
		return loginUserList;
	}

	public static void setLoginUserList(List<User> loginUserList) {
		AnalysisService.loginUserList = loginUserList;
	}

	public static List<HttpSession> getAllSessions() {
		return allSessions;
	}

	public static void setAllSessions(List<HttpSession> allSessions) {
		AnalysisService.allSessions = allSessions;
	}
	
	
}
