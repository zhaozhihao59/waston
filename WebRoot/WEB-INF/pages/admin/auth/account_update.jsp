<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>新增用户 / 修改用户</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<script type="text/javascript">
  			var roleJSON = $.parseJSON('${roleJSON}');
  		</script>
	</head>
	<body class="iframe-body">
		<%-- 基本信息 --%>
		<s:form id="roleForm" action="saveCurrentUser" namespace="/admin/user">
		<input id="itemId" name="item.id" value="${item.id }" type="hidden">
		<table class="form-table">
			<caption>
				用户信息
			</caption>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em>用户名：
				</td>
				<td class="form-content" width="30%">
					<input id="account" name="item.account" value="${item.account }" <c:if test="${item.id != null }">disabled</c:if> class="tc-input-text w" type="text"/>
					<input id="accountHidden" value="${item.account }" type="hidden"/>
				</td>
				<td class="form-content" width="58%">
					<span id="accountTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					初始密码：
				</td>
				<td class="form-content">
					<c:if test="${item.isChangePwd == 0 || item.isChangePwd == null}">
						<span class="status-red">初始密码未修改</span>
					</c:if>
					<c:if test="${item.isChangePwd == 1}">
						<span class="status-green">初始密码已修改</span>
					</c:if>
				</td>
				<td class="form-content">
					<span id="passwordTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					<em>*</em>用户姓名：
				</td>
				<td class="form-content">
					<input id="name" name="item.name" value="${item.name }" class="tc-input-text w" type="text" />
				</td>
				<td class="form-content">
					<span id="nameTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					用户编号：
				</td>
				<td class="form-content">
					<input id="staffId" name="item.staffId" value="${item.staffId }" class="tc-input-text w" type="text" />
				</td>
				<td class="form-content">
					<span id="staffIdTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					性别：
				</td>
				<td class="form-content">
					<label class="fl mr10">
						<input name="item.sex" value="1" class="mt2 fl mr5" <c:if test="${item.sex == 1 }">checked</c:if> type="radio"/>
						<span class="fl">男</span>
					</label>
					<label class="fl mr10">
						<input name="item.sex" value="0" class="mt2 fl mr5" <c:if test="${item.sex == 0 }">checked</c:if> type="radio"/>
						<span class="fl">女</span>
					</label>
				</td>
				<td class="form-content">
					<span id="sexTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					生日：
				</td>
				<td class="form-content">
					<input id="birthday" name="item.birthday" value="<s:date name='item.birthday' format='yyyy-MM-dd'/>" class="tc-input-text w150" type="text" />
				</td>
				<td class="form-content">
					<span id="birthdayTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					电子邮箱：
				</td>
				<td class="form-content">
					<input id="email" name="item.email" value="${item.email }" type="text" class="tc-input-text w" />
				</td>
				<td class="form-content">
					<span id="emailTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					手机号码：
				</td>
				<td class="form-content">
					<input id="mobile" name="item.mobile" value="${item.mobile }" type="text" class="tc-input-text w" />
				</td>
				<td class="form-content">
					<span id="mobileTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					固定电话：
				</td>
				<td class="form-content">
					<input id="tel" name="item.tel" value="${item.tel }" class="tc-input-text w" type="text" />
				</td>
				<td class="form-content">
					<span id="telTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					担任角色：
				</td>
				<td id="forAddRows" class="form-content" colspan="2">
					<span id="notRow">暂无角色</span>
					<div id="rows" class="status-gray mr10">
						<div class="cb"></div>
					</div>
					<input id="sendRows" name="roleIds" type="hidden">
				</td>
				<td class="form-content">
				</td>
			</tr>
		</table>
		</s:form>
		<div id="errorlist" class="mt5 mb5"></div>
		<a id="updateBtn" class="ui_button" href="javascript:void(0);" onclick="updateUser();">保存修改</a>
		<a id="toUpdatePwd" class="ui_button" href="javascript:void(0);">重置密码</a>
		
		<div id="updatePwd" class="mt5 hide">
		<s:form id="advSellerUpdatePwdForm" namespace="/admin/mem/adv_seller" action="resetPwd">
		<input name="item.id" value="${item.id }" type="hidden" />
		<table class="form-table">
			<caption>
				重置密码
			</caption>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em>重置密码：
				</td>
				<td class="form-content" width="30%">
					<input id="resetPwd" name="item.password" class="tc-input-text w" type="password"/>
				</td>
				<td class="form-content" width="58%"><span id="accountTip"></span>
				</td>
			</tr>
		</table>
		</s:form>
		<a class="ui_button mt5" href="javascript:void(0);" id="doUpdatePwd" onclick="resetPwd();">提交修改</a>
		</div>
		<script type="text/javascript" src="static/admin/auth/account_update.js"></script>
	</body>
</html>