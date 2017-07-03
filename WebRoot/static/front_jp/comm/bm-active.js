//提交标识
var submitWay="";
$(document).ready(function(){
	initFormValidator();
	ajaxForm();
});


function initFormValidator(){
	$.formValidator.initConfig({theme:"TocerDesign",validatorGroup:"1",onError:function(msg,obj,errorlist){}});
	$("#email").formValidator({validatorGroup:"1",onText:'入力してくださいメールアドレス',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"メールアドレスをブランクにすることはできませんので、入力してください。",onErrorMax:"電子メールが長すぎます"})
	.regexValidator({regExp:"email",dataType:"enum",onError:"メールボックス番号の形式が正しくありません"})
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
		error: function(jqXHR, textStatus, errorThrown){alert("サーバーからデータが未返送で、サーバーがビジー状態になった可能性があるため、もう一度やり直してください。"+errorThrown);},
		onError : "メールボックスがすでに存在しています",
		onWait : "検証、お待ちください..."
		
	});
	$("#name").formValidator({validatorGroup:"1",onText:'あなたの名前を入力してください',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"名前は空にすることはできません",onErrorMax:"名前が長すぎます"});
	$("#mobile").formValidator({validatorGroup:"1",onText:'電話番号を入力してください',onShow:'',onCorrect:''}).inputValidator({min:11,max:11,onErrorMin:"電話番号は11であります",onErrorMax:"電話番号は11であります"})
	.regexValidator({regExp:"mobile",dataType:"enum",onError:"電話番号の形式が正しくありません。"})
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
		error: function(jqXHR, textStatus, errorThrown){alert("サーバーからデータが未返送で、サーバーがビジー状態になった可能性があるため、もう一度やり直してください。"+errorThrown);},
		onError : " 既に登録されている携帯番号です。",
		onWait : "確認中です。しばらくお待ちください..."
	});
//	$("#positionName").formValidator({validatorGroup:"1",onText:'请输入职务',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"职务不能为空",onErrorMax:"职务长度过长"});
	$("#companyName").formValidator({validatorGroup:"1",onText:'会社名をブランクにすることできませんので、入力してください！',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"会社名をブランクにすることできませんので、入力してください！",onErrorMax:"会社名が長すぎます"});
	
}

function ajaxForm(){
	$("#regeisterForm").ajaxForm({
		dataType:'json',
		success: function(data) {
        	if(data.status == 'success'){
        		
        		//说明是同行人
        		if(submitWay=="regeisterTongxingNow"){
        			$.dialog.alert(" 確認できましたので、同行者の情報を入力してください。");
        			$("#email").val("");
        			$("#name").val("");
        			$("#mobile").val("");
        			$("#positionName").val("");
        			$("#tel").val("");
        			$("#titleH3").html("同行者を追加する");
        		}else{
        			$('#massageDiv').hide();
        			$('.msg-box').hide();
        			$("body").removeClass("modal-active");
        			$.dialog.alert(" 確認できましたので");
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
