package com.tocersoft.cms.action.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.auth.entity.User;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.action.BaseAction.Status;
import com.tocersoft.base.utils.UUIDUtil;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.entity.CmsChannel;
import com.tocersoft.cms.model.CmsArticleModel;
import com.tocersoft.cms.service.ICmsArticleService;
import com.tocersoft.cms.service.ICmsChannelService;

@ParentPackage("admin")
@Namespace("/admin/cms/article")
@Controller
public class ArticleAdminAction extends BaseAction implements ModelDriven<CmsArticleModel>{

	private static final long serialVersionUID = 5107024224886599818L;
	
	private CmsArticleModel model = new CmsArticleModel();
	
	private Log logger = LogFactory.getLog(ArticleAdminAction.class);
	@Resource
	private ICmsArticleService cmsArticleService;
	
	@Resource
	private ICmsChannelService cmsChannelService;
	
	@Action(value = "index", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/cms/article_list.jsp") })
	public String home() {
		// 获取所有栏目
		List<CmsChannel> list = cmsChannelService.getCmsChannelAll();
		model.setCmsChannels(list);
		return INDEX;
	}
	
	@Action(value = "index_qikan", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/cms/article_list_qikan.jsp") })
	public String index_qikan() {
		// 获取所有栏目
		List<CmsChannel> list = cmsChannelService.getCmsChannelAll();
		model.setCmsChannels(list);
		return INDEX;
	}
	
	/**
	 * 分页查询文章
	 */
	@Action(value = "searchArticleList_qikan")
	public String searchArticleList_qikan(){
		cmsArticleService.listArticleListByChannelId(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String []{"id","name","createDate","isPublish","channelName","sort","channelId","content"});
		return ajax(root);
	}
	
	@Action(value = "to_add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/cms/article_add.jsp") })
	public String toAdd() {
		// 获取所有栏目
//		List<CmsChannel> list = cmsChannelService.getCmsChannelAll();
//		model.setCmsChannels(list);
		if(model.getItem()!= null && model.getItem().getChannelId()!= null && StringUtils.isNotBlank(model.getItem().getChannelId())){
			CmsChannel channel = cmsChannelService.getCmsChannelById(model.getItem().getChannelId());
			model.getItem().setChannelName(channel.getName());
		}
		return SUCCESS;
	}
	
	@Action(value = "to_update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/cms/article_add.jsp") })
	public String toUpdate() {
		try {
		// 查找要修改的文章
		String id = model.getItem().getId();
		CmsArticle art = cmsArticleService.getArticleById(id);
		model.setItem(art);
		// 获取所有栏目
		List<CmsChannel> list = cmsChannelService.getCmsChannelAll();
		model.setCmsChannels(list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 分页查询文章
	 */
	@Action(value = "searchArticleList")
	public String searchArticleList(){
		List<CmsChannel> list= cmsChannelService.listChannelByPid(model.getCondition().getChannelId());
		if(list.size()!=0)
		{
			model.getCondition().setParentID(model.getCondition().getChannelId());
			model.getCondition().setChannelId("");
		}
		cmsArticleService.getArticleListByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String []{"id","name","createDate","isPublish","channelName","sort","channelId"});
		return ajax(root);
	}
	
	/**
	 * 添加文章
	 */
	@Action(value = "addArticle")
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "item.name" , message = "文章标题不能为空")
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String addArticle(){
		try {
			CmsArticle item = model.getItem();
			item.setId(UUIDUtil.uuid());
			// 设置创建时间
			//item.setCreateDate(new Date());
			// 设置创建人
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			item.setCreateBy(user.getId());
			// 保存实体
			cmsArticleService.doSave(item);
			
			return ajax(Status.success,item.getId());
			
		} catch (Exception e) {
			String msg = "操作时发生异常："+ e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error,msg);
		}
	}
	
	/**
	 * 修改文章
	 */
	@Action(value = "updateArticle")
	@Validations(requiredStrings = {
				@RequiredStringValidator(fieldName = "item.channelId" , message = "栏目名称不能为空"),
				@RequiredStringValidator(fieldName = "item.name" , message = "文章标题不能为空")
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String updateArticle(){
		try {
			CmsArticle item = model.getItem();
			CmsArticle dbItem = cmsArticleService.getArticleById(item.getId());
			if(null != dbItem){
				// 从数据库取得实体后，将客户端的传入的值进行封装
				dbItem.setChannelId(item.getChannelId());
				dbItem.setContent(item.getContent());
				dbItem.setSort(item.getSort());
				dbItem.setName(item.getName());
				dbItem.setKeyword(item.getKeyword());
				dbItem.setKeyword2(item.getKeyword2());
				dbItem.setKeyword3(item.getKeyword3());
				dbItem.setSummary(item.getSummary());
				dbItem.setPath(item.getPath());
				dbItem.setFilename(item.getFilename());
				dbItem.setAuthor(item.getAuthor());
				dbItem.setAuthorEn(item.getAuthorEn());
				dbItem.setAuthorJp(item.getAuthorJp());
				dbItem.setSource(item.getSource());
				dbItem.setAnnexFilename(item.getAnnexFilename());
				dbItem.setAnnexPath(item.getAnnexPath());
				dbItem.setEnName(item.getEnName());
				dbItem.setJpName(item.getJpName());
				dbItem.setEnContent(item.getEnContent());
				dbItem.setJpContent(item.getJpContent());
				dbItem.setEnSummary(item.getEnSummary());
				dbItem.setJpSummary(item.getJpSummary());
				dbItem.setEnAnnexFilename(item.getEnAnnexFilename());
				dbItem.setJpAnnexFilename(item.getJpAnnexFilename());
				dbItem.setCreateDate(item.getCreateDate());
				dbItem.setJpAnnexPath(item.getJpAnnexPath());
				dbItem.setEnAnnexPath(item.getEnAnnexPath());
				// 设置创建时间
				dbItem.setUpdateDate(new Date());
				// 保存实体
				cmsArticleService.doUpdate(dbItem);
				
				return ajax(Status.success,dbItem.getId());
			}else{
				return ajax(Status.error,"要保存的数据不存在");
			}
		} catch (Exception e) {
			String msg = "操作时发生异常："+ e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error,msg);
		}
	}
	
	/**
	 * 批量删除文章
	 */
	@Action(value = "delArticle")
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "model.articleId" , message = "文章ID不能为空"),
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String delArticle(){
		try {
			cmsArticleService.delArticle(model.getArticleId());
			return ajax(Status.success,"删除成功");
		} catch (Exception e) {
			String msg = "操作时发生异常："+ e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error,msg);
		}
	}
	
	@Action(value = "unPublish")
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "model.articleId" , message = "文章ID不能为空"),
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String unPublish(){
		try {
			cmsArticleService.unPublish(model.getArticleId());
			return ajax(Status.success,"取消发布成功");
		} catch (Exception e) {
			String msg = "操作时发生异常："+ e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error,msg);
		}
	}
	@Action(value = "publish")
	@Validations(
			requiredStrings = {
				@RequiredStringValidator(fieldName = "model.articleId" , message = "文章ID不能为空"),
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String publish(){
		try {
			cmsArticleService.publish(model.getArticleId());
			return ajax(Status.success,"发布成功");
		} catch (Exception e) {
			String msg = "操作时发生异常："+ e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error,msg);
		}
	}
	@Override
	public CmsArticleModel getModel() {
		return model;
	}

	
}
