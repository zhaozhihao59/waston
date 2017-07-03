package com.tocersoft.order.action.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

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
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.order.entity.FreightTemplateItem;
import com.tocersoft.order.model.FreightTemplateItemModel;
import com.tocersoft.order.service.IFreightTemplateItemService;
import com.tocersoft.system.entity.CodeAddress;
import com.tocersoft.system.entity.CodeCity;
import com.tocersoft.system.entity.CodeProvince;


@ParentPackage("admin")
@Namespace("/admin/freight/template/item")
@Controller
public class FreightTemplateItemAdminAction extends BaseAction implements ModelDriven<FreightTemplateItemModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(FreightTemplateItemAdminAction.class);

	private FreightTemplateItemModel model = new FreightTemplateItemModel();

	@Resource(name = "freightTemplateItemServiceImpl")
	private IFreightTemplateItemService freightTemplateItemService;



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
	@Action(value = "listFreightTemplateItemByPage")
	public String listFreightTemplateItemByPage(){
		model.getCondition().setTemplateId(model.getItem().getTemplateId());
		freightTemplateItemService.listFreightTemplateItemByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","arrivalCityList","firstWeightFee","firstWeight","continuedWeightFee","continuedWeight"});
		return ajax(root);
	}

	/**
	 * 新增
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/order/freight_template_item_add.jsp") })
	public String toAdd(){
		ServletContext content = this.getServletContext();
		List<CodeProvince> provinces = (List<CodeProvince>)content.getAttribute("provinces");
		
		// 构造城市的JSON字符串
		// {"name":"全国",isOpen:true,isParent:true,children:[{"name":"省份1",isOpen:false,isParent:true,children:[{"name":"城市1",isOpen:false,isParent:true,children:[{"name":"区县1",isOpen:false,isParent:false}]},{}]},{"name":"省份2",isOpen:false,isParent:true}]}
		JSONObject result = new JSONObject();
		result.put("name", "全国");
		result.put("isOpen", true);
		result.put("isParent", true);
		
		// 加载省份
		JSONArray arrayProvince = new JSONArray();
		for(CodeProvince province : provinces){
			JSONObject obj = new JSONObject();
			obj.put("name", province.getProvince());
			obj.put("isOpen", false);
			obj.put("isParent", true);
			
			// 加载城市
			List<CodeCity> cities = province.getCities();
			JSONArray arrayCity = new JSONArray();
			for(CodeCity city : cities){
				JSONObject objCity = new JSONObject();
				objCity.put("name", city.getCity());
				objCity.put("isOpen", false);
				objCity.put("isParent", true);
				
				// 加载区县
				List<CodeAddress> addrs = city.getAddrs();
				JSONArray arrayAddr = new JSONArray();
				for(CodeAddress addr : addrs){
					JSONObject objAddr = new JSONObject();
					objAddr.put("name", addr.getDong());
					objAddr.put("isOpen", false);
					objAddr.put("isParent", false);
					arrayAddr.add(objAddr);
				}
				objCity.put("children", arrayAddr);
				arrayCity.add(objCity);
				
			}
			obj.put("children", arrayCity);
			arrayProvince.add(obj);
		}
		result.put("children", arrayProvince);
		model.setProvinceArray(result.toJSONString());
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/order/freight_template_item_add.jsp") })
	public String toUpdate(){
		FreightTemplateItem item = freightTemplateItemService.getFreightTemplateItemById(model.getItem().getId());
		model.setItem(item);
		ServletContext content = this.getServletContext();
		List<CodeProvince> provinces = (List<CodeProvince>)content.getAttribute("provinces");
		// 构造城市的JSON字符串
		// {"name":"全国",isOpen:true,isParent:true,children:[{"name":"省份1",isOpen:false,isParent:true,children:[{"name":"城市1",isOpen:false,isParent:true,children:[{"name":"区县1",isOpen:false,isParent:false}]},{}]},{"name":"省份2",isOpen:false,isParent:true}]}
		JSONObject result = new JSONObject();
		result.put("name", "全国");
		result.put("isOpen", true);
		result.put("isParent", true);
		// 加载省份
		JSONArray arrayProvince = new JSONArray();
		for(CodeProvince province : provinces){
			JSONObject obj = new JSONObject();
			obj.put("name", province.getProvince());
			obj.put("isOpen", false);
			obj.put("isParent", true);
			
			// 加载城市
			List<CodeCity> cities = province.getCities();
			JSONArray arrayCity = new JSONArray();
			for(CodeCity city : cities){
				JSONObject objCity = new JSONObject();
				objCity.put("name", city.getCity());
				objCity.put("isOpen", false);
				objCity.put("isParent", true);
				
				// 加载区县
				List<CodeAddress> addrs = city.getAddrs();
				JSONArray arrayAddr = new JSONArray();
				for(CodeAddress addr : addrs){
					JSONObject objAddr = new JSONObject();
					objAddr.put("name", addr.getDong());
					objAddr.put("isOpen", false);
					objAddr.put("isParent", false);
					arrayAddr.add(objAddr);
				}
				objCity.put("children", arrayAddr);
				arrayCity.add(objCity);
				
			}
			obj.put("children", arrayCity);
			arrayProvince.add(obj);
		}
		result.put("children", arrayProvince);
		model.setProvinceArray(result.toJSONString());

		return SUCCESS;
	}

	/**
	 * 删除
	 * @return 
	 */
	@Action(value="del")
	public String del(){
		try {
			if(!StringUtils.isBlank(model.getSelIds())){
				freightTemplateItemService.delByIds(model.getSelIds().split(","));
			}else{
				model.setSelIds(model.getItem().getId());
				freightTemplateItemService.delByIds(model.getSelIds().split(","));

			}
			freightTemplateItemService.delByIds(model.getSelIds().split(","));
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
				freightTemplateItemService.add(model.getItem());
			}else{
				freightTemplateItemService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public FreightTemplateItemModel getModel() {
		return model;
	}}

