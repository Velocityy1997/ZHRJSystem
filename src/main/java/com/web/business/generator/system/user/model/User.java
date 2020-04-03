package com.web.business.generator.system.user.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.web.business.generator.system.area.model.Area;
import com.web.business.generator.system.industry.model.Industry;
import com.web.common.util.model.AutoModel;

/**
 *        用户实体类
 * @author GouYudong
 * @date 2019年7月3日
 *
 */

public class User extends AutoModel{
	private String id;

    private String name;

    private Integer state;
   // 用户类型 1 管理员  0普通同用户
    private Integer type;

    private Integer status;//无用

    private String password;

    private Integer errorCode;

    private Date lastTime;

    private String currentIp;

    private Integer loginNum;

    private String phone;

    private Date createDate;

    private String lastIp;//最后一次登录的ip

    private Integer passwordState;

    private String remark;

    private String userIndustry;

    private String userArea; // 用户所在区域的最小级别的id
    
    private String areaName; // 用户所在区域的全称
    
    private String industryName; // 用户所在行业名称
    
    private List<Area> areaList;
    
    private List<Industry> industList;
    
    private Map<String, Map<String, String>> areaInfoMap; //当前用户所在区域的 省市区级别信息

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

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getCurrentIp() {
        return currentIp;
    }

    public void setCurrentIp(String currentIp) {
        this.currentIp = currentIp == null ? null : currentIp.trim();
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp == null ? null : lastIp.trim();
    }

    public Integer getPasswordState() {
        return passwordState;
    }

    public void setPasswordState(Integer passwordState) {
        this.passwordState = passwordState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUserIndustry() {
        return userIndustry;
    }

    public void setUserIndustry(String userIndustry) {
        this.userIndustry = userIndustry == null ? null : userIndustry.trim();
    }

    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea == null ? null : userArea.trim();
    }
    
    

	public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	public List<Industry> getIndustList() {
		return industList;
	}

	public void setIndustList(List<Industry> industList) {
		this.industList = industList;
	}

	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", state=" + state + ", type=" + type + ", status=" + status
				+ ", password=" + password + ", errorCode=" + errorCode + ", lastTime=" + lastTime + ", currentIp="
				+ currentIp + ", loginNum=" + loginNum + ", phone=" + phone + ", createDate=" + createDate + ", lastIp="
				+ lastIp + ", passwordState=" + passwordState + ", remark=" + remark + ", userIndustry=" + userIndustry
				+ ", userArea=" + userArea + ", areaName=" + areaName + ", industryName=" + industryName + ", areaList="
				+ areaList + ", industList=" + industList + ", areaInfoMap=" + areaInfoMap + ", provinceName="
				+ provinceName + ", cityName=" + cityName + ", zoneName=" + zoneName + ", provinceId=" + provinceId
				+ ", cityId=" + cityId + ", zoneId=" + zoneId + "]";
	}

	

	

	



	
    
    
    
    
    
}