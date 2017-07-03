package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.ProductSku;
import com.tocersoft.product.dto.ProductSkuCondition;

@Repository("productSkuDaoImpl")
public interface IProductSkuDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductSku> listProductSkuByPage(RowBounds bounds,@Param("condition") ProductSkuCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductSkuByPageCount(@Param("condition") ProductSkuCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品SKU信息
	 */
	ProductSku getProductSkuById(@Param("id") String id);
	
	/**
	 * 根据产品ID查询
	 * @param productId 产品ID
	 * @return 产品SKU信息集合
	 */
	List<ProductSku> listProductSkuByProductId(@Param("productId")String productId);

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
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

	/**
	 * 根据skuID集合批量删除
	 * @param skuList  sku集合
	 */
	void delBySkuIds(@Param("skuList") List<ProductSku> skuList);
	
	/**
	 * 根据产品ID删除记录
	 * @param 	productId 产品ID
	 */
	void delByProductId(String productId);
}


