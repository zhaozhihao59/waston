package com.tocersoft.order.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 运费模板表
 * 
 * @creator
 * @create-time 2014-04-22 14:37:46
 */
public class FreightTemplate extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 运费模板 */
	private String templateName;
	/** 运费模板备注 */
	private String templateNote;
	/** 是否为默认运费模板：0-非默认，1-默认 */
	private Integer isDefault;

	/** 运费模板 */
	public String getTemplateName(){
		return this.templateName;
	}

	/** 运费模板 */
	public void setTemplateName(String templateName){
		this.templateName = templateName;
	}
	/** 运费模板备注 */
	public String getTemplateNote(){
		return this.templateNote;
	}

	/** 运费模板备注 */
	public void setTemplateNote(String templateNote){
		this.templateNote = templateNote;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
}