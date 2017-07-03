<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH }">
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>新增\修改栏目</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	</head>
	<body class="p10">
		<div>
			<input type="hidden" id="parentId" value="${parentId }" />
			<input type="hidden" id="nodeId" value="${nodeid}" />
			<table class="form-table ">
				<tr>
					<td class="form-title w100">
						类别名称：
					</td>
					<td class="form-content" colspan="2">
						<input id="name" value="${item.name }" type="text" maxlength="100" class="tc-input-text w150"/>
						<span id="nameDiv" style="display: none; color: red">名称不能为空</span>
					</td>
				</tr>
				<tr>
					<td class="form-title w100">
						备注：
					</td>
					<td class="form-content" colspan="2">
						<textarea id="remark"  class="tc-textarea w300 h100">${item.remark }</textarea>
					</td>
				</tr>
				<tr>
					<td class="form-title w100" id="sortTd">
						排序：
					</td>
					<td class="form-content" colspan="2">
						<input type="text" maxlength="5" class="tc-input-text w100"
							value="${item.sort}" id="sortNum" />
						<label>
							友情提示：排序数字越小越靠前
						</label>
						<span id="sortDiv" style="display: none; color: red">排序不能为空</span>
					</td>
				</tr>
			</table>
			<div class="tr p10">
				<c:if test="${empty nodeid}">
					<a class="ui_button" href="javascript:;"
						onclick="addCmsChannel();">保存</a>
				</c:if>
				<c:if test="${not empty nodeid}">
					<a class="ui_button" href="javascript:;"
						onclick="updateCmsChannel();">保存</a>
				</c:if>
				<a class="ui_button" href="javascript:;"
					onclick="exitWin();">退出</a>
			</div>
		</div>
			<script src="static/admin/cms/channel_add.js"></script>
	</body>
</html>
