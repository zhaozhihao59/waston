package com.tocersoft.order.dto;

import com.tocersoft.base.dto.BaseCondition;

public class OrderSellItemCondition extends BaseCondition {

	/** 订单ID */
	private String orderId;
	/** 产品ID */
	private String productId;
	/** 产品SKU的ID */
	private String productSkuId;
	/** 产品名称 */
	private String productName;
	/** 产品图片 */
	private String productPhoto;

	/** 订单ID */
	public String getOrderId(){
		return this.orderId;
	}

	/** 订单ID */
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}
	/** 产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 产品SKU的ID */
	public String getProductSkuId(){
		return this.productSkuId;
	}

	/** 产品SKU的ID */
	public void setProductSkuId(String productSkuId){
		this.productSkuId = productSkuId;
	}
	/** 产品名称 */
	public String getProductName(){
		return this.productName;
	}

	/** 产品名称 */
	public void setProductName(String productName){
		this.productName = productName;
	}
	/** 产品图片 */
	public String getProductPhoto(){
		return this.productPhoto;
	}

	/** 产品图片 */
	public void setProductPhoto(String productPhoto){
		this.productPhoto = productPhoto;
	}
}
