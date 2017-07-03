package com.tocersoft.subscribe.action.admin;

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
import com.tocersoft.subscribe.entity.SubscribeSendRecord;
import com.tocersoft.subscribe.model.SubscribeSendRecordModel;
import com.tocersoft.subscribe.service.ISubscribeSendRecordService;


@ParentPackage("admin")
@Namespace("/admin/subscribeSendRecord")
@Controller
public class SubscribeSendRecordAdminAction extends BaseAction implements ModelDriven<SubscribeSendRecordModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(SubscribeSendRecordAdminAction.class);

	private SubscribeSendRecordModel model = new SubscribeSendRecordModel();

	@Resource(name = "subscribeSendRecordServiceImpl")
	private ISubscribeSendRecordService subscribeSendRecordService;



	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/subscribe/subscribe_send_record_list.jsp") })
	public String index(){
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listSubscribeSendRecordByPage")
	public String listSubscribeSendRecordByPage(){
		subscribeSendRecordService.listSubscribeSendRecordByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{});
		return ajax(root);
	}

	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String toAdd(){
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String toUpdate(){
		SubscribeSendRecord item = subscribeSendRecordService.getSubscribeSendRecordById(model.getItem().getId());
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
			subscribeSendRecordService.delByIds(model.getSelIds().split(","));
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
				subscribeSendRecordService.add(model.getItem());
			}else{
				subscribeSendRecordService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}

	/**
	 *邮件发送记录列表
	 *@author lhb
	 */
	@Action(value="listSubscribeSendRecord")
	public String listSubscribeSendRecord(){
		
		try {
			subscribeSendRecordService.subscribeSendList(model.getPageResult(),model.getCondition());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","subscribeName","subscribeMobile","email","subscribeChannelName","subscribeSendTitle","createDate"});
		return ajax(root);
			
		
	}

	@Override
	public SubscribeSendRecordModel getModel() {
		return model;
	}}

