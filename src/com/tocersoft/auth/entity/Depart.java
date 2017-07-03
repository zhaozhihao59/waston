package com.tocersoft.auth.entity;
import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 部门表
 * 
 * @creator
 * @create-time 2014-04-29 10:41:34
 */
public class Depart extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 是否可删除标记：0-可删除，1-不可删除 */
	private Integer isDelete;
	/** 部门名称 */
	private String name;
	/** 父级部门ID */
	private String parentId;
	/** 节点级别：1-一级节点，2-二级节点，3-三级节点…… */
	private Integer level;
	/** 部门备注 */
	private String note;
	/** 部门负责员工ID */
	private String manageId;
	/** 排序参数：数字越小越靠前 */
	private Integer sort;
	/** 是否为父节点 */
	private int isParent;
	/** 是否可删除标记：0-可删除，1-不可删除 */
	/** 部门ID */
	private int departId;
	public Integer getIsDelete(){
		return this.isDelete;
	}

	/** 是否可删除标记：0-可删除，1-不可删除 */
	public void setIsDelete(Integer isDelete){
		this.isDelete = isDelete;
	}
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
	/** 节点级别：1-一级节点，2-二级节点，3-三级节点…… */
	public Integer getLevel(){
		return this.level;
	}

	/** 节点级别：1-一级节点，2-二级节点，3-三级节点…… */
	public void setLevel(Integer level){
		this.level = level;
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
	/** 排序参数：数字越小越靠前 */
	public Integer getSort(){
		return this.sort;
	}

	/** 排序参数：数字越小越靠前 */
	public void setSort(Integer sort){
		this.sort = sort;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	public int getDepartId() {
		return departId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
	}

	public void setIsParent(int isParent) {
		this.isParent = isParent;
	}
	
}