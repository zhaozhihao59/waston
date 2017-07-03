package com.tocersoft.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.DoubleUtil;
import com.tocersoft.order.dao.IOrderSellDao;
import com.tocersoft.order.dao.IOrderSellItemDao;
import com.tocersoft.order.dto.OrderSellItemCondition;
import com.tocersoft.order.entity.OrderSell;
import com.tocersoft.order.entity.OrderSellItem;
import com.tocersoft.order.service.IOrderSellItemService;
import com.tocersoft.product.dao.IExchangeRateDao;
import com.tocersoft.product.entity.ExchangeRate;

@Service("orderSellItemServiceImpl")
@Transactional(value = "transactionManager")
public class OrderSellItemServiceImpl implements IOrderSellItemService{

	@Resource(name = "orderSellItemDaoImpl")
	private IOrderSellItemDao orderSellItemDao;
	@Resource(name = "orderSellDaoImpl")
	private IOrderSellDao orderSellDao;
	@Resource(name = "exchangeRateDaoImpl")
	private IExchangeRateDao exchangeRateDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listOrderSellItemByPage(PageResult<OrderSellItem> pageResult,OrderSellItemCondition condition){
		int rows = orderSellItemDao.listOrderSellItemByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<OrderSellItem> list = orderSellItemDao.listOrderSellItemByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 销售订单明细
	 */
	public OrderSellItem getOrderSellItemById(String id){
		return orderSellItemDao.getOrderSellItemById(id);
	}
	
	/**
	 * 根据orderId查询
	 * @param orderId
	 */
	public List<OrderSellItem> listOrderSellItemByOrderId(String orderId){
		return orderSellItemDao.listOrderSellItemByOrderId(orderId);
	}

	/**
	 * 查询销量排前10产品信息(产品ID)
	 */
	public List<OrderSellItem> listBySales(){
		return orderSellItemDao.listBySales();
	}
	
	/**
	 * 新增
	 * @param item 销售订单明细
	 */
	public void add(OrderSellItem item){
		orderSellItemDao.add(item);
	}
	
	/**
	 * 批量新增订单明细集合
	 * @param sellItemList	订单明细集合
	 * @param orderSellId	订单ID
	 */
	public void addBatch(List<OrderSellItem> sellItemList, String orderSellId) {
		for(OrderSellItem sellItem : sellItemList){
			sellItem.setOrderId(orderSellId);
			orderSellItemDao.add(sellItem);
		}
	}

	/**
	 * 修改
	 * @param item 销售订单明细
	 */
	public void update(OrderSellItem item){
		orderSellItemDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		orderSellItemDao.delByIds(ids);
	}

	/**
	 * 修改订单项产品单价(将订单总额返回)
	 * @param itemId
	 * @param unitPrice
	 */
	public double updatePrice(OrderSellItem item){
		ExchangeRate rate = exchangeRateDao.getExchangeRate();
		item.setExchangeRateUnitPrice(DoubleUtil.mul(item.getUnitPrice(),rate.getNzdRate()));
		orderSellItemDao.updatePrice(item);
		double amountProduct = 0;
		double exchangeRateAmountProduct = 0;
		// 重新计算订单的总额和产品总额并修改
		List<OrderSellItem> orderSellItems = orderSellItemDao.listOrderSellItemByOrderId(item.getOrderId());
		for(OrderSellItem orderSellItem : orderSellItems){
			amountProduct += DoubleUtil.mul(orderSellItem.getUnitPrice(),orderSellItem.getNum());
			exchangeRateAmountProduct += DoubleUtil.mul(orderSellItem.getExchangeRateUnitPrice(),orderSellItem.getNum());
		}
		OrderSell orderSell = orderSellDao.getOrderSellById(item.getOrderId());
		orderSell.setAmountProduct(amountProduct);
		orderSell.setExchangeRateAmountProduct(exchangeRateAmountProduct);
		orderSell.setAmountTotal(orderSell.getAmountFreight() + amountProduct);
		orderSell.setExchangeRateAmountTotal(orderSell.getExchangeRateAmountFreight() + exchangeRateAmountProduct);
		orderSellDao.update(orderSell);
		return orderSell.getAmountTotal();
	}
}

