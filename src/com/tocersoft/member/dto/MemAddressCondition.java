package com.tocersoft.member.dto;

public class MemAddressCondition {
	
	/** 地址状态值:0-正常 1-默认收货地址 */ 
	private Integer addressStatus;
	/** 所属会员 */
	private String memberId;
	public Integer getAddressStatus() {
		return addressStatus;
	}
	public void setAddressStatus(Integer addressStatus) {
		this.addressStatus = addressStatus;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	
	
}
