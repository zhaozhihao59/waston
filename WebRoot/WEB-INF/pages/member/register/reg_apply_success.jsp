<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<!doctype html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>用户注册 - 注册成功</title>
		<jsp:include page="/WEB-INF/pages/common/member_inc.jsp" />
		<link rel="stylesheet" href="static/member/register/css/register.css">
		<link rel="stylesheet" type="text/css" href="static/front/css/csr-index.css" />
	</head>
	<body>
		<%--头部 --%>
			<div style="width:1000px;margin:20px auto;">
				<a href="index.htm"><img width="227px" height="69px" src="static/front/css/images/chance-logo.png"></a>
			</div>
		<%--头部end --%>
		<%--中间 --%>
			<div class="yy-bg" ></div>
					<div class="rs-content-box" >
						<div class="success-box">
							<i class="tishi-ico success"></i>
							<label >恭喜您申请成功！</label>
						</div>
						<div class="sc-font" style="margin:20px 0 0 0;">页面将于<i id="num">4</i>秒后跳转至 <a href="index.htm" style="color:#7EAE26">登录页面</a></div>
					</div>
		<%--中间end --%>
		<%--底部 --%>
			<div style="position:fixed;bottom:0px;width: 100%;"><jsp:include page="/WEB-INF/pages/front/comm/index_foot.jsp"></jsp:include></div>
		<%--底部end --%>
		<script language="javascript">
			 var t = 4;
			 var time = document.getElementById("num");
			 function fun(){
				  t--;
				  time.innerHTML = t;
				  if(t<=0){
			   			location.href = "index.htm";
			   			clearInterval(inter);
				  }
			 }
			 var inter = setInterval("fun();",1000);
		 </script>
	</body>
</html>
