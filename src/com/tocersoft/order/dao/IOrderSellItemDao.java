package com.tocersoft.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.order.entity.OrderSellItem;
import com.tocersoft.order.dto.OrderSellItemCondition;

@Repository("orderSellItemDaoImpl")
public interface IOrderSellItemDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<OrderSellItem> listOrderSellItemByPage(RowBounds bounds,@Param("condition") OrderSellItemCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listOrderSellItemByPageCount(@Param("condition") OrderSellItemCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 销售订单明细
	 */
	OrderSellItem getOrderSellItemById(@Param("id") String id);
	
	/**
	 * 根据orderId查询
	 * @param orderId
	 */
	List<OrderSellItem> listOrderSellItemByOrderId(@Param("orderId") String orderId);
	
	/**
	 * 查询产品销量
	 * @return
	 */
	int getProductOrderNum(@Param("productId") String productId);

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
	 * 根据订单ID删除订单明细
	 * @param orderId
	 */
	void delByOrderId(@Param("orderId")String orderId);

	/**
	 * 修改订单项产品单价
	 * @param itemId
	 * @param unitPrice
	 */
	public void updatePrice(OrderSellItem item);
}

