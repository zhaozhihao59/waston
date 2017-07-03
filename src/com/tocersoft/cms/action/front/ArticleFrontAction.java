package com.tocersoft.cms.action.front;

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
import com.tocersoft.activity.dto.ActivityCondition;
import com.tocersoft.activity.entity.Activity;
import com.tocersoft.activity.service.IActivityService;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.Utils;
import com.tocersoft.cms.action.admin.ArticleAdminAction;
import com.tocersoft.cms.dto.CmsArticleCondition;
import com.tocersoft.cms.entity.CmsAdvPhoto;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.entity.CmsChannel;
import com.tocersoft.cms.model.CmsArticleModel;
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
@Namespace("/cms")
@Controller
@Scope("prototype")
public class ArticleFrontAction extends BaseAction implements ModelDriven<CmsArticleModel> {

	private CmsArticleModel model = new CmsArticleModel();
	private Log logger = LogFactory.getLog(ArticleAdminAction.class);
	@Resource
	private ICmsArticleService cmsArticleService;
	@Resource
	private ICmsChannelService cmsChannelService;
	@Resource
	private ICmsAdvPhotoService advPhotoService;
	@Resource
	private IActivityService activityService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1901866052106760355L;

	/**
	 * 显示文章的详细信息
	 * 
	 */
	@Action(value = "cmsDetail", results = { @Result(name = "success", location = "/WEB-INF/pages/front/cms/cms_detail.jsp") })
	public String cmsDetail(){
		try {
		// 查询类别信息
		List<CmsChannel> cmsChannels = cmsChannelService.listChannelByIsEn(0);
		for (CmsChannel cmsChannel : cmsChannels) {
			List<CmsArticle> cmsArticles = cmsArticleService.getArticleByChannelId(cmsChannel.getId());
			cmsChannel.setCmsArticles(cmsArticles);
		}
		model.setCmsChannels(cmsChannels);
		
		// 查询指定文章信息
		CmsArticle cmsArticle = cmsArticleService.getArticleById(model.getId());
		model.setItem(cmsArticle);
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}
	
	@Action(value = "index", results = { @Result(name = "index", location = "/WEB-INF/pages/front/cms/cms_index.jsp") })
	public String index() {
		
		// 查询最新公告
		CmsArticleCondition conditiongg = new CmsArticleCondition();
		conditiongg.setChannelId(model.getCid());
		cmsArticleService.getArticleListByPage(model.getPageResult(),conditiongg);
		model.setArticlesAnnounce(model.getPageResult().getResult());
		
		// 文章页左侧广告位
		List<CmsAdvPhoto> photos = advPhotoService.listAdvPhotoByAdvId("2");
		model.setPhotosNo1(photos);
		
		// 获得栏目 及父栏目信息
		CmsChannel channel = cmsChannelService.getCmsChannelById(model.getCid());
		String pid = channel.getParent();
		List<CmsChannel> channelList = null;
		CmsChannel parentCh = null;
		CmsChannel childrenCh = null;
		if (pid.equals("0")) {
			channelList = cmsChannelService.listChannelByPid(channel.getId());
			parentCh = channel;
			if (channelList != null && channelList.size() > 0) {
				childrenCh = channelList.get(0);
			} else {
				channelList.add(channel);
			}
		} else if (!pid.equals("0")) {
			parentCh = cmsChannelService.getCmsChannelById(pid);
			channelList = cmsChannelService.listChannelByPid(parentCh.getId());
			childrenCh = channel;
		}
		model.setCmsChannels(channelList);
		model.setChannel(parentCh);
		model.setChannel1(childrenCh);

		PageResult<CmsArticle> pageResult = model.getPageResult();
		pageResult.setCurrentPage(model.getPage());
		pageResult.setPageSize(30);
		CmsArticleCondition condition = new CmsArticleCondition();
		condition.setChannelId(childrenCh != null ? childrenCh.getId() : parentCh.getId());
		cmsArticleService.getArticleListByPage(pageResult, condition);
		// 获得文章列表
		List<CmsArticle> article = cmsArticleService.getArticleByChannelId(channel.getId());
		model.setCmsArticles(article);
		model.setPageResult(pageResult);
		//将所有次级目录查询并放入
		List<CmsChannel> listChannel=cmsChannelService.listChannelByPid(channel.getParent());
		getRequest().setAttribute("listChannel", listChannel);
		return INDEX;
	}
	
	@Action(value = "game_result", results = { @Result(name = "index", location = "/WEB-INF/pages/front/cms/cms_index.jsp") })
	public String gameResult() {
		
		// 获得栏目 及父栏目信息
		CmsChannel channel = cmsChannelService.getCmsChannelById("4700992c-aea9-11e4-82d5-00fffd437687");
		String pid = channel.getParent();
		List<CmsChannel> channelList = null;
		CmsChannel parentCh = null;
		CmsChannel childrenCh = null;
		if (pid.equals("0")) {
			channelList = cmsChannelService.listChannelByPid(channel.getId());
			parentCh = channel;
			if (channelList != null && channelList.size() > 0) {
				childrenCh = channelList.get(0);
			} else {
				channelList.add(channel);
			}
		} else if (!pid.equals("0")) {
			parentCh = cmsChannelService.getCmsChannelById(pid);
			channelList = cmsChannelService.listChannelByPid(parentCh.getId());
			childrenCh = channel;
		}
		model.setCmsChannels(channelList);
		model.setChannel(parentCh);
		model.setChannel1(childrenCh);

		PageResult<CmsArticle> pageResult = model.getPageResult();
		pageResult.setCurrentPage(model.getPage());
		pageResult.setPageSize(30);
		CmsArticleCondition condition = new CmsArticleCondition();
		condition.setChannelId(childrenCh != null ? childrenCh.getId() : parentCh.getId());
		cmsArticleService.getArticleListByPage(pageResult, condition);
		// 获得文章列表
		List<CmsArticle> article = cmsArticleService.getArticleByChannelId(channel.getId());
		model.setCmsArticles(article);
		model.setPageResult(pageResult);
		//将所有次级目录查询并放入
		List<CmsChannel> listChannel=cmsChannelService.listChannelByPid(channel.getParent());
		getRequest().setAttribute("listChannel", listChannel);
		
		
		return INDEX;
	}
	
	/**
	 * 跳转到视频列表页面
	 * @return
	 */
	@Action(value = "video_list", results = { @Result(name = "index", location = "/WEB-INF/pages/front/cms/video_list.jsp") })
	public String video_list() {
		//读取视频栏目内全部视频内容
		List<CmsArticle> videoArticleList = cmsArticleService.getArticleByChannelId("8a6cf086-fa16-11e4-b164-00155d008ea1");
		model.setVideoArticleList(videoArticleList);
		return INDEX;
	}
	
	@Action(value = "about", results = { @Result(name = "index", location = "/WEB-INF/pages/cms/front/cms_about_index.jsp") })
	public String about() {
		List<CmsArticle> articles = cmsArticleService.getArticleByChannelId("1");
		model.setCmsArticles(articles);
		return INDEX;
	}
	
	@Action(value = "news", results = { @Result(name = "index", location = "/WEB-INF/pages/cms/front/cms_news_index.jsp") })
	public String news() {
		List<CmsChannel> cmsChannels = cmsChannelService.listChannelByPid("2");
		for(CmsChannel channel : cmsChannels){
			List<CmsArticle> articles = cmsArticleService.listNewArticleByCidLimit(channel.getId(), 4);
			channel.setArticles(articles);
		}
		model.setCmsChannels(cmsChannels);
		return INDEX;
	}
	
	@Action(value = "news_list", results = { @Result(name = "success", location = "/WEB-INF/pages/cms/front/cms_news_list.jsp") })
	public String newsList() {
		
		CmsArticleCondition condition = new CmsArticleCondition();
		condition.setChannelId(model.getCid());
		cmsArticleService.getArticleListByPage(model.getPageResult(),condition);
		
		CmsChannel channel = cmsChannelService.getById(model.getCid());
		model.setChannel(channel);
		
		List<CmsChannel> cmsChannels = cmsChannelService.listChannelByPid("2");
		model.setCmsChannels(cmsChannels);
		
		return SUCCESS;
	}
	
	@Action(value = "news_detail", results = { @Result(name = "success", location = "/WEB-INF/pages/cms/front/cms_news_detail.jsp") })
	public String newsDetail() {
		
		CmsArticle article = cmsArticleService.getArticleById(model.getId());
		model.setItem(article);
		
		CmsChannel channel = cmsChannelService.getById(article.getChannelId());
		model.setChannel(channel);
		
		List<CmsChannel> cmsChannels = cmsChannelService.listChannelByPid("2");
		model.setCmsChannels(cmsChannels);
		
		return SUCCESS;
	}
	
	@Action(value = "service", results = { @Result(name = "index", location = "/WEB-INF/pages/cms/front/cms_service_index.jsp") })
	public String service() {
		List<CmsArticle> articles = cmsArticleService.getArticleByChannelId("18");
		model.setCmsArticles(articles);
		return INDEX;
	}
	
	@Action(value = "service_detail", results = { @Result(name = "success", location = "/WEB-INF/pages/cms/front/cms_service_detail.jsp") })
	public String serviceDetail() {
		
		CmsArticle article = cmsArticleService.getArticleById(model.getId());
		model.setItem(article);
		
		CmsChannel channel = cmsChannelService.getById(article.getChannelId());
		model.setChannel(channel);
		
		List<CmsArticle> articles = cmsArticleService.getArticleByChannelId("18");
		model.setCmsArticles(articles);
		
		return SUCCESS;
	}

	/**
	 * 显示文章的详细信息
	 * 
	 */
	@Action(value = "aritlDetail", results = { @Result(name = "success", location = "/WEB-INF/pages/front/cms/cms_detail.jsp") })
	public String aritleDetail() {
//		CmsArticle artile = cmsArticleService.getArticleById(model.getCid());
//		CmsChannel cmsChannel = cmsChannelService.getCmsChannelById(artile
//				.getChannelId());
//		List<CmsArticle> listArticle=cmsArticleService.getArticleByChannelId(cmsChannel.getId());
//		
//		List<CmsChannel> listChannel=cmsChannelService.listChannelByPid(cmsChannel.getParent());
//		getRequest().setAttribute("listChannel", listChannel);
//		
//		getRequest().setAttribute("listArticle", listArticle);
//		getRequest().setAttribute("cmsChannel", cmsChannel);
//		getRequest().setAttribute("artile", artile);

		return SUCCESS;
	}

	@Action(value = "detail", results = { @Result(name = "success", location = "/WEB-INF/pages/front/cms/cms_detail.jsp") })
	public String detail() {
		// 文章详细
		String id = model.getId();
		CmsArticle item = cmsArticleService.getArticleById(id);
		model.setItem(item);
		
		// 文章页左侧广告位
		List<CmsAdvPhoto> photos = advPhotoService.listAdvPhotoByAdvId("2");
		model.setPhotosNo1(photos);

		// 获得栏目 及父栏目信息
		CmsChannel channel = cmsChannelService.getCmsChannelById(item.getChannelId());
		String pid = channel.getParent();

		// 详细页中左侧栏目为同级栏目的时候
		List<CmsChannel> channelList = null;
		
		// 详细页中左侧栏目标题部分
		CmsChannel parentCh = null;
		
		// 详细页中左侧栏目为文章的时候
		List<CmsArticle> menuArticles = null;
		CmsChannel childrenCh = null;
		// 当前的文章栏目为一级栏目时，查询一级栏目下的文章，作为左侧栏列表显示；
		if (pid.equals("0")) {
			
			menuArticles = cmsArticleService.getArticleByChannelId(channel.getId());
			model.setMenuArticles(menuArticles);
			// 左侧栏目标题部分直接显示该一级栏目
			parentCh = channel;
		
		// 当前的文章栏目不是一级栏目时，查询当前文章栏目的同级栏目
		} else if (!pid.equals("0")) {
			// 左侧栏目标题部分需要显示当前栏目的父级栏目
			parentCh = cmsChannelService.getCmsChannelById(pid);
			channelList = cmsChannelService.listChannelByPid(parentCh.getId());
			childrenCh = channel;
		}
		model.setCmsChannels(channelList);
		model.setChannel(parentCh);
		model.setChannel1(childrenCh);

		// 安阅读量查询新闻列表
		model.getPageResult().setPageSize(5);
		CmsArticleCondition condition = new CmsArticleCondition();
		condition.setChannelId(item.getChannelId());
		cmsArticleService.getArticleListByPageOrderByReady(model.getPageResult(), condition);
		model.setCmsArticles(model.getPageResult().getResult());

		// 获得关键词查询列表
		CmsArticleCondition conditionbd = new CmsArticleCondition();
		conditionbd.setKeyword(item.getKeyword());
		conditionbd.setKeyword2(item.getKeyword2());
		conditionbd.setKeyword3(item.getKeyword3());
		conditionbd.setChannelId(item.getChannelId());
		cmsArticleService.getArticleListByPage(model.getPageResult(),conditionbd);
		model.setKeyNewsList(model.getPageResult().getResult());
		
		// 查询最新公告
		CmsArticleCondition conditiongg = new CmsArticleCondition();
		conditiongg.setChannelId("d4844b33-5a7e-11e3-9187-f0def132abdb");
		cmsArticleService.getArticleListByPage(model.getPageResult(),conditiongg);
		model.setArticlesAnnounce(model.getPageResult().getResult());
		
		return SUCCESS;
	}
	
	/**
	 * 网站地图
	 * @return
	 */
	@Action(value = "site_map", results = { @Result(name = "index", location = "/WEB-INF/pages/front/cms/site_map.jsp") })
	public String siteMap() {
		
		return INDEX;
	}
	
	/**
	 * 全站搜索
	 * @return
	 */
	@Action(value = "search", results = { @Result(name = "index", location = "/WEB-INF/pages/front/cms/cms_list_search.jsp") })
	public String search() {
		
		// 查询最新公告
		CmsArticleCondition conditiongg = new CmsArticleCondition();
		conditiongg.setChannelId("d4844b33-5a7e-11e3-9187-f0def132abdb");
		cmsArticleService.getArticleListByPage(model.getPageResult(),conditiongg);
		model.setArticlesAnnounce(model.getPageResult().getResult());
		
		// 取得文章详细页广告图
		List<CmsAdvPhoto> photo = advPhotoService.listAdvPhotoByAdvId("3");
		if(null != photo && photo.size() > 0){
			model.setPhoto(photo.get(0));
		}
		
		// 文章页左侧广告位
		List<CmsAdvPhoto> photos = advPhotoService.listAdvPhotoByAdvId("8");
		model.setPhotosNo1(photos);
		
		PageResult<CmsArticle> pageResult = model.getPageResult();
		pageResult.setCurrentPage(model.getPage());
		CmsArticleCondition condition = model.getCondition();
		String keyGet = model.getKey();
		String key = Utils.stringFormat(keyGet);
		condition.setKey(key);
		model.setKey(key);
		cmsArticleService.getArticleListByPage(pageResult, condition);
		
		model.setPageResult(pageResult);
		
		return INDEX;
	}
	
	/**
	 * 联系我们
	 * @return
	 */
	@Action(value = "contact", results = { @Result(name = "index", location = "/WEB-INF/pages/front/cms/cms_contact.jsp") })
	public String contact() {
		return INDEX;
	}
	
	/**
	 * 企业文化
	 * @return
	 */
	@Action(value = "culture", results = { @Result(name = "index", location = "/WEB-INF/pages/front/cms/cms_culture.jsp") })
	public String culture() {
		return INDEX;
	}
	/**
	 * 工作机会
	 * @return
	 */
	@Action(value = "toJobOpprtunity", results = { @Result(name = "success", location = "/WEB-INF/pages/front/work/job_opportunity.jsp") })
	public String toJobOpprtunity() {
		// 获取律师与助理
		List<CmsArticle> cmsArticle1 = cmsArticleService.getArticleAll("f2a3b783-ed60-11e4-aa04-00266c0e7760");
		model.setCmsArticle1(cmsArticle1);
		//
		//专利代理人
		List<CmsArticle> cmsArticle2 = cmsArticleService.getArticleAll("ff882e26-ed60-11e4-aa04-00266c0e7760");
		model.setCmsArticle2(cmsArticle2);
		//其他职位
		List<CmsArticle> cmsArticle3 = cmsArticleService.getArticleAll("071bb2ad-ed61-11e4-aa04-00266c0e7760");
		model.setCmsArticle3(cmsArticle3);		
		return SUCCESS;
	}
	/**
	 * 常见问题列表
	 * @return
	 */
	@Action(value = "to_common_problem", results = { @Result(name = "index", location = "/WEB-INF/pages/front/common_problem.jsp") })
	public String to_common_problem() {
		//查询所有常见为题
		//List<CmsArticle> cmsArticle_problem = cmsArticleService.getArticleAll("d4c4dcbf-e891-11e4-ae9b-00266c0e7760");
		//保存到model
		//model.setCmsArticle_problem(cmsArticle_problem);
		//定义Condition
		CmsArticleCondition cmsarticleCondition = new CmsArticleCondition();
		//设置显示条目数setPageSize(条目数)
		model.getPageResult().setPageSize(6);
		cmsarticleCondition.setChannelId("d4c4dcbf-e891-11e4-ae9b-00266c0e7760");
		//getArticleListByPage(分页对象,查询条件)
		cmsArticleService.getArticleListByPage(model.getPageResult(), cmsarticleCondition);
		//读取视频栏目内全部视频内容
		List<CmsArticle> videoArticleList = cmsArticleService.getArticleByChannelId("8a6cf086-fa16-11e4-b164-00155d008ea1");
		model.setVideoArticleList(videoArticleList);
		//查询活动报名
		List<Activity> listActivity = activityService.listActivity(2);
		model.setListActivity(listActivity);
		return INDEX;
	}

	/**
	 * 常见问题列表 抽取页面
	 */
	@Action(value = "ListPageByOld", results = { @Result(name = "index", location = "/WEB-INF/pages/front/common_problem_inc.jsp") })
	public String ListPageByOld() {
		//定义Condition
		CmsArticleCondition cmsarticleCondition = new CmsArticleCondition();
		//设置显示条目数setPageSize(条目数)
		model.getPageResult().setPageSize(6);
		//根据ID查询常见问题列表
		cmsarticleCondition.setChannelId("d4c4dcbf-e891-11e4-ae9b-00266c0e7760");
		//查询上一页页码赋值为条件
		model.getPageResult().setCurrentPage(model.getPage());
		//getArticleListByPage(分页对象,查询条件)
		cmsArticleService.getArticleListByPage(model.getPageResult(), cmsarticleCondition);
		return INDEX;
	}
	/**
	 * 业务领域
	 * @return
	 */
	@Action(value = "business_field", results = { @Result(name = "success", location = "/WEB-INF/pages/front/work/knowledge_index.jsp") })
	public String business_field() {
		model.getI();
		// 公司商务业务
		List<CmsArticle> cmsArticleField1 = cmsArticleService.getArticleAll("c1272bc2-ee3e-11e4-a8bb-0071cc954734");
		model.setCmsArticleField1(cmsArticleField1);
		// 知识产权
		List<CmsArticle> cmsArticleField2 = cmsArticleService.getArticleAll("c884a615-ee3e-11e4-a8bb-0071cc954734");
		model.setCmsArticleField2(cmsArticleField2);
		// 诉讼仲裁业务
		List<CmsArticle> cmsArticleField3 = cmsArticleService.getArticleAll("d25b2a55-ee3e-11e4-a8bb-0071cc954734");
		model.setCmsArticleField3(cmsArticleField3);
		// 财富管理
		List<CmsArticle> cmsArticleField4 = cmsArticleService.getArticleAll("d987e778-ee3e-11e4-a8bb-0071cc954734");
		model.setCmsArticleField4(cmsArticleField4);
		return SUCCESS;
	}
	@Override
	public CmsArticleModel getModel() {
		return model;
	}
}
