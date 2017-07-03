package com.tocersoft.product.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.product.dto.ProductAttrValueCondition;
import com.tocersoft.product.dto.ProductCategoryAttributeCondition;
import com.tocersoft.product.entity.ProductAttrValue;
import com.tocersoft.product.entity.ProductCategoryAttribute;
import com.tocersoft.product.model.ProductAttrValueModel;
import com.tocersoft.product.service.IProductAttrValueService;
import com.tocersoft.product.service.IProductCategoryAttributeService;


@ParentPackage("admin")
@Namespace("/admin/product/attr/value")
@Controller
public class ProductAttrValueAction extends BaseAction implements ModelDriven<ProductAttrValueModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(ProductAttrValueAction.class);

	private ProductAttrValueModel model = new ProductAttrValueModel();

	@Resource(name = "productAttrValueServiceImpl")
	private IProductAttrValueService productAttrValueService;
	@Resource(name = "productCategoryAttributeServiceImpl")
	private IProductCategoryAttributeService productCategoryAttributeService;

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
	@Action(value = "listProductAttrValueByPage")
	public String listProductAttrValueByPage(){
		ProductAttrValueCondition conditionAttr = new ProductAttrValueCondition();
		conditionAttr.setCategoryId(model.getCategoryId());
		productAttrValueService.listProductAttrValueByPage(model.getPageResult(),conditionAttr);
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","attrName","lineAttrvalNameCn","attrId"});
		return ajax(root);
	}
	
	/**
	 * 属性分页查询
	 * @return 
	 */
	@Action(value = "listPruductAttrAndCategory")
	public String listPruductAttrAndCategory(){
		List<ProductAttrValue> result = productAttrValueService.listPruductAttrAndCategory(model.getCategoryId(),model.getProductId());
		JSONObject root = WebUtils.toJsonResultList(result, new String[]{"id","attrId","attrName","lineAttrvalNameCn"});
		return ajax(root);
	}

	/**
	 * 新增
	 * @return
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/product/product_attrValue_edit.jsp") })
	public String toAdd(){
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/product/product_Attrvalues_update.jsp") })
	public String toUpdate(){
		ProductAttrValue item = productAttrValueService.getProductAttrValueById(model.getItem().getId());
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
			productAttrValueService.delByIds(model.getSelIds().split(","));
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
			
			String productId = model.getProductId();
			String attrId = model.getAttrId();
			String attrValueId = model.getAttrValueId();
			
			ProductAttrValue value = productAttrValueService.getProductAttrValueByPidAndAttrId(productId, attrId);
			
			// 产品属性不为空时，表示已经选择过产品属性
			if(null != value){
				productAttrValueService.updateAttrValueId(attrValueId, value.getId());
				
			// 产品属性为空时，表示还没有选择过产品属性
			}else{
				value = new ProductAttrValue();
				value.setAttrId(attrId);
				value.setAttrValId(attrValueId);
				productAttrValueService.add(value, productId);
			}
			
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public ProductAttrValueModel getModel() {
		return model;
	}
}

