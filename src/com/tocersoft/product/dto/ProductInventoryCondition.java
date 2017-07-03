package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductInventoryCondition extends BaseCondition {

	/** 对应的产品ID */
	private String productId;
	/** 备货地 */
	private String inventoryLocation;

	/** 对应的产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 对应的产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 备货地 */
	public String getInventoryLocation(){
		return this.inventoryLocation;
	}

	/** 备货地 */
	public void setInventoryLocation(String inventoryLocation){
		this.inventoryLocation = inventoryLocation;
	}
}
