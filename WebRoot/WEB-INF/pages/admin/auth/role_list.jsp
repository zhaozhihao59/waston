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
			<a class="ui_button fl mr5" href="javascript:void(0);" onclick="addNewRole();return false;">新增角色</a>
			<a class="ui_button fl mr5" href="javascript:void(0);" onclick="batchDel();return false;">批量删除</a>
			<input id="searchKey" name="" value="" class="tc-input-text fl mr5 w200" type="text"/ placeholder="请输入角色名称" />
			<a class="ui_button fl mr5" href="javascript:void(0);" onclick="searchKey();return false;">查询</a>
			<a class="ui_button fl mr5" href="javascript:void(0);" onclick="reloadGrid();return false;">刷新</a>
			<div class="cb"><span id="searchKeyTip"></span></div>
		</div>
		<table id="table"></table>
		<div id="pagerBar"></div>
		<script type="text/javascript" src="static/admin/auth/role_list.js"></script>
	</body>
</html>