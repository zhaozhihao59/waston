package com.tocersoft.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.member.dao.IMemTeamDao;
import com.tocersoft.member.entity.MemTeam;
import com.tocersoft.member.service.IMemTeamService;

import com.tocersoft.member.dto.MemTeamCondition;

@Service("memTeamServiceImpl")
@Transactional(value = "transactionManager")
public class MemTeamServiceImpl implements IMemTeamService{

	@Resource(name = "memTeamDaoImpl")
	private IMemTeamDao memTeamDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listMemTeamByPage(PageResult<MemTeam> pageResult,MemTeamCondition condition){
		int rows = memTeamDao.listMemTeamByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<MemTeam> list = memTeamDao.listMemTeamByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 团队/团体信息
	 */
	public MemTeam getMemTeamById(String id){
		return memTeamDao.getMemTeamById(id);
	}

	/**
	 * 新增
	 * @param item 团队/团体信息
	 */
	public void add(MemTeam item){
		memTeamDao.add(item);
	}

	/**
	 * 修改
	 * @param item 团队/团体信息
	 */
	public void update(MemTeam item){
		memTeamDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		memTeamDao.delByIds(ids);
	}
	
	/**
	 *  用于自动补全控件
	 */
	@Override
	public List<MemTeam> listMemTeamByKey(String key) {
		return memTeamDao.listMemTeamByKey(key);
	}

	
	/**
	 * 报名时新增车队
	 * @param item 团队/团体信息
	 */
	@Override
	public void addMemTeamByEnroll(MemTeam item) {
		memTeamDao.addMemTeamByEnroll(item);
	}

}

