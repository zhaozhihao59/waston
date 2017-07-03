<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html >
<html >
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
		<meta name="baidu-site-verification" content="k66pxbcp2Z" />
		<title>澳新邮易购 - 重置密码</title>
		<meta name="Keywords" content="新西兰直邮商品,澳大利亚代购,新西兰代购" />
		<meta name="description" content="澳新邮易购 - 100%新西兰直邮商品,购买新西兰产品最优选择。" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
	</head>
  	<body>
  		<%--头部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<%--头部end--%>
		<div class="login-middle-box">
			<div class="login-tt">重置密码</div>
			<form id="passwordForm" method="POST" action="login/edit_forgeted_pwd.htm">
			<s:token></s:token>
				<input name="id" value="${id }" type="hidden"> 
				<input name="code" value="${code }" type="hidden"> 
				<div class="login-left-cc fl">
					<div class="login-form-item">
						<span class="login-item-title w100"><label class="red-xx">*</label>密码:</span>
						<input id="password"   name="item.password"  type="password" class="login-input w380 ml30 fl">
						<span id="passwordTip" class="fl"></span>
					</div>
					<div class="login-form-item">
						<span id="rePassword"     class="login-item-title w100"><label class="red-xx">*</label>确认密码:</span>
						<input type="password" class="login-input w380 ml30 fl">
						<span id="rePasswordTip" class="fl"></span>
					</div>
					<div class="login-form-item">
						<span class="login-item-title w100"></span>
						<a id="submitBtn" href="javascript:void(0);" class="sign-in-btn fl ml30">提交</a>
					</div>
				</div>
			</form>
	 
			<div class="cb"></div>
		</div>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
  	</body>
  	
  	<script src="static/member/login/js/login_forget_edit_password.js"></script>
</html>
