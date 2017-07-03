package com.tocersoft.activity.model;

import com.tocersoft.activity.dto.ActivityEnrollCondition;
import com.tocersoft.activity.entity.ActivityEnroll;
import com.tocersoft.base.model.BaseModel;

public class ActivityEnrollModel extends BaseModel<ActivityEnroll>{

	private ActivityEnroll item = new ActivityEnroll();

	private ActivityEnrollCondition condition = new ActivityEnrollCondition();

	public ActivityEnrollModel() {
		super();
	}

	public ActivityEnroll getItem() {
		return item;
	}

	public void setItem(ActivityEnroll item) {
		this.item = item;
	}

	public ActivityEnrollCondition getCondition() {
		return condition;
	}

	public void setCondition(ActivityEnrollCondition condition) {
		this.condition = condition;
	}

}
