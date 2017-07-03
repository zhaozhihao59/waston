package com.tocersoft.member.service;

import java.util.List;

import com.tocersoft.member.entity.MemLevel;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.member.dto.MemLevelCondition;

public interface IMemLevelService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listMemLevelByPage(PageResult<MemLevel> pageResult,MemLevelCondition condition);
	
	/**
	 * 列出所有会员等级
	 */
	List<MemLevel> listAllMemLevel();

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 
	 */
	MemLevel getMemLevelById(String id);

	/**
	 * 新增
	 * @param item 
	 */
	void add(MemLevel item);

	/**
	 * 修改
	 * @param item 
	 */
	void update(MemLevel item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 根据ID删除会员
	 * @param id
	 */
	public void delMemberById(String id);
	
	/**
	 * 批量删除会员
	 * @param selIds
	 */
	public void doBatchDelMember(String selIds);

	/**
	 * 检查会员等级序号是否存在
	 * @param item
	 * @return
	 */
	boolean checkMemLevelNumExists(MemLevel item);

}

