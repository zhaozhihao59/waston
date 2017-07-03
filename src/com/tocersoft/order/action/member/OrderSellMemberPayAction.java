package com.tocersoft.order.action.member;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.alipay.util.AliPayment;
import com.alipay.util.CheckURL;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.tocersoft.base.action.BaseAction;
import com.tocersoft.base.utils.ChinabankMD5;
import com.tocersoft.base.utils.Constant;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.service.IMemberService;
import com.tocersoft.order.entity.OrderSell;
import com.tocersoft.order.entity.OrderSellItem;
import com.tocersoft.order.model.OrderSellModel;
import com.tocersoft.order.service.IChinabankService;
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
@ParentPackage("member")
@Namespace("/member/order/pay")
@Controller
public class OrderSellMemberPayAction extends BaseAction implements ModelDriven<OrderSellModel>{

	private static final long serialVersionUID = 5248437331106578648L;
	
	private Log logger = LogFactory.getLog(OrderSellMemberPayAction.class);
	
	private OrderSellModel model = new OrderSellModel();
	
	@Resource(name = "orderSellServiceImpl")
	private IOrderSellService orderSellService;
	@Resource(name = "orderSellItemServiceImpl")
	private IOrderSellItemService orderSellItemService;
	@Resource(name = "paypalServiceImpl")
	private IPaypalService paypalService;
	@Resource(name = "chinabankServiceImpl")
	private IChinabankService chinabankService;
	@Resource(name ="memberServiceImpl")
	private IMemberService memberService;
	@Resource(name = "exchangeRateServiceImpl")
	private IExchangeRateService exchangeRateService;
	
	//paypal支付调用地址
	@Value("${paypal.expresscheckout.invoke.url}")
	private String paypalExpresscheckoutInvokeUrl;
	
	//网银在线key
	@Value("${chinabank.pay.key}")
	private String chinabankPayKey;
	
	//支付宝参数
	@Value("${alipay.pay.url}")
	private String alipayUrl;
	@Value("${alipay.notify.url}")
	private String alipayNotifyUrl;
	@Value("${alipay.return.url}")
	private String alipayReturnUrl;
	/** 合作商户ID */
	@Value("${alipay.partnerid}")
	private String alipayPartnerId;
	/** 秘钥 */
	@Value("${alipay.key}")
	private String alipayKey;
	
