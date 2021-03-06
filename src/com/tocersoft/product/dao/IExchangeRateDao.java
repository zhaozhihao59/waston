package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.dto.ExchangeRateCondition;
import com.tocersoft.product.entity.ExchangeRate;

@Repository("exchangeRateDaoImpl")
public interface IExchangeRateDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ExchangeRate> listExchangeRateByPage(RowBounds bounds,@Param("condition") ExchangeRateCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listExchangeRateByPageCount(@Param("condition") ExchangeRateCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 汇率表
	 */
	ExchangeRate getExchangeRateById(@Param("id") String id);

	/**
	 * 新增
	 * @param item 汇率表
	 */
	void add(ExchangeRate item);

	/**
	 * 修改
	 * @param item 汇率表
	 */
	void update(ExchangeRate item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

	/**
	 * 获取汇率
	 * @return
	 */
	ExchangeRate getExchangeRate();

}

