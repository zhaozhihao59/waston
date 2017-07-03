$(document).ready(function() {
	$("#email").blur(function(){
		checkemail();
	});
});

function ajaxForm(){
	$("#saveForm").ajaxForm({
		dataType:'json',
		success: function(data) {
        //$("body",parent.document).unmask();
        	if(data.status == 'success'){
        		$.dialog.alert("Successful sign-up");
        	}else{
        		$.dialog.tips(data.message);
        	}
        }
	});
}
$("#saveForm").ajaxForm({
	dataType:'json',
	success: function(data) {
    	$("body",parent.document).unmask();
    	if(data.status == 'success'){
    		$.dialog.alert("Successful sign-up");
    	}else{
    		$.dialog.tips(data.message);
    	}
    }
});
//验证邮箱是否订阅
function checkemail(){
	 var postData={};
     var email = $("#email").val();
     var channelId = $("#channelId").val();
     var id = $("#channelId").val();
     //1.
     var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
     if(!search_str.test(email)){       
     	$.dialog.alert("メールアドレスの形式が正しくありません。チェックしてください…");
//         $('#email').focus();
         return false;
     }
    
  	if(id == "Please select type of periodical…"){
  		$.dialog.alert("Please select type of periodical…");
  		return false;
  	}
     postData["condition.email"] = email;
     postData["condition.channelId"] = channelId;
 	$.ajax({
 		    type :'post',
 			url : 'front/subscribe/check_email.htm',
 			data : postData,
 			dataType:'json',
 		    async:false,
 			success : function(data) {
 				var result = data;
 				if(result.message=="200"){
 				}else{
 					$.dialog.alert("メールアドレスの形式が正しくありません。チェックしてください");
 					return false;
 				}
 			},
 			error : function() {
 				//错误
 			}
 			
 	});
}
//保存
function doSubmit(){
	var id = $("#channelId");
	if(id.val() == "请选择期刊类型"){
		$.dialog.alert("定期刊行物を選んでほしいと手に入れるタイプです");
		return;
	}
	var email = $("#email");
	if (email.val() == "") {
		$.dialog.alert("メールアドレスをブランクにすることはできません。");
    	email.focus();
    	return;
	}
    var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
    if(!search_str.test(email.val())){       
    	$.dialog.alert("メールアドレスの形式が正しくありません。チェックしてください…");
//        $('#email').focus();
        return false;
    }
    var name = $("#name");
    if (name.val() == "") {
    	$.dialog.alert("名前を入力してください");
    	name.focus();
    	return;
    }
    var mobile = $("#mobile");
    if(mobile.val() == ""){
    	$.dialog.alert("あなたの携帯电话番号を入力してください");
		mobile.focus();
		return;
	}
    
	if (!mobile.val().match("^1[34578]\\d{9}$")) {
		$.dialog.alert("携帯電話番号の形式に誤りがあります…");
		mobile.focus();
		return false;
		} 
	var companyName = $("#companyname");
    if (companyName.val() == "") {
    	$.dialog.alert("会社名をブランクにすることできませんので、入力してください！");
    	companyName.focus();
    	return;
    }
	var position = $("#position");
    if (position.val() == "") {
    	$.dialog.alert("職務をブランクすることはできませんので、入力ください！");
    	position.focus();
    	return;
    }
    var tf = 0;
    var postData={};
    var email = $("#email").val();
    var channelId = $("#channelId").val();
    postData["condition.email"] = email;
    postData["condition.channelId"] = channelId;
	$.ajax({
		    type :'post',
			url : 'front/subscribe/check_email.htm',
			data : postData,
			dataType:'json',
		    async:false,
			success : function(data) {
				var result = data;
				if(result.message=="200"){
					$("#saveForm").attr("action","front/subscribe/doSave.htm");
//					$("body",parent.document).mask("正在订阅中，请稍后...");
					$("#saveForm").submit();
				}else{
					$.dialog.alert("このメールボックスでは既に該当の電子刊物の配信が予約されております。");
					return false;
				}
			}
			
	});
   
}







