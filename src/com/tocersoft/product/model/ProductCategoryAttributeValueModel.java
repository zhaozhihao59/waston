package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.entity.ProductCategoryAttributeValue;

import com.tocersoft.product.dto.ProductCategoryAttributeValueCondition;

public class ProductCategoryAttributeValueModel extends BaseModel<ProductCategoryAttributeValue>{

	private ProductCategoryAttributeValue item = new ProductCategoryAttributeValue();

	private ProductCategoryAttributeValueCondition condition = new ProductCategoryAttributeValueCondition();
	
	private String categoryId;
	
	private String attrId;
	

	public String getAttrId() {
		return attrId;
	}

	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public ProductCategoryAttributeValueModel() {
		super();
	}

	public ProductCategoryAttributeValue getItem() {
		return item;
	}

	public void setItem(ProductCategoryAttributeValue item) {
		this.item = item;
	}

	public ProductCategoryAttributeValueCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductCategoryAttributeValueCondition condition) {
		this.condition = condition;
	}

}
