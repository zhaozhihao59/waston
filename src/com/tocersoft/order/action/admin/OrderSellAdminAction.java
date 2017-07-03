package com.tocersoft.order.action.admin;

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
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.WebUtils;
import com.tocersoft.order.entity.OrderSell;
import com.tocersoft.order.entity.OrderSellItem;
import com.tocersoft.order.model.OrderSellModel;
import com.tocersoft.order.service.IOrderSellItemService;
import com.tocersoft.order.service.IOrderSellService;


@ParentPackage("admin")
@Namespace("/admin/orderSell")
@Controller
public class OrderSellAdminAction extends BaseAction implements ModelDriven<OrderSellModel>{

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(OrderSellAdminAction.class);

	private OrderSellModel model = new OrderSellModel();

	@Resource(name = "orderSellServiceImpl")
	private IOrderSellService orderSellService;
	@Resource(name = "orderSellItemServiceImpl")
	private IOrderSellItemService orderSellItemService;



	/**
	 * 首页
	 * @return 
	 */
	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/order/order_sell_list.jsp") })
	public String index(){
		return SUCCESS;
	}

	/**
	 * 分页查询
	 * @return 
	 */
	@Action(value = "listOrderSellByPage")
	public String listOrderSellByPage(){
		orderSellService.listOrderSellByPage(model.getPageResult(),model.getCondition());
		JSONObject root = WebUtils.toPageJson(model.getPageResult(), new String[]{
			"id","orderNo","memberName","amountFreight","amountTotal","sendAddress","sendLinkman","sendContact","createDate","orderState"
		});
		return ajax(root);
	}
	
	/**
	 * 新增
	 * @return 
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/order/order_sell_add.jsp") })
	public String toAdd(){
		return SUCCESS;
	}

	/**
	 * 修改
	 * @return 
	 */
	@Action(value = "update", results = { @Result(name = "success", location = "/WEB-INF/pages/admin/order/order_sell_update.jsp") })
	public String toUpdate(){
		OrderSell item = orderSellService.getOrderSellById(model.getItem().getId());
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
			orderSellService.delByIds(model.getSelIds().split(","));
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
				orderSellService.add(model.getItem());
			}else{
				OrderSell orderSell = orderSellService.getOrderSellById(model.getItem().getId());
				orderSell.setSendLinkman(model.getItem().getSendLinkman());
				orderSell.setSendContact(model.getItem().getSendContact());
				orderSell.setOrderState(model.getItem().getOrderState());
				orderSell.setSendAddress(model.getItem().getSendAddress());
				orderSellService.update(orderSell);
			}
			return ajax(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajax(Status.error, msg);
		}
	}
	
	@Override
	public OrderSellModel getModel() {
		return model;
	}}

