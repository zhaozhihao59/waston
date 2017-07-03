package com.tocersoft.auth.action.admin;

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

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.auth.entity.Role;
import com.tocersoft.auth.entity.User;
import com.tocersoft.auth.model.UserModel;
import com.tocersoft.auth.service.IUserService;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.DataUtil;

@ParentPackage("admin")
@Namespace("/admin/user/account")
@Controller
public class UserAccountAction extends BaseAction implements ModelDriven<UserModel>{

	private static final long serialVersionUID = -3602322963148214270L;
	
	private UserModel model = new UserModel();
	
	@Resource(name="userServiceImpl")
	private IUserService userService;
	
	@SuppressWarnings("unchecked")
	@Action(value = "detail", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/auth/account_update.jsp") })
	public String home() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User current = userService.getUserDetailById(user.getId());
		model.setItem(current);
		// 将角色拼接成字符串 用于JSON 读取
		List<Role> roles = current.getRoles();
		JSONArray area = new JSONArray ();
		for(Role role:roles){
			JSONObject obj = new JSONObject();
			obj.put("id", role.getId());
			obj.put("name", role.getName());
			area.add(obj);
		}
		model.setRoleJSON(area.toString());
		return SUCCESS;
	}
	
	/**
	 * 判断当前密码是否正确
	 */
	@Action(value = "judgeCurrentPwd")
	public void judgeCurrentPwd(){
		String oldPwd = getRequest().getParameter("oldPwd");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User current = userService.getUserDetailById(user.getId());
		boolean result = false;
		if(current.getPassword().equals(DataUtil.encryptionPw(oldPwd))){
			result = true;
		}
		
		this.ajax(result ? "true" : "false");
	}
	
	/**
	 * 保存当前用户
	 * @return
	 */
	@Action(value = "saveCurrentUser")
	public void saveCurrentOperator(){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User current = userService.getUserDetailById(user.getId());
		User clientUser = model.getItem();
		current.setStaffId(clientUser.getStaffId());
		current.setBirthday(clientUser.getBirthday());
		current.setContact(clientUser.getContact());
		current.setEmail(clientUser.getEmail());
		current.setMobile(clientUser.getMobile());
		current.setName(clientUser.getName());
		current.setSex(clientUser.getSex());
		current.setTel(clientUser.getTel());
		userService.updateUser(current);
		this.ajax("true");
	}
	
	/**
	 * 修改密码
	 */
	@Action(value = "changeCurrentPwd")
	public void changeCurrentPwd(){
		String newPwd = getRequest().getParameter("newPwd");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User current = userService.getUserDetailById(user.getId());
		current.setPassword(newPwd);
		userService.resetPwd(current);
		this.ajax("true");
	}
	
	@Override
	public UserModel getModel() {
		return model;
	}
}	
