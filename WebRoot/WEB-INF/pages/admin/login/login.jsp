<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.tocersoft.base.utils.SpringUtil"%>
<%@page import="org.springframework.security.authentication.BadCredentialsException"%>
<%@page import="org.springframework.security.authentication.DisabledException"%>
<%@page import="org.springframework.security.authentication.LockedException"%>
<%@page import="javax.security.auth.login.AccountExpiredException"%>
<%
	response.setHeader("progma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);

	final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";

	String base = request.getContextPath();
	ApplicationContext applicationContext = SpringUtil.getApplicationContext();
	if (applicationContext == null) {
%>
	<p>系统出现异常</p>
<%
	return;
	}
	String message = null;

	String error = StringUtils.trim(request.getParameter("error"));

	//if (StringUtils.equalsIgnoreCase(error, "captcha")) {
		//message = "验证码错误,请重新输入!";
	//} else {
		Exception springSecurityLastException = (Exception)session.getAttribute(SPRING_SECURITY_LAST_EXCEPTION);
		if (springSecurityLastException != null) {
			if (springSecurityLastException instanceof BadCredentialsException) {
				message = "用户名或密码错误，请重新输入";
			} else if (springSecurityLastException instanceof DisabledException) {
				message = "您的账号已被禁用,无法登录!";
			} else if (springSecurityLastException instanceof LockedException) {
				message = "您的账号已被锁定,无法登录!";
			} else if (springSecurityLastException instanceof AccountExpiredException) {
				message = "您的账号已过期,无法登录!";
			} else {
				message = "出现未知错误,无法登录!";
			}
			session.removeAttribute(SPRING_SECURITY_LAST_EXCEPTION);
		}
	//}
%>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="baidu-site-verification" content="k66pxbcp2Z" />
		<title>华诚网站后台管理系统 - 登录</title>
		<meta name="Keywords" content="" />
		<meta name="description" content="" />
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link href="static/admin/login/login.css" rel="stylesheet" type="text/css"/>
		<style>
			html{
				background:url("static/admin/login/images/loginbg.png");
				width:100%;
				height:100%;
				overflow:hidden;
			}
		</style>
	</head>
	<body>
		<form id="loginForm" action="<%=base%>/admin/login/check" method="post">
			<div class="login-mbox">
				<div class="login-tt">华诚网站后台管理系统</div>
				<div class="zp-box" style="margin-top:25px;">
					<div class="fl zh-ico"></div>
					<div class="fl pr">
						<input id="j_username" name="j_username" placeholder="用户名" onkeypress="if(event.keyCode == 13){login();}" autocomplete="off" type="text" class="imb-text pp-input">
					</div>
					<div class="cb"></div>
				</div>
				<div class="zp-box" style="margin-top:15px;">
					<div class="fl pwd-ico"></div>
					<div class="fl pr">
						<input id="j_password" name="j_password" placeholder="密码" onkeypress="if(event.keyCode == 13){login();}" autocomplete="off" type="password" class="imb-text pp-input">
					</div>
					<div class="cb"></div>
				</div>
				
				<div style="margin-top:25px;">
					<div class="fl rm-box">
						<label><input type="checkbox" class="vm"><span class="vm ml5 f14">记住我</span></label>
					</div>
					<div class="fr">
						<a href="javascript:void(0);" onclick="javascript:login();return false;" class="login-btn"></a>
					</div>
					<div class="cb"></div>
				</div>
				
				<div class="login-error-tip"><%=StringUtils.defaultIfEmpty(message,"") %></div>
				
				<div class="cp-ntt f14">
					WATSON.COM
				</div>
			</div>
		</form>
	</body>
	<script src="static/admin/login/login.js" type="text/javascript"></script>
</html>