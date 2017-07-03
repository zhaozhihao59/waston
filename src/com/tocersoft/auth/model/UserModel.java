package com.tocersoft.auth.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.auth.dto.UserCondition;
import com.tocersoft.auth.dto.UserVo;
import com.tocersoft.auth.entity.Role;
import com.tocersoft.auth.entity.User;
import com.tocersoft.base.model.BaseModel;

public class UserModel extends BaseModel<UserVo>{
	
	private static final long serialVersionUID = -3579744296285032578L;
	private User item = new User();
	private UserCondition condition = new UserCondition();
	private List<Role> roleList = new ArrayList<Role>();
	private List<UserVo> voList = new ArrayList<UserVo>();
	private String roleIds;
	private String roleJSON;
	private String departIds;
	private String departJSON;
	
	private String selIds;
	private String managerId;
	//父节点ID
	private String parentId;
	
	private String path;

	@Override
	public String getSelIds() {
		return selIds;
	}

	@Override
	public void setSelIds(String selIds) {
		this.selIds = selIds;
	}

	public UserModel() {
		super();
	}

	public User getItem() {
		return item;
	}

	public void setItem(User item) {
		this.item = item;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public UserCondition getCondition() {
		return condition;
	}

	public void setCondition(UserCondition condition) {
		this.condition = condition;
	}

	public List<UserVo> getVoList() {
		return voList;
	}

	public void setVoList(List<UserVo> voList) {
		this.voList = voList;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleJSON() {
		return roleJSON;
	}

	public void setRoleJSON(String roleJSON) {
		this.roleJSON = roleJSON;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDepartIds() {
		return departIds;
	}

	public void setDepartIds(String departIds) {
		this.departIds = departIds;
	}

	public String getDepartJSON() {
		return departJSON;
	}

	public void setDepartJSON(String departJSON) {
		this.departJSON = departJSON;
	}

}
