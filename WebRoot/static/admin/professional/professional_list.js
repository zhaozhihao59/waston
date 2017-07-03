$(document).ready(function(){
	$("#table").jqGrid({
		url:basePath+"admin/professional/listProfessionalByPage.htm",
		colNames:["操作","专业人员名称","专业人员邮箱","业务","工作语言","专业人员类型"],
		colModel:[
		    {name:"id",title:false,width:110,align:"center",sortable:false,formatter:optFormater},
			{name:"name",title:false,width:120,sortable:false},
			{name:"email",width:140},
			{name:"qualificationName",width:140},
			{name:"languageName",width:140},
			{name:"type",width:140,formatter:optType}
		],
		caption:"专业人员",
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
//专业人员类型转换
function optType(cellvalue,options,rowObject){
	var btns = [];
	if(cellvalue == null){
		return "--";
	}
	
	if(cellvalue.indexOf("0") >= 0){
		btns.push("合伙人");
	}
	if(cellvalue.indexOf("1") >= 0){
		btns.push("高级顾问");
	}
	if(cellvalue.indexOf("2") >= 0){
		btns.push("专利代理人");
	}
	if(cellvalue.indexOf("3") >= 0){
		btns.push("律师");
	}
	if(cellvalue.indexOf("4") >= 0){
		btns.push("资深高级合伙人");
	}
	if(cellvalue.indexOf("5") >= 0){
		btns.push("主管合伙人");
	}
	if(cellvalue.indexOf("6") >= 0){
		btns.push("高级合伙人");
	}
	if(cellvalue.indexOf("7") >= 0){
		btns.push("执行主管合伙人");
	}
	return btns.join(",");
}
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
	var tv = {};
	tv.text="修改人员";
	tv.url = "admin/professional/update.htm?item.id="+id;
	tv.tabId = "professional_add";
	tv.doc = parent.document;
	showTab(tv);
}
// 删除订阅单
function delSubscript(id){
	$.dialog.confirm("确认要进行删除吗？",function(){
		$.post(basePath+"admin/professional/del.htm?selIds="+id,function(data){
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
		var tv = {};
		tv.text="新增人员";
		tv.url = "admin/professional/add.htm";
		tv.tabId = "professional_add";
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
//单条件查询

function quickSearch(){
	var keyword = $("#key").val();
	var postData = {};
	postData["condition.key"] =keyword;
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
//跳转到添加页面
$("#addProfessional").click(function(){
	
});
// 加载属性页面
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}