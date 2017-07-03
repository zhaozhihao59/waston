$(document).ready(function(){
	var gh = $(window).height() - $('#operBar').height() - 91;
	//渲染表格骨架
	$("#table").jqGrid({
	    url:'admin/cms/article/searchArticleList_qikan.htm?m='+Math.random(),
	    datatype:"json",
	    colNames:['操作','排序','文章名称','栏目名称','发布时间','是否发布','栏目ID','文章内容'],
		colModel:[ 
			{name:"id",index:"id",width:50,formatter:optFormater,unformat:optUnFormater}, 
			{name:'sort',index:'sort', width:30},
			{name:'name',index:'name', align:'left', width:200},
			{name:"channelName",index:'channelName',width:60},
			{name:'createDate',index:'createDate',width:70},
			{name:'isPublish',index:'isPublish',width:50,formatter:isPublishFormater},
			{name:'channelId',index:'channelId',hidden:true},
			{name:'content',hidden:true}
		], 
		caption:"查询结果列表",
		loadComplete: function(data){
			resizeWidth();
			fillEmptyRow('table',data);
	   	},
	   	height:gh,
		jsonReader:{id: "id",root:"resultList"}
  	});
  	$("#table").jqGrid("navGrid","#pagerBar");
  	$('#table_name').css('text-align','left');
		  
	resizeWidth();
	
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
	

	$("#delArticle").click(delArticle);
	$('#send').click(publish);
	$("#selectChannelId").click(selectChannelId);
	
	// 打开新增文章的页面
	$('#addArticle').live('click',function(){
		var cid = $('#channelId').val();
		var tv = {};
		tv.text="新增文章";
		tv.url = "admin/cms/article/to_add.htm?item.channelId=" + cid;
		tv.tabId = "cms_article_add";
		tv.doc = parent.document;
		showTab(tv);
	});
	
	$('#channel').live('change',function(){
		searchArticleList();
	});
	
	$('#refresh').live('click',function(){
		reloadGrid();
	});
	$('#isPublish').change(function(){
		var isPublish = $(this).val();
		$('#table').setGridParam({
			mtype:'post',
			postData:{'condition.isPublish':isPublish}
		}).trigger('reloadGrid',[{page:1}]);
	});
	
	 
	
	// 刷新栏目
	$("#selNodeRefresh").live('click',function(){
		hideRMenu();
		if(curNode == undefined){
			$.dialog.alert("请选择栏目！");
			return;
		}
		reloadNodes(curNode.id);
		
	});
	
	//左右拖拽
	var divsp = new Separator(document.getElementById("categoryTreeDiv"),document.getElementById("categoryContentDiv"),separatorCallbackFN,SP_LEFTRIGHT,4); 
	$(window).resize(function(){
		resizeWidth();
	});
});

// 拖拽后回调函数
function separatorCallbackFN(){
	resizeWidth();
}

function hideTipFN(){
	$("#loadTip").hide();
}

function resizeWidth(){
	var ww = $(window).width();
	var tw = $("#categoryTreeDiv").width();
	var cw = ww-tw-18;
	$("#categoryContentDiv").width(cw);
	$("#table").setGridWidth(cw);
}
 

/** 重新加载 * */
function reloadTree(){
	hideRMenu();
	zTree.reAsyncChildNodes(null, "refresh");
}

 
/**  反格式化*/
function optUnFormater(cellvalue,options,rowObject){
	return options.rowId;
}

//选择user
function chooseFN(rowid,rowName,content){
	var rowObject = $("#table").jqGrid("getRowData",rowid);
	var rows=[];
	rows.push(rowObject);
	var win = art.dialog.open.origin;
	win.addRow1(rows);
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
	win.addRow1(rows);
	$.dialog.close();
}



//格式化操作单元格
function optFormater(cellvalue,options,rowObject){
	return  "<a href='javascript:;' onclick=\"chooseFN('"+rowObject.id+"','"+rowObject.name+"','"+rowObject.content+"');return false;\">选择</a>&nbsp;";;
}
function isPublishFormater(cellvalue,options,rowObject){
	if(cellvalue == 0){
		return '<span class="status-red-f12">不发布</span>';
	}else if(cellvalue == 1){
		return '<span class="stauts-green-f12">已发布</span>';
	}else{
		return '<span class="status-gray-f12">未知</span>';
	}
}
 

//查询
function searchArticleList(){
	//var channelChoose = $("#channelChoose").val();
	//if(null==channelChoose||''==channelChoose){
		//$("#channelId").val("");
	//}
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
		$.post("admin/cms/article/delArticle.htm?m="+Math.random(),{"model.articleId":selIds.join(",")},function(data){
				$.dialog.alert(data.message);
				reloadGrid();
		},"json");
	});
}

function publish(){
	$.dialog.open(basePath+"admin/subscribe/index_qikan_select.htm",{
		title:'选择人员',
		width:'1050px',
		height:'550px',
		lock:true
	});
}

function selectChannelId(){
	var arr = $("#selectChannelId").val();
	var postData = {};
	postData["condition.channelId"]=arr;
	$('#table').setGridParam({
		mtype:'post',
		postData:postData
	}).trigger('reloadGrid',[{page:1}]);
}
 
//重新加载
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}