package com.tocersoft.product.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tocersoft.product.entity.ProductBrand;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductBrandCondition;

public interface IProductBrandService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductBrandByPage(PageResult<ProductBrand> pageResult,ProductBrandCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品品牌信息
	 */
	ProductBrand getProductBrandById(String id);

	/**
	 * 新增
	 * @param item 产品品牌信息
	 */
	void add(ProductBrand item);

	/**
	 * 批量设置或取消明星品牌
	 * @return
	 */
	void starBrand(String[] ids,Integer isStarBrand);
	
	/**
	 * 修改
	 * @param item 产品品牌信息
	 */
	void update(ProductBrand item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 查询所有
	 */
	List<ProductBrand> listProductBrand(ProductBrandCondition condition);
	
	/**
	 * 根据品牌类别查询
	 */
	List<ProductBrand> listProductBrandByBrandTypeId(String brandTypeId);
}

