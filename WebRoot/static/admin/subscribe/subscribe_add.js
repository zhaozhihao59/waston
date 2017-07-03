var sendRows = [];var sendRows1 = [];
$(document).ready(function(){
	initFormValidator();
	ajaxForm();
	
	  // 构造角色
    addRow(roleJSON);
});

function initFormValidator(){
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#name").formValidator({validatorGroup:"1",onText:'请输入姓名',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"姓名不能为空",onErrorMax:"姓名长度过长"});
	$("#mobile").formValidator({validatorGroup:"1",onText:'请输手机号',onShow:'',onCorrect:''}).inputValidator({min:11,max:11,onErrorMin:"手机号是11位",onErrorMax:"手机号是11位"})
	.regexValidator({regExp:"mobile",dataType:"enum",onError:"手机号格式不正确"});
	$("#email").formValidator({validatorGroup:"1",onText:'请输邮箱地址',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"邮箱地址不能为空",onErrorMax:"邮箱长度过长"})
	.regexValidator({regExp:"email",dataType:"enum",onError:"邮箱号格式不正确"});
	$("#companyName").formValidator({validatorGroup:"1",onText:'请输入公司名称',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"公司名称不能为空",onErrorMax:"公司名称长度过长"});
}


function saveSubscribe(){
	var result = $.formValidator.pageIsValid("1");
	if(!result){
		return false;
	}
	$("body").mask("保存中，请稍后...");
	$("#subscriptForm").submit();
}

function chooseSender(){
	$.dialog.open(basePath+"admin/subscribe/index_qikan_select.htm",{
		title:'选择人员',
		width:'1050px',
		height:'550px',
		lock:true
	});
}

function chooseArticle(){
	$.dialog.open(basePath+"admin/cms/article/index_qikan.htm",{
		title:'选择文章',
		width:'1050px',
		height:'550px',
		lock:true
	});
}


function ajaxForm(){
	$('#subscriptForm').ajaxForm({
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
			var liHtml = '<div class="choose-main-li fl mr15">'+row.email+'<a class="ml5" onclick="delRow(this,'+row.id+');return false;" href="javascript:void(0);">x</a></div>';
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


/** 新增角色 */
function addRow1(rows){
	if(!rows||rows.length == 0){
		return;
	}
	// 隐藏掉提示文字
	$('#notRow1').hide();
	for(var i=0; i<rows.length; i++){
		var row = rows[i];
		// 以下操作去除重复
		var isRepeat = false;
		for(var j=0; j<sendRows1.length; j++){
			var userId = sendRows1[j];
			if(userId == row.id){
				isRepeat = true;
				break;
			}
		}
		// 没有与原来重复的，则加入列表中
		if(!isRepeat){
			var liHtml = 
				'<div class="choose-main-li fl mr15">'+row.name+row.content+'</div>';
				$('#artContent').html(liHtml);
			sendRows1.push(row.id);
		}
	}
}

/** 删除角色 */	
function delRow1(e,id){
	$(e).parent('div').remove();
	if(sendRows1 && sendRows1.length > 0){
		for(var j=0;j<sendRows1.length;j++){
			var row = sendRows1[j];
			if(row == id){
				//找到ID
				sendRows1.splice(j,1);
				break;
			}
		}
	}
	if(!$('#rows1').children().has('.fl')){
		$('#notRow1').show();
	}
}

function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}