package com.tocersoft.product.service;

import java.util.List;

import com.tocersoft.product.entity.ProductWholesaleRange;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductWholesaleRangeCondition;

public interface IProductWholesaleRangeService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductWholesaleRangeByPage(PageResult<ProductWholesaleRange> pageResult,ProductWholesaleRangeCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品批发折扣区间
	 */
	ProductWholesaleRange getProductWholesaleRangeById(String id);

	/**
	 * 新增
	 * @param item 产品批发折扣区间
	 */
	void add(ProductWholesaleRange item);

	/**
	 * 修改
	 * @param item 产品批发折扣区间
	 */
	void update(ProductWholesaleRange item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

