package com.tocersoft.subscribe.action.front_en;

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
import com.tocersoft.subscribe.entity.Subscribe;
import com.tocersoft.subscribe.model.SubscribeModel;
import com.tocersoft.subscribe.service.ISubscribeService;


@ParentPackage("front")
@Namespace("/front/subscribe_en")
@Controller
public class SubscribeFrontEnAction extends BaseAction implements ModelDriven<SubscribeModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(SubscribeFrontEnAction.class);

	private SubscribeModel model = new SubscribeModel();

	@Resource(name = "subscribeServiceImpl")
	private ISubscribeService subscribeService;



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
	@Action(value = "listSubscribeByPage")
	public String listSubscribeByPage(){
		subscribeService.listSubscribeByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{});
		return ajax(root);
	}

	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String toadd(){
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String toUpdate(){
		Subscribe item = subscribeService.getSubscribeById(model.getItem().getId());
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
 * 查询邮箱是否订阅
 */
	@Action(value="check_email")
	public String check_email(){
		try {
			//100为已订阅200为未订阅
			int count = subscribeService.getSubscribeByemail(model.getCondition().getEmail(),model.getCondition().getChannelId());
			if(count > 0){
				return ajax(Status.success,"100");	
			}else{
				return ajax(Status.success,"200");
			}
			
		} catch (Exception e) {
			String msg = "查询时发生异常：" + e.getMessage();
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
				//创建日期
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
	 * 取消订阅
	 * @return
	 */
	@Action(value="cancle_subscribe")
	public String cancle_subscribe(){
		try {
			subscribeService.cancleSubscribe(model.getChannelId(),model.getEmail());
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}

	@Override
	public SubscribeModel getModel() {
		return model;
	}}

