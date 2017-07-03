package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.ProductPackage;
import com.tocersoft.product.dto.ProductPackageCondition;

@Repository("productPackageDaoImpl")
public interface IProductPackageDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductPackage> listProductPackageByPage(RowBounds bounds,@Param("condition") ProductPackageCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductPackageByPageCount(@Param("condition") ProductPackageCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品包装信息
	 */
	ProductPackage getProductPackageById(@Param("id") String id);

	/**
	 * 根据condition查询
	 * @param 
	 * @return 产品包装信息
	 */
	List<ProductPackage> listProductPackageByCondition(@Param("condition") ProductPackageCondition condition);
	
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

	/**
	 * 根据产品ID删除产品属性记录
	 * @param 	productId 产品ID
	 */
	void delByProductId(String productId);
}

