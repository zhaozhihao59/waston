 
package com.tocersoft.product.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.ProductCategoryAttribute;
import com.tocersoft.product.dto.ProductCategoryAttributeCondition;

@Repository("productCategoryAttributeDaoImpl")
public interface IProductCategoryAttributeDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductCategoryAttribute> listProductCategoryAttributeByPage(RowBounds bounds,@Param("condition") ProductCategoryAttributeCondition condition);
	
	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductCategoryAttributeByPageCount(@Param("condition") ProductCategoryAttributeCondition condition);

	/**
	 * 查询产品属性
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductCategoryAttribute> listProductAttrAndCategoryByPage(RowBounds bounds,@Param("condition") ProductCategoryAttributeCondition condition);
	
	/**
	 * 查询属性值总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductAttrAndCategoryCount(@Param("condition") ProductCategoryAttributeCondition condition);
	
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品类别属性
	 */
	ProductCategoryAttribute getProductCategoryAttributeById(@Param("id") String id);
	
	/**
	 * 根据多个ID查询
	 */
	List<ProductCategoryAttribute> listProductCategoryAttributeByIds(@Param("idList") List idList);
	
	/**
	 * 根据查询条件类查询
	 * @param condition 查询条件类
	 */
	List<ProductCategoryAttribute> listProductCategoryAttributeByCondition(@Param("condition") ProductCategoryAttributeCondition condition);

	/**
	 * 新增
	 * @param item 产品类别属性
	 */
	void add(ProductCategoryAttribute item);

	/**
	 * 修改产品类别属性全部属性
	 * @param item 产品类别属性
	 */
	void updateAll(ProductCategoryAttribute item);
	
	/**
	 * 修改
	 * @param item 产品类别属性
	 */
	void update(ProductCategoryAttribute item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 根据类别Id查询
	 */
	List<ProductCategoryAttribute> listProductCategoryAttributeByCategoryId(@Param("categoryId")String categoryId);
	
	/**
	 * 根据类别ID查询
	 */
	List<ProductCategoryAttribute> listCategoryAttrByCategoryIdIsSale(@Param("categoryId")String categoryId, @Param("saleAttr")String saleAttr);

}

