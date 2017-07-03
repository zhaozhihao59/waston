package com.tocersoft.game.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.game.dao.IGameEnrollTeamDao;
import com.tocersoft.game.entity.GameEnrollTeam;
import com.tocersoft.game.service.IGameEnrollTeamService;

import com.tocersoft.game.dto.GameEnrollTeamCondition;

@Service("gameEnrollTeamServiceImpl")
@Transactional(value = "transactionManager")
public class GameEnrollTeamServiceImpl implements IGameEnrollTeamService{

	@Resource(name = "gameEnrollTeamDaoImpl")
	private IGameEnrollTeamDao gameEnrollTeamDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listGameEnrollTeamByPage(PageResult<GameEnrollTeam> pageResult,GameEnrollTeamCondition condition){
		int rows = gameEnrollTeamDao.listGameEnrollTeamByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<GameEnrollTeam> list = gameEnrollTeamDao.listGameEnrollTeamByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 赛事报名表 - 团队报名
	 */
	public GameEnrollTeam getGameEnrollTeamById(String id){
		return gameEnrollTeamDao.getGameEnrollTeamById(id);
	}

	/**
	 * 新增
	 * @param item 赛事报名表 - 团队报名
	 */
	public void add(GameEnrollTeam item){
		gameEnrollTeamDao.add(item);
	}

	/**
	 * 修改
	 * @param item 赛事报名表 - 团队报名
	 */
	public void update(GameEnrollTeam item){
		gameEnrollTeamDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		gameEnrollTeamDao.delByIds(ids);
	}

}

