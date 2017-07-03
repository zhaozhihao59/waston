<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>首页</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<style type="text/css">
			.bcf4{background-color:#f4f4f4;}
			.col_264668{color:#264668;}
			.col_264668:hover{color:#264668;text-decoration:none;}
			#hello_left{text-align:left;padding-left:10px;}
			#hello_th{font-weight:bold;text-align:left;padding-left:10px;}
		</style>
		<script type="text/javascript" src="static/admin/workbench/welcome.js"></script>
		<link href="static/admin/workbench/welcome.css" rel="stylesheet">
	</head>
	<body>
		<div class="welcome">
			<p class="tit">欢迎使用网站后台管理系统</p>
			<p class="desc">网站后台管理系统用于管理网站内容的管理平台</p>
			<p class="version">V1.0.1</p>
			<p class="copyright">@copyright 2015</p>
		</div>
	</body>
		
</html>