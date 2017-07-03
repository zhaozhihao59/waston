package com.tocersoft.game.dto;

import com.tocersoft.base.dto.BaseCondition;

public class GameCondition extends BaseCondition {

	/** 赛事名称 */
	private String gameName;
	/** 比赛开始时间（字符串输入） */
	private String gameDateStr;
	/** 比赛地址 */
	private String gameAddress;
	/** 赛事状态：0-预告，1-报名中，3历届 */
	private Integer state;
	/** 赛事介绍 - 文本编辑器 */
	private String descHtml;
	/** 赛事须知 - 文本编辑器 */
	private String noteHtml;
	/** 日程安排 - 文本编辑器 */
	private String scheduleHtml;
	/** 赛道图 - 文本编辑器 */
	private String trackHtml;
	/** 录取奖励 - 文本编辑器 */
	private String rewardHtml;
	/** 交通住宿 - 文本编辑器 */
	private String transportHtml;
	/** 联系方式 - 文本编辑器 */
	private String contactHtml;

	/** 赛事名称 */
	public String getGameName(){
		return this.gameName;
	}

	/** 赛事名称 */
	public void setGameName(String gameName){
		this.gameName = gameName;
	}
	/** 比赛开始时间（字符串输入） */
	public String getGameDateStr(){
		return this.gameDateStr;
	}

	/** 比赛开始时间（字符串输入） */
	public void setGameDateStr(String gameDateStr){
		this.gameDateStr = gameDateStr;
	}
	/** 比赛地址 */
	public String getGameAddress(){
		return this.gameAddress;
	}

	/** 比赛地址 */
	public void setGameAddress(String gameAddress){
		this.gameAddress = gameAddress;
	}
	/** 赛事介绍 - 文本编辑器 */
	public String getDescHtml(){
		return this.descHtml;
	}

	/** 赛事介绍 - 文本编辑器 */
	public void setDescHtml(String descHtml){
		this.descHtml = descHtml;
	}
	/** 赛事须知 - 文本编辑器 */
	public String getNoteHtml(){
		return this.noteHtml;
	}

	/** 赛事须知 - 文本编辑器 */
	public void setNoteHtml(String noteHtml){
		this.noteHtml = noteHtml;
	}
	/** 日程安排 - 文本编辑器 */
	public String getScheduleHtml(){
		return this.scheduleHtml;
	}

	/** 日程安排 - 文本编辑器 */
	public void setScheduleHtml(String scheduleHtml){
		this.scheduleHtml = scheduleHtml;
	}
	/** 赛道图 - 文本编辑器 */
	public String getTrackHtml(){
		return this.trackHtml;
	}

	/** 赛道图 - 文本编辑器 */
	public void setTrackHtml(String trackHtml){
		this.trackHtml = trackHtml;
	}
	/** 录取奖励 - 文本编辑器 */
	public String getRewardHtml(){
		return this.rewardHtml;
	}

	/** 录取奖励 - 文本编辑器 */
	public void setRewardHtml(String rewardHtml){
		this.rewardHtml = rewardHtml;
	}
	/** 交通住宿 - 文本编辑器 */
	public String getTransportHtml(){
		return this.transportHtml;
	}

	/** 交通住宿 - 文本编辑器 */
	public void setTransportHtml(String transportHtml){
		this.transportHtml = transportHtml;
	}
	/** 联系方式 - 文本编辑器 */
	public String getContactHtml(){
		return this.contactHtml;
	}

	/** 联系方式 - 文本编辑器 */
	public void setContactHtml(String contactHtml){
		this.contactHtml = contactHtml;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}
