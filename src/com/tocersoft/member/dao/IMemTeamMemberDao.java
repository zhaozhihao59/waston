package com.tocersoft.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.member.entity.MemTeamMember;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.dto.MemTeamMemberCondition;

@Repository("memTeamMemberDaoImpl")
public interface IMemTeamMemberDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<MemTeamMember> listMemTeamMemberByPage(RowBounds bounds,@Param("condition") MemTeamMemberCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listMemTeamMemberByPageCount(@Param("condition") MemTeamMemberCondition condition);
	
	/**
	 * 根据TeamID查询
	 */
	List<MemTeamMember> listMemTeamMemberByTeamId(@Param("teamId")String teamId);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 团队下会员信息
	 */
	MemTeamMember getMemTeamMemberById(@Param("id") String id);
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 团队下会员信息
	 */
	MemTeamMember getMemTeamMemberByMemberIdAndTeamId(@Param("memberId")String memberId,@Param("teamId")String teamId);
	/**
	 * 新增
	 * @param item 团队下会员信息
	 */
	void add(MemTeamMember item);
	
	/**
	 * 新增批量新增
	 */
	void addTeamMember(@Param("list") List<MemTeamMember> list );

	/**
	 * 修改
	 * @param item 团队下会员信息
	 */
	void update(MemTeamMember item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 根据teamID删除
	 */
	void delByMemberIdAndTeamId(String memberId,String teamId);
	
}

