package com.tocersoft.product.dto;

import java.io.Serializable;

/**
 * 下载产品VO对象
 * @author hello
 *
 */
public class ProductDownloadVO implements Serializable{
	
	private static final long serialVersionUID = 5906920297566641457L;
	// ------------临时字段
	private String attr;	//属性
	private String attrVal;	//属性值
	public ProductDownloadVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public String getAttrVal() {
		return attrVal;
	}
	public void setAttrVal(String attrVal) {
		this.attrVal = attrVal;
	}
}
