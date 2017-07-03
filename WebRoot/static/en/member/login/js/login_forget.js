$(document).ready(function() {
	$('#email').live("keydown",function(e){
		if(e.keyCode == '13'){
			sendForgitMail();
			return false;
		}
	});
	$('#submitBtn').live('click',function(){
		sendForgitMail();
	});
});

function sendForgitMail(){
	if($('#email').val() == null || $('#email').val() == ''){
		$.dialog.tips('请输入邮箱找回密码  !!!');
		return;
	}
	$('#sendMailForm').ajaxSubmit(function(data){
		if(data.status == "success"){
			$.dialog.tips("发送成功  请检查您的邮箱 ！！！");
/*			$.dialog({
				title : '发送成功',
				content : '发送成功，请检查您的邮箱。',
				icon:'succeed',
				okVal : "确定",
				ok:function(){}
			})*/
		}else if(data.status == "error"){
			$.dialog.tips(data.message);
		}else{
			$.dialog.tips("发送失败");
		}
	});
	return false;
}
