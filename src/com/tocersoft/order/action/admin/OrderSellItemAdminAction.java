package com.tocersoft.order.action.admin;

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
import com.tocersoft.order.model.OrderSellItemModel;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.tocersoft.order.entity.OrderSellItem;
import com.tocersoft.order.service.IOrderSellItemService;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.base.action.BaseAction;


@ParentPackage("admin")
@Namespace("/admin/orderSellItem")
@Controller
public class OrderSellItemAdminAction extends BaseAction implements ModelDriven<OrderSellItemModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(OrderSellItemAdminAction.class);

	private OrderSellItemModel model = new OrderSellItemModel();

	@Resource(name = "orderSellItemServiceImpl")
	private IOrderSellItemService orderSellItemService;



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
	@Action(value = "listOrderSellItemByPage")
	public String listOrderSellItemByPage(){
		orderSellItemService.listOrderSellItemByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","productId","productName","productPhoto","unitPrice","num","sumPrice"
		});
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
	 * 修改订单项产品单价
	 * @return
	 */
	@Action(value = "updatePrice")
	public String updatePrice(){
		double amountTotal = orderSellItemService.updatePrice(model.getItem());
		return ajax(Status.success,amountTotal+"");
	}
	
	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/") })
	public String toUpdate(){
		OrderSellItem item = orderSellItemService.getOrderSellItemById(model.getItem().getId());
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
			orderSellItemService.delByIds(model.getSelIds().split(","));
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
				orderSellItemService.add(model.getItem());
			}else{
				orderSellItemService.update(model.getItem());
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}


	@Override
	public OrderSellItemModel getModel() {
		return model;
	}}

