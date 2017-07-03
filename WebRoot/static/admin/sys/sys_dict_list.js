$(document).ready(function(){
	var id = $("#globalDict").val();
	$("#table").jqGrid({
		url:basePath+"admin/sys/dict/list.htm?id="+id+"&m="+Math.random(),
		colNames:["操作","排序","名称",""],
		colModel:[
			{name:"id",title:false,width:40,align:"center",sortable:false,formatter:optFormater},
			{name:"sort",title:false,width:30,align:"center",sortable:false,editable:true,editoptions:{size:20}},
			{name:"name",title:false,width:120,align:"left",sortable:false,editable:true,editoptions:{size:50}},
			{name:"blank",width:300}
		],
		sortname:"sort",
		sortorder:"asc",
		caption:"数据项",
		loadComplete: function(data){
			var width = $(window).width()-10;
			$(this).setGridWidth(width);
	   	},
		jsonReader:{id: "id",root:"resultList"}
	});
	$("#table").jqGrid("navGrid","#pagerBar");
	$("#table_name").css("text-align","left");
	
	// 随窗体大小改变而改变Grid宽度
	window.onresize = function _doResize(){
	 	var width = $(window).width()-20;
	 	$("#table").jqGrid('setGridWidth', width);
	}
});

function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var updateBtn = "<a href='javascript:;' id='toUpdateBtn_"+rowObject.id+"' onclick='updateFN(\""+rowObject.id+"\",\""+rowObject.name+"\","+rowObject.type+");return false;'title='修改'>修改</a>";
	btns.push(updateBtn);
	var doUpdateBtn = "<a href='javascript:;' id='doUpdateBtn_"+rowObject.id+"' onclick=\"doUpdate('"+rowObject.id+"');return false;\" style='display:none;' title='保存'>保存</a>";
	btns.push(doUpdateBtn);
	var cancelBtn = "<a href='javascript:;' id='cancelBtn_"+rowObject.id+"' onclick='cancelFN(\""+rowObject.id+"\",\""+rowObject.name+"\","+rowObject.type+");return false;' style='display:none;' title='取消'>取消</a>";
	btns.push(cancelBtn);
	if(rowObject.type != 1){
		var removeBtn = "<a href='javascript:;' id='deleteBtn_"+rowObject.id+"' onclick=\"doDel('"+rowObject.id+"');return false;\" title='删除'>删除</a>";
		btns.push(removeBtn);
	}
	return btns.join(" ");
}

// 添加数据
function toAddDict(){
	$.dialog.open(basePath+'admin/sys/dict/toAdd.htm?m='+Math.random(),{
		title:'新增数据',
		width:'520px',
		height:'200px',
		lock:true
	});
}

// 修改数据
function toUpdateDict(){
	var dictId = $("#globalDict").val();
	$.dialog.open(basePath+'admin/sys/dict/toUpdate.htm?id='+dictId+'&m='+Math.random(),{
		title:'修改数据',
		width:'520px',
		height:'200px',
		lock:true
	});
}

// 删除数据
function delDict(){
	var dictId = $("#globalDict").val();
	$.dialog.confirm("确认要进行删除吗？",function(){
		$.post(basePath+"admin/sys/dict/del.htm?m="+Math.random(),{"selIds":dictId},function(data){
            if (data.status == "success") {
            	reloadDict(data.options);
            }else{
           	 	$.dialog.alert("删除失败");
            }
		});
	});
}

// 改变字典
function changeSelect(dictId){
	var id=null;
	if(dictId && dictId.length > 0){
		id = dictId;
	}else{
		id = $('#globalDict').val();
	}
	var url ='admin/sys/dict/list.htm?id='+id+'&m='+Math.random();
	$("#table").jqGrid("setGridParam", {
	    url: url,    //设置表格的url  
	    datatype: "json"   //设置数据类型                                                    
 	}).trigger("reloadGrid",[{page:1}]);
}

/** 修改操作 */
function updateFN(id,name,type){
  	$("#table").jqGrid('editRow',id);
	toggleBtn(id,type);
}

