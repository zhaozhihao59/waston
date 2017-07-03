package com.tocersoft.auth.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tocersoft.auth.dto.RoleVo;
import com.tocersoft.auth.entity.Right;
import com.tocersoft.auth.entity.Role;
import com.tocersoft.base.model.BaseModel;


public class RoleModel extends BaseModel<RoleVo>{

	private static final long serialVersionUID = 1L;
	
	private Role item = new Role();
	
	private List<RoleVo> roleVOList = new ArrayList<RoleVo>();
	
	private Map<Right, List<Map<Right, List<Map<Right, List<Right>>>>>> permissionMap = null;
	
	private String permissionIds;
	
	private String roleId;
	
	/** 权限集合 */
	private List<Right> rights = new ArrayList<Right>();

	public List<Right> getRights() {
		return rights;
	}

	public void setRights(List<Right> rights) {
		this.rights = rights;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Role getItem() {
		return item;
	}

	public void setItem(Role item) {
		this.item = item;
	}

	public List<RoleVo> getRoleVOList() {
		return roleVOList;
	}

	public void setRoleVOList(List<RoleVo> roleVOList) {
		this.roleVOList = roleVOList;
	}

	public Map<Right, List<Map<Right, List<Map<Right, List<Right>>>>>> getPermissionMap() {
		return permissionMap;
	}

	public void setPermissionMap(
			Map<Right, List<Map<Right, List<Map<Right, List<Right>>>>>> permissionMap) {
		this.permissionMap = permissionMap;
	}

	public String getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(String permissionIds) {
		this.permissionIds = permissionIds;
	}
}
