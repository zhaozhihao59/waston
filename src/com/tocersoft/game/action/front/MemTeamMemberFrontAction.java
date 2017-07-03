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
import com.tocersoft.member.model.MemTeamMemberModel;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.tocersoft.member.entity.MemTeamMember;
import com.tocersoft.member.service.IMemTeamMemberService;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.base.action.BaseAction;


@ParentPackage("front")
@Namespace("/memTeamMember")
@Controller
public class MemTeamMemberFrontAction extends BaseAction implements ModelDriven<MemTeamMemberModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(MemTeamMemberFrontAction.class);

	private MemTeamMemberModel model = new MemTeamMemberModel();

	@Resource(name = "memTeamMemberServiceImpl")
	private IMemTeamMemberService memTeamMemberService;



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
	@Action(value = "listMemTeamMemberByPage")
	public String listMemTeamMemberByPage(){
		memTeamMemberService.listMemTeamMemberByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","memberId","teamId","type","name","mobile","email"
		});
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
		MemTeamMember item = memTeamMemberService.getMemTeamMemberById(model.getItem().getId());
		model.setItem(item);
		return SUCCESS;
	}

	/**
	 * 添加队员
	 * @return 
	 */
	@Action(value = "addTeamMeber", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/member/teamMember_list.jsp") })
	public String addTeamMeber(){
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
			memTeamMemberService.delByIds(model.getSelIds().split(","));
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
				memTeamMemberService.add(model.getItem());
			}else{
				memTeamMemberService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	/**
	 * 添加保存
	 * @return 
	 */
	@Action(value="doUpdate")
	public String doUpdate(){
		try {
			model.getItem().setUpdateBy(this.getCurrentUser().getName());
				memTeamMemberService.addTeamMember(model.getItem());
			return ajax(Status.success,"保存成功!");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	
	/**
	 * 根据TeamID查询
	 * @return 
	 */
	@Action(value = "listMemTeamMemberByTeamId")
	public String listMemTeamMemberByTeamId(){
		List<MemTeamMember> teamMemberList= memTeamMemberService.listMemTeamMemberByTeamId(model.getItem().getTeamId());
		JSONArray root = new JSONArray();
		for (MemTeamMember item : teamMemberList) {
			JSONObject json = new JSONObject();
			json.put("memberId", item.getMemberId());
			json.put("name", item.getName());
			json.put("mobile",item.getMobile());
			json.put("idCardNo",item.getIdCardNo());
			json.put("sex",item.getSex());
			json.put("type",item.getType());
			root.add(json);
		}
			return ajax(root);
	}

	@Override
	public MemTeamMemberModel getModel() {
		return model;
	}}

