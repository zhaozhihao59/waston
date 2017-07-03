$(document).ready(function() {
	if( !$('input[name="isRmbPwd"]').attr('checked') ){
		$("#email").val('');
		$("#password").val('');
	}
/*	// 登录表单验证
	$.formValidator.initConfig({theme:"TocerDesign",validatorGroup:"2",onError:function(msg,obj,errorlist){}});
	$("#account").formValidator({validatorGroup:"2",onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"用户名必填",onErrorMax:"用户名长度过长"});
	$("#password").formValidator({validatorGroup:"2",onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:6,max:16,onErrorMin: "密码必填，且不能小于6位",onErrorMax: "密码不能大于16位"});
	*/
	//敲击回车
	$("#password").live("keydown",function(e){
		if(e.keyCode == '13'){
			$('#loginSubmit').click();
			return false;
		}
	});
	
	// 点击换一张验证码图片
	$('#noLook').live('click',function() {
		$("#captchaImage").attr('src', 'japtcha.htm?r=' + Math.random());
	});
	
	// 登录按钮
	$('#loginSubmit').click(doLogin);
	
	$('#loginForm').ajaxForm({
		dataType: 'json',
        success: function(data) {
   			if(data.status == "success"){
   				var preUrl = data.preUrl;
   				if(preUrl == null || preUrl == ''){
	   				window.location.href = base + "en/member/account/index.htm?m="+Math.random();
	   			}else{
	   				window.location.href = preUrl;
	   			}
   			}else if(data.status == "error"){
   				$.dialog.tips(data.message);
   			}
        }
	});
	
	// 如果记住密码，页面渲染完后打勾
	var rp = $('#isRmbPwd').val();
	if(rp == 1){
		$('#isRmbPwd').attr('checked',true);
	}else{
		$('#isRmbPwd').attr('checked',false);
	}
	$('#isRmbPwd').click(function(){
		var c = $(this).attr('checked');
		if(c == true){
			$(this).val(1);
		}else{
			$(this).val(0);
		}
	});
	
});
//登录
function doLogin(){
/*	var result = $.formValidator.pageIsValid(2);// 手动调用验证框架进行验证
		if(!result){
		return;
	}*/
	if($("#email").val()==""){
		$.dialog.tips("Please enter the account number !");
		return;
	}
	if($("#password").val()==""){
		$.dialog.tips("Please enter your password  !");
		return;
	}
 
	$('#loginForm').submit();
}