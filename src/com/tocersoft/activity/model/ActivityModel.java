package com.tocersoft.activity.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.activity.dto.ActivityCondition;
import com.tocersoft.activity.entity.Activity;
import com.tocersoft.base.model.BaseModel;

public class ActivityModel extends BaseModel<Activity>{

	private Activity item = new Activity();

	private ActivityCondition condition = new ActivityCondition();
	
	List<Activity> listActivity = new ArrayList<Activity>();
	List<Activity> listActivityPrediction = new ArrayList<Activity>();
	private Integer page;
	
	
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public ActivityModel() {
		super();
	}

	public Activity getItem() {
		return item;
	}

	public void setItem(Activity item) {
		this.item = item;
	}

	public ActivityCondition getCondition() {
		return condition;
	}

	public void setCondition(ActivityCondition condition) {
		this.condition = condition;
	}

	public List<Activity> getListActivity() {
		return listActivity;
	}

	public void setListActivity(List<Activity> listActivity) {
		this.listActivity = listActivity;
	}

	public List<Activity> getListActivityPrediction() {
		return listActivityPrediction;
	}

	public void setListActivityPrediction(List<Activity> listActivityPrediction) {
		this.listActivityPrediction = listActivityPrediction;
	}
	
	
	

}
