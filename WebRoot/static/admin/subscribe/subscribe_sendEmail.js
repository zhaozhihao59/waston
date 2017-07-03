var editor;var sendRows = [];var sendRows1 = [];
$(document).ready(function(){
	editor = UE.getEditor('artContent');
	 // 构造角色
    addRow(roleJSON);
});

function saveSubscribe(){
	/*alert(editor.setContent() );
	return;*/
	var receiver = "";
	for ( var i = 0; i < sendRows.length; i++) {
		receiver = receiver+sendRows[i]+","
	}
	receiver= receiver.substring(0,receiver.length-1);
	$("#sendRows").val(receiver);
	editor.sync();
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


$('#subscriptForm').ajaxForm({
	dataType:'json',
	success:function(data){
		$("body").unmask();
		if(data.status == 'success'){
			var viewId="mainIframe_tabli_6a58d3bbe87a11e4ae9b00266c0e7760";
			var win = top.document.frames? top.document.frames(viewId): top.document.getElementById(viewId).contentWindow;
			win.reloadGrid();
			$("#" + viewId, parent.document).click();
			parent.closeCurrentWin();

		}else{
		 	$.dialog({ content: '保存失败',okVal:"确定",ok: function(){}});
		}
	}
});

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
			var title ="<h3>"+row.name+"</h3><br/>";
			var content =row.content;
			var liHtml =title+content;
			editor.setContent(editor.getContent()+liHtml);
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