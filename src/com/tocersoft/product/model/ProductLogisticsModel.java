package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.entity.ProductLogistics;

import com.tocersoft.product.dto.ProductLogisticsCondition;

public class ProductLogisticsModel extends BaseModel<ProductLogistics>{

	private ProductLogistics item = new ProductLogistics();

	private ProductLogisticsCondition condition = new ProductLogisticsCondition();

	public ProductLogisticsModel() {
		super();
	}

	public ProductLogistics getItem() {
		return item;
	}

	public void setItem(ProductLogistics item) {
		this.item = item;
	}

	public ProductLogisticsCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductLogisticsCondition condition) {
		this.condition = condition;
	}

}
