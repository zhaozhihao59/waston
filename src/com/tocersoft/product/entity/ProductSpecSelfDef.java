package com.tocersoft.product.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品自定义规格信息
 * 
 * @creator
 * @create-time 2014-06-25 20:15:54
 */
public class ProductSpecSelfDef extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 对应的产品ID */
	private String productId;
	/** 自定义规格ID */
	private String attrValId;
	/** 自定义规格名称(不允许重复) */
	private String attrValName;
	/** 自定义规格图片 */
	private String picUrl;

	/** 对应的产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 对应的产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 自定义规格ID */
	public String getAttrValId(){
		return this.attrValId;
	}

	/** 自定义规格ID */
	public void setAttrValId(String attrValId){
		this.attrValId = attrValId;
	}
	/** 自定义规格名称(不允许重复) */
	public String getAttrValName(){
		return this.attrValName;
	}

	/** 自定义规格名称(不允许重复) */
	public void setAttrValName(String attrValName){
		this.attrValName = attrValName;
	}
	/** 自定义规格图片 */
	public String getPicUrl(){
		return this.picUrl;
	}

	/** 自定义规格图片 */
	public void setPicUrl(String picUrl){
		this.picUrl = picUrl;
	}
}