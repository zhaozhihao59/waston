// 查询订单
function searchOrder12(){
	var orderNo = $("#orderNo").val();
	var orderState = $("#orderState").val();
	window.location.href = basePath + "member/account/my_order.htm?orderSellCondition.orderNo="+
		orderNo+"&orderSellCondition.orderState="+orderState;
}

//订单付款
function payOrder(itemId){
	//$("body").mask("正在付款，请稍后...");
	//window.location.href = basePath + 'member/order/pay/toSetPaypalExpressCheckout.htm?item.id='+itemId;
	window.location.href = basePath + 'member/order/pay/toChoosePayType.htm?item.id='+itemId;
}

// 取消订单
function cancelOrder(id){
	$.post(
		"member/account/cancelOrder.htm?orderId="+id,
		function(data){
			window.location.href = basePath + "member/account/my_order.htm";
		}
	);
}
// 确认收货
function comfirmBusiness(id){
	$.post(
		"member/account/comfirmBusiness.htm?orderId="+id,
		function(data){
			window.location.href = basePath + "member/account/my_order.htm";
		}
	);
}
// 移除订单
function clearOrder(id){
	$.post(
		"member/account/clearOrder.htm?orderId="+id,
		function(data){
			window.location.href = basePath + "member/account/my_order.htm";
		}
	);
}
