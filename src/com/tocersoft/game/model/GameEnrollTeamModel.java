package com.tocersoft.game.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.game.entity.GameEnrollTeam;

import com.tocersoft.game.dto.GameEnrollTeamCondition;

public class GameEnrollTeamModel extends BaseModel<GameEnrollTeam>{

	private GameEnrollTeam item = new GameEnrollTeam();

	private GameEnrollTeamCondition condition = new GameEnrollTeamCondition();

	public GameEnrollTeamModel() {
		super();
	}

	public GameEnrollTeam getItem() {
		return item;
	}

	public void setItem(GameEnrollTeam item) {
		this.item = item;
	}

	public GameEnrollTeamCondition getCondition() {
		return condition;
	}

	public void setCondition(GameEnrollTeamCondition condition) {
		this.condition = condition;
	}

}
