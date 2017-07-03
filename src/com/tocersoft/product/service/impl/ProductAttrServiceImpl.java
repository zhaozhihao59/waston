package com.tocersoft.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dao.IProductAttrDao;
import com.tocersoft.product.entity.ProductAttr;
import com.tocersoft.product.service.IProductAttrService;

import com.tocersoft.product.dto.ProductAttrCondition;

@Service("productAttrServiceImpl")
@Transactional(value = "transactionManager")
public class ProductAttrServiceImpl implements IProductAttrService{

	@Resource(name = "productAttrDaoImpl")
	private IProductAttrDao productAttrDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductAttrByPage(PageResult<ProductAttr> pageResult,ProductAttrCondition condition){
		int rows = productAttrDao.listProductAttrByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductAttr> list = productAttrDao.listProductAttrByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品属性
	 */
	public ProductAttr getProductAttrById(String id){
		return productAttrDao.getProductAttrById(id);
	}

	/**
	 * 新增
	 * @param item 产品属性
	 */
	public void add(ProductAttr item){
		productAttrDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品属性
	 */
	public void update(ProductAttr item){
		productAttrDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productAttrDao.delByIds(ids);
	}

	/**
	 * 根据产品ID查询产品属性记录
	 */
	public List<ProductAttr> listProductAttrByPid(String productId){
		return productAttrDao.listProductAttrByPid(productId);
	}
}

