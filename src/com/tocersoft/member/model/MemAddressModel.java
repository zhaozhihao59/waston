package com.tocersoft.member.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.member.dto.MemAddressCondition;
import com.tocersoft.member.entity.MemAddress;

public class MemAddressModel extends BaseModel<MemAddress>{
	private static final long serialVersionUID = 3014280275190359357L;

	private MemAddress item = new MemAddress();

	private MemAddressCondition condition = new MemAddressCondition();
	
	/** 会员收货地址 */
	private List<MemAddress> memAddressList = new ArrayList<MemAddress>();

	public MemAddressModel() {
		super();
	}

	public MemAddress getItem() {
		return item;
	}

	public void setItem(MemAddress item) {
		this.item = item;
	}

	public MemAddressCondition getCondition() {
		return condition;
	}

	public void setCondition(MemAddressCondition condition) {
		this.condition = condition;
	}

	public List<MemAddress> getMemAddressList() {
		return memAddressList;
	}

	public void setMemAddressList(List<MemAddress> memAddressList) {
		this.memAddressList = memAddressList;
	}
}
