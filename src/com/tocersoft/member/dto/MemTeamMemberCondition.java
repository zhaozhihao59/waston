package com.tocersoft.member.dto;

import com.tocersoft.base.dto.BaseCondition;

public class MemTeamMemberCondition extends BaseCondition {

	/** 会员ID */
	private String memberId;
	/** 团队ID */
	private String teamId;

	/** 会员ID */
	public String getMemberId(){
		return this.memberId;
	}

	/** 会员ID */
	public void setMemberId(String memberId){
		this.memberId = memberId;
	}
	/** 团队ID */
	public String getTeamId(){
		return this.teamId;
	}

	/** 团队ID */
	public void setTeamId(String teamId){
		this.teamId = teamId;
	}
}
