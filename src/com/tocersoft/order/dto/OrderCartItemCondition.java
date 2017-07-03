package com.tocersoft.order.dto;

import com.tocersoft.base.dto.BaseCondition;

public class OrderCartItemCondition extends BaseCondition {

	/** 会员ID */
	private String memberId;
	/** 产品ID */
	private String productId;
	/** 产品SKU的ID */
	private String productSkuId;
	/** 备注 */
	private String remark;

	/** 会员ID */
	public String getMemberId(){
		return this.memberId;
	}

	/** 会员ID */
	public void setMemberId(String memberId){
		this.memberId = memberId;
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
	/** 备注 */
	public String getRemark(){
		return this.remark;
	}

	/** 备注 */
	public void setRemark(String remark){
		this.remark = remark;
	}
}
