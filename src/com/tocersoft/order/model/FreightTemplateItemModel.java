package com.tocersoft.order.model;

import com.tocersoft.base.model.BaseModel;

import com.tocersoft.order.dto.FreightTemplateItemCondition;
import com.tocersoft.order.entity.FreightTemplateItem;

public class FreightTemplateItemModel extends BaseModel<FreightTemplateItem>{

	private FreightTemplateItem item = new FreightTemplateItem();

	private FreightTemplateItemCondition condition = new FreightTemplateItemCondition();
	
	/** 省份字符串 */
	private String provinceArray;

	public FreightTemplateItemModel() {
		super();
	}

	public FreightTemplateItem getItem() {
		return item;
	}

	public void setItem(FreightTemplateItem item) {
		this.item = item;
	}

	public FreightTemplateItemCondition getCondition() {
		return condition;
	}

	public void setCondition(FreightTemplateItemCondition condition) {
		this.condition = condition;
	}

	public String getProvinceArray() {
		return provinceArray;
	}

	public void setProvinceArray(String provinceArray) {
		this.provinceArray = provinceArray;
	}

}
