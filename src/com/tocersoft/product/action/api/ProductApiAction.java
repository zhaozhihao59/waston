package com.tocersoft.product.action.api;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.product.model.ProductModel;
import com.tocersoft.product.service.IProductService;
import com.tocersoft.product.service.impl.ProductServiceImpl;

/**
 * 产品商城-类别
 * @author fangquan
 * @createDate 2013-10-28 上午10:10:17
 */
@ParentPackage("api")
@Namespace("/api/product")
@Controller
public class ProductApiAction extends BaseAction implements ModelDriven<ProductModel> {
	
	private static final long serialVersionUID = -8337375042837415260L;
	
	private Log logger = LogFactory.getLog(ProductApiAction.class);
	
	private ProductModel model = new ProductModel();
	
	@Resource
	private IProductService productService;
	
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
		
		String itemString = model.getItem();
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject)parser.parse(itemString);
			String productId = productService.addApi(obj);
			
			result.put("status", "000");
			result.put("message", "保存成功");
			result.put("itemcode", productId);
			
			return ajax(result);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			result.put("status", "300");
			result.put("message", "接口调用出现异常，请检查数据类型是否匹配，或者请求管理员查看系统日志");
			return ajax(result);
		}
		
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
	public ProductModel getModel() {
		return model;
	}
}
