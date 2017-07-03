package com.tocersoft.product.action.api;

import java.util.Date;

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
import com.tocersoft.product.model.ProductCategoryModel;
import com.tocersoft.product.service.IProductCategoryService;

/**
 * 产品商城-类别
 * @author fangquan
 * @createDate 2013-10-28 上午10:10:17
 */
@ParentPackage("api")
@Namespace("/api/product/category")
@Controller
public class ProductCategoryApiAction extends BaseAction implements ModelDriven<ProductCategoryModel> {
	
	private static final long serialVersionUID = -8337375042837415260L;
	
	private ProductCategoryModel model = new ProductCategoryModel();
	
	@Resource
	private IProductCategoryService categoryService;
	
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
		
		try {
			ProductCategory category = model.getItem();
			if(StringUtils.isBlank(category.getId())){
				result.put("status", "400");
				result.put("message", "验证不通过-类别ID不能为空");
				return ajax(result);
			}
			if(StringUtils.isBlank(category.getName())){
				result.put("status", "401");
				result.put("message", "验证不通过-类别名称不能为空");
				return ajax(result);
			}
			if(StringUtils.isBlank(category.getParentId())){
				result.put("status", "402");
				result.put("message", "验证不通过-类别父级ID不能为空");
				return ajax(result);
			}
			// 验证ID是否唯一
			ProductCategory cate = categoryService.getProductCategoryById(category.getId());
			if(null != cate){
				result.put("status", "405");
				result.put("message", "验证不通过-类别ID已存在");
				return ajax(result);
			}
			ProductCategory cateParent = categoryService.getProductCategoryById(category.getParentId());
			if(null != cateParent){
				result.put("status", "406");
				result.put("message", "验证不通过-类别父级ID不存在，请先保存父级类别");
				return ajax(result);
			}
			
			category.setCreateBy("dunhuang");
			categoryService.add(category);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "300");
			result.put("message", "保存出错-保存时出现异常");
			return ajax(result);
		}
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
	public ProductCategoryModel getModel() {
		return model;
	}
}
