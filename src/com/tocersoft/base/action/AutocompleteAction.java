package com.tocersoft.base.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

/**
 * 自动填充后端类
 * @author      miaoshuai
 * @email       miaoshuai@tocersfot.com
 * @company		www.tocersoft.com
 * @create-time 2013-12-29 上午10:12:00
 * @version     1.0
 */
@ParentPackage("admin")
@Namespace("/autocomplete")
@Controller("autocompleteAction")
public class AutocompleteAction extends BaseAction {
	private static final long serialVersionUID = -6243836344067336946L;
	/** 查询键值 */
	private String key;
	
//	@Resource(name = "employeeServiceImpl")
//	private IEmployeeService employeeService;
	
//	/**
//	 * 根据键值查询员工
//	 * @return
//	 */
//	@Action(value = "listEmployeeByKey")
//	@InputConfig(resultName = "ajaxError")
//	public String listEmployeeByKey(){
//		List<Employee> list = employeeService.listEmployeeByName(key);
//		JSONObject result = WebUtils.toJsonResultList(list, new String[]
//		{
//			"id","name","departmentId","identity"
//		});
//		return super.ajax(result);
//	}
	
//	/**
//	 * 根据键值查询项目
//	 * @return
//	 */
//	@Action(value = "listProjectByKey")
//	@InputConfig(resultName = "ajaxError")
//	public String listProjectByKey(){
////		List<Project> list = projectService.listProjectByName(key);
////		JSONObject result = WebUtils.toJsonResultList(list, new String[]
////		{
////			"id","projectName"
////		});
////		return super.ajax(result);
//	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
