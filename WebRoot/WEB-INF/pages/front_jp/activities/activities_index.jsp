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
<body class="transition">
	<div class="cur transition">
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_header.jsp"><jsp:param name="menu" value="6" /></jsp:include>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/news_move.jsp"></jsp:include>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="zz-img"></div>
			<div class="act-tt-box">イベント</div>
			<ul class="act-left-ul">
				<li class="cur">
					<a href="front/activity_jp/activities_ListPage_jp.htm" class="jzhd-ico">
						イベント
					</a>
				</li>
				<!-- <li>
					<a href="cms_ListPage.htm?item.id=3d5ffe8b-e890-11e4-ae9b-00266c0e7760" class="jzhd-ico1">
						华诚新闻
					</a>
				</li>
				<li>
					<a href="cms_ListPage.htm?item.id=439c9076-e890-11e4-ae9b-00266c0e7760" class="jzhd-ico2">
						行业新闻
					</a>
				</li> -->
			</ul>
			<div class="mt10">
					<a class="pw-wa" href="cms_jp/to_common_problem_jp.htm">よくある問題</a>
					<i></i>
				</a>
				<!-- <a class="dy-wa" href="#">
					华诚通讯订阅 -->
					<i></i>
				</a>
			</div>
		</div>
		<div class="fr actright-box">
			<div class="sctr-right-box">
				<div class="srb-nav-box fl">イベント</div>
				<div class="fr srb-ncbox mr15">
					<a href="jp/index.htm">ホーム</a>
					<label>></label>
					<span>イベント</span>
				</div>
				<div class="cb"></div>
			</div>
			
			<div class="bmhd-tt-box">
				<div class="yy-jt-ico"></div>
				申込中の講演
			</div>
			<div class="hd-list-box">
			<c:forEach items="${listActivity}" var="activity">
				<div class="mb15 clb">
					<div class="hd-imgbox fl">
						<a target="_blank" href="front/activity_jp/activities_detail_jp.htm?selIds=${activity.id }"><img src="${activity.photo }"></a>
					</div>
					<div class="fr hd-ccbox">
						<div class="hd-cc-tt"><a target="_blank" href="front/activity_jp/activities_detail_jp.htm?selIds=${activity.id }">${activity.titleJp}</a></div>
						<div class="addr-date mt15">
							<label>開催日：</label><span>${activity.dateStr}</span>
							<label class="ml30">開催場所：</label><span>${activity.addressJp}</span>
						</div>
						<div class="scl-act-content mt5">
						<a target="_blank" class="news-remark-a" href="front/activity_jp/activities_detail_jp.htm?selIds=${activity.id }">${dv:e(activity.promJp,275)}</a>
						</div>
						<a href="javascript:void(0);" class="ljbm-btn yjfk" data="${activity.id}">申込み</a>
					</div>
				</div>	
				</c:forEach>
				<div class="cb"></div>
			</div>
			<div class="bmhd-tt-box">
				<div class="yy-jt-ico"></div>
				講演予告
			</div>
			<div class="scl-hdyg-box">
				<ul class="sclhdyg-ul clb">
				<c:forEach items="${listActivityPrediction }" var ="listActivityPrediction">
					<li>
						<div class="sclhdyg-imgbox"><a target="_blank" href="front/activity_jp/activities_detail_jp.htm?selIds=${listActivityPrediction.id }"><img src="${listActivityPrediction.photo }"></a></div>
						<div class="sclhdyg-name">
							<a target="_blank" href="front/activity_jp/activities_detail_jp.htm?selIds=${listActivityPrediction.id }" title="${listActivityPrediction.titleJp}">
								${dv:e(listActivityPrediction.titleJp,55)}
							</a>
						</div>
						<div class="addr-date mt5">
							<label>開催日：</label><span>${listActivityPrediction.dateStr}</span>
						</div>
						<%-- <div class="scl-act-content mt5">
							<a target="_blank" href="front/activity/activities_detail.htm?selIds=${listActivityPrediction.id }">${dv:e(listActivityPrediction.prom,45)}</a>
						</div> --%>
					</li>
						</li>
				</c:forEach>
				</ul>
			</div>
			
	 		<!-- 历届 活动 -->
 			<div id="pageinc">
				<jsp:include page="/WEB-INF/pages/front_jp/activities/activities_index_inc.jsp"></jsp:include>
			</div>
	 		<!-- 历届 活动 -->
	 
		</div>
		<div class="cb"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/front_foot_v2.jsp"></jsp:include>
	</div>
	<jsp:include page="/WEB-INF/pages/front_jp/comm/bm-active.jsp"></jsp:include>
	<script type="text/javascript" src="static/front/activity/activity_index.js"></script>
</body>
</html>
