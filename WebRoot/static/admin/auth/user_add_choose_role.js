$(document).ready(function(){
	var gh = $(window).height() - $('#operBar').height() - 91;
	
	$('#table').jqGrid({
		url:'admin/auth/role/listRoles.htm',
		colNames:['id','操作','角色名称','角色类型','角色类型','描述','授权用户'],
		colModel:[
			{name:'id',width:40,hidden:true},
			{name:'',width:40,formatter:optFormater},
			{name:'name',width:60,align:'center'},
			{name:'roleType',width:60,hidden:true},
			{name:'roleType',width:40,align:'center',formatter:roleTypeFormater},
			{name:'remark',width:200,align:'left'},
			{name:'users',width:150,align:'left'}
		],
		caption:"角色列表",
		loadComplete: function(data){
			var width = $(window).width()-10;
			$(this).setGridWidth(width);
			fillEmptyRow('table',data);
	   	},
	   	height:gh,
	   	rownumbers:true,
		jsonReader:{id: "id",root:"resultList"}
	});
	$('#table').jqGrid('navGrid','#pagerBar');
	$('#table_remark').css('text-align','left');
	$('#table_users').css('text-align','left');
	
	//异步表单提交设置
    $('#roleForm').ajaxForm({
        dataType: 'json',
        success: function(data) {
        	$.dialog.alert(data.message);
         	reloadGrid();
        }
    });
});

function optFormater(cellvalue,options,rowObject){
	return "<a href='javascript:;' onclick=\"chooseFN('"+rowObject.id+"','"+rowObject.name+"');return false;\">选择</a>&nbsp;";
}

function roleTypeFormater(cellvalue,options,rowObject){
	var str = "";
	if(cellvalue == 1){
		str = '<span class="status-red">系统</span>';
	}else if(cellvalue == 2){
		str = '<span class="status-green">用户</span>';
	}
	return str;
}

// 选择角色
function chooseFN(id){
	alert("choose role");
}

// 重新加载
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}

//查询
function searchTable(){
	var searchkey = $("#searchkey").val();
	$('#table').setGridParam({
		mtype:'post',
		postData:{'search_key':searchkey}
	}).trigger('reloadGrid',[{page:1}]);
}

// 关闭弹出窗口
function closeWin(){
	$.dialog.close();
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