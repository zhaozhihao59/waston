package com.tocersoft.professional.action.admin;

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
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.professional.entity.Professional;
import com.tocersoft.professional.model.ProfessionalModel;
import com.tocersoft.professional.service.IProfessionalService;
import com.tocersoft.system.entity.SysDictItem;
import com.tocersoft.system.service.ISysDictItemService;



@ParentPackage("admin")
@Namespace("/admin/professional")
@Controller
public class ProfessionalAdminAction extends BaseAction implements ModelDriven<ProfessionalModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(ProfessionalAdminAction.class);

	private ProfessionalModel model = new ProfessionalModel();

	@Resource(name = "professionalServiceImpl")
	private IProfessionalService professionalService;
	@Resource(name = "sysDictItemServiceImpl")
	private ISysDictItemService sysdictitemservice;

	/**
	 * 首页
	 * @return
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/professional/professional_list.jsp") })
	public String index(){
		//头衔【中文】
		List<SysDictItem> qualificationList = sysdictitemservice.listSysDictItemByDictId("ccf76628fdf311e4b16400155d008ea1");
		model.setQualificationList(qualificationList);
		//工作语言
		List<SysDictItem> languageList = sysdictitemservice.listSysDictItemByDictId("97487dc0fdf311e4b16400155d008ea1");
		model.setLanguageList(languageList);
		//业务【中文】
		List<SysDictItem> businessList = sysdictitemservice.listSysDictItemByDictId("b9168131fdf311e4b16400155d008ea1");
		model.setBusinessList(businessList);
		//头衔【英文】
		List<SysDictItem> qualificationListEn = sysdictitemservice.listSysDictItemByDictId("d3583cbdfdf311e4b16400155d008ea1");
		model.setQualificationListEn(qualificationListEn);
		//工作语言【英文】
		List<SysDictItem> languageListEn = sysdictitemservice.listSysDictItemByDictId("a3a7022afdf311e4b16400155d008ea1");
		model.setLanguageListEn(languageListEn);
		//业务【英文】
		List<SysDictItem> businessListEn = sysdictitemservice.listSysDictItemByDictId("be018f0dfdf311e4b16400155d008ea1");
		model.setBusinessListEn(businessListEn);
		//头衔【日文】
		List<SysDictItem> qualificationListJp = sysdictitemservice.listSysDictItemByDictId("d7e4a2befdf311e4b16400155d008ea1");
		model.setQualificationListJp(qualificationListJp);
		//工作语言【日文】
		List<SysDictItem> languageListJp = sysdictitemservice.listSysDictItemByDictId("a7a36062fdf311e4b16400155d008ea1");
		model.setLanguageListJp(languageListJp);
		//业务【日文】
		List<SysDictItem> businessListJp = sysdictitemservice.listSysDictItemByDictId("c1ee82d8fdf311e4b16400155d008ea1");
		model.setBusinessListJp(businessListJp);
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listProfessionalByPage")
	public String listProfessionalByPage(){
		professionalService.listProfessionalByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{"id","name","desc","email","qualification","language","type","qualificationName","languageName"});
		return ajax(root);
	}

	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/professional/professional_add.jsp") })
	public String toAdd(){
		//头衔【中文】
		List<SysDictItem> qualificationList = sysdictitemservice.listSysDictItemByDictId("ccf76628fdf311e4b16400155d008ea1");
		model.setQualificationList(qualificationList);
		//工作语言
		List<SysDictItem> languageList = sysdictitemservice.listSysDictItemByDictId("97487dc0fdf311e4b16400155d008ea1");
		model.setLanguageList(languageList);
		//业务【中文】
		List<SysDictItem> businessList = sysdictitemservice.listSysDictItemByDictId("b9168131fdf311e4b16400155d008ea1");
		model.setBusinessList(businessList);
		//头衔【英文】
		List<SysDictItem> qualificationListEn = sysdictitemservice.listSysDictItemByDictId("d3583cbdfdf311e4b16400155d008ea1");
		model.setQualificationListEn(qualificationListEn);
		//工作语言【英文】
		List<SysDictItem> languageListEn = sysdictitemservice.listSysDictItemByDictId("a3a7022afdf311e4b16400155d008ea1");
		model.setLanguageListEn(languageListEn);
		//业务【英文】
		List<SysDictItem> businessListEn = sysdictitemservice.listSysDictItemByDictId("be018f0dfdf311e4b16400155d008ea1");
		model.setBusinessListEn(businessListEn);
		//头衔【日文】
		List<SysDictItem> qualificationListJp = sysdictitemservice.listSysDictItemByDictId("d7e4a2befdf311e4b16400155d008ea1");
		model.setQualificationListJp(qualificationListJp);
		//工作语言【日文】
		List<SysDictItem> languageListJp = sysdictitemservice.listSysDictItemByDictId("a7a36062fdf311e4b16400155d008ea1");
		model.setLanguageListJp(languageListJp);
		//业务【日文】
		List<SysDictItem> businessListJp = sysdictitemservice.listSysDictItemByDictId("c1ee82d8fdf311e4b16400155d008ea1");
		model.setBusinessListJp(businessListJp);
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/professional/professional_add.jsp") })
	public String toUpdate(){
		//头衔【中文】
		List<SysDictItem> qualificationList = sysdictitemservice.listSysDictItemByDictId("ccf76628fdf311e4b16400155d008ea1");
		model.setQualificationList(qualificationList);
		//工作语言
		List<SysDictItem> languageList = sysdictitemservice.listSysDictItemByDictId("97487dc0fdf311e4b16400155d008ea1");
		model.setLanguageList(languageList);
		//业务【中文】
		List<SysDictItem> businessList = sysdictitemservice.listSysDictItemByDictId("b9168131fdf311e4b16400155d008ea1");
		model.setBusinessList(businessList);
		//头衔【英文】
		List<SysDictItem> qualificationListEn = sysdictitemservice.listSysDictItemByDictId("d3583cbdfdf311e4b16400155d008ea1");
		model.setQualificationListEn(qualificationListEn);
		//工作语言【英文】
		List<SysDictItem> languageListEn = sysdictitemservice.listSysDictItemByDictId("a3a7022afdf311e4b16400155d008ea1");
		model.setLanguageListEn(languageListEn);
		//业务【英文】
		List<SysDictItem> businessListEn = sysdictitemservice.listSysDictItemByDictId("be018f0dfdf311e4b16400155d008ea1");
		model.setBusinessListEn(businessListEn);
		//头衔【日文】
		List<SysDictItem> qualificationListJp = sysdictitemservice.listSysDictItemByDictId("d7e4a2befdf311e4b16400155d008ea1");
		model.setQualificationListJp(qualificationListJp);
		//工作语言【日文】
		List<SysDictItem> languageListJp = sysdictitemservice.listSysDictItemByDictId("a7a36062fdf311e4b16400155d008ea1");
		model.setLanguageListJp(languageListJp);
		//业务【日文】
		List<SysDictItem> businessListJp = sysdictitemservice.listSysDictItemByDictId("c1ee82d8fdf311e4b16400155d008ea1");
		model.setBusinessListJp(businessListJp);
		Professional item = professionalService.getProfessionalById(model.getItem().getId());
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
			professionalService.delByIds(model.getSelIds().split(","));
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
			if (StringUtils.isBlank(model.getItem().getId())) {
				professionalService.add(model.getItem());
			} else {
				professionalService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public ProfessionalModel getModel() {
		return model;
	}}

