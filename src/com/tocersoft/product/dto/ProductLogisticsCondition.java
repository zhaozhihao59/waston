package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductLogisticsCondition extends BaseCondition {

	/** 对应的产品ID */
	private String productId;
	/** 运费模式：0-买家承担运费，1-卖家承担运费 */
	private String freightType;
	/** 运费模板ID */
	private String templateId;

	/** 对应的产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 对应的产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 运费模式：0-买家承担运费，1-卖家承担运费 */
	public String getFreightType(){
		return this.freightType;
	}

	/** 运费模式：0-买家承担运费，1-卖家承担运费 */
	public void setFreightType(String freightType){
		this.freightType = freightType;
	}
	/** 运费模板ID */
	public String getTemplateId(){
		return this.templateId;
	}

	/** 运费模板ID */
	public void setTemplateId(String templateId){
		this.templateId = templateId;
	}
}
