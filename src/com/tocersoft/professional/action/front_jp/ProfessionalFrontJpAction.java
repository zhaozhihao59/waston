package com.tocersoft.professional.action.front_jp;
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
import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.professional.dto.ProfessionalCondition;
import com.tocersoft.professional.entity.Professional;
import com.tocersoft.professional.model.ProfessionalModel;
import com.tocersoft.professional.service.IProfessionalService;
import com.tocersoft.system.entity.SysDictItem;
import com.tocersoft.system.service.ISysDictItemService;


@ParentPackage("front")
@Namespace("/front/professional_jp")
@Controller
public class ProfessionalFrontJpAction extends BaseAction implements ModelDriven<ProfessionalModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(ProfessionalFrontJpAction.class);

	private ProfessionalModel model = new ProfessionalModel();

	@Resource(name = "professionalServiceImpl")
	private IProfessionalService professionalService;
	@Resource
	private ISysDictItemService sysDictItemService;


	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/front_jp/professional/professional.jsp") })
	public String index(){
		// 数据词典查询准
		List<SysDictItem> sysDictItemList = sysDictItemService.listSysDictItemByDictId("c1ee82d8fdf311e4b16400155d008ea1");
		model.setSysDictItemList(sysDictItemList);
		// 分页查询
		ProfessionalCondition condition = model.getCondition();
		//合伙人和高级顾问
		if(condition.getType() != null){
		if(condition.getType() == 0 || condition.getType() == 1|| condition.getType() == 2|| condition.getType() == 3|| condition.getType() == 4|| condition.getType() == 5|| condition.getType() == 6){
			getSession().setAttribute("typeSessionJp", condition.getType());
			getSession().setAttribute("qualificationSessionJp", null);
			//选择大类时清空业务类型
			getSession().setAttribute("businessSessionJp", null);
		}
		}else{
			if(getSession().getAttribute("typeSessionJp") != null){
				condition.setType((Integer)getSession().getAttribute("typeSessionJp"));
			}
		}
		if(condition.getQualificationJp() != null){
			getSession().setAttribute("qualificationSessionJp", condition.getQualificationJp());
			getSession().setAttribute("typeSessionJp", null);
			//选择大类时清空业务类型
			getSession().setAttribute("businessSessionJp", null);
		}else{
			if(getSession().getAttribute("qualificationSessionJp") != null){
				condition.setQualificationJp((String)getSession().getAttribute("qualificationSessionJp"));
			}
		}
		//业务类型
		if(condition.getBusinessJp() != null){
			getSession().setAttribute("businessSessionJp", condition.getBusinessJp());
		}else{
			if(getSession().getAttribute("businessSessionJp") != null){
				condition.setBusinessJp((String)getSession().getAttribute("businessSessionJp"));
			}
		}
		PageResult<Professional> pageResult = model.getPageResult();
		pageResult.setPageSize(8);
		pageResult.setCurrentPage(model.getPage());
		professionalService.listProfessionalByPage(pageResult, condition);
		for (Professional pro : pageResult.getResult()) {
			if(pro.getQualificationJp() != null){
					String name = "";
					String[] ids = pro.getQualificationJp().split(",");
					if(ids.length > 0){
						for (int i = 0; i < ids.length; i++) {
							SysDictItem dictItem = sysDictItemService.getSysDictItemById(ids[i].trim());
							if(dictItem != null){
								name += dictItem.getName() + ",";
							}
						}
						if(pro.getQualification() !=""){
							pro.setQualification(name.substring(0, name.length()-1));
						}
					}
				}
		}
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listProfessionalByPage")
	public String listProfessionalByPage(){
		professionalService.listProfessionalByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{});
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
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String toUpdate(){
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
			if(StringUtils.isBlank(model.getItem().getId())){
				professionalService.add(model.getItem());
			}else{
				professionalService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	
	/**
	 * 专业人员
	 */
	@Action(value = "toProfessionalDetail_jp", results = { @Result(name = "success", location = "/WEB-INF/pages/front_jp/professional/professional_detail.jsp") })
	public String toProfessionalDetail() {
		Professional pro =professionalService.getProfessionalById(model.getSelIds());
		String[] languageId = null;
		String[] qualificationId = null;
		String[] businessId = null;
		if(pro.getLanguage() != null){
			languageId = pro.getLanguageJp().split(",");
		}
		 qualificationId = null;
		if(pro.getQualification() != null){
			qualificationId = pro.getQualificationJp().split(",");
		}
		 businessId = null;
		if(pro.getBusiness() != null){
		 businessId = pro.getBusinessJp().split(",");
		}
		SysDictItem dictItem = null;
		String languageStr ="";
		String qualificationStr ="";
		String businessStr ="";
		if(languageId != null){
		for(int i=0 ;i <languageId.length ;i++){
			dictItem = sysDictItemService.getSysDictItemById(languageId[i].trim());
			languageStr=languageStr+dictItem.getName()+",";
		}
		if(languageStr !=""){
			languageStr= languageStr.substring(0, languageStr.length()-1);
		}
		pro.setLanguageName(languageStr);
		}
		/*业务**/
		if(businessId != null){
		for(int i=0 ;i <businessId.length ;i++){
			dictItem = sysDictItemService.getSysDictItemById(businessId[i].trim());
			businessStr=businessStr+dictItem.getName()+",";
		}
		if(qualificationStr !=""){
			businessStr= businessStr.substring(0, businessStr.length()-1);
		}
		pro.setBusiness(businessStr);
		}
		/*头衔**/
		if(qualificationId != null){
		for(int i=0 ;i <qualificationId.length ;i++){
			dictItem = sysDictItemService.getSysDictItemById(qualificationId[i].trim());
			if(dictItem != null){
			qualificationStr=qualificationStr+dictItem.getName()+",";
			}
		}
		if(qualificationStr !=""){
			qualificationStr= qualificationStr.substring(0, qualificationStr.length()-1);
		}
		pro.setQualification(qualificationStr);
		}
		if(pro.getType().contains("0")&&pro.getType().contains("1")){
			pro.setTypeName("合伙人,高级顾问");
		}else if(pro.getType().contains("0")){
			pro.setTypeName("合伙人");
		}else if(pro.getType().contains("1")){
			pro.setTypeName("高级顾问");
		}
		model.setItem(pro);
		return SUCCESS;
	}


	/**
	 * 分页显示上一页专业人员详情
	 */
	@Action(value = "pre_page", results = { @Result(name = "success", location = "/WEB-INF/pages/front_jp/professional/professional_detail.jsp") })
	public String pre_page() {
		try {
			
			int sort = 0;
			List<Professional> professionlist = null;
			Professional pro = null;
			if(model.getItem().getSort() != null){
				sort =model.getItem().getSort()-1;
				if(model.getItem().getSort() <= 1){
					model.getItem().setSort(1);
				}
				 professionlist =  professionalService.listProfessionalSort(String.valueOf(sort));	
				if(professionlist == null){
				 professionlist =  professionalService.listProfessionalSort(model.getItem().getSort().toString());
				}else{
					if(professionlist.size() ==0){
						professionlist =  professionalService.listProfessionalSort(model.getItem().getSort().toString());
					}else{
					model.getItem().setSort(sort-1);
					}
					}
					 pro = professionlist.get(0);
			}else{
				 pro =professionalService.getProfessionalById(model.getSelIds());
			}
			
			String[] languageId = null;
			if(pro.getLanguage() != null){
				languageId = pro.getLanguage().split(",");
			}
			String[] qualificationId = null;
			if(pro.getQualificationJp() != null){
				qualificationId = pro.getQualificationJp().split(",");
			}
			String[] businessId = null;
			if(pro.getBusiness() != null){
			 businessId = pro.getBusiness().split(",");
			}
			SysDictItem dictItem = null;
			String languageStr ="";
			String qualificationStr ="";
			String businessStr ="";
			if(languageId != null){
			for(int i=0 ;i <languageId.length ;i++){
				dictItem = sysDictItemService.getSysDictItemById(languageId[i].trim());
				languageStr=languageStr+dictItem.getName()+",";
			}
			if(languageStr !=""){
				languageStr= languageStr.substring(0, languageStr.length()-1);
			}
			pro.setLanguageName(languageStr);
			}
			/*业务**/
			if(businessId != null){
			for(int i=0 ;i <businessId.length ;i++){
				dictItem = sysDictItemService.getSysDictItemById(businessId[i].trim());
				businessStr=businessStr+dictItem.getName()+",";
			}
			if(qualificationStr !=""){
				businessStr= businessStr.substring(0, businessStr.length()-1);
			}
			pro.setBusiness(businessStr);
			}
			/*头衔**/
			if(qualificationId != null){
			for(int i=0 ;i <qualificationId.length ;i++){
				dictItem = sysDictItemService.getSysDictItemById(qualificationId[i].trim());
				qualificationStr=qualificationStr+dictItem.getName()+",";
			}
			if(qualificationStr !=""){
				qualificationStr= qualificationStr.substring(0, qualificationStr.length()-1);
			}
			pro.setQualification(qualificationStr);
			}
			if(pro.getType().contains("0")&&pro.getType().contains("1")){
				pro.setTypeName("合伙人,高级顾问");
			}else if(pro.getType().contains("0")){
				pro.setTypeName("合伙人");
			}else if(pro.getType().contains("1")){
				pro.setTypeName("高级顾问");
			}
			model.setItem(pro);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 分页显示上一页专业人员详情
	 */
	@Action(value = "next_page", results = { @Result(name = "success", location = "/WEB-INF/pages/front_jp/professional/professional_detail.jsp") })
	public String next_page() {
		try {
			
			int sort = 0;
			List<Professional> professionlist = null;
			Professional pro = null;
			if(model.getItem().getSort() != null){
				sort =model.getItem().getSort()+1;
				if(model.getItem().getSort() <= 1){
					model.getItem().setSort(1);
				}
				 professionlist =  professionalService.listProfessionalSort(String.valueOf(sort));	
				if(professionlist == null){
				 professionlist =  professionalService.listProfessionalSort(model.getItem().getSort().toString());
				}else{
					if(professionlist.size() ==0){
						professionlist =  professionalService.listProfessionalSort(model.getItem().getSort().toString());
					}else{
					model.getItem().setSort(sort+1);
					}
					}
					 pro = professionlist.get(0);
			}else{
				 pro =professionalService.getProfessionalById(model.getSelIds());
			}
			
			String[] languageId = null;
			if(pro.getLanguage() != null){
				languageId = pro.getLanguage().split(",");
			}
			String[] qualificationId = null;
			if(pro.getQualification() != null){
				qualificationId = pro.getQualification().split(",");
			}
			String[] businessId = null;
			if(pro.getBusiness() != null){
			 businessId = pro.getBusiness().split(",");
			}
			SysDictItem dictItem = null;
			String languageStr ="";
			String qualificationStr ="";
			String businessStr ="";
			if(languageId != null){
			for(int i=0 ;i <languageId.length ;i++){
				dictItem = sysDictItemService.getSysDictItemById(languageId[i].trim());
				languageStr=languageStr+dictItem.getName()+",";
			}
			if(languageStr !=""){
				languageStr= languageStr.substring(0, languageStr.length()-1);
			}
			pro.setLanguageName(languageStr);
			}
			/*业务**/
			if(businessId != null){
			for(int i=0 ;i <businessId.length ;i++){
				dictItem = sysDictItemService.getSysDictItemById(businessId[i].trim());
				businessStr=businessStr+dictItem.getName()+",";
			}
			if(qualificationStr !=""){
				businessStr= businessStr.substring(0, businessStr.length()-1);
			}
			pro.setBusiness(businessStr);
			}
			/*头衔**/
			if(qualificationId != null){
			for(int i=0 ;i <qualificationId.length ;i++){
				dictItem = sysDictItemService.getSysDictItemById(qualificationId[i].trim());
				qualificationStr=qualificationStr+dictItem.getName()+",";
			}
			if(qualificationStr !=""){
				qualificationStr= qualificationStr.substring(0, qualificationStr.length()-1);
			}
			pro.setQualification(qualificationStr);
			}
			String typeName="";
			if(pro.getType().indexOf("0")>=0){
				typeName="合伙人";
				if(pro.getType().lastIndexOf("0")+1<pro.getType().length()){
					typeName="合伙人，";
				}
			}
			if(pro.getType().indexOf("1")>=0){
				typeName=typeName+"高级顾问";
				if(pro.getType().lastIndexOf("1")+1<pro.getType().length()){
					typeName=typeName+"高级顾问，";
				}
			}
			if(pro.getType().indexOf("2")>=0){
				typeName=typeName+"专利代理人";
				if(pro.getType().lastIndexOf("2")+1<pro.getType().length()){
					typeName=typeName+"专利代理人，";
				}
			}
			if(pro.getType().indexOf("3")>=0){
				typeName=typeName+"律师";
				}
			model.setItem(pro);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


	@Override
	public ProfessionalModel getModel() {
		return model;
	}}

