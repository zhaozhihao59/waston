$(document).ready(function(){
	
	/** =========== 前端样式相关 =========== */
	
	var oStar = document.getElementById("star");
	var aLi = oStar.getElementsByTagName("li");
	var oUl = oStar.getElementsByTagName("ul")[0];
	var oSpan = oStar.getElementsByTagName("span")[1];
	var oP = oStar.getElementsByTagName("p")[0];
	var i = iScore = iStar = 0;
	var aMsg = [
				"Poor unhappy with | too outrageous, inconsistent with the seller description of serious, very unhappy",
				"Not satisfied | part is broken, not in conformity with the seller described, not satisfied",
				"General | quality general, is not so good as the seller described",
				"Satisfied | it's of good quality, consistent with the seller description of the basic, is quite satisfactory",
				"Very satisfied | quality is very good, and the seller described completely consistent, very satisfied"
				];
	
	for (i = 1; i <= aLi.length; i++){
		aLi[i - 1].index = i;
		
		//鼠标移过显示分数
		aLi[i - 1].onmouseover = function (){
			fnPoint(this.index);
			//浮动层显示
			oP.style.display = "block";
			//计算浮动层位置
			oP.style.left = oUl.offsetLeft + this.index * this.offsetWidth - 94 + "px";
			//匹配浮动层文字内容
			oP.innerHTML = "<em><b>" + this.index + "</b> Point " + aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg[this.index - 1].match(/\|(.+)/)[1];
		};
		
		//鼠标离开后恢复上次评分
		aLi[i - 1].onmouseout = function (){
			fnPoint();
			//关闭浮动层
			oP.style.display = "none";
		};
		
		//点击后进行评分处理
		aLi[i - 1].onclick = function (){
			iStar = this.index;
			oP.style.display = "none";
			oSpan.innerHTML = "<strong>" + (this.index) + " Point</strong> (" + aMsg[this.index - 1].match(/\|(.+)/)[1] + ")";
			$("#mark").val(this.index);
		};
	}
	
	//评分处理
	function fnPoint(iArg){
		//分数赋值
		iScore = iArg || iStar;
		for (i = 0; i < aLi.length; i++) aLi[i].className = i < iScore ? "on" : "";	
	}
	
	//滑动列表js
	//默认状态
	var lis = $('.nlb-ul li');
	var liNum =lis.length;
	// 当前显示li所在位置
	var curIndex = 0;
	
	$(".next-btn").live("click",function(){
		if(liNum - curIndex >6){
			$(lis).eq(curIndex).animate({marginLeft: '-180px'}, "slow");
			curIndex = curIndex+1;
		}
	});
	$(".back-btn").live("click",function(){
		if(curIndex >0){
			$(lis).eq(curIndex-1).animate({marginLeft: '0px'}, "slow");
			curIndex = curIndex-1;
		}
	});
	
	var flag = $("#flag").val();
	if(flag != '' && flag == 0){
		$(".dnb-ul li").eq(0).removeClass("cur");
		$(".dnb-ul li").eq(1).addClass("cur");
		$(".dnb-content-box").eq(1).show().siblings(".dnb-content-box").hide();
	}else{
		$(".dnb-ul li").eq(1).removeClass("cur");
		$(".dnb-ul li").eq(0).addClass("cur");
		$(".dnb-content-box").eq(0).show().siblings(".dnb-content-box").hide();
	}
	
	$(".dnb-ul li").each(function(i){
		$(this).click(function(){
			$(this).addClass("cur").siblings().removeClass("cur");
			$(".dnb-content-box").eq(i).show().siblings(".dnb-content-box").hide();
		});
	});
	
	
	//默认状态
	var lis = $('.favourite-goods-ul li');
	var liNum =lis.length;
	// 当前显示li所在位置
	var curIndex = 0;
	
	$(".lnext-btn").live("click",function(){
		if(liNum - curIndex >4){
			$(lis).eq(curIndex).animate({marginLeft: '-250px'}, "slow");
			curIndex = curIndex+1;
		}
	});
	$(".lback-btn").live("click",function(){
		if(curIndex >0){
			$(lis).eq(curIndex-1).animate({marginLeft: '0px'}, "slow");
			curIndex = curIndex-1;
		}
	});
	
	/** =========== 前端样式相关 END =========== */
	
	/** =========== 后端功能相关 =========== */
	
	// 购物车数量增加与减少
	$("#minusNum").live("click",function(){
		var cartNum = $("#cartNum").val();
		if(cartNum > 1){
			cartNum--;
		}
		$("#cartNum").val(cartNum);
	});
	$("#addNum").live("click",function(){
		var cartNum = $("#cartNum").val();
		if(cartNum < 200){
			cartNum++;
		}
		$("#cartNum").val(cartNum);
	});
	$("#cartNum").live("blur",function(){
		var cartNum = $("#cartNum").val();
		if(isNaN(cartNum)){
			$("#cartNum").val(1);
		}
		if(cartNum < 1){
			$("#cartNum").val(1);
		}
		if(cartNum > 200){
			$("#cartNum").val(200);
		}
	});
	
	// 添加到购物车
	$(".J-addCart").live("click",function(){
	
		var productId = $("#productId").val();
		var cartNum = $("#cartNum").val();
		
		var e = $(this);
		
		// 点击添加购物车后，将此按钮设为不可点击
		$(e).removeClass("J-addCart");
		
		// 添加到购物车
		$.post('en/cart/add.htm?m='+Math.random(),{'productId':productId,'cartNum':cartNum},function(data){
			// 异步请求执行完毕后恢复点击
			$(e).addClass("J-addCart");
			
			if(data.status == 'success'){
				// 添加成功后弹框提示
				$(".msg-box-content").show();
				$(".msg-box").show();
				
				// 显示弹出框中加入购物车的产品信息
				$("#cartItemImage").attr("src",data.imageUrl);
				$("#cartItemProductName").html(data.productNameEn);
				$("#cartItemUnitPrice").html(data.unitPrice);
				$("#cartItemUnitPrice").formatCurrency();
				$("#num").html(data.num);
				$("#cartItemSumPrice").html(data.sumPrice);
				$("#cartItemSumPrice").formatCurrency();
				
				// 显示购物车内的商品数量
				$("#headerCart").html('A total of '+data.cartItemNum+' items');
			}else{
				$.dialog.alert(data.message);
			}
		});
	});
	
	// 初始化货币格式插件
    $.formatCurrency.regions[''] = {
    	symbol: '$',
		positiveFormat: '%s%n',
		negativeFormat: '%s-%n',
		decimalSymbol: '.',
		digitGroupSymbol: ',',
		groupDigits: false
    }
	
	
});

