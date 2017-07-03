package com.tocersoft.subscribe.dto;

import com.tocersoft.base.dto.BaseCondition;

public class SubscribeSendCondition extends BaseCondition {

	/** 订阅邮件标题 */
	private String sendTitle;
	/** 订阅邮件内容 */
	private String htmlContent;

	/** 订阅邮件标题 */
	public String getSendTitle(){
		return this.sendTitle;
	}

	/** 订阅邮件标题 */
	public void setSendTitle(String sendTitle){
		this.sendTitle = sendTitle;
	}
	/** 订阅邮件内容 */
	public String getHtmlContent(){
		return this.htmlContent;
	}

	/** 订阅邮件内容 */
	public void setHtmlContent(String htmlContent){
		this.htmlContent = htmlContent;
	}
}
