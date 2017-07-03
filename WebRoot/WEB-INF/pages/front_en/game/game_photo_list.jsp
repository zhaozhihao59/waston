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
		<title>HEROS - 自行车赛 - 赛事照片</title>
		<meta name="Keywords" content="自行车赛" />
		<meta name="description" content="自行车赛" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css_v2/common.css" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
		<link rel="stylesheet" href="static/front/css/jquery.fancybox-1.3.1.css"/>
		<script type="text/javascript"src="static/front/js/jquery.fancybox-1.3.1.js"></script>
	</head>
	
	<body style="background:#e9e9e9;">
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		
		<div class="game-list-tbox">
			<div class="game-list-ccbox">
				<ul class="glc-lul">
					<li><a href="game/index.htm">赛事集锦</a></li>
					<li><a href="game/photo_list.htm" class="cur">赛事照片</a></li>
					<li><a href="cms/game_result.htm">成绩查询</a></li>
				</ul>
			</div>
		</div>
		<div class="game-cc-blayout clb">
			<div class="fl photo-left-box">
				<ul class="photo-list-ul">
					<c:forEach items="${photoPageResult.result}" var="photo">
					<li><a href="${photo.url }" rel="example_group"><img src="${photo.url }"></a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="fr gcb-right-box">
				<div class="xzss-box">
					<div class="xzss-font">选择赛事</div>
					<select class="xz-select">
						<option value="">-- 请选择赛事 --</option>
					</select>
				</div>
				<div class="gpl-sbtnbox">
					<a href="game/photo_list.htm" class="gpl-search-btn">搜索</a>
				</div>
			</div>
		</div>
		
		<%--尾部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
		<%--尾部end--%>
		<script>
			$(function(){
				//hover效果
				$(".ts-bimg-box").hover(function () {
					$(this).addClass("hover");
					},function(){
					$(this).removeClass("hover");
					}
					); 		
				});
		</script>
		<script type="text/javascript">  
	$(document).ready(function() {
			/*
			*   Examples - images
			*/

			$("a#example1").fancybox({
				'titleShow'		: false
			});
			$("a#example2").fancybox({
				'titleShow'		: false,
				'transitionIn'	: 'elastic',
				'transitionOut'	: 'elastic'
			});

			$("a#example3").fancybox({
				'titleShow'		: false,
				'transitionIn'	: 'none',
				'transitionOut'	: 'none'
			});

			$("a#example4").fancybox();

			$("a#example5").fancybox({
				'titlePosition'	: 'inside'
			});

			$("a#example6").fancybox({
				'titlePosition'	: 'over'
			});

			$("a[rel=example_group]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
				}
			});

			/*
			*   Examples - various
			*/

			$("#various1").fancybox({
				'titlePosition'		: 'inside',
				'transitionIn'		: 'none',
				'transitionOut'		: 'none'
			});

			$("#various2").fancybox();

			$("#various3").fancybox({
				'width'				: '75%',
				'height'			: '75%',
				'autoScale'			: false,
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'type'				: 'iframe'
			});

			$("#various4").fancybox({
				'padding'			: 0,
				'autoScale'			: false,
				'transitionIn'		: 'none',
				'transitionOut'		: 'none'
			});
		});
	</script>  
	</body>
</html>