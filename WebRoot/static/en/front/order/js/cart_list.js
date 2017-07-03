var cartItemChoose=[];
$(document).ready(function(){

	// 初始化货币格式控件
	initCurreny();
	
	/** =========== 前端样式相关 =========== */
	
	/** =========== 前端样式相关 END =========== */
	
	/** =========== 后端功能相关 =========== */
	
	// 购物车数量减少
	$(".J-minusNum").live("click",function(){
		var n = $(this).closest("ul").find(".J-cartNum");
		var cartNum = $(n).val();
		if(cartNum > 1){
			cartNum--;
		}
		$(n).val(cartNum);
		
		changeNumMinus(cartNum, this);
	});
	
	// 购物车数量增大
	$(".J-addNum").live("click",function(){
		var n = $(this).closest("ul").find(".J-cartNum");
		var cartNum = $(n).val();
		if(cartNum < 200){
			cartNum++;
		}
		$(n).val(cartNum);
		
		changeNumAdd(cartNum, this);
	});
	
	// 光标从数量文本框中移开时
	$(".J-cartNum").live("blur",function(){
		var cartNum = $(this).val();
		if(cartNum < 1 || isNaN(cartNum)){
			$(this).val(1);
			changeNum(1,this);
		}else if(cartNum > 200){
			$(this).val(200);
			changeNum(200,this);
		}else{
			changeNum(cartNum,this);
		}
	});
	
	// 点击全选时
	$("#selectAll").live("click",function(){
		if($(this).attr("checked")){
			// 样式渲染
			$(".J-checkbox").attr("checked",true);
			dataReady();
		}else{
			// 样式渲染
			$(".J-checkbox").attr("checked",false);
			dataReady();
		}
	});
	
	// 点击checkbox选择框
	$(".J-checkbox").live("click",function(){
		dataReady();
	});
	
	// 默认页面加载完毕后，全选所有购物车产品
	
	// 样式渲染
	$("#selectAll").attr("checked",true);
	$(".J-checkbox").attr("checked",true);
	// 数据组织
	dataReady();
	
	// 点击立即购买
	$("#buyNow").live("click",function(){
		window.location.href = base + "en/member/order/sell/buy.htm?cartItemStrs="+cartItemChoose.join();
	});
	
	// 点击移除
	$(".J-removeCartItem").live("click",function(){
		// 点击后使再次点击失效，为了防止重复点击
		$(this).removeClass("J-removeCartItem");
		$.post("en/cart/remove_items.htm",{"cartItemChoose":cartItemChoose.join()},function(data){
			window.location.href = base + "en/cart/index.htm?m="+Math.random();
		});
		
	});
});

function dataReady(){
	cartItemChoose = [];
	var totalPrice = 0.0;
	var isAllChoose = true;
	
	$(".J-checkbox").each(function(i,n){
		if($(n).attr("checked")){
			// 组织购物车ID集合，为了生成订单
			var id = $(n).attr("data");
			cartItemChoose.push(id);
			
			// 组织即将提交订单的总金额
			var sumPrice = $(n).prev().val();
			totalPrice += Number(sumPrice);
			
		}else{
			isAllChoose = false;
		}
	});
	
	// 如果所有选择框全部选中时，全选也选中
	if(isAllChoose){
		$("#selectAll").attr("checked",true);
	}else{
		$("#selectAll").attr("checked",false);
	}
	
	$("#totalPrice").html(totalPrice);
	$("#totalPrice").formatCurrency();
	
}

function changeNumAdd(num, e){
	$(e).removeClass("J-addNum");
	var itemId = $(e).attr("data");
	$.post('en/cart/change_num.htm?m='+Math.random(),{"itemId":itemId, "cartNum":num},function(data){
		$(e).addClass("J-addNum");
		
		// 更改小计金额
		var n = $(e).closest("td").next().find(".J-sumPriceLabel");
		$(n).html(data.sumPrice);
		$(n).formatCurrency();
		
		var nn = $(e).closest("td").next().next().find(".J-sumPrice");
		$(nn).val(data.sumPrice);
		
		// 重新计算总金额
		dataReady();
		
		// 更新头部的购物车产品数量
		$("#headerCart").html('A total of '+data.cartItemNum+' items');
	});
}

function changeNumMinus(num, e){
	$(e).removeClass("J-minusNum");
	var itemId = $(e).attr("data");
	$.post('en/cart/change_num.htm?m='+Math.random(),{"itemId":itemId, "cartNum":num},function(data){
		$(e).addClass("J-minusNum");
		
		// 更改小计金额
		var n = $(e).closest("td").next().find(".J-sumPriceLabel");
		$(n).html(data.sumPrice);
		$(n).formatCurrency();
		
		var nn = $(e).closest("td").next().next().find(".J-sumPrice");
		$(nn).val(data.sumPrice);
		
		// 重新计算总金额
		dataReady();
		
		// 更新头部的购物车产品数量
		$("#headerCart").html('A total of '+data.cartItemNum+' items');
	});
}

/**
  * 改变购物车的数量时同步计算小计、总金额与购物车产品数量
  */
function changeNum(num, e){
	var itemId = $(e).attr("data");
	$.post('en/cart/change_num.htm?m='+Math.random(),{"itemId":itemId, "cartNum":num},function(data){
		
		// 更改小计金额
		var n = $(e).closest("td").next().find(".J-sumPriceLabel");
		$(n).html(data.sumPrice);
		$(n).formatCurrency();
		
		var nn = $(e).closest("td").next().next().find(".J-sumPrice");
		$(nn).val(data.sumPrice);
		
		// 重新计算总金额
		dataReady();
		
		// 更新头部的购物车产品数量
		$("#headerCart").html('A total of '+data.cartItemNum+' items');
	});
}

// 初始化货币格式插件
function initCurreny(){
	// 初始化货币格式插件
    $.formatCurrency.regions[''] = {
    	symbol: '$',
		positiveFormat: '%s%n',
		negativeFormat: '%s-%n',
		decimalSymbol: '.',
		digitGroupSymbol: ',',
		groupDigits: false
    }
}
