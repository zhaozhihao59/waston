package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.entity.ProductAttr;
import com.tocersoft.product.entity.ProductAttrValue;
import com.tocersoft.product.entity.ProductCategoryAttribute;
import com.tocersoft.product.entity.ProductCategoryAttributeValue;

import com.tocersoft.product.dto.ProductAttrValueCondition;

public class ProductAttrValueModel extends BaseModel<ProductAttrValue>{

	private ProductAttrValue item = new ProductAttrValue();

	private ProductAttrValueCondition condition = new ProductAttrValueCondition();
	
	//产品类别Id
	private String categoryId;
	
	/** 产品Id */
	private String productId;
	
	/** 属性Id */
	private String attrId;
	
	/** 属性值Id */
	private String attrValueId;
	
	private ProductAttr produnctAttr;
	
	private ProductCategoryAttribute productCategoryAttribute;
	
	private ProductCategoryAttributeValue productCategoryAttributeValue;
	
	private String lineAttrNameCn;
	
	
	public String getLineAttrNameCn() {
		return lineAttrNameCn;
	}

	public void setLineAttrNameCn(String lineAttrNameCn) {
		this.lineAttrNameCn = lineAttrNameCn;
	}

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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	//批量删除
	private String selIds;
	

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getSelIds() {
		return selIds;
	}

	public void setSelIds(String selIds) {
		this.selIds = selIds;
	}

	public ProductAttrValueModel() {
		super();
	}
	
	public String getAttrId() {
		return attrId;
	}

	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}

	public ProductAttrValue getItem() {
		return item;
	}

	public void setItem(ProductAttrValue item) {
		this.item = item;
	}

	public ProductAttrValueCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductAttrValueCondition condition) {
		this.condition = condition;
	}

	public String getAttrValueId() {
		return attrValueId;
	}

	public void setAttrValueId(String attrValueId) {
		this.attrValueId = attrValueId;
	}

}
