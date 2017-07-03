package com.tocersoft.member.service;

import java.util.List;

import com.tocersoft.member.entity.MemTeam;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.member.dto.MemTeamCondition;

public interface IMemTeamService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listMemTeamByPage(PageResult<MemTeam> pageResult,MemTeamCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 团队/团体信息
	 */
	MemTeam getMemTeamById(String id);
	 
	/**
	 *  用于自动补全控件
	 */
	List<MemTeam> listMemTeamByKey(String key);
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

