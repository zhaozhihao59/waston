package com.tocersoft.order.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.AddressType;
import urn.ebay.apis.eBLBaseComponents.CountryCodeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.ErrorType;
import urn.ebay.apis.eBLBaseComponents.ItemCategoryType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentInfoType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

import com.tocersoft.member.entity.Member;
import com.tocersoft.order.entity.OrderSell;
import com.tocersoft.order.entity.OrderSellItem;
import com.tocersoft.order.service.IPaypalService;

@Service("paypalServiceImpl")
@Transactional(value = "transactionManager")
public class PaypalServiceImpl implements IPaypalService{
	
	private Log logger = LogFactory.getLog(PaypalServiceImpl.class);
	
	//paypal快速付款接口传入的货币代码
	@Value("${paypal.expresscheckout.currency.code}")
	private String paypalExpresscheckoutCurrencyCode;
	//paypal支付接口用户名
	@Value("${paypal.acct.username}")
	private String paypalAcctUserName;
	//paypal支付接口密码
	@Value("${paypal.acct.password}")
	private String paypalAcctPassword;
	//paypal支付接口签名
	@Value("${paypal.acct.signature}")
	private String paypalAcctSignature;
	//paypal接口模式：sandbox/live
	@Value("${paypal.mode}")
	private String paypalMode;

