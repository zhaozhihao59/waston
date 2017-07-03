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
		<title>HEROS - 自行车赛 - 成绩查询</title>
		<meta name="Keywords" content="自行车赛" />
		<meta name="description" content="自行车赛" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css_v2/common.css" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
		<script type="text/javascript" src="static/front/js/jquery.mCustomScrollbar.concat.min.js"></script>
		<link rel="stylesheet" type="text/css" href="static/front/css/jquery.mCustomScrollbar.css" />
		<style>
			.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar {
			    background:#444444;
			}
		</style>
	</head>
	
	<body style="background:#e9e9e9;">
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		
		<div class="game-list-tbox">
			<div class="game-list-ccbox">
				<ul class="glc-lul">
					<li><a href="game/index.htm">赛事集锦</a></li>
					<li><a href="game/photo_list.htm">赛事照片</a></li>
					<li><a href="game/result_search.htm" class="cur">成绩查询</a></li>
				</ul>
			</div>
		</div>
		<div class="game-cc-blayout clb">
			<div class="fl game-rs-box">
				<div class="" style="text-align:center;font-size:24px;height:50px;line-height:50px;color:#999;margin-top:20px;">赛事成绩暂时未公布，敬请期待</div>
				<div class="" style="text-align:center;margin-top:20px;font-size:16px;color:#999;"><a class="enroll-now" href="game/enroll.htm">立即报名参加环上海大组赛</a></div>
				<%-- 
				<div class="game-rs-bg"></div>
				<div class="gtb-msg-box">
					<div class="gtb-lbox">
						<div class="gtb-ltt">2014年中国·南溪环万里长江第一滩 “蜀粤杯”自行车公开赛</div>
						<div class="gtb-addrbox" style="margin-top:7px;">
							<label>比赛地点：新疆-乌鲁木齐</label>
							<label class="ml50">比赛时间：2014-12-12</label>
						</div>
					</div>
				</div>
				
				<table class="glb-ltable2 mt25">
					<tr class="glb-tt-tr">
						<td width="120px">名次</td>
						<td width="120px">姓名</td>
						<td width="120px">参赛号</td>
						<td width="250px">团队/团体</td>
						<td>成绩</td>
					</tr>
				</table>
				<div class="glb-list-box2" id="content_3">
					<table class="glb-ltable2">
						<tr class="glb-cc-tr">
							<td width="120px">01</td>
							<td width="120px">邵彩乐</td>
							<td width="120px">55</td>
							<td width="250px">菊爆大队</td>
							<td>44：22.777</td>
						</tr>
						<tr class="glb-cc-tr white-bg">
							<td>01</td>
							<td>邵彩乐</td>
							<td>55</td>
							<td>菊爆大队</td>
							<td>44：22.777</td>
						</tr>
						<tr class="glb-cc-tr">
							<td>01</td>
							<td>邵彩乐</td>
							<td>55</td>
							<td>菊爆大队</td>
							<td>44：22.777</td>
						</tr>
						<tr class="glb-cc-tr white-bg">
							<td>01</td>
							<td>邵彩乐</td>
							<td>55</td>
							<td>菊爆大队</td>
							<td>44：22.777</td>
						</tr>
						<tr class="glb-cc-tr">
							<td>01</td>
							<td>邵彩乐</td>
							<td>55</td>
							<td>菊爆大队</td>
							<td>44：22.777</td>
						</tr>
						<tr class="glb-cc-tr white-bg">
							<td>01</td>
							<td>邵彩乐</td>
							<td>55</td>
							<td>菊爆大队</td>
							<td>44：22.777</td>
						</tr>
						<tr class="glb-cc-tr">
							<td>01</td>
							<td>邵彩乐</td>
							<td>55</td>
							<td>菊爆大队</td>
							<td>44：22.777</td>
						</tr>
						<tr class="glb-cc-tr white-bg">
							<td>01</td>
							<td>邵彩乐</td>
							<td>55</td>
							<td>菊爆大队</td>
							<td>44：22.777</td>
						</tr>
						<tr class="glb-cc-tr">
							<td>01</td>
							<td>邵彩乐</td>
							<td>55</td>
							<td>菊爆大队</td>
							<td>44：22.777</td>
						</tr>
						<tr class="glb-cc-tr white-bg">
							<td>01</td>
							<td>邵彩乐</td>
							<td>55</td>
							<td>菊爆大队</td>
							<td>44：22.777</td>
						</tr>
						<tr class="glb-cc-tr">
							<td>01</td>
							<td>邵彩乐</td>
							<td>55</td>
							<td>菊爆大队</td>
							<td>44：22.777</td>
						</tr>
						<tr class="glb-cc-tr white-bg">
							<td>01</td>
							<td>邵彩乐</td>
							<td>55</td>
							<td>菊爆大队</td>
							<td>44：22.777</td>
						</tr>
						<tr class="glb-cc-tr">
							<td>01</td>
							<td>邵彩乐</td>
							<td>55</td>
							<td>菊爆大队</td>
							<td>44：22.777</td>
						</tr>
						<tr class="glb-cc-tr white-bg">
							<td>01</td>
							<td>邵彩乐</td>
							<td>55</td>
							<td>菊爆大队</td>
							<td>44：22.777</td>
						</tr>
					</table>
				</div>
				
				--%>
				
				
			</div>
			<div class="fr gcb-right-box">
				<div class="xzss-box2">
					<div class="xzss-font">成绩查询</div>
					<select class="xz-select">
						<option>赛事</option>
					</select>
					<select class="xz-select">
						<option>比赛项目</option>
					</select>
					<div class="grs-text-box pr">
						 <input type="text" class="grs-text pp-input">
						 <label class="show-text" style="top:7px;left:16px;font-size:14px;">参赛号</label>
					</div>
					<div class="grs-text-box pr">
						 <input type="text" class="grs-text pp-input">
						 <label class="show-text" style="top:7px;left:16px;font-size:14px;">姓名</label>
					</div>
				</div>
				<div class="gpl-sbtnbox">
					<a href="#" class="gpl-search-btn">搜索</a>
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
		<script>
			$("#content_3").mCustomScrollbar({scrollButtons:{enable:true}});
		</script>
	</body>
</html>