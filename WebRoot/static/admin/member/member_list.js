$(document).ready(function(){
	var gh = $(window).height() - $('#operBar').height() - 91;
	//渲染表格骨架
	$("#table").jqGrid({
	    url:'admin/member/listMemberByPage.htm?m='+Math.random(),
	    datatype:"json",
	    colNames:['操作','注册时间',/*'会员帐号'*/'会员姓名',/*'性别',*/'手机号码','电子邮件','省市区','邮编'],
		colModel:[ 
			{name:"id",index:"id",width:50,formatter:optFormater}, 
			{name:'createDate',index:'createDate', width:70,formatter:ellipsis},
		/*	{name:'account',index:'account', width:100},*/
			{name:"name",index:'name',width:60},
		/*	{name:"sex",index:'sex',width:40,formatter:sexFormater},*/
			{name:"mobile",index:'mobile',width:60},
			{name:'email',index:'email',width:100},
			{name:'provinceCity',index:'provinceCity',width:100},
			{name:'postCode',index:'postCode',width:100}
		], 
		caption:"网站会员列表",
		loadComplete: function(data){
			var width = $(window).width()-10;
			$(this).setGridWidth(width);
			fillEmptyRow('table',data);
	   	},
	   	height:gh,
		jsonReader:{id: "id",root:"resultList"}
  	});
  	$("#table").jqGrid("navGrid","#pagerBar");
		  
	// 随窗体大小改变而改变Grid宽度
	window.onresize = function _doResize(){
	 	var width = $(window).width()-10;
	 	$("#table").jqGrid('setGridWidth', width);
	}
	
	$('#channelChoose').focus(function(){
		var cityObj = $("#channelChoose");
		var cityOffset = $("#channelChoose").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	});
	
    //异步表单提交设置
    $('#articleForm').ajaxForm({
        dataType: 'json',
        success: function(data) {
     		$("#itemName").val("");
     		$("#channelName").val("");
     		$("#itemContent").val("");
     		$("#itemId").val("");
        	$.dialog.alert(data.message);
        	reloadGrid();
        }
    });
    
	$('#refresh').live('onclick',function(){
		reloadGrid();
	});
	
});

function sexFormater(cellvalue,options,rowObject){
	if(cellvalue == 0){
		return "女";
	}else if(cellvalue == 1){
		return "男";
	}else{
		return "--";
	}
}

//格式化操作单元格
function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var detailBtn = "<a href='javascript:;' title='详细' class='no_unl' onclick=\"toDetail('"+rowObject.id+"');return false;\">详细</a>";
	var modifyBtn = "<a href='javascript:;' title='修改' class='no_unl' onclick=\"toModify('"+rowObject.id+"');return false;\">修改</a>";
	var deleteBtn = "<a href='javascript:;' title='删除' class='no_unl' onclick=\"toDelete('"+rowObject.id+"');return false;\">删除</a>";
	//	var toOpportunity = '<a href="javascript:void(0);" onclick="toOpportunity(\''+cellvalue+'\')">列为商机</a>'
	btns.push(detailBtn,modifyBtn,deleteBtn);
//	btns.push(toOpportunity);
	return btns.join(" ");
}

function ifFormater(cellvalue,options,rowObject){
	if(cellvalue == 2 || cellvalue == null){
		return '<span class="status-red">否</span>';
	}else{
		return '<span class="status-green">是</span>';
	}
}

function wayFormater(cellvalue,options,rowObject){
	if(cellvalue == 1){
		return '<span class="status-green">电子邮箱</span>';
	}else if(cellvalue == 2){
		return '<span class="status-green">手机号码</span>';
	}else{
		return '<span class="status-gray">--</span>';
	}
}

function ifNull(cellvalue,options,rowObject){
	if(cellvalue == null){
		return '--';
	}else{
		return cellvalue;
	}
}
function ellipsis(cellvalue,options,rowObject){
	return '<div style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:block; ">'+cellvalue+'</div>'
}
function isReply(cellvalue,options,rowObject){
	if(cellvalue == 0){
		return '<span class="status-red">未回复</span>';
	}else if(cellvalue == 1){
		return '<span class="status-green">已回复</span>';
	}else{
		return '<span class="status-gray">未知</span>';
	}
}

function add(){
	var tv = {};
	tv.text = '新增会员';
	tv.url = 'admin/member/add.htm';
	tv.tabId = "tabli_add_member";
	tv.doc = parent.document;
	tv.refresh=true;
	showTab(tv);
}

//查看详情
function toDetail(id){
	var tv = {};
	tv.text="会员详细";
	tv.url = "admin/member/detail.htm?item.id=" + id;
	tv.tabId = "member_detail_" + id;
	tv.doc = parent.document;
	showTab(tv);
}

//修改会员
function toModify(id){
	var tv={};
	tv.text="修改会员";
	tv.url="admin/member/update.htm?item.id=" + id;
	tv.tabId="tabli_add_member";
	tv.doc=parent.document;
	showTab(tv);
}

function toDelete(id){
	// 确认提示
	var dialog = $.dialog({
				title : '确认删除',
				content : '确认要删除吗？',
				okVal : "删除",
				cancelVal : "取消",
				ok : function() {
					$.post("admin/member/del.htm?selIds=" + id
							, function(data) {
								// $.dialog.alert(data.message);
								reloadGrid();
							});
				},
				cancel : function() {
					this.close();
				}
			});
}

//查询
function searchArticleList(){
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
function delArticle(){
	//确认提示
	var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
	$.dialog.confirm("确认要删除此项数据吗？",function(){
		$.post("admin/member/del.htm?m="+Math.random(),{"ids":selIds.toString()},function(data){
			if(data.success){
				$.dialog.alert('删除成功');
				reloadGrid();
			}else{
				$.dialog.alert('删除失败');
			}
		});
	});
}

//批量删除
$("#doBatchDelBtn").click(function doBatchDel(){
	var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
	if(selIds==''||selIds==null){
		$.dialog.alert("请勾选要删除的数据");
		return;
	}
	$.dialog.confirm('确认要进行批量删除吗？',function(){
		$("body",document).mask("正在提交信息，请稍候...");
		$.post("admin/member/del.htm?m="+Math.random(),{"selIds":selIds.toString()},function(data){
			$("body",document).unmask();
			if(data.status == "success"){
				reloadGrid();
			}else{
				$.dialog.alert('删除失败');
			}	
		});
	});
});

function unPublish(){
	var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
	$.post("admin/cms/article/unPublish.htm?m="+Math.random(),{"model.articleId":selIds.join(",")},function(data){
		$.dialog.alert(data.message);
		reloadGrid();
	},"json");
}
function publish(){
	var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
	$.post("admin/cms/article/publish.htm?m="+Math.random(),{"model.articleId":selIds.join(",")},function(data){
		$.dialog.alert(data.message);
		reloadGrid();
	},"json");
}

//模糊查询
function searchTable(){
	var postData = {};
		postData = {"condition.name":$("#searchKey").val()};
	 $("#table").setGridParam({
	 	postData : postData
	 }).trigger("reloadGrid",[{"page":1}]);

}

//重新加载
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}