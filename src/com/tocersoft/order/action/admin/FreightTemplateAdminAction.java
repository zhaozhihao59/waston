package com.tocersoft.order.action.admin;

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
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.order.entity.FreightTemplate;
import com.tocersoft.order.model.FreightTemplateModel;
import com.tocersoft.order.service.IFreightTemplateService;


@ParentPackage("admin")
@Namespace("/admin/freight/template")
@Controller
public class FreightTemplateAdminAction extends BaseAction implements ModelDriven<FreightTemplateModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(FreightTemplateAdminAction.class);

	private FreightTemplateModel model = new FreightTemplateModel();

	@Resource(name = "freightTemplateServiceImpl")
	private IFreightTemplateService freightTemplateService;

	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/order/freight_template_index.jsp") })
	public String index(){
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listFreightTemplateByPage")
	public String listFreightTemplateByPage(){
		freightTemplateService.listFreightTemplateByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","templateName","templateNote"});
		return ajax(root);
	}

	/**
	 * 查询所有
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "listFreightTemplate")
	public String listFreightTemplate(){
		freightTemplateService.listFreightTemplateByPage(model.getPageResult(),model.getCondition());
		List<FreightTemplate> freightTemplates = model.getPageResult().getResult();
		JSONArray array = new JSONArray();
		for(FreightTemplate freightTemplate : freightTemplates){
			JSONObject object = new JSONObject();
			object.put("name", freightTemplate.getTemplateName());
			object.put("id", freightTemplate.getId());
			array.add(object);
		}
		return ajax(Status.success,array.toJSONString());
	}
	
	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/order/freight_template_add.jsp") })
	public String toAdd(){
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/order/freight_template_add.jsp") })
	public String toUpdate(){
		FreightTemplate item = freightTemplateService.getFreightTemplateById(model.getItem().getId());
		model.setItem(item);
		return SUCCESS;
	}

	/**
	 * 删除
	 * @return 
	 */
	@Action(value="del")
	public String del(){
		try {
			if(model.getSelIds().length()>0){
				freightTemplateService.delByIds(model.getSelIds().split(","));
			} else{
				model.setSelIds(model.getItem().getId());
				freightTemplateService.delByIds(model.getSelIds().split(","));
			}
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
				model.getItem().setIsDefault(0);
				freightTemplateService.add(model.getItem());
			}else{
				freightTemplateService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public FreightTemplateModel getModel() {
		return model;
	}}

