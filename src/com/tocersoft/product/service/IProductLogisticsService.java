package com.tocersoft.product.service;

import java.util.List;

import com.tocersoft.product.entity.ProductLogistics;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductLogisticsCondition;

public interface IProductLogisticsService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductLogisticsByPage(PageResult<ProductLogistics> pageResult,ProductLogisticsCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品物流信息表
	 */
	ProductLogistics getProductLogisticsById(String id);
	
	/**
	 * 根据产品ID查询
	 * @param productId
	 * @return
	 */
	List<ProductLogistics> getProductLogisticsByProductId(String productId);

	/**
	 * 新增
	 * @param item 产品物流信息表
	 */
	void add(ProductLogistics item);

	/**
	 * 修改
	 * @param item 产品物流信息表
	 */
	void update(ProductLogistics item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

