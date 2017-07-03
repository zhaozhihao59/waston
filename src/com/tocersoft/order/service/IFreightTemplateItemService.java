package com.tocersoft.order.service;

import java.util.List;

import com.tocersoft.base.page.PageResult;

import com.tocersoft.order.dto.FreightTemplateItemCondition;
import com.tocersoft.order.entity.FreightTemplateItem;

public interface IFreightTemplateItemService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listFreightTemplateItemByPage(PageResult<FreightTemplateItem> pageResult,FreightTemplateItemCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 运费模板表-明细
	 */
	FreightTemplateItem getFreightTemplateItemById(String id);
	
	/**
	 * 查询集合通过条件
	 * @param condition
	 * @return
	 */
	List<FreightTemplateItem> listFreightTemplateItemByCondition(FreightTemplateItemCondition condition);

	/**
	 * 新增
	 * @param item 运费模板表-明细
	 */
	void add(FreightTemplateItem item);

	/**
	 * 修改
	 * @param item 运费模板表-明细
	 */
	void update(FreightTemplateItem item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

