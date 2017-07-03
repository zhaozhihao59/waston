package com.tocersoft.game.service;

import java.util.List;

import com.tocersoft.game.entity.Game;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.game.dto.GameCondition;

public interface IGameService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listGameByPage(PageResult<Game> pageResult,GameCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 赛事信息
	 */
	Game getGameById(String id);

	/**
	 * 条件查询
	 * @param condition 查询条件类
	 */
	List<Game> listGameByCondition(GameCondition condition);
	
	/**
	 * 新增
	 * @param item 赛事信息
	 */
	void add(Game item);

	/**
	 * 修改
	 * @param item 赛事信息
	 */
	void update(Game item);
	
	/**
	 * 修改图片路径 - 大图
	 * @param photo 赛事缩略图 - 大图
	 * @param gameId 赛事ID
	 */
	void updatePhoto(String photo, String gameId);
	
	/**
	 * 修改图片路径 - 小图
	 * @param photoTo 赛事缩略图 - 小图
	 * @param gameId 赛事ID
	 */
	void updatePhotoTo(String photoTo, String gameId);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

