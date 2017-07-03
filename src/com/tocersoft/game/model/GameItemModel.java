package com.tocersoft.game.model;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.game.entity.GameItem;

import com.tocersoft.game.dto.GameItemCondition;

public class GameItemModel extends BaseModel<GameItem>{

	private GameItem item = new GameItem();

	private GameItemCondition condition = new GameItemCondition();

	public GameItemModel() {
		super();
	}

	public GameItem getItem() {
		return item;
	}

	public void setItem(GameItem item) {
		this.item = item;
	}

	public GameItemCondition getCondition() {
		return condition;
	}

	public void setCondition(GameItemCondition condition) {
		this.condition = condition;
	}

}
