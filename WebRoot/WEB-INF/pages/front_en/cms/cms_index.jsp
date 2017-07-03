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
		<link rel="stylesheet" type="text/css" href="static/front_en/comm/en-wa-index.css" />
	</head>
<body>
	<jsp:include page="/WEB-INF/pages/front_en/comm/front_header.jsp"><jsp:param name="menu" value="5" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front_en/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="news-left-img"></div>
			<div class="act-tt-box">News publication</div>
			<ul class="act-left-ul">
			
				<li <c:if test="${item.id=='3d5ffe8b-e890-11e4-ae9b-00266c0e7760' }">class="cur"</c:if>>
					<a href="en/cms_ListPage_en.htm?item.id=3d5ffe8b-e890-11e4-ae9b-00266c0e7760"  class="xw-ico">
						Watson Dynamic
					</a>
				</li>
				<li  <c:if test="${item.id=='439c9076-e890-11e4-ae9b-00266c0e7760' }">class="cur"</c:if>>
					<a href="en/cms_ListPage_en.htm?item.id=439c9076-e890-11e4-ae9b-00266c0e7760" class="cbw-ico">
						Industry dynamics
					</a>
				</li>
				<li>
					<a href="en/pub_ListPage_en.htm?item.id=5da5b951-e890-11e4-ae9b-00266c0e7760" class="hcjb-ico">
						Publications
					</a>
				</li>
				<li>
					<a href="cms_en/video_list_en.htm" class="yew02-ico">
						video
					</a>
				</li>
			</ul>
			<div class="mt10">
				<a class="pw-wa" href="cms_en/to_common_problem_en.htm">
					Common problem
					<i></i>
				</a>
				<!-- 
				<a class="dy-wa" href="#">
					华诚通讯订阅 -->
					<i></i>
				</a>
			</div>
		</div>
		<input id="itemId" type="hidden" value="${item.id }">
		<div id="hcActive">
			<jsp:include page="/WEB-INF/pages/front_en/cms/cms_index_inc.jsp"></jsp:include>
		</div>
		<div class="cb"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/front_en/comm/front_foot_v2.jsp"></jsp:include>
</body>
	<script>
		/* $(".news li").click(function(){
			$(this).parent().addClass("cur").siblings().removeClass("cur");
		}); */
	</script>
</html>
