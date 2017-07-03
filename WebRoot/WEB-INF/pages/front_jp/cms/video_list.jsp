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
<script type="text/javascript" src="static/front/js/img-move/jquery.banner.revolution.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_header.jsp"><jsp:param name="menu" value="5" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="zz-img"></div>
			<div class="act-tt-box">イベント</div>
			<ul class="act-left-ul">
				<li >
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
				<li class="cur">
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
		<div class="fr actright-box">
			<div class="sctr-right-box">
				<div class="srb-nav-box fl">映像</div>
				<div class="fr srb-ncbox mr15">
					<a href="jp/index.htm">ホーム</a>
					<label>></label>
					<span>映像</span>
				</div>
				<div class="cb"></div>
			</div>
			<div class="article-right-ep clb">
				 <c:forEach items="${videoArticleList}" var="val">
				  <ul class="ar-ul-ep clb fl">
					<li style="width:285px;">
						<div class="boxgrid caption">
							<a href="#">
									<img src="${val.path}" width="290px" height="180px">
							</a>
							<div class="cover boxcaption" >
								<h3>${val.jpName}</h3>
								<p style="color:#f1f1f1;font-size:12px;">${val.jpSummary}</p>
								<p class="mt10"><a class="bofan-btn mt5" href="jp/cmsDetail_jp.htm?item.id=${val.id}" target="_BLANK"></a></p>
							</div>
						</div>
					</li>
				 </ul>
				 </c:forEach>
			</div>
		</div>
		<div class="cb"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
