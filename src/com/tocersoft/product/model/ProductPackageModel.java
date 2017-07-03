package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.entity.ProductPackage;

import com.tocersoft.product.dto.ProductPackageCondition;

public class ProductPackageModel extends BaseModel<ProductPackage>{

	private ProductPackage item = new ProductPackage();

	private ProductPackageCondition condition = new ProductPackageCondition();

	public ProductPackageModel() {
		super();
	}

	public ProductPackage getItem() {
		return item;
	}

	public void setItem(ProductPackage item) {
		this.item = item;
	}

	public ProductPackageCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductPackageCondition condition) {
		this.condition = condition;
	}

}
