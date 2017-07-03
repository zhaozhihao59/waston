package com.tocersoft.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.product.dao.IProductAttrDao;
import com.tocersoft.product.dao.IProductAttrValueDao;
import com.tocersoft.product.dto.ProductAttrValueCondition;
import com.tocersoft.product.dto.ProductCategoryAttributeCondition;
import com.tocersoft.product.entity.ProductAttr;
import com.tocersoft.product.entity.ProductAttrValue;
import com.tocersoft.product.entity.ProductCategoryAttribute;
import com.tocersoft.product.service.IProductAttrValueService;
import com.tocersoft.product.service.IProductCategoryAttributeService;
import com.tocersoft.product.service.IProductCategoryService;

@Service("productAttrValueServiceImpl")
@Transactional(value = "transactionManager")
public class ProductAttrValueServiceImpl implements IProductAttrValueService{

	@Resource(name = "productAttrDaoImpl")
	private IProductAttrDao productAttrDao;
	@Resource(name = "productAttrValueDaoImpl")
	private IProductAttrValueDao productAttrValueDao;
	@Resource(name = "productCategoryAttributeServiceImpl")
	private IProductCategoryAttributeService productCategoryAttributeService;
	@Resource(name = "productCategoryServiceImpl")
	private IProductCategoryService productCategoryService;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
//	ProductAttrValueModel model = new ProductAttrValueModel();
	public void listProductAttrValueByPage(PageResult<ProductAttrValue> pageResult,ProductAttrValueCondition condition){
		int rows = productAttrValueDao.listProductAttrValueByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductAttrValue> list = productAttrValueDao.listProductAttrValueByPage(rowBounds,condition);
	
		for(ProductAttrValue productAttrValue : list){
			//查询所有类别
			ProductCategoryAttributeCondition cateAttrCondition = new ProductCategoryAttributeCondition();
			cateAttrCondition.setCategoryId(condition.getCategoryId());
			List<ProductCategoryAttribute> productCategoryAttributeList = productCategoryAttributeService.listProductCategoryAttributeByCondition(cateAttrCondition);
			for(ProductCategoryAttribute  productCategoryAttribute : productCategoryAttributeList){
				productAttrValue.setAttrName(productCategoryAttribute.getLineAttrNameCn());
			}
		}
		pageResult.setResult(list);
	}

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
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public List<ProductAttrValue> listPruductAttrAndCategory(String categoryId, String productId){
		
		// 查询产品对应的类别的属性
		List<ProductCategoryAttribute> categoryAttrList = productCategoryAttributeService.listCategoryAttrByCategoryIdIsSale(categoryId, "0");
		
		// 查询产品已有的属性及属性值
		List<ProductAttrValue> list = productAttrValueDao.listPruductAttrAndCategory(categoryId,productId);
		
		List<ProductAttrValue> result = new ArrayList<ProductAttrValue>();
		// 将属性组织起来，确保所有的类别属性都能显示
		for(ProductCategoryAttribute attr : categoryAttrList){
			ProductAttrValue attrValue = new ProductAttrValue();
			boolean isExsit = false;
			for(ProductAttrValue item : list){
				// 当类别的属性在产品已有的属性中存在的话，表示已经设置过属性值
				if(null != attr.getId() && null != item.getAttrId() && attr.getId().equals(item.getAttrId())){
					isExsit = true;
					attrValue = item;
				}
			}
			// 如果还没设置过，则将类别的属性装入结果集中
			if(!isExsit){
				attrValue.setAttrId(attr.getId());
				attrValue.setAttrName(attr.getLineAttrNameCn());
			}
			result.add(attrValue);
		}
		
		return result;
	}
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品属性值
	 */
	public ProductAttrValue getProductAttrValueById(String id){
		return productAttrValueDao.getProductAttrValueById(id);
	}

	/**
	 * 新增产品属性值，同时新增产品属性记录
	 * @param item 		产品属性值
	 * @param productId 产品ID
	 */
	public void add(ProductAttrValue item, String productId){
		
		// 如果productAttr属性表已经存在属性记录，则不再追加productAttr属性记录
		ProductAttr attr = productAttrDao.getProductAttrByPidAndAttrId(productId, item.getAttrId());
		if(null == attr){
			// 新增产品属性
			attr = new ProductAttr();
			attr.setAttrId(item.getAttrId());
			attr.setProductId(productId);
			productAttrDao.add(attr);
		}
		// 新增产品属性值
		item.setProductAttrId(attr.getId());
		productAttrValueDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品属性值
	 */
	public void update(ProductAttrValue item){
		productAttrValueDao.update(item);
	}
	
	/**
	 * 更新ProductAttrValue的attrValueId
	 * @param attrValueId	需要更新的attrValueId
	 * @param id			当前的productAttrValue
	 */
	public void updateAttrValueId(String attrValueId, String id){
		productAttrValueDao.updateAttrValueId(attrValueId, id);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		
		List<String> attrIdList = new ArrayList<String>();
		for(String id : ids){
			// 根据属性值ID查询产品属性值
			ProductAttrValue attrValue = productAttrValueDao.getProductAttrValueById(id);
			
			if(null != attrValue){
				// 根据属性ID查询产品属性
				ProductAttr attr = productAttrDao.getProductAttrById(attrValue.getProductAttrId());
				attrIdList.add(attr.getId());
			}
		}
		
		// 删除属性
		productAttrDao.delByIds(attrIdList.toArray(new String[]{}));
		
		// 删除属性值
		productAttrValueDao.delByIds(ids);
	}

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
	@Override
	public ProductAttrValue getProductAttrValueByPidAndAttrId(String productId, String attrId) {
		
		ProductAttr attr = productAttrDao.getProductAttrByPidAndAttrId(productId, attrId);
		// 产品属性不为空时，表示已经选择过产品属性
		if(null != attr){
			ProductAttrValue attrValue = productAttrValueDao.getProductAttrValueByAttrId(attr.getId());
			return attrValue;
		
		// 产品属性为空时，表示还没有选择过产品属性
		}else{
			return null;
		}
		
	}
	
	/**
	 * 根据属性Id查询相应属性值
	 */
	public List<ProductAttrValue> listProductAttrValueByProductAttrId(String productAttrId){
		return productAttrValueDao.listProductAttrValueByProductAttrId(productAttrId);
	}

}

