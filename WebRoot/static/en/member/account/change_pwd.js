
	$(document).ready(function() {
		// 登录表单验证
		$.formValidator.initConfig({theme:"TocerDesign",validatorGroup:"1",onError:function(msg,obj,errorlist){}});
		$("#passworded").formValidator({validatorGroup:"1",onFocus:'Please enter the original password'}).inputValidator({min:1,max:16,onErrorMin: "Please enter the original password"});
		$("#password").formValidator({validatorGroup:"1",onFocus:'Password is required, 6-16, case-insensitive'}).inputValidator({min:6,max:16,onErrorMin: "Password is required, 6-16, case-insensitive",onErrorMax: "Password is not greater than 16, case-insensitive"});
		$("#rePassword").formValidator({validatorGroup:"1",onFocus:'Please enter the password again, 6-16, case-insensitive'}).inputValidator({min:6,max:16,onErrorMin: "Please enter the password again, 6-16, case-insensitive",onErrorMax: "Password is not greater than 16, case-insensitive"}).compareValidator({desID:'password',onError:'Enter the new password twice inconsistent! '});;
		passwordForm();
		
		// 重写密码处敲击回车
		$("#rePassword").live("keydown",function(e){
			if(e.keyCode == '13'){
				$('#passwordBtn').click();
				return false;
			}
		});
		// 登录按钮
		$('#passwordBtn').click(function(){
			doPassword();
		});
	});

	
	/**
	 * 
	 * 提交表单设置
	 */
	function passwordForm(){
		$("#passwordForm").ajaxForm({
			dataType:'json',
			success: function(data) {
	        	$("body",document).unmask();
	        	if(data.status == 'success'){
	        		//success();
	        		$.dialog.tips("SUCCESS!!");
					window.location.href = base + "en/member/account/change_pwd.htm?m="+Math.random();
	        	}else{
	        		$.dialog.tips(data.message);
	        		$('#submitTip').hide();
	        		$("#passworded").val("");
	        		$("#password").val("");
	        		$("#rePassword").val("");
	        	}
	        }
		});
	}
	
	 
	
	// 修改密码提交
	function doPassword(){
		var result = $.formValidator.pageIsValid(1);// 手动调用验证框架进行验证
		if(!result){
			return;
		}
		$("#passwordForm").submit();
		
	}
	 
	 