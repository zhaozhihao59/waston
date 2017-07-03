package com.tocersoft.product.action.api;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.product.entity.ProductCategory;
import com.tocersoft.product.entity.ProductCategoryAttribute;
import com.tocersoft.product.model.ProductCategoryAttributeModel;
import com.tocersoft.product.model.ProductCategoryModel;
import com.tocersoft.product.service.IProductCategoryAttributeService;
import com.tocersoft.product.service.IProductCategoryService;

/**
 * 产品商城-类别
 * @author fangquan
 * @createDate 2013-10-28 上午10:10:17
 */
@ParentPackage("api")
@Namespace("/api/product/category/attribute")
@Controller
public class ProductCategoryAttributeApiAction extends BaseAction implements ModelDriven<ProductCategoryAttributeModel> {
	
	private static final long serialVersionUID = -8337375042837415260L;
	
	private ProductCategoryAttributeModel model = new ProductCategoryAttributeModel();
	
	@Resource
	private IProductCategoryAttributeService categoryAttributeService;
	
	/**
	 * 新增/修改
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value="doAdd")
	public String doAdd(){
		
		JSONObject result = new JSONObject();
		
		// 接口授权验证
		String authAccount = model.getAuthAccount();
		String authPassword = model.getAuthPassword();
		if(StringUtils.isBlank(authAccount) || StringUtils.isBlank(authPassword)){
			result.put("status", "100");
			result.put("message", "验证不通过-接口授权帐号或密码为空");
			return ajax(result);
		}else{
			if(authAccount.equals("dunhuang") && authPassword.equals("DunHuang2014!@#")){
				
			}else{
				result.put("status", "200");
				result.put("message", "验证不通过-接口授权帐号或密码错误");
				return ajax(result);
			}
		}
		
		List<ProductCategoryAttribute> categoryAttributeList = model.getItemList();
		categoryAttributeService.addList(categoryAttributeList);
		
		result.put("status", "000");
		result.put("message", "保存成功");
		return ajax(result);
	}
	
	/**
	 * 跳转到接口测试页面
	 * @return
	 */
	@Action(value = "test", results = { @Result(name = "index", location = "/WEB-INF/pages/api/product/test.jsp") })
	public String test() {
		return INDEX;
	}
	
	@Override
	public ProductCategoryAttributeModel getModel() {
		return model;
	}
}
