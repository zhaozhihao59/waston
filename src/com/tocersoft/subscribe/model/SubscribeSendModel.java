package com.tocersoft.subscribe.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.subscribe.dto.SubscribeSendCondition;
import com.tocersoft.subscribe.entity.SubscribeSend;

public class SubscribeSendModel extends BaseModel<SubscribeSend>{

	private SubscribeSend item = new SubscribeSend();

	private SubscribeSendCondition condition = new SubscribeSendCondition();
	private String receiver;
	public SubscribeSendModel() {
		super();
	}

	public SubscribeSend getItem() {
		return item;
	}

	public void setItem(SubscribeSend item) {
		this.item = item;
	}

	public SubscribeSendCondition getCondition() {
		return condition;
	}

	public void setCondition(SubscribeSendCondition condition) {
		this.condition = condition;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


}
