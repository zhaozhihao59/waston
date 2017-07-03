package com.tocersoft.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.member.dao.IMemberDao;
import com.tocersoft.member.entity.Member;
import com.tocersoft.order.dao.IOrderSellDao;
import com.tocersoft.order.dao.IOrderSellItemDao;
import com.tocersoft.order.dto.OrderSellCondition;
import com.tocersoft.order.entity.OrderSell;
import com.tocersoft.order.entity.OrderSellItem;
import com.tocersoft.order.service.IOrderSellService;

@Service("orderSellServiceImpl")
@Transactional(value = "transactionManager")
public class OrderSellServiceImpl implements IOrderSellService{

	@Resource(name = "orderSellDaoImpl")
	private IOrderSellDao orderSellDao;
	@Resource(name="memberDaoImpl")
	private IMemberDao memberDao;
	@Resource(name = "orderSellItemDaoImpl")
	private IOrderSellItemDao orderSellItemDao;
	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listOrderSellByPage(PageResult<OrderSell> pageResult,OrderSellCondition condition){
		int rows = orderSellDao.listOrderSellByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<OrderSell> list = orderSellDao.listOrderSellByPage(rowBounds,condition);
		for(OrderSell orderSell : list){
			// 设置会员名称
			Member member = memberDao.getMemberById(orderSell.getMemberId());
			orderSell.setMemberName(member.getName());
			// 将订单项放入订单
			List<OrderSellItem> orderSellItems = orderSellItemDao.listOrderSellItemByOrderId(orderSell.getId());
			orderSell.setOrderSellItems(orderSellItems);
		}
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 销售订单
	 */
	public OrderSell getOrderSellById(String id){
		OrderSell orderSell = orderSellDao.getOrderSellById(id);
		Member member = memberDao.getMemberById(orderSell.getMemberId());
		orderSell.setMemberName(member.getName());
		return orderSell;
	}
	
	/**
	 * 根据memberId查询
	 * @return 销售订单
	 */
	public List<OrderSell> listOrderSellByMemberId(String memberId){
		return orderSellDao.listOrderSellByMemberId(memberId);
	}

	/**
	 * 新增
	 * @param item 销售订单
	 */
	public void add(OrderSell item){
		orderSellDao.add(item);
	}

	/**
	 * 修改
	 * @param item 销售订单
	 */
	public void update(OrderSell item){
		orderSellDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		orderSellDao.delByIds(ids);
		// 删除订单项
		for(String id : ids){
			orderSellItemDao.delByOrderId(id);
		}
	}

	/**
	 * 修改订单状态
	 * @param orderSellId 订单ID
	 * @param orderState 订单状态
	 */
	@Override
	public void changeOrderState(String orderSellId, int orderState) {
		orderSellDao.changeOrderState(orderSellId,orderState);
	}

	@Override
	public OrderSell getOrderSellByOrderNo(String orderNo) {
		return orderSellDao.getOrderSellByOrderNo(orderNo);
	}
}

