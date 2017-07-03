$(document).ready(function(){
	
	$(".l-img-box").hover(function(){
		var divSec = $(this).children(".sc-ico");
		$(divSec).removeClass("none");
	},function(){
		var divSec = $(this).children(".sc-ico");
		$(divSec).addClass("none");
	});
	
	var url = $("#url").val();
	// 搜索
	$("#search").click(function(){
		$("#form").submit();
	});
	// 价格排序
	$("#priceSort").click(function(){
		// 指定排序方式按价格
		$("#sort").val('unitPrice');
		$("#form").submit();
	});
	// 销量排序
	$("#salesSort").click(function(){
		// 指定排序方式按销量
		$("#sort").val('sales');
		$("#form").submit();
	});
	
	var currentPage = $("#currentPage").val();
	var allPages = $("#allPages").val();
	// 上一页
	$("#upPage").click(function(){
		var page = parseInt(currentPage)-1;
		$(this).attr("href",url+"&page="+page);
	});
	
	// 下一页
	$("#downPage").click(function(){
		var page = parseInt(currentPage)+1;
		if(page > allPages){
			page = allPages;
		}
		$(this).attr("href",url+"&page="+page);
	});
	
	// 添加到购物车
	$(".J-addCart").live("click",function(){
	
		var productId = $(this).prev().val();
		var cartNum = 1;
		
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
    	symbol: '￥',
		positiveFormat: '%s%n',
		negativeFormat: '%s-%n',
		decimalSymbol: '.',
		digitGroupSymbol: ',',
		groupDigits: false
    }
	
	
});


