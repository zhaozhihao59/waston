package com.tocersoft.cms.action.front_en;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.activity.entity.Activity;
import com.tocersoft.activity.service.IActivityService;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.cms.dto.CmsArticleCondition;
import com.tocersoft.cms.entity.CmsAdvPhoto;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.entity.CmsChannel;
import com.tocersoft.cms.model.IndexModel;
import com.tocersoft.cms.service.ICmsAdvPhotoService;
import com.tocersoft.cms.service.ICmsArticleService;
import com.tocersoft.cms.service.ICmsChannelService;
import com.tocersoft.professional.dto.ProfessionalCondition;
import com.tocersoft.professional.service.IProfessionalService;
import com.tocersoft.system.entity.SysDictItem;
import com.tocersoft.system.service.ISysDictItemService;

/**
 * 首页
 * 英文版
 * @author vinartis
 * @createDate Apr 7, 2013
 */
@ParentPackage("front")
@Namespace("/en")
@Controller
@Scope("prototype")
public class HomeEnAction extends BaseAction implements ModelDriven<IndexModel>{
	
	HttpSession session = WebUtils.getSession();
	private IndexModel model = new IndexModel();
	
	@Resource
	private ICmsArticleService articleService;
	@Resource
	private ISysDictItemService sysDictItemService;
	@Resource
	private ICmsAdvPhotoService cmsAdvPhotoService;
	@Resource
	private ICmsChannelService cmsChannelService;
	@Resource
	private IActivityService activityService;
	@Resource
	private IProfessionalService professionalService;
	@Resource
	private ICmsArticleService cmsArticleService;
	/** */
	private static final long serialVersionUID = 1901866052106760355L;

