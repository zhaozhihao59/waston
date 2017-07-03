package com.tocersoft.game.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.game.dao.IGameDao;
import com.tocersoft.game.entity.Game;
import com.tocersoft.game.service.IGameService;

import com.tocersoft.game.dto.GameCondition;

@Service("gameServiceImpl")
@Transactional(value = "transactionManager")
public class GameServiceImpl implements IGameService{

	@Resource(name = "gameDaoImpl")
	private IGameDao gameDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listGameByPage(PageResult<Game> pageResult,GameCondition condition){
		int rows = gameDao.listGameByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<Game> list = gameDao.listGameByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 赛事信息
	 */
	public Game getGameById(String id){
		return gameDao.getGameById(id);
	}

	/**
	 * 新增
	 * @param item 赛事信息
	 */
	public void add(Game item){
		gameDao.add(item);
	}

	/**
	 * 修改
	 * @param item 赛事信息
	 */
	public void update(Game item){
		gameDao.update(item);
	}
	
	/**
	 * 修改图片路径 - 大图
	 * @param photo 赛事缩略图 - 大图
	 * @param gameId 赛事ID
	 */
	public void updatePhoto(String photo, String gameId){
		gameDao.updatePhoto(photo,gameId);
	}
	
	/**
	 * 修改图片路径 - 小图
	 * @param photoTo 赛事缩略图 - 小图
	 * @param gameId 赛事ID
	 */
	public void updatePhotoTo(String photoTo, String gameId){
		gameDao.updatePhotoTo(photoTo,gameId);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		gameDao.delByIds(ids);
	}

	/**
	 * 条件查询
	 * @param condition 查询条件类
	 */
	@Override
	public List<Game> listGameByCondition(GameCondition condition) {
		Integer count= gameDao.listGameByPageCount(condition);
		RowBounds bounds=new RowBounds(0,count);
		return gameDao.listGameByPage(bounds,condition);
	}

}

