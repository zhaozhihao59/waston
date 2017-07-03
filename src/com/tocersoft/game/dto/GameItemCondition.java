package com.tocersoft.game.dto;

import com.tocersoft.base.dto.BaseCondition;

public class GameItemCondition extends BaseCondition {

	/** 赛事Id */
	private String gameId;
	/** 赛事项目名称 */
	private String itemName;


	/** 赛事项目名称 */
	public String getItemName(){
		return this.itemName;
	}

	/** 赛事项目名称 */
	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	
}
