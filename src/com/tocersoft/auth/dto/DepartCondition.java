package com.tocersoft.auth.dto;

import com.tocersoft.base.dto.BaseCondition;

public class DepartCondition extends BaseCondition {

	/** 部门名称 */
	private String name;
	/** 父级部门ID */
	private String parentId;
	/** 部门备注 */
	private String note;
	/** 部门负责员工ID */
	private String manageId;
	/** 部门ID */
	private String departId;
	
	/** 部门名称 */
	public String getName(){
		return this.name;
	}

	/** 部门名称 */
	public void setName(String name){
		this.name = name;
	}
	/** 父级部门ID */
	public String getParentId(){
		return this.parentId;
	}

	/** 父级部门ID */
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	/** 部门备注 */
	public String getNote(){
		return this.note;
	}

	/** 部门备注 */
	public void setNote(String note){
		this.note = note;
	}
	/** 部门负责员工ID */
	public String getManageId(){
		return this.manageId;
	}

	/** 部门负责员工ID */
	public void setManageId(String manageId){
		this.manageId = manageId;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}
	
}
