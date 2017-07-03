package com.tocersoft.member.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 团队/团体信息
 * 
 * @creator
 * @create-time 2015-01-10 11:36:05
 */
public class MemTeam extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 团体名称 */
	private String name;
	/** 0-业余团体；1-专业团体 */
	private Integer type;
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
	/** 0-业余团体；1-专业团体 */
	public Integer getType(){
		return this.type;
	}

	/** 0-业余团体；1-专业团体 */
	public void setType(Integer type){
		this.type = type;
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