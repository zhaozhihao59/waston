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
		<title>HEROS - 自行车赛 - 赛事集锦详情</title>
		<meta name="Keywords" content="自行车赛" />
		<meta name="description" content="自行车赛" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css_v2/common.css" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
	</head>
	
	<body style="background:#e9e9e9;">
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		
		<div class="game-list-tbox">
			<div class="game-list-ccbox">
				<ul class="glc-lul">
					<li><a href="game/index.htm" class="cur">赛事集锦</a></li>
					<li><a href="game/photo_list.htm">赛事照片</a></li>
					<li><a href="cms/game_result.htm">成绩查询</a></li>
				</ul>
			</div>
		</div>
		<div class="game-cc-blayout clb">
			<div class="fl game-detail-box">
				<div class="gdb-hbox clb">
					<div class="gdb-imgbox fl"><img src="${item.gamePhotoTo }"></div>
					<div class="gdb-ccbox fr">
						<div class="ts-tt-font">${item.gameName }</div>
						<div class="ts-stime-box mt5">开赛时间：${item.gameDateStr }<!-- 2015/03/11 - 2015/03/12 --></div>
						<div class="ts-stime-box">比赛地点：${item.gameAddress }</div>
					</div>
				</div>
				
				<div class="game-nav-box mt30">
					<ul class="game-detail-ul">
						<li>赛事介绍</li>
						<li>竞赛须知</li>
						<li>日程安排</li>
						<li>赛道图</li>
						<li>录取奖励</li>
						<li>交通住宿</li>
						<li>文件下载</li>
					</ul>
				</div>
				
				<div class="game-detail-cbox">
					${item.descHtml}
				</div>
				<div class="game-detail-cbox">
					${item.noteHtml}
				</div>
				<div class="game-detail-cbox">
					${item.scheduleHtml }
				</div>
				<div class="game-detail-cbox">
					${item.trackHtml }
				</div>
				<div class="game-detail-cbox">
					${item.rewardHtml }
				</div>
				<div class="game-detail-cbox">
					${item.transportHtml }
				</div>
				<div class="game-detail-cbox">
					<ul class="">
						<c:forEach items="${uploadList}" var="upload">
						<li class=""><a href="${upload.url }" class="download-a" target="_blank">${upload.name }</a></li>
						</c:forEach>
					</ul>
				</div>
				
				<script>
					$(function(){
						$(".game-detail-ul li").eq(0).addClass("cur");
						$(".game-detail-cbox").eq(0).show().siblings(".game-detail-cbox").hide();
						$(".game-detail-ul li").each(function(i){
							$(this).click(function(){
								$(this).addClass("cur").siblings().removeClass("cur");
								$(".game-detail-cbox").eq(i).show().siblings(".game-detail-cbox").hide();
							});
						});
						
					});
				</script>
				
				
			</div>
			<c:if test="${item.state==1 }">
			<div class="fr gcb-right-box">
				<div class="xzss-box2">
					<div class="xzss-font" style="margin-top:0px;">
						${item.gameName }
					</div>
					<div class="xzss-font" style="margin-top:15px;">
						报名截止日期
					</div>
					<div id="" class="xzss-font" style="margin-top:0px;">
						<fmt:formatDate value="${item.enrollEndDate }" pattern="yyyy-MM-dd"/>
					</div>
				</div>
				<div class="gpl-sbtnbox">
					<a href="game/enroll.htm" class="gpl-search-btn">立即报名</a>
				</div>
			</div>
			</c:if>
			
		</div>
		
		<%--尾部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
		<%--尾部end--%>
<%-- 		<script type="text/javascript">
			var data=${item.gameDateStr};
			setInterval(function(){ },1000);
		</script> --%>
		<script type="text/javascript">
				$(window).scroll(function() {
					var h = $(window).scrollTop();
					if (h > 372) {
						$('.gcb-right-box').addClass("pro-fixed");
					} else {
						$('.gcb-right-box').removeClass("pro-fixed");
					}
				});
		</script>
	</body>
</html>