package com.tocersoft.product.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.product.dto.ExchangeRateCondition;
import com.tocersoft.product.entity.ExchangeRate;

public class ExchangeRateModel extends BaseModel<ExchangeRate>{

	private ExchangeRate item = new ExchangeRate();

	private ExchangeRateCondition condition = new ExchangeRateCondition();

	public ExchangeRateModel() {
		super();
	}

	public ExchangeRate getItem() {
		return item;
	}

	public void setItem(ExchangeRate item) {
		this.item = item;
	}

	public ExchangeRateCondition getCondition() {
		return condition;
	}

	public void setCondition(ExchangeRateCondition condition) {
		this.condition = condition;
	}

}
