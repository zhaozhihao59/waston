package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;
//import com.tocersoft.product.entity.ProductAttr;
//import com.tocersoft.product.entity.ProductCategoryAttribute;
//import com.tocersoft.product.entity.ProductCategoryAttributeValue;

public class ProductAttrValueCondition extends BaseCondition {
	/** 产品类别Id */
	private String categoryId;
	/** 产品属性名称 */
	private String attrName;
	/** 属性值名称(自定义属性时,两个属性值必填) */
	private String lineAttrvalName;
	/** 属性值中文名称 */
	private String lineAttrvalNameCn;
	/** 产品属性图片路径 */
	private String picUrl;
	/** 产品Id */
	private String productId;
	
	
//	private ProductAttr produnctAttr;
//	
//	private ProductCategoryAttribute productCategoryAttribute;
//	
//	private ProductCategoryAttributeValue productCategoryAttributeValue;
	
	
//	public ProductAttr getProdunctAttr() {
//		return produnctAttr;
//	}
//
//	public void setProdunctAttr(ProductAttr produnctAttr) {
//		this.produnctAttr = produnctAttr;
//	}
//
//	public ProductCategoryAttribute getProductCategoryAttribute() {
//		return productCategoryAttribute;
//	}
//
//	public void setProductCategoryAttribute(
//			ProductCategoryAttribute productCategoryAttribute) {
//		this.productCategoryAttribute = productCategoryAttribute;
//	}
//
//	public ProductCategoryAttributeValue getProductCategoryAttributeValue() {
//		return productCategoryAttributeValue;
//	}
//
//	public void setProductCategoryAttributeValue(
//			ProductCategoryAttributeValue productCategoryAttributeValue) {
//		this.productCategoryAttributeValue = productCategoryAttributeValue;
//	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/** 产品属性名称 */
	public String getAttrName(){
		return this.attrName;
	}

	/** 产品属性名称 */
	public void setAttrName(String attrName){
		this.attrName = attrName;
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
}
