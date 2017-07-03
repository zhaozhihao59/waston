<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 
<s:if test="cmsArticlePageResult.rows == 0">
	<div class="no-sj">暂无内容</div>
</s:if>			
<!-- 分页 循环显示内容-->
<c:forEach var="article" items="${cmsArticlePageResult.result}">
     <ul class="sj-list-ul" style=" border-bottom:solid 1px #ddd;">
    	<li style="float:none;width:auto;margin-top:2px;">
    		<%-- <div class="sj-imgbox"><a href="${article.annexPath}"><img src="${article.path }" style="width: 100px;height: 130px;"></a></div>
    		<div class="sj-cctt">
    			<a href="${article.annexPath}">${article.name }</a>
    		</div> --%>
    		<a style="border:none;width:auto;" class="updaDown anli-box-wz1" href="pubDetail.htm?item.id=${article.id}">${article.name}</a> 
    	</li>
	 </ul>
</c:forEach>
	<div class="cb"></div>

	<!-- 分页 -->	
	<div class="pagination mt20" style="float:right;">
	<c:if test="${not empty cmsArticlePageResult.result and cmsArticlePageResult.allPages gt 0}">
			<div class="pagination mt20">
				<ul>
					<c:if test="${cmsArticlePageResult.currentPage>1}">
						<li>
							<a class="pageReslt"   title="首页">&lt;&lt;</a>
						</li>
						<li>
							<a class="pageReslt" currentPage="${cmsArticlePageResult.prePage}" title="上一页">&lt;</a>
						</li>
					</c:if>
					<c:forEach begin="${cmsArticlePageResult.pageBar[0]}" end="${cmsArticlePageResult.pageBar[1]}" varStatus="status">
						<c:if test="${cmsArticlePageResult.currentPage eq status.index}">
							<li>
								<a class="pageReslt" currentPage="${status.index}" class="cur">${status.index }</a>
							</li>
						</c:if>
						<c:if test="${cmsArticlePageResult.currentPage != status.index}">
							<li>
								<a class="pageReslt" currentPage="${status.index}">${status.index }</a>
							</li>
						</c:if>
				</c:forEach>
				<c:if test="${cmsArticlePageResult.currentPage lt cmsArticlePageResult.allPages}">
					<li>
						<a class="pageReslt" currentPage="${cmsArticlePageResult.nextPage}" title="下一页">&gt;</a>
					</li>
					<li>
						<a class="pageReslt" currentPage="${cmsArticlePageResult.lastPage}" title="尾页">&gt;&gt;</a>
					</li>
				</c:if>
			</ul>
		</div>
	</c:if>
	 <div class="cb"></div>
	</div>
	<!-- 分页 -->
	
	
	<!-- 页数一部提交 -->
	 <script type="text/javascript">
		$(".pageReslt").click(function (){
			var currentPage = $(this).attr("currentPage");
			var id = $("#itemId").val();
			// 相关证书异步请求
			$.post(base+"pub_ListPage_page_inc2.htm",{"page":currentPage},function(data){
			 	$("#hcActive1").html(data);
			});
		}) ;
	
	</script>
	