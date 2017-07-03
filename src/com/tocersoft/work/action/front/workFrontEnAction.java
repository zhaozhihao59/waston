package com.tocersoft.work.action.front;

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

import com.bestpay.model.CommModel;
import com.bestpay.util.BestpayUtil;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.base.utils.DoubleUtil;
import com.tocersoft.base.utils.UUIDUtil;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.model.CmsArticleModel;
import com.tocersoft.cms.service.ICmsArticleService;
import com.tocersoft.game.dto.GameCondition;
import com.tocersoft.game.entity.Game;
import com.tocersoft.game.entity.GameEnroll;
import com.tocersoft.game.entity.GameEnrollTeam;
import com.tocersoft.game.entity.GameItem;
import com.tocersoft.game.model.GameModel;
import com.tocersoft.game.service.IGameEnrollService;
import com.tocersoft.game.service.IGameItemService;
import com.tocersoft.game.service.IGameService;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.service.IMemTeamMemberService;
import com.tocersoft.member.service.IMemTeamService;
import com.tocersoft.member.service.IMemberService;
import com.tocersoft.system.dto.SysUploadFileCondition;
import com.tocersoft.system.entity.SysDictItem;
import com.tocersoft.system.entity.SysUploadFile;
import com.tocersoft.system.service.ISysDictItemService;
import com.tocersoft.system.service.ISysUploadFileService;


@ParentPackage("front")
@Namespace("/work_en")
@Controller
public class workFrontEnAction extends BaseAction implements ModelDriven<CmsArticleModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(workFrontEnAction.class);

	private CmsArticleModel model = new CmsArticleModel();

	@Resource(name = "gameServiceImpl")
	private IGameService gameService;
	@Resource(name = "gameEnrollServiceImpl")
	private IGameEnrollService gameEnrollService;
	@Resource(name = "memberServiceImpl")
	private IMemberService memberService;
	@Resource(name = "gameItemServiceImpl")
	private IGameItemService gameItemService;
	@Resource(name = "memTeamMemberServiceImpl")
	private IMemTeamMemberService memTeamMemberService;
	@Resource(name = "memTeamServiceImpl")
	private IMemTeamService memTeamService;
	@Resource(name = "sysUploadFileServiceImpl")
	private ISysUploadFileService uploadService;
	@Resource(name = "sysDictItemServiceImpl")
	private ISysDictItemService dictItemService;
	@Resource(name = "sysUploadFileServiceImpl")
	private ISysUploadFileService fileService;
	@Resource(name = "cmsArticleServiceImpl")
	private ICmsArticleService cmsArticleService;
	
	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "knowledge_list_en", results = { @Result(name = "success", location = "/WEB-INF/pages/front_en/work/knowledge_index.jsp") })
	public String knowledge_list(){
		return SUCCESS;
	}
	@Action(value = "job_opportunity_en", results = { @Result(name = "success", location = "/WEB-INF/pages/front_en/work/job_opportunity.jsp") })
	public String job_opportunity(){
		return SUCCESS;
	}
	@Action(value = "about_en", results = { @Result(name = "success", location = "/WEB-INF/pages/front_en/work/about.jsp") })
	public String about(){
		return SUCCESS;
	}
	@Action(value = "property_agency_en", results = { @Result(name = "success", location = "/WEB-INF/pages/front_en/work/property_agency_en.jsp") })
	public String property_agency(){
		CmsArticle article = cmsArticleService.getArticleById("4a1d0f2466eb4a7d8adc8d8da426aacb");
		model.setItem(article);
		return SUCCESS;
	}
	@Action(value = "contact_us_en", results = { @Result(name = "success", location = "/WEB-INF/pages/front_en/work/contact_us.jsp") })
	public String contact_us(){
		return SUCCESS;
	}
	
	@Override
	public CmsArticleModel getModel() {
		return model;
	}}

