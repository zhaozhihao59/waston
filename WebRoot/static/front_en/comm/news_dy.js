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
     	$.dialog.alert("Email address format is incorrect. Please check…");
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
 					$.dialog.alert("This periodical has already been subscribed to at the current email address.");
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
		$.dialog.alert("Choose the type of Journals");
		return;
	}
	var email = $("#email");
	if (email.val() == "") {
		$.dialog.alert("Email address cannot be left blank.");
    	email.focus();
    	return;
	}
    var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
    if(!search_str.test(email.val())){       
    	$.dialog.alert("Email address format is incorrect. Please check…");
//        $('#email').focus();
        return false;
    }
    var name = $("#name");
    if (name.val() == "") {
    	$.dialog.alert("Name cannot be left blank. Please enter a name…");
    	name.focus();
    	return;
    }
    var mobile = $("#mobile");
    if(mobile.val() == ""){
    	$.dialog.alert("Your mobile phone number…");
		mobile.focus();
		return;
	}
    
	if (!mobile.val().match("^1[34578]\\d{9}$")) {
		$.dialog.alert("Mobile phone number format is incorrect…");
		mobile.focus();
		return false;
		} 
	var companyName = $("#companyname");
    if (companyName.val() == "") {
    	$.dialog.alert("Company name cannot be left blank. Please enter company name.");
    	companyName.focus();
    	return;
    }
	var position = $("#position");
    if (position.val() == "") {
    	$.dialog.alert("Title cannot be left blank. Please enter title.");
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
					$.dialog.alert("This periodical has already been subscribed to at the current email address.");
					return false;
				}
			}
			
	});
   
}







