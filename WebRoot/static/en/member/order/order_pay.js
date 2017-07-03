// 查询订单
function searchOrder(){
	var orderNo = $("#orderNo").val();
	var orderState = $("#orderState").val();
	window.location.href = basePath + "en/member/account/my_order.htm?orderSellCondition.orderNo="+
		orderNo+"&orderSellCondition.orderState="+orderState;
}

//订单付款
function payOrder(itemId){
	$("body").mask("Is the payment, please later...");
	window.location.href = basePath + 'en/member/order/pay/toSetPaypalExpressCheckout.htm?item.id='+itemId;
}

// 取消订单
function cancelOrder(id){
	$.post(
		"en/member/account/cancelOrder.htm?orderId="+id,
		function(data){
			window.location.href = basePath + "en/member/account/my_order.htm";
		}
	);
}
// 确认收货
function comfirmBusiness(id){
	$.post(
		"en/member/account/comfirmBusiness.htm?orderId="+id,
		function(data){
			window.location.href = basePath + "en/member/account/my_order.htm";
		}
	);
}
// 移除订单
function clearOrder(id){
	$.post(
		"en/member/account/clearOrder.htm?orderId="+id,
		function(data){
			window.location.href = basePath + "en/member/account/my_order.htm";
		}
	);
}