/** 按钮显示/隐藏切换 */
function toggleBtn(id,type){
	$('#toUpdateBtn_'+id).toggle();
	$('#doUpdateBtn_'+id).toggle();
	$('#cancelBtn_'+id).toggle();
	if(type != 1){
		$('#deleteBtn_'+id).toggle();
	}
}
// 执行修改操作
function doUpdate(id){
	var name = $('#'+id+'_name').val();
	var sort = parseInt($('#'+id+'_sort').val());
	var globalId = $('#globalDict').val();
	if(null == name || name == ""){
		var dialog = $.dialog({
			title:"提交失败",
			content:"名称不能为空!",
			ok:true
		});
		return;
	}
	var reg = /^\d+$/;
	if(!reg.test(sort)){
		var dialog = $.dialog({
			title:"提交失败",
			content:"排序输入错误!",
			ok:true
		});
		return;
	}
	var postData = {
		'sysDictItem.id':id,
		'sysDictItem.name':name,
		'sysDictItem.sort':sort
	}
	$.ajax({
		url:basePath+'admin/sys/dict/doEditSysDictItem.htm?m='+Math.random(),
		type:'post',
		data:postData,
		success:function(data){
	        if (data.status == "success"){
	        	refreshGrid("#table");
	        }else {
	          $.dialog.alert(data.message);
	        }
		},
		error:function(){
	       $.dialog({content: '保存失败!',okVal:"确定",ok: function(){}});
		}
	});
}

function cancelFN(id,name,type){
	$("#table").jqGrid('restoreRow',id);
	toggleBtn(id,type);
}

// 删除操作
function doDel(id){
	$.dialog.confirm("确认要进行批量删除吗？",function(){
		// 所有异步提交，必须都要加上遮罩，为了防止AJAX重复提交
		$("body",parent.document).mask("正在处理，请稍候...");
		$.post(basePath+"admin/sys/dict/batchDelete.htm?m="+Math.random(),{"selIds":id},function(data){
			// 回调函数中去除遮罩
			$("body",parent.document).unmask();
			var msgs = "";
            var type = "success";
            if (data.status == "success") {
            	refreshGrid("#table");
            } else {
            	 $.dialog.alert(data.message);
            }
		});
	});
}

//批量删除
function doBatchDel(){
	var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
	if(null == selIds || selIds.length == 0){
		$.dialog.alert("请选择要删除的行");
		return false;
	}
	$.dialog.confirm("确认要进行批量删除吗？",function(){
		// 所有异步提交，必须都要加上遮罩，为了防止AJAX重复提交
		$("body",parent.document).mask("正在处理，请稍候...");
		$.post(basePath+"admin/sys/dict/batchDelete.htm?m="+Math.random(),{"selIds":selIds.join(",")},function(data){
			// 回调函数中去除遮罩
			$("body",parent.document).unmask();
			var msgs = "";
            var type = "success";
            if (data.status == "success") {
            	refreshGrid("#table");
            } else {
            	 $.dialog.alert(data.message);
            }
		});
	});
}

// 添加数据项
function toAddDictItem(){
	var dictId = $("#globalDict").val();
	$.dialog.open(basePath+'admin/sys/dict/toEditSysDictItem.htm?id='+dictId+'&m='+Math.random(),{
		title:'新增数据项',
		width:'520px',
		height:'200px',
		lock:true
	});
}

function reloadDict(options){
	var id = $("#globalDict").val();
	$("#globalDict").html('');
	for(var i=0;i<options.length;i++){
		var option = options[i];
		if(option.id == id || option.select){
			var optionHtml = '<option value="' + option.id + '" selected onchange="changeSelect();return false;">' + option.name + '</option>';
			$("#globalDict").val(option.id);
		}else{
			var optionHtml = '<option value="' + option.id + '" onchange="changeSelect();return false;">' + option.name + '</option>';
		}
		$("#globalDict").append(optionHtml);
	}
}

// 加载属性页面
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}