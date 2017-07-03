package com.tocersoft.order.service;

import java.util.List;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.order.dto.OrderSellCondition;
import com.tocersoft.order.entity.OrderSell;

public interface IOrderSellService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listOrderSellByPage(PageResult<OrderSell> pageResult,OrderSellCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 销售订单
	 */
	OrderSell getOrderSellById(String id);
	
	/**
	 * 根据memberId查询
	 * @return 销售订单
	 */
	List<OrderSell> listOrderSellByMemberId(String memberId);

	/**
	 * 新增
	 * @param item 销售订单
	 */
	void add(OrderSell item);
	
	/**
	 * 修改
	 * @param item 销售订单
	 */
	void update(OrderSell item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

	/**
	 * 修改订单状态
	 * @param orderSellId 订单ID
	 * @param orderState 订单状态
	 */
	void changeOrderState(String orderSellId, int orderState);

	/**
	 * 根据订单号获取订单
	 * @param orderNo
	 * @return
	 */
	OrderSell getOrderSellByOrderNo(String orderNo);
	
}

