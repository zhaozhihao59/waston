package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.ProductCategory;
import com.tocersoft.product.dto.ProductCategoryCondition;

@Repository("productCategoryDaoImpl")
public interface IProductCategoryDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductCategory> listProductCategoryByPage(RowBounds bounds,@Param("condition") ProductCategoryCondition condition);

	/**
	 * 查询所有数据
	 */
	List<ProductCategory> listProductCategory();
	
	
	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductCategoryByPageCount(@Param("condition") ProductCategoryCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品类别信息
	 */
	ProductCategory getProductCategoryById(@Param("id") String id);

	/**
	 * 根据父级ID查询下级类别
	 * @param pid 父级ID
	 */
	List<ProductCategory> listProductCatgoryByParentId(@Param("parentId")String parentId);
	
	/**
	 * */
	
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

	/**
	 * 查询所有父类别
	 */
	List<ProductCategory> listTopProductCatgory();
}

