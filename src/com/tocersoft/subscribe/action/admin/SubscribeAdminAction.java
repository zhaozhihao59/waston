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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.auth.entity.Role;
import com.tocersoft.auth.entity.User;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.action.BaseAction.Status;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.cms.entity.CmsChannel;
import com.tocersoft.cms.service.ICmsChannelService;
import com.tocersoft.cms.service.impl.CmsChannelServiceImpl;
import com.tocersoft.subscribe.action.front.SubscribeFrontAction;
import com.tocersoft.subscribe.entity.Subscribe;
import com.tocersoft.subscribe.model.SubscribeModel;
import com.tocersoft.subscribe.service.ISubscribeService;


@ParentPackage("admin")
@Namespace("/admin/subscribe")
@Controller
public class SubscribeAdminAction extends BaseAction implements ModelDriven<SubscribeModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(SubscribeFrontAction.class);

	private SubscribeModel model = new SubscribeModel();

	@Resource(name = "subscribeServiceImpl")
	private ISubscribeService subscribeService;
	@Resource
	private ICmsChannelService cmsChannelService;
	



	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/subscribe/subscribe_list.jsp") })
	public String index(){
		return SUCCESS;
	}
	/**
	 * 订阅名单列表
	 * @author 刘鸿博
	 * 2015-04-22
	 */
	

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listSubscribeByPage")
	public String listSubscribeByPage(){
		try {
			subscribeService.listSubscribeByPage(model.getPageResult(),model.getCondition());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","name","createDate","mobile","email","companyName","position","channelName","state"});
		return ajax(root);
	}

	

	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index_qikan_select", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/subscribe/subscribe_list_select.jsp") })
	public String index_qikan_select(){
		return SUCCESS;
	}
	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listSubscribeByPage_select")
	public String listSubscribeByPage_select(){
		try {
			subscribeService.listSubscribeByPage(model.getPageResult(),model.getCondition());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","name","createDate","mobile","email","companyName","position","channelName","state"});
		return ajax(root);
	}

	/**
	 * 选择收件人分页查询
	 * @return 
	 */
	@Action(value = "list_subscribe_by_page_select")
	public String list_subscribe_by_page_select(){
		try {
			subscribeService.listSubscribeSelectByPage(model.getPageResult(),model.getCondition());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","name","createDate","mobile","email","companyName","position","channelName","state"});
		return ajax(root);
	}

	
	
	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/subscribe/subscribe_add.jsp") })
	public String toAdd(){
		List<CmsChannel> listByParent = cmsChannelService.listChannelByPid("3b4e3fb0-e891-11e4-ae9b-00266c0e7760");
		model.setCmsChannel(listByParent);
		return SUCCESS;
	}

	
	
	
	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/subscribe/subscribe_update.jsp") })
	public String toUpdate(){
		List<CmsChannel> listByParent = cmsChannelService.listChannelByPid("3b4e3fb0-e891-11e4-ae9b-00266c0e7760");
		model.setCmsChannel(listByParent);
		Subscribe item = subscribeService.getSubscribeById(model.getSelIds());
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
			subscribeService.delByIds(model.getSelIds().split(","));
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
				subscribeService.add(model.getItem());
			}else{
				subscribeService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	
	/**
	 * 根据条件查询
	 */
	@Action(value="search_subscribe")
	public String search_subscribe(){
		try {
			subscribeService.delByIds(model.getSelIds().split(","));
			return ajax(Status.success,"删除成功");
		} catch (Exception e) {
			String msg = "删除时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	
	/**
	 * 导出
	 * @author liuhongbo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value="doExport")
	public String doExport(){
		try {
			String path = subscribeService.doExport(model.getCondition());
			JSONObject result = new JSONObject();
			result.put(STATUS_PARAMETER_NAME, Status.success);
			result.put(MESSAGE_PARAMETER_NAME, "导出成功");
			result.put("path", path);
			return ajax(result);
		} catch (Exception e) {
			String msg = "操作时发生异常："+e.getMessage();
			e.printStackTrace();
			return ajax("操作失败");
		}
	}

	@Override
	public SubscribeModel getModel() {
		return model;
	}}

