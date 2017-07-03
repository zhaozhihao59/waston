package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.dto.ProductCommentCondition;
import com.tocersoft.product.entity.ProductComment;

public class ProductCommentModel extends BaseModel<ProductComment>{

	private ProductComment item = new ProductComment();

	private ProductCommentCondition condition = new ProductCommentCondition();

	public ProductCommentModel() {
		super();
	}

	public ProductComment getItem() {
		return item;
	}

	public void setItem(ProductComment item) {
		this.item = item;
	}

	public ProductCommentCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductCommentCondition condition) {
		this.condition = condition;
	}

}
