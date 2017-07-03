 $(document).ready(function() {
	// 登录表单验证
	$.formValidator.initConfig({theme:"TocerDesign",validatorGroup:"1",onError:function(msg,obj,errorlist){}});
	$("#password").formValidator({onFocus:'密码必填，6-16位，区分大小写'}).inputValidator({min:6,max:16,onErrorMin: "密码必填，6-16位，区分大小写",onErrorMax: "密码不能大于16位，区分大小写"});
	$("#rePassword").formValidator({onFocus:'请再次输入密码，6-16位，区分大小写'}).inputValidator({min:6,max:16,onErrorMin: "请再次输入密码，6-16位，区分大小写"}).compareValidator({desID:'password',onError:'两次密码输入不一致，区分大小写'});
	
	// 重写密码处敲击回车
	$("#rePassword").live("keydown",function(e){
		if(e.keyCode == '13'){
			$('#submitBtn').click();
			return false;
		}
	});
	// 登录按钮
	$('#submitBtn').click(function(){
		doPassword();
	});
	
});
 
//注册
function doPassword(){
	var result = $.formValidator.pageIsValid(1);// 手动调用验证框架进行验证
	if(!result){
		return;
	}
	$('#passwordForm').ajaxSubmit(function(data){
		if(data.status == "success"){
			window.location.href=base+"login/forget_success.htm";
		}else if(data.status == "error"){
			$.dialog.alert(data.message);
		}else{
			$.dialog.alert(data.message);
		}
	});
}
 
 
