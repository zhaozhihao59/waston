package com.tocersoft.product.service;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dto.ExchangeRateCondition;
import com.tocersoft.product.entity.ExchangeRate;

public interface IExchangeRateService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listExchangeRateByPage(PageResult<ExchangeRate> pageResult,ExchangeRateCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 汇率表
	 */
	ExchangeRate getExchangeRateById(String id);

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

