package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.entity.ProductSpecSelfDef;

import com.tocersoft.product.dto.ProductSpecSelfDefCondition;

public class ProductSpecSelfDefModel extends BaseModel<ProductSpecSelfDef>{

	private ProductSpecSelfDef item = new ProductSpecSelfDef();

	private ProductSpecSelfDefCondition condition = new ProductSpecSelfDefCondition();

	public ProductSpecSelfDefModel() {
		super();
	}

	public ProductSpecSelfDef getItem() {
		return item;
	}

	public void setItem(ProductSpecSelfDef item) {
		this.item = item;
	}

	public ProductSpecSelfDefCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductSpecSelfDefCondition condition) {
		this.condition = condition;
	}

}
