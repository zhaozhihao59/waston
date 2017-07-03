$(document).ready(function(){
	$("#table").jqGrid({
		url:basePath+"admin/activity/listActivityByPage.htm",
		colNames:["操作","会议标题","会议时间","活动地址","活动状态","活动推荐","排序","创建时间"],
		colModel:[
		    {name:"id",title:false,width:110,align:"center",sortable:false,formatter:optFormater},
			{name:"title",title:false,width:120,align:"center",sortable:false},
			{name:"dateStr",width:140},
			{name:"address",width:140},
			{name:"state",width:140,formatter:stateFormater},
			{name:"isRecommend",width:140,formatter:isRecommendFormater},
			{name:"sort",width:140},
			{name:"createDate",width:140,formatter : 'date',formatoptions : {newformat : 'Y-m-d'}}
		],
		caption:"活动管理",
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

function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var updateBtn = "<a href='javascript:;' onclick=\"toUpdateSubscribe('"+rowObject.id+"');return false;\"title='修改'>修改</a>";
	btns.push(updateBtn);
	var removeBtn = "<a href='javascript:;' onclick=\"delSubscript('"+rowObject.id+"');return false;\" title='删除'>删除</a>";
	btns.push(removeBtn);
	return btns.join(" ");
}

function stateFormater(cellvalue,options,rowObject){
	var str;
	if(cellvalue == 1){
		 str = '<p><span class="status-green mr10">预告</span></p>';
	}else if(cellvalue == 2){
		 str = '<p><span class="status-green mr10">正在报名</span></p>';
	}else if(cellvalue == 3){
		 str = '<p><span class="status-green mr10">历届</span></p>';
	} else{
		 str = '<p><span class="status-green mr10">--</span></p>';
	}
	return str;
}

function isRecommendFormater(cellvalue,options,rowObject){
	var str;
	if(cellvalue == 1){
		 str = '<p><span class="status-green mr10">已推荐</span></p>';
	}else{
		 str = '<p><span class="status-gray mr10">未推荐</span></p>';
	} 
	return str;
}


/*// 修改订阅单
function toUpdateSubscribe(id){
	$.dialog.open(basePath+'admin/activity/update.htm?selIds='+id,{
		title:'修改活动',
		width:'800px',
		height:'500px',
		lock:true
	});
}*/

//打开新增文章的页面
function toUpdateSubscribe(id){
	var tv = {};
	tv.text="修改活动";
	tv.url = "admin/activity/update.htm?item.id="+id;
	tv.tabId = "cms_article_add";
	tv.doc = parent.document;
	showTab(tv);
}

// 删除订阅单
function delSubscript(id){
	$.dialog.confirm("确认要进行删除吗？",function(){
		$.post(basePath+"admin/activity/del.htm?selIds="+id,function(data){
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
		$.post("admin/activity/del.htm?m="+Math.random(),{"selIds":selIds.toString()},function(data){
			$("body",document).unmask();
			if(data.status == "success"){
				reloadGrid();
			}else{
				$.dialog.alert('删除失败');
			}	
		});
	});
}


/*// 添加订阅单
function toAddSubscribe(){
	$.dialog.open(basePath+'admin/subscribe/add.htm',{
		title:'新增订阅单',
		width:'800px',
		height:'500px',
		lock:true
	});
}*/

// 打开新增文章的页面
function toAddSubscribe(){
	var tv = {};
	tv.text="新增活动";
	tv.url = "admin/activity/add.htm";
	tv.tabId = "cms_article_add";
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