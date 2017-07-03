package com.tocersoft.auth.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.auth.entity.Depart;

import com.tocersoft.auth.dto.DepartCondition;

public class DepartModel extends BaseModel<Depart>{

	private Depart item = new Depart();
	
	private String parentId;
	
	private String id;
	 
	private DepartCondition condition = new DepartCondition();
	
	private Depart parentDepart = new Depart();

	public Depart getParentDepart() {
		return parentDepart;
	}

	public void setParentDepart(Depart parentDepart) {
		this.parentDepart = parentDepart;
	}

	public DepartModel() {
		super();
	}

	public Depart getItem() {
		return item;
	}

	public void setItem(Depart item) {
		this.item = item;
	}

	public DepartCondition getCondition() {
		return condition;
	}

	public void setCondition(DepartCondition condition) {
		this.condition = condition;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
