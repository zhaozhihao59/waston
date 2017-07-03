package com.tocersoft.activity.action.front_jp;

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
import com.tocersoft.activity.dto.ActivityCondition;
import com.tocersoft.activity.entity.Activity;
import com.tocersoft.activity.model.ActivityModel;
import com.tocersoft.activity.service.IActivityService;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;

/**
 * 日文活动
 * @author hys
 *
 */
@ParentPackage("front")
@Namespace("/front/activity_jp")
@Controller
public class ActivityFrontJpAction extends BaseAction implements ModelDriven<ActivityModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(ActivityFrontJpAction.class);

	private ActivityModel model = new ActivityModel();

	@Resource(name = "activityServiceImpl")
	private IActivityService activityService;



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
	@Action(value = "listActivityByPage_jp")
	public String listActivityByPage(){
		activityService.listActivityByPage(model.getPageResult(),model.getCondition());
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
		Activity item = activityService.getActivityById(model.getItem().getId());
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
			activityService.delByIds(model.getSelIds().split(","));
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
				activityService.add(model.getItem());
			}else{
				activityService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	/**
	 * 活动列表
	 */
	@Action(value = "activities_ListPage_jp", results = { @Result(name = "index", location = "/WEB-INF/pages/front_jp/activities/activities_index.jsp") })
	public String listPage() {
		
		//查询正在报名的活动
		List<Activity> listActivityEnrollIng = activityService.listActivity(2);
		model.setListActivity(listActivityEnrollIng);
		//查询预告活动 
		List<Activity> listActivityPrediction = activityService.listActivity(1);
		model.setListActivityPrediction(listActivityPrediction);
		
		
		//历届活动分页查询
		ActivityCondition activityCondition = new ActivityCondition();
		model.getPageResult().setPageSize(8);
		activityCondition.setState(3);
		activityService.listActivityByPageByOld(model.getPageResult(), activityCondition);
		return INDEX;
	}
	/**
	 * 活动详情
	 */
	@Action(value = "activities_detail_jp", results = { @Result(name = "index", location = "/WEB-INF/pages/front_jp/activities/activities_detail.jsp") })
	public String activities_detail() {
		Activity item = activityService.getActivityById(model.getSelIds());
		model.setItem(item);
		return INDEX;
	}

	
	
	/**
	 * 活动列表 历届 抽取页面
	 */
	@Action(value = "ListPageByOld_jp", results = { @Result(name = "index", location = "/WEB-INF/pages/front_jp/activities/activities_index_inc.jsp") })
	public String ListPageByOld() {
		ActivityCondition activityCondition = new ActivityCondition();
		activityCondition.setState(3);
		model.getPageResult().setPageSize(8);
		model.getPageResult().setCurrentPage(model.getPage());
		activityService.listActivityByPageByOld(model.getPageResult(), activityCondition);
		return INDEX;
	}
	
	/**
	 * 活动详情
	 */
	@Action(value = "activitiesDetail_jp", results = { @Result(name = "index", location = "/WEB-INF/pages/front_jp/activities/activities_detail.jsp") })
	public String listDetail() {
		String id = model.getItem().getId();
		Activity Activity = activityService.getActivityById(id);
		model.setItem(Activity);
		return INDEX;
	}
	
	
	
	@Override
	public ActivityModel getModel() {
		return model;
	}}

