package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.entity.ProductWholesaleRange;

import com.tocersoft.product.dto.ProductWholesaleRangeCondition;

public class ProductWholesaleRangeModel extends BaseModel<ProductWholesaleRange>{

	private ProductWholesaleRange item = new ProductWholesaleRange();

	private ProductWholesaleRangeCondition condition = new ProductWholesaleRangeCondition();

	public ProductWholesaleRangeModel() {
		super();
	}

	public ProductWholesaleRange getItem() {
		return item;
	}

	public void setItem(ProductWholesaleRange item) {
		this.item = item;
	}

	public ProductWholesaleRangeCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductWholesaleRangeCondition condition) {
		this.condition = condition;
	}

}
