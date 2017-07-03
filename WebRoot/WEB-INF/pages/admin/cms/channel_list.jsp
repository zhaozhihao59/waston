<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>栏目管理</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	</head>
	<body class="iframe-body">
		
		<table id="table"></table>
		<div id="pagerBar"></div>
		
		<div class="mb100" id="lastBottomDom"></div>
		<script src="static/admin/cms/channel.js"></script>
	</body>
</html>