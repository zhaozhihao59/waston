package com.tocersoft.subscribe.dto;

import com.tocersoft.base.dto.BaseCondition;

public class SubscribeCondition extends BaseCondition {

	/** 邮箱 */
	private String email;
	/** 手机号码 */
	private String mobile;
	/** 订阅者姓名 */
	private String name;
	/** 公司名称 */
	private String companyName;
	/** 职务 */
	private String position;
	/** 订阅状态：0-正常订阅，1-退订 */
	private String state;
	/** 期刊类型ID（栏目ID） */
	private String channelId;
	/** 期刊类型名称 */
	private String channelName;
	/**
	 * 查询条件
	 */
	private String key;
	/** 邮箱 */
	public String getEmail(){
		return this.email;
	}

	/** 邮箱 */
	public void setEmail(String email){
		this.email = email;
	}
	/** 手机号码 */
	public String getMobile(){
		return this.mobile;
	}

	/** 手机号码 */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	/** 订阅者姓名 */
	public String getName(){
		return this.name;
	}

	/** 订阅者姓名 */
	public void setName(String name){
		this.name = name;
	}
	/** 公司名称 */
	public String getCompanyName(){
		return this.companyName;
	}

	/** 公司名称 */
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	/** 职务 */
	public String getPosition(){
		return this.position;
	}

	/** 职务 */
	public void setPosition(String position){
		this.position = position;
	}
	/** 订阅状态：0-正常订阅，1-退订 */
	public String getState(){
		return this.state;
	}

	/** 订阅状态：0-正常订阅，1-退订 */
	public void setState(String state){
		this.state = state;
	}
	/** 期刊类型ID（栏目ID） */
	public String getChannelId(){
		return this.channelId;
	}

	/** 期刊类型ID（栏目ID） */
	public void setChannelId(String channelId){
		this.channelId = channelId;
	}
	/** 期刊类型名称 */
	public String getChannelName(){
		return this.channelName;
	}

	/** 期刊类型名称 */
	public void setChannelName(String channelName){
		this.channelName = channelName;
	}
	/** 查询条件 */
	public String getKey() {
		return key;
	}
	/** 查询条件  */
	public void setKey(String key) {
		this.key = key;
	}
	
}
