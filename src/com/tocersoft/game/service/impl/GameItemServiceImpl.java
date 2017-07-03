package com.tocersoft.game.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.game.dao.IGameItemDao;
import com.tocersoft.game.entity.GameItem;
import com.tocersoft.game.service.IGameItemService;

import com.tocersoft.game.dto.GameItemCondition;

@Service("gameItemServiceImpl")
@Transactional(value = "transactionManager")
public class GameItemServiceImpl implements IGameItemService{

	@Resource(name = "gameItemDaoImpl")
	private IGameItemDao gameItemDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listGameItemByPage(PageResult<GameItem> pageResult,GameItemCondition condition){
		int rows = gameItemDao.listGameItemByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<GameItem> list = gameItemDao.listGameItemByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 赛事项目
	 */
	public GameItem getGameItemById(String id){
		return gameItemDao.getGameItemById(id);
	}

	/**
	 * 新增
	 * @param item 赛事项目
	 */
	public void add(GameItem item){
		gameItemDao.add(item);
	}

	/**
	 * 修改
	 * @param item 赛事项目
	 */
	public void update(GameItem item){
		gameItemDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		gameItemDao.delByIds(ids);
	}

	/**
	 * 赛事项目列表
	 */
	@Override
	public List<GameItem> listGameItem(String gameId) {
		return gameItemDao.listGameItem(gameId);
	}

}

