package com.tocersoft.member.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.member.entity.MemTeam;

import com.tocersoft.member.dto.MemTeamCondition;

public class MemTeamModel extends BaseModel<MemTeam>{

	private MemTeam item = new MemTeam();

	private MemTeamCondition condition = new MemTeamCondition();

	public MemTeamModel() {
		super();
	}

	public MemTeam getItem() {
		return item;
	}

	public void setItem(MemTeam item) {
		this.item = item;
	}

	public MemTeamCondition getCondition() {
		return condition;
	}

	public void setCondition(MemTeamCondition condition) {
		this.condition = condition;
	}

}
