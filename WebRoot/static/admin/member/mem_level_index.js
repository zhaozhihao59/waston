$(document).ready(function(){
	//初始化验证表单
	initFormValidator();
	// 初始化表格
	initTable();
	// 初始化异步表单提交设置
	initAjaxForm();
	// 初始化按钮
	initOperatorBtn();
});

function initFormValidator(){
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#searchKey").formValidator({validatorGroup:1,onShowText:'请输入会员名称模糊查询'}).inputValidator({min:0,max:255,onErrorMax:"长度过长"});
}

function initOperatorBtn(){
	// 保存按钮
	$("#save").bind("click",function(){
		$("#memberForm").submit();
	});
	// 刷新按钮
	$("#refresh").bind("click",function(){
		reloadGrid();
	});
	//删除按钮
	$("#delMember").bind("click",function(){
		del();
	});
	//查询按钮
	$("#search").click(search);
}
// 表格
function initTable(){
	var gh = $(window).height() - $('#operBar').height() - 120;
	//渲染表格骨架
	$("#table").jqGrid({
		url:'admin/member/level/listMemLevelByPage.htm?m='+Math.random(),
	    datatype:"json",
	    colNames:['操作',"等级序号","等级名称",'备注'],
		colModel:[ 
			{name:"id",index:"id",width:40,formatter:optFormater}, 
			{name:"levelNum",width:40},
			{name:'levelName',width:80},
			{name:'levelNote',align:'left',width:400}
		], 
		caption:"会员等级列表",
		height:gh,
		loadComplete: function(data){
			var width = $(window).width()-10;
			$(this).setGridWidth(width);
	   	},
		jsonReader:{id: "id",root:"resultList"}
  	});
  	$("#table").jqGrid("navGrid","#pagerBar");
  	$("#table_levelNote").css("text-align","left");
		  
	// 随窗体大小改变而改变Grid宽度
	window.onresize = function _doResize(){
	 	var width = $(window).width()-10;
	 	$("#table").jqGrid('setGridWidth', width);
	}
}

// 异步提交
function initAjaxForm(){
	 //异步表单提交设置
    $('#memberForm').ajaxForm({
        dataType: 'json',
        success: function(data) {
	    	if(data.status == "success"){
	    		$("#itemId").val("");
	     		$("#account").val("");
	     		$("#mobile").val("");
	     		$("#name").val("");
	     		$("#wetchatNo").val("");
	     		$("#memberLeave").val("");
	     		$("#email").val("");
	     		$("#corporationName").val("");
	     		$("#newChannelStatus").val("xuanze");
	     		$.dialog.alert('保存成功');
			}else{
				$.dialog.alert('保存失败,请重新修改。');
				$.dialog.alert(data.message);
			}
        }
    });
}

function unStatusFormat(cellvalue,options,rowObject){
	return $("font",rowObject).attr("class");
}


// 格式化启用禁用状态
function stateFormater(cellvalue,options,rowObject){
	var str = '';
	if(cellvalue == 1){
		str = '<span class="stauts-green-f12">启用</span>';
	}else if(cellvalue == 2 || cellvalue == null){
		str = '<span class="status-red-f12">禁用</span>';
	}
	return str;
}

// 格式化启用禁用状态
function sexFormater(cellvalue,options,rowObject){
	var str = '';
	if(cellvalue == 0){
		str = '男';
	}else if(cellvalue == 1){
		str = '女';
	}
	return str;
}

// 格式化操作单元格
function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var updateBtn = "<a href='javascript:;' title='修改' class='no_unl' onclick=\"updateMemberLev('"+rowObject.id+"');return false;\">修改</a>";
	var delBtn = "<a href='javascript:;' title='删除' class='no_unl' onclick=\"removeFN('"+rowObject.id+"');return false;\">删除</a>";
	btns.push(updateBtn);
	btns.push(delBtn);
	return btns.join(" ");
}

//格式化操作单元格
function newChannelStatusFormater(cellvalue,options,rowObject){
	if(0 == cellvalue){
		return "<font color='red' class="+cellvalue+">不可查看</font>";	
	}else if(1 == cellvalue){
		return "<font color='green' class="+cellvalue+">可查看</font>";
	}else{
		return "<font class=''>未知</font>";
	}
}


//修改
function updateFN(id){
	var rowData = $("#table").jqGrid('getRowData',id);
	$("#itemId").val(id);
	$("#levelNum").val(rowData.levelNum);
	$("#levelName").val(rowData.levelName);
}

//启用/禁用
function stateFN(account,state){
	$.post("admin/mem/member/updateState.htm?m="+Math.random(),{"item.account":account,"item.state":state},function(item){
		reloadGrid();
	});
}

//查询
function searchUserList(){
	var arr = $("#searchForm").serializeArray();
	var postData = {};
	for(var i=0;i<arr.length;i++){
		var entry = arr[i];
		postData[entry["name"]]=entry["value"];
	}
	$('#table').setGridParam({
		mtype:'post',
		postData:postData
	}).trigger('reloadGrid',[{page:1}]);
}


//批量删除
function del(){
	// 获取选中的行
	var selarrrow = $("#table").getGridParam("selarrrow");
	if(!selarrrow || selarrrow.length <= 0){
		$.dialog.alert("请选择要删除的记录");
		return false;
	}
	$.dialog.confirm("确定要删除吗?",function(){
		$.post(base + "/admin/member/del.htm",{"selIds" : selarrrow.join(",")},function(data){
			if(data.status == "success"){
				$("#table").trigger("reloadGrid",[{page : 1}]);
			}else{
				$.dialog.alert(data.message);
			}
		},"json");
	});
}
//单条删除操作 周义德(zyd) 2014年4月18日10:06:26
function removeFN(id){
	//确认提示
	var dialog = $.dialog({
	    title: '确认删除',
	    content: '确认要删除吗？',
	    okVal:"删除",
	    cancelVal:"取消",
	    ok: function(){
	    	$.post("admin/member/level/delMemberById.htm?m="+Math.random(),{"item.id":id},function(data){
				 $.dialog.alert(data.message);
				 reloadGrid();
			});
	    },
	    cancel:function(){
	    	this.close();
	    }
	});
}
//批量删除
function delMembers(){
		var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
		if(selIds==null||selIds==''){	
			$.dialog.alert('请勾选要删除的数据');
			return;
		}
	$.dialog.confirm('确认要进行批量删除吗？',function(){
		$.post("admin/member/level/doBatchDelMember.htm?m="+Math.random(),{"model.selIds":selIds.join(",")},function(data){
			$.dialog.alert(data.message);
 			reloadGrid();
		});
	});
	
}
//弹出添加会员窗口
function addMemberLev(){
	//$.dialog.data("chooseTags",chooseTags);
	$.dialog.open("admin/member/level/addMemberLev.htm", {
		title: '新增会员等级',
		width:'500px',
		height:'300px',
		lock:true
	});
}

//弹出添加会员窗口
function updateMemberLev(id){
	$.dialog.open("admin/member/level/updateMemberLev.htm?item.id ="+id, {
		title: '修改会员等级',
		width:'500px',
		height:'400px',
		lock:true
	});
}
//条件模糊查询
function search(){
	var postData = {};
	// 快速搜索部分
	var nameKey = $("#searchKey").val();
	if(nameKey == "请输入会员名称模糊查询"){
		nameKey = "";
	}
	postData["condition.levelName"] = nameKey;
	$('#table').setGridParam({
		mtype:'post',
		postData:postData
	}).trigger('reloadGrid',[{page:1}]);
}
//重新加载
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}