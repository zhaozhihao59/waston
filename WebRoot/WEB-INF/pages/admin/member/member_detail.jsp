<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>会员详细</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link rel="stylesheet" href="static/base/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script src="static/base/lib/ztree/js/jquery.ztree.all-3.5.js"></script>
	</head>
	<body class="iframe-body">
		<s:form id="articleForm">
			<s:hidden name="item.id" id="itemId"/>
			<div id="login_info_bar">
				<div id="errorlist"></div>
				<table class="form-table ">
					<caption>基本信息</caption>
					<tr>
						<td class="w100" style="height:0"></td>
						<td class="w500" style="height:0"></td>
						<td class="w200" style="height:0"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							会员帐号：
						</td>
						<td class="form-content">
							<s:property value="item.account"/>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							会员名称：
						</td>
						<td class="form-content">
							<s:property value="item.name"/>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							手机号码：
						</td>
						<td class="form-content" colspan="2">
							<s:property value="item.mobile"/>
						</td>
					</tr>
					<tr>
						<td class="form-title w120">
							电子邮箱：
						</td>
						<td class="form-content" colspan="2">
							<s:property value="item.email"/>
						</td>
					</tr>
				</table>
			</div>
		</s:form>
		<div class="oper mt5 mb5">
			<a class="ui_button" href="javascript:;" id="saveUserBtn" onclick="submitForm('#articleForm');">保存</a>
		</div>
		<div class="mb50"></div>
		<script src="static/base/lib/swfupload/swfupload.js" type="text/javascript"></script>
		<script src="static/base/lib/swfupload/fileprogress.js" type="text/javascript"></script>
		<script src="static/base/lib/swfupload/handlers.js" type="text/javascript"></script>
		
		<script type="text/javascript" src="static/base/lib/kindeditor/kindeditor.js"></script>
		<script src="static/admin/member/member_add.js"></script>
	</body>
</html>