	/**
	 * 执行paypal快速付款接口SetExpressCheckout
	 * 文档地址：https://developer.paypal.com/webapps/developer/docs/classic/api/merchant/SetExpressCheckout_API_Operation_SOAP/
	 * @param orderSell	订单
	 * @param orderSellItemList 订单项列表
	 * @param request	请求对象
	 * param exchangeRate 汇率对象
	 */
	@Override
	public String doSetExpressCheckout(Member member,OrderSell orderSell,List<OrderSellItem> orderSellItemList, HttpServletRequest request) {
		DecimalFormat df = new DecimalFormat("#.00");	//格式化2位小数
		String returnToken = null;	//调用接口返回的token值
		
		StringBuffer url = new StringBuffer();
		url.append("http://");
		url.append(request.getServerName());
		url.append(":");
		url.append(request.getServerPort());
		url.append(request.getContextPath());
		
		String logoURL = url.toString() + "/static/front/comm/images/nzuz-logo.png";	//logo地址
		String returnURL = url.toString() + "/member/order/pay/doPaypalExpressCheckoutCallback.htm";	//进入付款执行的GetExpressCheckout方法的回调方法
		String cancelURL = url.toString() + "/member/account/my_order.htm";	//取消付款返回到我的订单页面
		

		Map<String,String> configurationMap =  new HashMap<String,String>();
		configurationMap.put("acct1.UserName", paypalAcctUserName);
		configurationMap.put("acct1.Password", paypalAcctPassword);
		configurationMap.put("acct1.Signature", paypalAcctSignature);
		// Endpoints are varied depending on whether sandbox OR live is chosen for mode
		configurationMap.put("mode", paypalMode);
		
		try{
			
			// Creating service wrapper object to make an API call by loading configuration map.
			PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);
			
			SetExpressCheckoutRequestType setExpressCheckoutReq = new SetExpressCheckoutRequestType();
			SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();
			
			
			/*
			 *  (Required) URL to which the buyer's browser is returned after choosing 
			 *  to pay with PayPal. For digital goods, you must add JavaScript to this 
			 *  page to close the in-context experience.
			  Note:
				PayPal recommends that the value be the final review page on which the buyer 
				confirms the order and payment or billing agreement.
				Character length and limitations: 2048 single-byte characters
			 */
			//设置币种
			//paypal快速付款支持币种类型列表：https://developer.paypal.com/webapps/developer/docs/classic/api/currency_codes/#paypal
			details.setReturnURL(returnURL + "?currencyCodeType=" + paypalExpresscheckoutCurrencyCode);
			details.setCancelURL(cancelURL);
			/*
			 *  (Optional) Email address of the buyer as entered during checkout.
			 *  PayPal uses this value to pre-fill the PayPal membership sign-up portion on the PayPal pages.
			 *	Character length and limitations: 127 single-byte alphanumeric characters
			 */
			//会员邮箱地址
			details.setBuyerEmail(member.getEmail());
			
			//商品详细列表
			List<PaymentDetailsItemType> lineItems = new ArrayList<PaymentDetailsItemType>();
			//循环遍历订单项
			for(OrderSellItem curOrderSellItem : orderSellItemList){
				PaymentDetailsItemType item = new PaymentDetailsItemType();
				BasicAmountType amt = new BasicAmountType();
				//PayPal uses 3-character ISO-4217 codes for specifying currencies in fields and variables. 
				amt.setCurrencyID(CurrencyCodeType.fromValue(paypalExpresscheckoutCurrencyCode));
				amt.setValue(df.format(curOrderSellItem.getExchangeRateUnitPrice()));		//汇率转换后的单价
				item.setQuantity((int)curOrderSellItem.getNum());	//商品数量
				item.setName(curOrderSellItem.getProductName());	//产品名称
				item.setAmount(amt);	//商品单价
				/*
				 * Indicates whether an item is digital or physical. For digital goods, this field is required and must be set to Digital. It is one of the following values:
				 	1.Digital 虚拟商品
					2.Physical 实体商品
				   This field is available since version 65.1. 
				 */
				item.setItemCategory(ItemCategoryType.fromValue("Physical"));	
				/*
				 *  (Optional) Item description.
					Character length and limitations: 127 single-byte characters
					This field is introduced in version 53.0. 
				 */
				//产品描述
				item.setDescription("");
				
				//加入集合
				lineItems.add(item);
			}

			
			
			/*
			 * (Optional) Item sales tax.
				Note: You must set the currencyID attribute to one of 
				the 3-character currency codes for any of the supported PayPal currencies.
				Character length and limitations: Value is a positive number which cannot exceed $10,000 USD in any currency.
				It includes no currency symbol. It must have 2 decimal places, the decimal separator must be a period (.), 
				and the optional thousands separator must be a comma (,).
			if (request.getParameter("salesTax") != "") {
				item.setTax(new BasicAmountType(CurrencyCodeType
						.fromValue(request.getParameter("currencyCode")),
						request.getParameter("salesTax")));					
			} */
			
			List<PaymentDetailsType> payDetails = new ArrayList<PaymentDetailsType>();
			PaymentDetailsType paydtl = new PaymentDetailsType();
			/*
			 * How you want to obtain payment. When implementing parallel payments, 
			 * this field is required and must be set to Order.
			 *  When implementing digital goods, this field is required and must be set to Sale.
			 *   If the transaction does not include a one-time purchase, this field is ignored. 
			 *   It is one of the following values:

				Sale � This is a final sale for which you are requesting payment (default).
				Authorization � This payment is a basic authorization subject to settlement with PayPal Authorization and Capture.
				Order � This payment is an order authorization subject to settlement with PayPal Authorization and Capture.
			
			paydtl.setPaymentAction(PaymentActionCodeType.fromValue(request.getParameter("paymentType")));
			 */
			/*
			 *  (Optional) Total shipping costs for this order.
				Note:
				You must set the currencyID attribute to one of the 3-character currency codes 
				for any of the supported PayPal currencies.
				Character length and limitations: 
				Value is a positive number which cannot exceed $10,000 USD in any currency.
				It includes no currency symbol. 
				It must have 2 decimal places, the decimal separator must be a period (.), 
				and the optional thousands separator must be a comma (,)
			 */
			BasicAmountType shippingTotal = new BasicAmountType();
			shippingTotal.setValue(df.format(orderSell.getExchangeRateAmountFreight()));	//运费总额
			shippingTotal.setCurrencyID(CurrencyCodeType.fromValue(paypalExpresscheckoutCurrencyCode));	//币种
			paydtl.setShippingTotal(shippingTotal);
			
			/*
			 * 	保险费
			 *  (Optional) Total shipping insurance costs for this order. 
			 *  The value must be a non-negative currency amount or null if you offer insurance options.
				 Note:
				 You must set the currencyID attribute to one of the 3-character currency 
				 codes for any of the supported PayPal currencies.
				 Character length and limitations: 
				 Value is a positive number which cannot exceed $10,000 USD in any currency. 
				 It includes no currency symbol. It must have 2 decimal places,
				 the decimal separator must be a period (.), 
				 and the optional thousands separator must be a comma (,).
				 InsuranceTotal is available since version 53.0.
			
			if (request.getParameter("insuranceTotal") != "") {
				paydtl.setInsuranceTotal(new BasicAmountType(
						CurrencyCodeType.fromValue(request
								.getParameter("currencyCode")), request
								.getParameter("insuranceTotal")));
				paydtl.setInsuranceOptionOffered("true");
				orderTotal += Double.parseDouble(request
						.getParameter("insuranceTotal"));
			}
			 */
			/*
			 * 	手续费
			 *  (Optional) Total handling costs for this order.
				 Note:
				 You must set the currencyID attribute to one of the 3-character currency codes 
				 for any of the supported PayPal currencies.
				 Character length and limitations: Value is a positive number which 
				 cannot exceed $10,000 USD in any currency.
				 It includes no currency symbol. It must have 2 decimal places, 
				 the decimal separator must be a period (.), and the optional 
				 thousands separator must be a comma (,). 
			 
			if (request.getParameter("handlingTotal") != "") {
				paydtl.setHandlingTotal(new BasicAmountType(
						CurrencyCodeType.fromValue(request
								.getParameter("currencyCode")), request
								.getParameter("handlingTotal")));
				orderTotal += Double.parseDouble(request
						.getParameter("handlingTotal"));
			}
			*/
			
			/*
			 * 	税费
			 *  (Optional) Sum of tax for all items in this order.
				 Note:
				 You must set the currencyID attribute to one of the 3-character currency codes
				 for any of the supported PayPal currencies.
				 Character length and limitations: Value is a positive number which 
				 cannot exceed $10,000 USD in any currency. It includes no currency symbol.
				 It must have 2 decimal places, the decimal separator must be a period (.),
				 and the optional thousands separator must be a comma (,).
			
			if (request.getParameter("taxTotal") != "") {
				paydtl.setTaxTotal(new BasicAmountType(CurrencyCodeType
						.fromValue(request.getParameter("currencyCode")),
						request.getParameter("taxTotal")));
				orderTotal += Double.parseDouble(request
						.getParameter("taxTotal"));
			}
			 */
			/*
			 *  (Optional) Description of items the buyer is purchasing.
				 Note:
				 The value you specify is available only if the transaction includes a purchase.
				 This field is ignored if you set up a billing agreement for a recurring payment 
				 that is not immediately charged.
				 Character length and limitations: 127 single-byte alphanumeric characters
			
			if (request.getParameter("orderDescription") != "") {
				paydtl.setOrderDescription(request.getParameter("orderDescription"));
			}
			 */
			
			BasicAmountType itemsTotal = new BasicAmountType();
			itemsTotal.setValue(df.format(orderSell.getExchangeRateAmountProduct()));	//汇率转换后的产品总额
			//PayPal uses 3-character ISO-4217 codes for specifying currencies in fields and variables. 
			itemsTotal.setCurrencyID(CurrencyCodeType.fromValue(paypalExpresscheckoutCurrencyCode));
			paydtl.setOrderTotal(new BasicAmountType(CurrencyCodeType.fromValue(paypalExpresscheckoutCurrencyCode),df.format(orderSell.getExchangeRateAmountTotal())));	//汇率转换后的订单总额
			paydtl.setPaymentDetailsItem(lineItems);

			paydtl.setItemTotal(itemsTotal);
			/*
			 *  (Optional) Your URL for receiving Instant Payment Notification (IPN) 
			 *  about this transaction. If you do not specify this value in the request, 
			 *  the notification URL from your Merchant Profile is used, if one exists.
				Important:
				The notify URL applies only to DoExpressCheckoutPayment. 
				This value is ignored when set in SetExpressCheckout or GetExpressCheckoutDetails.
				Character length and limitations: 2,048 single-byte alphanumeric characters
			paydtl.setNotifyURL(request.getParameter("notifyURL"));
			*/
			AddressType shipToAddress=new AddressType();
			/*
			 * Person's name associated with this shipping address.
			 *  It is required if using a shipping address.
			   Character length and limitations: 32 single-byte characters
			 */
			shipToAddress.setName(orderSell.getSendLinkman());
			/*
			 * First street address. It is required if using a shipping address.
			   Character length and limitations: 100 single-byte characters
			 */
			shipToAddress.setStreet1(orderSell.getSendAddress());
			/*
			 *  (Optional) Second street address.
				Character length and limitations: 100 single-byte characters
			 */
			shipToAddress.setStreet2("");
			/*
			 * Name of city. It is required if using a shipping address.
			   Character length and limitations: 40 single-byte characters
			 */
			shipToAddress.setCityName(orderSell.getSendCity());
			/*
			 * State or province. It is required if using a shipping address.
			   Character length and limitations: 40 single-byte characters
			 */
			shipToAddress.setStateOrProvince(orderSell.getSendProvince());
			/*
			 * U.S. ZIP code or other country-specific postal code. 
			 * It is required if using a U.S. shipping address and may be
			 *  required for other countries.
			   Character length and limitations: 20 single-byte characters
			 */
			shipToAddress.setPostalCode("200000");
			/*
			 * Country code. It is required if using a shipping address.
			   Character length and limitations: 2 single-byte characters
			 */
			shipToAddress.setCountry(CountryCodeType.fromValue(orderSell.getSendCountryCode()));	//送货国家
			paydtl.setShipToAddress(shipToAddress);
			payDetails.add(paydtl);
			details.setPaymentDetails(payDetails);
			
//			if (request.getParameter("billingAgreementText") != "") {
//				/*
//				 *  (Required) Type of billing agreement. For recurring payments,
//				 *   this field must be set to RecurringPayments. 
//				 *   In this case, you can specify up to ten billing agreements. 
//				 *   Other defined values are not valid.
//					 Type of billing agreement for reference transactions. 
//					 You must have permission from PayPal to use this field. 
//					 This field must be set to one of the following values:
//						1. MerchantInitiatedBilling - PayPal creates a billing agreement 
//						   for each transaction associated with buyer.You must specify 
//						   version 54.0 or higher to use this option.
//						2. MerchantInitiatedBillingSingleAgreement - PayPal creates a 
//						   single billing agreement for all transactions associated with buyer.
//						   Use this value unless you need per-transaction billing agreements. 
//						   You must specify version 58.0 or higher to use this option.
	//
//				 */
//				BillingAgreementDetailsType billingAgreement = new BillingAgreementDetailsType(
//						BillingCodeType.fromValue(request.getParameter("billingType")));
//				/*
//				 * Description of goods or services associated with the billing agreement. 
//				 * This field is required for each recurring payment billing agreement.
//				 *  PayPal recommends that the description contain a brief summary of 
//				 *  the billing agreement terms and conditions. For example,
//				 *   buyer is billed at "9.99 per month for 2 years".
//				   Character length and limitations: 127 single-byte alphanumeric characters
//				 */
//				billingAgreement.setBillingAgreementDescription(request.getParameter("billingAgreementText"));
//				List<BillingAgreementDetailsType> billList = new ArrayList<BillingAgreementDetailsType>();
//				billList.add(billingAgreement);
//				details.setBillingAgreementDetails(billList);
//			}
			
			//shipping address
			/*
			 * Indicates whether or not you require the buyer's shipping address on 
			 * file with PayPal be a confirmed address. For digital goods, 
			 * this field is required, and you must set it to 0. It is one of the following values:
				0 You do not require the buyer's shipping address be a confirmed address.
				1 You require the buyer's shipping address be a confirmed address.
				Note:
				Setting this field overrides the setting you specified in your Merchant Account Profile.
				Character length and limitations: 1 single-byte numeric character
			 */
			details.setReqConfirmShipping("1");	//用户确认收货地址
			/*
			 *  (Optional) Determines whether or not the PayPal pages should 
			 *  display the shipping address set by you in this SetExpressCheckout request,
			 *   not the shipping address on file with PayPal for this buyer. Displaying 
			 *   the PayPal street address on file does not allow the buyer to edit that address. 
			 *   It is one of the following values:
				 0 The PayPal pages should not display the shipping address.
				 1 The PayPal pages should display the shipping address.
				Character length and limitations: 1 single-byte numeric character
			 */
			details.setAddressOverride("1");

			
			/*
			 * Determines where or not PayPal displays shipping address fields on the PayPal pages. 
			 * For digital goods, this field is required, and you must set it to 1. It is one of the
			 *  following values:
				0  PayPal displays the shipping address on the PayPal pages.
				1  PayPal does not display shipping address fields whatsoever.
				2  If you do not pass the shipping address, PayPal obtains it from the 
				buyer's account profile.
				Character length and limitations: 4 single-byte numeric characters
			 */
			details.setNoShipping("0");
			
			// PayPal page styling attributes
			/*
			 *  (Optional) A label that overrides the business name in the PayPal account on 
			 *   the PayPal hosted checkout pages.
				 Character length and limitations: 127 single-byte alphanumeric characters
			 */
			details.setBrandName("");
			/*
			 *  (Optional) Name of the Custom Payment Page Style for payment pages associated with
			 *  this button or link. It corresponds to the HTML variable page_style for customizing
			 *  payment pages. It is the same name as the Page Style Name you chose to add or edit 
			 *  the page style in your PayPal Account profile.
				Character length and limitations: 30 single-byte alphabetic characters
			 */
			details.setCustom("");
			/*
			 * (Optional) URL for the image you want to appear at the top left of the payment page. 
			 * The image has a maximum size of 750 pixels wide by 90 pixels high. PayPal recommends 
			 * that you provide an image that is stored on a secure (https) server. 
			 * If you do not specify an image, the business name displays.
			 */
			details.setCppHeaderImage(logoURL);
			/*
			 *  (Optional) Sets the border color around the header of the payment page. 
			 *  The border is a 2-pixel perimeter around the header space, which is 750 pixels
			 *  wide by 90 pixels high. By default, the color is black.
				Character length and limitations: 6-character HTML hexadecimal ASCII color code
			 */
			details.setCppHeaderBorderColor("");
			/*
			 *  (Optional) Sets the background color for the header of the payment page. 
			 *   By default, the color is white.
				 Character length and limitations: 6-character HTML hexadecimal ASCII color code 
			 */
			details.setCppHeaderBackColor("");
			/*
			 *  (Optional) Sets the background color for the payment page. 
			 *   By default, the color is white.
				 Character length and limitations: 6-character HTML hexadecimal ASCII color code 
			 */
			details.setCppPayflowColor("");
			/*
			 *  (Optional) Enables the buyer to enter a note to the merchant on the 
			 *  PayPal page during checkout. The note is returned in the 
			 *  GetExpressCheckoutDetails response and the DoExpressCheckoutPayment response.
			 *  It is one of the following values:
				0 The buyer is unable to enter a note to the merchant.
				1 The buyer is able to enter a note to the merchant.
				Character length and limitations: 1 single-byte numeric character
				This field is available since version 53.0. 
			 */
			details.setAllowNote("0");

			setExpressCheckoutReq.setSetExpressCheckoutRequestDetails(details);

			SetExpressCheckoutReq expressCheckoutReq = new SetExpressCheckoutReq();
			expressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutReq);
			
