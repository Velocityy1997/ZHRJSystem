package com.web.business.generator.comm.taskList.model;
/*
 *  存放指挥机任务的参数，返回前端使用
 *  @author: GouYudong
 *  创建时间:  2019年11月11日下午3:39:27
 */
public class ZhjTaskInfo {
	
	private String name;//指挥机名称
	private String cardNum;//卡号
	private String type;//类型    0：普通指挥机    1：超级指挥机
	private String brocastAdd;//广播地址
	private String frequency;//服务频度
	private String status;// 服务状态  0:停止；1:忙碌；2:空闲
	private String txlevel;//通信等级
	private String txlength;//通信长度 
	private String longitude ;//经度
	private String latitude;//纬度
	private String locateTime;//定位时间
	//private String name;//
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBrocastAdd() {
		return brocastAdd;
	}
	public void setBrocastAdd(String brocastAdd) {
		this.brocastAdd = brocastAdd;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTxlevel() {
		return txlevel;
	}
	public void setTxlevel(String txlevel) {
		this.txlevel = txlevel;
	}
	public String getTxlength() {
		return txlength;
	}
	public void setTxlength(String txlength) {
		this.txlength = txlength;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLocateTime() {
		return locateTime;
	}
	public void setLocateTime(String locateTime) {
		this.locateTime = locateTime;
	}

	
	
}

