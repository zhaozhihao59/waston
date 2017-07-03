package com.tocersoft.product.service;

import java.util.List;

import com.tocersoft.product.entity.Product;
import com.tocersoft.product.entity.ProductCategory;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductCategoryCondition;

public interface IProductCategoryService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductCategoryByPage(PageResult<ProductCategory> pageResult,ProductCategoryCondition condition);

	/**
	 * 查询所有数据
	 */
	List<ProductCategory> listProductCategory();
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品类别信息
	 */
	ProductCategory getProductCategoryById(String id);

	/**
	 * 根据父级ID查询下级类别
	 * @param pid 父级ID
	 */
	List<ProductCategory> listProductCatgoryByParentId(String pid);
	
	/**
	 * 新增
	 * @param item 产品类别信息
	 */
	void add(ProductCategory item);

	/**
	 * 修改
	 * @param item 产品类别信息
	 */
	void update(ProductCategory item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/** 导入产品类别 */
	public String importXls(String path);
	
	/**
	 * 查询所有父类别
	 */
	List<ProductCategory> listTopProductCatgory();
}

