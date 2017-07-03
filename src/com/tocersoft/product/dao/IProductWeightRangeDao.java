package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.ProductWeightRange;
import com.tocersoft.product.dto.ProductWeightRangeCondition;

@Repository("productWeightRangeDaoImpl")
public interface IProductWeightRangeDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductWeightRange> listProductWeigthRangeByPage(RowBounds bounds,@Param("condition") ProductWeightRangeCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductWeigthRangeByPageCount(@Param("condition") ProductWeightRangeCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品包装重量信息
	 */
	ProductWeightRange getProductWeigthRangeById(@Param("id") String id);

	/**
	 * 新增
	 * @param item 产品包装重量信息
	 */
	void add(ProductWeightRange item);

	/**
	 * 修改
	 * @param item 产品包装重量信息
	 */
	void update(ProductWeightRange item);

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

