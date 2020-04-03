package com.web.business.generator.util.task;

public class TaskType {
	

	public static final int SEND_MESSAGE = 101; //发送消息
	public static final int REV_MESSAGE = 102; //接收消息	
	public static final int BDDW = 103; //指挥机本机定位
	public static final int BROAST = 104;//通播
	public static final int YLWZ = 105;//友邻位置
	public static final int BDNAC = 106;//指令导航
	public static final int BDNAL = 107;//路径规划
	public static final int RHTX = 108;//融合通信
	public static final int JKDX = 109;//监控短信
	
	public static final int RWCZ = 110;//任务重置
	
	public static final int XGMC = 111;//修改名称
	public static final int FSDX = 112;//发送短信
	
	public static final int WZCX = 113;//终端位置查询
	public static final int LSGJ = 114;//终端历史轨迹
	
	// 通信时间设置
	public static final int SETUP_TIME = 7;
	
	// 系统设置（通讯频度&串口）
	public static final int SETUP_SYS = 2;
	
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
		case RWCZ:
			name = "任务重置";
			break;
		case WZCX:
			name = "位置查询";
			break;
		case LSGJ:
			name = "历史轨迹";
			break;
		case XGMC:
			name = "修改名称";
			break;
		case FSDX:
			name = "发送短信";
			break;
		case SETUP_SYS:
			name = "系统设置";
			break;
		case SETUP_TIME:
			name = "通信时间设置";
			break;
		default:
			break;
		}
		
		return name;
		
	}


	
}
