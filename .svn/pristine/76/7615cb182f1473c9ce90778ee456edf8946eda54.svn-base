package com.web.schedule;

import java.text.ParseException;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SendMessageTask extends TimerTask{

	protected final static Logger log = LoggerFactory.getLogger(SendMessageTask.class);
	
	//sendMsg
	public void sendMsg() throws ParseException
	{
		// 每秒推送所有设备时间
		//TimeHandler.sendSocketAllTime();
		}

	@Override
	public void run() {
		try {
			sendMsg();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	 
}
