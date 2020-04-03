package com.web.schedule;

import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时线程
 * @author baitao
 * @date 2017-03-06
 *
 */

public class ScheduleController {

	private final static long MILLIS = 1000;
	private final static long SECOND = 1 * MILLIS;
	private final static long MINUTE = 60 * SECOND;
	private final static long HOUR = 60 * MINUTE;
	private final static long DAY = 24 * HOUR;
	private final static Logger log = LoggerFactory.getLogger(ScheduleController.class);
	private Timer timer = new Timer();
	private final static ScheduleController instance = new ScheduleController();

	public ScheduleController() {
	}

	public final static ScheduleController instance() {
		return instance;
	}

	public void CancelTimer(){
		this.timer.cancel();
	}
	
	public void start() {
		// 位置云rp链路重连操作定时任务
		SendMessageTask sendMessageTask = new SendMessageTask();
		timer.schedule(sendMessageTask, SECOND , SECOND);
		log.error("----线程开始启动-------------");
	}
	 
}
