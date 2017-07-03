package com.tocersoft.member.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.member.entity.MemLevel;

import com.tocersoft.member.dto.MemLevelCondition;

public class MemLevelModel extends BaseModel<MemLevel>{

	private MemLevel item = new MemLevel();

	private MemLevelCondition condition = new MemLevelCondition();

	public MemLevelModel() {
		super();
	}

	public MemLevel getItem() {
		return item;
	}

	public void setItem(MemLevel item) {
		this.item = item;
	}

	public MemLevelCondition getCondition() {
		return condition;
	}

	public void setCondition(MemLevelCondition condition) {
		this.condition = condition;
	}

}
