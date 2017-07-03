package com.tocersoft.game.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 赛事信息
 * 
 * @creator
 * @create-time 2015-01-10 11:38:41
 */
public class Game extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 赛事编号 */
	private String gameNo;
	/** 赛事名称 */
	private String gameName;
	/** 赛事状态：0-预告，1-报名中，2-报名结束等待比赛，3-比赛中，4-已过期 */
	private Integer state;
	/** 报名开始日期 */
	private Date enrollBeginDate;
	/** 报名结束日期 */
	private Date enrollEndDate;
	/** 比赛开始时间（字符串输入） */
	private String gameDateStr;
	/** 比赛地址 */
	private String gameAddress;
	/** 比赛城市 */
	private String gameCity;
	/** 赛事大图 */
	private String gamePhoto;
	/** 赛事列表图 */
	private String gamePhotoTo;
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
	/** 比赛月份 */
	private String gameMonth;
	/** 显示排序 */
	private Integer sort;
	
	public String getGameMonth() {
		return gameMonth;
	}

	public void setGameMonth(String gameMonth) {
		this.gameMonth = gameMonth;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/** 赛事编号 */
	public String getGameNo(){
		return this.gameNo;
	}

	/** 赛事编号 */
	public void setGameNo(String gameNo){
		this.gameNo = gameNo;
	}
	/** 赛事名称 */
	public String getGameName(){
		return this.gameName;
	}

	/** 赛事名称 */
	public void setGameName(String gameName){
		this.gameName = gameName;
	}
	/** 赛事状态：0-预告，1-报名中，2-报名结束等待比赛，3-比赛中，4-已过期 */
	public Integer getState(){
		return this.state;
	}

	/** 赛事状态：0-预告，1-报名中，2-报名结束等待比赛，3-比赛中，4-已过期 */
	public void setState(Integer state){
		this.state = state;
	}
	/** 报名开始日期 */
	public Date getEnrollBeginDate(){
		return this.enrollBeginDate;
	}

	/** 报名开始日期 */
	public void setEnrollBeginDate(Date enrollBeginDate){
		this.enrollBeginDate = enrollBeginDate;
	}
	/** 报名结束日期 */
	public Date getEnrollEndDate(){
		return this.enrollEndDate;
	}

	/** 报名结束日期 */
	public void setEnrollEndDate(Date enrollEndDate){
		this.enrollEndDate = enrollEndDate;
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

	public String getGamePhoto() {
		return gamePhoto;
	}

	public void setGamePhoto(String gamePhoto) {
		this.gamePhoto = gamePhoto;
	}

	public String getGamePhotoTo() {
		return gamePhotoTo;
	}

	public void setGamePhotoTo(String gamePhotoTo) {
		this.gamePhotoTo = gamePhotoTo;
	}

	public String getGameCity() {
		return gameCity;
	}

	public void setGameCity(String gameCity) {
		this.gameCity = gameCity;
	}
	
}