package com.tocersoft.system.dto;

import com.tocersoft.base.dto.BaseCondition;

public class SysUploadObjectCondition extends BaseCondition {

	/** 关联对象类型 */
	private String name;

	/** 关联对象类型 */
	public String getName(){
		return this.name;
	}

	/** 关联对象类型 */
	public void setName(String name){
		this.name = name;
	}
}
