$(document).ready(function(){
	$("#table").jqGrid({
		url:basePath+"admin/subscribe/listSubscribeByPage.htm",
		colNames:["操作","订阅者姓名","手机号码","邮箱","公司名称","职务","期刊类型名称","订阅状态"],
		colModel:[
			{name:"id",title:false,width:80,align:"center",sortable:false,formatter:optFormater},
			{name:"name",title:false,width:100,align:"center",sortable:false,editable:true,editoptions:{size:20}},
			{name:"mobile",title:false,width:120,align:"left",sortable:false,editable:true,editoptions:{size:50}},
			{name:"email",width:300},
			{name:"companyName",width:300},
			{name:"position",width:300},
			{name:"channelName",width:300},
			{name:"state",width:100,formatter:stateFormater}
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
	
	$("#exportEnroll").click(exportEnroll);
});

function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var updateBtn = "<a href='javascript:;' onclick=\"toUpdateSubscribe('"+rowObject.id+"');return false;\"title='修改'>修改</a>";
	btns.push(updateBtn);
	var removeBtn = "<a href='javascript:;' onclick=\"delSubscript('"+rowObject.id+"');return false;\" title='删除'>删除</a>";
	btns.push(removeBtn);
	return btns.join(" ");
}
function stateFormater(cellvalue,options,rowObject){
	var btns = [];
	var stateBtn;
	if(cellvalue ==0){
		stateBtn="<div style='color:#00FF00'>"+"正常订阅"+"</div>";
		btns.push(stateBtn);
	}else if(cellvalue ==1){
		stateBtn="<div style='color:#FF0000'>"+"退订"+"</div>";
		btns.push(stateBtn);
	}
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
		$.post(basePath+"admin/subscribe/del.htm?selIds="+id,function(data){
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


// 打开新增文章的页面
function sendMai(){
	var tv = {};
	tv.text="发送邮件";
	tv.url = "admin/subscribeSend/add.htm";
	tv.tabId = "send_mail";
	tv.doc = parent.document;
	showTab(tv);
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
function moreSearch(){
	var arr = $("#searchForm").serializeArray();
	var postData = {};
	for (var i = 0; i < arr.length; i++) {
		var entry = arr[i];
		postData[entry["name"]] = entry["value"];
	}
	var keyword = $("#key").val();
	// 增加关键字参数
	postData["condition.key"] = keyword;
	
	$('#table').setGridParam({
				mtype : 'post',
				postData : postData
			}).trigger('reloadGrid', [{
						page : 1
					}]);
}
/**
 * 批量导出
 * @author hys
 */
function exportEnroll(){
	var postData = {};
	var key = $("#key").val();
	postData["condition.key"] = key;
	$.ajax({
		    type :'post',
			url : base+'admin/subscribe/doExport.htm',
			data : postData,
			dataType:'json',
			success : function(data) {
				var result = data;
				if(result.status=="success"){
					window.location.href=basePath+"/download/downloadLocalFileByPath.htm?filePath="+data.path+"&fileName="+encodeURI(encodeURI("报名信息.xls"));
				}
			},
			error : function() {
			}
	});
}

//模糊查询
function searchTable(){
	var postData = {};
		postData = {"condition.gameName":$("#searchKey").val()};
	 $("#table").setGridParam({
	 	postData : postData
	 }).trigger("reloadGrid",[{"page":1}]);

}


// 加载属性页面
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}