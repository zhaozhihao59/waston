package com.tocersoft.product.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.member.entity.MemLevel;
import com.tocersoft.product.dao.IProductSkuDao;
import com.tocersoft.product.entity.ProductSku;
import com.tocersoft.product.service.IProductSkuService;

import com.tocersoft.product.dto.ProductSkuCondition;

@Service("productSkuServiceImpl")
@Transactional(value = "transactionManager")
public class ProductSkuServiceImpl implements IProductSkuService{

	@Resource(name = "productSkuDaoImpl")
	private IProductSkuDao productSkuDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductSkuByPage(PageResult<ProductSku> pageResult,ProductSkuCondition condition){
		int rows = productSkuDao.listProductSkuByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<ProductSku> list = productSkuDao.listProductSkuByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品SKU信息
	 */
	public ProductSku getProductSkuById(String id){
		return productSkuDao.getProductSkuById(id);
	}
	
	/**
	 * 根据产品ID查询
	 * @param productId 产品ID
	 * @return 产品SKU信息集合
	 */
	@Override
	public List<ProductSku> listProductSkuByProductId(String productId) {
		return productSkuDao.listProductSkuByProductId(productId);
	}

	/**
	 * 新增
	 * @param item 产品SKU信息
	 */
	public void add(ProductSku item){
		productSkuDao.add(item);
	}

	/**
	 * 修改
	 * @param item 产品SKU信息
	 */
	public void update(ProductSku item){
		productSkuDao.update(item);
	}
	
	/**
	 * 修改
	 * @param item 产品SKU 价格与库存信息
	 */
	public void update(JSONArray array,String productId){
		
		//逐一获取sku列表的每一行数据
		for(int i=0;i<array.size();i++){
			ProductSku productSku=new ProductSku();
			
			JSONObject obj = (JSONObject)array.get(i);
		
			String skuId = obj.get("skuId").toString();
			productSku.setId(skuId);
			productSku.setProductId(productId);
			
			String priceString=obj.get("price").toString();
			String inventorysString=obj.get("inventory").toString();
			Double price = null;
			Long inventory = null;
			if (!"".equals(priceString)) {
				price=Double.valueOf(priceString);
				productSku.setRetailPrice(price);
			}
			if (!"".equals(inventorysString)) {
				inventory = Long.valueOf(inventorysString);
				productSku.setInventory(inventory);
			}
			
			productSkuDao.update(productSku);
		}
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productSkuDao.delByIds(ids);
	}

	/**
	 * 根据skuID集合批量删除
	 * @param skuList  sku集合
	 */
	public void delBySkuIds(List<ProductSku> skuList){
		productSkuDao.delBySkuIds(skuList);
	}

}

