<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH }">
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<script type="text/javascript">var swfu;var basePath = '<%=request.getContextPath()%>';</script>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="robots" content="all" />
		<title>查看详细</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	</head>
	<body class="iframe-body">
		<div class="info-main">
			<input id="sid" value="${item.id}" type="hidden"/>
			<input id="messageType" name="item.messageType" value="${item.messageType}" type="hidden"/>
			<div class="message-person">
				<c:if test="${item.status == null || item.status == 0}">
				<div class="msg-status fl no-reply">未处理</div>
				</c:if>
				<c:if test="${item.status == 1}">
				<div class="msg-status fl yes-reply">已处理</div>
				</c:if>
				<div class="msg-person-info fl">
					<span class="ml20">留言人：${item.createName }</span>
					<span class="ml20">留言时间：<s:date format='yyyy-MM-dd H:M' name="item.createDate"/></span>
					<span class="ml20">手机：${item.mobile}</span>
					<span class="ml20">电子邮箱：${item.email}</span>
					<span class="ml20">其他联系方式：${item.other}</span>
				</div>
				<div class="cb"></div>
			</div>
			<div class="message-content">
				${item.content}
			</div>
			<c:if test="${item.messageType == 1 || item.messageType == 3 || item.messageType == 4}">
			<div class="message-relative mt10">
				<div class="msg-relat-title">其他内容</div>
				<div class="msg-relat-content">
					<c:if test="${item.messageType == 3}">
					<div class="msg-relat-item">
						<div class="msg-item-title">预约产品：</div>
						<div class="msg-item-content">
							<a href="product/detail.htm?id=${item.productId }" target="_blank">${item.productName}</a>
						</div>
						<div class="cb"></div>
					</div>
					</c:if>
					<c:if test="${item.messageType == 1}">
					<div class="msg-relat-item">
						<div class="msg-item-title">预约理财师：</div>
						<div class="msg-item-content">
							<a href="cms/detail.htm?id=${item.productId }" target="_blank">${item.productName}</a>
						</div>
						<div class="cb"></div>
					</div>
					</c:if>
				</div>
			</div>
			</c:if>
		</div>
		<%-- 
		<c:if test="${item.status == 1}">
		<div class="info-main mt10">
			<div class="message-person">
				<div class="msg-status fl yes-reply">回复</div>
				<div class="msg-person-info fl">
					<span class="ml20">回复人：${item.createName }</span>
					<span class="ml20">回复时间：<s:date format='yyyy-MM-dd H:M' name="item.createDate"/></span>
				</div>
				<div class="cb"></div>
			</div>
			<div class="message-content">
				${item.content }
			</div>
		</div>
		</c:if>
		--%>
		<div class="oper">
		<a onclick="isReply()" href="javascript:void(0)" class="ui_button mt10" style="width:100px;text-align:center;">标记为已处理</a>
		</div>
		<%-- <a onclick="doDel()" href="javascript:void(0)" class="ui_button mt10">删除</a>--%>
		<script src="static/admin/cms/message_detail.js"></script>
	</body>
</html>






















