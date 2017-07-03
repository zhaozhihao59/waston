package com.tocersoft.game.service;

import java.util.List;

import com.tocersoft.game.entity.GameItem;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.game.dto.GameItemCondition;

public interface IGameItemService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listGameItemByPage(PageResult<GameItem> pageResult,GameItemCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 赛事项目
	 */
	GameItem getGameItemById(String id);

	/**
	 * 赛事项目列表
	 */
	List<GameItem> listGameItem(String gameId);
	
	/**
	 * 新增
	 * @param item 赛事项目
	 */
	void add(GameItem item);

	/**
	 * 修改
	 * @param item 赛事项目
	 */
	void update(GameItem item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

