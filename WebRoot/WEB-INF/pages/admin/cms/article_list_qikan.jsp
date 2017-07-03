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
		<script src="static/base/lib/spliter.js"></script>
	</head>
	<body class="iframe-body">
			<a class="ui_button mr5 vm " id="quickSearch" href="javascript:;" onclick="selectRole();return false;">批量选择</a>
			<select id="selectChannelId" class="tc-select-div vm fl" style="height:25px">
				<option value="-1" selected="selected">请选择文章</option>
				<option value="c25f9c04-e891-11e4-ae9b-00266c0e7760">华诚合规</option>
				<option value="bba13349-e891-11e4-ae9b-00266c0e7760">华诚通讯</option>
			</select>
			<input id="fastSearch" name="condition.articleName" value="" class="tc-input-text fl mr5 w200" type="text"/>
			<a class="ui_button fl mr5 vm" href="javascript:;" onclick="searchArticleList();">查询</a>
			<a id="refresh" class="ui_button btn_add_user fl mr5 vm" href="javascript:void(0);">刷新</a>
		<table id="table"></table>
		<div id="pagerBar"></div>
		<div class="mb100"></div>
		<script src="static/admin/cms/article_list_qikan.js"></script>
	</body>
</html>