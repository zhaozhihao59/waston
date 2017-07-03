$(document).ready(function(){
	initFormValidator();
	ajaxForm();
});

function initFormValidator(){
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#activityName").formValidator({validatorGroup:"1",onText:'请输入活动名称',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"活动名称不能为空",onErrorMax:"活动名称长度过长"});
	$("#name").formValidator({validatorGroup:"1",onText:'请输入姓名',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"姓名不能为空",onErrorMax:"姓名长度过长"});
	$("#mobile").formValidator({validatorGroup:"1",onText:'请输手机号',onShow:'',onCorrect:''}).inputValidator({min:11,max:11,onErrorMin:"手机号是11位",onErrorMax:"手机号是11位"})
	.regexValidator({regExp:"mobile",dataType:"enum",onError:"手机号格式不正确"});
	$("#email").formValidator({validatorGroup:"1",onText:'请输邮箱地址',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"邮箱地址不能为空",onErrorMax:"邮箱长度过长"})
	.regexValidator({regExp:"email",dataType:"enum",onError:"邮箱号格式不正确"});
}


function updateActivity(){
	$("body").mask("保存中，请稍后...");
	$("#activityForm").submit();
}
function ajaxForm(){
	$('#activityForm').ajaxForm({
		dataType:'json',
		success:function(data){
			$("body").unmask();
			if(data.status == 'success'){
				var win = $.dialog.open.origin;
				win.reloadGrid();
				$.dialog.close();
			}else{
			 	$.dialog({ content: '保存失败',okVal:"确定",ok: function(){}});
			}
		}
	});
}
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}