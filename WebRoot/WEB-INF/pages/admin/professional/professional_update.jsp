<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>修改专业人员信息</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	
	</head>
	<body class="iframe-body">
		<%-- 基本信息 --%>
		<s:form id="activityForm" action="doSave" namespace="/admin/professional">
		<input id="itemId" name="item.id" value="${item.id }" type="hidden">
		<table class="form-table">
			<caption>
				修改专业人员信息
			</caption>
			<tr>
				<td class="form-title" width="15%">
					<em>*</em>专业人员名称：
				</td>
				<td class="form-content" width="30%">
					<input id="name" name="item.name" value="${item.name}"  class="tc-input-text w" type="text"/>
				</td>
				<td class="form-content" width="58%">
					<span id="nameTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em>专业人员简介：
				</td>
				<td class="form-content" width="30%">
					<input id="desc" name="item.desc" value="${item.desc}"  class="tc-input-text w" type="text"/>
				</td>
				<td class="form-content" width="58%">
					<span id="descTip"></span>
				</td>
			</tr>
			
			<tr>
				<td class="form-title">
					<em>*</em>专业人员邮箱：
				</td>
				<td class="form-content">
					<input id="email" name="item.email" value="${item.email}" class="tc-input-text w" type="text" />
				</td>
				<td class="form-content">
					<span id="emailTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					<em>*</em>资格类型：
				</td>
				<td class="form-content">
					<input id="qualification" name="item.email" value="${item.qualification}" class="tc-input-text w" type="text" />
				</td>
				<td class="form-content">
					<span id="qualificationTip"></span>
				</td>
			</tr>
						<tr>
				<td class="form-title">
					<em>*</em>工作语言：
				</td>
				<td class="form-content">
					<input id="language" name="item.language" value="${item.language}" class="tc-input-text w" type="text" />
				</td>
				<td class="form-content">
					<span id="languageTip"></span>
				</td>
			</tr>			<tr>
				<td class="form-title">
					<em>*</em>专业人员类型：
				</td>
				<td class="form-content">
					<input id="type" name="item.type" value="${item.type }" class="tc-input-text w" type="text" />
				</td>
				<td class="form-content">
					<span id="typeTip"></span>
				</td>
			</tr>
		</table>
		<div id="errorlist" class="mt5 mb5"></div>
		<a id="updateBtn" class="ui_button" href="javascript:void(0);" onclick="updateActivity();">保存修改</a>
		</s:form>
		
		<script type="text/javascript" src="static/admin/professional/professional_update.js"></script>
	</body>
</html>