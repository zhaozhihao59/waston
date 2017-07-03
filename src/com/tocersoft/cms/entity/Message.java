package com.tocersoft.cms.entity;

import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 网站留言
 * 
 * @creator fangquan
 * @create-time Nov 7, 2011 4:04:12 PM
 */
public class Message extends BaseBusEntity {
	
	private static final long serialVersionUID = 2790199600385605443L;
	
	/** 希望回复时间-最早 */
	private Date replyDateStart;
	/** 希望回复时间-最迟 */
	private Date replyDateEnd;
	/** 希望了解的方面 */
	private String hopeKnow;
	/** 对应的实体NAME，包括：产品、活动 */
	private String productName;
	/** 对应的实体ID，包括：产品、活动 */
	private String productId;
	/** 信息类型 1-预约理财师 2-申请续约 3-产品预约 4-活动预约 */
	private Integer messageType;
	/** 其它方面 */
	private String otherContent;
	/** 内容 */
	private String content;
	/** 发件人邮箱 */
	private String email;
	/** 发件人其他联系方式 */
	private String other;
	/** 发件人手机 */
	private String mobile;
	/** 发件人名 */
	private String createName;
	/** 状态 0-未处理 1-已回复 */
	private Integer status;
	/** 回复工程师 ID */
	private String replyId;
	/** 回复工程师 名字 */
	private String replyName;
	/** 回复工程师 日期 */
	private Date replyDate;
	/** IP地址 */
	private String ip;
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getOtherContent() {
		return otherContent;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Date getReplyDateStart() {
		return replyDateStart;
	}

	public void setReplyDateStart(Date replyDateStart) {
		this.replyDateStart = replyDateStart;
	}

	public Date getReplyDateEnd() {
		return replyDateEnd;
	}

	public void setReplyDateEnd(Date replyDateEnd) {
		this.replyDateEnd = replyDateEnd;
	}

	public String getHopeKnow() {
		return hopeKnow;
	}

	public void setHopeKnow(String hopeKnow) {
		this.hopeKnow = hopeKnow;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

}
