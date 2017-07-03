package com.tocersoft.member.action.weixin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.activity.dto.ActivityCondition;
import com.tocersoft.activity.entity.Activity;
import com.tocersoft.activity.service.IActivityService;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.cms.dto.CmsArticleCondition;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.entity.CmsChannel;
import com.tocersoft.cms.model.IndexModel;
import com.tocersoft.cms.service.ICmsAdvPhotoService;
import com.tocersoft.cms.service.ICmsArticleService;
import com.tocersoft.cms.service.ICmsChannelService;
import com.tocersoft.game.service.IGameService;
import com.tocersoft.product.service.IProductBrandService;
import com.tocersoft.product.service.IProductCategoryService;
import com.tocersoft.product.service.IProductService;
import com.tocersoft.professional.service.IProfessionalService;
import com.tocersoft.system.service.ISysDictItemService;
import com.tocersoft.system.service.ISysUploadFileService;

/**
 * 首页
 * 
 * @author vinartis
 * @createDate Apr 7, 2013
 */
@ParentPackage("weixin")
@Namespace("/weixin")
@Controller
@Scope("prototype")
public class WeixinAction extends BaseAction implements ModelDriven<IndexModel>{
	
	HttpSession session = WebUtils.getSession();
	private IndexModel model = new IndexModel();
	
	@Resource
	private ICmsArticleService articleService;
	@Resource
	private ICmsAdvPhotoService advPhotoService;
	@Resource
	private IProductCategoryService categoryService;
	@Resource
	private ISysDictItemService sysDictItemService;
	@Resource
	private IProductBrandService productBrandService;
	@Resource
	private IProductService productService;
	@Resource
	private ICmsAdvPhotoService cmsAdvPhotoService;
	@Resource
	private ICmsChannelService cmsChannelService;
	@Resource(name = "gameServiceImpl")
	private IGameService gameService;
	@Resource(name = "sysUploadFileServiceImpl")
	private ISysUploadFileService fileService;
	@Resource
	private IActivityService activityService;
	@Resource
	private IProfessionalService professionalService;
	/** */
	private static final long serialVersionUID = 1901866052106760355L;
	/***  手机端*/
	
	/***  手机端*/
	
	/***  手机端*/
	/**
	 * 活动列表
	 * @return
	 */
	@Action(value = "to_activity_list", results = { @Result(name = "success", location = "/WEB-INF/pages/weixin/activity_notice_list.jsp") })
	public String to_activity_list() {
		List<Activity> listActivityEnrollIng = activityService.listActivity(1);
		model.setListActivity(listActivityEnrollIng);
		List<Activity> listActivityPrediction = activityService.listActivity(0);
		model.setListActivityPrediction(listActivityPrediction);
		
		ActivityCondition activityCondition = new ActivityCondition();
		model.getActivityPageResult().setPageSize(10);
		if (model.getPage() == 0) {
			model.setPage(1);
		}
		model.getActivityPageResult().setCurrentPage(model.getPage());
		activityService.listActivityByPage(model.getActivityPageResult(), activityCondition);
		return SUCCESS;
	}
	
	/**
	 * 活动列表
	 * @return
	 */
	@Action(value = "to_activity_list_inc", results = { @Result(name = "success", location = "/WEB-INF/pages/weixin/activity_notice_list_inc.jsp") })
	public String to_activity_list_inc() {
		List<Activity> listActivityEnrollIng = activityService.listActivity(1);
		model.setListActivity(listActivityEnrollIng);
		List<Activity> listActivityPrediction = activityService.listActivity(0);
		model.setListActivityPrediction(listActivityPrediction);
		
		ActivityCondition activityCondition = new ActivityCondition();
		// 获取查询标题的内容
		Integer status = model.getStatus();
		if(null != status && -1!=status){
			activityCondition.setType(status);
		}
		
		model.getActivityPageResult().setPageSize(10);
		if (model.getPage() == 0){
			model.setPage(1);
		}
		model.getActivityPageResult().setCurrentPage(model.getPage());
		activityService.listActivityByPage(model.getActivityPageResult(), activityCondition);
		return SUCCESS;
	}
	
