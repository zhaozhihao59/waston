package com.tocersoft.game.model;

import java.util.ArrayList;
import java.util.List;

import com.tocersoft.base.model.BaseModel;
import com.tocersoft.game.entity.GameEnroll;
import com.tocersoft.game.entity.GameItem;

import com.tocersoft.game.dto.GameEnrollCondition;
import com.tocersoft.member.entity.MemTeam;

/**
 * @author Administrator
 *
 */
public class GameEnrollModel extends BaseModel<GameEnroll>{

	private GameEnroll item = new GameEnroll();

	private GameEnrollCondition condition = new GameEnrollCondition();
	
	private List<MemTeam> listMemTeam = new ArrayList<MemTeam>();
	private List<GameItem> listGameItem = new ArrayList<GameItem>();

	public GameEnrollModel() {
		super();
	}

	public GameEnroll getItem() {
		return item;
	}

	public void setItem(GameEnroll item) {
		this.item = item;
	}

	public GameEnrollCondition getCondition() {
		return condition;
	}

	public void setCondition(GameEnrollCondition condition) {
		this.condition = condition;
	}

	public List<MemTeam> getListMemTeam() {
		return listMemTeam;
	}

	public void setListMemTeam(List<MemTeam> listMemTeam) {
		this.listMemTeam = listMemTeam;
	}

	public List<GameItem> getListGameItem() {
		return listGameItem;
	}

	public void setListGameItem(List<GameItem> listGameItem) {
		this.listGameItem = listGameItem;
	}

}
