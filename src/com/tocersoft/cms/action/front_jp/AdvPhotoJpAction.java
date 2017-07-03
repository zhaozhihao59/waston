package com.tocersoft.cms.action.front_jp;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.cms.action.admin.ArticleAdminAction;
import com.tocersoft.cms.entity.CmsAdvPhoto;
import com.tocersoft.cms.model.CmsAdvPhotoModel;
import com.tocersoft.cms.service.ICmsAdvPhotoService;
import com.tocersoft.cms.service.ICmsArticleService;
import com.tocersoft.cms.service.ICmsChannelService;

/**
 * 新闻中心
 * 
 * @author vinartis
 * @createDate Apr 7, 2013
 */
@ParentPackage("front")
@Namespace("/photo_jp")
@Controller
@Scope("prototype")
public class AdvPhotoJpAction extends BaseAction implements ModelDriven<CmsAdvPhotoModel> {

	private CmsAdvPhotoModel model = new CmsAdvPhotoModel();
	private Log logger = LogFactory.getLog(ArticleAdminAction.class);
	@Resource
	private ICmsAdvPhotoService advPhotoService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1901866052106760355L;

	@Action(value = "index", results = { @Result(name = "index", location = "/WEB-INF/pages/cms/front/img-show.jsp") })
	public String index() {
		List<CmsAdvPhoto> list = advPhotoService.listAdvPhotoByAdvId("2");
		model.setPhotoList(list);
		return INDEX;
	}
	
	@Action(value = "secondPage", results = { @Result(name = "index", location = "/WEB-INF/pages/front/comm/customer_list_inc.jsp") })
	public String secondPage() {
		List<CmsAdvPhoto> list = advPhotoService.listAdvPhotoByAdvId("3");
		getRequest().setAttribute("secondPageList", list);
		return INDEX;
	}
	
	@Override
	public CmsAdvPhotoModel getModel() {
		return model;
	}
}
