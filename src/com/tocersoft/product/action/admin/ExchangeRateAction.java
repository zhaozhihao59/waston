package com.tocersoft.product.action.admin;

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
import com.tocersoft.product.entity.ExchangeRate;
import com.tocersoft.product.model.ExchangeRateModel;
import com.tocersoft.product.service.IExchangeRateService;


@ParentPackage("admin")
@Namespace("/admin/exchangeRate")
@Controller
public class ExchangeRateAction extends BaseAction implements ModelDriven<ExchangeRateModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(ExchangeRateAction.class);

	private ExchangeRateModel model = new ExchangeRateModel();

	@Resource(name = "exchangeRateServiceImpl")
	private IExchangeRateService exchangeRateService;



	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String index(){
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listExchangeRateByPage")
	public String listExchangeRateByPage(){
		exchangeRateService.listExchangeRateByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{});
		return ajax(root);
	}

	/** 跳转到汇率管理页面 */
	@Action(value = "toExchangeRate",results = {@Result(name = "success", location = "/WEB-INF/pages/admin/product/exchange_rate.jsp")})
	public String toExchangeRate(){
		exchangeRateService.listExchangeRateByPage(model.getPageResult(),model.getCondition());
		List<ExchangeRate> rates = model.getPageResult().getResult();
		if(rates != null && rates.size()>0){
			model.setItem(rates.get(0));
		}
		return SUCCESS;
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
		ExchangeRate item = exchangeRateService.getExchangeRateById(model.getItem().getId());
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
			exchangeRateService.delByIds(model.getSelIds().split(","));
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
				exchangeRateService.add(model.getItem());
			}else{
				exchangeRateService.update(model.getItem());
			}
			return ajax(Status.success,model.getItem().getId());
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public ExchangeRateModel getModel() {
		return model;
	}}

