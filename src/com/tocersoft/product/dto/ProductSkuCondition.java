package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductSkuCondition extends BaseCondition {

	/** 对应的产品ID */
	private String productId;
	/** 产品SKU属性值列表 */
	private String prodSkuAttrvalList;
	/** sku编码 */
	private String skuCode;

	/** 对应的产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 对应的产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 产品SKU属性值列表 */
	public String getProdSkuAttrvalList(){
		return this.prodSkuAttrvalList;
	}

	/** 产品SKU属性值列表 */
	public void setProdSkuAttrvalList(String prodSkuAttrvalList){
		this.prodSkuAttrvalList = prodSkuAttrvalList;
	}
	/** sku编码 */
	public String getSkuCode(){
		return this.skuCode;
	}

	/** sku编码 */
	public void setSkuCode(String skuCode){
		this.skuCode = skuCode;
	}
}
