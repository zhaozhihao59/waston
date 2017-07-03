<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${not empty pageResult.result}">
<div class="gcb-left-ccbox mt50" >
	<div class="past-bg"></div>
	<c:forEach items="${pageResult.result }" var="curGame">
		<div class="clb mt50">
			<div class="ts-left-box fl">
				<div class="ts-tt-font"><a class="ts-game-detail" href="game/detail.htm">${curGame.gameName }</a></div>
				<div class="ts-stime-box mt5">开赛时间：${curGame.gameDateStr}</div>
				<div class="ts-stime-box">比赛地点：${curGame.gameAddress}</div>
			</div>
			<div class="ts-right-box fr">
				<div class="jzbm-font">报名截止日期：</div>
				<div class="jzbm-font"><fmt:formatDate value="${curGame.enrollEndDate}" pattern="yyyy年MM月dd日"/></div>
			</div>
		</div>
		<div class="ts-bimg-box">
			<img src="${curGame.gamePhoto }">
			<div class="opacity-bg"></div>
			<div class="tag1">
				<b class="bg"></b>
				<em>HREOS</em>
				<a href="game/detail.htm?item.id=${curGame.id }">查看详情</a>
			</div>
		</div>
	</c:forEach>
</div>
<%--一个--%>
<div class="mt30 clb" style="margin-right:65px;">
	<div class="news-pager fr pl5 clb">
		<div class="fl mr10">
			<span class="news-page-font mr3">当前第</span> 
			<span class="news-page-no mr3">${pageResult.currentPage}</span> 
			<span class="news-page-font mr3">页 / 总共</span> 
			<span class="news-page-no mr3">${pageResult.allPages}</span> 
			<span class="news-page-font">页</span>
		</div>
		<div class="fl">
			<a href="javascript:void(0)" data="${pageResult.homePage}" class="news-pager-btn">首页</a> 
			<a href="javascript:void(0)" data="${pageResult.prePage}" class="news-pager-btn">上一页</a>
			<a href="javascript:void(0)" data="${pageResult.nextPage}" class="news-pager-btn">下一页</a>
			<a href="javascript:void(0)" data="${pageResult.lastPage}" class="news-pager-btn">尾页</a>
		</div>
	</div>
</div>
</c:if>