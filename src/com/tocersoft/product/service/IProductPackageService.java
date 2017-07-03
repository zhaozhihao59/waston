package com.tocersoft.product.service;

import java.util.List;

import com.tocersoft.product.entity.ProductPackage;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductPackageCondition;

public interface IProductPackageService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductPackageByPage(PageResult<ProductPackage> pageResult,ProductPackageCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品包装信息
	 */
	ProductPackage getProductPackageById(String id);
	
	/**
	 * 根据condition查询
	 * @param 
	 * @return 产品包装信息
	 */
	List<ProductPackage> listProductPackageByCondition(ProductPackageCondition condition);

	/**
	 * 新增
	 * @param item 产品包装信息
	 */
	void add(ProductPackage item);

	/**
	 * 修改
	 * @param item 产品包装信息
	 */
	void update(ProductPackage item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

