package com.tocersoft.product.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品批发折扣区间
 * 
 * @creator
 * @create-time 2014-06-25 20:15:54
 */
public class ProductWholesaleRange extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 对应的产品ID */
	private String productId;
	/** 折扣率 */
	private Double discount;
	/** 起批数量 */
	private Long startQty;

	/** 对应的产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 对应的产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 折扣率 */
	public Double getDiscount(){
		return this.discount;
	}

	/** 折扣率 */
	public void setDiscount(Double discount){
		this.discount = discount;
	}
	/** 起批数量 */
	public Long getStartQty(){
		return this.startQty;
	}

	/** 起批数量 */
	public void setStartQty(Long startQty){
		this.startQty = startQty;
	}
}