package com.tocersoft.auth.dto;

import java.io.Serializable;

public class RoleVo implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 角色类型
	 */
	private Integer roleType;

	/**
	 * 角色类型字符串
	 */
	private String roleTypeStr;

	/**
	 * 授权用户
	 */
	private String users;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public String getRoleTypeStr() {
		return roleTypeStr;
	}

	public void setRoleTypeStr(String roleTypeStr) {
		this.roleTypeStr = roleTypeStr;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}
}
