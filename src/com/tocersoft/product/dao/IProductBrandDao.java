package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.ProductBrand;
import com.tocersoft.product.dto.ProductBrandCondition;

@Repository("productBrandDaoImpl")
public interface IProductBrandDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductBrand> listProductBrandByPage(RowBounds bounds,@Param("condition") ProductBrandCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductBrandByPageCount(@Param("condition") ProductBrandCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品品牌信息
	 */
	ProductBrand getProductBrandById(@Param("id") String id);

	/**
	 * 新增
	 * @param item 产品品牌信息
	 */
	void add(ProductBrand item);

	/**
	 * 修改
	 * @param item 产品品牌信息
	 */
	void update(ProductBrand item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 查询所有
	 */
	List<ProductBrand> listProductBrand(@Param("condition") ProductBrandCondition condition);
	
	/**
	 * 根据品牌类别查询
	 */
	List<ProductBrand> listProductBrandByBrandTypeId(@Param("brandTypeId") String brandTypeId);
}

