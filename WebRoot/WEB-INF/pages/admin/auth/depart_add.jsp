<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH }">
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>新增\修改部门</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	</head>
	<body class="iframe-body">
		<form id="departForm" action="">
		<div>
			<input type="hidden" id="isParent" name="item.isParent" value="${item.isParent}" />
			<input type="hidden" id="parentId" name="item.parentId" value="${item.parentId}" />
			<input type="hidden" id="id" name="item.id" value="${item.id}" />
			<table class="form-table ">
				<tr>
					<td class="form-title w100">
						类别名称：
					</td>
					<td class="form-content" colspan="2">
						<input id="name" name ="item.name" value="${item.name}"  type="text" maxlength="100" class="tc-input-text w150"/>
						<span id="nameDiv" style="display: none; color: red">部门名称不能为空</span>
					</td>
				</tr>
				<tr>
					<td class="form-title w100">
						备注：
					</td>
					<td class="form-content" colspan="2">
						<textarea id="note" name="item.note"  class="tc-textarea w300 h100">${item.note}</textarea>
					</td>
				</tr>
				<tr>
					<td class="form-title w100" id="sortTd">
						排序：
					</td>
					<td class="form-content" colspan="2">
						<input type="text" maxlength="5" class="tc-input-text w100"
							value="${item.sort}" id="sort" name="item.sort" />
						<label>
							友情提示：排序数字越小越靠前
						</label>
						<span id="sortDiv" style="display: none; color: red">排序不能为空</span>
					</td>
				</tr>
			</table>
			<div class="tr p10">
				<c:if test="${empty item.id}">
					<a class="ui_button" href="javascript:;"
						onclick="addDepart();">保存</a>
				</c:if>
				<c:if test="${not empty item.id}">
					<a class="ui_button" href="javascript:;"
						onclick="updateDepart();">保存</a>
				</c:if>
				<a class="ui_button" href="javascript:;"
					onclick="exitWin();">退出</a>
			</div>
		</div>
	</form>
	<div id="errorlist" class="mt5 mb5"></div>
	
	<script src="static/admin/auth/depart_add.js"></script>
	</body>
</html>
