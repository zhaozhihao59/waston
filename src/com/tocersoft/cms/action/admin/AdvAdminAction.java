package com.tocersoft.cms.action.admin;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.cms.entity.CmsAdv;
import com.tocersoft.cms.model.CmsAdvModel;
import com.tocersoft.cms.service.ICmsAdvService;

/**
 * 
 * 广告管理
 * @author 方泉
 *
 */
@ParentPackage("admin")
@Namespace("/admin/cms/adv")
@Controller
public class AdvAdminAction extends BaseAction implements ModelDriven<CmsAdvModel>{

	private static final long serialVersionUID = 5107024224886599818L;
	
	private CmsAdvModel model = new CmsAdvModel();
	
	private Log logger = LogFactory.getLog(AdvAdminAction.class);

	@Resource
	private ICmsAdvService cmsAdvService;
	
	@Action(value = "index", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/cms/adv_list.jsp") })
	public String index() {
		return INDEX;
	}
	
	@Action(value = "to_add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/cms/add.jsp") })
	public String toAdd() {
		return SUCCESS;
	}
	
	@Action(value = "to_manage", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/cms/adv_manage.jsp") })
	public String toManage() {
		// 查找要管理的广告位
		String id = model.getItem().getId();
		CmsAdv adv = cmsAdvService.getAdvById(id);
		// 将CmsAdv放入Model中
		model.setItem(adv);
		return SUCCESS;
	}
	
	/**
	 * 分页查询文章
	 */
	@Action(value = "list_adv")
	public String listAdv(){
		cmsAdvService.listAdvByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String []{"id","name","note","photoWidth","photoHeight","maxPhotoNum"});
		return ajax(root);
	}
	
	@Override
	public CmsAdvModel getModel() {
		return model;
	}
}
