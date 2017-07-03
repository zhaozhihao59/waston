package com.tocersoft.product.entity;
import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 产品类别信息
 * 
 * @creator
 * @create-time 2014-06-25 20:15:54
 */
public class ProductCategory extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 类别名称 */
	private String name;
	/** 类别名称(英文) */
	private String nameEn;
	/** 提示文字 */
	private String tip;
	/** 类别父节点ID，必须先存在才能添加 */
	private String parentId;
	/** 0-叶节点，1-根节点 */
	private Integer isParent;
	/** 类别图片URL */
	private String imgPath;
	/** 类别备注 */
	private String remark;
	/** 类别备注(英文) */
	private String remarkEn;
	
	/** 排序参数：数字越小越靠前 */
	private Integer sort;
	
	/** 广告产品 */
	private Product product = new Product();
	
	/** 子级类别 */
	private List<ProductCategory> childCategory = new ArrayList<ProductCategory>();

	public List<ProductCategory> getChildCategory() {
		return childCategory;
	}

	public void setChildCategory(List<ProductCategory> childCategory) {
		this.childCategory = childCategory;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getRemarkEn() {
		return remarkEn;
	}

	public void setRemarkEn(String remarkEn) {
		this.remarkEn = remarkEn;
	}

	/** 类别名称 */
	public String getName(){
		return this.name;
	}

	/** 类别名称 */
	public void setName(String name){
		this.name = name;
	}
	/** 提示文字 */
	public String getTip(){
		return this.tip;
	}

	/** 提示文字 */
	public void setTip(String tip){
		this.tip = tip;
	}
	/** 类别父节点ID，必须先存在才能添加 */
	public String getParentId(){
		return this.parentId;
	}

	/** 类别父节点ID，必须先存在才能添加 */
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	/** 0-叶节点，1-根节点 */
	public Integer getIsParent(){
		return this.isParent;
	}

	/** 0-叶节点，1-根节点 */
	public void setIsParent(Integer isParent){
		this.isParent = isParent;
	}
	/** 类别图片URL */
	public String getImgPath(){
		return this.imgPath;
	}

	/** 类别图片URL */
	public void setImgPath(String imgPath){
		this.imgPath = imgPath;
	}
	/** 类别备注 */
	public String getRemark(){
		return this.remark;
	}

	/** 类别备注 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}