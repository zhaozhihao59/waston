package com.tocersoft.game.action.front;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.member.model.MemTeamModel;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.tocersoft.member.entity.MemTeam;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.service.IMemTeamService;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.base.action.BaseAction;


@ParentPackage("front")
@Namespace("/mem_team")
@Controller
public class MemTeamFrontAction extends BaseAction implements ModelDriven<MemTeamModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(MemTeamFrontAction.class);

	private MemTeamModel model = new MemTeamModel();

	@Resource(name = "memTeamServiceImpl")
	private IMemTeamService memTeamService;

	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/member/mem_team_list.jsp") })
	public String index(){
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listMemTeamByPage")
	public String listMemTeamByPage(){
		memTeamService.listMemTeamByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","createDate","name","type","desc"
		});
		return ajax(root);
	}
	

	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/member/mem_team_add.jsp") })
	public String toAdd(){
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/member/mem_team_detail.jsp") })
	public String toUpdate(){
		MemTeam item = memTeamService.getMemTeamById(model.getItem().getId());
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
			memTeamService.delByIds(model.getSelIds().split(","));
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
				memTeamService.add(model.getItem());
			}else{
				memTeamService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	/**
	 * 用于自动补全控件
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "autoListAllMemTeam")
	public String autoListAllMember(){
		String key = getRequest().getParameter("key");
		List<MemTeam> memTeams = memTeamService.listMemTeamByKey(key);
		JSONArray root = new JSONArray();
		for(MemTeam item : memTeams){
			JSONObject json = new JSONObject();
			json.put("id", item.getId());
			json.put("name",item.getName());
			json.put("type",item.getType());
			root.add(json);
		}
		return ajax(root);
	}

	@Override
	public MemTeamModel getModel() {
		return model;
	}}

