package com.tocersoft.subscribe.entity;
import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 邮件订阅发送表
 * 
 * @creator
 * @create-time 2015-04-22 16:38:26
 */
public class SubscribeSend extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

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