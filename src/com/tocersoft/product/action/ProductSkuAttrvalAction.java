package com.tocersoft.product.action;

import java.util.ArrayList;
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
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.product.model.ProductSkuAttrvalModel;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.tocersoft.product.entity.ProductCategoryAttributeValue;
import com.tocersoft.product.entity.ProductSku;
import com.tocersoft.product.entity.ProductSkuAttrval;
import com.tocersoft.product.service.IProductCategoryAttributeValueService;
import com.tocersoft.product.service.IProductSkuAttrvalService;
import com.tocersoft.product.service.IProductSkuService;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.base.action.BaseAction;


@ParentPackage("admin")
@Namespace("/admin/productSkuAttrval")
@Controller
public class ProductSkuAttrvalAction extends BaseAction implements ModelDriven<ProductSkuAttrvalModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(ProductSkuAttrvalAction.class);

	private ProductSkuAttrvalModel model = new ProductSkuAttrvalModel();

	@Resource(name = "productSkuAttrvalServiceImpl")
	private IProductSkuAttrvalService productSkuAttrvalService;
	@Resource(name = "productSkuServiceImpl")
	private IProductSkuService productSkuService;
	@Resource(name = "productCategoryAttributeValueServiceImpl")
	private IProductCategoryAttributeValueService proCatAttrValService;

	static List<String[]> stringList=new ArrayList<String[]>();


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
	@Action(value = "listProductSkuAttrvalByPage")
	public String listProductSkuAttrvalByPage(){
		productSkuAttrvalService.listProductSkuAttrvalByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{});
		return ajax(root);
	}

	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String toAdd(){
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String toUpdate(){
		ProductSkuAttrval item = productSkuAttrvalService.getProductSkuAttrvalById(model.getItem().getId());
		model.setItem(item);
		return SUCCESS;
	}

	/**
	 * 删除
	 * @return 
	 */
	@Action(value="del")
	@Validations(requiredStrings = {
				@RequiredStringValidator(fieldName = "selIds", message = "ID不能为空")
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String del(){
		try {
			productSkuAttrvalService.delByIds(model.getSelIds().split(","));
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
		ProductSkuAttrval pkav = model.getItem();
		String productId=model.getProductId();
		String attrValIds[]=null;
		//清空数据
		List<ProductSku> skuList=productSkuService.listProductSkuByProductId(productId);
		if (skuList.size()>0) {
			productSkuAttrvalService.delBySkuIds(skuList);
			productSkuService.delBySkuIds(skuList);
		}
		
		//	list [{"sttrValId1","sttrValId2","sttrValId3"} , {"sttrValId4","sttrValId5"} , {"sttrValId6"}]
		List<String[]> list=new ArrayList<String[]>();
				
		if (!"".equals(pkav.getAttrValIds())) {
//			拿到前台传过来的属性值ID字符串  转成数组 {"sttrValId1,sttrValId2,sttrValId3" , "sttrValId4,sttrValId5" , "sttrValId6"}
			attrValIds=pkav.getAttrValIds().split(";");
			
			// 把每个属性值ID独立分开 并按属性分组 放到list
			for (int i = 0; i < attrValIds.length; i++) {
				list.add(attrValIds[i].split(","));
			}
			// 属性值ID交叉组合后重新放到list
			ProductSkuAttrvalAction.test(list, list.get(0), "");
		}
		
		//	拿到SKU个数
		int row=stringList.size();
		
		ProductSku sku=new ProductSku();
		sku.setProductId(productId);
		//	把当前产品ID插入sku表
		for (int i = 0; i < row; i++) {
			productSkuService.add(sku);
		}
		//	查出刚刚添加的sku
		skuList=productSkuService.listProductSkuByProductId(productId);
		
		ProductSkuAttrval proSkuAttrval=new ProductSkuAttrval();
		try {
			for (int i = 0; i < row; i++) {
				String skuId=skuList.get(i).getId();
				String [] strings=stringList.get(i);
			   for (int j = 0; j < strings.length; j++) {
				   ProductCategoryAttributeValue proCatAttrVal=proCatAttrValService.getProductCategoryAttributeValueById(strings[j]);
				   proSkuAttrval.setAttrId(proCatAttrVal.getCatePubAttrId());
				   proSkuAttrval.setAttrValId(proCatAttrVal.getId());
				   proSkuAttrval.setSkuId(skuId);
				   productSkuAttrvalService.add(proSkuAttrval);
			   }
			}
		} catch (Exception e) {
			stringList.clear();
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
		}
		stringList.clear();
		
		return null;
	}


	private static void test(List<String[]> list, String[] arr, String str) {
	    for (int i = 0; i < list.size(); i++){
	        //取得当前的数组
	        if (i == list.indexOf(arr)){
	            //迭代数组  
	            for (String st : arr){
	                st = str + st+",";  
	                if (i < list.size() - 1){
	                    test(list, list.get(i + 1), st);
	                }  
	                else if (i == list.size() - 1){
	                	stringList.add(st.split(","));
	                }
	            }
	        }
	    }
	}

	@Override
	public ProductSkuAttrvalModel getModel() {
		return model;
	}
}

