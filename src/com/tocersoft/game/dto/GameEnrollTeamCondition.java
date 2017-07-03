package com.tocersoft.game.dto;

import com.tocersoft.base.dto.BaseCondition;

public class GameEnrollTeamCondition extends BaseCondition {

	/** 所属车队 */
	private String teamId;
	/** 车队经理ID */
	private String teamLeaderId;
	/** 车队经理 */
	private String teamLeaderName;
	/** 车队经理电话 */
	private String teamLeaderPhone;
	/** 备注 */
	private String remark;

	/** 所属车队 */
	public String getTeamId(){
		return this.teamId;
	}

	/** 所属车队 */
	public void setTeamId(String teamId){
		this.teamId = teamId;
	}
	/** 车队经理ID */
	public String getTeamLeaderId(){
		return this.teamLeaderId;
	}

	/** 车队经理ID */
	public void setTeamLeaderId(String teamLeaderId){
		this.teamLeaderId = teamLeaderId;
	}
	/** 车队经理 */
	public String getTeamLeaderName(){
		return this.teamLeaderName;
	}

	/** 车队经理 */
	public void setTeamLeaderName(String teamLeaderName){
		this.teamLeaderName = teamLeaderName;
	}
	/** 车队经理电话 */
	public String getTeamLeaderPhone(){
		return this.teamLeaderPhone;
	}

	/** 车队经理电话 */
	public void setTeamLeaderPhone(String teamLeaderPhone){
		this.teamLeaderPhone = teamLeaderPhone;
	}
	/** 备注 */
	public String getRemark(){
		return this.remark;
	}

	/** 备注 */
	public void setRemark(String remark){
		this.remark = remark;
	}
}
