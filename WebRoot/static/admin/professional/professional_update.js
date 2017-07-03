$(document).ready(function(){
	initFormValidator();
	ajaxForm();
});

function initFormValidator(){
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#name").formValidator({validatorGroup:"1",onText:'请输入专业人员姓名',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"专业人员姓名不能为空",onErrorMax:"专业人员姓名长度过长"});
	$("#desc").formValidator({validatorGroup:"1",onText:'请输入专业人员简介',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"专业人员简介不能为空",onErrorMax:"专业人员简介长度过长"});
	$("#qualification").formValidator({validatorGroup:"1",onText:'请输入资格类型',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"资格类型不能为空",onErrorMax:"资格类型长度过长"});
	$("#language").formValidator({validatorGroup:"1",onText:'请输入工作语言',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"工作语言不能为空",onErrorMax:"工作语言长度过长"});
	$("#type").formValidator({validatorGroup:"1",onText:'请输入专业人员类别',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"专业人员类别不能为空",onErrorMax:"专业人员类别长度过长"});
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