//立即购买
function buy(productId){
	var cartNum = $("#cartNum").val();
	window.location.href = basePath+"en/member/order/sell/buy.htm?productId="+productId+"&num="+cartNum;
}

//发布评论
function publish(){
	var content = $("#content").val();
	var mark = $("#mark").val();
	var productId = $("#productId").val();
	if(mark == ''){
		$("#errorlist").html('');
		$("#errorlist").html("Please score");
		return;
	}
	if(content == ''){
		$("#errorlist").html('');
		$("#errorlist").html("Please fill in the comment");
		return;
	}
	var categoryAndBrandName = $("#categoryAndBrandName").val();
	$.post(
		'en/productComment/doSave.htm',
		{"item.content":content,"item.mark":mark,"item.productId":productId,"preUrl":"en/product/detail.htm?id="+productId+"&categoryAndBrandName="+categoryAndBrandName+"&flag=0"},
		function(data){
			if(data.status == 'success'){
				window.location.href = basePath + "en/product/detail.htm?id="+productId+"&categoryAndBrandName="+categoryAndBrandName+"&flag=0";
			}else{
				window.location.href = basePath + "en/login/index_back.htm";
			}
		}
	);
}

// 改变评论的字数
function changeCharNum(){
	var len =$("#content").val().length;
    var remain = len;
    var tip_span = $("#charNum");
    tip_span.html(225-Math.abs(remain));
}
