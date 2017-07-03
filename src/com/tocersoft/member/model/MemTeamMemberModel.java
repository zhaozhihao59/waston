package com.tocersoft.member.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.member.entity.MemTeamMember;

import com.tocersoft.member.dto.MemTeamMemberCondition;

public class MemTeamMemberModel extends BaseModel<MemTeamMember>{

	private MemTeamMember item = new MemTeamMember();

	private MemTeamMemberCondition condition = new MemTeamMemberCondition();

	public MemTeamMemberModel() {
		super();
	}

	public MemTeamMember getItem() {
		return item;
	}

	public void setItem(MemTeamMember item) {
		this.item = item;
	}

	public MemTeamMemberCondition getCondition() {
		return condition;
	}

	public void setCondition(MemTeamMemberCondition condition) {
		this.condition = condition;
	}

}
