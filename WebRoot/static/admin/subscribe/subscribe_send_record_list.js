$(document).ready(function(){
	$("#table").jqGrid({
		url:basePath+"admin/subscribeSendRecord/listSubscribeSendRecord.htm",
		colNames:["操作","订阅者姓名","手机号码","邮箱","期刊类型名称","订阅邮件标题","发送时间"],
		colModel:[
			{name:"id",title:false,width:80,align:"center",sortable:false,formatter:optFormater},
			{name:"subscribeName",title:false,width:100,align:"center",sortable:false,editable:true,editoptions:{size:20}},
			{name:"subscribeMobile",title:false,width:120,align:"left",sortable:false,editable:true,editoptions:{size:50}},
			{name:"email",width:300},
			{name:"subscribeChannelName",width:300},
			{name:"subscribeSendTitle",width:300},
			{name:"createDate",width:300,formatter:'date',formatoptions:{srcformat:'Y-m-d H:i', newformat: 'Y-m-d'}}
		],
		caption:"邮件发送列表",
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
	var updateBtn = "<a href='javascript:;' onclick=\"toUpdateSubscribe('"+rowObject.id+"');return false;\"title='修改'>修改</a>";
	btns.push(updateBtn);
	var removeBtn = "<a href='javascript:;' onclick=\"delSubscript('"+rowObject.id+"');return false;\" title='删除'>删除</a>";
	btns.push(removeBtn);
	return btns.join(" ");
}

// 修改订阅单
function toUpdateSubscribe(id){
	$.dialog.open(basePath+'admin/subscribe/update.htm?selIds='+id,{
		title:'修改订阅',
		width:'800px',
		height:'500px',
		lock:true
	});
}

// 删除订阅单
function delSubscript(id){
	$.dialog.confirm("确认要进行删除吗？",function(){
		$.post(basePath+"admin/subscribeSendRecord/del.htm?selIds="+id,function(data){
            if (data.status == "success") {
            	reloadGrid();
            }else{
           	 	$.dialog.alert("删除失败");
            }
		});
	});
}


// 添加订阅单
function toAddSubscribe(){
	$.dialog.open(basePath+'admin/subscribe/add.htm',{
		title:'新增订阅单',
		width:'800px',
		height:'500px',
		lock:true
	});
}
//查询searchForm
function ajaxForm(){
	$('#searchForm').ajaxForm({
		dataType:'json',
		success:function(data){
			$("body").unmask();
			if(data.status == 'success'){
				var win = $.dialog.open.origin;
				win.reloadGrid();
				$.dialog.close();
			}else{
			 	$.dialog({ content: '查询失败',okVal:"确定",ok: function(){}});
			}
		}
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

// 加载属性页面
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}