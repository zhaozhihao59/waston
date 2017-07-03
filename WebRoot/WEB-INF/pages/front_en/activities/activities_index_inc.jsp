<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 
			
<div class="bmhd-tt-box">
	<div class="yy-jt-ico"></div>
	Previous Lectures
</div>
<s:if test="pageResult.rows == 0">
	<div class="no-sj">Content N/A</div>
</s:if>

<div class="scl-hdyg-box">
	<ul class="sclhdyg-ul clb">
		<!-- 分页 循环显示内容-->
		<c:forEach var="activity" items="${pageResult.result}">	 
			<li>
				<div class="sclhdyg-imgbox">
					<a target="_blank" href="front/activity_en/activitiesDetail_en.htm?item.id=${activity.id }">
						<img src="${activity.photo }">
					</a>
				</div>
				<div class="sclhdyg-name">
					<a target="_blank" href="front/activity_en/activitiesDetail_en.htm?item.id=${activity.id }" title="${activity.titleEn}">${dv:e(activity.titleEn,40)}</a>
				</div>
				<div class="sclhdyg-time-box">
					Time：${activity.dateStrEn}
				</div>
				<%-- <div class="scl-act-content mt5">
							<a target="_blank" href="front/activity/activities_detail.htm?selIds=${activity.id }">${dv:e(activity.prom,45)}</a>
				</div> --%>
			</li>
		</c:forEach>
	</ul>
</div>
			 
 
<div class="cb"></div>

		<!-- 分页 -->
		<div class="pagination mt20" style="float:right;">
		<c:if test="${not empty pageResult.result and pageResult.allPages gt 0}">
				<div class="pagination mt20">
					<ul>
						<c:if test="${pageResult.currentPage>1}">
							<li>
								<a class="pageReslt"   title="首页">&lt;&lt;</a>
							</li>
							<li>
								<a class="pageReslt" currentPage="${pageResult.prePage}" title="上一页">&lt;</a>
							</li>
						</c:if>
						<c:forEach begin="${pageResult.pageBar[0]}" end="${pageResult.pageBar[1]}" varStatus="status">
							<c:if test="${pageResult.currentPage eq status.index}">
								<li>
									<a class="pageReslt" currentPage="${status.index}" style="background-color: #dfdfdf;"  class="cur">${status.index }</a>
								</li>
							</c:if>
							<c:if test="${pageResult.currentPage != status.index}">
								<li>
									<a class="pageReslt" currentPage="${status.index}">${status.index }</a>
								</li>
							</c:if>
					</c:forEach>
					<c:if test="${pageResult.currentPage lt pageResult.allPages}">
						<li>
							<a class="pageReslt" currentPage="${pageResult.nextPage}" title="下一页">&gt;</a>
						</li>
						<li>
							<a class="pageReslt" currentPage="${pageResult.lastPage}" title="尾页">&gt;&gt;</a>
						</li>
					</c:if>
				</ul>
			</div>
		</c:if>
		<div class="cb"></div>
		</div>
		<!-- 分页 -->
	
	<!-- 页数异步提交 -->
 	<script type="text/javascript">
		$(".pageReslt").click(function (){
			var currentPage = $(this).attr("currentPage");
			if(currentPage == null){
				currentPage = 1;
			}
			// 相关证书异步请求
			$.post(base+"front/activity_en/ListPageByOld_en.htm",{"page":currentPage},function(data){
			 	$("#pageinc").html(data);
			});
		}) ;
	
	</script> 