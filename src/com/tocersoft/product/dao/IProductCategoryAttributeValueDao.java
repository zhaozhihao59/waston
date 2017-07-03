package com.tocersoft.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.tocersoft.product.entity.ProductCategoryAttributeValue;
import com.tocersoft.product.entity.ProductSkuAttrval;
import com.tocersoft.product.dto.ProductCategoryAttributeValueCondition;

@Repository("productCategoryAttributeValueDaoImpl")
public interface IProductCategoryAttributeValueDao {

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<ProductCategoryAttributeValue> listProductCategoryAttributeValueByPage(RowBounds bounds,@Param("condition") ProductCategoryAttributeValueCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listProductCategoryAttributeValueByPageCount(@Param("condition") ProductCategoryAttributeValueCondition condition);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品类别属性
	 */
	ProductCategoryAttributeValue getProductCategoryAttributeValueById(@Param("id") String id);
	
	/**
	 * 根据多个ID查询
	 */
	List<ProductCategoryAttributeValue> listProductCategoryAttributeValueByIds(@Param("skuAttrvals") List<ProductSkuAttrval> skuAttrvals);

	/**
	 * 根据属性ID查询
	 * @param 属性id 
	 * @return 产品类别属性值集合
	 */
	List<ProductCategoryAttributeValue> listProductCategoryAttributeValueByCatePubAttrId(@Param("catePubAttrId") String catePubAttrId);
	
	/**
	 * 新增
	 * @param item 产品类别属性
	 */
	void add(ProductCategoryAttributeValue item);

	/**
	 * 修改
	 * @param item 产品类别属性
	 */
	void update(ProductCategoryAttributeValue item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 根据发布类目属性编号删除相应的属性值
	 */
	void delByCatePubAttrId(String CatePubAttrId);

}

