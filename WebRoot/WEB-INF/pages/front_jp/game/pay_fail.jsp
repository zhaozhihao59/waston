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
			<div class="img-xx-ico mt50">
				<div>支付失败</div>	
				<span  style="font-size:12px;color:#888">不好意思，支付失败！错误代码：<span class="fb" style="color:#ff3300">${retncode}</span>
			</div>
			<a class="link-a" style="margin-left:60px" href="game/enroll.htm">返回报名页面</a>	
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