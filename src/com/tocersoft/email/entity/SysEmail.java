package com.tocersoft.email.entity;

import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * email发送信息
 * 
 * @author vinartis
 * @createDate 2013 7 16
 */
public class SysEmail extends BaseBusEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 接收邮件地址 多个以分号分割 */
	private String email;
	/** 邮件内容 */
	private String content;
	/** 0 未发送 1 已发送 2 发送失败 */
	private Integer flag;
	/** 备注 */
	private String remark;
	/** 标题 */
	private String title;
	/** 计划发送时间 默认为当前系统时间*/
	private Date planSendDate;
	/** 邮件类型 */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPlanSendDate() {
		return planSendDate;
	}

	public void setPlanSendDate(Date planSendDate) {
		this.planSendDate = planSendDate;
	}
}