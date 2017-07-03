package com.tocersoft.game.service;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.game.dto.GameEnrollCondition;
import com.tocersoft.game.entity.GameEnroll;
import com.tocersoft.game.entity.GameEnrollTeam;

public interface IGameEnrollService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listGameEnrollByPage(PageResult<GameEnroll> pageResult,GameEnrollCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 赛事报名表
	 */
	GameEnroll getGameEnrollById(String id);
	
	/**
	 * 根据会员ID车队ID查询，判断重复报名
	 * @param id 主键
	 * @return 赛事报名表
	 */
	GameEnroll getGameEnrollByMemberIdAndGameItemId(String memberId,String gameItemId);


	/**
	 * 新增
	 * @param item 赛事报名表
	 */
	void add(GameEnroll item);
	
	/**
	 * 新增团队报名
	 * @param item 赛事报名表
	 */
	void addEnrollTeam(GameEnrollTeam enrollTeam);

	/**
	 * 修改
	 * @param item 赛事报名表
	 */
	void update(GameEnroll item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

