package com.tocersoft.member.entity;
import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 
 * 
 * @creator
 * @create-time 2014-04-15 22:22:54
 */
public class MemLevel extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 等级名称 */
	private String levelName;
	/** 等级数字：1-一级；2-二级；3-三级；…… */
	private Integer levelNum;
	/** 等级备注 */
	private String levelNote;
	
	/** 不持久到数据库 */
	private Double proMemPrice;

	/** 等级名称 */
	public String getLevelName(){
		return this.levelName;
	}

	/** 等级名称 */
	public void setLevelName(String levelName){
		this.levelName = levelName;
	}
	/** 等级数字：1-一级；2-二级；3-三级；…… */
	public Integer getLevelNum(){
		return this.levelNum;
	}

	/** 等级数字：1-一级；2-二级；3-三级；…… */
	public void setLevelNum(Integer levelNum){
		this.levelNum = levelNum;
	}
	/** 等级备注 */
	public String getLevelNote(){
		return this.levelNote;
	}

	/** 等级备注 */
	public void setLevelNote(String levelNote){
		this.levelNote = levelNote;
	}

	public Double getProMemPrice() {
		return proMemPrice;
	}

	public void setProMemPrice(Double proMemPrice) {
		this.proMemPrice = proMemPrice;
	}
}