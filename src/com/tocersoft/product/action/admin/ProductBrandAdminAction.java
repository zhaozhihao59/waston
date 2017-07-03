package com.tocersoft.product.action.admin;

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
import com.tocersoft.product.entity.ProductBrand;
import com.tocersoft.product.model.ProductBrandModel;
import com.tocersoft.product.service.IProductBrandService;
import com.tocersoft.system.entity.SysDictItem;
import com.tocersoft.system.service.ISysDictItemService;


@ParentPackage("admin")
@Namespace("/admin/product/brand")
@Controller
public class ProductBrandAdminAction extends BaseAction implements ModelDriven<ProductBrandModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(ProductBrandAdminAction.class);

	private ProductBrandModel model = new ProductBrandModel();

	@Resource(name = "productBrandServiceImpl")
	private IProductBrandService productBrandService;
	@Resource(name = "sysDictItemServiceImpl")
	private ISysDictItemService sysDictItemService;


	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/product/product_brand_list.jsp") })
	public String index(){
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listProductBrandByPage")
	public String listProductBrandByPage(){
		productBrandService.listProductBrandByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","imagePath","name","nameEn","introduce","introduceEn","sort","brandTypeId","brandTypeName","isStarBrand"
		});
		return ajax(root);
	}

	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/product/product_brand_add.jsp") })
	public String toAdd(){
		// 进入新增页面加载品牌类型的数据字典项
		List<SysDictItem> brandTypeList = sysDictItemService.listSysDictItemByDictId("492854501c7a11e4b26f00fffd437687");
		model.setBrandTypeList(brandTypeList);
		
		return SUCCESS;
	}
	
	/**
	 * 设为明星品牌
	 * @return
	 */
	@Action(value = "setStarBrand")
	public String setStarBrand(){
		productBrandService.starBrand(model.getSelIds().split(","),1);
		return ajax(Status.success);
	}
	
	/**
	 * 取消明星品牌
	 * @return
	 */
	@Action(value = "cancelStarBrand")
	public String cancelStarBrand(){
		productBrandService.starBrand(model.getSelIds().split(","),0);
		return ajax(Status.success);
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/product/product_brand_add.jsp") })
	public String toUpdate(){
		
		// 进入页面加载品牌类型的数据字典项
		List<SysDictItem> brandTypeList = sysDictItemService.listSysDictItemByDictId("492854501c7a11e4b26f00fffd437687");
		model.setBrandTypeList(brandTypeList);
		
		ProductBrand item = productBrandService.getProductBrandById(model.getItem().getId());
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
			productBrandService.delByIds(model.getSelIds().split(","));
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
				model.getItem().setIsStarBrand(0);
				productBrandService.add(model.getItem());
			}else{
				productBrandService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public ProductBrandModel getModel() {
		return model;
	}}

