package com.tocersoft.subscribe.dto;

import com.tocersoft.base.dto.BaseCondition;

public class SubscribeSendRecordCondition extends BaseCondition {

	/** 订阅人ID */
	private String subscribeId;
	/** 订阅人邮箱 */
	private String email;
	/** 发送内容ID */
	private String sendId;

	/** 订阅人ID */
	public String getSubscribeId(){
		return this.subscribeId;
	}

	/** 订阅人ID */
	public void setSubscribeId(String subscribeId){
		this.subscribeId = subscribeId;
	}
	/** 订阅人邮箱 */
	public String getEmail(){
		return this.email;
	}

	/** 订阅人邮箱 */
	public void setEmail(String email){
		this.email = email;
	}
	/** 发送内容ID */
	public String getSendId(){
		return this.sendId;
	}

	/** 发送内容ID */
	public void setSendId(String sendId){
		this.sendId = sendId;
	}
}
