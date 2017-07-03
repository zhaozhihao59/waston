package com.tocersoft.product.action.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.auth.action.admin.DepartAction;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.action.BaseAction.Status;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.product.entity.ProductCategory;
import com.tocersoft.product.model.ProductCategoryModel;
import com.tocersoft.product.service.IProductCategoryService;

/**
 * 产品商城-类别
 * @author fangquan
 * @createDate 2013-10-28 上午10:10:17
 */
@ParentPackage("admin")
@Namespace("/admin/product/category")
@Controller
public class ProductCategoryAdminAction extends BaseAction implements ModelDriven<ProductCategoryModel> {
	private Log logger = LogFactory.getLog(DepartAction.class);
	private static final long serialVersionUID = -8337375042837415260L;
	
	private ProductCategoryModel model = new ProductCategoryModel();
	
	@Resource
	private IProductCategoryService categoryService;
	/**
	 * 类别
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value="list")
	public String list(){
		JSONArray root = new JSONArray();
		List<ProductCategory> list=new ArrayList<ProductCategory>();
		// 初次加载
		if(StringUtils.isBlank(model.getItem().getParentId())){
			// 构造“全部”项
			JSONObject topJson = new JSONObject();
			topJson.put("id", "0");
			topJson.put("name", "全部");
			topJson.put("sort", 0);
			topJson.put("location",0);
			topJson.put("parentId", "-1");
			topJson.put("isParent", true);
			topJson.put("open", true);
			
			list = categoryService.listProductCatgoryByParentId("0");
			JSONArray children = new JSONArray();
			for (int i = 0; i < list.size(); i++){
				JSONObject json = makeTreeJson(list.get(i));
				children.add(json);
			}
			topJson.put("children", children);
			root.add(topJson);
		
		// 点击后加载
		}else{
			list = categoryService.listProductCatgoryByParentId(model.getItem().getParentId());
			for (int i = 0; i < list.size(); i++){
				JSONObject json = makeTreeJson(list.get(i));
				root.add(json);
			}
		}
		return ajax(root);
	}
	
	/**
	 * 组装tree数据
	 * @param tasCategory
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private JSONObject makeTreeJson(ProductCategory category){
		JSONObject json=new JSONObject();
		json.put("id", category.getId());
		json.put("name", category.getName());
		json.put("parentId", category.getParentId());
		if(category.getIsParent() != null && category.getIsParent().intValue() == 1){
			json.put("isParent", true);
		}else{
			json.put("isParent", false);
		}
		return  json;
	}
	
	/**
	 * 去新增类别
	 */
	@Action(value="toAdd",results={@Result(name="success",location="/WEB-INF/pages/admin/product/product_category_add.jsp")})
	public String toAdd(){
		return SUCCESS;
	}
	
	/**
	 * 新增/修改
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value="doAdd")
	public void doAdd(){
		try {
			// 新增
			ProductCategory pc=categoryService.getProductCategoryById(model.getItem().getParentId());
			if (pc!=null) {
				pc.setIsParent(1);
				categoryService.update(pc);
			}
			
			if(StringUtils.isBlank(model.getItem().getId())){
				model.getItem().setCreateBy(this.getCurrentUser().getName());
				model.getItem().setCreateDate(new Date());
				if(StringUtils.isBlank(model.getItem().getParentId())){
					model.getItem().setParentId("0");
				}
				categoryService.add(model.getItem());
				
				JSONObject result = new JSONObject();
				result.put(STATUS_PARAMETER_NAME, "success");
				result.put(MESSAGE_PARAMETER_NAME, "添加成功");
				result.put("item", model.getItem());
				ajax(result);
				
			// 修改
			}else{
				ProductCategory item=model.getItem();
				ProductCategory category = categoryService.getProductCategoryById(item.getId());
				category.setName(item.getName());
				category.setTip(item.getTip());
				category.setImgPath(item.getImgPath());
				category.setRemark(item.getRemark());
				category.setSort(item.getSort());
				categoryService.update(category);
				
				JSONObject result = new JSONObject();
				result.put(STATUS_PARAMETER_NAME, "success");
				result.put(MESSAGE_PARAMETER_NAME, "修改成功");
				result.put("item", category);
				ajax(result);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "添加类别节点时发生异常："+e.getMessage();
			logger.error(msg,e);
			ajax(Status.error,msg);
		}
	}
	
	/**
	 * 去修改类别
	 */
	@Action(value="toUpdate",results={@Result(name="success",location="/WEB-INF/pages/admin/product/product_category_add.jsp")})
	public String toUpdate(){
		String id = model.getItem().getId();
		ProductCategory category = categoryService.getProductCategoryById(id);
		model.setItem(category);
		return SUCCESS;
	}
	
	/**
	 * 管理类别属性
	 */
	@Action(value="attr_manage",results={@Result(name="success",location="/WEB-INF/pages/admin/product/product_category_attr_manage.jsp")})
	public String toAttrManage(){
		ProductCategory category=categoryService.getProductCategoryById(model.getItem().getId());
		
		model.getItem().setName(category.getName());
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@Action(value="doDelete")
	public String doDelete(){
		String strIds = model.getSelIds();
		String[] ids = strIds.split(",");
		categoryService.delByIds(ids);
		return ajax(Status.success);
	}
	
	/** 导入产品类别 */
	@Action(value="doImport")
	public String doImport(){
		String errorMessage = "";
		try {
			String path = WebUtils.getRealPath(model.getPath());
			errorMessage = categoryService.importXls(path);	
			if(StringUtils.isBlank(errorMessage)){
				errorMessage = "导入成功";
			}
			// 导入完毕删除文件
			File file = new File(path);
			file.delete();
		} catch (Exception e) {
			return ajax(Status.error, e.getMessage());
		}finally{
		}
		
		return ajax(Status.success , errorMessage);
	}
	
	@Override
	public ProductCategoryModel getModel() {
		return model;
	}
}
