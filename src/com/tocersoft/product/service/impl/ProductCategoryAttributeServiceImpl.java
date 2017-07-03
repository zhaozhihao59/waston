package com.tocersoft.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.math.Combination;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.UUIDUtil;
import com.tocersoft.product.dao.IProductCategoryAttributeDao;
import com.tocersoft.product.dao.IProductCategoryAttributeValueDao;
import com.tocersoft.product.dao.IProductSkuAttrvalDao;
import com.tocersoft.product.dao.IProductSkuDao;
import com.tocersoft.product.entity.ProductCategoryAttribute;
import com.tocersoft.product.entity.ProductCategoryAttributeValue;
import com.tocersoft.product.entity.ProductSku;
import com.tocersoft.product.entity.ProductSkuAttrval;
import com.tocersoft.product.model.ProductCategoryAttributeValueModel;
import com.tocersoft.product.service.IProductCategoryAttributeService;

import com.tocersoft.product.dto.ProductCategoryAttributeCondition;
import com.tocersoft.product.dto.ProductCategoryAttributeValueCondition;

@Service("productCategoryAttributeServiceImpl")
@Transactional(value = "transactionManager")
public class ProductCategoryAttributeServiceImpl implements IProductCategoryAttributeService{

	@Resource(name = "productCategoryAttributeDaoImpl")
	private IProductCategoryAttributeDao productCategoryAttributeDao;
	@Resource(name = "productSkuDaoImpl")
	private IProductSkuDao productSkuDao;
	@Resource(name = "productSkuAttrvalDaoImpl")
	private IProductSkuAttrvalDao productSkuAttrvalDao;
	@Resource(name = "productCategoryAttributeValueDaoImpl")
	private IProductCategoryAttributeValueDao productCategoryAttributeValueDao;
	private ProductCategoryAttributeValueModel valueModel = new ProductCategoryAttributeValueModel();

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductCategoryAttributeByPage(PageResult<ProductCategoryAttribute> pageResult,ProductCategoryAttributeCondition condition){
		int rows = productCategoryAttributeDao.listProductCategoryAttributeByPageCount(condition);
		pageResult.setRows(rows);
		
		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		
		//查询所有产品类别属性
		List<ProductCategoryAttribute> list = productCategoryAttributeDao.listProductCategoryAttributeByPage(rowBounds,condition);
		for (ProductCategoryAttribute productCategoryAttribute : list) {
			//申明一个集合用来存储属性值
			List<String> Valueslist = new ArrayList<String>();
			//根据产品类别属性id查询
			List<ProductCategoryAttributeValue> attrValueList =	productCategoryAttributeValueDao.listProductCategoryAttributeValueByCatePubAttrId(productCategoryAttribute.getId());
			//取出属性id对应的属性值
			for(ProductCategoryAttributeValue Values : attrValueList){
				//将得到的属性值放到集合中
				Valueslist.add(Values.getLineAttrvalNameCn());
			}
			//将得到的属性值用逗号拼接
			productCategoryAttribute.setAttributeValue(org.apache.commons.lang.StringUtils.join(Valueslist,","));
		}
		pageResult.setResult(list);
	}
	
	/**
	 * 属性值分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductAttrAndCategoryByPage(PageResult<ProductCategoryAttribute> pageResult,ProductCategoryAttributeCondition condition){
		int rows = productCategoryAttributeDao.listProductAttrAndCategoryCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductCategoryAttribute> list = productCategoryAttributeDao.listProductAttrAndCategoryByPage(rowBounds,condition);
		pageResult.setResult(list);
	}


	/**
	 * 根据查询条件类查询
	 * @param condition 查询条件类
	 */
	@Override
	public List<ProductCategoryAttribute> listProductCategoryAttributeByCondition(
			ProductCategoryAttributeCondition condition) {
		return productCategoryAttributeDao.listProductCategoryAttributeByCondition(condition);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品类别属性
	 */
	public ProductCategoryAttribute getProductCategoryAttributeById(String id){
		return productCategoryAttributeDao.getProductCategoryAttributeById(id);
	}
	
//	/**
//	 * 根据ID查询
//	 * @param categoryId 类别ID
//	 * @return 产品类别属性
//	 */
//	public List<ProductCategoryAttribute> listProductCategoryAttributeByCategoryId(String categoryId)
//	{
//		return productCategoryAttributeDao.listProductCategoryAttributeByCategoryId(categoryId);
//	}
	
	/**
	 * 新增
	 * @param item 产品类别属性
	 */
	public void add(ProductCategoryAttribute item){
		productCategoryAttributeDao.add(item);
	}
	
	/**
	 * 新增属性与属性值
	 * @param item 			类别属性实体
	 * @param attrValues 	传入属性值的集合字符串，解析成多个属性值集合，例如：红,黄,蓝
	 */
	public void addAttrAndValue(ProductCategoryAttribute item, String attrValues){
		productCategoryAttributeDao.add(item);
		
		// 解析属性值字符串，解析成字符串数组
		String[] attrValueList = attrValues.split(",");
		for(String attrValue : attrValueList){
			ProductCategoryAttributeValue attrValueObj = new ProductCategoryAttributeValue();
			// 属性值对应的属性ID
			attrValueObj.setCatePubAttrId(item.getId());
			// 属性值名称
			attrValueObj.setLineAttrvalNameCn(attrValue);
			// 保存属性值记录
			productCategoryAttributeValueDao.add(attrValueObj);
		}
	}

	/**
	 * 修改属性与属性值
	 * @param item 产品类别属性
	 */
	public void update(ProductCategoryAttribute item){
		productCategoryAttributeDao.update(item);
	}
	
	/**
	 * 修改
	 * @param item 产品类别属性
	 */
	public void updateAttrValue(ProductCategoryAttribute item, String attrValues){
		
		// 更新类别属性
		productCategoryAttributeDao.update(item);
		
		// 根据发布类目属性编号删除相应的属性值
		productCategoryAttributeValueDao.delByCatePubAttrId(item.getId());
		
		// 解析属性值字符串，解析成字符串数组
		String[] attrValueList = attrValues.split(",");
		for(String attrValue : attrValueList){
			ProductCategoryAttributeValue attrValueObj = new ProductCategoryAttributeValue();
			// 属性值对应的属性ID
			attrValueObj.setCatePubAttrId(item.getId());
			// 属性值名称
			attrValueObj.setLineAttrvalNameCn(attrValue);
			// 保存属性值记录
			productCategoryAttributeValueDao.add(attrValueObj);
		}
	}


	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productCategoryAttributeDao.delByIds(ids);
	}

