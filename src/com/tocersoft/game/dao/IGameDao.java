package com.tocersoft.game.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.game.entity.Game;
import com.tocersoft.game.dto.GameCondition;

@Repository("gameDaoImpl")
public interface IGameDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Game> listGameByPage(RowBounds bounds,@Param("condition") GameCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listGameByPageCount(@Param("condition") GameCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 赛事信息
	 */
	Game getGameById(@Param("id") String id);

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
	void updatePhoto(@Param("photo")String photo, @Param("gameId")String gameId);
	
	/**
	 * 修改图片路径 - 小图
	 * @param photoTo 赛事缩略图 - 小图
	 * @param gameId 赛事ID
	 */
	void updatePhotoTo(@Param("photoTo")String photoTo, @Param("gameId")String gameId);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

