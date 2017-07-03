package com.tocersoft.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dao.IProductLogisticsDao;
import com.tocersoft.product.entity.ProductLogistics;
import com.tocersoft.product.service.IProductLogisticsService;

import com.tocersoft.product.dto.ProductLogisticsCondition;

@Service("productLogisticsServiceImpl")
@Transactional(value = "transactionManager")
public class ProductLogisticsServiceImpl implements IProductLogisticsService{

	@Resource(name = "productLogisticsDaoImpl")
	private IProductLogisticsDao productLogisticsDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductLogisticsByPage(PageResult<ProductLogistics> pageResult,ProductLogisticsCondition condition){
		int rows = productLogisticsDao.listProductLogisticsByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductLogistics> list = productLogisticsDao.listProductLogisticsByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品物流信息表
	 */
	public ProductLogistics getProductLogisticsById(String id){
		return productLogisticsDao.getProductLogisticsById(id);
	}
	
	/**
	 * 根据产品ID查询
	 * @param productId
	 * @return
	 */
	public List<ProductLogistics> getProductLogisticsByProductId(String productId){
		return productLogisticsDao.getProductLogisticsByProductId(productId);
	}

	/**
	 * 新增
	 * @param item 产品物流信息表
	 */
	public void add(ProductLogistics item){
		productLogisticsDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品物流信息表
	 */
	public void update(ProductLogistics item){
		productLogisticsDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productLogisticsDao.delByIds(ids);
	}

}

