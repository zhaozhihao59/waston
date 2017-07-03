$(document).ready(function() {
	// 验证码处敲击回车
	$("#rePassword").live("keydown",function(e){
		if(e.keyCode == '13'){
			$("#registerBtn").click();
			return false;
		}
	});
	
 
	$("#password").val('');
	$("#rePassword").val('');
	$.formValidator.initConfig({theme:"TocerDesign",validatorGroup:1});
	$("#email").formValidator({onShow:'',onFocus:'Email required',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"Email required",onErrorMax:"E-mail for long"})
	.regexValidator({regExp:'email',dataType:"enum",onError:"E-mail format is not correct"})
	.ajaxValidator({
		dataType:'json',
		async:true,
		url:'en/register/checkEmail.htm',
		data:{'email':function(){
			return $("#email").val();
		}},
		success:function(data){
			if(data.message == '200'){
				return true;
			}else{
				return false;
			}
		},
		buttons:$("#registerBtn"),
		error:function(jqXHR, textStatus, errorThrown){
			//$.dialog.alert("服务器没有返回数据，可能服务器忙，请重试");
		},
		onError:"This email already exists",
		onWait:"Are checking, please wait..."
	});
	$("#password").formValidator({onFocus:'Numbers and letters any 6-16 combinations, difference between case'}).inputValidator({min:6,max:16,onErrorMin: "数字、字母任意6-16位组合，区别大小写",onErrorMax: "The password is not greater than 16"});
	$("#rePassword").formValidator({onFocus:'Numbers and letters any 6-16 combinations, difference between case'}).inputValidator({min:6,max:16,onErrorMin: "Numbers and letters any 6-16 combinations, difference between case"}).compareValidator({desID:'password',onError:'The two passwords you typed do not match.'});
	$("#name").formValidator({onShow:'',onFocus:'Name required',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"Name required",onErrorMax:"Name is too long"});

	
	$("#registerBtn").live("click",function(){
		var result = $.formValidator.pageIsValid(1);
		if(!result){
			return false;
		}
	  	$("#registerForm").submit();
	});
	
	$('#registerForm').ajaxForm({
		dataType: 'json',
	    success: function(data) {
				if(data.status == "success"){
					var preUrl = data.message;
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
	
});



function sumbieAddUser(){
	if($("#preaded").attr("checked") == false){
		$.dialog.tips("Please check the user registration agreement");
		return;
	} 
}
