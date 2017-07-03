$(document).ready(function(){
	//渲染表格骨架
	$("#table").jqGrid({
	    url:'admin/cms/adv/list_adv.htm?m='+Math.random(),
	    datatype:"json",
	    colNames:['操作','广告位名称','广告位描述','图片宽度（像素）','图片高度（像素）','最多上传数量'],
		colModel:[ 
			{name:"id", index:"id", width:50, formatter:optFormater}, 
			{name:'name', index:'name', width:100}, 
			{name:'note', index:'note', width:150}, 
			{name:'photoWidth', index:'photoWidth', width:80}, 
			{name:"photoHeight", index:'photoHeight', width:80}, 
			{name:'maxPhotoNum', index:'maxPhotoNum', width:120} 
		], 
		caption:"广告位列表",
		loadComplete: function(data){
			var width = $(window).width()-10;
			$(this).setGridWidth(width);
			fillEmptyRow('table',data);
	   	},
		jsonReader:{id: "id",root:"resultList"}
  	});
  	$("#table").jqGrid("navGrid","#pagerBar");
		  
	// 随窗体大小改变而改变Grid宽度
	window.onresize = function _doResize(){
	 	var width = $(window).width()-20;
	 	$("#table").jqGrid('setGridWidth', width);
	}
	
	//渲染分页工具条
	$("#table").jqGrid('navGrid','#pagerBar');
	
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
    
    //日期控件
	$("#itemBeginBirthday").calendar({maxDate:'%y-%M-%d'});
	$("#itemBeginBirthdayIcon").calendar({id:'itemBeginBirthday'});
	$("#itemEndBirthday").calendar({maxDate:'%y-%M-%d'});
	$("#itemEndBirthdayIcon").calendar({id:'itemBeginBirthday'});
	
    //验证框架信息
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	
	$("#itemName").formValidator({validatorGroup:1}).inputValidator({min:1,max:255,onErrorMin:"文章标题不能为空",onErrorMax:"文章标题长度过长"});
	$("#channelName").formValidator().inputValidator({min:1,onError: "请选择栏目名称"}).defaultPassed();
	$("#itemContent").formValidator({validatorGroup:1}).inputValidator({min:1,onErrorMin:"内容不能为空"});

	$('#channel').live('change',function(){
		searchArticleList();
	})
	
	$('#refresh').live('click',function(){
		reloadGrid();
	});
});

//格式化操作单元格
function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var updateBtn = "<a href='javascript:;' title='管理广告图片' class='no_unl' onclick=\"manageFN('"+rowObject.id+"');return false;\">管理广告图片</a>";
	btns.push(updateBtn);
	return btns.join(" ");
}

//修改
function manageFN(id){
	var tv = {};
	tv.text="管理广告图片";
	tv.url = "admin/cms/adv/to_manage.htm?item.id=" + id;
	tv.tabId = "cms_adv_manage"+id;
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

//重新加载
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}

//新增文章
function add(){
	var tv = {};
	tv.text = '新增文章';
	tv.url = '/admin/cms/article/to_add.htm';
	tv.tabId = "tabli_add_memberRegApply";
	tv.doc = parent.document;
	tv.refresh=true;
	showTab(tv);
}



