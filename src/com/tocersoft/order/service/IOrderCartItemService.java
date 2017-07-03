package com.tocersoft.order.service;

import java.util.List;

import com.tocersoft.order.entity.OrderCartItem;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.order.dto.OrderCartItemCondition;

public interface IOrderCartItemService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listOrderCartItemByPage(PageResult<OrderCartItem> pageResult,OrderCartItemCondition condition);
	
	/**
	 * 根据会员ID查询购物车明细
	 * @param	memberId	会员ID
	 * @return
	 */
	List<OrderCartItem> listOrderCartItemByMemberId(String memberId);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 购物车明细项
	 */
	OrderCartItem getOrderCartItemById(String id);

	/**
	 * 新增
	 * @param item 购物车明细项
	 */
	void add(OrderCartItem item);
	
	/**
	 * 新增或者更新
	 * @param itemList 购物车明细项集合
	 * @param memberId 会员ID
	 */
	void addOrUpdate(List<OrderCartItem> itemList, String memberId);

	/**
	 * 修改
	 * @param item 购物车明细项
	 */
	void update(OrderCartItem item);
	
	/**
	 * 更新购物车明细项的数量
	 * @param newNum	新的数量
	 * @param itemId	购物车明细项ID
	 */
	void updateNum(Double newNum, String itemId);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

