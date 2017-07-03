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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.auth.entity.Right;
import com.tocersoft.auth.model.RightModel;
import com.tocersoft.auth.service.IRightService;
import com.tocersoft.auth.service.IRoleService;
import com.tocersoft.base.action.BaseAction;

@ParentPackage("admin")
@Namespace("/admin/auth/right")
@Controller
public class RightAction extends BaseAction implements ModelDriven<RightModel> {

	private static final long serialVersionUID = -3396342158526697542L;
	private Log logger = LogFactory.getLog(RightAction.class);

	private RightModel model = new RightModel();

	@Resource
	private IRightService rightService;

	@Autowired
	private IRoleService roleService;
	
	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = {@Result(name = "success", location = "/WEB-INF/pages/admin/auth/right_list.jsp") })
	public String index(){
		return SUCCESS;
	}
	
	/**
	 * 展示树状操作权限
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value="list")
	public String list(){
		JSONArray root = new JSONArray();
		List<Right> list = rightService.listRightByPid(model.getPid(),3);
		if(null != list && list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Right right = list.get(i);
				JSONObject json = makeRightTreeJsonForSet(right);
				
				List<Right> listChild = right.getChildRights();
				JSONArray arrayChild = new JSONArray();
				for(Right rightChild : listChild){
					JSONObject obj = makeRightTreeJsonForSet(rightChild);
					List<Right> listChildSub = rightChild.getChildRights();
					JSONArray arrayChildSub = new JSONArray();
					for(Right rightChildSub : listChildSub){
						JSONObject objSub = makeRightTreeJsonForSet(rightChildSub);
						arrayChildSub.add(objSub);
					}
					obj.put("children", arrayChildSub);
					arrayChild.add(obj);
				}
				json.put("children", arrayChild);
				root.add(json);
			}
		}
		return ajax(root);
	}
	
	/**
	 * 加载权限数据
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "list_right")
	public void listRightByPid() {
		String pid = model.getPid();
		List<Right> rights = rightService.listRightByPid(pid, 2);

		JSONArray array = new JSONArray();
		for (Right right : rights) {
			JSONObject obj = new JSONObject();
			obj = this.makeRightTreeJsonObject(right, pid);

			List<Right> childRights = right.getChildRights();
			JSONArray subArray = new JSONArray();
			for (Right subRight : childRights) {
				JSONObject subObj = new JSONObject();
				subObj = this.makeRightTreeJsonObject(subRight, pid);
				subArray.add(subObj);
			}
			obj.put("children", subArray);
			array.add(obj);
		}

		this.ajax(array);

	}

	@SuppressWarnings("unchecked")
	@Action(value = "list_right_all")
	public String listAllRight() {
		String roleId=model.getRoleId();
		List<Right> rightList=new ArrayList<Right>();
		if(StringUtils.isNotBlank(roleId)){
			rightList=roleService.getRolePermissionsById(roleId);
		}
		List<Right> rightAll = rightService.getAllEnablePermissions();
		JSONArray result = new JSONArray();
		
		for (Right right : rightAll) {
			boolean checked=false;
			if(null!=rightList&&rightList.size()>0){
				for (Right r : rightList) {
					if(right.getId().equals(r.getId())){
						checked=true;
					}
				}
			}
			result.add(makeRightTreeJsonObject(right,checked));
		}
		System.out.println(result.toJSONString());
		return ajax(result.toJSONString(),"text/json");
	}


	@SuppressWarnings("unchecked")
	private JSONObject makeRightTreeJsonObject(Right right, String pid) {
		JSONObject data = new JSONObject();
		data.put("id", right.getId());
		data.put("name", right.getName());
		data.put("sort", right.getSort());
		data.put("funUrl", right.getUrl());
		data.put("tip", right.getTip());
		data.put("location", right.getLocation());
		if(null != right.getLocation() && right.getLocation().intValue() != 3){
			data.put("isParent", true);
		}else{
			data.put("isParent", false);
		}
		data.put("open", true);
		data.put("pid", pid);
		return data;
	}
	
	/**
	 * 为权限设置构建树形菜单结构
	 * @param right
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private JSONObject makeRightTreeJsonForSet(Right right) {
		JSONObject data = new JSONObject();
		data.put("id", right.getId());
		data.put("name", right.getName());
		data.put("tip", right.getTip());
		data.put("sort", right.getSort());
		data.put("funUrl", right.getUrl());
		data.put("location", right.getLocation());
		data.put("parentId", right.getParentId());
		
		if(null != right.getLocation() && right.getLocation().intValue() != 3){
			data.put("isParent", true);
		}else{
			data.put("isParent", false);
		}
		if(null != right.getLocation() && right.getLocation().intValue() == 1){
			data.put("open", true);
		}else{
			data.put("open", false);
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	private JSONObject makeRightTreeJsonObject(Right right,Boolean checked) {
		JSONObject data = new JSONObject();
		data.put("id", right.getId());
		data.put("pid", right.getParentId());
		data.put("name", right.getName());
		data.put("checked", checked);
		data.put("open", true);
		data.put("sort", right.getSort());
		data.put("tip", right.getTip());
		data.put("url", right.getUrl());
		data.put("location", right.getLocation());
		data.put("parentId", right.getParentId());
		return data;
	}
	
	/**
	 * 跳转到修改类别页面
	 */
	@Action(value="toUpdateRight",results={@Result(name="toNewWin",location="/WEB-INF/pages/admin/auth/right_add.jsp")})
	public String toUpdateRight() {
		if(StringUtils.isNotBlank(model.getItem().getId())){
			// 获得类别的ID
			String nodeId = model.getItem().getId();
			// 获得类别信息
			Right item = rightService.getRightById(nodeId);
			model.setItem(item);
		}
		return "toNewWin";
	}
	
	/**
	 * 新增类别
	 */
	@SuppressWarnings("unchecked")
	@Action(value="doAddRight")
	public void doAddRight() throws Exception {
		try {
			if (StringUtils.isBlank(model.getItem().getName())) {
				ajax(Status.error, "类别名称不能为空。");
				return;
			}
			// 排序
			Integer sort = model.getItem().getSort() == null ? 0 : model.getItem().getSort() ;
			model.getItem().setSort(sort);
			//rightService.doAddRight(parentId, name, sort,tip,fuUrl,location);
			rightService.add(model.getItem());
			JSONObject result = new JSONObject();
			result.put(STATUS_PARAMETER_NAME, "success");
			result.put("item", model.getItem());
			ajax(result);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "新增菜单时发生异常："+e.getMessage();
			logger.error(msg,e);
			ajax(Status.error,msg);
		}
	}
	
	
	/**
	 * 修改
	 */
	@Action(value="doUpdateRight")
	public void doUpdateRight() throws Exception {
		if (StringUtils.isBlank(model.getItem().getId())) {
			ajax(Status.error, "修改失败");
			return;
		}
		if (StringUtils.isBlank(model.getItem().getName())) {
			ajax(Status.error, "类别名称不能为空。");
			return;
		}
		// 排序
		Integer sort = model.getItem().getSort() == null ? 0 : model.getItem().getSort();
		rightService.doUpdateRight(model.getItem()); 
		ajax(Status.success, "修改成功。");
	}
	
	
	/**
	 * 删除
	 */
	@Action(value="doDelete")
	@SuppressWarnings("unchecked")
	public void doDelete() throws Exception {
		String id = model.getItem().getId();
		Right right = rightService.getRightById(id);
		String parentId = right.getParentId();
		// 删除标记位
		if(null != right && right.getDeleteFlag() != null && right.getDeleteFlag().intValue() == 1){
			ajax(Status.error,"该类别不可删除");
			return;
		}
		int del = rightService.delRight(right);
		if(del==1){
			ajax(Status.error,"删除失败");
		}
		ajax(Status.success);
	}
	
	@Override
	public RightModel getModel() {
		return model;
	}
	
	
}

