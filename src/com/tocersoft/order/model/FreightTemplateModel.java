package com.tocersoft.order.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.model.BaseModel;

import com.tocersoft.order.dto.FreightTemplateCondition;
import com.tocersoft.order.entity.FreightTemplate;

public class FreightTemplateModel extends BaseModel<FreightTemplate>{

	private FreightTemplate item = new FreightTemplate();

	private FreightTemplateCondition condition = new FreightTemplateCondition();
	
	private List<FreightTemplate> freightTemplates = new ArrayList<FreightTemplate>();

	public FreightTemplateModel() {
		super();
	}

	public FreightTemplate getItem() {
		return item;
	}

	public void setItem(FreightTemplate item) {
		this.item = item;
	}

	public FreightTemplateCondition getCondition() {
		return condition;
	}

	public void setCondition(FreightTemplateCondition condition) {
		this.condition = condition;
	}

	public List<FreightTemplate> getFreightTemplates() {
		return freightTemplates;
	}

	public void setFreightTemplates(List<FreightTemplate> freightTemplates) {
		this.freightTemplates = freightTemplates;
	}

}
