<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>广告管理</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	</head>
	<body class="iframe-body">
		
		<table id="table"></table>
		<div id="pagerBar"></div>
		
		<div class="mb50"></div>
		<script src="static/admin/cms/adv_list.js"></script>
	</body>
</html>