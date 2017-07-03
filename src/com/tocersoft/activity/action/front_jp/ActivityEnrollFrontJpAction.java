package com.tocersoft.activity.action.front_jp;

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
import com.tocersoft.activity.entity.ActivityEnroll;
import com.tocersoft.activity.model.ActivityEnrollModel;
import com.tocersoft.activity.service.IActivityEnrollService;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;

/**
 * 日文活动报名
 * @author hys
 *
 */

@ParentPackage("front")
@Namespace("/front/activityEnroll_jp")
@Controller
public class ActivityEnrollFrontJpAction extends BaseAction implements ModelDriven<ActivityEnrollModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(ActivityEnrollFrontJpAction.class);

	private ActivityEnrollModel model = new ActivityEnrollModel();

	@Resource(name = "activityEnrollServiceImpl")
	private IActivityEnrollService activityEnrollService;



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
	@Action(value = "listActivityEnrollByPage")
	public String listActivityEnrollByPage(){
		activityEnrollService.listActivityEnrollByPage(model.getPageResult(),model.getCondition());
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
		ActivityEnroll item = activityEnrollService.getActivityEnrollById(model.getItem().getId());
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
			activityEnrollService.delByIds(model.getSelIds().split(","));
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
				activityEnrollService.add(model.getItem());
			}else{
				activityEnrollService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	
	/**
	 * 立即报名
	 * @return 
	 */
	@Action(value="regeister_now")
	public String regeisterNow(){
		try {
			activityEnrollService.insertRegeister(model.getItem());
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}

	/**
	 * 检验邮箱是否已经报名过
	 * @author LHB
	 */
	@Action(value="check_email")
	public String checkEmail(){
		try {
			String activityId = model.getCondition().getActivityId();
			String email = model.getCondition().getEmail();
			int flag = activityEnrollService.checkEnrollEmail(activityId, email);
			if(flag>0){
				return ajax(Status.success,"100");
			}else{
				return ajax(Status.success,"200");
			}
			
		} catch (Exception e) {
			String msg = "查询时时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	/**
	 * 检验手机号是否已经报名过
	 * @author LHB
	 */
	@Action(value="check_mobile")
	public String check_mobile(){
		try {
			String activityId = model.getCondition().getActivityId();
			String mobile = model.getCondition().getMobile();
			int flag = activityEnrollService.checkEnrollMobile(activityId, mobile);
			if(flag>0){
				return ajax(Status.success,"100");
			}else{
				return ajax(Status.success,"200");
			}
			
		} catch (Exception e) {
			String msg = "查询时时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}

	@Override
	public ActivityEnrollModel getModel() {
		return model;
	}}

