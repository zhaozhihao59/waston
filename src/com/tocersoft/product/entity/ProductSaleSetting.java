package com.tocersoft.product.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品销售属性设置信息
 * 
 * @creator
 * @create-time 2014-06-25 20:15:54
 */
public class ProductSaleSetting extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 对应的产品ID */
	private String productId;
	/** 备货期 */
	private Long leadingTime;
	/** 买家一次最大购买量 */
	private Long maxSaleQty;
	/** 设置价格类型，1 分别设置价格，2 统一设置价格 */
	private Long priceConfigType;

	/** 对应的产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 对应的产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 备货期 */
	public Long getLeadingTime(){
		return this.leadingTime;
	}

	/** 备货期 */
	public void setLeadingTime(Long leadingTime){
		this.leadingTime = leadingTime;
	}
	/** 买家一次最大购买量 */
	public Long getMaxSaleQty(){
		return this.maxSaleQty;
	}

	/** 买家一次最大购买量 */
	public void setMaxSaleQty(Long maxSaleQty){
		this.maxSaleQty = maxSaleQty;
	}
	/** 设置价格类型，1 分别设置价格，2 统一设置价格 */
	public Long getPriceConfigType(){
		return this.priceConfigType;
	}

	/** 设置价格类型，1 分别设置价格，2 统一设置价格 */
	public void setPriceConfigType(Long priceConfigType){
		this.priceConfigType = priceConfigType;
	}
}