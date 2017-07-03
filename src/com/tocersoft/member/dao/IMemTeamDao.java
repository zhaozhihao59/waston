package com.tocersoft.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.member.entity.MemTeam;
import com.tocersoft.member.dto.MemTeamCondition;

@Repository("memTeamDaoImpl")
public interface IMemTeamDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<MemTeam> listMemTeamByPage(RowBounds bounds,@Param("condition") MemTeamCondition condition);
	/**
	 *  用于自动补全控件
	 */
	List<MemTeam> listMemTeamByKey(@Param("key")String key);
	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listMemTeamByPageCount(@Param("condition") MemTeamCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 团队/团体信息
	 */
	MemTeam getMemTeamById(@Param("id") String id);

	/**
	 * 新增
	 * @param item 团队/团体信息
	 */
	void add(MemTeam item);
	
	/**
	 * 报名时新增车队
	 * @param item 团队/团体信息
	 */
	void addMemTeamByEnroll(MemTeam item);

	/**
	 * 修改
	 * @param item 团队/团体信息
	 */
	void update(MemTeam item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

