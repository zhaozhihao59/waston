package com.tocersoft.product.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.dto.ProductCategoryAttributeCondition;
import com.tocersoft.product.entity.ProductCategoryAttribute;

public class ProductCategoryAttributeModel extends BaseModel<ProductCategoryAttribute>{

	private ProductCategoryAttribute item = new ProductCategoryAttribute();

	private ProductCategoryAttributeCondition condition = new ProductCategoryAttributeCondition();
	
	private String categoryId;
	private String productId;
	/** 批量删除 */
	private String selIds;
	/** 属性值字符串，多个属性值以,号隔开 */
	private String attrValues;

	public String getAttrValues() {
		return attrValues;
	}

	public void setAttrValues(String attrValues) {
		this.attrValues = attrValues;
	}

	/** 批量删除 */
	public String getSelIds() {
		return selIds;
	}

	/** 批量删除 */
	public void setSelIds(String selIds) {
		this.selIds = selIds;
	}

	
	/** 产品接口 - 授权帐号 */
	private String authAccount;
	
	/** 产品接口 - 授权密码 */
	private String authPassword;
	
	/** 产品类别属性列表 */
	private List<ProductCategoryAttribute> itemList = new ArrayList<ProductCategoryAttribute>();

	public List<ProductCategoryAttribute> getItemList() {
		return itemList;
	}

	public void setItemList(List<ProductCategoryAttribute> itemList) {
		this.itemList = itemList;
	}

	public String getAuthAccount() {
		return authAccount;
	}

	public void setAuthAccount(String authAccount) {
		this.authAccount = authAccount;
	}

	public String getAuthPassword() {
		return authPassword;
	}

	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}

	public ProductCategoryAttributeModel() {
		super();
	}

	public ProductCategoryAttribute getItem() {
		return item;
	}

	public void setItem(ProductCategoryAttribute item) {
		this.item = item;
	}

	public ProductCategoryAttributeCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductCategoryAttributeCondition condition) {
		this.condition = condition;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}
