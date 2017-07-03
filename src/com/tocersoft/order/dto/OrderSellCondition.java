package com.tocersoft.order.dto;

import com.tocersoft.base.dto.BaseCondition;

public class OrderSellCondition extends BaseCondition {

	/** 订单编号 */
	private String orderNo;
	/** 会员ID */
	private String memberId;
	/** 订单备注 */
	private String remark;
	/** 送货地址 */
	private String sendAddress;
	/** 送货联系人 */
	private String sendLinkman;
	/** 送货联系电话 */
	private String sendContact;
	/** 物流公司 */
	private String freightCompany;
	/** 运单号码 */
	private String freightBillNo;
	/** 发货时间 */
	private String freightDate;
	/** 订单状态：0-未付款 1-待发货 2-已发货 3-交易成功 4-订单作废 */
	private Integer orderState;
	/** 删除标志位 0：未删除 1：已删除 */
	private Integer deleteFlag;

	/** 订单编号 */
	public String getOrderNo(){
		return this.orderNo;
	}

	/** 订单编号 */
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	/** 会员ID */
	public String getMemberId(){
		return this.memberId;
	}

	/** 会员ID */
	public void setMemberId(String memberId){
		this.memberId = memberId;
	}
	/** 订单备注 */
	public String getRemark(){
		return this.remark;
	}

	/** 订单备注 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	/** 送货地址 */
	public String getSendAddress(){
		return this.sendAddress;
	}

	/** 送货地址 */
	public void setSendAddress(String sendAddress){
		this.sendAddress = sendAddress;
	}
	/** 送货联系人 */
	public String getSendLinkman(){
		return this.sendLinkman;
	}

	/** 送货联系人 */
	public void setSendLinkman(String sendLinkman){
		this.sendLinkman = sendLinkman;
	}
	/** 送货联系电话 */
	public String getSendContact(){
		return this.sendContact;
	}

	/** 送货联系电话 */
	public void setSendContact(String sendContact){
		this.sendContact = sendContact;
	}
	/** 物流公司 */
	public String getFreightCompany(){
		return this.freightCompany;
	}

	/** 物流公司 */
	public void setFreightCompany(String freightCompany){
		this.freightCompany = freightCompany;
	}
	/** 运单号码 */
	public String getFreightBillNo(){
		return this.freightBillNo;
	}

	/** 运单号码 */
	public void setFreightBillNo(String freightBillNo){
		this.freightBillNo = freightBillNo;
	}
	/** 发货时间 */
	public String getFreightDate(){
		return this.freightDate;
	}

	/** 发货时间 */
	public void setFreightDate(String freightDate){
		this.freightDate = freightDate;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}
