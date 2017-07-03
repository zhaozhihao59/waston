package com.tocersoft.product.service;

import java.util.List;

import com.tocersoft.product.entity.ProductAttrValue;
import com.tocersoft.base.page.PageResult;

import com.tocersoft.product.dto.ProductAttrValueCondition;

public interface IProductAttrValueService {

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	void listProductAttrValueByPage(PageResult<ProductAttrValue> pageResult,ProductAttrValueCondition condition);

	/**
	 * 产品属性查询
	 * 
	 * 说明：
	 * 1、此方法用于产品详细页中显示产品属性栏；
	 * 2、产品属性默认显示所有该类别对应的属性，如果属性还没有本产品的属性值，则可以选择；
	 * 3、本方法不再进行分页显示
	 * 
	 * 算法：
	 * 1、行数取决于当前产品对应的类别的属性数量
	 * 2、先查询产品已有的属性值
	 * 3、再根据类别的属性值进行遍历，将已有的属性值载入类别的属性值中，再传送至前台
	 * 
	 * @param categoryId 	类别ID
	 * @param productId 	产品ID
	 */
	List<ProductAttrValue> listPruductAttrAndCategory(String categoryId, String productId);
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品属性值
	 */
	ProductAttrValue getProductAttrValueById(String id);
	
	/**
	 * 根据产品ID与属性ID查询产品属性值是否存在
	 * 说明：
	 * 1、在做产品属性管理时，如果当前产品的属性值已经存在，则修改
	 * 2、在做产品属性管理时，如果当前产品的属性值不存在，则新增
	 * 
	 * @param productId
	 * @param attrId
	 * @return
	 */
	ProductAttrValue getProductAttrValueByPidAndAttrId(String productId, String attrId);

	/**
	 * 新增产品属性值，同时新增产品属性记录
	 * @param item 		产品属性值
	 * @param productId 产品ID
	 */
	void add(ProductAttrValue item, String productId);

	/**
	 * 修改
	 * @param item 产品属性值
	 */
	void update(ProductAttrValue item);
	
	/**
	 * 更新ProductAttrValue的attrValueId
	 * @param attrValueId	需要更新的attrValueId
	 * @param id			当前的productAttrValue
	 */
	void updateAttrValueId(String attrValueId, String id);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 根据属性Id查询相应属性值
	 */
	List<ProductAttrValue> listProductAttrValueByProductAttrId(String productAttrId);

}

