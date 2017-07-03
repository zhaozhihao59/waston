<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html >
<html >
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
		<meta name="baidu-site-verification" content="k66pxbcp2Z" />
		<title>华诚 - 注册</title>
		<meta name="Keywords" content="自行车赛" />
		<meta name="description" content="自行车赛" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css_v2/common.css" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<div class="register-top-bg"></div>
		<div class="inner-bg"></div>
		<div class="register-box">
			<div class="zczh-font">
				注册帐号
			</div>
			<div class="sf-rdl">您是否已经注册帐号？<a href="login/index.htm">立即登录</a></div>
			
			<s:form id="saveForm" action="register/registerSuccess.htm" method="post">
				<div class="form-item" style="margin-top:20px;">
					<span class="item-title fl w100"><em>*</em>姓名：</span>
					<input id="name" name="item.name" class="ui-input-text w300 fl" autocomplete="off" type="text"/>
					<span id="nameTip" class="fl"></span>
				</div>
				<div class="form-item">
					<span class="item-title fl w100"><em>*</em>手机：</span>
					<input id="mobile" name="item.mobile" class="ui-input-text w300 fl" autocomplete="off" type="text"/>
					<span id="mobileTip" class="fl"></span>
				</div>
				<div class="form-item">
					<span class="item-title fl w100"><em>*</em>邮箱：</span>
					<input id="email" name="item.email" class="ui-input-text fl w300" autocomplete="off" type="text"/>
					<span id="emailTip" class="fl"></span>
				</div>
				<div class="form-item">
					<span class="item-title fl w100"><em>*</em>密码：</span>
					<input id="password" name="item.password" type="password" class="ui-input-text fl w300"  autocomplete="off" type="text"/>
					<span id="passwordTip" class="fl"></span>
				</div>
				<div class="form-item">
					<span class="item-title fl w100"></span>
					<ul class="state-ul" style="margin-top:7px;">
						<li id="pwdWeak">弱</li>
						<li id="pwdNormal">中</li>
						<li id="pwdStrong">强</li>
					</ul>
				</div>
				<div class="form-item">
					<span class="item-title fl w100"><em>*</em>确认密码：</span>
					<input id="rePassword" type="password" class="ui-input-text fl w300" autocomplete="off" type="text"/>
					<span id="rePasswordTip" class="fl"></span>
				</div>
				<div class="form-item">
					<span class="item-title fl w100"><em>*</em>验证码：</span>
					<input class="ui-input-text fl w100 vm" type="text">
					<a href="">
						<img class="vm" src="static/front/css/images/yzm-ico.png">
					</a>
				</div>
				<div class="form-item">
					<span class="item-title w100"></span>
					<div class="item-font" style="line-height:28px;">
					<label class="vm">
						<input class="vm" type="checkbox" checked="checked">
						<span class="ml5 vm" style="color:#4fa6bc;">我已阅读并同意</span>
					</label>
					<a class="register-treaty vm" href="">《用户注册协议》</a>
					</div>
				</div>
			</s:form>
			<div class="form-item">
				<span class="item-title w100"></span>
				<div class="item-font">
					<a class="register-btn fl" href="javascript:void(0);">注册</a>
				</div>
			</div>
		</div>
		
		<%--尾部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
		<%--尾部end--%>
		
		<script src="static/member/register/js/register_index.js"></script>
	</body>
</html>