<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>部门树</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link rel="stylesheet" href="static/base/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script src="static/base/lib/ztree/js/jquery.ztree.all-3.5.js"></script>
	</head>
	<body class="iframe-body">
		<div class="" >
			<a href="javascript:void(0);" class="ui_button " style="display: block;width: 25px;margin: 10px 0px 0px;" onclick="chooses();">选择</a>
		</div>
		<div class="mt10" style="background: none repeat scroll 0% 0% rgb(249, 249, 249); padding: 10px 5px; border: 1px solid rgb(238, 238, 238);">
				<ul id="departTree" class="ztree ztree-select" ></ul>
		</div>
		<script type="text/javascript" src="static/admin/auth/js/user_add_choose_depart.js"></script>
	</body>
</html>