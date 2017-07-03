package com.tocersoft.subscribe.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.cms.entity.CmsChannel;
import com.tocersoft.subscribe.dto.SubscribeCondition;
import com.tocersoft.subscribe.entity.Subscribe;
import com.tocersoft.system.entity.SysDictItem;

public class SubscribeModel extends BaseModel<Subscribe>{

	private Subscribe item = new Subscribe();

	private SubscribeCondition condition = new SubscribeCondition();

	private String email;
	private String channelId;
	private List<CmsChannel> cmsChannel; 
	public SubscribeModel() {
		super();
	}

	public Subscribe getItem() {
		return item;
	}

	public void setItem(Subscribe item) {
		this.item = item;
	}

	public SubscribeCondition getCondition() {
		return condition;
	}

	public void setCondition(SubscribeCondition condition) {
		this.condition = condition;
	}

	public List<CmsChannel> getCmsChannel() {
		return cmsChannel;
	}

	public void setCmsChannel(List<CmsChannel> cmsChannel) {
		this.cmsChannel = cmsChannel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	
	

}
