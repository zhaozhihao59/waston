package com.tocersoft.game.dto;

import com.tocersoft.base.dto.BaseCondition;

public class GameEnrollCondition extends BaseCondition {

	/** 赛事ID */
	private String gameItemId;
	/** 会员ID */
	private String memberId;
	/** 所属车队 */
	private String teamId;
	/** 参赛号 */
	private String enrollNo;
	/** 参赛运动员姓名 */
	private String memberName;
	/** 参赛运动员手机号码 */
	private String memberPhone;
	/** 备注 */
	private String remark;
	/** 比赛成绩 */
	private String score;

	/** 赛事ID */
	public String getGameItemId(){
		return this.gameItemId;
	}

	/** 赛事ID */
	public void setGameItemId(String gameItemId){
		this.gameItemId = gameItemId;
	}
	/** 会员ID */
	public String getMemberId(){
		return this.memberId;
	}

	/** 会员ID */
	public void setMemberId(String memberId){
		this.memberId = memberId;
	}
	/** 所属车队 */
	public String getTeamId(){
		return this.teamId;
	}

	/** 所属车队 */
	public void setTeamId(String teamId){
		this.teamId = teamId;
	}
	/** 参赛号 */
	public String getEnrollNo(){
		return this.enrollNo;
	}

	/** 参赛号 */
	public void setEnrollNo(String enrollNo){
		this.enrollNo = enrollNo;
	}
	/** 参赛运动员姓名 */
	public String getMemberName(){
		return this.memberName;
	}

	/** 参赛运动员姓名 */
	public void setMemberName(String memberName){
		this.memberName = memberName;
	}
	/** 参赛运动员手机号码 */
	public String getMemberPhone(){
		return this.memberPhone;
	}

	/** 参赛运动员手机号码 */
	public void setMemberPhone(String memberPhone){
		this.memberPhone = memberPhone;
	}
	/** 备注 */
	public String getRemark(){
		return this.remark;
	}

	/** 备注 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	/** 比赛成绩 */
	public String getScore(){
		return this.score;
	}

	/** 比赛成绩 */
	public void setScore(String score){
		this.score = score;
	}
}
