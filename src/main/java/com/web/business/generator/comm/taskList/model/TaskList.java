package com.web.business.generator.comm.taskList.model;

import java.sql.Timestamp;

public class TaskList {
	
    private String id;//v
    private Integer type;   //类型v
    private String content;  //内容v
    private Integer level;  //等级  
    private String description;  //描述
    private Timestamp startTime;  //开始时间v
    private String userName;//用户名
    private String zhjNum;//指挥机卡号  v
    private String sender;//发送方  v
    private String receiver;//接收方---存通播地址v
    private Timestamp planTime;//计划时间
    private Integer sentTimes;//发送次数v
    private Integer sendType;//发送类型  普通：0，特快：1  v
    private String userIp;//用户ip  
    private Integer start;//起始坐标
    private Integer max;//最大  

    
    
    
    public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
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

    public Timestamp getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Timestamp planTime) {
        this.planTime = planTime;
    }

    public Integer getSentTimes() {
        return sentTimes;
    }

    public void setSentTimes(Integer sentTimes) {
        this.sentTimes = sentTimes;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }
}