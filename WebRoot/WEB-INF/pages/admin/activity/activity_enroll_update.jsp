<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>修改订单</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<script type="text/javascript">
  			var roleJSON = $.parseJSON('${roleJSON}');
  		</script>
	</head>
	<body class="iframe-body">
		<%-- 基本信息 --%>
		<s:form id="activityForm" action="doSave" namespace="/admin/activityEnroll">
		<input id="itemId" name="item.id" value="${item.id }" type="hidden">
		<table class="form-table">
			<caption>
				修改活动信息
			</caption>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em>活动名称：
				</td>
				<td class="form-content" width="30%">
					<input id="activityName" name="item.activityName" value="${item.activityName }"  class="tc-input-text w" type="text"/>
				</td>
				<td class="form-content" width="58%">
					<span id="activityNameTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em>姓名：
				</td>
				<td class="form-content" width="30%">
					<input id="name" name="item.name" value="${item.name }"  class="tc-input-text w" type="text"/>
				</td>
				<td class="form-content" width="58%">
					<span id="nameTip"></span>
				</td>
			</tr>
			
			<tr>
				<td class="form-title">
					<em>*</em>手机号码：
				</td>
				<td class="form-content">
					<input id="mobile" name="item.mobile" value="${item.mobile }" class="tc-input-text w" type="text" />
				</td>
				<td class="form-content">
					<span id="mobileTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					<em>*</em>邮箱地址：
				</td>
				<td class="form-content">
					<input id="email" name="item.email" value="${item.email }" class="tc-input-text w" type="text" />
				</td>
				<td class="form-content">
					<span id="emailTip"></span>
				</td>
			</tr>
			
		</table>
		<div id="errorlist" class="mt5 mb5"></div>
		<a id="updateBtn" class="ui_button" href="javascript:void(0);" onclick="updateActivity();">保存修改</a>
		</s:form>
		
		<script type="text/javascript" src="static/admin/activity/activity_enroll_update.js"></script>
	</body>
</html>