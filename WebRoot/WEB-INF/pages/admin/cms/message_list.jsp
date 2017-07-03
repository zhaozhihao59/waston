<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>文章管理</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link rel="stylesheet" href="static/base/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script src="static/base/lib/ztree/js/jquery.ztree.all-3.5.js"></script>
	</head>
	<body class="iframe-body">
		<div class="oper mb3">
		<a id="" onclick="delArticle();" class="ui_button btn_add_user fl mr5" href="javascript:void(0);">批量删除</a>
		<s:form id="searchForm">
		<div class="tc-select-div w100 fl mr5">
			<select id="isPublish" name="condition.status" class="w">
				<option value="">-- 全部 --</option>
				<option value="1">已处理</option>
				<option value="0">未处理</option>
			</select>
		</div>
		<div class="tc-select-div w100 fl mr5">
			<select id="messageType" name="condition.messageType" class="w">
				<option value="">-- 全部 --</option>
				<option value="1">预约理财师</option>
				<option value="2">申请续约</option>
				<option value="3">产品预约</option>
				<option value="4">活动预约</option>
			</select>
		</div>
		<input id="" name="condition.productName" value="" placeholder="请输入相关内容进行查询" type="text" class="tc-input-text fl w200 mr5"/>
		</s:form>
		<a class="ui_button fl mr5" href="javascript:;" onclick="searchArticleList();return false;">查询</a>
		<a id="" onclick="reloadGrid();" class="ui_button btn_add_user fl" href="javascript:void(0);">刷新</a>
		</div>
		<table id="table"></table>
		<div id="pagerBar"></div>
		<div class="mb100"></div>
		<script src="static/admin/cms/message_list.js"></script>
	</body>
</html>