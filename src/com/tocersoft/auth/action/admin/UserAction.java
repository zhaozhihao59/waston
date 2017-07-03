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
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.auth.dto.UserCondition;
import com.tocersoft.auth.entity.Depart;
import com.tocersoft.auth.entity.Role;
import com.tocersoft.auth.entity.User;
import com.tocersoft.auth.model.UserModel;
import com.tocersoft.auth.service.IDepartService;
import com.tocersoft.auth.service.IRoleService;
import com.tocersoft.auth.service.IUserService;
import com.tocersoft.auth.service.impl.DepartServiceImpl;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;

@ParentPackage("admin")
@Namespace("/admin/auth/user")
@Controller
public class UserAction extends BaseAction implements ModelDriven<UserModel> {

	private static final long serialVersionUID = -8830542616503958456L;

	private Log logger = LogFactory.getLog(User.class);

	private UserModel model = new UserModel();

	@Resource
	private IUserService userService;

	@Resource
	private IRoleService roleService;
	@Resource
	private IDepartService departService;

	/**
	 * 用户管理
	 * 
	 * @return
	 */
	@Action(value = "index", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/auth/user_list.jsp") })
	public String home() {
		List<Role> roleList = roleService.getAllEnableRoles("");
		model.setRoleList(roleList);
		return INDEX;
	}

	/**
	 * 客户选择用户管理
	 * 
	 * @return
	 */
	@Action(value = "choose_user_index", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/auth/user_list.jsp") })
	public String index() {
		return INDEX;
	}

	/**
	 * 跳转到新增用户页面
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "to_add_user", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/auth/user_add_update.jsp") })
	public String addUser() {
		String departId = model.getItem().getDepartId();
		if (StringUtils.isNotBlank(departId)) {
			String[] departIds = departId.split(",");
			List<Depart> departList = new ArrayList<Depart>();
			for (String did : departIds) {
				Depart depart = departService.getDepartById(did);
				departList.add(depart);
			}
			JSONArray area1 = new JSONArray();
			for (Depart depart : departList) {
				JSONObject obj = new JSONObject();
				obj.put("id", depart.getId());
				obj.put("name", depart.getName());
				area1.add(obj);
			}
			model.setDepartJSON(area1.toString());
		}
		return INDEX;
	}

	/**
	 * 弹出选择角色页面
	 * 
	 * @return
	 */
	@Action(value = "choose_role", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/auth/user_add_choose_role.jsp") })
	public String chooseRole() {
		return SUCCESS;
	}

	/**
	 * 弹出选择部门页面
	 * 
	 * @return
	 */
	@Action(value = "chooseDepart", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/auth/user_add_choose_depart.jsp") })
	public String chooseDepart() {
		return INDEX;
	}
	
	/**
	 * 分页查询用户
	 * 
	 * @return
	 */
	@Action(value = "searchUserList")
	public String searchUserList() {
		try{
			UserCondition condition = model.getCondition();
			condition.setAdmin("admin");
			userService.getUserByPage(model.getPageResult(), condition);
			JSONObject root = WebUtils.toPageJson(model.getPageResult(),
					new String[] { "isManager", "id", "state", "account", "name",
							"staffId", "sex", "birthday", "mobile", "email",
							"roleNames", "departId" });
			return ajax(root);
		}catch(Exception e){
			e.printStackTrace();
			return ajax(Status.error);
		}
	}

	/**
	 * 分页查询用户包含管理员
	 * 
	 * @return
	 */
	@Action(value = "searchUserListAndAdm")
	public String searchUserListAndAdm() {
		UserCondition condition = model.getCondition();
		userService.getUserByPage(model.getPageResult(), condition);
		JSONObject root = WebUtils.toPageJson(model.getPageResult(),
				new String[] { "id", "state", "account", "name", "staffId",
						"sex", "birthday", "mobile", "email", "roleNames" });
		return ajax(root);
	}

	/**
	 * 自动补全控件模糊查询用户
	 * 
	 * @return
	 */
	@Action(value = "listUserByKeyAndDepartId")
	public String listUserByKeyAndDepartId() {
		UserCondition condition = model.getCondition();
		String departId = condition.getDepartId();

		List<User> list = new ArrayList<User>();
		if (StringUtils.isNotBlank(departId)) {
			list = userService.getAllUser(model.getCondition());
		}
		JSONObject result = WebUtils.toJsonResultList(list, new String[] {
				"id", "name", "account" });
		return super.ajax(result);

	}

	/**
	 * 保存操作员
	 * 
	 * @return
	 */
	@Action(value = "saveUser")
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "item.account", message = "账号不能为空!"),
			@RequiredStringValidator(fieldName = "item.password", message = "密码不能为空!") })
	@InputConfig(resultName = "ajaxError")
	public String saveUser() {
		try {
			userService.saveUser(model.getItem(), model.getRoleIds());
			return ajax(Status.success, "保存成功");
		} catch (Exception e) {
			String msg = "添加用户时发生异常：" + e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error, msg);
		}
	}

