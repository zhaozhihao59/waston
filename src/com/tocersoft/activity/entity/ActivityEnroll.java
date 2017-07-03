package com.tocersoft.activity.entity;
import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 活动报名表
 * 
 * @creator
 * @create-time 2015-04-22 17:47:03
 */
public class ActivityEnroll extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

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
	/**电话 */
	private String tel;
	/** 公司名称 */
	private String companyName;
	/** 职位 */
	private String positionName;
	

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
	/**电话 */
	public String getTel() {
		return tel;
	}
	/**电话 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/** 公司名称 */
	public String getCompanyName() {
		return companyName;
	}
	/** 公司名称 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/** 职位 */
	public String getPositionName() {
		return positionName;
	}
	/** 职位 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
}