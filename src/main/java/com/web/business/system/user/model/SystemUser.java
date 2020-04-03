package com.web.business.system.user.model;

import java.io.Serializable;

public class SystemUser implements Serializable{
	/**
	 * 用户管理实体类
	 * @author 
	 */
	
	private static final long serialVersionUID = -1749527906682694498L;
	
	private Long userId;//用户主键
	
	private String userName;//用户名
	
	private String password;//密码
	
	private String oldPassword;//旧密码
	
	private String realName;//真实姓名
	
	private Long departmentId;//部门主键
	
	private String departmentName;//部门名称
	
	private String userNumber;//员工编号
	
	private String phone;//电话
	
	private String email;//邮箱;
	
	private Integer delFlag;//逻辑删除标志（0：未删除 1：已删除）
	
	private String createTime;//创建时间
	
	private String roleName;//用户所属角色名称
	
	private String roleId;//用户所属角色id
	
	private Long corpId;//企业主键
	
	private Integer departmentLeader;//是否部门领导（0：不是 1：是）
	
	private Integer chargeLeader;//是否分管领导（0：不是 1：是）
	
	private Long createUserId;//创建者Id
	
	public SystemUser() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Long getCorpId() {
		return corpId;
	}

	public void setCorpId(Long corpId) {
		this.corpId = corpId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getDepartmentLeader() {
		return departmentLeader;
	}

	public void setDepartmentLeader(Integer departmentLeader) {
		this.departmentLeader = departmentLeader;
	}

	public Integer getChargeLeader() {
		return chargeLeader;
	}

	public void setChargeLeader(Integer chargeLeader) {
		this.chargeLeader = chargeLeader;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

}
