package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductSaleSettingCondition extends BaseCondition {

	/** 对应的产品ID */
	private String productId;

	/** 对应的产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 对应的产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
}
