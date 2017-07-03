package com.tocersoft.product.service;

import java.util.List;

import com.tocersoft.product.entity.ProductSpecSelfDef;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductSpecSelfDefCondition;

public interface IProductSpecSelfDefService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductSpecSelfDefByPage(PageResult<ProductSpecSelfDef> pageResult,ProductSpecSelfDefCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品自定义规格信息
	 */
	ProductSpecSelfDef getProductSpecSelfDefById(String id);

	/**
	 * 新增
	 * @param item 产品自定义规格信息
	 */
	void add(ProductSpecSelfDef item);

	/**
	 * 修改
	 * @param item 产品自定义规格信息
	 */
	void update(ProductSpecSelfDef item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

