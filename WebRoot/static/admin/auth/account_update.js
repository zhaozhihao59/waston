var sendRows = [];
$(document).ready(function(){
	
	$('#birthday').datepicker({
		maxDate:0,
		changeMonth: true,
		changeYear: true,
		defaultDate:"-24y",
		onClose: function(selectedDate){
			$(this).css('color','#333');
		}
	});
	
	//验证框架信息
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#account").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"用户帐号必填",onErrorMax:"用户帐号长度过长"});
	$("#password").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"初始密码必填",onErrorMax:"初始密码长度过长"});
	$("#name").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"用户姓名必填",onErrorMax:"用户姓名长度过长"});
	$("#staffId").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:0,max:255,onErrorMax:"用户编号长度过长"});
	$("#birthday").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:0,max:255,onErrorMax:"生日长度过长"});
	$("#email").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:0,max:255,onErrorMax:"电子邮箱长度过长"});
	$("#mobile").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:0,max:255,onErrorMax:"手机号码长度过长"});
	$("#tel").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:0,max:255,onErrorMax:"固定电话长度过长"});
	
	$('#toUpdatePwd').live('click',function(){
		$('#updatePwd').slideToggle("fast");
		var result2 = $.formValidator.pageIsValid(2);
	});
	
	
	//异步表单提交设置
    $('#roleForm').ajaxForm({
        dataType: 'json',
        success: function(data) {
        	if(data.status = 'success'){
        		$.dialog.alert('保存成功');
        	}else if(data.status = 'error'){
        		$.dialog.alert('保存失败');
        	}
        }
    });
    
    // 构造角色
    addRow(roleJSON);
    
});


/**
 * 更新角色
 */
function updateUser(){
	var result = $.formValidator.pageIsValid(1);	//手动调用验证框架进行验证
	if(!result){
		return;
	}
	// 加入角色信息
	$('#sendRows').val(sendRows);
	$('#account').removeAttr('disabled');
	$('#roleForm').attr("action","admin/auth/user/updateUser.htm");
	$('#roleForm').submit();
}
/**
 * 重置密码
 */
function resetPwd(){
	var pwd = $('#resetPwd').val();
	var userId = $('#itemId').val();
	$.post('admin/auth/user/resetPwd.htm',{'item.password':pwd,'item.id':userId,'item.isChangePwd':1},function(data){
		if(data.status = "success"){
			$.dialog.alert('保存成功');
			window.location.reload();
		}else{
			$.dialog.alert(data.msg);
		}
	})
}

/** 关闭当前页 */
function closeCurrentTab(event){
	var current = $("#menubar_tabs",parent.document).find("a[class='currenttab']")[0];
	var id = current.id;
	id = id.substring(6,id.length);
	parent.closeTab(id,event);
}
/** 新增角色 */
function addRow(rows){
	if(rows.length == 0){
		return;
	}
	// 隐藏掉提示文字
	$('#notRow').hide();
	for(var i=0; i<rows.length; i++){
		var row = rows[i];
		// 以下操作去除重复
		var isRepeat = false;
		for(var j=0; j<sendRows.length; j++){
			var userId = sendRows[j].id;
			if(userId == row.id){
				isRepeat = true;
				break;
			}
		}
		// 没有与原来重复的，则加入列表中
		if(!isRepeat){
			var liHtml = '<div class="choose-main-li fl ml15">'+row.name+'</div>';
			$('#rows').find('.cb').before(liHtml);
			sendRows.push(row.id);
		}
	}
}