	/**
	 * 系统前台首页
	 */
	@Action(value = "index", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/index.jsp") })
	public String index() {
		model.setEn("sdfsf");
		//查询跑马灯广告
		List<CmsArticle> cmsarticletopfive = articleService.getAdvByTopFive("en",null);
		model.setListCmsArticle(cmsarticletopfive);
		//查询华城动态
		//List<CmsArticle> cmsselectHC = articleService.getArticleByChannelId("3d5ffe8b-e890-11e4-ae9b-00266c0e7760");
		//model.setListCmsArticle1(cmsselectHC);
		
		//华诚动态，显示三条语句
		List<CmsArticle> getArticleByComplex1 = articleService.getArticleByComplex("3d5ffe8b-e890-11e4-ae9b-00266c0e7760",3,"en",null);
		model.setListArticleByComplex1(getArticleByComplex1);
		//存入session
		session.setAttribute("ListArticleByComplex1", getArticleByComplex1);
		// 查询案例分析和出版物  前5条  2015年4月23日10:39:39  周义德
		//案例分析 显示五条  案例分析channID  为319dd556-e891-11e4-ae9b-00266c0e7760
		List<CmsArticle> getArticleByComplex2 = articleService.getArticleByComplex2("b343df2e-ee3e-11e4-a8bb-0071cc954734",6,"en",null);
		model.setListArticleByComplex2(getArticleByComplex2);
		//出版物显示6条
	/*	List<CmsArticle> getArticleByComplex3 = articleService.getArticleByComplexs("bba13349-e891-11e4-ae9b-00266c0e7760", "c25f9c04-e891-11e4-ae9b-00266c0e7760",5);*/
		List<CmsArticle> getArticleByComplex3  = articleService.getArticleListByChannelId("3b4e3fb0-e891-11e4-ae9b-00266c0e7760",6,"en",null);
		model.setListArticleByComplex3(getArticleByComplex3);
		// 查询轮转首图
		List<CmsAdvPhoto> cmsAdvPhotos1 = cmsAdvPhotoService.listAdvPhotoByAdvId("1");
		model.setCmsAdvPhotos1(cmsAdvPhotos1);
		//读取视频栏目内全部视频内容
		List<CmsArticle> videoArticleList = cmsArticleService.getArticleByChannelId("8a6cf086-fa16-11e4-b164-00155d008ea1");
		model.setVideoArticleList(videoArticleList);
		List<Activity> listActivity = activityService.listActivity(2);
		model.setListActivity(listActivity);
		
		return INDEX;
	}
	
	/**
	 * 底部帮助信息
	 * @return
	 */
	@Action(value="frontFoot_en")
	public String frontFoot(){
		// 查询底部信息
//		List<CmsChannel> cmsChannels = cmsChannelService.listChannelByPid("0");
//		for (CmsChannel cmsChannel : cmsChannels) {
//			List<CmsArticle> cmsArticles = cmsArticleService.getArticleByChannelId(cmsChannel.getId());
//			cmsChannel.setCmsArticles(cmsArticles);
//		}
//		model.setCmsChannels(cmsChannels);
		return ajax(Status.success);
	}
	
	/**
	 * 搜索结果
	 * @throws UnsupportedEncodingException 
	 */
	@Action(value = "search_en", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/search_result.jsp") })
	public String search() throws UnsupportedEncodingException {
		// 获取查询标题的内容
		String searchName = model.getSearchName();
		String searchNames = searchName;
//		if(StringUtils.isNotBlank(searchName)){
//			searchNames = new String(searchName.getBytes("iso-8859-1"),"utf-8");
//		}
		// 根据标题查询
		CmsArticleCondition cc = new CmsArticleCondition();
		cc.setEn("en");
		String channelArray []= new String[]{"3d5ffe8b-e890-11e4-ae9b-00266c0e7760","439c9076-e890-11e4-ae9b-00266c0e7760","c1272bc2-ee3e-11e4-a8bb-0071cc954734","c884a615-ee3e-11e4-a8bb-0071cc954734","d25b2a55-ee3e-11e4-a8bb-0071cc954734","d987e778-ee3e-11e4-a8bb-0071cc954734","bba13349-e891-11e4-ae9b-00266c0e7760","c25f9c04-e891-11e4-ae9b-00266c0e7760","cec7815c-e891-11e4-ae9b-00266c0e7760","d4c4dcbf-e891-11e4-ae9b-00266c0e7760"};
		cc.setChannelIdArray(channelArray);
		cc.setKeyEn(searchNames);
		model.getPageResult().setPageSize(10);
		articleService.getArticleListByPage(model.getPageResult(), cc);
		model.setSearchName(searchNames);
		return INDEX;
	}
	@Action(value = "search_inc_en", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/search_result_inc.jsp") })
	public String search_inc() throws UnsupportedEncodingException {
		// 获取查询标题的内容
		String searchName = model.getSearchName();
		// 根据标题查询
		CmsArticleCondition cc = new CmsArticleCondition();
		cc.setEn("en");
		String channelArray []= new String[]{"3d5ffe8b-e890-11e4-ae9b-00266c0e7760","439c9076-e890-11e4-ae9b-00266c0e7760","c1272bc2-ee3e-11e4-a8bb-0071cc954734","c884a615-ee3e-11e4-a8bb-0071cc954734","d25b2a55-ee3e-11e4-a8bb-0071cc954734","d987e778-ee3e-11e4-a8bb-0071cc954734","bba13349-e891-11e4-ae9b-00266c0e7760","c25f9c04-e891-11e4-ae9b-00266c0e7760","cec7815c-e891-11e4-ae9b-00266c0e7760","d4c4dcbf-e891-11e4-ae9b-00266c0e7760"};
		cc.setChannelIdArray(channelArray);
		cc.setKeyEn(searchName);
		model.getPageResult().setPageSize(10);
		model.getPageResult().setCurrentPage(model.getPage());
		articleService.getArticleListByPage(model.getPageResult(), cc);
		model.setSearchName(searchName);
		model.setChannelId(model.getChannelId());
		return INDEX;
	}
	
	/**
	 * 常见问题
	 */
	@Action(value = "problem_en", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/common_problem.jsp") })
	public String problem() {
		return INDEX;
	}
	
	/**
	 * 活动列表
	 */
	@Action(value = "activities_ListPage_en", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/activities/activities_index.jsp") })
	public String listPage() {
		return INDEX;
	}
	
	/**
	 * 活动详情
	 *//*
	@Action(value = "activitiesDetail", results = { @Result(name = "index", location = "/WEB-INF/pages/front/activities/activities_detail.jsp") })
	public String listDetail() {
		
		return INDEX;
	}
	*/
	
	/**
	 * 文章列表
	 */
	@Action(value = "cms_ListPage_en", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/cms/cms_index.jsp") })
	public String cmsListPage() {
		// 查询案例分析和出版物  前5条  2015年4月23日10:39:39  周义德
		//案例分析 显示五条  案例分析channID  为319dd556-e891-11e4-ae9b-00266c0e7760
		CmsArticleCondition cc = new CmsArticleCondition();
		cc.setChannelId(model.getItem().getId());
		model.getPageResult().setPageSize(5);
		cc.setEn("en");
		articleService.getArticleListByPage(model.getPageResult(), cc);
		return INDEX;
	}
	
	/**
	 * 文章列表
	 */
	@Action(value = "cms_ListPage_inc_en", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/cms/cms_index_inc.jsp") })
	public String cms_ListPage_inc() {
		// 查询案例分析和出版物  前5条  2015年4月23日10:39:39  周义德
		//案例分析 显示五条  案例分析channID  为319dd556-e891-11e4-ae9b-00266c0e7760
		CmsArticleCondition cc = new CmsArticleCondition();
		cc.setChannelId(model.getItem().getId());
		model.getPageResult().setPageSize(5);
		cc.setEn("en");
		model.getPageResult().setCurrentPage(model.getPage());
		articleService.getArticleListByPage(model.getPageResult(), cc);
		return INDEX;
	}
	
	/**
	 * 文章列表
	 */
	@Action(value = "cms_ListPages_en", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/cms/cms_index.jsp") })
	public String cmsListPages() {
		//出版物显示五条
		return INDEX;
	}
	
	/**
	 * 文章列表
	 */
	@Action(value = "cms_ListPages_inc_en", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/cms/cms_index.jsp") })
	public String cms_ListPages_inc() {
		return INDEX;
	}
	
	
	/**
	 * 文章详情
	 */
	@Action(value = "cmsDetail_en", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/cms/cms_detail.jsp") })
	public String cmsDetail() {
		CmsArticle  cmsArticle = articleService.getArticleById(model.getItem().getId());
		model.setItem(cmsArticle);
		return INDEX;
	}
	
	/**
	 * 出版物列表
	 */
	@Action(value = "pub_ListPage_en", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/cms/pub_index.jsp") })
	public String pubListPage() {
		// 案例
		CmsArticleCondition cc = new CmsArticleCondition();
		cc.setEn("en");
		cc.setChannelId(model.getItem().getId());
		model.getPageResult().setPageSize(5);
		model.getPageResult().setCurrentPage(model.getPage());
		articleService.getArticleListByPageByParentIdEn(model.getPageResult());
		// 著作
		cc.setChannelId("cec7815c-e891-11e4-ae9b-00266c0e7760");
		model.getCmsArticlePageResult().setPageSize(5);
		model.getCmsArticlePageResult().setCurrentPage(model.getPage());
		articleService.getArticleListByPage(model.getCmsArticlePageResult(), cc);
		
		// 查询期刊 3b4e3fb0-e891-11e4-ae9b-00266c0e7760
		List<CmsChannel> cmsChannelList = cmsChannelService.listChannelByPid("3b4e3fb0-e891-11e4-ae9b-00266c0e7760");
		model.setCmsChannels(cmsChannelList);
		
		return INDEX;
	}
	
	/**
	 * 出版物列表
	 */
	@Action(value = "pub_ListPage_page_en", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/cms/pub_index_inc.jsp") })
	public String pub_ListPage_page() {
		// 案例
		model.getPageResult().setPageSize(5);
		model.getPageResult().setCurrentPage(model.getPage());
		articleService.getArticleListByPageByParentIdEn(model.getPageResult());
 
		return INDEX;
	}
	
	@Action(value = "pub_ListPage_page_inc2_en", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/cms/pub_index_inc2.jsp") })
	public String pub_ListPage_page_inc2() {
		CmsArticleCondition cc = new CmsArticleCondition();
		cc.setEn("en");
		cc.setChannelId("cec7815c-e891-11e4-ae9b-00266c0e7760");
		model.getCmsArticlePageResult().setPageSize(2);
		model.getCmsArticlePageResult().setCurrentPage(model.getPage());
		articleService.getArticleListByPage(model.getCmsArticlePageResult(), cc);
		
		return INDEX;
	}
	
	/**
	 * 出版物列表
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "pub_ListPage_inc_en")
	public String pub_ListPage_inc() {
		// 查询期刊  查询期刊下的pdf文档
		List<CmsArticle> cmsArticleList = articleService.listArticleByCidEn(model.getItem().getChannelId());
		JSONArray array =new JSONArray();
		for (CmsArticle cmsarticle : cmsArticleList) {
			JSONObject json = new JSONObject();
			json.put("id",cmsarticle.getId());
			json.put("name",cmsarticle.getEnAnnexFilename());
			json.put("path",cmsarticle.getEnAnnexPath());
			array.add(json);
		}
	 
		JSONObject object=new JSONObject();
		object.put("status", "success");
		object.put("cmsArticleList", array);
		return ajax(Status.success, object.toJSONString());
 
	}
	
	/**
	 * 出版物详情
	 */
	@Action(value = "pubDetail_en", results = { @Result(name = "index", location = "/WEB-INF/pages/front_en/cms/pub_detail.jsp") })
	public String pubDetail() {
		CmsArticle  cmsArticle = articleService.getArticleById(model.getItem().getId());
		model.setItem(cmsArticle);
		return INDEX;
	}
	
	/**
	 * 专业人员
	 */
	@Action(value = "toProfessional_en", results = { @Result(name = "success", location = "/WEB-INF/pages/front_en/professional/professional.jsp") })
	public String toProfessional() {
		// 数据词典查询准
		List<SysDictItem> sysDictItemList = sysDictItemService.listSysDictItemByDictId("9a04dbf7e88211e4ae9b00266c0e7760");
		model.setSysDictItemList(sysDictItemList);
		
		// 分页查询专业人员
		// 分页查询
		ProfessionalCondition cc = new ProfessionalCondition();
		/*cc.setChannelId("3b4e3fb0-e891-11e4-ae9b-00266c0e7760");*/
		model.getProfessionalPageResult().setPageSize(8);
		model.getProfessionalPageResult().setCurrentPage(model.getPage());
		professionalService.listProfessionalByPage(model.getProfessionalPageResult(), cc);
		return SUCCESS;
	}
	
	/**
	 * 专业人员
	 * @throws UnsupportedEncodingException 
	 */
	@Action(value = "toProfessional_inc_en", results = { @Result(name = "success", location = "/WEB-INF/pages/front_en/professional/professional_inc.jsp") })
	public String toProfessional_inc() throws UnsupportedEncodingException {
		return SUCCESS;
	}
	
	/**
	 * 专业人员
	 *//*
	@Action(value = "toProfessionalDetail", results = { @Result(name = "success", location = "/WEB-INF/pages/front/professional/professional_detail.jsp") })
	public String toProfessionalDetail() {
		model.setProfessional(professionalService.getProfessionalById(id));
		return SUCCESS;
	}*/
	
	/**
	 * 隐私保护
	 */
	@Action(value = "privacy_protect", results = { @Result(name = "success", location = "/WEB-INF/pages/front/cms/privacy_protect_detail.jsp") })
	public String privacy_protect() {
		List<CmsArticle> cmArticles = cmsArticleService.getArticleAll("badaf2ab-f2c5-11e4-b164-00155d008ea1");
		model.setItem(cmArticles.get(0));
		return SUCCESS;
	}
	
	/**
	 * 用户反馈
	 */
	@Action(value = "user_feedback", results = { @Result(name = "success", location = "/WEB-INF/pages/front/cms/privacy_protect_detail.jsp") })
	public String user_feedback() {
		List<CmsArticle> cmArticles = cmsArticleService.getArticleAll("ca143975-f2c5-11e4-b164-00155d008ea1");
		model.setItem(cmArticles.get(0));
		return SUCCESS;
	}
	
	/**
	 * 切换到中文，返回中文首页
	 */
	@Action(value = "chinese_index", results = { @Result(name = "index", location = "/WEB-INF/pages/front/index.jsp") })
	public String chinese_index() {
		//设置当前系统语言
		getSession().setAttribute(Constant.SYSTEM_LANGUAGE, SystemLanguage.Chinese);
		//查询跑马灯广告
		List<CmsArticle> cmsarticletopfive = articleService.getAdvByTopFive(null,null);
		model.setListCmsArticle(cmsarticletopfive);
		//查询华城动态
		//List<CmsArticle> cmsselectHC = articleService.getArticleByChannelId("3d5ffe8b-e890-11e4-ae9b-00266c0e7760");
		//model.setListCmsArticle1(cmsselectHC);
		//华诚动态，显示三条语句
		List<CmsArticle> getArticleByComplex1 = articleService.getArticleByComplex("3d5ffe8b-e890-11e4-ae9b-00266c0e7760",3,"en",null);
		model.setListArticleByComplex1(getArticleByComplex1);
		//存入session
		session.setAttribute("ListArticleByComplex1", getArticleByComplex1);
		// 查询案例分析和出版物  前5条  2015年4月23日10:39:39  周义德
		//案例分析 显示五条  案例分析channID  为319dd556-e891-11e4-ae9b-00266c0e7760
		List<CmsArticle> getArticleByComplex2 = articleService.getArticleByComplex2("b343df2e-ee3e-11e4-a8bb-0071cc954734",6,"en",null);
		model.setListArticleByComplex2(getArticleByComplex2);
		//出版物显示6条
	/*	List<CmsArticle> getArticleByComplex3 = articleService.getArticleByComplexs("bba13349-e891-11e4-ae9b-00266c0e7760", "c25f9c04-e891-11e4-ae9b-00266c0e7760",5);*/
		List<CmsArticle> getArticleByComplex3  = articleService.getArticleListByChannelId("3b4e3fb0-e891-11e4-ae9b-00266c0e7760",6,"en",null);
		model.setListArticleByComplex3(getArticleByComplex3);
		// 查询轮转首图
		List<CmsAdvPhoto> cmsAdvPhotos1 = cmsAdvPhotoService.listAdvPhotoByAdvId("1");
		model.setCmsAdvPhotos1(cmsAdvPhotos1);
		List<Activity> listActivity = activityService.listActivity(2);
		model.setListActivity(listActivity);
		return INDEX;
	}

	
	@Override
	public IndexModel getModel() {
		return model;
	}
}
