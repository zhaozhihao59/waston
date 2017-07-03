package com.tocersoft.product.service;

import java.util.List;

import com.tocersoft.product.entity.ProductCategoryAttributeValue;
import com.tocersoft.product.entity.ProductSkuAttrval;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductCategoryAttributeValueCondition;

public interface IProductCategoryAttributeValueService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductCategoryAttributeValueByPage(PageResult<ProductCategoryAttributeValue> pageResult,ProductCategoryAttributeValueCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品类别属性
	 */
	ProductCategoryAttributeValue getProductCategoryAttributeValueById(String id);
	
	/**
	 */
	List<ProductCategoryAttributeValue> listProductCategoryAttributeValueByIds(List<ProductSkuAttrval> skuAttrvals);
	
	/**
	 * 根据属性ID查询
	 * @param 属性id 
	 * @return 产品类别属性值集合
	 */
	List<ProductCategoryAttributeValue> listProductCategoryAttributeValueByCatePubAttrId(String catePubAttrId);

	/**
	 * 新增
	 * @param item 产品类别属性
	 */
	void add(ProductCategoryAttributeValue item);

	/**
	 * 修改
	 * @param item 产品类别属性
	 */
	void update(ProductCategoryAttributeValue item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

	/**
	 * 根据发布类目属性编号删除相应的属性值
	 */
	void delByCatePubAttrId(String CatePubAttrId);
}

