<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>文章管理</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link rel="stylesheet" href="static/base/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<link href="static/base/lib/spliter/spliter.css" rel="stylesheet" type="text/css" />
		<script src="static/base/lib/spliter/spliter.js"></script>
		<script src="static/base/lib/ztree/js/jquery.ztree.all-3.5.js"></script>
	</head>
	<body class="iframe-body">
		<div id="categoryTreeDiv" class="category-tree fl" style="width:140px;">
			<div class="bg-caption">
				<span class="bgc-title">栏目管理</span>
			</div>
			<div id="treeDiv" class="depart-tree">
				<div class="min-height"></div>
				<ul id="categoryTree" class="ztree"></ul>
			</div>
		</div>
		<div id="categoryContentDiv" class="category-list fl">
		<div id="operBar" class="oper mb3">
			<s:form id="searchForm">
			<input id="channelId" name="condition.channelId" type="hidden"/>
			<a id="addArticle" class="ui_button btn_add_user fl" href="javascript:void(0);">新增文章</a>
			<a id="delArticle" class="ui_button btn_add_user fl ml5" href="javascript:void(0);">批量删除</a>
			<input id="fastSearch" name="condition.articleName" value="" class="tc-input-text fl ml5 w200" type="text"/>
			<a class="ui_button fl ml5" href="javascript:;" onclick="searchArticleList();">查询</a>
			<%-- <a id="searchMoreBtn" class="ui_button fl ml5" href="javascript:;">更多查询</a> --%>
			<a id="refresh" class="ui_button btn_add_user fl ml5" href="javascript:void(0);">刷新</a>
			<a id="publish" class="ui_button btn_add_user fl ml5" href="javascript:void(0);">发布</a>
			<a id="unPublish" class="ui_button btn_add_user fl ml5" href="javascript:void(0);">取消发布</a>
			<%-- <input id="isMoreSearch" value="0" type="hidden"/>--%>
			</s:form>
		</div>
	
		<table id="table"></table>
		<div id="pagerBar"></div>
		<div id="rMenu" style="visibility: hidden; top: 130px; left: 89px;">
			<ul class="r-menu">
				<li class="bd bd_add"><a id="addNodeBtn" href="javascript:void(0);">新增栏目</a></li>
				<li class="bd bd_add"><a id="editNodeBtn" href="javascript:void(0);">修改栏目</a></li>
				<li class="bd bd_delete"><a id="selNodeDelete"  href="javascript:void(0);">删除栏目</a></li>
				<li class="bd bd_refresh"><a id="selNodeRefresh"  href="javascript:void(0);">刷新栏目</a></li>
			</ul>
		</div>
		</div>
		<div class="mb100"></div>
		<script src="static/admin/cms/article_list.js"></script>
	</body>
</html>