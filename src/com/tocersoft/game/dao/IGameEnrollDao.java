package com.tocersoft.game.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.game.entity.GameEnroll;
import com.tocersoft.game.dto.GameEnrollCondition;

@Repository("gameEnrollDaoImpl")
public interface IGameEnrollDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<GameEnroll> listGameEnrollByPage(RowBounds bounds,@Param("condition") GameEnrollCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listGameEnrollByPageCount(@Param("condition") GameEnrollCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 赛事报名表
	 */
	GameEnroll getGameEnrollById(@Param("id") String id);

	/**
	 * 根据会员ID车队ID查询，判断重复报名
	 * @param id 主键
	 * @return 赛事报名表
	 */
	GameEnroll getGameEnrollByMemberIdAndGameItemId(@Param("memberId")String memberId,@Param("gameItemId")String gameItemId);
	
	/**
	 * 新增
	 * @param item 赛事报名表
	 */
	void add(GameEnroll item);

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