	/**
	 * 活动详情
	 * @return
	 */
	@Action(value = "to_activity_detail", results = { @Result(name = "success", location = "/WEB-INF/pages/weixin/activity_notice_detail.jsp") })
	public String to_activity_detail() {
		Activity item = activityService.getActivityById(model.getSelIds());
		model.setActivity(item);
		return SUCCESS;
	}
	
	/**
	 * 活动报名
	 * @return
	 */
	@Action(value = "activity_enroll", results = { @Result(name = "success", location = "/WEB-INF/pages/weixin/") })
	public String activity_enroll() {
		return SUCCESS;
	}
	/**
	 * 期刊列表
	 * @return
	 */
	@Action(value = "journal_list", results = { @Result(name = "success", location = "/WEB-INF/pages/weixin/journal_list.jsp") })
	public String journal_list() {
		CmsArticleCondition cc = new CmsArticleCondition();
		//cc.setChannelId("3b4e3fb0-e891-11e4-ae9b-00266c0e7760");
		
		model.getPageResult().setCurrentPage(model.getPage());
		
		model.getPageResult().setPageSize(10);
		if (model.getPage() == 0) {
			model.setPage(1);
		}
		model.getPageResult().setCurrentPage(model.getPage());
		
		articleService.listArticleListByChannelId(model.getPageResult(), cc);
		
		
		// 查询期刊 3b4e3fb0-e891-11e4-ae9b-00266c0e7760
		List<CmsChannel> cmsChannelList = cmsChannelService.listChannelByPid("3b4e3fb0-e891-11e4-ae9b-00266c0e7760");
		model.setCmsChannels(cmsChannelList);
		
		return SUCCESS;
	}
	
	
	/**
	 * 期刊列表
	 * @return
	 */
	@Action(value = "journal_list_inc", results = { @Result(name = "success", location = "/WEB-INF/pages/weixin/journal_list_inc.jsp") })
	public String journal_list_inc() {
		CmsArticleCondition cc = new CmsArticleCondition();
		//cc.setChannelId("3b4e3fb0-e891-11e4-ae9b-00266c0e7760");
		// 获取查询标题的内容
		String channelId = model.getChannelId();
		if(null != channelId && !("-1").equals(channelId)){
			cc.setChannelId(channelId);
		}
		model.getPageResult().setCurrentPage(model.getPage());
		model.getPageResult().setPageSize(10);
		if (model.getPage() == 0) {
			model.setPage(1);
		}
		model.getPageResult().setCurrentPage(model.getPage());
		articleService.listArticleListByChannelId(model.getPageResult(), cc);
		
		// 查询期刊 3b4e3fb0-e891-11e4-ae9b-00266c0e7760
		List<CmsChannel> cmsChannelList = cmsChannelService.listChannelByPid("3b4e3fb0-e891-11e4-ae9b-00266c0e7760");
		model.setCmsChannels(cmsChannelList);
		return SUCCESS;
	}
	
	/**
	 * 手机端  跳转到文章详情（招聘页）
	 * @return
	 */
	@Action(value = "article_list", results = { @Result(name = "success", location = "/WEB-INF/pages/weixin/article_list.jsp") })
	public String article_list() {
		return SUCCESS;
	}

	/**
	 * 手机端  跳转到文章详情（招聘页）
	 * @return
	 */
	@Action(value = "article_detail", results = { @Result(name = "success", location = "/WEB-INF/pages/weixin/article_detail.jsp") })
	public String article_detail() {
		String channelId = model.getSelIds();
		List<CmsArticle> cmsArticle = articleService.getArticleByChannelId(channelId);
		model.setListCmsArticle(cmsArticle);
		model.setCmsChannel(cmsChannelService.getById(channelId));
		return SUCCESS;
	}
	@Override
	public IndexModel getModel() {
		return model;
	}
}
