package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.ProductLogistics;
import com.tocersoft.product.dto.ProductLogisticsCondition;

@Repository("productLogisticsDaoImpl")
public interface IProductLogisticsDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductLogistics> listProductLogisticsByPage(RowBounds bounds,@Param("condition") ProductLogisticsCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductLogisticsByPageCount(@Param("condition") ProductLogisticsCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品物流信息表
	 */
	ProductLogistics getProductLogisticsById(@Param("id") String id);
	
	/**
	 * 根据产品ID查询
	 * @param productId
	 * @return
	 */
	List<ProductLogistics> getProductLogisticsByProductId(String productId);

	/**
	 * 新增
	 * @param item 产品物流信息表
	 */
	void add(ProductLogistics item);

	/**
	 * 修改
	 * @param item 产品物流信息表
	 */
	void update(ProductLogistics item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

