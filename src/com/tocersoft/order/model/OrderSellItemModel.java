package com.tocersoft.order.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.order.entity.OrderSellItem;

import com.tocersoft.order.dto.OrderSellItemCondition;

public class OrderSellItemModel extends BaseModel<OrderSellItem>{

	private OrderSellItem item = new OrderSellItem();

	private OrderSellItemCondition condition = new OrderSellItemCondition();

	public OrderSellItemModel() {
		super();
	}

	public OrderSellItem getItem() {
		return item;
	}

	public void setItem(OrderSellItem item) {
		this.item = item;
	}

	public OrderSellItemCondition getCondition() {
		return condition;
	}

	public void setCondition(OrderSellItemCondition condition) {
		this.condition = condition;
	}

}
