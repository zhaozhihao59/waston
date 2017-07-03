package com.tocersoft.game.entity;
import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 赛事报名表 - 团队报名
 * 
 * @creator
 * @create-time 2015-03-08 01:43:36
 */
public class GameEnrollTeam extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 赛事项目ID */
	private String gameItemId;
	/** 所属车队ID */
	private String teamId;
	/** 所属车队名称 */
	private String teamName;
	/** 车队经理ID */
	private String teamLeaderId;
	/** 车队经理 */
	private String teamLeaderName;
	/** 车队经理电话 */
	private String teamLeaderPhone;
	/** 报名人数 */
	private Integer enrollCount;
	/** 报名费总计 */
	private Double enrollFee;
	/** 报名费 - 已付款 */
	private Double enrollFeePaid;
	/** 报名状态：0-未付款，1-已付款，2-已成功，3-已取消 */
	private Integer state;
	/** 备注 */
	private String remark;
	
	/** ========== 不持久化 ========== */
	/** 团队下多名队员报名 */
	private List<GameEnroll> enrollList = new ArrayList<GameEnroll>();

	public List<GameEnroll> getEnrollList() {
		return enrollList;
	}

	public void setEnrollList(List<GameEnroll> enrollList) {
		this.enrollList = enrollList;
	}

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
	/** 报名人数 */
	public Integer getEnrollCount(){
		return this.enrollCount;
	}

	/** 报名人数 */
	public void setEnrollCount(Integer enrollCount){
		this.enrollCount = enrollCount;
	}
	/** 报名费总计 */
	public Double getEnrollFee(){
		return this.enrollFee;
	}

	/** 报名费总计 */
	public void setEnrollFee(Double enrollFee){
		this.enrollFee = enrollFee;
	}
	/** 报名费 - 已付款 */
	public Double getEnrollFeePaid(){
		return this.enrollFeePaid;
	}

	/** 报名费 - 已付款 */
	public void setEnrollFeePaid(Double enrollFeePaid){
		this.enrollFeePaid = enrollFeePaid;
	}
	/** 报名状态：0-未付款，1-已付款，2-已成功，3-已取消 */
	public Integer getState(){
		return this.state;
	}

	/** 报名状态：0-未付款，1-已付款，2-已成功，3-已取消 */
	public void setState(Integer state){
		this.state = state;
	}
	/** 备注 */
	public String getRemark(){
		return this.remark;
	}

	/** 备注 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getGameItemId() {
		return gameItemId;
	}

	public void setGameItemId(String gameItemId) {
		this.gameItemId = gameItemId;
	}
}