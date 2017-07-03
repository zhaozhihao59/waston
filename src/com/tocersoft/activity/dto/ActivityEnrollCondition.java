package com.tocersoft.activity.dto;

import com.tocersoft.base.dto.BaseCondition;

public class ActivityEnrollCondition extends BaseCondition {

	/** 活动ID */
	private String activityId;
	/** 活动名称 */
	private String activityName;
	/** 姓名 */
	private String name;
	/** 手机号码  */
	private String mobile;
	/** 邮箱 */
	private String email;
	/** 查询key */
	private String key;


	/** 活动ID */
	public String getActivityId(){
		return this.activityId;
	}

	/** 活动ID */
	public void setActivityId(String activityId){
		this.activityId = activityId;
	}
	/** 活动名称 */
	public String getActivityName(){
		return this.activityName;
	}

	/** 活动名称 */
	public void setActivityName(String activityName){
		this.activityName = activityName;
	}
	/** 姓名 */
	public String getName(){
		return this.name;
	}

	/** 姓名 */
	public void setName(String name){
		this.name = name;
	}
	/** 手机号码  */
	public String getMobile(){
		return this.mobile;
	}

	/** 手机号码  */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	/** 邮箱 */
	public String getEmail(){
		return this.email;
	}

	/** 邮箱 */
	public void setEmail(String email){
		this.email = email;
	}
	/** 查询key */
	public String getKey() {
		return key;
	}
	/** 查询key */
	public void setKey(String key) {
		this.key = key;
	}
	
}
