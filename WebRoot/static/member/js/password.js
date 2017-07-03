$(document).ready(function(){
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#oldPassword").formValidator({onShow:'',onFocus:'',onCorrect:''})
		.inputValidator({min:8,max:16,onErrorMin:"当前密码不能小于8位",onErrorMax:"当前密码不能大于16位"})
		.ajaxValidator({
			dataType:'html',
			async:true,
			url:'register/checkPasswordExist.htm',
			data:{'password':function(){
				return $("#oldPassword").val();
			},'memberId':function(){
				return $("#memberId").val();
			}},
			success:function(data){
				if(data=='true'){
					return true;
				}else{
					return false;
				}
			},
			buttons:$("#registerBtn"),
			error:function(jqXHR, textStatus, errorThrown){
				$.dialog.alert("服务器没有返回数据，可能服务器忙，请重试");
			},
			onError:"密码填写不正确",
			onWait:"正在校验，请稍候..."
		});
	$("#newPassword").formValidator({onShow:"",onFocus:"请输入新密码长度为8-16位",onCorrect:""}).inputValidator({min:8,max:16,onErrorMin:"密码长度为8-16位",onErrorMax:"密码长度为8-16位"});
	$("#confirmPassword").formValidator({onShow:"",onFocus:"请输入确认密码",onCorrect:""})
		.inputValidator({min:4,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"})
		.compareValidator({desID:"newPassword",operateor:"=",onError:"两次密码输入不一致,请重新输入"});
	
});
// 提交修改密码
function submitUpdPassword(){
	var result = $.formValidator.pageIsValid(1);// 手动调用验证框架进行验证
		if(!result){
		return;
	}
	var memberId = $('#memberId').val();
	memberId = $.trim(memberId);
	var password = $('#newPassword').val();
	password = $.trim(password);
	$.post(basePath+'m/sec/updPassword.htm',{'password':password,'memberId':memberId},function(data){
		if(data == "true"){
			alert('修改成功');
    		window.location.href = basePath + "m/acc/index.htm";
		}else{
			alert("密码修改失败");
		}
	});
}