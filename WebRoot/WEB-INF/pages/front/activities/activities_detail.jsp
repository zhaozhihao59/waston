<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html >
<html>
<head>
<base href="${BASE_PATH}" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
<meta name="baidu-site-verification" content="k66pxbcp2Z" />
<meta property="qc:admins" content="422176645761667301356375636" />
<meta property="wb:webmaster" content="9fe48f4d2c9e0fa7" />
<title>∷WATSON & BAND∷</title>
<meta name="Description" content="WATSON & BAND, 华诚律师事务所" />
<meta name="Keywords" content="WATSON & BAND, 华诚律师事务所" />
<meta name="author" content="华诚律师事务所" />
<meta name="copyright" content="WATSON & BAND" />
<meta name="company" content="WATSON & BAND, 华诚律师事务所" />
<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
<link rel="stylesheet" type="text/css" href="static/front/css/wa-index.css" />
</head>
<body class="transition">
	<div class="cur transition">
	<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"><jsp:param name="menu" value="6" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="zz-img"></div>
			<div class="act-tt-box">讲座活动</div>
			<ul class="act-left-ul">
				<li class="cur"><a href="front/activity/activities_ListPage.htm" class="jzhd-ico">讲座活动</a></li>
			</ul>
			<div class="mt10">
				<a class="pw-wa" href="cms/to_common_problem.htm">常见问题<i></i></a>
				<!-- <a class="dy-wa" href="#">华诚通讯订阅<i></i></a> -->
			</div>
		</div>
		<div class="fr actright-box">
			<div class="sctr-right-box">
				<div class="srb-nav-box fl">讲座活动</div>
				<div class="fr srb-ncbox mr15">
					<a href="#">首页</a>
					<label>></label>
					<span>讲座活动</span>
				</div>
				<div class="cb"></div>
			</div>
			
			<div class="bmhd-tt-box">
				<div class="yy-jt-ico"></div>
				正在报名的活动
			</div>
			<div class="hd-list-box">
				<div class="hd-imgbox fl">
					<a href="javascript:void(0);"><img src="${item.photo }"></a>
				</div>
				<div class="fr hd-ccbox">
					<div class="hd-cc-tt clb"> ${item.title } <a href="front/activity/activities_ListPage.htm" class="goback-ico fr" style="margin-top:0;font-size:14px;">返回</a></div>
					<div class="addr-date mt5">
						<label>活动日期：</label><span>${item.dateStr }</span>
						<label class="ml30">活动地点：</label><span>${item.address }</span>
					</div>
				<%-- 	<div class="scl-act-content mt5">
						${dv:e(item.prom,180)}
					</div> --%>
					<s:if test="item.state == 2">
						<div class="cp ljbm-btn yjfk"  data="${item.id }">立即报名</div>
					</s:if>
					
				</div>
				<div class="cb"></div>
			</div>
			
			<div class="pd20">
				<!-- <div class="no-shuju">
					暂无活动介绍信息
				</div> -->
				<div class="bjq-css">${item.desc }</div>
			</div>	
		</div>
		<div class="cb"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
	</div>
	<jsp:include page="/WEB-INF/pages/front/comm/bm-active.jsp"></jsp:include>
</body>
</html>
