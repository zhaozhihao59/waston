package com.tocersoft.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dao.IProductWeightRangeDao;
import com.tocersoft.product.entity.ProductWeightRange;
import com.tocersoft.product.service.IProductWeightRangeService;

import com.tocersoft.product.dto.ProductWeightRangeCondition;

@Service("productWeightRangeServiceImpl")
@Transactional(value = "transactionManager")
public class ProductWeightRangeServiceImpl implements IProductWeightRangeService{

	@Resource(name = "productWeightRangeDaoImpl")
	private IProductWeightRangeDao productWeightRangeDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductWeigthRangeByPage(PageResult<ProductWeightRange> pageResult,ProductWeightRangeCondition condition){
		int rows = productWeightRangeDao.listProductWeigthRangeByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductWeightRange> list = productWeightRangeDao.listProductWeigthRangeByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品包装重量信息
	 */
	public ProductWeightRange getProductWeigthRangeById(String id){
		return productWeightRangeDao.getProductWeigthRangeById(id);
	}

	/**
	 * 新增
	 * @param item 产品包装重量信息
	 */
	public void add(ProductWeightRange item){
		productWeightRangeDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品包装重量信息
	 */
	public void update(ProductWeightRange item){
		productWeightRangeDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productWeightRangeDao.delByIds(ids);
	}

}

