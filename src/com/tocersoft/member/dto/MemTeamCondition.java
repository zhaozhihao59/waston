package com.tocersoft.member.dto;

import com.tocersoft.base.dto.BaseCondition;

public class MemTeamCondition extends BaseCondition {

	/** 团体名称 */
	private String name;
	/** 团队描述 */
	private String desc;

	/** 团体名称 */
	public String getName(){
		return this.name;
	}

	/** 团体名称 */
	public void setName(String name){
		this.name = name;
	}
	/** 团队描述 */
	public String getDesc(){
		return this.desc;
	}

	/** 团队描述 */
	public void setDesc(String desc){
		this.desc = desc;
	}
}
