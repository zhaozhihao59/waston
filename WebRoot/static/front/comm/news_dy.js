$(document).ready(function() {
	$("#email").blur(function(){
		checkemail();
	});
});

function ajaxForm(){
	$("#saveForm").ajaxForm({
		dataType:'json',
		success: function(data) {
        	if(data.status == 'success'){
        		$.dialog.alert("订阅成功");
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
    		$.dialog.alert("订阅成功");
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
     	$.dialog.alert("邮箱格式不正确，请检查!");
         return false;
     }
    
  	if(id == "请选择期刊类型"){
  		$.dialog.alert("请选择期刊类型！");
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
 					$.dialog.alert("当前邮箱已经订阅该类型的期刊");
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
		$.dialog.alert("请选择期刊类型！");
		return;
	}
	var email = $("#email");
	if (email.val() == "") {
		$.dialog.alert("邮箱不能为空，请输入邮箱！");
//    	email.focus();
    	return;
	}
    var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
    if(!search_str.test(email.val())){       
    	$.dialog.alert("邮箱格式不正确，请检查!");
       // $.dialog.alert("邮箱格式不正确，请检查~!");
        $('#email').focus();
        return false;
    }
    var name = $("#name");
    if (name.val() == "") {
    	$.dialog.alert("姓名不能为空，请输入姓名！");
    	name.focus();
    	return;
    }
    var mobile = $("#mobile");
    if(mobile.val() == ""){
    	$.dialog.alert("请输入手机号码！");
		mobile.focus();
		return;
	}
    
	if (!mobile.val().match("^1[34578]\\d{9}$")) {
		$.dialog.alert("手机号码格式不正确！");
		mobile.focus();
		return false;
		} 
	var companyName = $("#companyname");
    if (companyName.val() == "") {
    	$.dialog.alert("公司名称不能为空，请输入公司名称！");
    	companyName.focus();
    	return;
    }
	var position = $("#position");
    if (position.val() == "") {
    	$.dialog.alert("职务名称不能为空，请输入职务名称！");
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
					$.dialog.alert("当前邮箱已经订阅改类型的期刊");
					return false;
				}
			}
			
	});
   
}







