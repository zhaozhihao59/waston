package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.Product;
import com.tocersoft.product.dto.ProductCondition;

@Repository("productDaoImpl")
public interface IProductDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Product> listProductByPage(RowBounds bounds,@Param("condition") ProductCondition condition);
	
	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Product> listProductAndAttrVal();
	

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductByPageCount(@Param("condition") ProductCondition condition);

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Product> listProductByPage(@Param("condition") ProductCondition condition);

	/**
	 * 查询产品信息(作导出用)
	 * @return
	 */
	List<Product> listProduct();
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品信息
	 */
	Product getProductById(@Param("id") String id);
	
	/**
	 * 根据productNo查询
	 * @param 	productNo 敦煌网提供产品的ID
	 * @return 	产品信息
	 */
	Product getProductByNo(@Param("productNo") String productNo);

	/**
	 * 新增
	 * @param item 产品信息
	 */
	void add(Product item);

	/**
	 * 修改
	 * @param item 产品信息
	 */
	void update(Product item);
	
	/**
	 * 保存产品首图url
	 * @param item 
	 */
	void saveOneImgUrl(Product item);
	
	/**
	 * 保存默认价格
	 * @param item 
	 */
	void saveDefaultPrice(Product item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

	/**
	 * 根据敦煌网提供产品的ID删除产品记录
	 * @param 	productNo 	敦煌网提供产品的ID
	 */
	void delByProductNo(String productNo);
	
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
	void changeCategory(Product product);
}

