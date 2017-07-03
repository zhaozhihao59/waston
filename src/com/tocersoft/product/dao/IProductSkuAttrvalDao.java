package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.ProductSku;
import com.tocersoft.product.entity.ProductSkuAttrval;
import com.tocersoft.product.dto.ProductSkuAttrvalCondition;

@Repository("productSkuAttrvalDaoImpl")
public interface IProductSkuAttrvalDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductSkuAttrval> listProductSkuAttrvalByPage(RowBounds bounds,@Param("condition") ProductSkuAttrvalCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductSkuAttrvalByPageCount(@Param("condition") ProductSkuAttrvalCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品SKU属性值
	 */
	ProductSkuAttrval getProductSkuAttrvalById(@Param("id") String id);
	
	/**
	 * 根据多个skuID查询
	 */
	List<ProductSkuAttrval> listProductSkuAttrvalBySkuIds(@Param("skuList") List<ProductSku> skuList);

	/**
	 * 新增
	 * @param item 产品SKU属性值
	 */
	void add(ProductSkuAttrval item);

	/**
	 * 修改
	 * @param item 产品SKU属性值
	 */
	void update(ProductSkuAttrval item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 根据skuID集合批量删除
	 * @param ids skuID集合
	 */
	void delBySkuIds(@Param("skuList") List<ProductSku> skuList);

}

