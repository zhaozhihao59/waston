package com.tocersoft.cms.dto;

import com.tocersoft.base.dto.BaseCondition;

/**
 * 考勤总汇查询条件实体
 * 
 * @creator fangquan
 * @create-time Nov 7, 2011 4:04:12 PM
 */
public class MessageCondition extends BaseCondition {
	
	private static final long serialVersionUID = 2790199600385605443L;
	
	/** 内容 */
	private String content;
	
	/** 发件人邮箱 */
	private String email;
	
	/** 发件人其他联系方式 */
	private String other;
	
	/** 状态 0.未处理 1.已回复 */
	private String status;
	
	/** 回复工程师 ID */
	private String replyId;
	
	/** 信息类型 1-预约理财师 2-申请续约 3-产品预约 4-活动预约 */
	private Integer messageType;
	
	/** 相关内容 */
	private String productName;

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	
}
