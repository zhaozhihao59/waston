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
		<s:form id="subscriptForm" action="send_email" namespace="/admin/subscribeSend">
		<input id="itemId" name="item.id" value="${item.id }" type="hidden">
		<div id="errorlist" class="mt5 mb5"></div>
		<a id="updateBtn" class="ui_button mb5" href="javascript:void(0);" onclick="saveSubscribe();">发送</a>
		<table class="form-table">
			<caption>
				发送邮件
			</caption>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em>收件人：
				</td>
				<td class="form-content" width="30%">
					<a href="javascript:void(0);" class="ui_button fl" onclick="chooseSender();return false;">选择收件人</a>
				</td>
				<td class="form-content" width="58%">
					<span id="nameTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title" width="12%">
					已选择的收件人：
				</td>
				<td class="form-content" width="30%" colspan="2">
					<div id="rows" class="status-gray mt5 fl">
						<div class="cb"></div>
					</div>
					<div class="cb" id="receiveUser"></div>
					<input id="sendRows" name="receiver" type="hidden">
				</td>
			</tr>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em>选择文章：
				</td>
				<td class="form-content" width="30%">
					<div id="rows1" class="status-gray mt5 fl">
						<div class="cb"></div>
					</div>
					<a href="javascript:void(0);" class="ui_button fl" onclick="chooseArticle();return false;">选择文章</a>
					<div class="cb"></div>
					<input id="sendRows1" name="roleIds" type="hidden">
				</td>
				<td class="form-content" width="58%">
					<span id="nameTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em>邮件标题：
				</td>
				<td class="form-content" width="30%">
					<input id="sendTitle" name="item.sendTitle" class="tc-input-text w355" type="text" value="${item.sendTitle }" >
				</td>
				<td class="form-content" width="58%">
					<span id="sendTitleTip"></span>
				</td>
			</tr>
		</table>
		<div class="caption-div"><span class="">文章内容</span></div>
				<div class="art-content">
					<textarea id="artContent" name="item.htmlContent" class="ui_textarea h400"> </textarea>
				</div>
		</s:form>
		
			<script src="static/base/lib/ueditor/ueditor.config.js?t=${sysVersion}"></script>
		<script src="static/base/lib/ueditor/ueditor.all.min.js?t=${sysVersion}"></script>
		<script src="static/base/lib/ueditor/lang/zh-cn/zh-cn.js?t=${sysVersion}"></script>
		<script type="text/javascript" src="static/admin/subscribe/subscribe_sendEmail.js"></script>
		
	</body>
</html>