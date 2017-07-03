package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductCategoryCondition extends BaseCondition {

	/** 类型Id */
	private String id;
	/** 类型Id */
	public String getId() {
		return id;
	}
	/** 类型Id */
	public void setId(String id) {
		this.id = id;
	}
	/** 类别名称 */
	private String name;
	/** 类别名称(英文) */
	private String nameEn;
	/** 提示文字 */
	private String tip;
	/** 类别父节点ID，必须先存在才能添加 */
	private String parentId;
	/** 类别图片URL */
	private String imgPath;
	/** 类别备注 */
	private String remark;
	/** 类别备注(英文) */
	private String remarkEn;
	
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getRemarkEn() {
		return remarkEn;
	}
	public void setRemarkEn(String remarkEn) {
		this.remarkEn = remarkEn;
	}
	/** 类别名称 */
	public String getName(){
		return this.name;
	}

	/** 类别名称 */
	public void setName(String name){
		this.name = name;
	}
	/** 提示文字 */
	public String getTip(){
		return this.tip;
	}

	/** 提示文字 */
	public void setTip(String tip){
		this.tip = tip;
	}
	/** 类别父节点ID，必须先存在才能添加 */
	public String getParentId(){
		return this.parentId;
	}

	/** 类别父节点ID，必须先存在才能添加 */
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	/** 类别图片URL */
	public String getImgPath(){
		return this.imgPath;
	}

	/** 类别图片URL */
	public void setImgPath(String imgPath){
		this.imgPath = imgPath;
	}
	/** 类别备注 */
	public String getRemark(){
		return this.remark;
	}

	/** 类别备注 */
	public void setRemark(String remark){
		this.remark = remark;
	}
}
