package com.tocersoft.activity.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ActivityCondition extends BaseCondition {

	/** 活动状态 1-预告 2正在报名 3历届*/
	private Integer state;
	/** 会议标题 */
	private String title;
	/** 会议图片 */
	private String photo;
	/** 会议简介 */
	private String desc;
	/** 会议时间(字符串输入) */
	private String dateStr;
	/** 活动地址 */
	private String address;

	private Integer type;
	/** 查询key*/
	private String key;
	//
	private String en;
	private String jp;
	
	
	public String getJp() {
		return jp;
	}

	public void setJp(String jp) {
		this.jp = jp;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/** 会议标题 */
	public String getTitle(){
		return this.title;
	}

	/** 会议标题 */
	public void setTitle(String title){
		this.title = title;
	}
	/** 会议图片 */
	public String getPhoto(){
		return this.photo;
	}

	/** 会议图片 */
	public void setPhoto(String photo){
		this.photo = photo;
	}
	/** 会议简介 */
	public String getDesc(){
		return this.desc;
	}

	/** 会议简介 */
	public void setDesc(String desc){
		this.desc = desc;
	}
	/** 会议时间(字符串输入) */
	public String getDateStr(){
		return this.dateStr;
	}

	/** 会议时间(字符串输入) */
	public void setDateStr(String dateStr){
		this.dateStr = dateStr;
	}
	/** 活动地址 */
	public String getAddress(){
		return this.address;
	}
	
	/** 活动地址 */
	public void setAddress(String address){
		this.address = address;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
}
