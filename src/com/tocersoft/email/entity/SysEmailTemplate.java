package com.tocersoft.email.entity;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 模板信息
 * 
 * @author taiqichao
 * 
 */
public class SysEmailTemplate extends BaseBusEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 模板类型
	 * @return
	 */
	private String type;

	/**
	 * 模板内容
	 * 
	 * @return
	 */
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
