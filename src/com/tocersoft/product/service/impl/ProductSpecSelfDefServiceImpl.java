package com.tocersoft.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dao.IProductSpecSelfDefDao;
import com.tocersoft.product.entity.ProductSpecSelfDef;
import com.tocersoft.product.service.IProductSpecSelfDefService;

import com.tocersoft.product.dto.ProductSpecSelfDefCondition;

@Service("productSpecSelfDefServiceImpl")
@Transactional(value = "transactionManager")
public class ProductSpecSelfDefServiceImpl implements IProductSpecSelfDefService{

	@Resource(name = "productSpecSelfDefDaoImpl")
	private IProductSpecSelfDefDao productSpecSelfDefDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductSpecSelfDefByPage(PageResult<ProductSpecSelfDef> pageResult,ProductSpecSelfDefCondition condition){
		int rows = productSpecSelfDefDao.listProductSpecSelfDefByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductSpecSelfDef> list = productSpecSelfDefDao.listProductSpecSelfDefByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品自定义规格信息
	 */
	public ProductSpecSelfDef getProductSpecSelfDefById(String id){
		return productSpecSelfDefDao.getProductSpecSelfDefById(id);
	}

	/**
	 * 新增
	 * @param item 产品自定义规格信息
	 */
	public void add(ProductSpecSelfDef item){
		productSpecSelfDefDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品自定义规格信息
	 */
	public void update(ProductSpecSelfDef item){
		productSpecSelfDefDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productSpecSelfDefDao.delByIds(ids);
	}

}

