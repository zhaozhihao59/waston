package com.tocersoft.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dao.IExchangeRateDao;
import com.tocersoft.product.dto.ExchangeRateCondition;
import com.tocersoft.product.entity.ExchangeRate;
import com.tocersoft.product.service.IExchangeRateService;

@Service("exchangeRateServiceImpl")
@Transactional(value = "transactionManager")
public class ExchangeRateServiceImpl implements IExchangeRateService{

	@Resource(name = "exchangeRateDaoImpl")
	private IExchangeRateDao exchangeRateDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listExchangeRateByPage(PageResult<ExchangeRate> pageResult,ExchangeRateCondition condition){
		int rows = exchangeRateDao.listExchangeRateByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ExchangeRate> list = exchangeRateDao.listExchangeRateByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 汇率表
	 */
	public ExchangeRate getExchangeRateById(String id){
		return exchangeRateDao.getExchangeRateById(id);
	}

	/**
	 * 新增
	 * @param item 汇率表
	 */
	public void add(ExchangeRate item){
		exchangeRateDao.add(item);
	}

	/**
	 * 修改
	 * @param item 汇率表
	 */
	public void update(ExchangeRate item){
		exchangeRateDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		exchangeRateDao.delByIds(ids);
	}

	@Override
	public ExchangeRate getExchangeRate() {
		return exchangeRateDao.getExchangeRate();
	}

}

