$(document).ready(function(){
	$("#table").jqGrid({
		url:basePath+"admin/activityEnroll/listActivityEnrollByPage.htm",
		colNames:["操作","活动名称","姓名","手机号码","邮箱","公司名称","职位","创建时间"],
		colModel:[
			{name:"id",title:false,width:80,align:"center",sortable:false,formatter:optFormater},
			{name:"activityName",title:false,width:200,align:"center",sortable:false,editable:true,editoptions:{size:20}},
			{name:"name",title:false,width:100,align:"center",sortable:false,editable:true,editoptions:{size:20}},
			{name:"mobile",title:false,width:120,align:"center",sortable:false,editable:true,editoptions:{size:50}},
			{name:"email",width:300},
			{name:"companyName",width:200,align:"center"},
			{name:"positionName",width:100,align:"center"},
			{name:"createDate",width:100,align:"center",formatter:'date',formatoptions:{srcformat:'Y-m-d ',newformat:'Y-m-d '}},
		],
		caption:"报名信息",
		loadComplete: function(data){
			var width = $(window).width()-10;
			$(this).setGridWidth(width);
	   	},
		jsonReader:{id: "id",root:"resultList"}
	});
	$("#table").jqGrid("navGrid","#pagerBar");
	/*$("#table_name").css("text-align","left");*/
	
	// 随窗体大小改变而改变Grid宽度
	window.onresize = function _doResize(){
	 	var width = $(window).width()-20;
	 	$("#table").jqGrid('setGridWidth', width);
	}
	
	$("#exportEnroll").click(exportEnroll);
});

function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var updateBtn = "<a href='javascript:;' onclick=\"toUpdateActivity('"+rowObject.id+"');return false;\"title='修改'>修改</a>";
	btns.push(updateBtn);
	var removeBtn = "<a href='javascript:;' onclick=\"delActivity('"+rowObject.id+"');return false;\" title='删除'>删除</a>";
	btns.push(removeBtn);
	return btns.join(" ");
}

// 修改订阅单
function toUpdateActivity(id){
	$.dialog.open(basePath+'admin/activityEnroll/update.htm?selIds='+id,{
		title:'修改报名信息',
		width:'800px',
		height:'500px',
		lock:true
	});
}

// 删除订阅单
function delActivity(id){
	$.dialog.confirm("确认要进行删除吗？",function(){
		$.post(basePath+"admin/activityEnroll/del.htm?selIds="+id,function(data){
            if (data.status == "success") {
            	reloadGrid();
            }else{
           	 	$.dialog.alert("删除失败");
            }
		});
	});
}

//批量删除
function doBatchDel(){
	var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
	if(selIds==''||selIds==null){
		$.dialog.alert("请勾选要删除的数据");
		return;
	}
	$.dialog.confirm('确认要进行批量删除吗？',function(){
		$("body",document).mask("正在提交信息，请稍候...");
		$.post("admin/activityEnroll/del.htm?m="+Math.random(),{"selIds":selIds.toString()},function(data){
			$("body",document).unmask();
			if(data.status == "success"){
				reloadGrid();
			}else{
				$.dialog.alert('删除失败');
			}	
		});
	});
}

// 添加订阅单
function toAddActivity(){
	$.dialog.open(basePath+'admin/activityEnroll/add.htm',{
		title:'新增报名',
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
			url : base+'admin/activityEnroll/doExport.htm',
			data : postData,
			dataType:'json',
			success : function(data) {
				var result = data;
				if(result.status=="success"){
					window.location.href=basePath+"/download/downloadLocalFileByPath.htm?filePath="+encodeURI(data.path)+"&fileName="+encodeURI(encodeURI("活动报名名单.xls"));
				}
			},
			error : function() {
			}
	});
}



