package com.tocersoft.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dao.IProductCategoryAttributeValueDao;
import com.tocersoft.product.entity.ProductCategoryAttributeValue;
import com.tocersoft.product.entity.ProductSkuAttrval;
import com.tocersoft.product.service.IProductCategoryAttributeValueService;

import com.tocersoft.product.dto.ProductCategoryAttributeValueCondition;

@Service("productCategoryAttributeValueServiceImpl")
@Transactional(value = "transactionManager")
public class ProductCategoryAttributeValueServiceImpl implements IProductCategoryAttributeValueService{

	@Resource(name = "productCategoryAttributeValueDaoImpl")
	private IProductCategoryAttributeValueDao productCategoryAttributeValueDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductCategoryAttributeValueByPage(PageResult<ProductCategoryAttributeValue> pageResult,ProductCategoryAttributeValueCondition condition){
		int rows = productCategoryAttributeValueDao.listProductCategoryAttributeValueByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductCategoryAttributeValue> list = productCategoryAttributeValueDao.listProductCategoryAttributeValueByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品类别属性
	 */
	public ProductCategoryAttributeValue getProductCategoryAttributeValueById(String id){
		return productCategoryAttributeValueDao.getProductCategoryAttributeValueById(id);
	}
	
	/**
	 * 根据属性ID查询
	 * @param 属性id 
	 * @return 产品类别属性值集合
	 */
	@Override
	public List<ProductCategoryAttributeValue> listProductCategoryAttributeValueByCatePubAttrId(
			String catePubAttrId) {
		return productCategoryAttributeValueDao.listProductCategoryAttributeValueByCatePubAttrId(catePubAttrId);
	}

	/**
	 * 新增
	 * @param item 产品类别属性
	 */
	public void add(ProductCategoryAttributeValue item){
		productCategoryAttributeValueDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品类别属性
	 */
	public void update(ProductCategoryAttributeValue item){
		productCategoryAttributeValueDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productCategoryAttributeValueDao.delByIds(ids);
	}

	@Override
	public List<ProductCategoryAttributeValue> listProductCategoryAttributeValueByIds(
			List<ProductSkuAttrval> skuAttrvals) {
		return productCategoryAttributeValueDao.listProductCategoryAttributeValueByIds(skuAttrvals);
	}
	
	/**
	 * 根据发布类目属性编号删除相应的属性值
	 */
	public void delByCatePubAttrId(String CatePubAttrId){
		productCategoryAttributeValueDao.delByCatePubAttrId(CatePubAttrId);
	}
}

