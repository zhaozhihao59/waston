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
		<title>华诚 - 登录</title>
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
		<div class="register-box clb">
			<div class="login-lbox fl">
				<div class="zczh-font">
					登录帐号
				</div>
				<div class="sf-rdl">请在下方输入您的用户名及密码，立即登录。</div>
				<label  id="error" style="color: red"></label>
				<%-- <s:form id="loginForm" action="login/login.htm" method="post"> --%>
				<div class="login-text-box mt20">
					邮箱<input type="text" name="item.email" class="login-text email">
				</div>
				<div class="login-text-box">
					密码<input type="password" name="item.password" class="login-text pwd">
				</div>
			<%-- 	</s:form> --%>
				<a href="javascript:void(0);" class="ljdl-btn">立即登录</a>
				
				<div class="tr mt10">
					<a href="login/forget.htm" class="forgot-pwdbox">忘记密码？</a>
				</div>
			
			</div>
			<div class="double-line-box fl"></div>
			<div class="no-acc-box fl">
				<div class="zczh-font mt50">
					没有帐号？
				</div>
				<div class="sf-rdl">请点击下方按钮立即注册帐号</div>
				<a href="register/to_register.htm" class="ls-ljzc-btn transition">立即注册</a>
			</div>
		</div>
		
		<%--尾部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
		<%--尾部end--%>
		
		<script type="text/javascript">
			$(".ljdl-btn").click(doSubmit);
			function doSubmit(){
				var email=$(".email").val();
				var pwd=$(".pwd").val();
				if(email.length<=0){
					$("#error").html("<label>邮箱账号不能为空！</label>");
					return ;
				}
				if(pwd.length<=0){
					$("#error").html("<label>密码不能为空！</label>");
					return ;
				}
				$("body").mask("正在处理，请稍候...");
				$.post("login/login.htm",{"item.email":email,"item.password":pwd},function(data){
					$("body").unmask();
					if(data.status=="success"){
						
						if(data.preUrl != null) location.href=data.preUrl;
						else location.href="member/account/index.htm";
					}else{
						$("#error").html(data.message);
					}
				});
			}
		</script>
	</body>
</html>