<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
		<meta name="baidu-site-verification" content="k66pxbcp2Z" />
		<title>Trademonster - toPay</title>
		<meta name="Keywords" content="" />
		<meta name="description" content="" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
		<link rel="stylesheet" href="static/member/register/css/register.css">
		<link rel="stylesheet" type="text/css" href="static/front/css/csr-index.css" />
	</head>
  	<body>
  		<%--头部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<%--头部end--%>
		<%--中间 --%>
		<div class="rs-content-box"  style="min-height:300px;">
			<div class="success-box">
				<i class="tishi-ico success"></i>
				<label >恭喜您付款成功！</label>
			</div>
			<div class="sc-font" style="margin:20px 0 0 0;">页面将于<i id="num">4</i>秒后跳转至 <a href="member/account/my_order.htm" style="color:#7EAE26">我的订单</a></div>
		</div>
		<%--中间end --%>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
		<script language="javascript">
			 var t = 5;
			 var time = document.getElementById("num");
			 function fun(){
				  t--;
				  time.innerHTML = t;
				  if(t<=0){
			   			location.href = basePath + "member/account/my_order.htm";
			   			clearInterval(inter);
				  }
			 }
			 var inter = setInterval("fun();",1000);
		 </script>
  	</body>
</html>
