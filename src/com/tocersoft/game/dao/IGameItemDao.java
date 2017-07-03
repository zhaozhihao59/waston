package com.tocersoft.game.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.game.entity.GameItem;
import com.tocersoft.game.dto.GameItemCondition;

@Repository("gameItemDaoImpl")
public interface IGameItemDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<GameItem> listGameItemByPage(RowBounds bounds,@Param("condition") GameItemCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listGameItemByPageCount(@Param("condition") GameItemCondition condition);

	/**
	 * 赛事项目列表
	 */
	List<GameItem> listGameItem(@Param("gameId")String gameId);
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 赛事项目
	 */
	GameItem getGameItemById(@Param("id") String id);

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

