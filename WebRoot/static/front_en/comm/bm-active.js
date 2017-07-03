//提交标识
var submitWay="";
$(document).ready(function(){
	initFormValidator();
	ajaxForm();
});


function initFormValidator(){
	$.formValidator.initConfig({theme:"TocerDesign",validatorGroup:"1",onError:function(msg,obj,errorlist){}});
	$("#email").formValidator({validatorGroup:"1",onText:'请输邮箱地址',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"邮箱地址不能为空",onErrorMax:"邮箱长度过长"})
	.regexValidator({regExp:"email",dataType:"enum",onError:"邮箱号格式不正确"})
	.ajaxValidator({
		type:"post",
		url:"front/activityEnroll/check_email.htm",
		dataType:"json",
		data:{
			"condition.activityId":function (){
			`	return $("#activityId").val();
			},
			"condition.email":function(){
				return $("#email").val();
			}
		},
		async:true,
		success:function(data){
			if(data.message == '200'){
				return true;
			}else{
				return false;
			}
		},
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "邮箱已存在。",
		onWait : "正在校验，请稍候..."
		
	});
	$("#name").formValidator({validatorGroup:"1",onText:'请输入姓名',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"姓名不能为空",onErrorMax:"姓名长度过长"});
	$("#mobile").formValidator({validatorGroup:"1",onText:'请输手机号',onShow:'',onCorrect:''}).inputValidator({min:11,max:11,onErrorMin:"手机号是11位",onErrorMax:"手机号是11位"})
	.regexValidator({regExp:"mobile",dataType:"enum",onError:"手机号格式不正确"})
	.ajaxValidator({
		type:"post",
		url:"front/activityEnroll/check_mobile.htm",
		dataType:"json",
		data:{
			"condition.activityId":function (){
				return $("#activityId").val();
			},
			"condition.mobile":function(){
				return $("#mobile").val();
			}
		},
		async:true,
		success:function(data){
			if(data.message == '200'){
				return true;
			}else{
				return false;
			}
		},
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "手机号已注册。",
		onWait : "正在校验，请稍候..."
	});
//	$("#positionName").formValidator({validatorGroup:"1",onText:'请输入职务',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"职务不能为空",onErrorMax:"职务长度过长"});
	$("#companyName").formValidator({validatorGroup:"1",onText:'请输入公司名称',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"公司名称不能为空",onErrorMax:"公司名称长度过长"});
	
}

function ajaxForm(){
	$("#regeisterForm").ajaxForm({
		dataType:'json',
		success: function(data) {
        	if(data.status == 'success'){
        		
        		//说明是同行人
        		if(submitWay=="regeisterTongxingNow"){
        			$.dialog.alert("提交成功，请填写同行人信息");
        			$("#email").val("");
        			$("#name").val("");
        			$("#mobile").val("");
        			$("#positionName").val("");
        			$("#tel").val("");
        			$("#titleH3").html("添加同行人");
        		}else{
        			$('#massageDiv').hide();
        			$('.msg-box').hide();
        			$("body").removeClass("modal-active");
        			$.dialog.alert("提交成功");
        			 var inputArr = document.getElementsByTagName("input");
          			for(i=0;i<inputArr.length-1;i++)
          			{
          				inputArr[i].value="";
          			}
          			location.reload();
          			$("#tel").val("");
        		}
        }
		}
	});
}

//保存并提交
function regeisterNow(){
	$("#mobile").focus();
	$("#email").focus();
	//var result = $.formValidator.pageIsValid(1);
	//if(!result){
	//	return false;
	///}
	submitWay="regeisterNow";
	$("#regeisterForm").attr("action","front/activityEnroll/regeister_now.htm");
	$("#regeisterForm").submit();
}
//提交并添加同行人
function regeisterTongxingNow(){
	var result = $.formValidator.pageIsValid(1);
	if(!result){
		return false;
	}
	submitWay="regeisterTongxingNow";
	$("#regeisterForm").attr("action","front/activityEnroll/regeister_now.htm");
	$("#regeisterForm").submit();
}