	/**
	 * 新增产品类别属性集合
	 * @param itemList
	 */
	@Override
	public void addList(List<ProductCategoryAttribute> itemList) {
		for(ProductCategoryAttribute item : itemList){
			productCategoryAttributeDao.add(item);
		}
	}



	@Override
	public List<List<ProductSkuAttrval>> loadCombineAttrValues(
			String productId) {
		List<ProductSku> skuList=productSkuDao.listProductSkuByProductId(productId);
		//根据skuid拿到产品SKU属性值的表 (按属性值字段分组查询)
		List<ProductSkuAttrval> skuAttrValList = productSkuAttrvalDao.listProductSkuAttrvalBySkuIds(skuList);
		//根据SKU属性值表中的属性值ID
		// 找到该产品对应的属性值列表
//		List<ProductCategoryAttributeValue> proCatAttrList=productCategoryAttributeValueDao.listProductCategoryAttributeValueByIds(skuAttrValList);
		
		Map<String,ProductSkuAttrval> proProductAttrValueIdMap = new HashMap<String,ProductSkuAttrval>();
		for(ProductSkuAttrval item : skuAttrValList){
			proProductAttrValueIdMap.put(item.getId(), item);
		}
		
		
		//key:attrId value:List<ProductSkuAttrval>
		Map<String,List<ProductSkuAttrval>> proProductAttrValueMap = new LinkedHashMap<String,List<ProductSkuAttrval>>();
		for(ProductSkuAttrval attrValue : skuAttrValList){
			List<ProductSkuAttrval> list = proProductAttrValueMap.get(attrValue.getAttrId());
			if(null == list){
				list = new ArrayList<ProductSkuAttrval>();
				proProductAttrValueMap.put(attrValue.getAttrId(), list);
			}
			
			list.add(attrValue);
		}
		
		//调用排列组合方法进行全排列
			int numberToPick = proProductAttrValueMap.keySet().size();
			Combination<String> combination = Combination.of(proProductAttrValueIdMap.keySet(),numberToPick);
			List<List<ProductSkuAttrval>> needToUseCombineList = new ArrayList<List<ProductSkuAttrval>>();
		
		
			List<String> checkedAttrIdList = new ArrayList<String>();
			for(List<String> list : combination) {
				//如果有任意2个的attrId相同,则去除
				boolean needToRemove = false;
				for(String attrValueId : list){
					ProductSkuAttrval item = proProductAttrValueIdMap.get(attrValueId);
					if(checkedAttrIdList.contains(item.getAttrId())){
						//已经包含,直接去除
						needToRemove = true;
						break;
					}else{
						checkedAttrIdList.add(item.getAttrId());
					}
				}
				checkedAttrIdList.clear();
				
				if(!needToRemove){
					List<ProductSkuAttrval> needToUseList = new ArrayList<ProductSkuAttrval>();
					for(String attrValueId : list){
						ProductSkuAttrval item = proProductAttrValueIdMap.get(attrValueId);
						needToUseList.add(item);
					}
					needToUseCombineList.add(needToUseList);
				}
			}
		  
		return needToUseCombineList;
	}


	/**
	 * 根据产品ID列出该产品所有已选择的销售属性集合
	 * @param productId
	 */
	@Override
	public List<ProductCategoryAttribute> listProductCategoryAttributeByProductId(
			String productId) {
		List<ProductCategoryAttribute> proCatAttrList=new ArrayList<ProductCategoryAttribute>();
		
		// 根据 产品ID 在sku表中得到 sku ID
		List<ProductSku> skuList=productSkuDao.listProductSkuByProductId(productId);
		
		if(skuList.size()>0){
//			用sku ID 在sku_attr表中得到类别属性ID集合
			List<ProductSkuAttrval> skuAttrValList = productSkuAttrvalDao.listProductSkuAttrvalBySkuIds(skuList);

			if(skuAttrValList.size()>0){
//			用类别属性ID 在类别属性表里得到 属性集合
			proCatAttrList=productCategoryAttributeDao.listProductCategoryAttributeByIds(skuAttrValList);
			}
		}

		return proCatAttrList;
	}
	
	/**
	 * 根据类别Id查询
	 */
	public List<ProductCategoryAttribute> listProductCategoryAttributeByCategoryId(String categoryId){
		return productCategoryAttributeDao.listProductCategoryAttributeByCategoryId(categoryId);
	}
	
	/**
	 * 根据类别ID查询
	 * @Param categoryId	类别ID
	 * @Param saleAttr		'0'-非销售属性，'1'-销售属性
	 */
	public List<ProductCategoryAttribute> listCategoryAttrByCategoryIdIsSale(String categoryId, String saleAttr) {
		return productCategoryAttributeDao.listCategoryAttrByCategoryIdIsSale(categoryId, saleAttr);
	}

}

