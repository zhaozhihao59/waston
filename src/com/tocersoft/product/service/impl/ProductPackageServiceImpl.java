package com.tocersoft.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dao.IProductPackageDao;
import com.tocersoft.product.entity.ProductPackage;
import com.tocersoft.product.service.IProductPackageService;

import com.tocersoft.product.dto.ProductPackageCondition;

@Service("productPackageServiceImpl")
@Transactional(value = "transactionManager")
public class ProductPackageServiceImpl implements IProductPackageService{

	@Resource(name = "productPackageDaoImpl")
	private IProductPackageDao productPackageDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductPackageByPage(PageResult<ProductPackage> pageResult,ProductPackageCondition condition){
		int rows = productPackageDao.listProductPackageByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductPackage> list = productPackageDao.listProductPackageByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品包装信息
	 */
	public ProductPackage getProductPackageById(String id){
		return productPackageDao.getProductPackageById(id);
	}

	/**
	 * 根据condition查询
	 * @param 
	 * @return 产品包装信息
	 */
	public List<ProductPackage> listProductPackageByCondition(ProductPackageCondition condition){
		return productPackageDao.listProductPackageByCondition(condition);
	}
	
	/**
	 * 新增
	 * @param item 产品包装信息
	 */
	public void add(ProductPackage item){
		productPackageDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品包装信息
	 */
	public void update(ProductPackage item){
		productPackageDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productPackageDao.delByIds(ids);
	}

}

