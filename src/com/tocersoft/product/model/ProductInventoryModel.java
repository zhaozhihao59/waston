package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.entity.ProductInventory;

import com.tocersoft.product.dto.ProductInventoryCondition;

public class ProductInventoryModel extends BaseModel<ProductInventory>{

	private ProductInventory item = new ProductInventory();

	private ProductInventoryCondition condition = new ProductInventoryCondition();

	public ProductInventoryModel() {
		super();
	}

	public ProductInventory getItem() {
		return item;
	}

	public void setItem(ProductInventory item) {
		this.item = item;
	}

	public ProductInventoryCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductInventoryCondition condition) {
		this.condition = condition;
	}

}
