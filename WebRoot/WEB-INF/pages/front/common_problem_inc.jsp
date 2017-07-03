<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 
				<c:forEach var="pr" items="${pageResult.result}">
				<div class="news-item">
					<div class="news-title">
						<a target="_blank" class="news-title-a" href="cmsDetail.htm?item.id=${pr.id}">${pr.name}</a>
					</div>
					<div class="news-brief">
						
						<div class="news-content fl">
							<div class="news-data">
								<span class="news-date"><fmt:formatDate value="${pr.createDate}" pattern="yyyy年MM月dd日 E"/><br> </span>
								<span class="news-publisher">发布人：${pr.author}</span>
								<span class="news-count">阅读量：${pr.readNum}次</span>
							</div>
							<div class="news-remark fl">
								<a target="_blank" class="news-remark-a" href="cmsDetail.htm?item.id=${pr.id}">
									${pr.summary}
								</a>
							</div>
						</div>
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
	
	<!-- 页数异步提交 -->
 	<script type="text/javascript">
		$(".pageReslt").click(function (){
			var currentPage = $(this).attr("currentPage");
			if(currentPage == null){
				currentPage = 1;
			}
			// 相关证书异步请求
			$.post(base+"cms/ListPageByOld.htm",{"page":currentPage},function(data){
			 	$("#pageinc").html(data);
			});
		}) ;
	
	</script> 