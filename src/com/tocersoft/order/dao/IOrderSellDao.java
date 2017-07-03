package com.tocersoft.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.order.dto.OrderSellCondition;
import com.tocersoft.order.entity.OrderSell;

@Repository("orderSellDaoImpl")
public interface IOrderSellDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<OrderSell> listOrderSellByPage(RowBounds bounds,@Param("condition") OrderSellCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listOrderSellByPageCount(@Param("condition") OrderSellCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 销售订单
	 */
	OrderSell getOrderSellById(@Param("id") String id);

	/**
	 * 根据memberId查询
	 * @return 销售订单
	 */
	List<OrderSell> listOrderSellByMemberId(@Param("memberId") String memberId);
	
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
	void changeOrderState(@Param("orderSellId") String orderSellId,@Param("orderState") int orderState);

	/**
	 * 根据订单号获取订单
	 * @param orderNo
	 * @return
	 */
	OrderSell getOrderSellByOrderNo(@Param("orderNo") String orderNo);

}

