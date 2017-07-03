package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.entity.ProductCategory;

import com.tocersoft.product.dto.ProductCategoryCondition;

public class ProductCategoryModel extends BaseModel<ProductCategory>{

	private ProductCategory item = new ProductCategory();

	private ProductCategoryCondition condition = new ProductCategoryCondition();
	
	/** 产品接口 - 授权帐号 */
	private String authAccount;
	
	/** 产品接口 - 授权密码 */
	private String authPassword;

	private String path;
	
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

	public ProductCategoryModel() {
		super();
	}

	public ProductCategory getItem() {
		return item;
	}

	public void setItem(ProductCategory item) {
		this.item = item;
	}

	public ProductCategoryCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductCategoryCondition condition) {
		this.condition = condition;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
