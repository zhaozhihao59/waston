package com.tocersoft.cms.dto;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 文章管理VO
 * 
 * @author 欧阳明航
 * 
 */
public class CmsArticleVo extends BaseBusEntity {

	private static final long serialVersionUID = 8818168607500646034L;
	/** 文章标题 */
	private String name;
	/** 文章内容 */
	private String content;
	/** 栏目名称 */
	private String channelName;
	/** 栏目ID */
	private String channelId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

}
