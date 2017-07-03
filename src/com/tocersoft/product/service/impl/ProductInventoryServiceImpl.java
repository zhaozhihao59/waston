package com.tocersoft.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dao.IProductInventoryDao;
import com.tocersoft.product.entity.ProductInventory;
import com.tocersoft.product.service.IProductInventoryService;

import com.tocersoft.product.dto.ProductInventoryCondition;

@Service("productInventoryServiceImpl")
@Transactional(value = "transactionManager")
public class ProductInventoryServiceImpl implements IProductInventoryService{

	@Resource(name = "productInventoryDaoImpl")
	private IProductInventoryDao productInventoryDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductInventoryByPage(PageResult<ProductInventory> pageResult,ProductInventoryCondition condition){
		int rows = productInventoryDao.listProductInventoryByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductInventory> list = productInventoryDao.listProductInventoryByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品库存表
	 */
	public ProductInventory getProductInventoryById(String id){
		return productInventoryDao.getProductInventoryById(id);
	}

	/**
	 * 新增
	 * @param item 产品库存表
	 */
	public void add(ProductInventory item){
		productInventoryDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品库存表
	 */
	public void update(ProductInventory item){
		productInventoryDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productInventoryDao.delByIds(ids);
	}

}

