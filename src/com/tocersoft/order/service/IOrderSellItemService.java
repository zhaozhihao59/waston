package com.tocersoft.order.service;

import java.util.List;

import com.tocersoft.order.entity.OrderSellItem;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.order.dto.OrderSellItemCondition;

public interface IOrderSellItemService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listOrderSellItemByPage(PageResult<OrderSellItem> pageResult,OrderSellItemCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 销售订单明细
	 */
	OrderSellItem getOrderSellItemById(String id);
	
	/**
	 * 根据orderId查询
	 * @param orderId
	 */
	List<OrderSellItem> listOrderSellItemByOrderId(String orderId);

	/**
	 * 查询销量排前10产品信息(产品ID)
	 */
	List<OrderSellItem> listBySales();
	
	/**
	 * 新增
	 * @param item 销售订单明细
	 */
	void add(OrderSellItem item);
	
	/**
	 * 批量新增订单明细集合
	 * @param sellItemList	订单明细集合
	 * @param orderSellId	订单ID
	 */
	void addBatch(List<OrderSellItem> sellItemList, String orderSellId);

	/**
	 * 修改
	 * @param item 销售订单明细
	 */
	void update(OrderSellItem item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

	/**
	 * 修改订单项产品单价,返回订单总额
	 * @param itemId
	 * @param unitPrice
	 */
	double updatePrice(OrderSellItem item);
}

