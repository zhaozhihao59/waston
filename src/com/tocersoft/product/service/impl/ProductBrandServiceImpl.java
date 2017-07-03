package com.tocersoft.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dao.IProductBrandDao;
import com.tocersoft.product.entity.ProductBrand;
import com.tocersoft.product.service.IProductBrandService;

import com.tocersoft.product.dto.ProductBrandCondition;

@Service("productBrandServiceImpl")
@Transactional(value = "transactionManager")
public class ProductBrandServiceImpl implements IProductBrandService{

	@Resource(name = "productBrandDaoImpl")
	private IProductBrandDao productBrandDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductBrandByPage(PageResult<ProductBrand> pageResult,ProductBrandCondition condition){
		int rows = productBrandDao.listProductBrandByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductBrand> list = productBrandDao.listProductBrandByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品品牌信息
	 */
	public ProductBrand getProductBrandById(String id){
		return productBrandDao.getProductBrandById(id);
	}

	/**
	 * 新增
	 * @param item 产品品牌信息
	 */
	public void add(ProductBrand item){
		productBrandDao.add(item);
	}

	/**
	 * 批量设置或取消明星品牌
	 * @return
	 */
	public void starBrand(String[] ids,Integer isStarBrand){
		for(String id : ids){
			ProductBrand productBrand = productBrandDao.getProductBrandById(id);
			productBrand.setIsStarBrand(isStarBrand);
			productBrandDao.update(productBrand);
		}
	}
	
	/**
	 * 修改
	 * @param item 产品品牌信息
	 */
	public void update(ProductBrand item){
		productBrandDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productBrandDao.delByIds(ids);
	}

	/**
	 * 查询所有
	 */
	public List<ProductBrand> listProductBrand(ProductBrandCondition condition){
		return productBrandDao.listProductBrand(condition);
	}
	
	/**
	 * 根据品牌类别查询
	 */
	public List<ProductBrand> listProductBrandByBrandTypeId(String brandTypeId){
		return productBrandDao.listProductBrandByBrandTypeId(brandTypeId);
	}
}

