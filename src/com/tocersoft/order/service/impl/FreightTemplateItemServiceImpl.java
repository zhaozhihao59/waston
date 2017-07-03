package com.tocersoft.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;

import com.tocersoft.order.dao.IFreightTemplateItemDao;
import com.tocersoft.order.dto.FreightTemplateItemCondition;
import com.tocersoft.order.entity.FreightTemplateItem;
import com.tocersoft.order.service.IFreightTemplateItemService;

@Service("freightTemplateItemServiceImpl")
@Transactional(value = "transactionManager")
public class FreightTemplateItemServiceImpl implements IFreightTemplateItemService{

	@Resource(name = "freightTemplateItemDaoImpl")
	private IFreightTemplateItemDao freightTemplateItemDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listFreightTemplateItemByPage(PageResult<FreightTemplateItem> pageResult,FreightTemplateItemCondition condition){
		int rows = freightTemplateItemDao.listFreightTemplateItemByPageCount(condition);
		pageResult.setRows(rows);
		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<FreightTemplateItem> list = freightTemplateItemDao.listFreightTemplateItemByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 运费模板表-明细
	 */
	public FreightTemplateItem getFreightTemplateItemById(String id){
		return freightTemplateItemDao.getFreightTemplateItemById(id);
	}

	/**
	 * 查询集合通过条件
	 * @param condition
	 * @return
	 */
	public List<FreightTemplateItem> listFreightTemplateItemByCondition(FreightTemplateItemCondition condition){
		return freightTemplateItemDao.listFreightTemplateItemByCondition(condition);
	}
	
	/**
	 * 新增
	 * @param item 运费模板表-明细
	 */
	public void add(FreightTemplateItem item){
		freightTemplateItemDao.add(item);
	}

	/**
	 * 修改
	 * @param item 运费模板表-明细
	 */
	public void update(FreightTemplateItem item){
		freightTemplateItemDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		freightTemplateItemDao.delByIds(ids);
	}

}

