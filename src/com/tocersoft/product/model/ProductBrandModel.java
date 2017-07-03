package com.tocersoft.product.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.dto.ProductBrandCondition;
import com.tocersoft.product.entity.ProductBrand;
import com.tocersoft.system.entity.SysDictItem;

public class ProductBrandModel extends BaseModel<ProductBrand>{

	private ProductBrand item = new ProductBrand();

	private ProductBrandCondition condition = new ProductBrandCondition();
	
	private List<SysDictItem> brandTypeList = new ArrayList<SysDictItem>();

	public List<SysDictItem> getBrandTypeList() {
		return brandTypeList;
	}

	public void setBrandTypeList(List<SysDictItem> brandTypeList) {
		this.brandTypeList = brandTypeList;
	}

	public ProductBrandModel() {
		super();
	}

	private String selIds;
	
	public String getSelIds() {
		return selIds;
	}

	public void setSelIds(String selIds) {
		this.selIds = selIds;
	}

	public ProductBrand getItem() {
		return item;
	}

	public void setItem(ProductBrand item) {
		this.item = item;
	}

	public ProductBrandCondition getCondition() {
		return condition;
	}

	public void setCondition(ProductBrandCondition condition) {
		this.condition = condition;
	}

}
