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
		<title>HEROS - 自行车赛 - 赛事</title>
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
			<div class="fl gcb-left-box">
				<%--一个--%>
				<div class="gcb-left-ccbox" >
					<div class="to-starbg"></div>
						<s:if test="gameList.size()>0">
							<c:forEach items="${gameList }" var="curGame">
								<div class="clb mt50">
									<div class="ts-left-box fl">
										<div class="ts-tt-font"><a class="ts-game-detail" href="game/detail.htm?id=${curGame.id}">${curGame.gameName }</a></div>
										<div class="ts-stime-box mt5">开赛时间：${curGame.gameDateStr}</div>
										<div class="ts-stime-box">比赛地点：${curGame.gameAddress}</div>
									</div>
									<div class="ts-right-box fr">
										<div class="jzbm-font">报名截止日期：</div>
										<div class="jzbm-font"><fmt:formatDate value="${curGame.enrollEndDate}" pattern="yyyy年MM月dd日"/></div>
									</div>
								</div>
								<div class="ts-bimg-box">
									<img src="${curGame.gamePhoto}">
									<div class="opacity-bg"></div>
									<div class="tag1">
										<b class="bg"></b>
										<em>HREOS</em>
										<a href="game/detail.htm?id=${curGame.id}">查看详情</a>
									</div>
								</div>
							</c:forEach>
						</s:if>
						<s:else>
							<div class="jft-tt" style="height:50px;text-align: center;">
								<div class="fl">没有正在报名的赛事</div>
							</div>
						</s:else>
				</div>
				<%--一个--%>
				<%--一个--%>
				<div id="gameList" >
					<jsp:include page="/WEB-INF/pages/front/game/game_list_inc.jsp"></jsp:include>
				</div>
				
			</div>
			<div class="fr gcb-right-box">
				<div class="gcb-ue-box">
					<div class="upc-btt"></div>
					<ul class="upc-list-ul">
						<li>
							<div>
								<label>3月</label>
								<label class="ml30">嘉定新城</label>
							</div>
							<div class="mt5">2015年斯柯达HEROS中国自行车系列赛（环上海—嘉定新城站）</div>
						</li>
						<li class="cur">
							<div>
								<label>3月</label>
								<label class="ml30">黄山</label>
							</div>
							<div class="mt5">安徽 - 黄山国际山地节 - 黄山站</div>
						</li>
						<li>
							<div>
								<label>4月</label>
								<label class="ml30">临港新城</label>
							</div>
							<div class="mt5">上海 - 大组赛（环上海） - 临港新城站</div>
						</li>
						<li class="cur">
							<div>
								<label>5月</label>
								<label class="ml30">安亭新镇</label>
							</div>
							<div class="mt5">上海 - 个人计时赛、大组赛（环上海） - 安亭新镇</div>
						</li>
						<li>
							<div>
								<label>6月</label>
								<label class="ml30">松江</label>
							</div>
							<div class="mt5">上海 - 魁地仑团队接力 - 松江站</div>
						</li>
						<li>
							<div>
								<label>7月</label>
								<label class="ml30">黄山</label>
							</div>
							<div class="mt5">安徽 - 大组赛（格兰枫度） - 黄山站</div>
						</li>
						<li>
							<div>
								<label>8月</label>
								<label class="ml30">奉贤</label>
							</div>
							<div class="mt5">上海 - 大组赛（环上海） - 奉贤站</div>
						</li>
						<li>
							<div>
								<label>9月</label>
								<label class="ml30">青浦</label>
							</div>
							<div class="mt5">上海 - 个人计时赛、大组赛（环上海） - 青浦站</div>
						</li>
						<li>
							<div>
								<label>11月</label>
								<label class="ml30">武夷山</label>
							</div>
							<div class="mt5">福建 - 大组赛（格兰枫度） - 武夷山</div>
						</li>
						<li>
							<div>
								<label>11月</label>
								<label class="ml30">嘉定</label>
							</div>
							<div class="mt5">上海 - 团队接力赛（heros年度盛典）&biketo江南车友节 - 嘉定站</div>
						</li>
					</ul>
				</div>
				
				<div class="game-list-wm">
					<img src="static/front/comm/images/wx-wb-bg.png">
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
					$(".news-pager-btn").live("click",assessSubmit);					
				});
				function assessSubmit(){
					var currentPage=$(this).attr("data");
					$.post("front/institution/listMemberAssessByPage.htm?assessPageResult.currentPage="+currentPage,function(data){
						$("#gameList").html(data);
					});
				}
		</script>
	</body>
</html>