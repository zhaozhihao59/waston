<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>团队管理</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link rel="stylesheet" href="static/base/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script src="static/base/lib/ztree/js/jquery.ztree.all-3.5.js"></script>
	</head>
	<body class="iframe-body">	
		<div id="operBar" class="oper mb3">
			<a class="ui_button fl mr5" href="javascript:void(0);" onclick="add();return false;">新增团队</a>
			<a id="doBatchDelBtn" class="ui_button fl mr5" href="javascript:void(0);"  >批量删除</a>
			<input id="searchKey" name="" value="" class="tc-input-text fl mr5 w200" type="text" placeholder="输入团队名称查询"/>
			<a class="ui_button fl mr5" href="javascript:void(0);" onclick="searchTable();return false;">查询</a>
			<a class="ui_button fl mr5" href="javascript:void(0);" onclick="reloadGrid();return false;">刷新</a>
			<div class="cb"></div>
		</div>
		<table id="table"></table>
		<div id="pagerBar"></div>
		<script src="static/admin/member/mem_team_list.js"></script>
	</body>
</html>