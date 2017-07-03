package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductWeightRangeCondition extends BaseCondition {

	/** 对应的产品ID */
	private String productId;
	/** 是否只以重量计算运费 */
	private String isOnlyWeight;

	/** 对应的产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 对应的产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 是否只以重量计算运费 */
	public String getIsOnlyWeight(){
		return this.isOnlyWeight;
	}

	/** 是否只以重量计算运费 */
	public void setIsOnlyWeight(String isOnlyWeight){
		this.isOnlyWeight = isOnlyWeight;
	}
}
