package com.tocersoft.base.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.tocersoft.auth.entity.Right;
import com.tocersoft.auth.entity.User;
import com.tocersoft.auth.service.IRightService;
import com.tocersoft.base.utils.Constant;

@ParentPackage("admin")
@Namespace("/admin")
@Controller
public class AdminHomeAction extends BaseAction {
	private static final long serialVersionUID = 3351785033210042535L;

	@Resource
	private IRightService permissionService;

	@Action(value = "index", results = { @Result(name = "index", location = "/WEB-INF/pages/admin/base/index.jsp") })
	public String home() {
		return INDEX;
	}

	@Action(value = "welcome", results = { @Result(name = "welcome", location = "/WEB-INF/pages/admin/workbench/welcome.jsp") })
	public String welcome() {
		return WELCOME;
	}

	@Action(value = "login", results = { @Result(name = "login", location = "/WEB-INF/pages/admin/login/login.jsp") })
	public String login() {
		return "login";
	}

	@Action(value = "logout", results = { @Result(name = "success", type = "redirectAction", params = {
			"namespace", "/admin", "actionName", "login" }) })
	public String logout() {
		removeSession(Constant.CURRENT_OPERATOR);
		return SUCCESS;
	}


	@SuppressWarnings("unchecked")
	@Action(value = "loadAdminRights")
	public String loadAdminRights() {
		String permissionJsonString = null;
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		List<Right> permissions = new ArrayList<Right>();
		/*
		 * admin 拥有最大权限数据
		 */
		if (user.getAccount().equals(Constant.ADMIN_USER)) {
			permissions = permissionService.getAllEnablePermissions();
		} else {
			permissions = permissionService
					.getAllEnablePermissionsByUserId(user.getId());
		}

		JSONArray root = new JSONArray();
		// 构建权限
		for (int i = 0; i < permissions.size(); i++) {
			Right permission = permissions.get(i);
			if (permission.getLocation() == 1) { // 1级
				JSONObject first = new JSONObject();
				first.put("id", permission.getId());
				first.put("text", permission.getName());
				first.put("url", permission.getUrl());
				first.put("location", permission.getLocation());
				first.put("iconPath", permission.getIconPath());

				this.buildRights(first, permission, permissions);

				root.add(first);
			}

		}

		permissionJsonString = root.toJSONString();

		return ajax(permissionJsonString);
	}

	/**
	 * 构建
	 * 
	 * @param first
	 * @param permission
	 */
	@SuppressWarnings("unchecked")
	private void buildRights(JSONObject po, Right parent,
			List<Right> permissions) {
		JSONArray children = new JSONArray();
		po.put("children", children);
		for (int i = 0; i < permissions.size(); i++) {
			Right permission = permissions.get(i);
			if (null != permission.getParentId()
					&& permission.getParentId().equals(parent.getId())) {
				JSONObject node = new JSONObject();
				node.put("id", permission.getId());
				node.put("pcode", permission.getPcode());
				node.put("text", permission.getName());
				node.put("url", permission.getUrl());
				node.put("location", permission.getLocation());
				node.put("iconPath", permission.getIconPath());

				this.buildRights(node, permission, permissions);
				children.add(node);

			}
		}
	}
}
