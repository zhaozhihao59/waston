package com.tocersoft.product.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tocersoft.product.entity.ProductCategoryAttribute;
import com.tocersoft.product.entity.ProductCategoryAttributeValue;
import com.tocersoft.product.entity.ProductSkuAttrval;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductCategoryAttributeCondition;

public interface IProductCategoryAttributeService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductCategoryAttributeByPage(PageResult<ProductCategoryAttribute> pageResult,ProductCategoryAttributeCondition condition);
	
	/**
	 * 属性值分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductAttrAndCategoryByPage(PageResult<ProductCategoryAttribute> pageResult,ProductCategoryAttributeCondition condition);
	
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品类别属性
	 */
	ProductCategoryAttribute getProductCategoryAttributeById(String id);
	/**
	 * 根据条件查询
	 * @param condition  查询条件
	 */
	List<ProductCategoryAttribute> listProductCategoryAttributeByCondition(ProductCategoryAttributeCondition condition);

	/**
	 * 新增
	 * @param item 产品类别属性
	 */
	void add(ProductCategoryAttribute item);
	
	/**
	 * 新增属性与属性值
	 * @param item 			类别属性实体
	 * @param attrValues 	传入属性值的集合字符串，解析成多个属性值集合，例如：红,黄,蓝
	 */
	void addAttrAndValue(ProductCategoryAttribute item, String attrValues);

	/**
	 * 修改
	 * @param item 产品类别属性
	 */
	void update(ProductCategoryAttribute item);
	
	/**
	 * 修改属性与属性值
	 * @param item 产品类别属性
	 */
	void updateAttrValue(ProductCategoryAttribute item, String attrValues);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 新增产品类别属性集合
	 * @param itemList
	 */
	void addList(List<ProductCategoryAttribute> itemList);

	/**
	 * 根据产品ID列出该产品所有已选择的销售属性集合
	 * @param productId
	 */
	List<ProductCategoryAttribute> listProductCategoryAttributeByProductId(
			String productId);
	
	/**
	 * 根据产品ID列出该产品所有已选择的销售属性值集合
	 * @param productId
	 */
	public List<List<ProductSkuAttrval>> loadCombineAttrValues(
			String productId);

	/**
	 * 根据类别Id查询
	 */
	List<ProductCategoryAttribute> listProductCategoryAttributeByCategoryId(@Param("categoryId")String categoryId);
	
	/**
	 * 根据类别ID查询
	 * @Param categoryId	类别ID
	 * @Param saleAttr		'0'-非销售属性，'1'-销售属性
	 */
	List<ProductCategoryAttribute> listCategoryAttrByCategoryIdIsSale(String categoryId, String saleAttr);
}

