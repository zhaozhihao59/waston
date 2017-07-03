package com.tocersoft.member.action.admin;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.tocersoft.member.model.MemLevelModel;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.tocersoft.member.entity.MemLevel;
import com.tocersoft.member.service.IMemLevelService;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.base.action.BaseAction;


@ParentPackage("admin")
@Namespace("/admin/member/level")
@Controller
public class MemLevelAdminAction extends BaseAction implements ModelDriven<MemLevelModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(MemLevelAdminAction.class);

	private MemLevelModel model = new MemLevelModel();

	@Resource(name = "memLevelServiceImpl")
	private IMemLevelService memLevelService;



	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/member/mem_level_index.jsp") })
	public String index(){
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listMemLevelByPage")
	public String listMemLevelByPage(){
		memLevelService.listMemLevelByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","levelNum","levelName","levelNote"});
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
			memLevelService.delByIds(model.getSelIds().split(","));
			return ajax(Status.success,"删除成功");
		} catch (Exception e) {
			String msg = "删除时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}

	

	/**
	 * 删除用户 zyd 2014年4月13日18:52:50
	 * @return
	 */
	@Action(value = "delMemberById")
	@Validations(
		requiredFields = {
				@RequiredFieldValidator(fieldName = "item.id", message = "ID不能为空!")
		}
	)
	@InputConfig(resultName = "ajaxError")
	public String delMemberById(){
		try {
			memLevelService.delMemberById(model.getItem().getId());
			return ajax(Status.success,"删除成功");
		} catch (Exception e) {
			String msg = "操作时发生异常：" + e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error, msg);
		}
	}
	/**
	 * 批量删除用户  zyd2014年4月13日18:52:57
	 * @return
	 */
	@Action(value = "doBatchDelMember")
	@Validations(
		requiredStrings = {
				@RequiredStringValidator(fieldName = "selIds", message = "ID不能为空!")
		}
	)
	@InputConfig(resultName = "ajaxError")
	public String doBatchDelUser(){
		try {
			memLevelService.doBatchDelMember(model.getSelIds());
			return ajax(Status.success,"删除成功");
		} catch (Exception e) {
			String msg = "操作时发生异常：" + e.getMessage();
			logger.error(msg);
			e.printStackTrace();
			return ajax(Status.error, msg);
		}
	}
	
 
	/**mem_level_add.jsp
	 * 新增
	 * @return 
	 */
	@Action(value = "addMemberLev", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/member/mem_level_add.jsp") })
	public String addMemberLev(){
		return SUCCESS;
	}
	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "updateMemberLev", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/member/mem_level_add.jsp") })
	public String updateMemberLev(){
		MemLevel item = memLevelService.getMemLevelById(model.getItem().getId());
		model.setItem(item);
 		return SUCCESS;
	}

	
//	/**mem_level_add.jsp
//	 *修改
//	 * @return 
//	 */
//	@Action(value = "updateMemberLev", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/mem/mem_level_add.jsp") })
//	public String updateMemberLev(){
//		MemLevel item = memLevelService.getMemLevelById(model.getItem().getId());
//		model.setItem(item);
//		return SUCCESS;
//	}
	/**
	 * 保存
	 * @return 
	 */
	@Action(value = "doSave" )
	public String doSave(){
		try {
			//1.验证会员等级
			boolean result = memLevelService.checkMemLevelNumExists(model.getItem());
			if(result){
				return ajax(Status.error,"会员等级已经存在");
			}
			
			//2.保存
			if(StringUtils.isBlank(model.getItem().getId())){
				memLevelService.add(model.getItem());
			}else{
				MemLevel newmolever=new MemLevel();
				newmolever.setLevelName(model.getItem().getLevelName());
				newmolever.setLevelNote(model.getItem().getLevelNote());
				newmolever.setLevelNum(model.getItem().getLevelNum());
				newmolever.setCreateBy(model.getItem().getCreateBy());
				newmolever.setCreateDate(model.getItem().getCreateDate());
				newmolever.setDeleteFlag(model.getItem().getDeleteFlag());
				newmolever.setCreateBy(model.getItem().getCreateBy());
				newmolever.setId(model.getItem().getId());
				memLevelService.update(newmolever);
			}
			return ajax(Status.success,"保存成功");

		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}

	/**
	 * 检查会员等级是否存在
	 * @return
	 */
	@Action(value = "checkMemLevelNumExists")
	@Validations(
		requiredFields = {
				@RequiredFieldValidator(fieldName = "item.levelNum", message = "等级序号不能为空!")
		}
	)
	@InputConfig(resultName = "ajaxError")
	public String checkMemLevelNumExists(){
		try{
			boolean result = memLevelService.checkMemLevelNumExists(model.getItem());
			if(!result){
				//不存在
				return ajax(Status.success);
			}else{
				return ajax(Status.error,"等级序号已经存在");
			}
		}catch (Exception e) {
			String msg = "检查会员等级时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, e.getMessage());
		}
	}
	
	
	
	@Override
	public MemLevelModel getModel() {
		return model;
	}}

