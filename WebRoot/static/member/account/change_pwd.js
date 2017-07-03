
	$(document).ready(function() {
		// 登录表单验证
		$.formValidator.initConfig({theme:"TocerDesign",validatorGroup:"1",onError:function(msg,obj,errorlist){}});
		$("#passworded").formValidator({validatorGroup:"1",onFocus:'请输入原始密码'}).inputValidator({min:1,max:16,onErrorMin: "请输入原始密码"});
		$("#password").formValidator({validatorGroup:"1",onFocus:'密码必填，6-16位，区分大小写'}).inputValidator({min:6,max:16,onErrorMin: "密码必填，6-16位，区分大小写",onErrorMax: "密码不能大于16位，区分大小写"});
		$("#rePassword").formValidator({validatorGroup:"1",onFocus:'请再次输入密码，6-16位，区分大小写'}).inputValidator({min:6,max:16,onErrorMin: "请再次输入密码，6-16位，区分大小写",onErrorMax: "密码不能大于16位，区分大小写"}).compareValidator({desID:'password',onError:'两次密码输入不一致，区分大小写'});;
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
	        		$.dialog.tips("密码修改成功!!");
					window.location.href = base + "member/account/change_pwd.htm?m="+Math.random();
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
	 
	 