package com.tocersoft.product.service;

import java.util.List;

import com.tocersoft.product.entity.ProductInventory;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductInventoryCondition;

public interface IProductInventoryService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductInventoryByPage(PageResult<ProductInventory> pageResult,ProductInventoryCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品库存表
	 */
	ProductInventory getProductInventoryById(String id);

	/**
	 * 新增
	 * @param item 产品库存表
	 */
	void add(ProductInventory item);

	/**
	 * 修改
	 * @param item 产品库存表
	 */
	void update(ProductInventory item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

