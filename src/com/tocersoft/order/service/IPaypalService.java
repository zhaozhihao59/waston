package com.tocersoft.order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tocersoft.member.entity.Member;
import com.tocersoft.order.entity.OrderSell;
import com.tocersoft.order.entity.OrderSellItem;

/**
 * paypal付款方法，与paypal对接使用该service
 * @creator     zhangqiang
 * @email zhangqiang@tocersoft.com
 * @company www.tocersoft.com
 * @create-time Aug 15, 2014   10:24:13 AM
 * @version 1.0
 */
public interface IPaypalService {

	/**
	 * 执行paypal快速付款接口SetExpressCheckout
	 * 文档地址：https://developer.paypal.com/webapps/developer/docs/classic/api/merchant/SetExpressCheckout_API_Operation_SOAP/
	 * @param member 付款会员
	 * @param orderSell	订单
	 * @param orderSellItemList 订单项列表
	 * @param request	请求对象
	 * @return 返回调用成功后的token值
	 */
	String doSetExpressCheckout(Member member,OrderSell orderSell,List<OrderSellItem> orderSellItemList, HttpServletRequest request);

	/**
	 * 执行paypal快速付款接口doExpressCheckout
	 * DoExpressCheckoutPayment文档地址：https://developer.paypal.com/webapps/developer/docs/classic/api/merchant/DoExpressCheckoutPayment_API_Operation_SOAP/
	 * @param orderSell 订单
	 * @param orderSellItemList  订单项列表
	 * @param token token
	 * @param payerId 付款方
	 * @param currencyCodeType 货币类型
	 * @return 支付是否成功
	 */
	boolean doDoExpressCheckout(OrderSell orderSell,List<OrderSellItem> orderSellItemList,String token,String payerId,String currencyCodeType);

}
