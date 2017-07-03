package com.tocersoft.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.order.entity.OrderCartItem;
import com.tocersoft.order.dto.OrderCartItemCondition;

@Repository("orderCartItemDaoImpl")
public interface IOrderCartItemDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<OrderCartItem> listOrderCartItemByPage(RowBounds bounds,@Param("condition") OrderCartItemCondition condition);

	/**
	 * 根据会员ID查询购物车明细
	 * @param	memberId	会员ID
	 * @return
	 */
	List<OrderCartItem> listOrderCartItemByMemberId(@Param("memberId")String memberId);
	
	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listOrderCartItemByPageCount(@Param("condition") OrderCartItemCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 购物车明细项
	 */
	OrderCartItem getOrderCartItemById(@Param("id") String id);

	/**
	 * 新增
	 * @param item 购物车明细项
	 */
	void add(OrderCartItem item);

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
	void updateNum(@Param("newNum")Double newNum, @Param("itemId")String itemId);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

}

