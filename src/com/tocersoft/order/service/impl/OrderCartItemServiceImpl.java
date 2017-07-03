package com.tocersoft.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.order.dao.IOrderCartItemDao;
import com.tocersoft.order.entity.OrderCartItem;
import com.tocersoft.order.service.IOrderCartItemService;

import com.tocersoft.order.dto.OrderCartItemCondition;

@Service("orderCartItemServiceImpl")
@Transactional(value = "transactionManager")
public class OrderCartItemServiceImpl implements IOrderCartItemService{

	@Resource(name = "orderCartItemDaoImpl")
	private IOrderCartItemDao orderCartItemDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listOrderCartItemByPage(PageResult<OrderCartItem> pageResult,OrderCartItemCondition condition){
		int rows = orderCartItemDao.listOrderCartItemByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<OrderCartItem> list = orderCartItemDao.listOrderCartItemByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 购物车明细项
	 */
	public OrderCartItem getOrderCartItemById(String id){
		return orderCartItemDao.getOrderCartItemById(id);
	}

	/**
	 * 新增
	 * @param item 购物车明细项
	 */
	public void add(OrderCartItem item){
		orderCartItemDao.add(item);
	}
	
	/**
	 * 新增或者更新
	 * @param itemList 购物车明细项集合
	 */
	public void addOrUpdate(List<OrderCartItem> itemList, String memberId){
		for(OrderCartItem item : itemList){
			OrderCartItem itemDB = orderCartItemDao.getOrderCartItemById(item.getId());
			// 如果已经存在购物车明细项，则执行更新操作
			if(null != itemDB){
				orderCartItemDao.updateNum(item.getNum(), item.getId());
			}else{
				item.setMemberId(memberId);
				orderCartItemDao.add(item);
			}
		}
	}

	/**
	 * 修改
	 * @param item 购物车明细项
	 */
	public void update(OrderCartItem item){
		orderCartItemDao.update(item);
	}
	
	/**
	 * 更新购物车明细项的数量
	 * @param newNum	新的数量
	 * @param itemId	购物车明细项ID
	 */
	public void updateNum(Double newNum, String itemId){
		orderCartItemDao.updateNum(newNum, itemId);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		orderCartItemDao.delByIds(ids);
	}

	/**
	 * 根据会员ID查询购物车明细
	 * @param	memberId	会员ID
	 * @return
	 */
	@Override
	public List<OrderCartItem> listOrderCartItemByMemberId(String memberId){
		return orderCartItemDao.listOrderCartItemByMemberId(memberId);
	}

}

