<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>操作权限</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link rel="stylesheet" href="static/base/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script src="static/base/lib/ztree/js/jquery.ztree.all-3.5.js"></script>
		<script src="static/base/lib/spliter/spliter.js"></script>
	</head>
	<body class="iframe-body">
		<div id="categoryTreeDiv" class="category-tree fl" style="width:150px;">
			<div class="bg-caption">
				<span class="bgc-title">权限设置</span>
			</div>
			<div id="treeDiv" class="depart-tree">
				<div class="min-height"></div>
				<ul id="categoryTree" class="ztree"></ul>
				<div id="loadTip" class="load-tip">
					<p>正在加载菜单</p>
					<p>…………</p>
				</div>
			</div>
		</div>
		<div id="categoryContentDiv" class="category-list fl" style="margin-top:-1px;">
			<form id="updateForm" action="admin/auth/right/doUpdateRight.htm" method="POST">
			<input id="rightId" name="item.id" type="hidden"/>
			<input id="parentId" name="item.parentId" type="hidden"/>
			<table class="form-table ">
				<caption>操作权限</caption>
				<tr>
					<td style="height:0;width:12%"></td>
					<td style="height:0;width:40%"></td>
					<td style="height:0;width:48%"></td>
				</tr>
				<tr>
					<td class="form-title">
				  		<em>*</em>菜单名称：
				  	</td>
					<td class="form-content">
						<input id="name" name="item.name" value="" class="tc-input-text w" type="text"/>
					</td>
					<td class="form-content"></td>
				</tr>
				<tr>
					<td class="form-title">
				  		<em>*</em>提示文字：
				  	</td>
					<td class="form-content">
						<input id="tip" name="item.tip" value="" class="tc-input-text w" type="text"/>
					</td>
					<td class="form-content"></td>
				</tr>
				<tr>
					<td class="form-title">
				  		<em>*</em>功能链接：
				  	</td>
					<td class="form-content">
						<input id="funUrl" name="item.url" value="" class="tc-input-text w" type="text"/>
					</td>
					<td class="form-content"></td>
				</tr>
				<tr>
					<td class="form-title">
				  		<em>*</em>排序参数：
				  	</td>
					<td class="form-content">
						<input id="sort" name="item.sort" value="" class="tc-input-text w80" type="text"/>
						<span class="status-gray-666 ml5">注意：排序参数越小越靠前</span>
					</td>
					<td class="form-content"></td>
				</tr>
				<tr>
					<td class="form-content"></td>
					<td class="form-content" colspan="2">
						<a id="saveUserBtn" class="ui_button" style="margin:5px 0;width:85px;text-align:center;" href="javascript:;" onclick="submitForm();">保存修改</a>
					</td>
				</tr>
			</table>
			<div class="tip-div">
				<p class="tip-p">操作提示：</p>
				<p class="tip-p">1、右键单击左侧菜单项可对菜单项进行新增、修改、删除、刷新操作。</p>
			</div>
		</div>
		
		<div id="rMenu" style="visibility: hidden; top: 130px; left: 89px;">
			<ul class="r-menu">
				<li class="bd bd_add"><a id="addNodeBtn" onclick="addChild();return false;" href="javascript:void(0);">新增菜单</a></li>
				<li class="bd bd_add"><a id="editNodeBtn" href="javascript:void(0);">修改菜单</a></li>
				<li class="bd bd_delete"><a id="selNodeDelete" href="javascript:void(0);">删除菜单</a></li>
				<li class="bd bd_refresh"><a id="selNodeRefresh" href="javascript:void(0);">刷新菜单</a></li>
			</ul>
		</div>
		<script src="static/admin/auth/right_list.js"></script>
	</body>
</html>