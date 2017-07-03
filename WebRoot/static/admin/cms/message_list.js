$(document).ready(function(){
	//渲染表格骨架
	$("#table").jqGrid({
	    url:'admin/cms/message/listMessageByPage.htm?m='+Math.random(),
	    datatype:"json",
	    colNames:['操作','留言内容','留言分类','相关内容','留言人/留言时间','留言时间','手机','Email','状态','处理人/处理时间','处理时间',''],
		colModel:[ 
			{name:"id",index:"id",width:40,formatter:optFormater}, 
			{name:'content',index:'sort', width:150,align:'left',formatter:ellipsis},
			{name:'messageType',index:'messageType',width:50,formatter:typeFormater },
			{name:'productName',index:'productName',width:130,align:'left'},
			{name:'createName',index:'createName',width:70,formatter:nameFormater},
			{name:"createDate",index:'createDate',width:70,hidden:true},
			{name:'mobile',index:'mobile',width:60},
			{name:'email',index:'email', width:80,formatter:ifNull},
			{name:'status',index:'status',width:30,formatter:isReply},
			{name:'replyName',index:'replyName',width:70,formatter:replyFormater},
			{name:"replyDate",index:'replyDate',width:70,hidden:true},
			{name:'replyId',index:'replyId',width:120,hidden:true},
		], 
		caption:"查询结果列表",
		loadComplete: function(data){
			var width = $(window).width()-10;
			$(this).setGridWidth(width);
			fillEmptyRow('table',data);
	   	},
		jsonReader:{id: "id",root:"resultList"}
  	});
  	$("#table").jqGrid("navGrid","#pagerBar");
  	$('#table_name').css('text-align','left');
  	$('#table_content').css('text-align','left');
  	$('#table_productName').css('text-align','left');
  	
		  
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

//格式化操作单元格
function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var updateBtn = "<a href='javascript:;' title='处理' class='no_unl' onclick=\"toDetail('"+rowObject.id+"');return false;\">处理</a>";
//	var toOpportunity = '<a href="javascript:void(0);" onclick="toOpportunity(\''+cellvalue+'\')">列为商机</a>'
	btns.push(updateBtn);
//	btns.push(toOpportunity);
	return btns.join(" ");
}

function nameFormater(cellvalue,options,rowObject){
	var name = '<p>'+cellvalue+'</p>';
	var time = '<p>'+rowObject.createDate+'</p>';
	return name + time;
}

function replyFormater(cellvalue,options,rowObject){
	var name = '<p>'+cellvalue+'</p>';
	if(cellvalue == '' || cellvalue == null){
		name = '<p class="status-gray">--</p>';
	}
	var time = '<p>'+rowObject.replyDate+'</p>';
	if(rowObject.replyDate == '' || rowObject.replyDate == null){
		time = '<p class="status-gray">--</p>';
	}
	return name + time;
}

function typeFormater(cellvalue,options,rowObject){
	var result = '--';
	if(cellvalue == 1){
		result = "预约理财师";
	}else if(cellvalue == 2){
		result = "申请续约";
	}else if(cellvalue == 3){
		result = "产品预约";
	}else if(cellvalue == 4){
		result = "活动预约";
	}
	return result;
}

function ifNull(cellvalue,options,rowObject){
	if(cellvalue == null){
		return '--';
	}else{
		return cellvalue.substring(0,10);
	}
}
function ellipsis(cellvalue,options,rowObject){
	return '<div style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:block; ">'+cellvalue+'</div>'
}
function isReply(cellvalue,options,rowObject){
	if(cellvalue == 0){
		return '<span class="status-red">未处理</span>';
	}else if(cellvalue == 1){
		return '<span class="status-green">已处理</span>';
	}else{
		return '<span class="status-gray">未知</span>';
	}
}

//查看详情
function toDetail(id){
	var tv = {};
	tv.text="查看留言";
	tv.url = "admin/cms/message/toDetail.htm?item.id=" + id;
	tv.tabId = "cms_message_detail_" + id;
	tv.doc = parent.document;
	showTab(tv);
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
		$.post("admin/cms/message/doDel.htm?m="+Math.random(),{"ids":selIds.toString()},function(data){
			if(data.success){
				$.dialog.alert('删除成功');
				reloadGrid();
			}else{
				$.dialog.alert('删除失败');
			}
		});
	});
}

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
//重新加载
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}