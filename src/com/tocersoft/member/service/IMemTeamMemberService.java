package com.tocersoft.member.service;

import java.util.List;

import com.tocersoft.member.entity.MemTeamMember;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.member.dto.MemTeamMemberCondition;

public interface IMemTeamMemberService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listMemTeamMemberByPage(PageResult<MemTeamMember> pageResult,MemTeamMemberCondition condition);
	
	/**
	 * 根据TeamID查询
	 */
	List<MemTeamMember> listMemTeamMemberByTeamId(String teamId);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 团队下会员信息
	 */
	MemTeamMember getMemTeamMemberById(String id);

	/**
	 * 新增
	 * @param item 团队下会员信息
	 */
	void add(MemTeamMember item);
	
	/**
	 * 根据ID集合批量新增
	 */
	void addTeamMember(MemTeamMember item);

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

}

