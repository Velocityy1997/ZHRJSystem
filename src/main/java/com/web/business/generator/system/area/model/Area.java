package com.web.business.generator.system.area.model;

import com.web.common.util.model.AutoModel;

/**
 * 	区域实体类
 * @author GouYudong
 * @date 2019年7月3日
 *
 */
public class Area extends AutoModel{
	 	private String areaId;

	    private String areaName;

	    private Integer level;//0:省；1-市；2-区县

	    private String parentId;

	    private String remark;
	    
	    // 上级区域名称
	    private String parentName;
	    
	    private String provinceName;// 省名称
	    
	    private String provinceId; // 省id
	    
	    private String cityName; // 市名称
	    
	    private String cityId; // 市id
	    
	    //ssprivate
	    
	    
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

		public String getParentName() {
			return parentName;
		}

		public void setParentName(String parentName) {
			this.parentName = parentName;
		}

		public String getAreaId() {
	        return areaId;
	    }

	    public void setAreaId(String areaId) {
	        this.areaId = areaId == null ? null : areaId.trim();
	    }

	    public String getAreaName() {
	        return areaName;
	    }

	    public void setAreaName(String areaName) {
	        this.areaName = areaName == null ? null : areaName.trim();
	    }

	    public Integer getLevel() {
	        return level;
	    }

	    public void setLevel(Integer level) {
	        this.level = level;
	    }

	    

	    public String getParentId() {
			return parentId;
		}

		public void setParentId(String parentId) {
			this.parentId = parentId;
		}

		public String getRemark() {
	        return remark;
	    }

	    public void setRemark(String remark) {
	        this.remark = remark == null ? null : remark.trim();
	    }

	    
}