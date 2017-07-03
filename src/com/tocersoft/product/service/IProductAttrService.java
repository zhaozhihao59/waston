package com.tocersoft.product.service;

import java.util.List;

import com.tocersoft.product.entity.ProductAttr;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductAttrCondition;

public interface IProductAttrService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductAttrByPage(PageResult<ProductAttr> pageResult,ProductAttrCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品属性
	 */
	ProductAttr getProductAttrById(String id);

	/**
	 * 新增
	 * @param item 产品属性
	 */
	void add(ProductAttr item);

	/**
	 * 修改
	 * @param item 产品属性
	 */
	void update(ProductAttr item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

	/**
	 * 根据产品ID查询产品属性记录
	 */
	List<ProductAttr> listProductAttrByPid(String productId);
}

