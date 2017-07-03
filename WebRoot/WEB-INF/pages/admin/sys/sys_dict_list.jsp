<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>数据字典</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	</head>
	<body class="iframe-body">
		<table class="form-table mb3">
			<tr>
				<td style="width:10%;height:0"></td>
				<td style="width:50%;height:0"></td>
				<td style="width:40%;height:0"></td>
			</tr>
			<tr>
				<td class="form-title">
					数据名称：
				</td>
				<td class="form-content">
					<div class="tc-select-div w150 fl">
						<s:select id="globalDict"
								  list="sysDictList" 
								  listKey="id" 
								  listValue="name" 
								  cssClass="w" onchange="changeSelect();return false;">
						</s:select>
					</div>
					<a class="ui_button ml5" href="javascript:void(0);" onclick="toAddDict();">新增数据名称</a>
					<a class="ui_button" href="javascript:void(0);" onclick="toUpdateDict();">修改数据名称</a>
					<a class="ui_button" href="javascript:void(0);" onclick="delDict();">删除数据名称</a>
				</td>
				<td class="form-content"></td>
			</tr>
		</table>
		
		<div class="oper mb3">
			<a class="ui_button" href="javascript:void(0);" onclick="toAddDictItem();">新增数据项</a>
			<a id="batchDelBtn" class="ui_button" href="javascript:void(0);" onclick="doBatchDel();return false;">批量删除</a>
			<a class="ui_button" href="javascript:void(0);" onclick="reloadGrid();return false;">刷新</a>
		</div>
		
		<table id="table"></table>
		<div id="pagerBar"></div>
		
		<script type="text/javascript" src="${BASE_PATH}static/admin/sys/sys_dict_list.js?t=${sysVersion}"></script>
	</body>
</html>