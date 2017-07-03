var zTree, rMenu, curNode, categoryId;
var chooseTags = $.dialog.data("chooseTags");
$(document).ready(function(){
	var gh = $(window).height() - 86;
	$("#treeDiv").css("height",gh);
		  
    //异步表单提交设置
    $('#userForm').ajaxForm({
        dataType: 'json',
        success: function(data) {
        	if(data.status == "success"){
        		$("#login_info_bar").hide();
        	}
        	$.dialog.alert(data.message);
        	reloadGrid();
        }
    });
    
    $('#searchMoreBtn').click(function(){
    	$('#searchMore').slideToggle("fast");
    })
    
    $('#releaseDateMin').datepicker({
		changeMonth: true,
		changeYear: true,
		onClose: function(selectedDate){
			$(this).css('color','#333');
		}
	});
	$('#releaseDateMax').datepicker({
		changeMonth: true,
		changeYear: true,
		onClose: function(selectedDate){
			$(this).css('color','#333');
		}
	});
	
	// 生成Ztree
	var setting = {
		async : {
			enable : true,
			url : treeLoadUrl,
			autoParam : ["id", "name", "location", "open"]
		},
		callback : {
			onRightClick : OnRightClick,
			onClick : OnClick
		},
		view : {
			nameIsHTML : true
		},
		check : {
			enable : false,
			chkStyle : "checkbox"
		}
	};
	
	$.fn.zTree.init($("#categoryTree"), setting);
	zTree = $.fn.zTree.getZTreeObj("categoryTree");
 	rMenu = $("#rMenu");
 	
	// 修改类别
	$("#editNodeBtn").live("click",editNode);
	
	// 刷新选定节点
	$("#selNodeRefresh").live('click',function(){
		hideRMenu();
		if(curNode == null){
			$.dialog.alert("请选择要刷新的节点！");
			return ;
		}
		reloadNodes(curNode.id);
	});
	
	// 删除节点
	$("#selNodeDelete").live('click',function(){
		hideRMenu();
		if(curNode == null){
			$.dialog.alert("请选择要删除的节点！");
			return;
		}
		
		var location=curNode.location;
		if(location ==3){
			$.dialog.confirm("确定要删除此节点吗？",function(){
				$("body",parent.document).mask("正在删除中，请稍候...");
				deleteSelectNode(curNode.id);
			});
		}
		
		if(location == 2 || location == 1){
			$.dialog.confirm("确定要删除此节点及其子节点吗？",function(){
				$("body",parent.document).mask("正在删除中，请稍候...");
				deleteSelectNode(curNode.id);
			});
		}
	});
	
	$("#chooseBtn").live("click",function(){
		var selectArray = zTree.getSelectedNodes();
		var newArray = [];
		for(var i=0;i<selectArray.length;i++){
			var obj = selectArray[i];
			// 判断是否为父级标签，如果是父节点，则不再作为标签加入
			if(obj.isParent != true){
				// 已经添加了标签的情况
				if(chooseTags != null && chooseTags.length > 0){
					var isAdd = true;
					for(var j=0;j<chooseTags.length;j++){
						var chooseTag = chooseTags[j];
						// 判断要添加的标签是否已经添加过，如果已经添加过，则不再添加
						if(chooseTag.id == obj.id){
							isAdd = false;
							continue;
						}
					}
					if(isAdd){
						newArray.push(obj);
					}
				}else{
					newArray.push(obj);
				}
			}
		}
		for(var k=0;k<newArray.length;k++){
			if(chooseTags == null){
				chooseTags = [];
			}
			//向页面中添加标签
			chooseTags.push(newArray[k]);
		}
		showChooseTags();
		$.dialog.close();
	});
	
	$("#closeBtn").live("click",function(){
		$.dialog.close();
	});
});

function showChooseTags(){
	var win = $.dialog.open.origin;
	win.showChooseTags(chooseTags);
}

//格式化操作单元格
function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var updateBtn = "<a href='javascript:;' title='修改' class='no_unl' onclick=\"updateFN('"+rowObject.id+"');return false;\">修改</a>";
	var removeBtn = "<a href='javascript:;' class='no_unl' title='删除' onclick=\"removeFN('"+rowObject.id+"');return false;\">删除</a>";
	btns.push(updateBtn);
	btns.push(removeBtn);
	return btns.join(" ");
}

