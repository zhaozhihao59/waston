package com.tocersoft.product.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONObject;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dto.ProductCondition;
import com.tocersoft.product.entity.Product;

public interface IProductService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductByPage(PageResult<Product> pageResult,ProductCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品信息
	 */
	Product getProductById(String id);

	/**
	 * 新增
	 * @param item 产品信息
	 */
	void add(Product item);
	
	String doImport(String path);
	
	/**
	 * API新增
	 * @param 	obj 	产品的JSON对象
	 * @return 	String	产品ID
	 */
	String addApi(JSONObject obj);
	
	/**
	 * 批量设置产品为促销产品
	 * @param ids
	 * @return
	 */
	void updateIsPromotion(String[] ids,Integer isPromotion,double discount);
	
	/**
	 * 批量设置产品为推荐产品
	 * @param ids
	 * @return
	 */
	void updateIsRecommend(String[] ids,Integer isRecommend);
	
	/**
	 * 批量设置产品为明星产品
	 * @param ids
	 * @return
	 */
	void updateIsStar(String[] ids,Integer isStarProduct);

	/**
	 * 修改
	 * @param item 产品信息
	 */
	void update(Product item);

	/**
	 * 保存默认价格
	 * @param item 
	 */
	void saveDefaultPrice(Product item);
	
	/**
	 * 保存产品首图url
	 * @param item 
	 */
	void saveOneImgUrl(Product item);
	
	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 根据类别ID查询
	 */
	List<Product> listProductByCategoryId(String categoryId);
	
	/**
	 * 根据品牌ID查询
	 */
	List<Product> listProductByBrandId(String brandId);
	
	/**
	 * 修改产品类别
	 * @param product
	 */
	void changeCategory(String productIds[],String categoryId);
	
	/**
	 * 批量导出产品
	 */
	Workbook batchExport();
}

