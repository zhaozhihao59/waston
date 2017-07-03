package com.tocersoft.auth.entity;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 角色实体
 * 
 * @creator zhangqiang
 * @create-time Nov 7, 2011 4:04:12 PM
 */
public class Role extends BaseBusEntity {
	private static final long serialVersionUID = 2790199600385605443L;

	/** 角色名称 */
	private String name;
	
	/** 角色类型 1:系统 2:用户 */
	
	private Integer roleType;

	/** 角色说明 */
	private String remark;

	/** 关联的菜单 */
	private List<Right> permissions = new ArrayList<Right>();

	/** 关联的用户 */
	private List<User> users = new ArrayList<User>();

	public Role() {
		super();
	}

	public Role(String id,String name, Integer roleType) {
		super();
		this.setId(id);
		this.name = name;
		this.roleType = roleType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getRoleType() {
		return roleType;
	}
	
	public String getRoleTypeString() {
		String str = "未知";
		if(null != roleType){
			if(roleType == 1){
				str = "系统";
			}else if(roleType == 2){
				str = "用户";
			}
		}
		
		return str;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Right> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Right> permissions) {
		this.permissions = permissions;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
