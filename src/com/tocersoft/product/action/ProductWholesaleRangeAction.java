package com.tocersoft.product.action;

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
import com.tocersoft.product.entity.ProductWholesaleRange;
import com.tocersoft.product.model.ProductWholesaleRangeModel;
import com.tocersoft.product.service.IProductWholesaleRangeService;


@ParentPackage("admin")
@Namespace("/admin/productWholesaleRange")
@Controller
public class ProductWholesaleRangeAction extends BaseAction implements ModelDriven<ProductWholesaleRangeModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(ProductWholesaleRangeAction.class);

	private ProductWholesaleRangeModel model = new ProductWholesaleRangeModel();

	@Resource(name = "productWholesaleRangeServiceImpl")
	private IProductWholesaleRangeService productWholesaleRangeService;



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
	@Action(value = "listProductWholesaleRangeByPage")
	public String listProductWholesaleRangeByPage(){
		if(!model.getCondition().getProductId().isEmpty()){
			productWholesaleRangeService.listProductWholesaleRangeByPage(model.getPageResult(),model.getCondition());
		}
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","startQty","discount"
		});
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
		ProductWholesaleRange item = productWholesaleRangeService.getProductWholesaleRangeById(model.getItem().getId());
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
			productWholesaleRangeService.delByIds(model.getSelIds().split(","));
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
				productWholesaleRangeService.add(model.getItem());
			}else{
				productWholesaleRangeService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public ProductWholesaleRangeModel getModel() {
		return model;
	}}

