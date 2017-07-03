package com.tocersoft.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dao.IProductSaleSettingDao;
import com.tocersoft.product.entity.ProductSaleSetting;
import com.tocersoft.product.service.IProductSaleSettingService;

import com.tocersoft.product.dto.ProductSaleSettingCondition;

@Service("productSaleSettingServiceImpl")
@Transactional(value = "transactionManager")
public class ProductSaleSettingServiceImpl implements IProductSaleSettingService{

	@Resource(name = "productSaleSettingDaoImpl")
	private IProductSaleSettingDao productSaleSettingDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductSaleSettingByPage(PageResult<ProductSaleSetting> pageResult,ProductSaleSettingCondition condition){
		int rows = productSaleSettingDao.listProductSaleSettingByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductSaleSetting> list = productSaleSettingDao.listProductSaleSettingByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品销售属性设置信息
	 */
	public ProductSaleSetting getProductSaleSettingById(String id){
		return productSaleSettingDao.getProductSaleSettingById(id);
	}

	/**
	 * 新增
	 * @param item 产品销售属性设置信息
	 */
	public void add(ProductSaleSetting item){
		productSaleSettingDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品销售属性设置信息
	 */
	public void update(ProductSaleSetting item){
		productSaleSettingDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productSaleSettingDao.delByIds(ids);
	}

}

