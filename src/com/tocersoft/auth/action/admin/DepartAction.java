package com.tocersoft.auth.action.admin;

import java.util.ArrayList;
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
import com.tocersoft.auth.entity.Depart;
import com.tocersoft.auth.model.DepartModel;
import com.tocersoft.auth.service.IDepartService;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;


@ParentPackage("admin")
@Namespace("/admin/auth/depart")
@Controller
public class DepartAction extends BaseAction implements ModelDriven<DepartModel>{
	private static final long serialVersionUID = -4488256309577995299L;

	private Log logger = LogFactory.getLog(DepartAction.class);

	private DepartModel model = new DepartModel();

	@Resource
	private IDepartService departService;



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
	@Action(value = "listDepartByPage")
	public String listDepartByPage(){
		departService.listDepartByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","name"
		});
		return ajax(root);
	}

	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/auth/depart_add.jsp") })
	public String toAdd(){
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String toUpdate(){
		Depart item = departService.getDepartById(model.getItem().getId());
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
			departService.delByIds(model.getSelIds().split(","));
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
				departService.add(model.getItem());
			}else{
				departService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public DepartModel getModel() {
		return model;
	}
	
	/**
	 * 显示部门树，默认展开第一级
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "list_depart")
	public void listDepartFirst(){
		JSONArray root = new JSONArray();
		List<Depart> list=new ArrayList<Depart>();
		// 初次加载
		if(StringUtils.isBlank(model.getParentId())){
			// 构造“全部”项
			JSONObject topJson = new JSONObject();
			topJson.put("id", "0");
			topJson.put("name", "HEROS");
			topJson.put("sort", 0);
			topJson.put("parentId", "-1");
			topJson.put("isParent", true);
			topJson.put("isDelete", 1);
			topJson.put("nocheck",true);
			topJson.put("open", true);
			list = departService.listDepartByPid("0");
			JSONArray children = new JSONArray();
			for (int i = 0; i < list.size(); i++){
				JSONObject json = makeJsonObject(list.get(i));
				children.add(json);
			}
			topJson.put("children", children);
			root.add(topJson);
		
		// 点击后加载
		}else{
			list = departService.listDepartByPid(model.getId());
			for (int i = 0; i < list.size(); i++){
				JSONObject json = makeJsonObject(list.get(i));
				root.add(json);
			}
		}
		ajax(root);
	}

	@SuppressWarnings("unchecked")
	private JSONObject makeJsonObject(Depart depart){
		JSONObject data = new JSONObject();
		data.put("id",depart.getId());
		data.put("name",depart.getName());
		if(depart.getParentId() == "0"){
			data.put("isParent", true);
		}else{
			List<Depart> list = departService.listDepartByPid(depart.getId());
			if(list.size() == 0){
				data.put("isParent", false);
			}else{
				data.put("isParent", true);
				data.put("nocheck",true);
			}
		}
		
		data.put("parentId",depart.getParentId());
		data.put("isDelete", depart.getDeleteFlag());
		return data;
	}
	
	/**
	 * 跳转到修改类别页面
	 */
	@Action(value="toUpdateDepart",results={@Result(name="toNewWin",location="/WEB-INF/pages/admin/auth/depart_add.jsp")})
	public String toUpdateCategory() {
		if(StringUtils.isNotBlank(model.getItem().getId())){
			// 获得类别的ID
			String nodeId = model.getItem().getId();
			// 获得类别信息
			Depart item = departService.getDepartById(nodeId);
			model.setItem(item);
		}
		return "toNewWin";
	}
	
	/**
	 * 新增类别
	 */
	@SuppressWarnings("unchecked")
	@Action(value="doAddDepart")
	public void doAddDepart() throws Exception {
		try {
			// 上级类别ID
			String parentId = model.getItem().getParentId();
			// 类别名称
			String name = model.getItem().getName();
			String note = model.getItem().getNote();
			int isParent=model.getItem().getIsParent();
			if (StringUtils.isBlank(model.getItem().getName())) {
				ajax(Status.error, "类别名称不能为空。");
				return;
			}
			// 排序
			Integer sort = model.getItem().getSort() == null ? 0 : model.getItem().getSort() ;
			model.getItem().setSort(sort);
			departService.doAddDepart(parentId,name,sort,note,isParent);
			//departService.add(model.getItem());
			JSONObject result = new JSONObject();
			result.put(STATUS_PARAMETER_NAME, "success");
			result.put(MESSAGE_PARAMETER_NAME, "添加成功");
			result.put("item", model.getItem());
			ajax(result);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "添加部门节点时发生异常："+e.getMessage();
			logger.error(msg,e);
			ajax(Status.error,msg);
		}
	}

	/**
	 * 修改商品类型
	 */
	@Action(value="doUpdateDepart")
	public void doUpdateDepart() throws Exception {
		String nodeId = model.getItem().getId();
		if (StringUtils.isBlank(nodeId)) {
			ajax(Status.error, "修改失败");
			return;
		}
		model.getItem().setId(nodeId);
		// 类别名称
		String name = model.getItem().getName();
		String note = model.getItem().getNote();
		if (StringUtils.isBlank(name)) {
			ajax(Status.error, "类别名称不能为空。");
			return;
		}
		// 排序
		Integer sort = model.getItem().getSort() == null ? 0 : model.getItem().getSort();
		departService.doUpdateDepart(nodeId, name, sort, note);
		ajax(Status.success, "修改成功。");
	}
	
	/**
	 * 删除部门类别
	 */
	@Action(value="delDepartById")
	@SuppressWarnings("unchecked")
	public void delDepartById() throws Exception {
		String id = model.getItem().getId();
		Depart depart = departService.getDepartById(id);
		// 删除标记位
//		if(null != depart && depart.getIsDelete() != null && depart.getIsDelete().intValue() == 1){
//			ajax(Status.error,"该类别不可删除");
//			return;
//		}
		int del=departService.delDepart(depart);
		if(del==1){
			ajax(Status.error,"删除失败,该节点下有数据");
		}
		ajax(Status.success);
	}
}



