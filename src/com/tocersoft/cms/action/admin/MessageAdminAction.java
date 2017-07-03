package com.tocersoft.cms.action.admin;

import javax.annotation.Resource;

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
import com.tocersoft.auth.entity.User;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.cms.entity.CmsArticle;
import com.tocersoft.cms.entity.Message;
import com.tocersoft.cms.model.MessageModel;
import com.tocersoft.cms.service.ICmsArticleService;
import com.tocersoft.cms.service.IMessageService;

@ParentPackage("admin")
@Namespace("/admin/cms/message")
@Controller
public class MessageAdminAction extends BaseAction implements ModelDriven<MessageModel>{

	private static final long serialVersionUID = 5107024224886599818L;
	
	private MessageModel model = new MessageModel();
	
	private Log logger = LogFactory.getLog(MessageAdminAction.class);
	
	@Resource
	private IMessageService messageService;
	
	@Resource
	private ICmsArticleService articleService;
	
	@Action(value = "index", results = {@Result(name = "index", location = "/WEB-INF/pages/admin/cms/message_list.jsp") })
	public String home() {
		return INDEX;
	}
	
	@Action(value = "toDetail", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/cms/message_detail.jsp") })
	public String toDetail() {
		String id = model.getItem().getId();
		Message item = messageService.getMessageById(id);
		
		// 如果是会员独享活动的预约的话，将活动内容放入Model中
		if(item.getMessageType()!=null && item.getMessageType().intValue() == 4){
			CmsArticle article = articleService.getArticleById(item.getProductId());
			model.setArticle(article);
		}
		
		model.setItem(item);
		return INDEX;
	}
	
	/**
	 * 分页查询文章
	 */
	@Action(value = "listMessageByPage")
	public String listMessageByPage(){
		messageService.listMessageByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String []{"id","createDate","replyDate","createName","mobile","content","productName","email","other","status","replyId","replyName","messageType"});
		return ajax(root);
	}
	
	/**
	 * 修改回复状态
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "isReply")
	public String isReply(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Message item = model.getItem();
		item.setReplyId(user.getId());
		item.setStatus(1);
		messageService.replyMessage(item);
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		return ajax(obj);
	}
	
	/**
	 * 删除
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "doDel")
	public String doDel(){
		messageService.delMessage(model.getIds());
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		return ajax(obj);
	}
	
	@Override
	public MessageModel getModel() {
		return model;
	}
}
