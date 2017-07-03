<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>新增团队</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link rel="stylesheet" href="static/base/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script src="static/base/lib/ztree/js/jquery.ztree.all-3.5.js"></script>
	</head>
	<body class="iframe-body">
		<s:form id="articleForm" action="doSave" namespace="/admin/mem_team">
			<s:hidden name="item.id" id="itemId"/>
			<div id="login_info_bar">
				<table class="form-table ">
					<tr>
						<td style="width:22%;height:0"></td>
						<td style="width:78%;height:0"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							<em>*</em>团队名称：
						</td>
						<td class="form-content">
							<input id="itemName" name="item.name" value="${item.name}" class="tc-input-text w" type="text"/>
						</td>
					</tr>
					<tr>
						<td class="form-title w120">
							<em>*</em>团队类型：
						</td>
						<td class="form-content">
							<input id="teamType" name="item.type" value="${item.type }" type="hidden"/>
							<label class="fl mr10">
								<input id="" name="teamType" value="1" class="fl mt4" type="radio"/>
								<span class="fl ml5">专业团队</span>
							</label>
							<label class="fl">
								<input id="" name="teamType" value="0" class="fl mt4" type="radio"/>
								<span class="fl ml5">业余团队</span>
							</label>
						</td>
					</tr>
					<tr>
						<td class="form-title w120">
							团队描述：
						</td>
						<td class="form-content">
							<textarea class="tc-textarea w h80" name="item.desc">${item.desc}</textarea>
						</td>
					</tr>
				</table>
			</div>
		</s:form>
		<div class="oper mt5 mb5 tr">
			<a id="saveUserBtn" class="ui_button" style="width:100px;text-align:center;" href="javascript:;" onclick="submitForm('#articleForm');">保存</a>
			<a id="saveUserBtn" class="ui_button" href="javascript:;" onclick="$.dialog.close();">关闭</a>
		</div>
		<div id="errorList"></div>
		<script src="static/base/lib/swfupload/swfupload.js" type="text/javascript"></script>
		<script src="static/base/lib/swfupload/fileprogress.js" type="text/javascript"></script>
		<script src="static/base/lib/swfupload/handlers.js" type="text/javascript"></script>
		
		<script type="text/javascript" src="static/base/lib/kindeditor/kindeditor.js"></script>
		<script src="static/admin/member/mem_team_add.js"></script>
	</body>
</html>