	/**
	 * 确认银行账号状态
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "updateBankStatus")
	public String updateBankStatus() {
		User item = model.getItem();
		userService.updateBankStatus(item.getId());
		JSONObject obj = new JSONObject();
		obj.put("success", "true");
		return ajax(obj);
	}

	/**
	 * 验证用户名是否存在
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "alreadyExists")
	public String alreadyExists() {
		String id = model.getItem().getId();
		String account = model.getItem().getAccount();
		JSONObject obj = new JSONObject();
		// 如果帐号没有修改 则不直切返回正确
		if (StringUtils.isNotBlank(id)) {
			User user = userService.getUserDetailById(id);
			if (user.getAccount().equals(account)) {
				obj.put("success", "true");
				return ajax(obj);
			}
		}
		boolean res = userService.alreadyExists(account);
		if (res) {
			obj.put("success", "true");
		} else {
			obj.put("success", "false");
		}
		return ajax(obj);
	}

	/**
	 * 修改操作员
	 * 
	 * @return
	 */
	@Action(value = "updateUser")
	@InputConfig(resultName = "ajaxError")
	public String updateUser() {
		try {
			userService.updateUserDetail(model.getItem(), model.getRoleIds());
			return ajax(Status.success, "保存成功");
		} catch (Exception e) {
			String msg = "修改用户时发生异常：" + e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error, msg);
		}
	}

	/**
	 * 判断账号是否存在
	 */
	@Action(value = "judgeAccountExist")
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "item.account", message = "账号不能为空!") })
	@InputConfig(resultName = "ajaxError")
	public String judgeAccountExist() {
		boolean result = false;
		if (StringUtils.isBlank(model.getItem().getId())) {
			result = userService
					.judgeAccountExist(model.getItem().getAccount());
		} else {
			result = userService.judgeAnotherAccountExist(model.getItem()
					.getAccount(), model.getItem().getId());
		}

		if (result) {
			return ajax(FALSE);
		} else {
			return ajax(TRUE);
		}
	}

	/**
	 * 选择角色
	 * 
	 * @return
	 */
	@Action(value = "chooseRoles", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/auth/user_choose_roles.jsp") })
	public String chooseRoles() {
		List<Role> roleList = roleService.getAllEnableRoles(model.getRoleIds());
		model.setRoleList(roleList);
		return INDEX;
	}
	
	


	/**
	 * 根据名称查询用户
	 * 
	 * @return
	 */
	@Action(value = "listUserCondition")
	@InputConfig(resultName = "ajaxError")
	public String listUserByCondition() {

		UserCondition condition = model.getCondition();
		List<User> list = userService.getAllUser(condition);
		JSONObject result = WebUtils.toJsonResultList(list, new String[] {
				"id", "name", "staffId" });
		return super.ajax(result);
	}

	/**
	 * 修改用户状态
	 */
	@Action(value = "updateState")
	@Validations(requiredFields = { @RequiredFieldValidator(fieldName = "item.id", message = "ID不能为空!") }

	)
	@InputConfig(resultName = "ajaxError")
	public String updateState() {
		try {
			userService.updateState(model.getItem());
			return ajax(Status.success, "操作成功");
		} catch (Exception e) {
			String msg = "操作时发生异常：" + e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error, msg);
		}
	}

	/**
	 * 重置密码
	 * 
	 * @return
	 */
	@Action(value = "resetPwd")
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "item.password", message = "密码不能为空!"), }, requiredFields = { @RequiredFieldValidator(fieldName = "item.id", message = "ID不能为空!") }

	)
	@InputConfig(resultName = "ajaxError")
	public String resetPwd() {
		try {
			User user = model.getItem();
			userService.resetPwd(user);
			return ajax(Status.success, "操作成功");
		} catch (Exception e) {
			String msg = "操作时发生异常：" + e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error, msg);
		}
	}

	/**
	 * 查看用户详细
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "viewUser", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/auth/user_add_update.jsp") })
	@Validations(requiredFields = { @RequiredFieldValidator(fieldName = "item.id", message = "ID不能为空!") })
	@InputConfig(resultName = "ajaxError")
	public String viewUser() {
		User user = userService.getUserDetailById(model.getItem().getId());
		model.setItem(user);
		// 将角色拼接成字符串 用于JSON 读取
		List<Role> roles = user.getRoles();
		JSONArray area = new JSONArray();
		for (Role role : roles) {
			JSONObject obj = new JSONObject();
			obj.put("id", role.getId());
			obj.put("name", role.getName());
			area.add(obj);
		}
		model.setRoleJSON(area.toString());

		String departId = user.getDepartId();
		if (StringUtils.isNotBlank(departId)) {
			String[] departIds = departId.split(",");
			List<Depart> departList = new ArrayList<Depart>();
			for (String did : departIds) {
				Depart depart = departService.getDepartById(did);
				departList.add(depart);
			}
			JSONArray area1 = new JSONArray();
			for (Depart depart : departList) {
				JSONObject obj = new JSONObject();
				obj.put("id", depart.getId());
				obj.put("name", depart.getName());
				area1.add(obj);
			}
			model.setDepartJSON(area1.toString());
		}
		return INDEX;
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	@Action(value = "delUserById")
	@Validations(requiredFields = { @RequiredFieldValidator(fieldName = "item.id", message = "ID不能为空!") })
	@InputConfig(resultName = "ajaxError")
	public String delUserById() {
		try {
			userService.delUserById(model.getItem().getId());
			return ajax(Status.success, "删除成功");
		} catch (Exception e) {
			String msg = "操作时发生异常：" + e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error, msg);
		}
	}

	/**
	 * 批量删除用户
	 * 
	 * @return
	 */
	@Action(value = "doBatchDelUser")
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "model.selIds", message = "ID不能为空!"), })
	@InputConfig(resultName = "ajaxError")
	public String doBatchDelUser() {
		try {
			userService.doBatchDelUser(model.getSelIds());
			return ajax(Status.success, "删除成功");
		} catch (Exception e) {
			String msg = "操作时发生异常：" + e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error, msg);
		}
	}

	@Override
	public UserModel getModel() {
		return model;
	}

	/**
	 * 批量设置用户主管
	 * 
	 * @return
	 */
	@Action(value = "doManagerContr")
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "model.selIds", message = "ID不能为空!"), })
	@InputConfig(resultName = "ajaxError")
	public String doManagerContr() {
		try {
			userService.doManagerContr(model.getSelIds(), model.getManagerId());
			return ajax(Status.success, "设置成功");
		} catch (Exception e) {
			String msg = "操作时发生异常：" + e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error, msg);
		}
	}

	/**
	 * 批量设置用户取消主管
	 * 
	 * @return
	 */
	@Action(value = "doManagerNoContr")
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "model.selIds", message = "ID不能为空!"), })
	@InputConfig(resultName = "ajaxError")
	public String doManagerNoContr() {
		try {
			userService.doManagerNoContr(model.getSelIds(),
					model.getManagerId());
			return ajax(Status.success, "设置成功");
		} catch (Exception e) {
			String msg = "操作时发生异常：" + e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error, msg);
		}
	}

	/**
	 * 批量导入用户
	 * 
	 * @return
	 */
	@Action(value = "doImport")
	public String doImport() {
		try {
			userService.doImport(model.getPath());
			return ajax(Status.success);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return ajax(Status.success, e.getMessage());
		}
	}

}
