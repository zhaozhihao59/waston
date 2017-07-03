<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>角色权限</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	</head>
	<body class="iframe-body">
		<div id="operBar" class="oper mb3">
			<a class="ui_button fl mr5" href="javascript:void(0);" onclick="selectRole();return false;">批量选择</a>
			<input id="searchkey" name="" value="" class="tc-input-text fl mr5 w200" type="text" placeholder="角色名称" />
			<a class="ui_button fl mr5" href="javascript:void(0);" onclick="searchTable();return false;">查询</a>
			<a class="ui_button fl mr5" href="javascript:void(0);" onclick="reloadGrid();return false;">刷新</a>
			<a class="ui_button fl mr5" href="javascript:void(0);" onclick="closeWin();return false;">关闭</a>
			<div class="cb"></div>
		</div>
		<table id="table"></table>
		<div id="pagerBar"></div>
		<script type="text/javascript" src="static/admin/auth/user_add_choose_role.js"></script>
	</body>
</html>