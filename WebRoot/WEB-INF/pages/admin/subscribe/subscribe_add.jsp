<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>添加订单</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<script type="text/javascript">
  			var roleJSON = $.parseJSON('${roleJSON}');
  		</script>
	</head>
	<body class="iframe-body">
		<%-- 基本信息 --%>
		<s:form id="subscriptForm" action="doSave" namespace="/admin/subscribe">
		<input id="itemId" name="item.id" value="${item.id }" type="hidden">
		<table class="form-table">
			<caption>
				订阅名单
			</caption>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em>订阅者姓名：
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
			
			<tr>
				<td class="form-title">
					<em>*</em>公司名称：
				</td>
				<td class="form-content">
					<input id="companyName" name="item.companyName" value="${item.companyName }" class="tc-input-text w" type="text" />
				</td>
				<td class="form-content">
					<span id="companyNameTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					职务：
				</td>
				<td class="form-content">
					<input id="position" name="item.position" value="${item.position }" type="text" class="tc-input-text w" />
				</td>
				<td class="form-content">
					<span id="positionTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					期刊类型名称：
				</td>
				<td class="form-content">
					<input id="channelName" name="item.channelName" value="${item.channelName}"  type="hidden" class="tc-input-text w" />
					<div class="tc-select-div w">
						<SELECT id="channelId" class="w" name="item.channelId">
						<c:forEach items="${cmsChannel}" var="obj">
							<option value="${obj.id }">${obj.name}</option>
						</c:forEach>
					</SELECT>
					</div>
				</td>
				<td class="form-content"><span id="channelTip"></span>
				</td>
			</tr>
		</table>
		<div id="errorlist" class="mt5 mb5"></div>
		<a id="updateBtn" class="ui_button" href="javascript:void(0);" onclick="saveSubscribe();">保存</a>
		</s:form>
		<script src="static/base/lib/ueditor/ueditor.config.js?t=${sysVersion}"></script>
		<script src="static/base/lib/ueditor/ueditor.all.min.js?t=${sysVersion}"></script>
		<script src="static/base/lib/ueditor/lang/zh-cn/zh-cn.js?t=${sysVersion}"></script>
		<script type="text/javascript" src="static/admin/subscribe/subscribe_add.js"></script>
	</body>
</html>