function nameFormater(cellvalue,options,rowObject){
	var name = cellvalue;
	if(rowObject.isRecommend == 1){
		name = '[ 推荐 ]' + name;
	}
	if(rowObject.isRecommendMember == 1){
		name = '<span class="status-red">[ 会员精选 ]</span>' + name;
	}
	return '<a href="javascript:void(0);" onclick="updateFN(\''+rowObject.id+'\');return false;" class="grid-item-title">'+name+'</a><p class="status-gray">发行机构：'+rowObject.issuer+'</p>';
}

function doubleFormater(cellvalue,options,rowObject){
	return Number(cellvalue).toFixed(1);
}

function roiFormater(cellvalue,options,rowObject){
	if(cellvalue == null && rowObject.annualRateMax != null){
		return Number(rowObject.annualRateMin).toFixed(1) + " %";
	}else if(cellvalue != null && rowObject.annualRateMax != null){
		return Number(cellvalue).toFixed(1) + " - " + Number(rowObject.annualRateMax).toFixed(1) + " %";
	}else if(cellvalue != null && rowObject.annualRateMax == null){
		return Number(cellvalue).toFixed(1) + " %";
	}else{
		return "--";
	}
}

function recommendFormater(cellvalue,options,rowObject){
	if(cellvalue != null && cellvalue == 1){
		return '<span class="status-green">推荐</span>';
	}else{
		return '<span class="status-gray">--</span>';
	}
}

function ellipsis(cellvalue,options,rowObject){
	return '<span style="text-overflow: ellipsis;white-space:nowrap;overflow:hidden;display:block;">'+cellvalue+'</span>';
}
function download(cellvalue,options,rowObject){
	if( cellvalue != null ){
		return '<a href="'+rowObject.path+'">'+cellvalue+'</a>';
	}else{
		return '--';
	}
}

function deleteSelectNode(nodeId){
	$.post(base+"admin/resource/tag/doDelete.htm?m="+Math.random(),{"selIds":nodeId},function(data){
		$("body",parent.document).unmask();
		if(data.status=="success"){
			$.dialog.close();
       		reloadTree();
		}else{
			$.dialog.alert("删除失败！");
		}
	});
}

function percentage(cellvalue,options,rowObject){
	var percentage = "--";
	if(cellvalue != null && $.trim(cellvalue) != ''){
		percentage = cellvalue+' %';
	}
	return percentage;
}

function toDetail(cellvalue,options,rowObject){
	return '<a onclick="showDetail(\''+rowObject.id+'\')" class="" href="javascript:void(0)">'+cellvalue+'</a>';
}

// 搜索
function searchTable(){
	
	var postData = {};
	// 快速搜索部分
	var nameKey = $("#fastSearch").val();
	if(nameKey == "请输入产品名称模糊查询"){
		nameKey = "";
	}
	postData["condition.name"] = nameKey;
	
	var isMoreSearch = $("#isMoreSearch").val();
	if(isMoreSearch == 1){
		var arr = $("#searchForm").serializeArray();
		for(var i=0;i<arr.length;i++){
			var entry = arr[i];
			postData[entry["name"]]=entry["value"];
		}
	}
	
	$('#categoryContentTable').setGridParam({
		mtype:'post',
		postData:postData
	}).trigger('reloadGrid',[{page:1}]);
}

// 详细
function toDetailFN(id){
	var tv = {};
	tv.text = '商机详细';
	tv.url = 'admin/cms/product/toDetail.htm?m='+Math.random()+'&item.id='+id;
	tv.tabId = "tabli_detail_"+id;
	tv.doc = parent.document;
	tv.refresh=true;
	showTab(tv);
}

// 修改
function updateFN(id){
	var tv = {};
	tv.text = '修改产品';
	tv.url = 'admin/cms/product/toUpdate.htm?m='+Math.random()+'&id='+id;
	tv.tabId = "tabli_update_product_"+id;
	tv.doc = parent.document;
	tv.refresh=true;
	showTab(tv);
}

// 新增
function toAdd(){
	var tv = {};
	tv.text = '新增产品';
	tv.url = 'admin/cms/product/toAdd.htm?m='+Math.random();
	tv.tabId = "tabli_add_product";
	tv.doc = parent.document;
	tv.refresh=true;
	showTab(tv);
}

