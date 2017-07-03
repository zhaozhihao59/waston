package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductCommentCondition extends BaseCondition {
	/** 产品ID */
	private String productId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
