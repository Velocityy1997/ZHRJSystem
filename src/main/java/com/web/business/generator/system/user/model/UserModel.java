package com.web.business.generator.system.user.model;

import java.util.Map;

public class UserModel {

	private String userID;

	private String userName;

	private String userType;

	private String userRemark;

	private String userPhone;

	private String userArea;

	private String userIndustry;

	

	private String areaName; // 用户所在区域的全称

	private String industryName; // 用户所在行业名称
	
	private String industryId ;

	private String level;

	private Map<String, Map<String, String>> areaInfoMap; // 当前用户所在区域的 省市区级别信息

	private String provinceName;

	private String cityName;

	private String zoneName;

	private String provinceId;

	private String cityId;

	private String zoneId;
	

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

	public Map<String, Map<String, String>> getAreaInfoMap() {
		return areaInfoMap;
	}

	public void setAreaInfoMap(Map<String, Map<String, String>> areaInfoMap) {
		this.areaInfoMap = areaInfoMap;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserArea() {
		return userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	public String getUserIndustry() {
		return userIndustry;
	}

	public void setUserIndustry(String userIndustry) {
		this.userIndustry = userIndustry;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getIndustryId() {
		return industryId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	@Override
	public String toString() {
		return "UserModel [userID=" + userID + ", userName=" + userName + ", userType=" + userType + ", userRemark="
				+ userRemark + ", userPhone=" + userPhone + ", userArea=" + userArea + ", userIndustry=" + userIndustry
				+ ", areaName=" + areaName + ", industryName=" + industryName + ", industryId=" + industryId
				+ ", level=" + level + ", areaInfoMap=" + areaInfoMap + ", provinceName=" + provinceName + ", cityName="
				+ cityName + ", zoneName=" + zoneName + ", provinceId=" + provinceId + ", cityId=" + cityId
				+ ", zoneId=" + zoneId + "]";
	}

	
	
}
