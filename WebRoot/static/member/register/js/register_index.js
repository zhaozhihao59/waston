$(document).ready(function() {
	// 验证码处敲击回车
	$("#rePassword").live("keydown",function(e){
		if(e.keyCode == '13'){
			$("#registerBtn").click();
			return false;
		}
	});
	
	// 鼠标移开验证密码强度
	$("#password").live('blur',function(){
		checkPwdStrong();
	});
 
	$("#password").val('');
	$("#rePassword").val('');
	$.formValidator.initConfig({theme:"TocerDesign",validatorGroup:1});
	$("#email").formValidator({onShow:'',onFocus:'电子邮箱必填',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"电子邮箱必填",onErrorMax:"电子邮箱长度过长"})
	.regexValidator({regExp:'email',dataType:"enum",onError:"电子邮箱格式不正确"})
	.ajaxValidator({
		dataType:'json',
		async:true,
		url:'register/checkEmail.htm',
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
		onError:"该电子邮箱已存在",
		onWait:"正在校验，请稍候..."
	});
	$("#mobile").formValidator({validatorGroup:"1",onShow:'',onFocus:'手机号码必填',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"手机号码必填",onErrorMax:"长度过长"})
	.regexValidator({regExp:'mobile',dataType:"enum",onError:"手机号码不正确"})
	.ajaxValidator({
		dataType:'text',
		async:true,
		type:'post',
		url:'register/checkMobile.htm',
		data:{'mobile':function(){
			return $("#emobile").val();
		}},
		success:function(data){
			if(data == 'true'){
				return false;
			}else{
				return true;
			}
		},
		buttons:$("#e_registerBtn"),
		error:function(jqXHR, textStatus, errorThrown){
			$.dialog.alert("服务器没有返回数据，可能服务器忙，请重试");
		},
		onError:function(o){
			return "该手机号码已存在";
		},
		onWait:"正在校验，请稍候..."
	});
	$("#password").formValidator({onFocus:'数字、字母任意6-16位组合，区别大小写'}).inputValidator({min:6,max:16,onErrorMin: "数字、字母任意6-16位组合，区别大小写",onErrorMax: "密码不能大于16位"});
	$("#rePassword").formValidator({onFocus:'数字、字母任意6-16位组合，区别大小写'}).inputValidator({min:6,max:16,onErrorMin: "数字、字母任意6-16位组合，区别大小写"}).compareValidator({desID:'password',onError:'两次密码输入不一致'});
	$("#name").formValidator({onShow:'',onFocus:'姓名必填',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"姓名必填",onErrorMax:"长度过长"});

	$(".register-btn").live("click",function(){
		var result = $.formValidator.pageIsValid(1);
		if(!result){
			return false;
		}
	  	$("#saveForm").submit();
	});
	
//	$('#saveForm').ajaxForm({
//		dataType: 'json',
//	    success: function(data) {
//			if(data.status == "success"){
//				var preUrl = data.message;
//				if(preUrl == null || preUrl == ''){
//   				window.location.href = base + "member/account/index.htm?m="+Math.random();
//	   			}else{
//	   				window.location.href = preUrl;
//	   			}
//			}else if(data.status == "error"){
//				$.dialog.tips(data.message);
//			}
//	    }
//	});
	
});

// 密码输入框事件
function checkPwdStrong() {
	var re = $.formValidator.oneIsValid('password');
	if (re.isValid) {
		var password = $.trim($('#password').val());
		var username = $.trim($('#account').val());
		var num = testPassword(password,username);
		if(num < 33){
			$('#pwdWeak').removeClass('select-col');
			$('#pwdNormal').removeClass('select-col');
			$('#pwdStrong').removeClass('select-col');
			$('#pwdWeak').addClass('select-col');
		}else if(num < 67){
			$('#pwdWeak').removeClass('select-col');
			$('#pwdNormal').removeClass('select-col');
			$('#pwdStrong').removeClass('select-col');
			$('#pwdWeak').addClass('select-col');
			$('#pwdNormal').addClass('select-col');
		}else if(num <= 100){
			$('#pwdWeak').removeClass('select-col');
			$('#pwdNormal').removeClass('select-col');
			$('#pwdStrong').removeClass('select-col');
			$('#pwdWeak').addClass('select-col');
			$('#pwdNormal').addClass('select-col');
			$('#pwdStrong').addClass('select-col');
		}
	} else {
		$('#pwdWeak').removeClass('select-col');
		$('#pwdNormal').removeClass('select-col');
		$('#pwdStrong').removeClass('select-col');
	}
}

/**
 * 验证密码强度
 * 
 * @param {} password
 * @return {}
 */
function testPassword(password,username){
    var score = 0;
    if (password.length < 4 ) { return -4; }
    if (typeof(username) != 'undefined' && password.toLowerCase() == username.toLowerCase()){return -2}
    score += password.length * 4;
    score += ( repeat(1,password).length - password.length ) * 1;
    score += ( repeat(2,password).length - password.length ) * 1;
    score += ( repeat(3,password).length - password.length ) * 1;
    score += ( repeat(4,password).length - password.length ) * 1;
    if (password.match(/(.*[0-9].*[0-9].*[0-9])/)){ score += 5;}
    if (password.match(/(.*[!,@,#,$,%,^,&,*,?,_,~].*[!,@,#,$,%,^,&,*,?,_,~])/)){ score += 5 ;}
    if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)){ score += 10;}
    if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)){ score += 15;}
    if (password.match(/([!,@,#,$,%,^,&,*,?,_,~])/) && password.match(/([0-9])/)){ score += 15;}
    if (password.match(/([!,@,#,$,%,^,&,*,?,_,~])/) && password.match(/([a-zA-Z])/)){score += 15;}
    if (password.match(/^\w+$/) || password.match(/^\d+$/) ){ score -= 10;}
    if ( score < 0 ){score = 0;}
    if ( score > 100 ){ score = 100;}
    return score;
     
    function repeat(len,str){
    var res = "";
    for (var i = 0; i < str.length; i++ ){
        var repeated = true;
        for (var j = 0, max = str.length - i - len; j < len && j < max; j++){
            repeated = repeated && (str.charAt(j + i) == str.charAt(j + i + len));
        }
        if (j < len) repeated = false;
        if (repeated) {
            i += len - 1;
            repeated = false;
        }else{
            res += str.charAt(i);
        }
    }
    return res;
    }
}

function sumbieAddUser(){
	if($("#preaded").attr("checked") == false){
		$.dialog.tips("请勾选用户注册协议");
		return;
	} 
}
