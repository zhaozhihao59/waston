package com.tocersoft.product.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tocersoft.product.entity.ProductSku;
import com.tocersoft.product.entity.ProductSkuAttrval;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductSkuAttrvalCondition;

public interface IProductSkuAttrvalService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductSkuAttrvalByPage(PageResult<ProductSkuAttrval> pageResult,ProductSkuAttrvalCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品SKU属性值
	 */
	ProductSkuAttrval getProductSkuAttrvalById(String id);

	/**
	 * 根据多个skuID查询
	 */
	List<ProductSkuAttrval> listProductSkuAttrvalBySkuIds(@Param("skuList") List<ProductSku> skuList);
	
	/**
	 * 新增
	 * @param item 产品SKU属性值
	 */
	void add(ProductSkuAttrval item);

	/**
	 * 修改
	 * @param item 产品SKU属性值
	 */
	void update(ProductSkuAttrval item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 根据skuID集合批量删除
	 * @param ids skuID集合
	 */
	void delBySkuIds(List<ProductSku> skuList);

}

