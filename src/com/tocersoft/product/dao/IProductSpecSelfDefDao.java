package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.ProductSpecSelfDef;
import com.tocersoft.product.dto.ProductSpecSelfDefCondition;

@Repository("productSpecSelfDefDaoImpl")
public interface IProductSpecSelfDefDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductSpecSelfDef> listProductSpecSelfDefByPage(RowBounds bounds,@Param("condition") ProductSpecSelfDefCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductSpecSelfDefByPageCount(@Param("condition") ProductSpecSelfDefCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品自定义规格信息
	 */
	ProductSpecSelfDef getProductSpecSelfDefById(@Param("id") String id);

	/**
	 * 新增
	 * @param item 产品自定义规格信息
	 */
	void add(ProductSpecSelfDef item);

	/**
	 * 修改
	 * @param item 产品自定义规格信息
	 */
	void update(ProductSpecSelfDef item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

	/**
	 * 根据产品ID删除记录
	 * @param 	productId 产品ID
	 */
	void delByProductId(String productId);
	
}

