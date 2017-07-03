<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="sctr-right-box">
	<div class="srb-nav-box fl">法律研究</div>
	<div class="fr srb-ncbox mr15">
		<a href="jp/index.htm">ホーム</a>
		<label>></label>
		<span>出版物</span>
	</div>
	<div class="cb"></div>
</div>
<s:if test="pageResult.rows == 0">
	<div class="no-sj">内容がない</div>
</s:if>			
<!-- 分页 循环显示内容-->
<c:forEach var="article" items="${pageResult.result}">
	<div class="news-item mt20">
		<div class="news-title">
			<a class="news-title-a" href="jp/pubDetail_jp.htm?item.id=${article.id }">${article.jpName }</a>
		</div>
		<div class="news-brief">
			<div class="news-content-image fl" style="width:885px;margin-left:0px;">
				<div class="news-data">
					<span class="news-date"><fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd"/></span>
					<span class="news-publisher">発表者：${article.authorJp}</span>
					<%-- <span class="news-count">阅读量：30次</span> --%>
				</div>
				<div class="news-remark fl">
					${dv:e(article.jpSummary,250)}
				</div>
			</div>
			<div class="cb"></div>
		</div>
		<div class="news-line"></div>
	</div>
</c:forEach>

	<!-- 分页 -->	
	<div class="pagination mt20" style="float:left;">
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
	 <div class="cb"></div>
	<!-- 分页 -->
	
	
	<!-- 页数一部提交 -->
	 <script type="text/javascript">
		$(".pageReslt").click(function (){
			var currentPage = $(this).attr("currentPage");
			var id = $("#itemId").val();
			// 相关证书异步请求
			$.post(base+"jp/pub_ListPage_page_jp.htm",{"page":currentPage},function(data){
			 	$("#hcActive").html(data);
			});
		}) ;
	
	</script>
	