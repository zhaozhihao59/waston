package com.tocersoft.base.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

@ParentPackage("admin")
@Namespace("/")
@Controller
public class AdminLoginPageAction {

	@Action(value = "toLogin", results = { @Result(name = "login", location = "/WEB-INF/pages/admin/login_page.jsp") })
	public String toLogin() {
		return "login";
	}
	
}
