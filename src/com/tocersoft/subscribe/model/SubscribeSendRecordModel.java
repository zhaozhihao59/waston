package com.tocersoft.subscribe.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.subscribe.dto.SubscribeSendRecordCondition;
import com.tocersoft.subscribe.entity.SubscribeSendRecord;

public class SubscribeSendRecordModel extends BaseModel<SubscribeSendRecord>{

	private SubscribeSendRecord item = new SubscribeSendRecord();

	private SubscribeSendRecordCondition condition = new SubscribeSendRecordCondition();

	public SubscribeSendRecordModel() {
		super();
	}

	public SubscribeSendRecord getItem() {
		return item;
	}

	public void setItem(SubscribeSendRecord item) {
		this.item = item;
	}

	public SubscribeSendRecordCondition getCondition() {
		return condition;
	}

	public void setCondition(SubscribeSendRecordCondition condition) {
		this.condition = condition;
	}

}
