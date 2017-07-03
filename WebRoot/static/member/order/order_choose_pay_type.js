//订单付款
function payOrder(itemId){
	var payType = $("input[name='payType']:checked").val();
	if(payType == 1){
		//Paypal
		$("body").mask("正在付款，请稍后...");
		window.location.href = basePath + 'member/order/pay/toSetPaypalExpressCheckout.htm?item.id='+itemId;	
	}else if(payType == 2){
		//网银在线
		window.location.href = basePath + 'member/order/pay/toChinabankPay.htm?item.id='+itemId;
	}else if(payType == 3){
		//支付宝
		window.location.href = basePath + 'member/order/pay/toAlipay.htm?item.id='+itemId;
	}
}
