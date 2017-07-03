package com.tocersoft.order.action.en.member;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.service.IMemberService;
import com.tocersoft.order.entity.OrderSell;
import com.tocersoft.order.entity.OrderSellItem;
import com.tocersoft.order.model.OrderSellModel;
import com.tocersoft.order.service.IOrderSellItemService;
import com.tocersoft.order.service.IOrderSellService;
import com.tocersoft.order.service.IPaypalService;
import com.tocersoft.product.entity.ExchangeRate;
import com.tocersoft.product.service.IExchangeRateService;

/**
 * 订单付款
 * @creator     zhangqiang
 * @email zhangqiang@tocersoft.com
 * @company www.tocersoft.com
 * @create-time Aug 15, 2014   10:08:49 AM
 * @version 1.0
 */
@ParentPackage("enMember")
@Namespace("/en/member/order/pay")
@Controller
public class OrderSellMemberPayEnAction extends BaseAction implements ModelDriven<OrderSellModel>{

	private static final long serialVersionUID = 5248437331106578648L;
	
	private Log logger = LogFactory.getLog(OrderSellMemberPayEnAction.class);
	
	private OrderSellModel model = new OrderSellModel();
	
	@Resource(name = "orderSellServiceImpl")
	private IOrderSellService orderSellService;
	@Resource(name = "orderSellItemServiceImpl")
	private IOrderSellItemService orderSellItemService;
	@Resource(name = "paypalServiceImpl")
	private IPaypalService paypalService;
	@Resource(name ="memberServiceImpl")
	private IMemberService memberService;
	@Resource(name = "exchangeRateServiceImpl")
	private IExchangeRateService exchangeRateService;
	
	//paypal支付调用地址
	@Value("${paypal.expresscheckout.invoke.url}")
	private String paypalExpresscheckoutInvokeUrl;
	
	/**
	 * 查看订单付款详细页面
	 * @return
	 */
	@Action(value = "detail", results = { @Result(name = "success", location = "/WEB-INF/pages/en/front/order/order_pay.jsp") })
	public String detail(){
		if(StringUtils.isBlank(model.getItem().getId())){
			return ERROR;
		}
		OrderSell orderSell = orderSellService.getOrderSellById(model.getItem().getId());
		List<OrderSellItem> orderSellItems = orderSellItemService.listOrderSellItemByOrderId(model.getItem().getId());
		if(orderSell.getOrderState() != null){
			String instantOrderState = orderSell.formatOrderState(orderSell.getOrderState());
			orderSell.setInstantOrderState(instantOrderState);
		}
		orderSell.setOrderSellItems(orderSellItems);
		model.setItem(orderSell);
		
		//查找汇率
		ExchangeRate exchangeRate = exchangeRateService.getExchangeRate();
		model.setExchangeRate(exchangeRate);
		
		return SUCCESS;
	}
	
	
	/**
	 * 进入paypal快速付款流程
	 * 说明：该方法执行paypal接口中的快速付款接口SetExpressCheckout方法
	 */
	@Action(value = "toSetPaypalExpressCheckout")
	@Validations(requiredStrings = {
				@RequiredStringValidator(fieldName = "item.id", message = "订单ID不能为空")
			}
	)
	@InputConfig(resultName = "error")
	public String toSetPaypalExpressCheckout(){
		logger.info("进入paypal快速付款流程，订单ID："+model.getItem().getId());
		//1.获取订单信息
		OrderSell orderSell = orderSellService.getOrderSellById(model.getItem().getId());
		if(null == orderSell){
			//订单不存在
			logger.warn("订单不存在，传入订单ID："+model.getItem().getId());
			return ERROR;
		}
		
		List<OrderSellItem> orderSellItemList = orderSellItemService.listOrderSellItemByOrderId(orderSell.getId());
		if(CollectionUtils.isEmpty(orderSellItemList)){
			//订单项不存在
			logger.warn("订单项不存在，传入订单ID："+model.getItem().getId());
			return ERROR;
		}
		
		//2.获取会员信息
		Member member = memberService.getMemberById(orderSell.getMemberId());
		if(null == member){
			//会员不存在
			logger.warn("会员不存在，传入订单ID："+model.getItem().getId());
			return ERROR;
		}
		
		//3.执行SetExpressCheckout方法
		String token = paypalService.doSetExpressCheckout(member,orderSell,orderSellItemList,getRequest());
		if(org.apache.commons.lang.StringUtils.isBlank(token)){
			return ERROR;
		}
		
		//token存入session中
		getSession().setAttribute(Constant.PAYPAL_TOKEN, token);
		getSession().setAttribute(Constant.CURRENT_PAY_ORDER_ID, orderSell.getId());
		
		//4.调用陈公公，进入PayPal支付网页
		String invokeUrl = paypalExpresscheckoutInvokeUrl + "?cmd=_express-checkout&token=" + token;
		try{
			getResponse().sendRedirect(invokeUrl);
		}catch(IOException ex){
			logger.error("跳转到PayPal支付页面发生异常："+ex.getMessage(),ex);
			return ERROR;
		}
		
		return NONE;
	}
	
