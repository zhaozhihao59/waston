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
		<jsp:include page="/WEB-INF/pages/front_en/comm/front_header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/pages/front_en/comm/news_move.jsp"></jsp:include>
		<div class="arb-tt-box">
			<span>SEARC RESULT</span>
			<label>Searc result</label>
		</div>
		<div class="w1170-mg-auto clb">
			<div class="scl-knowledge-showbox">
				<div class="search-actright-box">
				<input id="searchNames" type="hidden" value="${searchName}">
				<div id="hcActive">
					<jsp:include page="/WEB-INF/pages/front_en/search_result_inc.jsp"></jsp:include>
				</div>
					 
				</div>
			</div>
			 
		</div>	
		<jsp:include page="/WEB-INF/pages/front_en/comm/front_foot_v2.jsp"></jsp:include>
	<script>
			$(function(){
				$(function(){
					$(".knowledge-ul li").each(function(i){
						$(".scl-knowledge-showbox").eq(0).show().siblings(".scl-knowledge-showbox").hide();
						$(this).click(function(){
							$(this).addClass("cur").siblings().removeClass("cur");
							$(".scl-knowledge-showbox").eq(i).css("marginTop",20);
							$(".act-left-ul li").eq(i).addClass("cur").siblings(".act-left-ul li").removeClass("cur");
							$(".scl-knowledge-showbox").eq(i).show().animate({opacity:'1',marginTop:'0'},1000).siblings(".scl-knowledge-showbox").hide();
						});
					});
				});
			
			});	
	</script>
	</body>
</html>