package com.tocersoft.work.action.front;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.model.CmsArticleModel;
import com.tocersoft.cms.service.ICmsArticleService;
import com.tocersoft.game.service.IGameEnrollService;
import com.tocersoft.game.service.IGameItemService;
import com.tocersoft.game.service.IGameService;
import com.tocersoft.member.service.IMemTeamMemberService;
import com.tocersoft.member.service.IMemTeamService;
import com.tocersoft.member.service.IMemberService;
import com.tocersoft.system.service.ISysDictItemService;
import com.tocersoft.system.service.ISysUploadFileService;


@ParentPackage("front")
@Namespace("/work")
@Controller
public class workFrontAction extends BaseAction implements ModelDriven<CmsArticleModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(workFrontAction.class);

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
	@Action(value = "knowledge_list", results = { @Result(name = "success", location = "/WEB-INF/pages/front/work/knowledge_index.jsp") })
	public String knowledge_list(){
		return SUCCESS;
	}
	@Action(value = "job_opportunity", results = { @Result(name = "success", location = "/WEB-INF/pages/front/work/job_opportunity.jsp") })
	public String job_opportunity(){
		return SUCCESS;
	}
	@Action(value = "property_agency", results = { @Result(name = "success", location = "/WEB-INF/pages/front/work/property_agency.jsp") })
	public String property_agency(){
		CmsArticle article = cmsArticleService.getArticleById("4a1d0f2466eb4a7d8adc8d8da426aacb");
		model.setItem(article);
		return SUCCESS;
	}
	@Action(value = "about", results = { @Result(name = "success", location = "/WEB-INF/pages/front/work/about.jsp") })
	public String about(){
		return SUCCESS;
	}
	@Action(value = "contact_us", results = { @Result(name = "success", location = "/WEB-INF/pages/front/work/contact_us.jsp") })
	public String contact_us(){
		return SUCCESS;
	}

	@Override
	public CmsArticleModel getModel() {
		return model;
	}}


