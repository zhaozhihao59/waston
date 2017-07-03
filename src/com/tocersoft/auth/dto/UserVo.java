package com.tocersoft.auth.dto;

import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

public class UserVo extends BaseBusEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4966368036172390628L;
	/** 员工工号 */
	private String staffId;
	/** 真实姓名 */
	private String name;
	/** 性别 1:男，2女 */
	private Integer sex;
	/** 生日 */
	private Date birthday;
	/** 手机 */
	private String mobile;
	/** 邮箱 */
	private String email;
	/** 是否启用 1:启用 2：禁用 */
	private Integer state;
	/** 账号 */
	private String account;
	/** 角色 */
	private String roleNames;
	/** 部门ID */
	private String departId;
	/** 是否主管 */
	private Integer isManager;
	
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public Integer getIsManager() {
		return isManager;
	}

	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}
	
	
}
