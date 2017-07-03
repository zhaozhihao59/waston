$(document).ready(function(){
	var gh = $(window).height()  - 91;
	$('#table').jqGrid({
		url:'admin/auth/role/listRoles.htm',
		colNames:['操作','角色名称','角色类型','角色类型','描述','授权用户'],
		colModel:[
			{name:'id',width:40,formatter:optFormater},
			{name:'name',width:60,align:'center',formatter:nameFormater},
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
    
    //验证框架信息
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#searchKey").formValidator({validatorGroup:1,onShowText:'请输入角色名称',onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:0,max:255,onErrorMax:"关键词长度过长"});
	
});
//查询


function searchKey(){
	var searchkey = $("#searchKey").val();
	$('#table').setGridParam({
		mtype:'post',
		postData:{'search_key':searchkey}
	}).trigger('reloadGrid',[{page:1}]);
}
function nameFormater(cellvalue,options,rowObject){
	return '<a href="javascript:void(0);" onclick=\'updateFN("'+rowObject.id+'","'+cellvalue+'");return false;\'>'+cellvalue+'</a>';
}

function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var updateBtn = "<a href='javascript:;' onclick=\"updateFN('"+rowObject.id+"','"+rowObject.name+"');return false;\">修改</a>&nbsp;";
	btns.push(updateBtn);
	if(rowObject.roleType != 1){
		var removeBtn = "<a href='javascript:;' onclick=\"removeFN('"+rowObject.id+"');return false;\">删除</a>&nbsp;"
		btns.push(removeBtn);
	}
	return btns.join(" ");
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

// 添加新角色
function addNewRole(){
	var tv = {};
	tv.text = '新增角色';
	tv.url = 'admin/auth/role/to_add_role.htm';
	tv.tabId = "tabli_addrole";
	tv.doc = parent.document;
	tv.refresh=true;
	showTab(tv);
}

function viewUsersFN(id){
	$.dialog.open("admin/auth/role/toViewUserList.htm?item.id="+id,{
		title: '授权用户',
		width: '650px',
		height:'300px',
		cancelVal:"关闭",
		cancel:function(){
			$.dialog.close();
		}
	});
}

//删除
function removeFN(id){
	var rowData = $("#table").jqGrid('getRowData',id);
	if(rowData.users.length > 0){
		var dialog = $.dialog({
		    title: '删除失败',
		    content: '该角色下存在授权的用户，请先解除授权后再删除该角色',
		    okVal:"关闭",
		    ok: function(){
		    	this.close();
		    }
		});
		return;
	}
	
	$.dialog.confirm("确认要删除该角色吗?",function(){
		// 所有异步提交，必须都要加上遮罩，为了防止AJAX重复提交
		$("body",parent.document).mask("正在处理，请稍候...");
		$.post('admin/auth/role/delRole.htm?m='+Math.random(),{"item.id":id},function(data){
			// 回调函数中去除遮罩
			$("body",parent.document).unmask();
			if(data.status == "success"){
				$("#roleForm").clearForm();
				reloadGrid();
			}else{
				$.dialog.alert(data.message);
			}
		});
	});
	
}

//保存角色
function saveRole(){
	var itemName = $("#itemName").val();
	if(itemName.length == 0){
		//提示消息
        $.msg({wrapID:"errorlist",type:"error",messages:"请输入角色名"});
		return;
	}
	
	$.ajax({
		type:"POST",
		url:"admin/auth/role/judgeNameExist.htm?m="+Math.random(),
		data:{
			"item.id":$("#itemId").val(),
			"item.name":$("#itemName").val()
		},
		success:function(data){
			if(data == "true"){
				$("#roleForm").submit();
			}else{
				//提示消息
        		$.msg({wrapID:"errorlist",type:"error",messages:"该角色名已经被使用"});
			}
		},
		error:function(){
			$.msg({wrapID:"errorlist",type:"error",messages:"保存角色时发生异常!"});
		}
	});
}

//修改
function updateFN(id,name){
	var tv = {};
	tv.text = '修改角色 - ' + name;
	tv.url = 'admin/auth/role/to_update_role.htm?item.id='+id;
	tv.tabId = "tabli_update_role";
	tv.doc = parent.document;
	tv.refresh=true;
	showTab(tv);
}

//重新加载
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}

