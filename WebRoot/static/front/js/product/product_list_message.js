$(document).ready(function(){
	var h = $(window).height();
	
	// 打开信息
	$(".btn-show").click(function(){
		var productName=$(this).attr("data");
		var productId = $(this).attr("dataId");
		$.post(base+"product/checkUser.htm",{},function(data){
			if(data.status=="success"){
				$('.msg-box-pd').find('input').val('');
				$(".msg-box-pd").show();
				$(".msg-box").show();
				$('.msg-box').css('height', h + 'px');
				
				$("#itemProductName").val(productName);
				$("#itemCreateBy").val(data.message);
				$("#itemType").val(3);
				$("#itemContent").val("");
				$("#itemProductId").val(productId);
			}else{
				window.location.href=base+"m/acc/index.htm";
			}
		});
	});
	$(".od-close-btn").click(function(){
		$(".msg-box-pd").hide();
		$(".msg-box").hide();
		$("#massageDiv").unmask();
	});
	$(".c-btn").click(function(){
		$(".msg-box-pd").hide();
		$(".msg-box").hide();
		$("#massageDiv").unmask();
	});
		
	$(window).scroll(function() {
		var y = $(window).scrollTop();
			$('.msg-box').css('margin-top', y + 'px');
	});
	
//	$.formValidator.initConfig({validatorGroup:"1"});
//	$("#itemContent").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"请输入内容！",onErrorMax:"内容过长！"});
	
	$("#messageForm").ajaxForm({
		dataType:'json',
		success:function(data){
			$("#massageDiv").unmask();
			if(data.status=="success"){
				$(".msg-box-pd").hide();
				$(".msg-box").hide();
				$.dialog({
					title:'消息',
					icon:'succeed',
					content:'提交成功！',
					ok:function(){
					}
				});
			}else{
				$.dialog.alert("提交失败！");
			}
		}
	});
	
	// 保存信息
	$('.saveBtn').live('click',function(){
//		var result = $.formValidator.pageIsValid(1);	//手动调用验证框架进行验证
		
		var content=$("#itemContent").val();
		if(content.length<1){
			var tip = $("#itemContent").attr("placeholder");
			var timer = setTimeout(function(){
			$("#itemContent").attr("placeholder",tip);
			},5000);
			$("#itemContent").attr("placeholder","请输入备注信息");
			return;
		}else if(content.length>500){
			var tip = $("#itemContent").attr("placeholder");
			var timer = setTimeout(function(){
			$("#itemContent").attr("placeholder",tip);
			},5000);
			$("#itemContent").attr("placeholder","请输入备注信息过长");
			return;
		}
		
		$("#massageDiv").mask("正在提交，请稍候...");
		$("#messageForm").submit();
	});
});