//删除
function removeFN(id){
	$.dialog.confirm("确认要删除吗？",function(){
		$.post("admin/cms/product/doBatchDel.htm?m="+Math.random(),{"selIds":id},function(data){
			if(data.status == "success"){
				$.dialog.alert('删除成功',function(){
					reloadGrid();
				});
			}else{
				$.dialog.alert('删除失败');
			}
		});
	});
}
//批量删除
function doBatchDel(){
	$.dialog.confirm('确认要进行批量删除吗？',function(){
		var selIds = $("#categoryContentTable").jqGrid("getGridParam", "selarrrow");
		$.post("admin/cms/product/doBatchDel.htm?m="+Math.random(),{"selIds":selIds.toString()},function(data){
			if(data.status == "success"){
				$.dialog.alert('删除成功',function(){
					reloadGrid();
				});
			}else{
				$.dialog.alert('删除失败');
			}	
		});
	});
}

function createBy(cellvalue,options,rowObject){
	var createBy = '<span class="status-blue">'+cellvalue+'</span>';
	var updateBy = '<span class="status-yellow">'+rowObject.updateBy+'</span>';
	return createBy+'<br/>'+updateBy
}

function createDate(cellvalue,options,rowObject){
	var createDate = '<span class="status-blue">'+cellvalue+'</span>';
	var updateDate = '<span class="status-yellow">'+rowObject.updateDate+'</span>';
	return createDate+'<br/>'+updateDate
}

// 打开详情页
function showDetail(id){
	var tv = {};
	tv.text="客户详情";
	tv.url = "admin/cus/customer/toDetail.htm?m="+Math.random()+"&item.id="+id;
	tv.tabId = "tabli_customer_detail_"+id;
	tv.doc = parent.document;
	tv.refresh=true;
	showTab(tv);
}

//重新加载
function reloadGrid(){
	$('#categoryContentTable').trigger('reloadGrid',[{page:1}]);
}

// 新增子类
function addChild(){
	hideRMenu();
	if(curNode == null){
		$.dialog.alert("请选择父类！");
		return;
	}
	
	// 隐藏右键菜单
	hideRMenu();
	// 若是多选，则只选中当前一个
	var location = curNode.location;
	location = parseInt(location);
	location++;
	var isParent = curNode.isParent;
	var isParentItem = 0;
	if(isParent){
		isParentItem = 1;
	}
	
	$.dialog.open(
		base+"admin/resource/tag/to_add.htm?item.parentId="+curNode.id+"&item.location="+location+"&item.isParent="+isParentItem,{
			title : "新增资源标签",
			width : "500px",
			height : "200px",
			lock : true
		}
	);
}

function editNode(){
	// 隐藏右键菜单
	hideRMenu();
	if(curNode == null){
		$.dialog.alert("请选择类别！");
		return;
	}
	if(curNode.id == "0"){
		$.dialog.alert("根节点不能修改");
		return;
	}
	$.dialog.open(
		base+"admin/resource/tag/toUpdate.htm?item.id="+curNode.id,{
			title : "修改资源标签",
			width : "500px",
			height : "200px",
			lock : true
		}
	);
}

function treeLoadUrl(treeId, treeNode) {
	return "admin/resource/tag/list_team.htm";
}

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

/** 点击zTree查询 */
function OnClick(event, treeId, treeNode) {
	curNode = treeNode;
	searchCategoryList();
}

/** 单击类别树查询 */
function searchCategoryList() {
	var postData = {"condition.categoryId":curNode.id};
	$('#categoryContentTable').setGridParam({
		mtype:'post',
		postData:postData
	}).trigger('reloadGrid',[{page:1}]);
}

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
function reloadTree() {
	hideRMenu();
	zTree.reAsyncChildNodes(null, "refresh");
}

/** 刷新指定节点 */
function reloadNodes(id) {
	var node = zTree.getNodeByParam('id', id);
	if (node != null) {
		if (node.open) {
			zTree.reAsyncChildNodes(node, 'refresh');
		} else {
			zTree.expandNode(node);
		}
	}
	hideRMenu();
}

/** 隐藏右键菜单 */
function hideRMenu() {
	if (rMenu)
		rMenu.css({
					"visibility" : "hidden"
				});
	$("body").unbind("mousedown", onBodyMouseDown);
}

function onBodyMouseDown(event) {
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}