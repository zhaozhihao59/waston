package com.tocersoft.member.dto;

import com.tocersoft.base.dto.BaseCondition;

public class MemLevelCondition extends BaseCondition {

	/** 等级名称 */
	private String levelName;
	/** 等级备注 */
	private String levelNote;

	/** 等级名称 */
	public String getLevelName(){
		return this.levelName;
	}

	/** 等级名称 */
	public void setLevelName(String levelName){
		this.levelName = levelName;
	}
	/** 等级备注 */
	public String getLevelNote(){
		return this.levelNote;
	}

	/** 等级备注 */
	public void setLevelNote(String levelNote){
		this.levelNote = levelNote;
	}
}
