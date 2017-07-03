package com.tocersoft.subscribe.action.admin;

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
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.cms.entity.CmsChannel;
import com.tocersoft.subscribe.entity.SubscribeSend;
import com.tocersoft.subscribe.model.SubscribeSendModel;
import com.tocersoft.subscribe.service.ISubscribeSendService;


@ParentPackage("admin")
@Namespace("/admin/subscribeSend")
@Controller
public class SubscribeSendAdminAction extends BaseAction implements ModelDriven<SubscribeSendModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(SubscribeSendAdminAction.class);

	private SubscribeSendModel model = new SubscribeSendModel();

	@Resource(name = "subscribeSendServiceImpl")
	private ISubscribeSendService subscribeSendService;



	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String index(){
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listSubscribeSendByPage")
	public String listSubscribeSendByPage(){
		subscribeSendService.listSubscribeSendByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{});
		return ajax(root);
	}

	/**
	 * 打开发送邮件页面
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/subscribe/subscribe_sendEmail.jsp") })
	public String sendEmail(){
		return SUCCESS;
	}



	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String toUpdate(){
		SubscribeSend item = subscribeSendService.getSubscribeSendById(model.getItem().getId());
		model.setItem(item);
		return SUCCESS;
	}

	/**
	 * 删除
	 * @return 
	 */
	@Action(value="del")
	@Validations(requiredStrings = {
				@RequiredStringValidator(fieldName = "selIds", message = "ID不能为空")
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String del(){
		try {
			subscribeSendService.delByIds(model.getSelIds().split(","));
			return ajax(Status.success,"删除成功");
		} catch (Exception e) {
			String msg = "删除时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}

	/**
	 * 保存
	 * @return 
	 */
	@Action(value="doSave")
	public String doSave(){
		try {
			if(StringUtils.isBlank(model.getItem().getId())){
				subscribeSendService.add(model.getItem());
			}else{
				subscribeSendService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	/**
	 * 发送邮件
	 * @return
	 */
	@Action(value="send_email")
	public String send_email(){
		try {
			String subscribe[] = model.getReceiver().split(",");
			SubscribeSend subscribeSend = model.getItem();
			subscribeSendService.sendEmail(subscribe, subscribeSend);
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public SubscribeSendModel getModel() {
		return model;
	}}

