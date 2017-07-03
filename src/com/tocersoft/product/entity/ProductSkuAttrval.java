package com.tocersoft.product.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品SKU属性值
 * 
 * @creator
 * @create-time 2014-06-25 20:15:54
 */
public class ProductSkuAttrval extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 对应的SKUID */
	private String skuId;
	/** 属性id */
	private String attrId;
	/** 属性值id */
	private String attrValId;
	/** 规格类型：1 销售规格，2，规格，3自定义规格 */
	private Long sizeSpecType;

	private String attrValIds ;
	
	/** 对应的SKUID */
	public String getSkuId(){
		return this.skuId;
	}

	/** 对应的SKUID */
	public void setSkuId(String skuId){
		this.skuId = skuId;
	}
	/** 属性id */
	public String getAttrId(){
		return this.attrId;
	}

	/** 属性id */
	public void setAttrId(String attrId){
		this.attrId = attrId;
	}
	/** 属性值id */
	public String getAttrValId(){
		return this.attrValId;
	}

	/** 属性值id */
	public void setAttrValId(String attrValId){
		this.attrValId = attrValId;
	}
	/** 规格类型：1 销售规格，2，规格，3自定义规格 */
	public Long getSizeSpecType(){
		return this.sizeSpecType;
	}

	/** 规格类型：1 销售规格，2，规格，3自定义规格 */
	public void setSizeSpecType(Long sizeSpecType){
		this.sizeSpecType = sizeSpecType;
	}

	public String getAttrValIds() {
		return attrValIds;
	}

	public void setAttrValIds(String attrValIds) {
		this.attrValIds = attrValIds;
	}
}