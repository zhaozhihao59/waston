package com.tocersoft.cms.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.cms.entity.CmsChannel;

public class CmsChannelModel extends BaseModel<CmsChannel> {

	private static final long serialVersionUID = -9144930297403209741L;
	/**
	 * 栏目实体
	 */
	private CmsChannel item = new CmsChannel();

	/**
	 * 栏目ID
	 * 
	 * @return
	 */
	private String channelId;
	
	/**
	 * 当前类别ID
	 */
	private String nodeid;
	
	/**
	 * 上级类别ID
	 */
	private String parentId;

	/**
	 * 类另名称 
	 */
	private String name;
	
	/**
	 * 排序
	 */
	private Integer sortNum;
	public CmsChannel getItem() {
		return item;
	}

	public void setItem(CmsChannel item) {
		this.item = item;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getNodeid() {
		return nodeid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
}
