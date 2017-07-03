package com.tocersoft.product.service;

import java.util.List;

import org.json.simple.JSONArray;

import com.tocersoft.product.entity.ProductSku;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductSkuCondition;

public interface IProductSkuService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductSkuByPage(PageResult<ProductSku> pageResult,ProductSkuCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品SKU信息
	 */
	ProductSku getProductSkuById(String id);

	/**
	 * 根据产品ID查询
	 * @param productId 产品ID
	 * @return 产品SKU信息集合
	 */
	List<ProductSku> listProductSkuByProductId(String productId);
	
	/**
	 * 新增
	 * @param item 产品SKU信息
	 */
	void add(ProductSku item);

	/**
	 * 修改
	 * @param item 产品SKU信息
	 */
	void update(ProductSku item);

	/**
	 * 修改
	 * @param item 产品SKU 价格与库存信息
	 */
	void update(JSONArray array,String productId);
	
	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 根据skuID集合批量删除
	 * @param skuList  sku集合
	 */
	void delBySkuIds(List<ProductSku> skuList);

}

