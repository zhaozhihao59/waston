	<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<div class="fr actright-box">
		<div class="sctr-right-box">
			<div class="srb-nav-box fl">
				<c:if test="${item.id=='3d5ffe8b-e890-11e4-ae9b-00266c0e7760'}">
					 Latest on Watson & Band
				</c:if>
				<c:if test="${item.id=='439c9076-e890-11e4-ae9b-00266c0e7760'}">Latest Legal News</c:if>
			</div>
			<div class="fr srb-ncbox mr15">
				<a href="#">HOME</a>
				<label>></label>
				<span>
				<c:if test="${item.id=='3d5ffe8b-e890-11e4-ae9b-00266c0e7760'}">
					 Latest on Watson & Band
				</c:if>
				<c:if test="${item.id=='439c9076-e890-11e4-ae9b-00266c0e7760'}">Latest Legal News</c:if>
				 </span>
			</div>
			<div class="cb"></div>
		</div>
		<s:if test="pageResult.rows == 0">
		<div class="no-sj">Content N/A</div>
		</s:if>
		<!-- 分页 循环显示内容-->
		<c:forEach var="article" items="${pageResult.result}">
			<div class="news-item mt20">
				<div class="news-title">
					<a target="_blank" class="news-title-a" href="en/cmsDetail_en.htm?item.id=${article.id }">${article.enName }</a>
				</div>
				<div class="news-brief">
					<div class="news-image fl mr15">
						<c:if test="${empty article.path}">
							<a target="_blank" class="news-image-a" href="en/cmsDetail_en.htm?item.id=${article.id}"></a>
						</c:if>
						<c:if test="${!empty article.path}">
							<a target="_blank" class="news-image-a" href="en/cmsDetail_en.htm?item.id=${article.id}"><img src="${article.path}" width="160px" height="110px"/></a>
						</c:if>
					</div>
					<c:if test="${empty article.path}">
					<div class="news-content-image fl" style="width:885px;">
						<div class="news-data">
							<span class="news-date"><fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd"/></span>
							<span class="news-publisher">Published by：${article.authorEn}</span>
							<%-- <span class="news-count">阅读量：${article.readNum}次</span> --%>
						</div>	
						<div class="news-remark fl">
							<a c class="news-remark-a" href="en/cmsDetail_en.htm?item.id=${article.id }">
								${dv:e(article.enSummary,250)}
							</a>
						</div>
					</div>
					</c:if>
					<c:if test="${!empty article.path}">
					<div class="news-content-image fl" style="width:705px;">
						<div class="news-data">
							<span class="news-date"><fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd"/></span>
							<span class="news-publisher">Published by：${article.authorEn}</span>
							<%-- <span class="news-count">阅读量：${article.readNum}次</span> --%>
						</div>
						<div class="news-remark fl">
							<a c class="news-remark-a" href="en/cmsDetail_en.htm?item.id=${article.id }">
								${dv:e(article.enSummary,250)}
							</a>
						</div>
					</div>
					</c:if>
					<div class="cb"></div>
				</div>
				<div class="news-line"></div>
			</div>
		</c:forEach>
		
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
	</div>
	
	<!-- 页数一部提交 -->
 	<script type="text/javascript">
		$(".pageReslt").click(function (){
			var currentPage = $(this).attr("currentPage");
			var id = $("#itemId").val();
			// 相关证书异步请求
			$.post(base+"en/cms_ListPage_inc_en.htm",{"page":currentPage,"item.id":id},function(data){
			 	$("#hcActive").html(data);
			});
		}) ;
	
	</script>