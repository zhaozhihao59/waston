package com.tocersoft.product.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.Excel2007Util;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.order.dao.IOrderSellItemDao;
import com.tocersoft.product.dao.IProductAttrDao;
import com.tocersoft.product.dao.IProductAttrValueDao;
import com.tocersoft.product.dao.IProductBrandDao;
import com.tocersoft.product.dao.IProductCategoryAttributeDao;
import com.tocersoft.product.dao.IProductCategoryAttributeValueDao;
import com.tocersoft.product.dao.IProductCategoryDao;
import com.tocersoft.product.dao.IProductDao;
import com.tocersoft.product.dao.IProductInventoryDao;
import com.tocersoft.product.dao.IProductPackageDao;
import com.tocersoft.product.dao.IProductSaleSettingDao;
import com.tocersoft.product.dao.IProductSkuAttrvalDao;
import com.tocersoft.product.dao.IProductSkuDao;
import com.tocersoft.product.dao.IProductSpecSelfDefDao;
import com.tocersoft.product.dao.IProductWeightRangeDao;
import com.tocersoft.product.dao.IProductWholesaleRangeDao;
import com.tocersoft.product.dto.ProductBrandCondition;
import com.tocersoft.product.dto.ProductCategoryAttributeCondition;
import com.tocersoft.product.dto.ProductCondition;
import com.tocersoft.product.dto.ProductDownloadVO;
import com.tocersoft.product.entity.Product;
import com.tocersoft.product.entity.ProductAttr;
import com.tocersoft.product.entity.ProductAttrValue;
import com.tocersoft.product.entity.ProductBrand;
import com.tocersoft.product.entity.ProductCategory;
import com.tocersoft.product.entity.ProductCategoryAttribute;
import com.tocersoft.product.entity.ProductCategoryAttributeValue;
import com.tocersoft.product.entity.ProductInventory;
import com.tocersoft.product.entity.ProductPackage;
import com.tocersoft.product.entity.ProductSaleSetting;
import com.tocersoft.product.entity.ProductSku;
import com.tocersoft.product.entity.ProductSkuAttrval;
import com.tocersoft.product.entity.ProductSpecSelfDef;
import com.tocersoft.product.entity.ProductWeightRange;
import com.tocersoft.product.entity.ProductWholesaleRange;
import com.tocersoft.product.service.IProductCategoryAttributeService;
import com.tocersoft.product.service.IProductService;
import com.tocersoft.system.dao.ISysDictItemDao;
import com.tocersoft.system.dao.ISysUploadFileDao;
import com.tocersoft.system.entity.SysDictItem;
import com.tocersoft.system.entity.SysUploadFile;

@Service("productServiceImpl")
@Transactional(value = "transactionManager")
public class ProductServiceImpl implements IProductService{
	
	private Log logger = LogFactory.getLog(ProductServiceImpl.class);

