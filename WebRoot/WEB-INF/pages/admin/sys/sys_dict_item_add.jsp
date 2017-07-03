<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}"/>
		<title>数据字典</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp"/>
	</head>
	<body class="iframe-body">
		<s:form action="doEditSysDictItem" namespace="/admin/sys/dict" id="addDictForm">
			<input type="hidden" id="globalDictId" name="sysDictItem.dictId" value="${item.id }"/>
			<table class="form-table">
				<tr>
					<td style="width:20%;height:0"></td>
					<td style="width:80%;height:0"></td>
				</tr>
				<tr>
					<td class="form-title w100">
						数据名称：
					</td>
					<td class="form-content">
						${item.name}
					</td>
				</tr>
				<tr>
					<td class="form-title">
						数据项：
					</td>
					<td class="form-content">
						<input type="text" class="tc-input-text fl mr5 w" name="sysDictItem.name" id="dictItemName" />
					</td>
				</tr>
				<tr>
					<td class="form-title">
						排序：
					</td>
					<td class="form-content">
						<input type="text" class="tc-input-text fl mr5 w80" name="sysDictItem.sort" id="dictItemSort" />
						注意：数字越小，排序越靠前
					</td>
				</tr>
			</table>
			<span class="fr mt10">
				<a class="ui_button" id="saveBtn" href="javascript:void(0);">保存</a>
				<a class="ui_button" id="exitBtn" href="javascript:void(0);">关闭</a>
			</span>
		</s:form>
		
		<div class="prompt_box" id="errorlist">
		</div>
		<script type="text/javascript" src="${BASH_PATH}static/admin/sys/sys_dict_item_add.js?t=${sysVersion}"></script>
	</body>
</html>