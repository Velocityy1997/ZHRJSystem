package com.web.business.generator.comm.message.model;

import java.sql.Timestamp;

public class Message {
	
    private String id;
    /**
	 * 短信类型，0：发送消息，1：接受消息，2：短信通播，3：定位
	 */
    private Integer type;
    /**
     * 短信内容
     */
    private String content;

    private String userName;
    /**
	 * 指挥机卡号，不能为空
	 */
    private String zhjNum;
    /**
	 * 发送方卡号，不能为空
	 */
    private String sender;
    /**
	 * 接收方卡号，不能为空
	 */
    private String receiver;
    /**
	 * 发送类型，0：普通，1：特快
	 */
    private Integer sendType;

    private String userIp;
    /**
	 * 接收状态，0：未读，1：已读
	 */
    private Integer receiverState;

    private String createTime;
    /**
	 * 接收状态，0：失败，1：成功
	 */
    private Integer messageState;

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

    public Integer getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(Integer receiverState) {
        this.receiverState = receiverState;
    }

  
    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getMessageState() {
        return messageState;
    }

    public void setMessageState(Integer messageState) {
        this.messageState = messageState;
    }
}