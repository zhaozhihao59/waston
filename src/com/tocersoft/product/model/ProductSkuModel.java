package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.entity.ProductSku;

import com.tocersoft.product.dto.ProductSkuCondition;

public class ProductSkuModel extends BaseModel<ProductSku>{

	private ProductSku item = new ProductSku();

	//sku列表字符串 
	private String skuData;
	
	private String productId;
	
	private ProductSkuCondition condition = new ProductSkuCondition();

	public ProductSkuModel() {
		super();
	}

	public ProductSku getItem() {
		return item;
	}

	public void setItem(ProductSku item) {
		this.item = item;
	}

	public ProductSkuCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductSkuCondition condition) {
		this.condition = condition;
	}

	public String getSkuData() {
		return skuData;
	}

	public void setSkuData(String skuData) {
		this.skuData = skuData;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
