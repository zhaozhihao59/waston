package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.entity.ProductAttr;

import com.tocersoft.product.dto.ProductAttrCondition;

public class ProductAttrModel extends BaseModel<ProductAttr>{

	private ProductAttr item = new ProductAttr();

	private ProductAttrCondition condition = new ProductAttrCondition();

	public ProductAttrModel() {
		super();
	}

	public ProductAttr getItem() {
		return item;
	}

	public void setItem(ProductAttr item) {
		this.item = item;
	}

	public ProductAttrCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductAttrCondition condition) {
		this.condition = condition;
	}

}
