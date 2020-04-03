package com.web.business.generator.system.zhj.model;

import java.util.List;

import com.web.business.generator.system.area.model.Area;
import com.web.business.generator.system.industry.model.Industry;
import com.web.common.util.model.AutoModel;

/**
 *      指挥机实体类
 * @author GouYudong
 * @date 2019年7月3日
 *
 */
public class Zhj extends AutoModel{
    private String id;

    private String name;
    /**
	 * 类型    0：普通指挥机    1：超级指挥机
	 */
    private Integer type;
    /**
	 * 区号  范围1~63。1~5 超级指挥机，其他普通指挥机
	 */
    private Integer zone;
    /**
	 * 服务状态  0:停止；1:忙碌；2:空闲
	 */
    private Integer status;
    /**
	 * 卡号
	 */
    private String cardNum;
    /**
	 * 通信等级
	 */
    private Integer txlevel;
    /**
	 * 广播地址
	 */
    private String brocastAdd;

    private String remark;
    /**
	 * 服务频度
	 */
    private Integer frequency;
    /**
	 * 加密标志
	 */
    private Integer encryptflag;
    /**
	 * 区内编号  范围64~1023。
	 */
    private Integer numInZone;
    /**
	 * 下属用户
	 */
    private Integer usercount;
    /**
	 * 是否为指挥机  0：否  1：是
	 */
    private Integer iscurrent;
    /**
	 * 用户特征 
	 */
    private Integer userFeature;
    /**
	 * 通信长度 
	 */
    private Integer txlength;
    /**
	 * 位置
	 */
    private String position;
    
    private String zhjArea;

    private String zhjIndustry;
    
    private String areaName;
    
    private String industryName;
    
    private List<Area> areaList;
    
    private List<Industry> industryList;
    
	private String provinceName;

	private String cityName;

	private String zoneName;

	private String provinceId;

	private String cityId;

	private String zoneId;
    
    
    
    

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

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
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

    public Integer getTxlevel() {
        return txlevel;
    }

    public void setTxlevel(Integer txlevel) {
        this.txlevel = txlevel;
    }

    public String getBrocastAdd() {
        return brocastAdd;
    }

    public void setBrocastAdd(String brocastAdd) {
        this.brocastAdd = brocastAdd == null ? null : brocastAdd.trim();
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

    public Integer getEncryptflag() {
        return encryptflag;
    }

    public void setEncryptflag(Integer encryptflag) {
        this.encryptflag = encryptflag;
    }

    public Integer getNumInZone() {
        return numInZone;
    }

    public void setNumInZone(Integer numInZone) {
        this.numInZone = numInZone;
    }

    public Integer getUsercount() {
        return usercount;
    }

    public void setUsercount(Integer usercount) {
        this.usercount = usercount;
    }

    public Integer getIscurrent() {
        return iscurrent;
    }

    public void setIscurrent(Integer iscurrent) {
        this.iscurrent = iscurrent;
    }

    public Integer getUserFeature() {
        return userFeature;
    }

    public void setUserFeature(Integer userFeature) {
        this.userFeature = userFeature;
    }

    public Integer getTxlength() {
        return txlength;
    }

    public void setTxlength(Integer txlength) {
        this.txlength = txlength;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getZhjArea() {
        return zhjArea;
    }

    public void setZhjArea(String zhjArea) {
        this.zhjArea = zhjArea == null ? null : zhjArea.trim();
    }

    public String getZhjindustry() {
        return zhjIndustry;
    }

    public void setZhjindustry(String zhjindustry) {
        this.zhjIndustry = zhjindustry;
    }

	public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	public List<Industry> getIndustryList() {
		return industryList;
	}

	public void setIndustryList(List<Industry> industryList) {
		this.industryList = industryList;
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
    
	
    
}