package com.tocersoft.system.action.admin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.system.entity.SysDict;
import com.tocersoft.system.entity.SysDictItem;
import com.tocersoft.system.model.SysDictModel;
import com.tocersoft.system.service.ISysDictItemService;
import com.tocersoft.system.service.ISysDictService;

/**
 * 数据库字典管理
 * @author      miaoshuai
 * @email       miaoshuai@tocersfot.com
 * @company		www.tocersoft.com
 * @create-time 2014-1-5 下午02:29:27
 * @version     1.0
 */
@ParentPackage("admin")
@Namespace("/admin/sys/dict")
@Controller("sysDictAction")
public class SysDictAction extends BaseAction implements ModelDriven<SysDictModel> {
	private static final long serialVersionUID = -732810202014378023L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private SysDictModel model = new SysDictModel();
	@Resource(name = "sysDictServiceImpl")
	private ISysDictService sysDictService;
	@Resource(name = "sysDictItemServiceImpl")
	private ISysDictItemService sysDictItemService;
	
	/**
	 * 数据库字典首页
	 * @return
	 */
	@Action(value = "index",results = {@Result(name = "index",location = "/WEB-INF/pages/admin/sys/sys_dict_list.jsp")})
	@InputConfig(resultName = "error")
	public String index(){
		List<SysDict> sysDictList = sysDictService.listAll();
		model.setSysDictList(sysDictList);
		return INDEX;
	}
	
	/**
	 * 跳转新增数据页面
	 * @return
	 */
	@Action(value = "toAdd",results = {@Result(name = "success",location = "/WEB-INF/pages/admin/sys/sys_dict_add.jsp")})
	public String toAdd(){
		return SUCCESS;
	}
	
	/**
	 * 跳转新增数据页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "doAdd")
	public String doAdd(){
		SysDict item = model.getItem();
		sysDictService.doSave(item);
		
		List<SysDict> dicts = sysDictService.listAll();
		JSONArray array = new JSONArray();
		for(SysDict dict : dicts){
			JSONObject obj = new JSONObject();
			obj.put("id", dict.getId());
			obj.put("name", dict.getName());
			if(dict.getId().equals(item.getId())){
				obj.put("select", "true");
			}
			array.add(obj);
		}
		
		JSONObject result = new JSONObject();
		result.put("status", "success");
		result.put("options", array);
		
		return ajax(result);
	}
	
	/**
	 * 跳转新增数据页面
	 * @return
	 */
	@Action(value = "toUpdate",results = {@Result(name = "success",location = "/WEB-INF/pages/admin/sys/sys_dict_add.jsp")})
	public String toUpdate(){
		String id = model.getId();
		SysDict dict = sysDictService.getSysDictById(id);
		model.setItem(dict);
		return SUCCESS;
	}
	
	/**
	 * 更新数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "doUpdate")
	public String doUpdate(){
		
		sysDictService.doUpdate(model.getItem());
		
		List<SysDict> dicts = sysDictService.listAll();
		JSONArray array = new JSONArray();
		for(SysDict dict : dicts){
			JSONObject obj = new JSONObject();
			obj.put("id", dict.getId());
			obj.put("name", dict.getName());
			array.add(obj);
		}
		
		JSONObject result = new JSONObject();
		result.put("status", "success");
		result.put("options", array);
		
		return ajax(result);
	}
	
	/**
	 * 删除
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "del")
	public String del(){
		
		sysDictService.delBatch(new String[]{model.getSelIds()});
		
		List<SysDict> dicts = sysDictService.listAll();
		JSONArray array = new JSONArray();
		for(SysDict dict : dicts){
			JSONObject obj = new JSONObject();
			obj.put("id", dict.getId());
			obj.put("name", dict.getName());
			array.add(obj);
		}
		
		JSONObject result = new JSONObject();
		result.put("status", "success");
		result.put("options", array);
		
		return ajax(result);
	}
	
	/**
	 * 数据库字典明细
	 * @return
	 */
	@Action(value = "list")
	@Validations(requiredStrings = {@RequiredStringValidator(fieldName = "id",message = "数据库字典ID不存在")})
	@InputConfig(resultName = "ajaxError")
	public String list(){
		List<SysDictItem> sysDictItemList = sysDictItemService.listSysDictItemByDictId(model.getId());
		JSONObject result = WebUtils.toJsonResultList(sysDictItemList, new String []{
			"id","name","sort"
		});
		return super.ajax(result);
	}
	
	/**
	 * 跳转编辑数据库字典
	 * @return
	 */
	@Action(value = "toEditSysDictItem",results = {@Result(name = "success",location = "/WEB-INF/pages/admin/sys/sys_dict_item_add.jsp")})
	@InputConfig(resultName = "error")
	public String toEditSysDictItem(){
		if(StringUtils.isNotBlank(model.getId())){
			SysDict item = sysDictService.getSysDictById(model.getId());
			model.setItem(item);
		}
		return SUCCESS;
	}
	
	/**
	 * 编辑数据库字典
	 * @return
	 */
	@Action(value = "doEditSysDictItem")
	@InputConfig(resultName = "ajaxError")
	public String doEditSysDictItem(){
		try {
			sysDictItemService.editSysDictItem(model.getSysDictItem(), super.getCurrentUser());
			return super.ajax(Status.success);
		} catch (Exception e) {
			String message = "编辑数据库字典时发生异常:"+e.getMessage();
			e.printStackTrace();
			logger.error(message, e);
			return super.ajax(Status.error,message);
		}
	}
	
	/**
	 * 批量删除数据库字典项
	 * @return
	 */
	@Action(value = "batchDelete")
	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "selIds", message = "数据库字典项编号不存在") })
	@InputConfig(resultName = "ajaxError")
	public String batchDelete(){
		try {
			String [] ids = StringUtils.split(model.getSelIds(),",");
			sysDictItemService.batchDelete(ids);
			return super.ajax(Status.success);
		} catch (Exception e) {
			String message = "批量删除数据库字典项时发生异常：" + e.getMessage();
			e.printStackTrace();
			logger.error(message,e);
			return super.ajax(Status.error, message);
		}
	}
	
	@Override
	public SysDictModel getModel() {
		return model;
	}
}