	/**
	 * 用户在paypal上进行付款的回调方法
	 * 说明：该方法执行paypal接口中的快速付款接口DoExpressCheckoutPayment方法
	 * @return
	 */
	@Action(value = "doPaypalExpressCheckoutCallback", results = { @Result(name = "success", type="redirectAction",params={"namespace","/en/member/order/pay","actionName","pay_success"})})
	public String doPaypalExpressCheckoutCallback(){
		//1.获取货币类型
		String currencyCodeType = getRequest().getParameter("currencyCodeType");
		//2.用户ID
		String payerId = getRequest().getParameter("PayerID");
		//3.执行SetExpressCheckout方法获取到的token值
		String token = getRequest().getParameter("token");
		//4.比较Token
		String sessionToken = (String) getSession().getAttribute(Constant.PAYPAL_TOKEN);
		//5.当前支付订单
		String currentPayOrderId = (String) getSession().getAttribute(Constant.CURRENT_PAY_ORDER_ID);
		if(StringUtils.isBlank(currencyCodeType) 
				|| StringUtils.isBlank(payerId) 
				|| StringUtils.isBlank(token) 
				|| StringUtils.isBlank(sessionToken) 
				|| StringUtils.isBlank(currentPayOrderId)){
			logger.error("参数传入错误");
			return ERROR;
		}
		if(!sessionToken.equals(token)){
			logger.error("token不匹配");
			return ERROR;
		}
		
		OrderSell orderSell = orderSellService.getOrderSellById(currentPayOrderId);
		if(null == orderSell){
			//订单不存在
			logger.warn("订单不存在，传入订单ID："+model.getItem().getId());
			return ERROR;
		}
		
		List<OrderSellItem> orderSellItemList = orderSellItemService.listOrderSellItemByOrderId(orderSell.getId());
		if(CollectionUtils.isEmpty(orderSellItemList)){
			//订单项不存在
			logger.warn("订单项不存在，传入订单ID："+model.getItem().getId());
			return ERROR;
		}
		
		//最终付款
		boolean payResult = paypalService.doDoExpressCheckout(orderSell,orderSellItemList,token,payerId,currencyCodeType);
		if(!payResult){
			return ERROR;
		}
		
		//修改订单状态
		orderSellService.changeOrderState(orderSell.getId(),1);	//已付款待发货
		
		//清空session付款数据
		getSession().removeAttribute(Constant.PAYPAL_TOKEN);
		getSession().removeAttribute(Constant.CURRENT_PAY_ORDER_ID);
		
		return SUCCESS;
	}
	
	/**
	 * 付款成功
	 * @return
	 */
	@Action(value = "pay_success", results = { @Result(name = "success", location = "/WEB-INF/pages/en/front/order/pay_success.jsp") })
	public String paySuccess(){
		return SUCCESS;
	}

	@Override
	public OrderSellModel getModel() {
		return model;
	}

}
