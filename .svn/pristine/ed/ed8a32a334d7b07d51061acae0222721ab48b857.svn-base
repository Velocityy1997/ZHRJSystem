package com.web.business.generator.comm.historyTask.model;

import java.sql.Timestamp;

import java.util.Date;

public class HistoryModel {
	
	
	public static final int SEND_MESSAGE = 101; //发送消息
	public static final int REV_MESSAGE = 102; //接收消息	
	public static final int BDDW = 103; //指挥机本机定位
	public static final int BROAST = 104;//通播
	public static final int YLWZ = 105;//友邻位置
	public static final int BDNAC = 106;//指令导航
	public static final int BDNAL = 107;//路径规划
	public static final int RHTX = 108;//融合通信
	public static final int JKDX = 109;//监控短信
	
	
    private String taskId;

    private String taskType;

    private String taskContent;

    private String taskDescription;

    private String startTime;

    private String endTime;

    private String taskName;

    private String zhjNum;

    private String taskSender;

    private String taskReceiver;

    private Integer sendTimes;

    private String taskSendType;
    /**
   	 * 成功类型，0：失败，1：成功
   	 */
    private String success;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	
	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getZhjNum() {
		return zhjNum;
	}

	public void setZhjNum(String zhjNum) {
		this.zhjNum = zhjNum;
	}

	public String getTaskSender() {
		return taskSender;
	}

	public void setTaskSender(String taskSender) {
		this.taskSender = taskSender;
	}

	public String getTaskReceiver() {
		return taskReceiver;
	}

	public void setTaskReceiver(String taskReceiver) {
		this.taskReceiver = taskReceiver;
	}

	public Integer getSendTimes() {
		return sendTimes;
	}

	public void setSendTimes(Integer sendTimes) {
		this.sendTimes = sendTimes;
	}

	public String getTaskSendType() {
		return taskSendType;
	}

	public void setTaskSendType(String taskSendType) {
		this.taskSendType = taskSendType;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	
	
	public static String getTaskType(int type) {
		String name = "未知类型";
		
		switch (type) {
		case SEND_MESSAGE:
			name = "发送短信";
			break;
		case REV_MESSAGE:
			name = "接收短信";
			break;
		case BDDW:
			name = "指挥机本机定位";
			break;
		case BROAST:
			name = "通播";
			break;
		case YLWZ:
			name = "友邻位置";
			break;
		case BDNAC:
			name = "指令导航";
			break;
		case BDNAL:
			name = "路径规划";
			break;	
		case RHTX:
			name = "融合通信";
			break;
		case JKDX:
			name = "监控短信";
			break;
		default:
			break;
		}
		
		return name;
		
	}
	
	
	
}