	@Resource(name = "productDaoImpl")
	private IProductDao productDao;
	@Resource(name = "productAttrDaoImpl")
	private IProductAttrDao productAttrDao;
	@Resource(name = "productInventoryDaoImpl")
	private IProductInventoryDao productInventoryDao;
	@Resource(name = "productPackageDaoImpl")
	private IProductPackageDao productPackageDao;
	@Resource(name = "productSaleSettingDaoImpl")
	private IProductSaleSettingDao productSaleSettingDao;
	@Resource(name = "productSkuDaoImpl")
	private IProductSkuDao productSkuDao;
	@Resource(name = "productSpecSelfDefDaoImpl")
	private IProductSpecSelfDefDao productSpecSelfDefDao;
	@Resource(name = "productAttrValueDaoImpl")
	private IProductAttrValueDao productAttrValueDao;
	@Resource(name = "productWeightRangeDaoImpl")
	private IProductWeightRangeDao productWeightRangeDao;
	@Resource(name = "productSkuAttrvalDaoImpl")
	private IProductSkuAttrvalDao productSkuAttrvalDao;
	@Resource(name = "sysUploadFileDaoImpl")
	private ISysUploadFileDao fileDao;
	@Resource(name = "productWholesaleRangeDaoImpl")
	private IProductWholesaleRangeDao wholesaleRangeDao;
	@Resource
	private IProductBrandDao productBrandDao;
	@Resource
	private IProductCategoryDao  productCategoryDao;
	@Resource
	private ISysDictItemDao  sysDictItemDao ;
	@Resource
	private IProductCategoryAttributeDao  productCategoryAttributeDao ;
	@Resource
	private IProductCategoryAttributeValueDao  productCategoryAttributeValueDao;
	@Resource
	private IOrderSellItemDao orderSellItemDao;
	@Resource(name = "productCategoryAttributeServiceImpl")
	private IProductCategoryAttributeService productCategoryAttributeService;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listProductByPage(PageResult<Product> pageResult,ProductCondition condition){
		int rows = productDao.listProductByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<Product> list = productDao.listProductByPage(rowBounds,condition);
		for(Product product : list){
			// 查询产品产地
			ProductCategoryAttributeCondition pcaCondition = new ProductCategoryAttributeCondition();
			pcaCondition.setCategoryId(product.getCategoryId());
			pcaCondition.setLineAttrNameCn("产地");
			List<ProductCategoryAttribute> productCategoryAttributes = productCategoryAttributeService.listProductCategoryAttributeByCondition(pcaCondition);
			if(productCategoryAttributes != null && productCategoryAttributes.size()>0){
				ProductAttr productAttr = productAttrDao.getProductAttrByPidAndAttrId(product.getId(),productCategoryAttributes.get(0).getId());
				if(null != productAttr){
					List<ProductAttrValue> attrValues = productAttrValueDao.listProductAttrValueByProductAttrId(productAttr.getId());
					if(attrValues != null && attrValues.size()>0){
						product.setOrigin(attrValues.get(0).getLineAttrvalNameCn());
					}
				}
			}
		}
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 产品信息
	 */
	public Product getProductById(String id){
//		return productDao.getProductById(id);
		return null;
	}
	
	/**
	 * 新增
	 * @param item 产品信息
	 */
	public void add(Product item){
		productDao.add(item);
	}
	
	@Override
	public String doImport(String path) {
		String message = "";
		// 绝对路径
		path = WebUtils.getRealPath(path);
		
		// 根据品牌名查询品牌
		ProductBrandCondition brandCondition = new ProductBrandCondition();
		List<ProductBrand> brandList = productBrandDao.listProductBrand(brandCondition);
		Map<String, ProductBrand> brandMap = new HashMap<String, ProductBrand>();
		for(ProductBrand item : brandList){
			brandMap.put(item.getName().trim(), item);
		}
		// 全部的产品类别
		List<ProductCategory> categoryList = productCategoryDao.listProductCategory();
		
		// 获得全部产品
		List<Product> allProduct = productDao.listProductAndAttrVal();
		
		// 类别属性
		Map<String, List<ProductCategoryAttribute>> categoryAttrMap = new HashMap<String, List<ProductCategoryAttribute>>();
		for(ProductCategory item : categoryList){
			List<ProductCategoryAttribute> attrList = productCategoryAttributeDao.listProductCategoryAttributeByCategoryId(item.getId());
			for(ProductCategoryAttribute attr : attrList ){
				List<ProductCategoryAttributeValue> attrValueList = 
					productCategoryAttributeValueDao.listProductCategoryAttributeValueByCatePubAttrId(attr.getId());
				attr.setProCatAttrValList(attrValueList);
			}
			categoryAttrMap.put(item.getId(), attrList);
		}
		
		// 品牌类别
		List<SysDictItem> brandCategoryList = sysDictItemDao.listSysDictItemByDictId("492854501c7a11e4b26f00fffd437687");
		Map<String, SysDictItem> brandCategoryListMap = new HashMap<String, SysDictItem>();
		for(SysDictItem item : brandCategoryList){
			brandCategoryListMap.put(item.getName().trim(), item);
		}
		
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		try {
			fs = new POIFSFileSystem(new FileInputStream(path));
			wb = new HSSFWorkbook(fs);
		} catch (Exception e) {
			message = e.getMessage();
		}
		 
		if (null == wb) {
			return message;
		}
		 
		HSSFSheet sheet = wb.getSheetAt(1);
		// 获得总行数
		int rowNum = sheet.getLastRowNum();
		for(int i  = 1 ; i <= rowNum ; i ++){
			
			logger.info("===== 正在解析第 " + i + " 行 ======");
			
			HSSFRow row = sheet.getRow(i);
			Object[] value = new Object[23];
			
			if(StringUtils.isBlank(row.getCell(1).toString())){
				message += "第"+(i+1)+"行因无中文名 已跳过";
				continue;
			}
			for(int k = 0 ; k < 23  ; k ++){
				if(row.getCell(k) == null){
					row.createCell(k);
				}
				row.getCell(k).setCellType(HSSFCell.CELL_TYPE_STRING);
				value[k] = row.getCell(k).getStringCellValue().trim();
			}
			
			Product item = new Product();
			
			// 获得品牌
			ProductBrand brand = brandMap.get((String)value[0]);
			if(brand == null){
				// 如果为空则新增品牌。并刷新全部品牌MAP
				// 获得品牌类别
				String productCategoryName = value[19].toString();
				SysDictItem productCategory = brandCategoryListMap.get(productCategoryName);
				brand = new ProductBrand();
				brand.setBrandTypeId(productCategory.getId());
				brand.setName(value[0].toString());
				productBrandDao.add(brand);
				brandMap.put(brand.getName().trim(), brand);
			}
			item.setBrandId(brand.getId());
			item.setBrandName(brand.getName());
			item.setName(value[1].toString());
			item.setNameEn(value[2].toString());
			item.setShortDesc(value[12].toString());
			item.setShortDescEn(value[13].toString());
			
			String contentHtml = "<p style=\"font-family: '微软雅黑',Tahoma;  font-size: 14px;color:#365f91 \">"+(String)value[15]+"</p>";
			contentHtml = contentHtml.replaceAll("\r\n", "</br>");
			contentHtml = contentHtml.replaceAll("\n", "</br>");
			
			String contentHtmlEn = "<p style=\"font-family: Tahoma;  font-size: 14px;color:#333 \">"+(String)value[16]+"</p>";
			contentHtmlEn = contentHtmlEn.replaceAll("\r\n", "</br>");
			contentHtmlEn = contentHtmlEn.replaceAll("\n", "</br>");
			
			item.setHtmlContent(contentHtml);
			item.setHtmlContentEn(contentHtmlEn);
			item.setUnitPrice(Double.valueOf(value[17].toString().replace("￥","").trim()));
			item.setUnitPriceEn(value[18].toString());
			item.setKeyWords(item.getName());
			item.setKeyWordsEn(item.getNameEn()); 
			
			
			// 匹配旧数据
			boolean neetSaveNewItem = true;
			for(Product oldItem : allProduct ){
				if(oldItem.getName().replaceAll(" ","").equals(item.getName().replaceAll(" ",""))  && oldItem.getAttrVal().equals((String)value[5]) ){
					// 匹配到相应产品
					if(oldItem.getHtmlContent().indexOf("<img") <0 ){
						// 如果就数据还未改变
						productDao.delByIds(oldItem.getId().split(","));
					}else{
						neetSaveNewItem = false;
					}
				}
			}
			if(!neetSaveNewItem){
				continue;
			}
			
			// 查询产品类别
			String categoryLevelStr1 = "";
			String categoryLevelStr2 = "";
			String categoryLevelStr3 = "";
			if(StringUtils.isNotBlank((String)value[20])){
				categoryLevelStr1 = (String)value[20];
			}
			if(StringUtils.isNotBlank((String)value[21])){
				categoryLevelStr2 = (String)value[21];
			}
			if(StringUtils.isNotBlank((String)value[22])){
				categoryLevelStr3 = (String)value[22];
			}
			
			ProductCategory lv1 = null;
			ProductCategory lv2 = null;
			ProductCategory lv3 = null;
			
			if(StringUtils.isNotBlank(categoryLevelStr3)){
				// 匹配一级类别
				for( ProductCategory cat : categoryList  ){
					if(cat.getParentId().equals("0") && cat.getName().equals(categoryLevelStr1) ){
						lv1 = cat;
						break;
					}
				}
				if(lv1 == null){
					lv1 = new ProductCategory();
					lv1.setName(categoryLevelStr1);
					lv1.setParentId("0");
					lv1.setIsParent(1);
					productCategoryDao.add(lv1);
					categoryList.add(lv1);
				}
				
				// 匹配二级类别
				for( ProductCategory cat : categoryList  ){
					if(cat.getParentId().equals(lv1.getId()) && cat.getName().equals(categoryLevelStr2) ){
						lv2 = cat;
						break;
					}
				}
				if(lv2 == null){
					lv2 = new ProductCategory();
					lv2.setName(categoryLevelStr2);
					lv2.setParentId(lv1.getId());
					lv2.setIsParent(1);
					productCategoryDao.add(lv2);
					categoryList.add(lv2);
				}
				
				// 匹配三级类别
				for( ProductCategory cat : categoryList  ){
					if(cat.getParentId().equals(lv2.getId()) && cat.getName().equals(categoryLevelStr3) ){
						lv3 = cat;
						break;
					}
				}
				if(lv3 == null){
					lv3 = new ProductCategory();
					lv3.setName(categoryLevelStr3);
					lv3.setParentId(lv2.getId());
					lv3.setIsParent(0);
					productCategoryDao.add(lv3);
					categoryList.add(lv3);
				}
				item.setCategoryId(lv3.getId());
			}else{
				item.setCategoryId("0");
			}
			productDao.add(item);
			
			
			if(StringUtils.isBlank(categoryLevelStr3)){
				continue;
			}
			
			// 需要的属性
			String[] attrNameArray = {"产地" , "产品规格" ,"产品重量" , "适用人群","保质期"};
			// 格式化重量数据
			
			String attrValue3 = (String)value[7];
			if(StringUtils.isNotBlank(attrValue3)){
				Integer lastFlag = attrValue3.lastIndexOf(".");
				if(lastFlag > 0){
					attrValue3 = attrValue3.substring(0 ,lastFlag );
				}
				attrValue3 = attrValue3.replaceAll("（g）", "");
				attrValue3 = attrValue3.replaceAll("(g)", "");
				attrValue3 = attrValue3.replaceAll("克", "");
				attrValue3 = attrValue3.trim();
				attrValue3 += "g";
			}
			
			// 导入文件中对应的属性值
			List<String[]> attrValueArray = new ArrayList<String[]>(); 
			attrValueArray.add(new String[]{ value[3].toString() , value[4].toString() });
			attrValueArray.add(new String[]{ value[5].toString() , value[6].toString() });
			attrValueArray.add(new String[]{ attrValue3 , null });
			attrValueArray.add(new String[]{ value[8].toString() , value[9].toString() });
			attrValueArray.add(new String[]{ value[10].toString() , value[11].toString() });
			
			// 属性
			for(int n = 0 ; n < attrNameArray.length ; n ++){
				String[] valueItem = attrValueArray.get(n);
				if( StringUtils.isBlank(valueItem[0] )){
					continue;
				}
				
				List<ProductCategoryAttribute> attrList = categoryAttrMap.get(item.getCategoryId());
				if(attrList == null){
					attrList = new ArrayList<ProductCategoryAttribute>();
				}
				// 产地
				ProductCategoryAttribute attr1 = null;
				for(ProductCategoryAttribute attrItem : attrList){
					if(attrItem.getLineAttrNameCn().equals(attrNameArray[n])){
						attr1 = attrItem;
						break;
					}
				}
				if(attr1 == null){
					attr1 = new ProductCategoryAttribute();
					attr1.setCategoryId(lv3.getId());
					attr1.setIsbrand("0");
					attr1.setLineAttrNameCn(attrNameArray[n]);
					List<ProductCategoryAttributeValue> attrValueList = new ArrayList<ProductCategoryAttributeValue>();
					attr1.setProCatAttrValList(attrValueList);
					attr1.setSaleAttr("0");
					productCategoryAttributeDao.add(attr1);
					attrList.add(attr1);
					categoryAttrMap.put(item.getCategoryId(), attrList);
				}
				// 属性值
				ProductCategoryAttributeValue attrValue1 = null;
				for(ProductCategoryAttributeValue attrValueItem : attr1.getProCatAttrValList()){
					if(attrValueItem.getLineAttrvalNameCn().equals(valueItem[0] )){
						attrValue1 = attrValueItem;
					}
				}
				if(attrValue1 == null){
					attrValue1 = new ProductCategoryAttributeValue();
					attrValue1.setLineAttrvalNameCn( valueItem[0] );
					attrValue1.setLineAttrvalName( valueItem[1] );
					attrValue1.setCatePubAttrvalId(attr1.getId());
					attrValue1.setCatePubAttrId(attr1.getId());
					logger.info("=========== LineAttrvalName : " + valueItem[1] + " =============");
					productCategoryAttributeValueDao.add(attrValue1);
					attr1.getProCatAttrValList().add(attrValue1);
					attrList.add(attr1);
					categoryAttrMap.put(item.getCategoryId(), attrList);
				}
				ProductAttr pattr1 = new ProductAttr();
				pattr1.setProductId(item.getId());
				pattr1.setAttrId(attr1.getId());
				productAttrDao.add(pattr1);
				
				ProductAttrValue attrValue = new ProductAttrValue();
				attrValue.setProductAttrId(pattr1.getId());
				attrValue.setAttrId(attr1.getId());
				attrValue.setAttrName(attr1.getLineAttrNameCn());
				attrValue.setAttrValId(attrValue1.getId());
				attrValue.setLineAttrvalName(attrValue1.getLineAttrvalName());
				attrValue.setLineAttrvalNameCn(attrValue1.getLineAttrvalNameCn());
				productAttrValueDao.add(attrValue);
			}
		}
		
		return message;
	}

	/**
	 * API新增
	 * @param 	obj 	产品的JSON对象
	 * @return 	String	产品ID
	 */
	public String addApi(JSONObject obj){
		
		String productNo = String.valueOf(obj.get("id"));
		Product productDB = productDao.getProductByNo(productNo);
		
		// 如果数据库中已经存在该id的产品，则将与该产品相关的记录删除，之后再执行新增
		if(null != productDB){
			// 删除产品记录
			productDao.delByProductNo(productNo);
			// 删除产品属性表
			productAttrDao.delByProductId(productDB.getId());
			// 删除产品库存表
			productInventoryDao.delByProductId(productDB.getId());
			// 删除产品包装信息表
			productPackageDao.delByProductId(productDB.getId());
			// 删除产品销售属性设置信息表
			productSaleSettingDao.delByProductId(productDB.getId());
			// 删除产品SKU信息表
			productSkuDao.delByProductId(productDB.getId());
			// 删除产品自定义规格信息表
			productSpecSelfDefDao.delByProductId(productDB.getId());
			// 删除产品折扣区间
			wholesaleRangeDao.delByProductId(productDB.getId());
		}
		
		// 解析产品数据
		Product product = new Product();
		product.setProductNo(String.valueOf(obj.get("id")));
		product.setCreateBy("API-dunhuang");
		product.setCategoryId(String.valueOf(obj.get("categoryId")));
		product.setName(String.valueOf(obj.get("name")));
		product.setImageUrl(String.valueOf(obj.get("imageUrl")));
		product.setKeyWords(String.valueOf(obj.get("keyWords")));
		product.setShortDesc(String.valueOf(obj.get("shortDesc")));
		product.setHtmlContent(String.valueOf(obj.get("htmlContent")));
		product.setVideoUrl(String.valueOf(obj.get("videoUrl")));
		
		logger.info("======= 开始新增产品：" + product.getId() + " === 产品名称：" + product.getName());
		// 添加产品信息表数据
		productDao.add(product);
		
		logger.info("======= 开始新增产品图片表 =======");
		JSONArray fileArray = (JSONArray)obj.get("prodAttachList");
		if (fileArray != null && fileArray.size() > 0) {
			for(int i=0; i<fileArray.size(); i++){
				JSONObject fileJson = (JSONObject)fileArray.get(i);
				// 解析产品图文数据
				SysUploadFile file = new SysUploadFile();
				// 关联对象的ID，此处是指产品ID
				file.setObjectId(product.getId());
				// 关联对象类型的ID，此处是指1-产品
				file.setObjectType("1");
				file.setCreateBy("API-dunhuang");
				file.setUrl(String.valueOf(fileJson.get("url")));
				// 新增产品图片
				fileDao.add(file);
			}
		}
		
		logger.info("======= 开始新增产品属性表 =======");
		JSONArray attrArray = (JSONArray)obj.get("prodAttrList");
		if (attrArray != null && attrArray.size() > 0) {
			for(int i=0; i<attrArray.size(); i++){
				JSONObject attrJson = (JSONObject)attrArray.get(i);
				// 解析产品属性数据
				ProductAttr attr = new ProductAttr();
				// 关联对象的ID，此处是指产品ID
				attr.setProductId(product.getId());
				attr.setCreateBy("API-dunhuang");
				attr.setAttrId(String.valueOf(attrJson.get("attrId")));
				// 新增产品属性
				productAttrDao.add(attr);
				
				// 解析产品属性值
				JSONArray array = (JSONArray)attrJson.get("prodAttrValueList");
				if (array != null && array.size() > 0) {
					for(int j=0;j<array.size();j++){
						JSONObject objAttrValue = (JSONObject)array.get(j);
						ProductAttrValue attrValue = new ProductAttrValue();
						attrValue.setAttrId(String.valueOf(objAttrValue.get("attrId")));
						attrValue.setAttrName(String.valueOf(objAttrValue.get("attrName")));
						attrValue.setAttrValId(String.valueOf(objAttrValue.get("attrValId")));
						attrValue.setLineAttrvalName(String.valueOf(objAttrValue.get("lineAttrvalName")));
						attrValue.setLineAttrvalNameCn(String.valueOf(objAttrValue.get("lineAttrvalNameCn")));
						attrValue.setPicUrl(String.valueOf(objAttrValue.get("picUrl")));
						attrValue.setProductAttrId(attr.getId());
						attrValue.setCreateBy("API-dunhuang");
						
						productAttrValueDao.add(attrValue);
					}
				}
			}
		}
		
		// 添加产品库存表数据
		logger.info("======= 开始新增产品库存表 =======");
		JSONObject inventoryObj = (JSONObject)obj.get("prodInventory");
		ProductInventory proInventory = new ProductInventory();
		proInventory.setProductId(product.getId());
		proInventory.setInventoryLocation(String.valueOf(inventoryObj.get("inventoryLocation")));
		proInventory.setInventoryOriQty((Long)inventoryObj.get("inventoryOriQty"));
		proInventory.setInventoryQty((Long)inventoryObj.get("inventoryQty"));
		proInventory.setInventoryStatus((Long)inventoryObj.get("inventoryStatus"));
		proInventory.setCreateBy("API-dunhuang");
		productInventoryDao.add(proInventory);
		
		
		// 添加产品包装信息表数据
		logger.info("======= 开始新增产品包装信息表 =======");
		JSONObject packageObj = (JSONObject)obj.get("prodPackage");
		ProductPackage proPackage = new ProductPackage();
		proPackage.setProductId(product.getId());
		proPackage.setGrossWeight((Double)packageObj.get("grossWeight"));
		proPackage.setHeight((Double)packageObj.get("height"));
		proPackage.setLength((Double)packageObj.get("length"));
		proPackage.setLots((Long)packageObj.get("lots"));
		proPackage.setMeasureId(String.valueOf(packageObj.get("measureId")));
		proPackage.setPackingQuantity((Long)packageObj.get("packingQuantity"));
		proPackage.setWidth((Double)packageObj.get("width"));
		proPackage.setCreateBy("API-dunhuang");
		productPackageDao.add(proPackage);
		
		
		// 添加产品包装重量信息表数据
		logger.info("======= 开始新增产品包装重量信息表 =======");
		JSONObject weightRangeObj = (JSONObject)packageObj.get("prodWeightRange");
		if (weightRangeObj != null) {
			ProductWeightRange proWeightRange = new ProductWeightRange();
			proWeightRange.setPackageId(proPackage.getId());
			proWeightRange.setBaseQt((Long)weightRangeObj.get("baseQt"));
			proWeightRange.setIsOnlyWeight(String.valueOf(weightRangeObj.get("isOnlyWeight")));
			proWeightRange.setStepQt((Long)weightRangeObj.get("stepQt"));
			proWeightRange.setStepWeight((Double)weightRangeObj.get("stepWeight"));
			proWeightRange.setCreateBy("API-dunhuang");
			productWeightRangeDao.add(proWeightRange);
		}
		
		
		// 添加产品销售属性设置信息表数据
		logger.info("======= 开始新增产品销售属性设置信息表 =======");
		JSONObject settingObj = (JSONObject)obj.get("prodSaleSetting");
		ProductSaleSetting proSaleSetting = new ProductSaleSetting();
		proSaleSetting.setProductId(product.getId());
		proSaleSetting.setLeadingTime((Long)settingObj.get("leadingTime"));
		proSaleSetting.setMaxSaleQty((Long)settingObj.get("maxSaleQty"));
		proSaleSetting.setPriceConfigType((Long)settingObj.get("priceConfigType"));
		proSaleSetting.setCreateBy("API-dunhuang");
		productSaleSettingDao.add(proSaleSetting);
		
		
		// 添加产品SKU信息表数据
		logger.info("======= 开始新增产品SKU信息表 =======");
		JSONArray skuArray = (JSONArray)obj.get("prodSkuList");
		if (skuArray != null && skuArray.size() > 0) {
			for(int i=0; i<skuArray.size(); i++){
				JSONObject skuJson = (JSONObject)skuArray.get(i);
				// 解析产品SKU数据
				ProductSku sku = new ProductSku();
				// 关联对象的ID，此处是指产品ID
				sku.setProductId(product.getId());
				sku.setInventory((Long)skuJson.get("inventory"));
				sku.setRetailPrice((Double)skuJson.get("retailPrice"));
				sku.setSaleStatus((Long)skuJson.get("saleStatus"));
				sku.setSkuCode(String.valueOf(skuJson.get("skuCode")));
				sku.setCreateBy("API-dunhuang");
				// 新增产品SKU
				productSkuDao.add(sku);
				
				// 解析产品SKU对应的属性值
				JSONArray array = (JSONArray)skuJson.get("prodSkuAttrvalList");
				if (array != null && array.size() > 0) {
					for(int j=0;j<array.size();j++){
						JSONObject skuValueObj = (JSONObject)array.get(j);
						ProductSkuAttrval skuValue = new ProductSkuAttrval();
						skuValue.setAttrId(String.valueOf(skuValueObj.get("attrId")));
						skuValue.setAttrValId(String.valueOf(skuValueObj.get("attrValId")));
						skuValue.setSizeSpecType((Long)skuValueObj.get("sizeSpecType"));
						skuValue.setSkuId(sku.getId());
						skuValue.setCreateBy("API-dunhuang");
						// 新增产品SKU对应的属性值
						productSkuAttrvalDao.add(skuValue);
					}
				}
			}
		}
		
		// 添加产品自定义规格信息表数据
		logger.info("======= 开始新增产品自定义规格信息表 =======");
		JSONArray selfArray = (JSONArray)obj.get("prodSpecSelfDefList");
		if (selfArray != null && selfArray.size() > 0) {
			for(int i=0; i<selfArray.size(); i++){
				JSONObject selfJson = (JSONObject)selfArray.get(i);
				// 解析产品自定义规格数据
				ProductSpecSelfDef selfDef = new ProductSpecSelfDef();
				// 关联对象的ID，此处是指产品ID
				selfDef.setProductId(product.getId());
				selfDef.setAttrValId(String.valueOf(selfJson.get("attrValId")));
				selfDef.setAttrValName(String.valueOf(selfJson.get("attrValName")));
				selfDef.setPicUrl(String.valueOf(selfJson.get("picUrl")));
				selfDef.setCreateBy("API-dunhuang");
				// 新增产品自定义规格
				productSpecSelfDefDao.add(selfDef);
			}
		}
		
		
		// 添加产品折扣区间数据
		logger.info("======= 开始新增产品折扣区间表 =======");
		JSONArray wholesaleArray = (JSONArray)obj.get("prodWholesaleRangeList");
		if (wholesaleArray != null && wholesaleArray.size() > 0) {
			for(int i=0; i<wholesaleArray.size(); i++){
				JSONObject wholesaleJson = (JSONObject)wholesaleArray.get(i);
				// 解析产品折扣区间数据
				ProductWholesaleRange wholesaleRange = new ProductWholesaleRange();
				// 关联对象的ID，此处是指产品ID
				wholesaleRange.setProductId(product.getId());
				wholesaleRange.setDiscount((Double)wholesaleJson.get("discount"));
				wholesaleRange.setStartQty((Long)wholesaleJson.get("startQty"));
				wholesaleRange.setCreateBy("API-dunhuang");
				// 新增产品折扣区间
				wholesaleRangeDao.add(wholesaleRange);
			}
		}
		
		return product.getId();
	}
	
	/**
	 * 批量设置产品为促销产品
	 * @param ids
	 * @return
	 */
	public void updateIsPromotion(String[] ids,Integer isPromotion,double discount){
		for (String id : ids) {
			Product product = productDao.getProductById(id);
			product.setIsPromotion(isPromotion);
			product.setDiscount(discount);
			productDao.update(product);
		}
	}
	
	/**
	 * 批量设置产品为推荐产品
	 * @param ids
	 * @return
	 */
	public void updateIsRecommend(String[] ids,Integer isRecommend){
		for (String id : ids) {
			Product product = productDao.getProductById(id);
			product.setIsRecommend(isRecommend);
			productDao.update(product);
		}
	}
	
	/**
	 * 批量设置产品为推荐产品
	 * @param ids
	 * @return
	 */
	public void updateIsStar(String[] ids,Integer isStarProduct){
		for (String id : ids) {
			Product product = productDao.getProductById(id);
			product.setIsStarProduct(isStarProduct);
			productDao.update(product);
		}
	}

	/**
	 * 修改
	 * @param item 产品信息
	 */
	public void update(Product item){
		productDao.update(item);
	}
	
	/**
	 * 保存产品首图url
	 * @param item 
	 */
	public void saveOneImgUrl(Product item){
		productDao.saveOneImgUrl(item);
	}
	
	/**
	 * 保存默认价格
	 * @param item 
	 */
	public void saveDefaultPrice(Product item){
		productDao.saveDefaultPrice(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		productDao.delByIds(ids);
	}
	
	/**
	 * 根据类别ID查询
	 */
	public List<Product> listProductByCategoryId(String categoryId){
		return productDao.listProductByCategoryId(categoryId);
	}
	
	/**
	 * 根据品牌ID查询
	 */
	public List<Product> listProductByBrandId(String brandId){
		return productDao.listProductByBrandId(brandId);
	}

	/**
	 * 修改产品类别
	 * @param product
	 */
	public void changeCategory(String productIds[],String categoryId){
		for(String productId : productIds){
			Product product = productDao.getProductById(productId);
			product.setCategoryId(categoryId);
			productDao.changeCategory(product);
		}
	}
	
	/**
	 * 批量导出产品
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("rawtypes")
	public Workbook batchExport(){
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("sheet1");
		String[] titles = new String[] {"产品名称","产品品牌","产品类别","价格","产地","产品规格","产品重量","适用人群","保质期"};
		Integer[] titleWidths = new Integer[] { 30, 20, 20,15,15,15,15,20,15};
		// 列头
		Excel2007Util.createRow(titles, titleWidths, wb, sheet, 0);
		
		Map<String,Product> map = new HashMap<String,Product>();
		List<Product> productList = productDao.listProduct();
		List<ProductDownloadVO> vos = null;
		for(Product product : productList){
			Product pro = map.get(product.getId());
			if(null == pro){
				pro = product;
				map.put(product.getId(), pro);
				vos = new ArrayList<ProductDownloadVO>();
				pro.setVoList(vos);
			}
			ProductDownloadVO vo = new ProductDownloadVO();
			vo.setAttr(product.getAttr());
			vo.setAttrVal(product.getAttrVal());
			vos.add(vo);
		}
		
		int currentRow = 1;
		
		Iterator iter = map.values().iterator();
		while(iter.hasNext()){
			Product product = (Product) iter.next();
			List<ProductDownloadVO> voList = product.getVoList();
			
			String attrValue1 = "";	//产地
			String attrValue2 = "";	//产品规格
			String attrValue3 = "";	//产品重量
			String attrValue4 = "";	//适用人群
			String attrValue5 = "";	//保质期
			
			for(ProductDownloadVO vo : voList){
				if("产地".equals(vo.getAttr())){
					attrValue1 = vo.getAttrVal();
				}else if("产品规格".equals(vo.getAttr())){
					attrValue2 = vo.getAttrVal();
				}else if("产品重量".equals(vo.getAttr())){
					attrValue3 = vo.getAttrVal();
				}else if("适用人群".equals(vo.getAttr())){
					attrValue4 = vo.getAttrVal();
				}else if("保质期".equals(vo.getAttr())){
					attrValue5 = vo.getAttrVal();
				}
			}
		String values[] = new String[]{
					product.getName(),product.getBrandName(),product.getCategoryName(),
					product.getUnitPrice()+"",attrValue1,attrValue2,attrValue3,attrValue4,attrValue5
		};
		Excel2007Util.createRow(values, titleWidths, wb, sheet, currentRow);
		currentRow++;	
		}
		return wb;
	}
}

