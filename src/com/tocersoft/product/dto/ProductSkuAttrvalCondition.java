package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductSkuAttrvalCondition extends BaseCondition {

	/** 对应的SKUID */
	private String skuId;

	/** 对应的SKUID */
	public String getSkuId(){
		return this.skuId;
	}

	/** 对应的SKUID */
	public void setSkuId(String skuId){
		this.skuId = skuId;
	}
}
