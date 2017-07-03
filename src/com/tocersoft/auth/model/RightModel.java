package com.tocersoft.auth.model;
import com.tocersoft.auth.entity.Right;
import com.tocersoft.base.model.BaseModel;

public class RightModel extends BaseModel<Right>{
	
	private Right item = new Right();
	
	private static final long serialVersionUID = 1L;
	
	private String roleId;
	
	/** 权限父级ID */
	private String pid;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Right getItem() {
		return item;
	}

	public void setItem(Right item) {
		this.item = item;
	}
	
}
