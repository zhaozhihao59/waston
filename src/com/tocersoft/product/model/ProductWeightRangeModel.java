package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.entity.ProductWeightRange;

import com.tocersoft.product.dto.ProductWeightRangeCondition;

public class ProductWeightRangeModel extends BaseModel<ProductWeightRange>{

	private ProductWeightRange item = new ProductWeightRange();

	private ProductWeightRangeCondition condition = new ProductWeightRangeCondition();

	public ProductWeightRangeModel() {
		super();
	}

	public ProductWeightRange getItem() {
		return item;
	}

	public void setItem(ProductWeightRange item) {
		this.item = item;
	}

	public ProductWeightRangeCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductWeightRangeCondition condition) {
		this.condition = condition;
	}

}
