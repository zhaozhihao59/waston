<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}"/>
		<title>数据字典</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp"/>
	</head>
	<body class="iframe-body">
		<s:form id="addDictForm" action="doAdd" namespace="/admin/sys/dict">
			<input type="hidden" id="dictId" name="item.id" value="${item.id }"/>
			<table class="form-table">
				<tr>
					<td style="width:20%;height:0"></td>
					<td style="width:80%;height:0"></td>
				</tr>
				<tr>
					<td class="form-title">
						<em>*</em>数据名称：
					</td>
					<td class="form-content">
						<input id="name" name="item.name" value="${item.name }" type="text" class="tc-input-text w"/>
					</td>
				</tr>
				<tr>
					<td class="form-title">
						排序：
					</td>
					<td class="form-content">
						<input id="sort" name="item.sort" value="${item.sort}" type="text" class="tc-input-text w80"/>
						注意：数字越小，排序越靠前
					</td>
				</tr>
			</table>
			<span class="fr mt10">
				<a class="ui_button" id="saveBtn" href="javascript:void(0);">保存</a>
				<a class="ui_button" id="exitBtn" href="javascript:void(0);">关闭</a>
			</span>
		</s:form>
		
		<div class="prompt_box" id="errorlist"></div>
		<script type="text/javascript" src="${BASH_PATH}static/admin/sys/sys_dict_add.js?t=${sysVersion}"></script>
	</body>
</html>