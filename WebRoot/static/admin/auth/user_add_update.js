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
	
	$('#updateBankAccountBtn').click(function(){
		$('#updateBankDiv').slideToggle("fast");
	});
	
	$('#doUpdateBank').click(function(){
		 var id = $('#itemId').val();
		 var departId=$('#departId').val();
		 var bankAccount = $('#newBankAccount').val();
		 $.post('admin/auth/user/updateBankAccount.htm?m='+Math.random(),{'item.id':id,'item.bankAccount':bankAccount,'item.departId':departId},function(data){
		 	if(data.success){
		 		$.dialog.alert('修改成功');
		 	}else{
		 		$.dialog.alert('修改失败');
		 	}
		 })
	})
	
	var account = $('#account').val();
	//验证框架信息
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#account").formValidator({validatorGroup:"1",onText:'请输入帐号',onShow:'',onCorrect:''})
	.inputValidator({min:1,max:255,onErrorMin:"账号不能为空",onErrorMax:"账号长度过长"})
	.regexValidator({regExp:"accountCheck",dataType:"enum",onError:"必须是字母或数字,不能是汉字和特殊字符"})
	.ajaxValidator({
		dataType : "json",
		type : 'post',
		async : true,
		url : "admin/auth/user/alreadyExists.htm?m="+Math.random(),
		data : {
			"item.account":function(){
				return $("#account").val();
			},
			"item.id":function(){
				return $("#itemId").val();
			}
		},
		success : function(data){
			return data.success == "true";
		},
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "该账号已经被使用",
		onWait : "正在校验，请稍候..."
	});
	$("#password").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"初始密码必填",onErrorMax:"初始密码长度过长"});
	$("#name").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"用户姓名必填",onErrorMax:"用户姓名长度过长"});
	$("#staffId").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).regexValidator({regExp:"accountCheck",dataType:"enum",onError:"必须是字母和数字,不能是汉字和特殊字符"});
	$("#birthday").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:0,max:255,onErrorMax:"生日长度过长"});
	if($("#email").val()!=null&&$("#email").val()!=""){
		$("#email").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).regexValidator({regExp:"email", dataType:"enum",onError: "邮箱格式不正确" });
	}
	if($("#mobile").val()!=null&&$("#email").val()!=""){
		$("#mobile").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).regexValidator({regExp:"mobile", dataType: "enum", onError: "手机格式不正确,格式：1[3/4/5/7/8]XXXXXXXXX。" });
	}
	if($("#tel").val()!=null&&$("#email").val()!=""){
		$("#tel").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).regexValidator({regExp:"tel" , dataType:"enum",onError:"电话格式不正确"});
	}
	$('#toUpdatePwd').live('click',function(){
		$('#updatePwd').slideToggle("fast");
		var result2 = $.formValidator.pageIsValid(2);
	});
	
	
	//异步表单提交设置
    $('#roleForm').ajaxForm({
        dataType: 'json',
        success: function(data) {
        	if(data.status = 'success'){
        		$.dialog.alert('保存成功',function(){
        			closeCurrentTab();
        		});
        	}else if(data.status = 'error'){
        		$.dialog.alert('保存失败');
        	}
        }
    });
    
    // 构造角色
    addRow(roleJSON);
    
    // 生成Ztree
	var setting = {
		async : {
			enable : true,
			url : treeLoadUrl,
			autoParam : ["id", "name", "isDelete","parentId"]
		},
		callback : {
			onClick : onClickTree,
			onAsyncSuccess : hideTipFN
		},
		view : {
			 nameIsHTML : true
		},
		check : {
			enable : false,
			chkStyle : "checkbox"
		}
	};
	
	$.fn.zTree.init($("#departTree"), setting);
	zTree = $.fn.zTree.getZTreeObj("departTree");
	
	$('#departName').focus(function(){
		var cityObj = $("#departName");
		var cityOffset = $("#departName").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	});
    
});

// 树单击事件
function treeClickFN(event,treeId,treeNode){
	//var nodes = zTree.getSelectedNodes();
	//var win = $.dialog.open.origin;
	//win.test(treeNode.id,treeNode.name);
	//$.dialog.location.reload;
//	$.dialog.close();
	curNode = treeNode;
	//$('#channelChoose').val(treeNode.name);
	//全部查询
	if(curNode.id!=0){
	$("#departId").val(curNode.id);
	}
	searchUserList();
	hideRMenu();
}

