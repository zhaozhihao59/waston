<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>用户管理</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link rel="stylesheet" href="static/base/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script src="static/base/lib/ztree/js/jquery.ztree.all-3.5.js"></script>
		<script src="static/base/lib/spliter/spliter.js"></script>
	</head>
	<body class="iframe-body">
		<div id="categoryTreeDiv" class="category-tree fl" style="width:150px;">
			<div class="bg-caption">
				<span class="bgc-title">部门组织架构</span>
			</div>
			<div id="treeDiv" class="depart-tree">
				<div class="min-height"></div>
				<ul id="categoryTree" class="ztree"></ul>
				<div id="loadTip" class="load-tip">
					<p>正在加载部门</p>
				</div>
			</div>
		</div>
		
		<div id="categoryContentDiv" class="category-list fl">
			<div id="operBar" class="oper mb3">
			<s:form id="searchForm">
			<input id="departId" name="condition.departId" type="hidden"/>
				<a class="ui_button fl mr5" href="javascript:void(0);" onclick="addNewUser();return false;">新增用户</a>
				<a class="ui_button fl mr5" href="javascript:void(0);" onclick="doBatchDel();return false;">批量删除</a>
				<input id="searchKey" name="condition.searchKey" value="" class="tc-input-text fl mr5 w200" type="text" placeholder="请输入账号/用户姓名"/>
				<a class="ui_button fl mr5" href="javascript:void(0);" onclick="searchUserList();return false;">查询</a>
				<a class="ui_button fl mr5" href="javascript:void(0);" onclick="reloadGrid();return false;">刷新</a><%--
				<a class="ui_button fl mr5" href="javascript:void(0);" onclick="manageUser();return false;">设置主管</a>
				<a class="ui_button fl mr5" href="javascript:void(0);" onclick="manageNoUser();return false;">取消主管</a>
				--%><div class="cb"></div>
				</s:form>
			</div>
			<table id="table"></table>
			<div id="pagerBar"></div>
		</div>
		
		<div id="rMenu" style="visibility: hidden; top: 130px; left: 89px;">
			<ul class="r-menu">
				<li class="bd bd_add"><a id="addNodeBtn" onclick="addChild();return false;" href="javascript:void(0);">新增菜单</a></li>
				<li class="bd bd_add"><a id="editNodeBtn" href="javascript:void(0);">修改菜单</a></li>
				<li class="bd bd_delete"><a id="selNodeDelete"  href="javascript:void(0);">删除菜单</a></li>
				<li class="bd bd_refresh"><a id="selNodeRefresh"  href="javascript:void(0);">刷新菜单</a></li>
			</ul>
		</div>
 		<script type="text/javascript" src="static/admin/auth/user_list.js"></script>
	</body>
</html>