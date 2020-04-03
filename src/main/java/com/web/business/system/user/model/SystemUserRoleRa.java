package com.web.business.system.user.model;

import java.io.Serializable;

public class SystemUserRoleRa implements Serializable{
	/**
	 * 用户--角色关联表实体类
	 * @author  
	 */

	private static final long serialVersionUID = 8499693235664674715L;
	
	private Long userId;//用户主键
	
	private Long roleId;//角色主键
	
	public SystemUserRoleRa() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
