package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.entity.ProductSkuAttrval;

import com.tocersoft.product.dto.ProductSkuAttrvalCondition;

public class ProductSkuAttrvalModel extends BaseModel<ProductSkuAttrval>{

	private ProductSkuAttrval item = new ProductSkuAttrval();

	private String productId;
	
	private ProductSkuAttrvalCondition condition = new ProductSkuAttrvalCondition();

	public ProductSkuAttrvalModel() {
		super();
	}

	public ProductSkuAttrval getItem() {
		return item;
	}

	public void setItem(ProductSkuAttrval item) {
		this.item = item;
	}

	public ProductSkuAttrvalCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductSkuAttrvalCondition condition) {
		this.condition = condition;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
