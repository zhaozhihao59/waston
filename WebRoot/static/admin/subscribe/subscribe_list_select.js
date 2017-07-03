$(document).ready(function(){
	$("#table").jqGrid({
		url:basePath+"admin/subscribe/list_subscribe_by_page_select.htm",
		colNames:["操作","订阅者姓名","手机号码","邮箱","公司名称","职务","期刊类型名称"],
		colModel:[
			{name:"id",title:false,width:80,align:"center",sortable:false,formatter:optFormater,unformat:optUnFormater},
			{name:"name",title:false,width:100,align:"center",sortable:false,editable:true,editoptions:{size:20}},
			{name:"mobile",title:false,width:120,align:"left",sortable:false,editable:true,editoptions:{size:50}},
			{name:"email",width:300},
			{name:"companyName",width:300},
			{name:"position",width:300},
			{name:"channelName",width:300}
		],
		caption:"订阅名单",
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
	
	$("#selectChannelId").change(selectChannelName);
});

/**  反格式化*/
function optUnFormater(cellvalue,options,rowObject){
	return options.rowId;
}
function optFormater(cellvalue,options,rowObject){
	return "<a href='javascript:;' onclick=\"chooseFN('"+rowObject.id+"','"+rowObject.name+"');return false;\">选择</a>&nbsp;";
}

//选择user
function chooseFN(rowid,rowName){
	var rowObject = $("#table").jqGrid("getRowData",rowid);
	var rows=[];
	rows.push(rowObject);
	var win = art.dialog.open.origin;
	win.addRow(rows);
	$.dialog.close();
}

//批量选择
function selectRole(){
	var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
	var rows=[];
	for(var i=0;i<selIds.length;i++){
		var rowid = selIds[i];
		var rowObject = $("#table").jqGrid("getRowData",rowid);
		rows.push(rowObject);
	}
	var win = art.dialog.open.origin;
	win.addRow(rows);
	$.dialog.close();
}



 




// 删除订阅单
function delSubscript(id){
	$.dialog.confirm("确认要进行删除吗？",function(){
		$.post(basePath+"admin/subscribe/del.htm?selIds="+id,function(data){
            if (data.status == "success") {
            	reloadGrid();
            }else{
           	 	$.dialog.alert("删除失败");
            }
		});
	});
}


 
 
//单条件查询

function quickSearch(){
	var keyword = $("#key").val();
	// 清空其他参数
	var arr = $("#searchForm").serializeArray();
	var postData = {};
	for (var i = 0; i < arr.length; i++) {
		var entry = arr[i];
		postData[entry["name"]] = "";
	}
	// 增加关键字参数
	postData["condition.key"] = keyword;
	$('#table').setGridParam({
				mtype : 'post',
				postData : postData
			}).trigger('reloadGrid', [{
						page : 1
					}]);
	$("#key").focus();

}

function moreSearch(){
	var arr = $("#searchForm").serializeArray();
	var postData = {};
	for (var i = 0; i < arr.length; i++) {
		var entry = arr[i];
		postData[entry["name"]] = entry["value"];
	}
	$('#table').setGridParam({
				mtype : 'post',
				postData : postData
			}).trigger('reloadGrid', [{
						page : 1
					}]);
}

function selectChannelName(){
	var postData = {};
	var channelId = $("#selectChannelId").val();
	// 增加关键字参数
	postData["condition.channelId"] = channelId;
	$('#table').setGridParam({
				mtype : 'post',
				postData : postData
			}).trigger('reloadGrid', [{
						page : 1
					}]);
}

// 加载属性页面
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}