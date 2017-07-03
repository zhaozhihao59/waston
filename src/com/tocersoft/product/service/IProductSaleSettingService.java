package com.tocersoft.product.service;

import java.util.List;

import com.tocersoft.product.entity.ProductSaleSetting;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductSaleSettingCondition;

public interface IProductSaleSettingService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductSaleSettingByPage(PageResult<ProductSaleSetting> pageResult,ProductSaleSettingCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品销售属性设置信息
	 */
	ProductSaleSetting getProductSaleSettingById(String id);

	/**
	 * 新增
	 * @param item 产品销售属性设置信息
	 */
	void add(ProductSaleSetting item);

	/**
	 * 修改
	 * @param item 产品销售属性设置信息
	 */
	void update(ProductSaleSetting item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