			logger.info("调用PayPal支付接口setExpressCheckout方法开始...");
			SetExpressCheckoutResponseType setExpressCheckoutResponse = service.setExpressCheckout(expressCheckoutReq);
			if (setExpressCheckoutResponse != null) {
				logger.info("调用PayPal支付接口setExpressCheckout结束...");
				logger.warn("调用PayPal支付接口setExpressCheckout方法输入参数："+service.getLastRequest());
				logger.warn("调用PayPal支付接口setExpressCheckout方法输出参数："+service.getLastResponse());
				if (setExpressCheckoutResponse.getAck().toString().equalsIgnoreCase("SUCCESS")) {
					returnToken = setExpressCheckoutResponse.getToken();
					logger.info("调用PayPal支付接口setExpressCheckout方法成功，返回的TOKEN为：" + returnToken);
				} else {
					logger.error("调用PayPal支付接口setExpressCheckout方法失败，返回的错误消息为：");
					for(ErrorType curErrorType : setExpressCheckoutResponse.getErrors()){
						logger.error("错误代码：" + curErrorType.getErrorCode() + ",短消息："+curErrorType.getShortMessage() + ",长消息："+curErrorType.getLongMessage());
					}
				}
			}
		}catch(Exception ex){
			logger.error("调用PayPal快速支付接口发生异常：" + ex.getMessage(),ex);
		}
		
		return returnToken;
	}

	/**
	 * 执行paypal快速付款接口doExpressCheckout
	 * DoExpressCheckoutPayment文档地址：https://developer.paypal.com/webapps/developer/docs/classic/api/merchant/DoExpressCheckoutPayment_API_Operation_SOAP/
	 * @param orderSell 订单
	 * @param orderSellItemList  订单项列表
	 * @param token token
	 * @param payerId 付款方
	 * @param currencyCodeType 货币类型
	 * @param exchangeRate 汇率对象
	 * @return 支付是否成功
	 */
	@Override
	public boolean doDoExpressCheckout(OrderSell orderSell,List<OrderSellItem> orderSellItemList,String token,String payerId,String currencyCodeType) {
		DecimalFormat df = new DecimalFormat("#.00");	//格式化2位小数
		
		Map<String,String> configurationMap =  new HashMap<String,String>();
		configurationMap.put("acct1.UserName", paypalAcctUserName);
		configurationMap.put("acct1.Password", paypalAcctPassword);
		configurationMap.put("acct1.Signature", paypalAcctSignature);
		// Endpoints are varied depending on whether sandbox OR live is chosen for mode
		configurationMap.put("mode", paypalMode);
		
		try{
			// Creating service wrapper object to make an API call by loading configuration map.
			PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);
			
			DoExpressCheckoutPaymentRequestType doCheckoutPaymentRequestType = new DoExpressCheckoutPaymentRequestType();
			DoExpressCheckoutPaymentRequestDetailsType details = new DoExpressCheckoutPaymentRequestDetailsType();
			/*
			 * A timestamped token by which you identify to PayPal that you are processing
			 * this payment with Express Checkout. The token expires after three hours. 
			 * If you set the token in the SetExpressCheckout request, the value of the token
			 * in the response is identical to the value in the request.
			   Character length and limitations: 20 single-byte characters
			 */
			details.setToken(token);
			/*
			 * Unique PayPal Customer Account identification number.
			   Character length and limitations: 13 single-byte alphanumeric characters
			 */
			details.setPayerID(payerId);
			
			PaymentDetailsType paymentDetails = new PaymentDetailsType();
			BasicAmountType orderTotal = new BasicAmountType();
			orderTotal.setValue(df.format(orderSell.getExchangeRateAmountTotal()));
			//PayPal uses 3-character ISO-4217 codes for specifying currencies in fields and variables.
			orderTotal.setCurrencyID(CurrencyCodeType.fromValue(currencyCodeType));
			/*
			 *  (Required) The total cost of the transaction to the buyer. 
			 *  If shipping cost (not applicable to digital goods) and tax charges are known, 
			 *  include them in this value. If not, this value should be the current sub-total 
			 *  of the order. If the transaction includes one or more one-time purchases, this 
			 *  field must be equal to the sum of the purchases. Set this field to 0 if the 
			 *  transaction does not include a one-time purchase such as when you set up a 
			 *  billing agreement for a recurring payment that is not immediately charged. 
			 *  When the field is set to 0, purchase-specific fields are ignored. 
			 *  For digital goods, the following must be true:
				total cost > 0
				total cost <= total cost passed in the call to SetExpressCheckout
			 Note:
				You must set the currencyID attribute to one of the 3-character currency codes 
				for any of the supported PayPal currencies.
				When multiple payments are passed in one transaction, all of the payments must 
				have the same currency code.
				Character length and limitations: Value is a positive number which cannot 
				exceed $10,000 USD in any currency. It includes no currency symbol. 
				It must have 2 decimal places, the decimal separator must be a period (.), 
				and the optional thousands separator must be a comma (,).
			 */
			paymentDetails.setOrderTotal(orderTotal);

			BasicAmountType itemTotal = new BasicAmountType();
			itemTotal.setValue(df.format(orderSell.getExchangeRateAmountProduct()));
			//PayPal uses 3-character ISO-4217 codes for specifying currencies in fields and variables.
			itemTotal.setCurrencyID(CurrencyCodeType.fromValue(currencyCodeType));
			/*
			 *  Sum of cost of all items in this order. For digital goods, this field is 
			 *  required. PayPal recommends that you pass the same value in the call to 
			 *  DoExpressCheckoutPayment that you passed in the call to SetExpressCheckout.
			 Note:
				You must set the currencyID attribute to one of the 3-character currency 
				codes for any of the supported PayPal currencies.
				Character length and limitations: Value is a positive number which cannot 
				exceed $10,000 USD in any currency. It includes no currency symbol. 
				It must have 2 decimal places, the decimal separator must be a period (.), 
				and the optional thousands separator must be a comma (,).
			 */
			paymentDetails.setItemTotal(itemTotal);
			
			/*
			 * 邮费 
			 */
			BasicAmountType shippingTotal = new BasicAmountType();
			shippingTotal.setValue(df.format(orderSell.getExchangeRateAmountFreight()));
			//PayPal uses 3-character ISO-4217 codes for specifying currencies in fields and variables.
			shippingTotal.setCurrencyID(CurrencyCodeType.fromValue(currencyCodeType));
			paymentDetails.setShippingTotal(shippingTotal);

			List<PaymentDetailsItemType> paymentItems = new ArrayList<PaymentDetailsItemType>();
			
			for(OrderSellItem curOrderSellItem : orderSellItemList){
				PaymentDetailsItemType paymentItem = new PaymentDetailsItemType();
				/*
				 * Item name. This field is required when you pass a value for ItemCategory.
				   Character length and limitations: 127 single-byte characters
				   This field is introduced in version 53.0. 
				 */
				paymentItem.setName(curOrderSellItem.getProductName());
				/*
				 * Item quantity. This field is required when you pass a value for ItemCategory. 
				 * For digital goods (ItemCategory=Digital), this field is required.
					Character length and limitations: Any positive integer
					This field is introduced in version 53.0. 
				 */
				paymentItem.setQuantity((int)curOrderSellItem.getNum());
				BasicAmountType amount = new BasicAmountType();
				/*
				 * Cost of item. This field is required when you pass a value for ItemCategory.
				Note:
				You must set the currencyID attribute to one of the 3-character currency codes for
				any of the supported PayPal currencies.
				Character length and limitations: Value is a positive number which cannot 
				exceed $10,000 USD in any currency. It includes no currency symbol.
				It must have 2 decimal places, the decimal separator must be a period (.), 
				and the optional thousands separator must be a comma (,).
				This field is introduced in version 53.0.
				 */
				amount.setValue(df.format(curOrderSellItem.getExchangeRateUnitPrice()));
				//PayPal uses 3-character ISO-4217 codes for specifying currencies in fields and variables.
				amount.setCurrencyID(CurrencyCodeType.fromValue(currencyCodeType));
				paymentItem.setAmount(amount);
				paymentItems.add(paymentItem);
			}
			
			paymentDetails.setPaymentDetailsItem(paymentItems);
			/*
			 *  (Optional) Your URL for receiving Instant Payment Notification (IPN) 
			 *  about this transaction. If you do not specify this value in the request, 
			 *  the notification URL from your Merchant Profile is used, if one exists.
				Important:
				The notify URL applies only to DoExpressCheckoutPayment. 
				This value is ignored when set in SetExpressCheckout or GetExpressCheckoutDetails.
				Character length and limitations: 2,048 single-byte alphanumeric characters
			
			paymentDetails.setNotifyURL(request.getParameter("notifyURL"));
			 */
			
			List<PaymentDetailsType> payDetailType = new ArrayList<PaymentDetailsType>();
			payDetailType.add(paymentDetails);
			/*
			 * When implementing parallel payments, you can create up to 10 sets of payment 
			 * details type parameter fields, each representing one payment you are hosting 
			 * on your marketplace.
			 */
			details.setPaymentDetails(payDetailType);

			doCheckoutPaymentRequestType.setDoExpressCheckoutPaymentRequestDetails(details);
			DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();
			doExpressCheckoutPaymentReq.setDoExpressCheckoutPaymentRequest(doCheckoutPaymentRequestType);

			logger.info("调用PayPal支付接口doExpressCheckout方法开始...");
			DoExpressCheckoutPaymentResponseType doCheckoutPaymentResponseType = service.doExpressCheckoutPayment(doExpressCheckoutPaymentReq);
			if (doCheckoutPaymentResponseType != null) {
				logger.info("调用PayPal支付接口doExpressCheckout结束...");
				logger.warn("调用PayPal支付接口doExpressCheckout方法输入参数："+service.getLastRequest());
				logger.warn("调用PayPal支付接口doExpressCheckout方法输出参数："+service.getLastResponse());
				if (doCheckoutPaymentResponseType.getAck().toString().equalsIgnoreCase("SUCCESS")) {
					logger.info("调用PayPal支付接口doExpressCheckout成功!");
					Iterator<PaymentInfoType> iterator = doCheckoutPaymentResponseType.getDoExpressCheckoutPaymentResponseDetails().getPaymentInfo().iterator();
					int index = 1;
					/*
					 * Unique transaction ID of the payment.
					 Note:
						If the PaymentAction of the request was Authorization or Order, 
						this value is your AuthorizationID for use with the Authorization 
						& Capture APIs.
						Character length and limitations: 19 single-byte characters
					 */
					while (iterator.hasNext()) {
						PaymentInfoType result = (PaymentInfoType) iterator.next();
						logger.info("Transaction ID" + index + ":" + result.getTransactionID());
						index++;
					}
					
					return true;
					
				} else {	
					logger.error("调用PayPal支付接口doExpressCheckout方法失败，返回的错误消息为：");
					for(ErrorType curErrorType : doCheckoutPaymentResponseType.getErrors()){
						logger.error("错误代码：" + curErrorType.getErrorCode() + ",短消息："+curErrorType.getShortMessage() + ",长消息："+curErrorType.getLongMessage());
					}
				}
			}
			
		}catch(Exception ex){
			logger.error("调用PayPal快速支付接口doExpressCheckout发生异常：" + ex.getMessage(),ex);
		}
		
		return false;
	}

}
