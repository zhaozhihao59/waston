package com.tocersoft.product.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ProductPackageCondition extends BaseCondition {

	/** 对应的产品ID */
	private String productId;
	/** 单位ID */
	private String measureId;
	/** 产品阶梯重量 */
	private String prodWeightRange;

	/** 对应的产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 对应的产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 单位ID */
	public String getMeasureId(){
		return this.measureId;
	}

	/** 单位ID */
	public void setMeasureId(String measureId){
		this.measureId = measureId;
	}
	/** 产品阶梯重量 */
	public String getProdWeightRange(){
		return this.prodWeightRange;
	}

	/** 产品阶梯重量 */
	public void setProdWeightRange(String prodWeightRange){
		this.prodWeightRange = prodWeightRange;
	}
}
