package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductCategoryAttributeValueCondition extends BaseCondition {
	
	/** 属性ID */
	private String catePubAttrvalId;
	/** 属性值英文 */
	private String lineAttrvalName;
	/** 属性值中文 */
	private String lineAttrvalNameCn;
	/** 属性值图片链接 */
	private String picUrl;
	
	/** 发布类目属性编号 */
	private String catePubAttrId;

	public String getCatePubAttrId() {
		return catePubAttrId;
	}

	public void setCatePubAttrId(String catePubAttrId) {
		this.catePubAttrId = catePubAttrId;
	}

	/** 属性值英文 */
	public String getLineAttrvalName(){
		return this.lineAttrvalName;
	}

	/** 属性值英文 */
	public void setLineAttrvalName(String lineAttrvalName){
		this.lineAttrvalName = lineAttrvalName;
	}
	/** 属性值中文 */
	public String getLineAttrvalNameCn(){
		return this.lineAttrvalNameCn;
	}

	/** 属性值中文 */
	public void setLineAttrvalNameCn(String lineAttrvalNameCn){
		this.lineAttrvalNameCn = lineAttrvalNameCn;
	}
	/** 属性值图片链接 */
	public String getPicUrl(){
		return this.picUrl;
	}

	/** 属性值图片链接 */
	public void setPicUrl(String picUrl){
		this.picUrl = picUrl;
	}

	public String getCatePubAttrvalId() {
		return catePubAttrvalId;
	}

	public void setCatePubAttrvalId(String catePubAttrvalId) {
		this.catePubAttrvalId = catePubAttrvalId;
	}
}
