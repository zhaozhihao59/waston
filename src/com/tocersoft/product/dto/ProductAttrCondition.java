package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductAttrCondition extends BaseCondition {

	/** 产品ID */
	private String productId;
	
	/** 类别属性ID */
	private String attrId;

	/** 产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getAttrId() {
		return attrId;
	}

	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}
	
	
}
