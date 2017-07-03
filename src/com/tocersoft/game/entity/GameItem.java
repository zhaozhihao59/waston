package com.tocersoft.game.entity;
import java.util.Date;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 赛事项目
 * 
 * @creator
 * @create-time 2015-01-10 11:38:41
 */
public class GameItem extends BaseBusEntity {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 赛事ID */
	private String gameId;
	/** 赛事项目名称 */
	private String itemName;
	/** 赛事项目状态：0-关闭，1-开启 */
	private Integer state;
	/** 赛事项目报名费 */
	private Double enrollFee;
	/** 赛事项目报名费 - 团队 */
	private Double enrollFeeTeam;
	/** 赛事项目报名费 - 当地人 */
	private Double enrollFeeLocal;

	/** 赛事ID */
	public String getGameId(){
		return this.gameId;
	}

	/** 赛事ID */
	public void setGameId(String gameId){
		this.gameId = gameId;
	}
	/** 赛事项目名称 */
	public String getItemName(){
		return this.itemName;
	}

	/** 赛事项目名称 */
	public void setItemName(String itemName){
		this.itemName = itemName;
	}
	/** 赛事项目状态：0-关闭，1-开启 */
	public Integer getState(){
		return this.state;
	}

	/** 赛事项目状态：0-关闭，1-开启 */
	public void setState(Integer state){
		this.state = state;
	}
	/** 赛事项目报名费 */
	public Double getEnrollFee(){
		return this.enrollFee;
	}

	/** 赛事项目报名费 */
	public void setEnrollFee(Double enrollFee){
		this.enrollFee = enrollFee;
	}

	public Double getEnrollFeeTeam() {
		return enrollFeeTeam;
	}

	public void setEnrollFeeTeam(Double enrollFeeTeam) {
		this.enrollFeeTeam = enrollFeeTeam;
	}

	public Double getEnrollFeeLocal() {
		return enrollFeeLocal;
	}

	public void setEnrollFeeLocal(Double enrollFeeLocal) {
		this.enrollFeeLocal = enrollFeeLocal;
	}
}