	/**
	 * 查看订单付款详细页面
	 * @return
	 */
	@Action(value = "detail", results = { @Result(name = "success", location = "/WEB-INF/pages/front/order/order_pay.jsp") })
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
	 * 选择付款方式
	 * @return
	 */
	@Action(value = "toChoosePayType", results = { @Result(name = "success", location = "/WEB-INF/pages/front/order/order_choose_pay_type.jsp") })
	@Validations(requiredStrings = {
				@RequiredStringValidator(fieldName = "item.id", message = "订单ID不能为空")
			}
	)
	@InputConfig(resultName = "error")
	public String toChoosePayType(){
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
	@Action(value = "doPaypalExpressCheckoutCallback", results = { @Result(name = "success", type="redirectAction",params={"namespace","/member/order/pay","actionName","pay_success"})})
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
	 * 网银在线支付
	 */
	@Action(value = "toChinabankPay", results = { @Result(name = "success", location = "/WEB-INF/pages/front/order/order_chinabank_pay.jsp") })
	public String toChinabankPay(){
		logger.info("进入网银在线支付流程，订单ID："+model.getItem().getId());
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
		
		try{
			//3.拿到支付信息字符串
			String payInfoXmlStr = chinabankService.getPayInfoXmlStr(member,orderSell,orderSellItemList);
			//以Base64方式加密字符串
			BASE64Encoder encoder = new BASE64Encoder();
			String payInfoEncodeStr = encoder.encode(payInfoXmlStr.getBytes("GBK"));
			logger.warn("base64加密后的字符串：" + payInfoEncodeStr);
			//以支付明文信息+md5key方式完成MD5签名
			String payInfoSignStr = ChinabankMD5.md5(payInfoXmlStr, chinabankPayKey, "GBK", true);
			logger.warn("md5key签名后的字符串：" + payInfoSignStr);
			//传递到页面
			model.setPayInfoEncodeStr(payInfoEncodeStr);
			model.setPayInfoSignStr(payInfoSignStr);
			
		}catch(Exception ex){
			logger.error("获取支付字符串发生异常："+ex.getMessage(),ex);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 接收网银在线支付成功后的信息
	 */
	@Action(value = "chinabank_receive_pay_result")
	public void chinabank_receive_pay_result(){
		logger.warn("接收网银在线支付成功后的信息========>");
		
		//支付结果信息的Base64编码密文串。解密后明文为以 | 分割的字符串，字符集：GBK。每个位数含义参见返回报文明文串说明。
		String pres = getRequest().getParameter("PRes");
		logger.warn("PRes:" + pres);
		
		try{
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bs = decoder.decodeBuffer(pres);
			String respStr = new String(bs,"GBK");
			logger.warn("xmlInfoStr:" + respStr);
			
			String[] respArr = respStr.split("|");
			String orderNo = respArr[0];	//我方订单号
			String payResultCode = respArr[4];	//支付结果代码
			if("20".equals(payResultCode)){
				//支付成功
				OrderSell orderSell = orderSellService.getOrderSellByOrderNo(orderNo);
				//修改订单状态
				orderSellService.changeOrderState(orderSell.getId(),1);	//已付款待发货
				
				//清空session付款数据
				getSession().removeAttribute(Constant.PAYPAL_TOKEN);
				getSession().removeAttribute(Constant.CURRENT_PAY_ORDER_ID);
			}
			
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
	}
	
	/**
	 * 网银在线支付成功回调地址
	 */
	@Action(value = "chinabank_pay_success", results = { @Result(name = "success", location = "/WEB-INF/pages/front/order/pay_success.jsp") })
	public String chinabankPaySuccess(){
		logger.warn("进入网银在线支付成功回调地址========>");
		
		//支付结果信息的Base64编码密文串。解密后明文为以 | 分割的字符串，字符集：GBK。每个位数含义参见返回报文明文串说明。
		String pres = getRequest().getParameter("PRes");
		logger.warn("PRes:" + pres);
		
		try{
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bs = decoder.decodeBuffer(pres);
			String respStr = new String(bs,"GBK");
			logger.warn("xmlInfoStr:" + respStr);
			
			String[] respArr = respStr.split("\\|");
			String orderNo = respArr[0];	//我方订单号
			String payResultCode = respArr[4];	//支付结果代码
			if(!"20".equals(payResultCode)){
				return ERROR;
			}
			
			//支付成功
			OrderSell orderSell = orderSellService.getOrderSellByOrderNo(orderNo);
			//修改订单状态
			orderSellService.changeOrderState(orderSell.getId(),1);	//已付款待发货
			
			//清空session付款数据
			getSession().removeAttribute(Constant.PAYPAL_TOKEN);
			getSession().removeAttribute(Constant.CURRENT_PAY_ORDER_ID);
			
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 支付宝付款
	 * @return
	 */
	@Action(value = "toAlipay")
	public String toAlipay(){
		try{
			OrderSell orderSell = orderSellService.getOrderSellById(model.getItem().getId());
			List<OrderSellItem> orderSellItemList = orderSellItemService.listOrderSellItemByOrderId(orderSell.getId());
			
			DecimalFormat df = new DecimalFormat("0.##");
			
			String paygateway = alipayUrl;
			String service = "create_forex_trade";
			String sign_type = "MD5";
			String out_trade_no = orderSell.getOrderNo();
			String input_charset = "GBK";
			String partner = alipayPartnerId;
			String key = alipayKey;
			String body = orderSellItemList.get(0).getProductName();
			String total_fee = df.format(orderSell.getAmountTotal());
			String currency = "NZD";
			String subject = orderSellItemList.get(0).getProductName();
			String notify_url = alipayNotifyUrl;
			String return_url = alipayReturnUrl;
			
			String itemUrl=AliPayment.createPaymentUrl(paygateway,service,sign_type,out_trade_no,input_charset,partner,key,body,total_fee,currency,subject,notify_url,return_url);
			logger.warn("支付宝支付url："+itemUrl);
			
			getResponse().sendRedirect(itemUrl);
			return NONE;
			
		}catch(Exception ex){
			logger.error(ex.getMessage(),ex);
			return ERROR;
		}
	}
	
	/**
	 * 支付宝通知url
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action(value = "alipayNotify")
	public String alipayNotify(){
		logger.warn("进入支付宝通知url，传入参数：" + getRequest().getQueryString());
		
		String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?"
				+ "partner="
				+ alipayPartnerId
				+ "&notify_id="
				+ getRequest().getParameter("notify_id");

		//获取支付宝ATN返回结果，true是正确的订单信息，false 是无效的
		String responseTxt = CheckURL.check(alipayNotifyURL);

		Map params = new HashMap();
		//获得POST 过来参数设置到新的params中
		Map requestParams = getRequest().getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";				
			}
			params.put(name, valueStr);
		}
		
		String reqSign = getRequest().getParameter("sign");
		String mysign = com.alipay.util.SignatureHelper.sign(params, alipayKey);

		if (mysign.equals(reqSign) && responseTxt.equals("true")) {
			String orderNo = getRequest().getParameter("out_trade_no");	//订单号
			logger.warn("签名验证成功，订单号：" + orderNo);
			
			//支付成功
			OrderSell orderSell = orderSellService.getOrderSellByOrderNo(orderNo);
			//修改订单状态
			orderSellService.changeOrderState(orderSell.getId(),1);	//已付款待发货
			
			//清空session付款数据
			getSession().removeAttribute(Constant.PAYPAL_TOKEN);
			getSession().removeAttribute(Constant.CURRENT_PAY_ORDER_ID);
			
			return ajax("success");
		} else {
			logger.error("签名验证失败，计算后的签名值为：" + mysign + ",传递过来的签名为：" + reqSign);
			return ajax("fail");
		}
	}
	
	/**
	 * 支付宝支付成功
	 * @return
	 */
	@Action(value = "alipaySuccess", results = { @Result(name = "success", location = "/WEB-INF/pages/front/order/pay_success.jsp") })
	public String alipaySuccess(){
		logger.warn("进入支付宝支付成功回调url，参数：" + getRequest().getQueryString());
		//获取订单号
		String orderNo = getRequest().getParameter("out_trade_no");
		//获取状态
		String tradeStatus = getRequest().getParameter("trade_status");
		//获取货币
		String currency = getRequest().getParameter("currency");
		if(StringUtils.isNotBlank(orderNo) && "TRADE_FINISHED".equals(tradeStatus) && "NZD".equals(currency)){
			//获取金额
			String totalFee = getRequest().getParameter("total_fee");
			//根据订单号查询
			OrderSell orderSell = orderSellService.getOrderSellByOrderNo(orderNo);
			if(null != orderSell){
				//判断金额是否一致
				DecimalFormat df = new DecimalFormat("0.##");
				String orderFee = df.format(orderSell.getExchangeRateAmountTotal());
				if(orderFee.equals(totalFee)){
					return SUCCESS;
				}
			}
			
		}
		return ERROR;
	}
	
	/**
	 * 付款成功
	 * @return
	 */
	@Action(value = "pay_success", results = { @Result(name = "success", location = "/WEB-INF/pages/front/order/pay_success.jsp") })
	public String paySuccess(){
		return SUCCESS;
	}

	@Override
	public OrderSellModel getModel() {
		return model;
	}

}
