package com.tocersoft.game.service;

import java.util.List;

import com.tocersoft.game.entity.GameEnrollTeam;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.game.dto.GameEnrollTeamCondition;

public interface IGameEnrollTeamService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listGameEnrollTeamByPage(PageResult<GameEnrollTeam> pageResult,GameEnrollTeamCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 赛事报名表 - 团队报名
	 */
	GameEnrollTeam getGameEnrollTeamById(String id);

	/**
	 * 新增
	 * @param item 赛事报名表 - 团队报名
	 */
	void add(GameEnrollTeam item);

	/**
	 * 修改
	 * @param item 赛事报名表 - 团队报名
	 */
	void update(GameEnrollTeam item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

