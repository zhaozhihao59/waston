package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.entity.ProductSaleSetting;

import com.tocersoft.product.dto.ProductSaleSettingCondition;

public class ProductSaleSettingModel extends BaseModel<ProductSaleSetting>{

	private ProductSaleSetting item = new ProductSaleSetting();

	private ProductSaleSettingCondition condition = new ProductSaleSettingCondition();

	public ProductSaleSettingModel() {
		super();
	}

	public ProductSaleSetting getItem() {
		return item;
	}

	public void setItem(ProductSaleSetting item) {
		this.item = item;
	}

	public ProductSaleSettingCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductSaleSettingCondition condition) {
		this.condition = condition;
	}

}
