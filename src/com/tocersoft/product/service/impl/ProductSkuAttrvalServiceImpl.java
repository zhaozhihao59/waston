package com.tocersoft.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dao.IProductSkuAttrvalDao;
import com.tocersoft.product.entity.ProductSku;
import com.tocersoft.product.entity.ProductSkuAttrval;
import com.tocersoft.product.service.IProductSkuAttrvalService;

import com.tocersoft.product.dto.ProductSkuAttrvalCondition;

@Service("productSkuAttrvalServiceImpl")
@Transactional(value = "transactionManager")
public class ProductSkuAttrvalServiceImpl implements IProductSkuAttrvalService{

	@Resource(name = "productSkuAttrvalDaoImpl")
	private IProductSkuAttrvalDao productSkuAttrvalDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductSkuAttrvalByPage(PageResult<ProductSkuAttrval> pageResult,ProductSkuAttrvalCondition condition){
		int rows = productSkuAttrvalDao.listProductSkuAttrvalByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductSkuAttrval> list = productSkuAttrvalDao.listProductSkuAttrvalByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品SKU属性值
	 */
	public ProductSkuAttrval getProductSkuAttrvalById(String id){
		return productSkuAttrvalDao.getProductSkuAttrvalById(id);
	}

	/**
	 * 新增
	 * @param item 产品SKU属性值
	 */
	public void add(ProductSkuAttrval item){
		productSkuAttrvalDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品SKU属性值
	 */
	public void update(ProductSkuAttrval item){
		productSkuAttrvalDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productSkuAttrvalDao.delByIds(ids);
	}

	/**
	 * 根据多个skuID查询
	 */
	@Override
	public List<ProductSkuAttrval> listProductSkuAttrvalBySkuIds(
			List<ProductSku> skuList) {
		return productSkuAttrvalDao.listProductSkuAttrvalBySkuIds(skuList);
	}

	/**
	 * 根据skuID集合批量删除
	 * @param ids skuID集合
	 */
	@Override
	public void delBySkuIds(List<ProductSku> skuList){
		productSkuAttrvalDao.delBySkuIds(skuList);
	}
}

