package com.tocersoft.email.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.email.entity.EmailServerConfig;
import com.tocersoft.email.entity.SysEmailTemplate;
import com.tocersoft.email.model.AdminEmailModel;
import com.tocersoft.email.service.SysEmailServerConfigService;
import com.tocersoft.email.service.SysEmailTemplateService;

/**
 * 邮件服务管理
 * 
 * @author 付磊
 * 
 */
@ParentPackage("admin")
@Controller
@Namespace("/admin/email")
public class AdminEmailAction extends BaseAction implements
		ModelDriven<AdminEmailModel> {
	private static final long serialVersionUID = -5735950658803262457L;

	private AdminEmailModel model = new AdminEmailModel();
	@Autowired
	private SysEmailServerConfigService emailServerConfigService;
	@Autowired
	private SysEmailTemplateService emailTemplateService;

	/**
	 * 邮件服务器配置
	 * 
	 * @return
	 */
	@Action(value = "config", results = { @Result(name = SUCCESS, location = "/WEB-INF/pages/admin/email/config.jsp") })
	public String config() {
		EmailServerConfig emailServerConfig = emailServerConfigService
				.getEmailServerConfig();
		getRequest().setAttribute("serverConfig", emailServerConfig);
		return SUCCESS;
	}

	/**
	 * 修改邮件服务器配置
	 * 
	 * @return
	 */
	@Action(value = "saveConfig")
	public String saveConfig() {
		EmailServerConfig emailServerConfig = model.getServerConfig();
		if (null == emailServerConfig) {
			return ajax(Status.error, "保存失败");
		}
		emailServerConfigService.update(emailServerConfig);
		return ajax(Status.success, "保存成功");
	}

	/**
	 * 邮件模板列表
	 * 
	 * @return
	 */
	@Action(value = "tempList", results = { @Result(name = SUCCESS, location = "/WEB-INF/pages/admin/email/tempList.jsp") })
	public String tempList() {
		return SUCCESS;
	}
	
	/**
	 * 邮件模板列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "ajaxTempList")
	public String ajaxTempList() {
		List<SysEmailTemplate> templateList = emailTemplateService.getAllEmailTemplates();
		JSONObject root = new JSONObject();
		JSONArray array = new JSONArray();
		root.put("resultList", array);
		for(int i=0;i<templateList.size();i++){
			JSONObject json = new JSONObject();
			SysEmailTemplate obj = templateList.get(i);
			json.put("type", obj.getType());
			json.put("content", obj.getContent());
			json.put("id", obj.getId());
			array.add(json);
		}
		return ajax(root);
	}

	/**
	 * 添加或修改模板模板
	 * 
	 * @return
	 */
	@Action(value = "viewTemplate", results = { @Result(name = SUCCESS, location = "/WEB-INF/pages/admin/email/viewTemplate.jsp") })
	public String viewTemplate() {
		String id = model.getId();
		if (StringUtils.isBlank(id)) {
			return ERROR;
		}
		SysEmailTemplate emailTemplate = emailTemplateService
				.getEmailTemplateById(id);
		if (null == emailTemplate) {
			return ERROR;
		}
		getRequest().setAttribute("emailTemplate", emailTemplate);
		return SUCCESS;
	}

	/**
	 * 添加或修改模板模板
	 * 
	 * @return
	 */
	@Action(value = "saveOrUpdateTemplate")
	public String saveOrUpdateTemplate() {
		SysEmailTemplate emailTemplate = model.getEmailTemplate();
		if (null == emailTemplate) {
			return ajax(Status.error, "保存失败");
		}
		emailTemplateService.updateEmailTemplate(emailTemplate);
		return ajax(Status.success, "保存成功");
	}

	@Override
	public AdminEmailModel getModel() {
		return model;
	}
}