function treeLoadUrl(treeId, treeNode) {
	return treeNode == null ? "admin/auth/depart/list_depart.htm" : "admin/auth/depart/list_depart.htm?parentId ="+treeNode.id;
}

function hideTipFN(){
	$("#loadTip").hide();
}

function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}

//zTree点击事件		
function onClickTree(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("departTree");
	var nodes = zTree.getSelectedNodes();
	$('#departName').val(nodes[0].name);
	$("#departId").val(nodes[0].id);
	hideMenu();
}

function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}

function doBankStatus(){
	var id = $('#itemId').val();
	$.post('admin/auth/user/updateBankStatus.htm?m='+Math.random(),{'item.id':id},function(data){
	 	if(data.success){
	 		$.dialog.alert('确认成功');
	 	}else{
	 		$.dialog.alert('确认失败');
	 	}
	 });
}

// 选择角色
function chooseRole(){
	$.dialog.open(basePath + "admin/auth/user/choose_role.htm", {
		title: '选择角色',width:'800px',height:'500px',lock:true
	});
}

/**
 * 保存新角色
 */
function submitForm(){
	var departId=$('#departId').val();
	var result = $.formValidator.pageIsValid(1);	//手动调用验证框架进行验证
	if(!result){
		return;
	}
	
	// 加入角色信息
	$('#sendRows').val(sendRows);
	$('#account').removeAttr('disabled');
	$('#roleForm').attr("action","admin/auth/user/saveUser.htm");
	$('#roleForm').submit();
}

/**
 * 更新角色
 */
function updateUser(){
	$("#account").unFormValidator(true);
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
	$.post('admin/auth/user/resetPwd.htm',{'item.password':pwd,'item.id':userId,'isChangePwd':0},function(data){
		if(data.status = "success"){
			$.dialog.alert('保存成功');
			window.location.reload();
		}else{
			$.dialog.alert(data.msg);
		}
	})
}


/** 关闭当前页 */
function closeCurrentTab(){
var itemId=$("#itemId").val();
var parent_tabli="tabli_25";
var cur_tabli="";
if(itemId!=null&&itemId!=""){
	cur_tabli ="tabli_update_user_"+itemId;
}else{
    cur_tabli = "tabli_add_user";
}
//刷新之前的列表页
try{
    	 var viewId = "mainIframe_"+parent_tabli;
    	 var parentTab = top.document.getElementById(viewId);
    	 var win;
// 退货单页面存在
    	 if (null != parentTab) {
			var win = parentTab.contentWindow;
			win.reloadGrid();
			$("#" + parent_tabli, parent.document).click();
			$("#"+cur_tabli,parent.document).remove();
    	 }
     	}catch(e){
     	}
}
/** 新增角色 */
function addRow(rows){
	if(!rows||rows.length == 0){
		return;
	}
	// 隐藏掉提示文字
	$('#notRow').hide();
	for(var i=0; i<rows.length; i++){
		var row = rows[i];
		// 以下操作去除重复
		var isRepeat = false;
		for(var j=0; j<sendRows.length; j++){
			var userId = sendRows[j];
			if(userId == row.id){
				isRepeat = true;
				break;
			}
		}
		// 没有与原来重复的，则加入列表中
		if(!isRepeat){
			var liHtml = '<div class="choose-main-li fl mr15">'+row.name+'<a class="ml5" onclick="delRow(this,\''+row.id+'\');return false;" href="javascript:void(0);">X</a></div>';
			$('#rows').find('.cb').before(liHtml);
			sendRows.push(row.id);
		}
	}
}

/** 删除角色 */	
function delRow(e,id){
	$(e).parent('div').remove();
	if(sendRows && sendRows.length > 0){
		for(var j=0;j<sendRows.length;j++){
			var row = sendRows[j];
			if(row == id){
				//找到ID
				sendRows.splice(j,1);
				break;
			}
		}
	}
	if(!$('#rows').children().has('.fl')){
		$('#notRow').show();
	}
}
