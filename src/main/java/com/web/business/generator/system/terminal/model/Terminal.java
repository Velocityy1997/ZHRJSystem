package com.web.business.generator.system.terminal.model;

import java.util.Date;
import java.util.Map;

import com.web.common.util.model.AutoModel;

/**
 * 终端实体类
 * 
 * @author GouYudong
 * @date 2019年7月3日
 *
 */
public class Terminal extends AutoModel {
	private String id;

	private String name;

	private Integer type;//1:区内；0:区外

	private Integer status;

	private String cardNum;

	private Integer txLevel;

	private String brocastAddress;

	private String remark;

	private Integer frequency;

	private String zhjNum;

	private Integer txLength;

	private Integer positionState;

	private Date createDate;

	private String positionId;

	private String terArea; // 区域id

	private String terIndustry; // 行业id

	private String areaName;

	private String industryName;

	private String provinceName;

	private String cityName;

	private String zoneName;

	private String provinceId;

	private String cityId;

	private String zoneId;

	private Map<String, Map<String, String>> areaInfoMap; // 当前用户所在区域的 省市区级别信息
	

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public Map<String, Map<String, String>> getAreaInfoMap() {
		return areaInfoMap;
	}

	public void setAreaInfoMap(Map<String, Map<String, String>> areaInfoMap) {
		this.areaInfoMap = areaInfoMap;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum == null ? null : cardNum.trim();
	}

	public Integer getTxLevel() {
		return txLevel;
	}

	public void setTxLevel(Integer txLevel) {
		this.txLevel = txLevel;
	}

	public String getBrocastAddress() {
		return brocastAddress;
	}

	public void setBrocastAddress(String brocastAddress) {
		this.brocastAddress = brocastAddress == null ? null : brocastAddress.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public String getZhjNum() {
		return zhjNum;
	}

	public void setZhjNum(String zhjNum) {
		this.zhjNum = zhjNum == null ? null : zhjNum.trim();
	}

	public Integer getTxLength() {
		return txLength;
	}

	public void setTxLength(Integer txLength) {
		this.txLength = txLength;
	}

	public Integer getPositionState() {
		return positionState;
	}

	public void setPositionState(Integer positionState) {
		this.positionState = positionState;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId == null ? null : positionId.trim();
	}

	public String getTerArea() {
		return terArea;
	}

	public void setTerArea(String terArea) {
		this.terArea = terArea == null ? null : terArea.trim();
	}

	public String getTerIndustry() {
		return terIndustry;
	}

	public void setTerIndustry(String terIndustry) {
		this.terIndustry = terIndustry == null ? null : terIndustry.trim();
	}
}