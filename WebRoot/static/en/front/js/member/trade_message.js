$(document).ready(function(){
	// 打开信息
	$(".trade-box").click(function(){
		$.post("product/checkUser.htm",{},function(data){
			if(data.status=="success"){
				$('.trade-box-content').find('input').val('');
				$(".trade-box-content").show();
				$(".msg-box").show();
				$('.msg-box').css('height', h + 'px');
				
				$("#itemCreateBy").val(data.message);
				$("#itemType").val(2);
				$("#itemContent").val("");
			}else{
				window.location.href=base+"m/acc/index.htm";
			}
		});
	});
	
	$(".od-close-btn").click(function(){
				$(".trade-box-content").hide();
				$(".msg-box").hide();
				$("#massageDiv").unmask();
			});
			$(".c-btn").click(function(){
				$(".trade-box-content").hide();
				$(".msg-box").hide();
				$("#massageDiv").unmask();
			});
	$("#messageForm").ajaxForm({
		dataType:'json',
		success:function(data){
			$("#massageDiv").unmask();
			if(data.status=="success"){
				$(".trade-box-content").hide();
				$(".msg-box").hide();
				$.dialog({
					title:'消息',
					icon:'succeed',
					content:'申请成功！',
					ok:function(){
					}
				});
			}else{
				$.dialog.alert("申请失败！");
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
			$("#itemContent").attr("placeholder","您输入的备注信息过长");
			return;
		}
		
		$("#massageDiv").mask("正在申请中，请稍候...");
		$("#messageForm").submit();
	});
});