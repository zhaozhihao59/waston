package com.tocersoft.product.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.member.entity.MemLevel;
import com.tocersoft.product.model.ProductCategoryAttributeModel;
import com.tocersoft.product.model.ProductCategoryAttributeValueModel;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.tocersoft.product.dao.IProductSkuDao;
import com.tocersoft.product.dto.ProductCategoryAttributeCondition;
import com.tocersoft.product.dto.ProductCategoryAttributeValueCondition;
import com.tocersoft.product.entity.Product;
import com.tocersoft.product.entity.ProductCategoryAttribute;
import com.tocersoft.product.entity.ProductCategoryAttributeValue;
import com.tocersoft.product.entity.ProductSku;
import com.tocersoft.product.entity.ProductSkuAttrval;
import com.tocersoft.product.service.IProductCategoryAttributeService;
import com.tocersoft.product.service.IProductCategoryAttributeValueService;
import com.tocersoft.product.service.IProductSkuAttrvalService;
import com.tocersoft.product.service.IProductSkuService;
import com.tocersoft.base.utils.UUIDUtil;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.action.BaseAction.Status;


@ParentPackage("admin")
@Namespace("/admin/productCategoryAttribute")
@Controller
public class ProductCategoryAttributeAction extends BaseAction implements ModelDriven<ProductCategoryAttributeModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(ProductCategoryAttributeAction.class);

	private ProductCategoryAttributeModel model = new ProductCategoryAttributeModel();
	private ProductCategoryAttributeValueModel valueModel = new ProductCategoryAttributeValueModel();

	@Resource
	private IProductCategoryAttributeService categoryAttributeService;
	@Resource
	private IProductCategoryAttributeValueService categoryAttributeValueService;
	@Resource
	private IProductSkuService productSkuService;
	@Resource
	private IProductSkuAttrvalService productSkuAttrvalService;

	/**
	 * 首页
	 * @return
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String index(){
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listProductCategoryAttributeByPage")
	public String listProductCategoryAttributeByPage(){
		String categoryId = model.getCategoryId();
		ProductCategoryAttributeCondition condition = model.getCondition();
		condition.setCategoryId(categoryId);
		categoryAttributeService.listProductCategoryAttributeByPage(model.getPageResult(),condition);
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","lineAttrNameCn","attributeValue","saleAttr"});
		return ajax(root);
	}
	
	
	/**
	 * 分页查询属性值
	 * @return 
	 */
	@Action(value = "listProductAttributeByPage")
	public String listProductAttributeByPage(){
		String attrId = valueModel.getAttrId();
		ProductCategoryAttributeValueCondition condition = new ProductCategoryAttributeValueCondition();
		condition.setCatePubAttrvalId(attrId);
		categoryAttributeValueService.listProductCategoryAttributeValueByPage(valueModel.getPageResult(),condition);
		JSONObject root = WebUtils.toPageJson(valueModel.getPageResult(), new String[]{"id","lineAttrNameCn","attributeValue","saleAttr"});
		return ajax(root);
	}
	
	
	/**
	 * 用于构造自定义SKU表格
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "listProductCategoryAttributeByCondition")
	public String listProductCategoryAttributeByCondition(){
		
		String productId = model.getProductId();
		//根据产品ID列出该产品所有的已选销售属性
		List<ProductCategoryAttribute> productCategoryAttrList=categoryAttributeService.listProductCategoryAttributeByProductId(productId);
		JSONObject result = new JSONObject();
		// 构造attr列表
		JSONArray arrayAttr = new JSONArray();
		for(ProductCategoryAttribute attr : productCategoryAttrList){
			
			JSONObject obj = new JSONObject();
			obj.put("id", attr.getId());
			obj.put("name", attr.getLineAttrNameCn());
			arrayAttr.add(obj);
		}
		
		result.put("attr", arrayAttr);
		
		return ajax(result);
	}
	
	
	/**
	 * 加载自定义SKU表格的数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "listSKUTable")
	public String listSKUTable(){
		
		// 构造成JSON数据传送至前台
		// {"resultList":[{"id":uuid1,"attrID1":attrValue1,"attrID2":attrValue2,"price":price,"inventory":inventory},{"id":uuid1,"attrID1":attrValue1,"attrID2":attrValue2,"price":price,"inventory":inventory}]}
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		String productId = model.getProductId();
		
		List<ProductSku> skuList = productSkuService.listProductSkuByProductId(productId);
		
		//key : skuId     value : List<ProductSkuAttrval>
		Map<String,List<ProductSkuAttrval>> productAttrValueMap = new LinkedHashMap<String,List<ProductSkuAttrval>>();
		if (skuList.size()>0) {
			List<ProductSkuAttrval> skuAttrvalList = productSkuAttrvalService.listProductSkuAttrvalBySkuIds(skuList);
			for (ProductSkuAttrval productSkuAttrval : skuAttrvalList) {
				List<ProductSkuAttrval> proSkuAttrvalList =productAttrValueMap.get(productSkuAttrval.getSkuId());
				if(null == proSkuAttrvalList){
					proSkuAttrvalList = new ArrayList<ProductSkuAttrval>();
					productAttrValueMap.put(productSkuAttrval.getSkuId(), proSkuAttrvalList);
				}
				proSkuAttrvalList.add(productSkuAttrval);
			}
		}
		

		Collection<List<ProductSkuAttrval>> proSkuAttrVals = productAttrValueMap.values();
		
		for (List<ProductSkuAttrval> list : proSkuAttrVals) {
			JSONObject itemObj = new JSONObject();
			itemObj.put("id", UUIDUtil.uuid());
			
			boolean bl=false;
			for (ProductSkuAttrval productSkuAttrval : list) {
				ProductCategoryAttributeValue pcav= categoryAttributeValueService.getProductCategoryAttributeValueById(productSkuAttrval.getAttrValId());
				
				// 发现有垃圾数据存在pcav为空的情况，为了避免以后出现这种情况，特此加上非空判断 -- 方泉 2014-07-19
				if(null == pcav){
					continue;
				}
				
				itemObj.put("attrId_"+productSkuAttrval.getAttrId(), pcav.getLineAttrvalNameCn());
				itemObj.put("skuId", productSkuAttrval.getSkuId());
				if (bl) {
					ProductSku sku=productSkuService.getProductSkuById(productSkuAttrval.getSkuId());
					itemObj.put("price",sku.getRetailPrice() == null ? "" : sku.getRetailPrice());
					itemObj.put("inventory",sku.getInventory() == null ? "" : sku.getInventory());
				}
				bl=true;
			}
			array.add(itemObj);
		}
		
		
		
		
		//查找共计多少行 list[ {list{属性值，属性值}}，{list{属性值，属性值}}，{list{属性值，属性值}} ]
//		List<List<ProductSkuAttrval>> combineAttrValueList = categoryAttributeService.loadCombineAttrValues(productId);
//		System.out.println("====================="+combineAttrValueList.size());
		
		
//		for(List<ProductSkuAttrval> row : combineAttrValueList){
//			JSONObject itemObj = new JSONObject();
//			
//			
//			itemObj.put("id", UUIDUtil.uuid());
//			for(ProductSkuAttrval col : row){
//				
//				ProductCategoryAttributeValue pcav= categoryAttributeValueService.getProductCategoryAttributeValueById(col.getAttrValId());
//				
//				itemObj.put("attrId_"+col.getAttrId(), pcav.getLineAttrvalNameCn());
//				
//				ProductSku sku=productSkuService.getProductSkuById(col.getSkuId());
//				
//				itemObj.put("memLevelId_p",sku.getRetailPrice() == null ? "" : sku.getRetailPrice());
//				itemObj.put("memLevelId_i",sku.getInventory() == null ? "" : sku.getInventory());
//			}
//			
//			
//			
////			for(int i=0;i<memLevelList.size();i++){
////				Double price = null;
////				MemLevel memLevel = memLevelList.get(i);
////				
////				
////				//根据组合产品,查找价格
////				ProProductPrice curMemLevelPrice = proProductPriceService.findCurMemLevelPrice(prices,row,memLevel);
////				if(null != curMemLevelPrice){
////					price = curMemLevelPrice.getPrice();
////				}
////				
////				
////				itemObj.put("memLevelId_"+memLevel.getId(),price == null ? "" : price);
////			}
//			
//			
//			// 将itemObj放入array中
//			array.add(itemObj);
//		}
		
		// 将array放入result中，发送至前台
		result.put("resultList", array);
		
		return ajax(result);
	}
	
	
	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/product/product_category_edit.jsp") })
	public String toAdd(){
		return SUCCESS;
	}
	
	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/product/product_category_edit.jsp") })
	public String toUpdate(){
		//属性值拼接
		List<String> Valueslist = new ArrayList<String>();
		//根据属性id取到所有属性值列表
		List<ProductCategoryAttributeValue> attrValueList = categoryAttributeValueService.listProductCategoryAttributeValueByCatePubAttrId(model.getItem().getId());
		for(ProductCategoryAttributeValue Values : attrValueList){
			//所有属性值集合
			Valueslist.add(Values.getLineAttrvalNameCn());
		}
		ProductCategoryAttribute item = categoryAttributeService.getProductCategoryAttributeById(model.getItem().getId());
		//将所有属性值拼接起来
		item.setAttributeValue(org.apache.commons.lang.StringUtils.join(Valueslist,","));
		String categoryId = model.getCategoryId();
		System.out.println(categoryId);
		model.getItem().setCategoryId(categoryId);
		model.setItem(item);
		return SUCCESS;
	}

	/**
	 * 批量删除删除s
	 * @return 
	 */
	@Action(value="del")
	@InputConfig(resultName = "ajaxError")
	public String del(){
		try {
			System.out.println(model.getSelIds());
			categoryAttributeService.delByIds(model.getSelIds().split(","));
			return ajax(Status.success,"删除成功");
		} catch (Exception e) {
			String msg = "删除时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}

	/**
	 * 保存
	 * @return 
	 */
	@Action(value="doSave")
	public String doSave(){
		try {
			if(StringUtils.isBlank(model.getItem().getId())){
				String addAttrValues = model.getAttrValues();
				categoryAttributeService.addAttrAndValue(model.getItem(), addAttrValues);
			}else{
				String UpdateAttrValues = model.getAttrValues();
				categoryAttributeService.updateAttrValue(model.getItem(), UpdateAttrValues);
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public ProductCategoryAttributeModel getModel() {
		return model;
	}}

