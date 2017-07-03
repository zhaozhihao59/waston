var submitWay="";
$(document).ready(function(){
	initFormValidator();
	ajaxForm();
});


function initFormValidator(){
	$.formValidator.initConfig({theme:"TocerDesign",validatorGroup:"1",onError:function(msg,obj,errorlist){
		var tip = '';
		for(var i=0;i<errorlist.length;i++){
			tip += (i+1)+'、'+errorlist[i]+"<br/>"
		}
		$.dialog.tips(tip);
	}});
	$("#email").formValidator({validatorGroup:"1",onText:'请输邮箱地址',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"邮箱地址不能为空",onErrorMax:"邮箱长度过长"})
	.regexValidator({regExp:"email",dataType:"enum",onError:"邮箱号格式不正确"})
	.ajaxValidator({
		type:"post",
		url:"front/activityEnroll/check_email.htm",
		dataType:"json",
		data:{
			"condition.activityId":function (){
				return $("#activityId").val();
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
		onError : "该邮箱已注册过",
		onWait : "正在校验，请稍候..."
		
	});
	$("#name").formValidator({validatorGroup:"1",onText:'请输入姓名',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"姓名不能为空",onErrorMax:"姓名长度过长"});
	$("#mobile").formValidator({validatorGroup:"1",onText:'请输手机号',onShow:'',onCorrect:''}).inputValidator({min:11,max:11,onErrorMin:"手机号是11位",onErrorMax:"手机号是11位"})
	.regexValidator({regExp:"mobile",dataType:"enum",onError:"手机号格式不正确"});
	/*$("#positionName").formValidator({validatorGroup:"1",onText:'请输入职务',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"职务不能为空",onErrorMax:"职务长度过长"});
	$("#companyName").formValidator({validatorGroup:"1",onText:'请输入公司名称',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"公司名称不能为空",onErrorMax:"公司名称长度过长"});*/
	
}

function ajaxForm(){
	$("#registerForm").ajaxForm({
		dataType:'json',
		success: function(data) {
        	if(data.status == 'success'){
        			if(submitWay =="bm-btn-tongxingren"){
        				$.dialog.alert("提交成功，请填写同行人信息");
        				$("#email").val("");
        				$("#name").val("");
        				$("#mobile").val("");
        				$("#positionName").val("");
        				$("#tel").val("");
        				$(".hmain").html("填写同行人信息");
        			}else{
            			var inputArr = document.getElementsByTagName("input");
            			for(i=0;i<inputArr.length-1;i++)
            			{
            				inputArr[i].value="";
            			}
            			$("#tel").val("");
            			$(".qd-success-box").fadeIn("slow");
        			}
        	}else{
        		$.dialog.tips(data.message);
        	}
        }
	});
}

$("#bm-btn").click(function(){
	var result = $.formValidator.pageIsValid(1);
	if(!result){
		return false;
	}
	submitWay  ="bm-btn";
	$("#registerForm").attr("action","front/activityEnroll/regeister_now.htm");
	$("#registerForm").submit();
	$(".and-showbox2").fadeOut("slow");
	});
$("#bm-btn-tongxingren").click(function(){
	var result = $.formValidator.pageIsValid(1);
	if(!result){
		return false;
	}
	submitWay="bm-btn-tongxingren";
	$("#registerForm").attr("action","front/activityEnroll/regeister_now.htm");
	$("#registerForm").submit();
});
