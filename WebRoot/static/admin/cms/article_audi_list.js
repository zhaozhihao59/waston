var zTree, rMenu, curNode, categoryId;
$(document).ready(function(){
	var gh = $(window).height() - $('#operBar').height() - 91;
	$("#treeDiv").css("height",gh+69);
	//渲染表格骨架
	$("#table").jqGrid({
	    url:'admin/cms/article/searchUnAuditArticleList.htm?m='+Math.random(),
	    datatype:"json",
	    colNames:['操作','排序','文章名称','栏目名称','发布时间','待审核','栏目ID'],
		colModel:[ 
			{name:"id",index:"id",width:50,formatter:optFormater}, 
			{name:'sort',index:'sort', width:30},
			{name:'name',index:'name', align:'left', width:200},
			{name:"channelName",index:'channelName',width:60},
			{name:'createDate',index:'createDate',width:70},
			{name:'isPublish',index:'isPublish',width:50,formatter:isPublishFormater},
			{name:'channelId',index:'channelId',hidden:true}
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
	
	// 生成Ztree
	var setting = {
		async : {
			enable : true,
			url : treeLoadUrl,
			autoParam : ["id", "name", "isDelete", "state"]
		},
		callback : {
			onRightClick : OnRightClick,
			onClick: onClickTree
		},
		view : {
		// fontCss: getFontCss
		}
	};
	$.fn.zTree.init($("#categoryTree"), setting);
	zTree = $.fn.zTree.getZTreeObj("categoryTree");
	rMenu = $("#rMenu");
	
	
    //验证框架信息
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	
	$("#itemName").formValidator({validatorGroup:1}).inputValidator({min:1,max:255,onErrorMin:"文章标题不能为空",onErrorMax:"文章标题长度过长"});
	$("#channelName").formValidator().inputValidator({min:1,onError: "请选择栏目名称"}).defaultPassed();
	$("#itemContent").formValidator({validatorGroup:1}).inputValidator({min:1,onErrorMin:"内容不能为空"});
	$("#delArticle").click(delArticle);
	
	$('#audit').click(function(){
		//审核通过
		audit(1);
	});
	$('#unAudit').click(function(){
		//审核通过
		audit(3);
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

// 加载tree
function treeLoadUrl(treeId, treeNode) {
	return treeNode == null ? "admin/cms/channel/list_channel.htm" : "admin/cms/channel/list_channel.htm?parentId="+treeNode.id;
}

//zTree点击事件		
function onClickTree(e, treeId, treeNode) {
	curNode = treeNode;
	$('#channelChoose').val(treeNode.name);
	$("#channelId").val(treeNode.id);
	searchArticleList();
	hideRMenu();
}

// tree右键事件
function OnRightClick(event, treeId, treeNode) {
	if (!treeNode && event.target.tagName.toLowerCase() != "button"
			&& $(event.target).parents("a").length == 0) {
		zTree.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
		curNode = null;
	} else if (treeNode && !treeNode.noR) {
		// 暂时取消 右键选中 节点功能
		// curNode = treeNode;
		// zTree.selectNode(treeNode);
		showRMenu("node", event.clientX, event.clientY);
	}
}

// 显示右键菜单
function showRMenu(type, x, y) {
	$("#rMenu ul").show();
	if (type == "root") {
		curNode = null;
		$("#m_del").hide();
		$("#m_check").hide();
		$("#m_unCheck").hide();
	} else {
		$("#m_del").show();
		$("#m_check").show();
		$("#m_unCheck").show();
	}
	rMenu.css({
				"top" : y + "px",
				"left" : x + "px",
				"visibility" : "visible"
			});

	$("body").bind("mousedown", onBodyMouseDown);
}

/** 重新加载 * */
function reloadTree(){
	hideRMenu();
	zTree.reAsyncChildNodes(null, "refresh");
}

/** 刷新指定节点 */
function reloadNodes(id){
	hideRMenu();
	var node = zTree.getNodeByParam('id', id);
	if(node.isParent == 0){
		pNode = zTree.getNodeByParam('id',node.parentId);
		zTree.reAsyncChildNodes(pNode, 'refresh');
	}else{
		if (node != null) {
			if (node.open) {
				zTree.reAsyncChildNodes(node, 'refresh');
			} else {
				zTree.expandNode(node);
			}
		}
	}
}

/** 隐藏右键菜单 */
function hideRMenu() {
	if (rMenu)
		rMenu.css({
					"visibility" : "hidden"
				});
	$("body").unbind("mousedown", onBodyMouseDown);
}

// 隐藏菜单
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}

function onBodyMouseDown(event) {
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}

//格式化操作单元格
function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var updateBtn = "<a href='javascript:;' title='查看\修改' class='no_unl' onclick=\"updateFN('"+rowObject.id+"');return false;\">查看/修改</a>";
	btns.push(updateBtn);
	return btns.join(" ");
}
function isPublishFormater(cellvalue,options,rowObject){
	if(cellvalue == 2){
		return '<span class="status-red">待审核</span>';
	}else if(cellvalue == 3){
		return '<span class="stauts-green-f12">审核不通过</span>';
	}
}

//修改
function updateFN(id){
	var tv = {};
	tv.text="修改文章";
	tv.url = "admin/cms/article/to_update.htm?item.id=" + id;
	tv.tabId = "cms_article_update_" + id;
	tv.doc = parent.document;
	showTab(tv);
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

function unPublish(){
	var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
	$.post("admin/cms/article/unPublish.htm?m="+Math.random(),{"model.articleId":selIds.join(",")},function(data){
		$.dialog.alert(data.message);
		reloadGrid();
	},"json");
}

function audit(state){
	var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
	if(selIds !=""){
		$.post("admin/cms/article/audit.htm?m="+Math.random()+"&item.state="+state,{"model.articleId":selIds.join(",")},function(data){
			if(data.status=="success"){
				reloadGrid();
			}else{
				$.dialog.alert("审核失败");
			}
		},"json");
	}else{
		$.dialog.alert("没有选中数据");
		return false;
	}
	
}
//重新加载
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}