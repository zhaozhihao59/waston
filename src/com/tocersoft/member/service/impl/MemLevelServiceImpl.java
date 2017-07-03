package com.tocersoft.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.member.dao.IMemLevelDao;
import com.tocersoft.member.entity.MemLevel;
import com.tocersoft.member.service.IMemLevelService;

import com.tocersoft.member.dto.MemLevelCondition;

@Service("memLevelServiceImpl")
@Transactional(value = "transactionManager")
public class MemLevelServiceImpl implements IMemLevelService{

	@Resource(name = "memLevelDaoImpl")
	private IMemLevelDao memLevelDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listMemLevelByPage(PageResult<MemLevel> pageResult,MemLevelCondition condition){
		int rows = memLevelDao.listMemLevelByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<MemLevel> list = memLevelDao.listMemLevelByPage(rowBounds,condition);
		pageResult.setResult(list);
	}
	
	/**
	 * 列出所有会员等级
	 */
	@Override
	public List<MemLevel> listAllMemLevel() {
		return memLevelDao.listAllMemLevel();
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 
	 */
	public MemLevel getMemLevelById(String id){
		return memLevelDao.getMemLevelById(id);
	}

	/**
	 * 新增
	 * @param item 
	 */
	public void add(MemLevel item){
		memLevelDao.add(item);
	}

	/**
	 * 修改
	 * @param item 
	 */
	public void update(MemLevel item){
		memLevelDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		memLevelDao.delByIds(ids);
	}

	@Override
	public void delMemberById(String id) {
		if(StringUtils.isNotBlank(id)){
			memLevelDao.delMemberById(id);
		}
				
	}

	@Override
	public void doBatchDelMember(String selIds) {
		String [] sel = selIds.split(",");
		if(StringUtils.isNotBlank(sel.toString())){
			memLevelDao.doBatchDelMember(sel);
		}
		
	}

	@Override
	public boolean checkMemLevelNumExists(MemLevel item) {
		int count = memLevelDao.getCountByLevelNumAndId(item);
		return count > 0;
	}

}

