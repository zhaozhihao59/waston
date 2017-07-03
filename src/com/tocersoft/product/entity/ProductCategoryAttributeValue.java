package com.tocersoft.product.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品类别属性
 * 
 * @creator
 * @create-time 2014-06-25 20:15:54
 */
public class ProductCategoryAttributeValue extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	private String id;
	/** 产品线属性值编号 */
	private Integer attrValId;
	/** 发布类目属性编号 */
	private String catePubAttrId;
	/** 属性ID */
	private String catePubAttrvalId;
	/** 属性值英文 */
	private String lineAttrvalName;
	/** 属性值中文 */
	private String lineAttrvalNameCn;
	/** 属性值图片链接 */
	private String picUrl;
	/** 排序值 */
	private Integer sortval;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/** 产品线属性值编号 */
	public Integer getAttrValId(){
		return this.attrValId;
	}

	/** 产品线属性值编号 */
	public void setAttrValId(Integer attrValId){
		this.attrValId = attrValId;
	}
	/** 发布类目属性编号 */
	public String getCatePubAttrId(){
		return this.catePubAttrId;
	}

	/** 发布类目属性编号 */
	public void setCatePubAttrId(String catePubAttrId){
		this.catePubAttrId = catePubAttrId;
	}
	/** 属性ID */
	public String getCatePubAttrvalId(){
		return this.catePubAttrvalId;
	}

	/** 属性ID */
	public void setCatePubAttrvalId(String catePubAttrvalId){
		this.catePubAttrvalId = catePubAttrvalId;
	}
	/** 属性值英文 */
	public String getLineAttrvalName(){
		return this.lineAttrvalName;
	}

	/** 属性值英文 */
	public void setLineAttrvalName(String lineAttrvalName){
		this.lineAttrvalName = lineAttrvalName;
	}
	/** 属性值中文 */
	public String getLineAttrvalNameCn(){
		return this.lineAttrvalNameCn;
	}

	/** 属性值中文 */
	public void setLineAttrvalNameCn(String lineAttrvalNameCn){
		this.lineAttrvalNameCn = lineAttrvalNameCn;
	}
	/** 属性值图片链接 */
	public String getPicUrl(){
		return this.picUrl;
	}

	/** 属性值图片链接 */
	public void setPicUrl(String picUrl){
		this.picUrl = picUrl;
	}
	/** 排序值 */
	public Integer getSortval(){
		return this.sortval;
	}

	/** 排序值 */
	public void setSortval(Integer sortval){
		this.sortval = sortval;
	}
}