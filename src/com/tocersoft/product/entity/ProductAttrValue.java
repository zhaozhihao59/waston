package com.tocersoft.product.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品属性值
 * 
 * @creator
 * @create-time 2014-06-25 20:15:54
 */
public class ProductAttrValue extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 产品属性表ID */
	private String productAttrId;
	/** 属性ID */
	private String attrId;
	/** 产品属性名称 */
	private String attrName;
	/** 产品属性值id */
	private String attrValId;
	/** 属性值名称(自定义属性时,两个属性值必填) */
	private String lineAttrvalName;
	/** 属性值中文名称 */
	private String lineAttrvalNameCn;
	/** 产品属性图片路径 */
	private String picUrl;
	/** 主键Id */
	private String id;
	
	
	private ProductAttr produnctAttr;
	
	private ProductCategoryAttribute productCategoryAttribute;
	
	private ProductCategoryAttributeValue productCategoryAttributeValue;
	
	
	public ProductAttr getProdunctAttr() {
		return produnctAttr;
	}
	public void setProdunctAttr(ProductAttr produnctAttr) {
		this.produnctAttr = produnctAttr;
	}
	public ProductCategoryAttribute getProductCategoryAttribute() {
		return productCategoryAttribute;
	}
	public void setProductCategoryAttribute(
			ProductCategoryAttribute productCategoryAttribute) {
		this.productCategoryAttribute = productCategoryAttribute;
	}
	public ProductCategoryAttributeValue getProductCategoryAttributeValue() {
		return productCategoryAttributeValue;
	}
	public void setProductCategoryAttributeValue(
			ProductCategoryAttributeValue productCategoryAttributeValue) {
		this.productCategoryAttributeValue = productCategoryAttributeValue;
	}
	/** 主键Id */
	public String getId() {
		return id;
	}
	/** 主键Id */
	public void setId(String id) {
		this.id = id;
	}
	
	/** 属性ID */
	public String getAttrId(){
		return this.attrId;
	}

	/** 属性ID */
	public void setAttrId(String attrId){
		this.attrId = attrId;
	}
	/** 产品属性名称 */
	public String getAttrName(){
		return this.attrName;
	}

	/** 产品属性名称 */
	public void setAttrName(String attrName){
		this.attrName = attrName;
	}
	/** 产品属性值id */
	public String getAttrValId(){
		return this.attrValId;
	}

	/** 产品属性值id */
	public void setAttrValId(String attrValId){
		this.attrValId = attrValId;
	}
	/** 属性值名称(自定义属性时,两个属性值必填) */
	public String getLineAttrvalName(){
		return this.lineAttrvalName;
	}

	/** 属性值名称(自定义属性时,两个属性值必填) */
	public void setLineAttrvalName(String lineAttrvalName){
		this.lineAttrvalName = lineAttrvalName;
	}
	/** 属性值中文名称 */
	public String getLineAttrvalNameCn(){
		return this.lineAttrvalNameCn;
	}

	/** 属性值中文名称 */
	public void setLineAttrvalNameCn(String lineAttrvalNameCn){
		this.lineAttrvalNameCn = lineAttrvalNameCn;
	}
	/** 产品属性图片路径 */
	public String getPicUrl(){
		return this.picUrl;
	}

	/** 产品属性图片路径 */
	public void setPicUrl(String picUrl){
		this.picUrl = picUrl;
	}

	public String getProductAttrId() {
		return productAttrId;
	}

	public void setProductAttrId(String productAttrId) {
		this.productAttrId = productAttrId;
	}
}