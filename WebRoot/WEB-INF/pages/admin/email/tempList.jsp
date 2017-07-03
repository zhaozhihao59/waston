<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>邮件模板列表</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	</head>
	<body class="iframe-body">
		<table id="table"></table>
		<div id="pagerBar"></div>
	<script type="text/javascript">
	var gh = $(window).height() - $('#operBar').height() - 91;
	//渲染表格骨架
	$("#table").jqGrid({
	    url:'admin/email/ajaxTempList.htm?m='+Math.random(),
	    datatype:"json",
	    colNames:['操作','模板','内容'],
		colModel:[ 
			{name:"id",index:"id",width:98,formatter:optFormater},
			{name:"type",width:55,index:'type'},
			{name:'content',index:'content'}
		], 
		caption:"邮件模板列表",
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
	//渲染分页工具条
	$("#table").jqGrid('navGrid','#pagerBar');
	
	//格式化操作单元格
function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var updateBtn = "<a href='javascript:;' title='查看\修改' class='no_unl' onclick=\"updateFN('"+rowObject.id+"');return false;\">查看/修改</a>";
	btns.push(updateBtn);
	return btns.join(" ");
}
	function updateFN(id){
		var url = basePath + "admin/email/viewTemplate.htm?id="+id;
		$.dialog.open(url,{
			width:900,
			height:600,
			title:'修改模板',
			lock:true
		});
	
	}
	</script>
	</body>
</html>