package com.web.business.generator.system.logInfo.model;


import java.util.Date;

import com.web.common.util.model.AutoModel;

public class LogInfo extends AutoModel{
	
	
	 	private String logId;

	    private String logType;

	    private String content;

	    private String logTime;

	    private String result;

	    private String userName;

	    private String zhjNum;

	    private String sender;

	    private String receiver;

	    private String userIp;

	    private String zhjName;

	    public String getLogId() {
	        return logId;
	    }

	    public void setLogId(String logId) {
	        this.logId = logId == null ? null : logId.trim();
	    }

	    public String getLogType() {
	        return logType;
	    }

	    public void setLogType(String logType) {
	        this.logType = logType == null ? null : logType.trim();
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content == null ? null : content.trim();
	    }

	    public String getLogTime() {
			return logTime;
		}

		public void setLogTime(String logTime) {
			this.logTime = logTime;
		}

		public String getResult() {
	        return result;
	    }

	    public void setResult(String result) {
	        this.result = result == null ? null : result.trim();
	    }

	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName == null ? null : userName.trim();
	    }

	    public String getZhjNum() {
	        return zhjNum;
	    }

	    public void setZhjNum(String zhjNum) {
	        this.zhjNum = zhjNum == null ? null : zhjNum.trim();
	    }

	    public String getSender() {
	        return sender;
	    }

	    public void setSender(String sender) {
	        this.sender = sender == null ? null : sender.trim();
	    }

	    public String getReceiver() {
	        return receiver;
	    }

	    public void setReceiver(String receiver) {
	        this.receiver = receiver == null ? null : receiver.trim();
	    }

	    public String getUserIp() {
	        return userIp;
	    }

	    public void setUserIp(String userIp) {
	        this.userIp = userIp == null ? null : userIp.trim();
	    }

	    public String getZhjName() {
	        return zhjName;
	    }

	    public void setZhjName(String zhjName) {
	        this.zhjName = zhjName == null ? null : zhjName.trim();
	    }

		@Override
		public String toString() {
			return "LogInfo [logId=" + logId + ", logType=" + logType + ", content=" + content + ", logTime=" + logTime
					+ ", result=" + result + ", userName=" + userName + ", zhjNum=" + zhjNum + ", sender=" + sender
					+ ", receiver=" + receiver + ", userIp=" + userIp + ", zhjName=" + zhjName + "]";
		}
	    
	    
}