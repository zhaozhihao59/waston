<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html >
<html>
	<head>
    	<base href="${BASE_PATH}"/>
    	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    	<title>会员后台-修改密码</title>
    	<jsp:include page="/WEB-INF/pages/common/member_inc.jsp" />
    	<link rel="stylesheet" href="static/member/product/css/product.css">
  	</head>
  	<body>
  		<%--头部 --%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<%--头部end --%>
		<div class="member-center-layout">
			<jsp:include page="/WEB-INF/pages/common/left-menu.jsp">
				<jsp:param name="menu" value="6" />
			</jsp:include>
			<div class="member-center-right-layout fl">
				<div class="mcr-lbg"></div>
				<div class="pwd-content-bg">
					<div class="pwd-title-bg">密码修改</div>
					<input id="memberId" value="${member.id }" type="hidden" />
					<div class="pt10 ml5">
						<div class="form-item">
							<span class="item-title w100">当前密码：</span>
							<input id="oldPassword" class="ui-input-text w200" type="password">
							<span id="oldPasswordTip"></span>
						</div>
						<div class="form-item">
							<span class="item-title w100">新密码：</span>
							<input id="newPassword" class="ui-input-text w200" type="password">
							<span id="newPasswordTip"></span>
						</div>
						<div class="form-item">
							<span class="item-title w100">重复新密码：</span>
							<input id="confirmPassword" class="ui-input-text w200" type="password">
							<span id="confirmPasswordTip"></span>
						</div>
						<div class="form-item">
							<span class="item-title w80"></span>
							<a href="javascript:void(0);" onclick="submitUpdPassword();return false;" class="red-btn fl">保存</a>
						</div>
					</div>
				<div class="pro-bottom-bg"></div>
			</div>
			</div>
			<div class="cb"></div>
		</div>
		<%--底部 --%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot.jsp"></jsp:include>
		<%--底部end --%>
  	</body>
  	<script src="static/lib/formvalidator/formValidator-4.1.1.js?t=${sysVersion}"></script>
	<script src="static/lib/formvalidator/formValidatorRegex.js?t=${sysVersion}"></script>
	<script src="static/lib/jquery.form.2.2.7.js?t=${sysVersion}"></script>
  	<script src="static/member/js/password.js"></script>
</html>