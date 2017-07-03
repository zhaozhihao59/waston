package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.ProductAttr;
import com.tocersoft.product.dto.ProductAttrCondition;

@Repository("productAttrDaoImpl")
public interface IProductAttrDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductAttr> listProductAttrByPage(RowBounds bounds,@Param("condition") ProductAttrCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductAttrByPageCount(@Param("condition") ProductAttrCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品属性
	 */
	ProductAttr getProductAttrById(@Param("id") String id);
	
	/**
	 * 根据产品ID与属性ID查询产品属性是否存在
	 * 说明：
	 * 1、在做产品属性管理时，如果当前产品的属性值已经存在，则修改
	 * 2、在做产品属性管理时，如果当前产品的属性值不存在，则新增
	 * 
	 * @param productId
	 * @param attrId
	 * @return
	 */
	ProductAttr getProductAttrByPidAndAttrId(@Param("productId") String productId, @Param("attrId")String attrId);

	/**
	 * 新增
	 * @param item 产品属性
	 */
	void add(ProductAttr item);

	/**
	 * 修改
	 * @param item 产品属性
	 */
	void update(ProductAttr item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

	/**
	 * 根据产品ID删除产品属性记录
	 * @param 	productId 产品ID
	 */
	void delByProductId(String productId);
	
	/**
	 * 根据产品ID查询产品属性记录
	 */
	List<ProductAttr> listProductAttrByPid(String productId);
}

