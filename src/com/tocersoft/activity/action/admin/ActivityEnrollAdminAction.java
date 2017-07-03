package com.tocersoft.activity.action.admin;

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
import com.tocersoft.base.action.BaseAction.Status;
import com.tocersoft.base.utils.WebUtils;


@ParentPackage("admin")
@Namespace("/admin/activityEnroll")
@Controller
public class ActivityEnrollAdminAction extends BaseAction implements ModelDriven<ActivityEnrollModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(ActivityEnrollAdminAction.class);

	private ActivityEnrollModel model = new ActivityEnrollModel();

	@Resource(name = "activityEnrollServiceImpl")
	private IActivityEnrollService activityEnrollService;



	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/activity/activity_enroll_list.jsp") })
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
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","activityName","name","mobile","email","companyName","positionName","createDate"});
		return ajax(root);
	}

	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/activity/activity_enroll_add.jsp") })
	public String toAdd(){
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/activity/activity_enroll_update.jsp") })
	public String toUpdate(){
		ActivityEnroll item = activityEnrollService.getActivityEnrollById(model.getSelIds());
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
	 * 导出
	 * @author liuhongbo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value="doExport")
	public String doExport(){
		try {
			String path = activityEnrollService.doExport(model.getCondition());
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
	public ActivityEnrollModel getModel() {
		return model;
	}}

