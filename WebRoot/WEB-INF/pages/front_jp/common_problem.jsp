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
		<jsp:include page="/WEB-INF/pages/front_jp/comm/front_header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/pages/front_jp/comm/news_move.jsp"></jsp:include>
		<div class="arb-tt-box">
			<span>よくある問題</span>
			<label>Questions</label>
		</div>
		<div class="w1170-mg-auto clb">
				<div class="main-left fl">
					
					<div id="pageinc">
						<jsp:include page="/WEB-INF/pages/front_jp/common_problem_inc.jsp"></jsp:include>
					</div>
					
				</div>
			<div class="fr right-main">
				<div style="padding-bottom:0px;" class="main-right">
						<div class="block-title clb">
							<div class="block-title-font fl"><a class="block-title-a" href="cms_jp/video_list_jp.htm">映像</a></div>
						</div>
						<div class="video clb">
						 <c:forEach items="${videoArticleList}" var="val">
							<div class="video-item clb">
								<a class="video-image" href="javascript:void(0);">
									<img src="${val.path}" width="90px" height="50px" onclick="videoimghref('${val.id}');javascript:void(0);" />
								</a>
								<div class="fl ml10">
									<a class="video-title" href="javascript:void(0);" title="" onclick="videoimghref('${val.id}');javascript:void(0);">${val.jpName}</a>
									<p class="video-remark" title="">${val.jpSummary}</p>
								</div>	
							</div>
							 </c:forEach>
						</div>
				</div>
					<div class="main-right mt20 clb">
						<div class="block-title clb">
							<div class="block-title-font fl"><a class="block-title-a" href="front/activity_jp/activities_ListPage_jp.htm">講演の申込</a></div>
							<a class="fr a-link f12 mt3" href="front/activity_jp/activities_ListPage_jp.htm">更に詳しく</a>
						</div>
						<c:if test="${empty listActivity}">
								<div class="no-sj">内容がない</div>
						</c:if>
						<c:forEach items="${listActivity}" var="activity">
						<div class="activity">
							<div class="act-title">
								<a class="act-title-a" href="front/activity_jp/activities_detail_jp.htm?selIds=${activity.id }">${activity.titleJp}</a>
							</div>
							<div class="act-brief clb">
								<div class="act-image fl">
									<a class="" href="front/activity_jp/activities_detail_jp.htm?selIds=${activity.id }"><img src="${activity.photo}" height="50px" width="80px"/></a>
								</div>
								<div class="act-content fl">
									<p>
										<span class="act-brief-title">開催日：</span><span class="act-brief-intro">${activity.dateStr}</span>
									</p>
									<p>
										<span class="act-brief-title">開催場所：</span><span class="act-brief-intro">${activity.addressJp}</span>
									</p>
									<a class="ljbm-btn" href="front/activity_jp/activities_detail_jp.htm?selIds=${activity.id }" data="${activity.id}">詳細情報</a>
								</div>
							</div>
						</div>
						</c:forEach>
					</div>
				<div class="mt20">
					<jsp:include page="/WEB-INF/pages/front_jp/comm/news_dy.jsp"></jsp:include>
				</div>	
			</div>
		</div>	
		<jsp:include page="/WEB-INF/pages/front_jp/comm/front_foot_v2.jsp"></jsp:include>
	</body>
	<!-- 异步请求 -->
		<script type="text/javascript">
		//视频点击进去
		function videoimghref(id){
			window.location.href = "jp/cmsDetail_jp.htm?item.id="+id;
		}
		$(".pageReslt").click(function (){
			var currentPage = $(this).attr("currentPage");
			if(currentPage == null){
				currentPage = 1;
			}
			// 异步请求
			$.post(base+"cms_jp/ListPageByOld_jp.htm",{"page":currentPage},function(data){
			 	$("#pageinc").html(data);
			});
		}) ;
	</script>
</html>