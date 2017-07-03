package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.ProductAttrValue;
import com.tocersoft.product.entity.ProductCategoryAttribute;
import com.tocersoft.product.dto.ProductAttrValueCondition;
import com.tocersoft.product.dto.ProductCategoryAttributeCondition;

@Repository("productAttrValueDaoImpl")
public interface IProductAttrValueDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductAttrValue> listProductAttrValueByPage(RowBounds bounds,@Param("condition") ProductAttrValueCondition condition);
	
	/**
	 * 查询产品已有的属性数据
	 * @param categoryId 	类别ID
	 * @param productId 	产品ID
	 */
	List<ProductAttrValue> listPruductAttrAndCategory(@Param("categoryId")String categoryId,@Param("productId")String productId);
	
	/**
	 * 查询属性总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listPruductAttrAndCategoryCount(@Param("condition") ProductAttrValueCondition condition);
	
	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductAttrValueByPageCount(@Param("condition") ProductAttrValueCondition condition);
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品属性值
	 */
	ProductAttrValue getProductAttrValueById(@Param("id") String id);
	
	/**
	 * 根据产品属性ID查询产品属性值是否存在
	 * @param attrId
	 * @return
	 */
	ProductAttrValue getProductAttrValueByAttrId(@Param("attrId")String attrId);

	/**
	 * 新增
	 * @param item 产品属性值
	 */
	void add(ProductAttrValue item);

	/**
	 * 修改
	 * @param item 产品属性值
	 */
	void update(ProductAttrValue item);
	
	/**
	 * 更新ProductAttrValue的attrValueId
	 * @param attrValueId	需要更新的attrValueId
	 * @param id			当前的productAttrValue
	 */
	void updateAttrValueId(@Param("attrValueId")String attrValueId, @Param("id")String id);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

	/**
	 * 根据属性Id查询相应属性值
	 */
	List<ProductAttrValue> listProductAttrValueByProductAttrId(String productAttrId);
}

