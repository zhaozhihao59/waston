$(document).ready(function() {
	$("#account").val('');
	$("#password").val('');
	$("#verCode").val('');
	$.formValidator.initConfig({theme:"OutSourcing",validatorGroup:1});
	$("#account").formValidator({onShow:'',onFocus:'',onCorrect:''})
		.inputValidator({min:1,max:255,onErrorMin:"电子邮箱必填",onErrorMax:"电子邮箱长度过长"})
		.regexValidator({regExp:'email',dataType:"enum",onError:"电子邮箱格式不正确"});
	$("#password").formValidator().inputValidator({min:8,max:16,onErrorMin: "密码必填，且不能小于8位",onErrorMax: "密码不能大于16位"});
	$("#verCode").formValidator().inputValidator({min:1,max:4,onErrorMin: "验证码必填",onErrorMax: "验证码不能超过4位"});
	
	// 验证码处敲击回车
	$("#verCode").live("keydown",function(e){
		if(e.keyCode == '13'){
			$('#loginSubmit').click();
			return false;
		}
	});
	
	// 点击换一张验证码图片
	$('#noLook').live('click',function(){
		$("#captchaImage").attr('src', 'japtcha.htm?r=' + Math.random());
	});
	
	// 登录按钮
	$('#loginSubmit').click(function(){
		var result = $.formValidator.pageIsValid(1);// 手动调用验证框架进行验证
		if(!result){
			return;
		}
		$('#submitTip').html('<span class="onLoad">正在登录...</span>');
		$('#loginForm').submit();
	});
	
	$('#loginForm').ajaxForm({
		dataType: 'json',
        success: function(data) {
   			if(data.status == "success"){
   				window.parent.loadLoginStatus();
   				//登录成功执行回调
   				var method = $.dialog.data('method');
				var param = $.dialog.data('param');
   				if(method){
   					if(param){
   						method(param);
   					}else{
   						method();
   					}
   					$.dialog.close();
   				}else if($('#refererPage').val().length>0){
   					window.location.href = $('#refererPage').val();
   				}
   			}else{
   				$('#submitTip').html('<span class="onError">' + data.message + '</span>');
   				// 未完成第2步，则跳转至第2步
   				if(data.message == "300"){
					$.dialog.alert('您的注册信息不完整');   				
   				// 邮箱未激活，跳转至邮箱激活页
   				}else if(data.message == "400"){
   					$.dialog.alert('您的邮箱尚未激活');   
   				}else{
   					$('#submitTip').html('<span class="onError">' + data.message + '</span>');
   				}
   			}
        }
	});
	
});