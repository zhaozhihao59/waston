package com.tocersoft.game.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.game.entity.GameEnrollTeam;
import com.tocersoft.game.dto.GameEnrollTeamCondition;

@Repository("gameEnrollTeamDaoImpl")
public interface IGameEnrollTeamDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<GameEnrollTeam> listGameEnrollTeamByPage(RowBounds bounds,@Param("condition") GameEnrollTeamCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listGameEnrollTeamByPageCount(@Param("condition") GameEnrollTeamCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 赛事报名表 - 团队报名
	 */
	GameEnrollTeam getGameEnrollTeamById(@Param("id") String id);

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

