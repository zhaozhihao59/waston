package com.tocersoft.auth.action.admin;

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
import com.tocersoft.auth.entity.Right;
import com.tocersoft.auth.entity.Role;
import com.tocersoft.auth.model.RoleModel;
import com.tocersoft.auth.service.IRightService;
import com.tocersoft.auth.service.IRoleService;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;

@ParentPackage("admin")
@Namespace("/admin/auth/role")
@Controller
public class RoleAction extends BaseAction implements ModelDriven<RoleModel>{

	private static final long serialVersionUID = 4770643302141631L;
	
	private Log logger = LogFactory.getLog(RoleAction.class);
	
	@Resource
	private IRoleService roleService;
	
	@Resource
	private IRightService rightService;
	
	private RoleModel model = new RoleModel();
	
	/**
	 * 跳转到角色权限列表页面
	 * @return
	 */
	@Action(value = "index", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/auth/role_list.jsp") })
	public String home(){
		return INDEX;
	}
	
	/**
	 * 分页查询角色
	 * @return
	 */
	@Action(value = "listRoles")
	public String searchRoleList() {
		roleService.getRoleListByPage(model.getPageResult());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","name","roleType","remark","users"});
		return ajax(root);
	}
	
	/**
	 * 跳转到新增角色页面
	 * @return
	 */
	@Action(value = "to_add_role", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/auth/role_add_update.jsp") })
	public String toAddRole() {
		List<Right> rights = rightService.listRightByPid("0", 1);
		model.setRights(rights);
		return SUCCESS;
	}
	
	@Action(value = "to_update_role", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/auth/role_add_update.jsp") })
	public String toUpdateRole(){
		String id=model.getItem().getId();
		if(StringUtils.isBlank(id)){
			return ERROR;
		}
		Role role = roleService.getById(id);
		model.setItem(role);
		List<Right> rights = rightService.listRightByPid("0", 1);
		model.setRights(rights);
		return SUCCESS;
	}
	
	/**
	 * 判断角色是否存在
	 */
	@Action(value = "judgeNameExist")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "item.name", message = "角色名不能为空!")
			}
	)
	
	@InputConfig(resultName = "ajaxError")
	public String judgeNameExist(){
		boolean result = false;
		if(StringUtils.isBlank(model.getItem().getId())){
			result = roleService.judgeNameExist(model.getItem().getName());
		}else{
			result = roleService.judgeAnotherNameExist(model.getItem().getName(),model.getItem().getId());
		}
		if(result){
			return ajax(FALSE);
		}else{
			return ajax(TRUE);
		}
	}
	
	/**
	 * 保存
	 */
	@Action(value = "doSave")
	@Validations(
		requiredStrings = {
				@RequiredStringValidator(fieldName = "item.name", message = "角色名字不能为空!")
		}
	)
	@InputConfig(resultName = "ajaxError")
	public String doSave(){
		try {
			roleService.saveRole(model.getItem());
			String roleId=model.getItem().getId();
			String permissionIds=model.getPermissionIds();
			if(StringUtils.isNotBlank(permissionIds)){
				roleService.saveRolePermissions(roleId,permissionIds);
			}
			return ajax(Status.success,roleId);
		} catch (Exception e) {
			String msg = "操作时发生异常：" + e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error, msg);
		}
	}
	
	/**
	 * 删除角色
	 */
	@Action(value = "delRole")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "item.id", message = "ID不能为空!")
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String delRole(){
		try {
			roleService.delRoleById(model.getItem().getId());
			return ajax(Status.success,"删除成功");
		} catch (Exception e) {
			String msg = "操作时发生异常：" + e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error, msg);
		}
	}
	
	/**
	 * 保存角色权限
	 * @return
	 */
	@Action(value = "saveRolePermissions")
	@Validations(
			requiredStrings = {
					@RequiredStringValidator(fieldName = "roleId", message = "ID不能为空!"),
					@RequiredStringValidator(fieldName = "permissionIds", message = "permissionIds不能为空!")
			}
	)
	@InputConfig(resultName = "ajaxError")
	public String saveRolePermissions(){
		try {
			roleService.saveRolePermissions(model.getRoleId(),model.getPermissionIds());
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "操作时发生异常：" + e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error, msg);
		}
	}
	@Override
	public RoleModel getModel() {
		return model;
	}
}
