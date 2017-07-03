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
<link rel="stylesheet" type="text/css" href="static/front_jp/comm/jp-wa-index.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_header.jsp"><jsp:param name="menu" value="5" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="news-left-img"></div>
			<div class="act-tt-box">ニュースと出版物</div>
			<ul class="act-left-ul">
				<li class="cur">
					<a href="jp/cms_ListPage_jp.htm?item.id=3d5ffe8b-e890-11e4-ae9b-00266c0e7760" " class="xw-ico">
						華誠の動向
					</a>
				</li>
				<li>
					<a href="jp/cms_ListPage_jp.htm?item.id=439c9076-e890-11e4-ae9b-00266c0e7760" class="cbw-ico">
						業界の動向
					</a>
				</li>
				<li>
					<a href="jp/pub_ListPage_jp.htm?item.id=5da5b951-e890-11e4-ae9b-00266c0e7760" class="hcjb-ico">
						出版物
					</a>
				</li>
				<li>
					<a href="cms_jp/video_list_jp.htm" class="yew02-ico">
						映像
					</a>
				</li>
			</ul>
			<div class="mt10">
				<a class="pw-wa" href="cms_jp/to_common_problem_jp.htm">
					よくある問題
					<i></i>
				</a>
				<!-- 
				<a class="dy-wa" href="#">
					华诚通讯订阅 -->
					<i></i>
				</a>
			</div>
		</div>
		<div class="fr actright-box" style="padding-bottom:10px;">
			<div class="cms-right-box">
				<div class="news-title-span">${item.jpName }</div>
				<div class="news-data mt10">
					<span class="news-date"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
					<span class="news-publisher">発表者：${item.authorJp}</span>
					<%-- <span class="news-count">阅读量：${item.readNum} 次</span> --%>
				</div>
			</div>
			<div class="detail-img"  >
				<p class="f14 mt10" style="color:#666;line-height:1.6em;text-indent:20px;">
					${item.jpContent}
				</p>
			</div>
			
			<div class="mt10">
				<jsp:include page="/WEB-INF/pages/front/comm/fx-ico.jsp"></jsp:include>
			</div>
		</div>
		<div class="cb"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
