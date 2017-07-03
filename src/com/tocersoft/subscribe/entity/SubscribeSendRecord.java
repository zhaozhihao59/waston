package com.tocersoft.subscribe.entity;
import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 邮件订阅发送表
 * 
 * @creator
 * @create-time 2015-04-22 16:38:26
 */
public class SubscribeSendRecord extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 订阅人ID */
	private String subscribeId;
	/** 订阅人姓名 */
	private String subscribeName;
	/** 订阅人手机号 */
	private String subscribeMobile;
	/** 订阅期刊名称*/
	private String subscribeChannelName;
	/** 订阅人邮箱 */
	private String email;
	/** 发送内容ID */
	private String sendId;
	/** 发送标题 */
	private String subscribeSendTitle;
	

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
	/** 订阅人姓名 */
	public String getSubscribeName() {
		return subscribeName;
	}
	/** 订阅人姓名 */
	public void setSubscribeName(String subscribeName) {
		this.subscribeName = subscribeName;
	}
	/** 订阅人手机号 */
	public String getSubscribeMobile() {
		return subscribeMobile;
	}
	/** 订阅人手机号 */
	public void setSubscribeMobile(String subscribeMobile) {
		this.subscribeMobile = subscribeMobile;
	}
	/** 订阅期刊名称*/
	public String getSubscribeChannelName() {
		return subscribeChannelName;
	}
	/** 订阅期刊名称*/
	public void setSubscribeChannelName(String subscribeChannelName) {
		this.subscribeChannelName = subscribeChannelName;
	}
	/** 发送标题 */
	public String getSubscribeSendTitle() {
		return subscribeSendTitle;
	}
	/** 发送标题 */
	public void setSubscribeSendTitle(String subscribeSendTitle) {
		this.subscribeSendTitle = subscribeSendTitle;
	}
	
}