package com.tocersoft.product.service;

import java.util.List;

import com.tocersoft.product.entity.ProductWeightRange;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductWeightRangeCondition;

public interface IProductWeightRangeService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductWeigthRangeByPage(PageResult<ProductWeightRange> pageResult,ProductWeightRangeCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品包装重量信息
	 */
	ProductWeightRange getProductWeigthRangeById(String id);

	/**
	 * 新增
	 * @param item 产品包装重量信息
	 */
	void add(ProductWeightRange item);

	/**
	 * 修改
	 * @param item 产品包装重量信息
	 */
	void update(ProductWeightRange item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

