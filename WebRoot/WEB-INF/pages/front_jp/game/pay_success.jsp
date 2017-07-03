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
		<title>HEROS - 自行车赛 - 参赛报名</title>
		<meta name="Keywords" content="自行车赛" />
		<meta name="description" content="自行车赛" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css_v2/common.css" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
	</head>
	
	<body style="background:#e9e9e9;">
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<div class="game-enroll-box" style="min-height:300px;">
			<div class="geb-step3-box"></div>
			<div class="register-success-box mt50">
				报名成功
			</div>
			<div class="rs-font-box">
				恭喜您！您已成功报名，并且已经成功支付！支付金额：<span class="mn-nub" style="font-size:16px"><fmt:formatNumber value="${payAmount }" type="currency"/></span>
			</div>
			<div class="rs-font-box" style="margin-top:10px;">
				<label id="num" style="font-size:18px;">9</label> 秒后页面将跳转至首页。
			</div>
			<div class="clb mt20">
				<%-- <a href="#" class="ljqw-btn fl">立即前往</a>--%>
				<a href="index.htm" class="hdsy-btn fl ml10">回到首页</a>
			</div>
			<div class="success-fbg"></div>
		</div>
		
			
		<%--尾部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
		<%--尾部end--%>
		 <script language="javascript">
			 var t = 9;
			 var time = document.getElementById("num");
			 function fun(){
			  t--;
			  time.innerHTML = t;
			  if(t<=0){
			   location.href = "#";
			   clearInterval(inter);
			  }
			 }
			 var inter = setInterval("fun()",1000);
		</script> 
	</body>
</html>