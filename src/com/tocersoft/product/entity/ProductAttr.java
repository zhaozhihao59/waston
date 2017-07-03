package com.tocersoft.product.entity;
import java.util.List;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品属性
 * 
 * @creator
 * @create-time 2014-06-25 20:15:54
 */
public class ProductAttr extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;
	
	private String id;
	/** 产品ID */
	private String productId;
	/** 属性Id(自定义属性为000000) */
	private String attrId;
	/** 产品属性值列表 */
	private List<ProductAttrValue> prodAttrValueList;
	/** 产品属性名称 */
	private String proAttrName;

	public String getProAttrName() {
		return proAttrName;
	}

	public void setProAttrName(String proAttrName) {
		this.proAttrName = proAttrName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ProductAttrValue> getProdAttrValueList() {
		return prodAttrValueList;
	}

	public void setProdAttrValueList(List<ProductAttrValue> prodAttrValueList) {
		this.prodAttrValueList = prodAttrValueList;
	}

	/** 产品ID */
	public String getProductId(){
		return this.productId;
	}

	/** 产品ID */
	public void setProductId(String productId){
		this.productId = productId;
	}
	/** 属性Id(自定义属性为000000) */
	public String getAttrId(){
		return this.attrId;
	}

	/** 属性Id(自定义属性为000000) */
	public void setAttrId(String attrId){
		this.attrId = attrId;
	}
}