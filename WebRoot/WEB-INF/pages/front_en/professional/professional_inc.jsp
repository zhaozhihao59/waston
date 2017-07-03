<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <s:if test="professionalPageResult.rows == 0">
	<div class="no-sj">Content N/A</div>
</s:if>
<!-- 分页 循环显示内容-->
<c:forEach var="professional" items="${professionalPageResult.result}">	 				
	<ul class="pro-imgul">
		<li>
			<div class="photo-h fl">
				<a href="front/professional_en/toProfessionalDetail_en.htm?selIds=${professional.id }"><img src="${professional.photo }" style="width:66px;Height:66px;" /></a>
			</div>
			
			<div class="fl photo-c">
				<div class="pc-name"><a href="front/professional_en/toProfessionalDetail_en.htm?selIds=${professional.id }">${professional.nameEn}</a></div>
				<c:if test="${professional.type ==0 }">
					<div class="pc-cfont">Partners</div>
				</c:if>
				<c:if test="${professional.type ==1 }">
					<div class="pc-cfont">Of Counsels</div>
				</c:if>
				<div class="pc-cfont">${professional.email }</div>
			</div>
		</li>
	</ul>
</c:forEach>
	<div class="cb"></div>
		<!-- 分页 -->
		<div class="pagination mt20" style="float:right;">
		<c:if test="${not empty professionalPageResult.result and professionalPageResult.allPages gt 0}">
				<div class="pagination mt20">
					<ul>
						<c:if test="${professionalPageResult.currentPage>1}">
							<li>
								<a class="pageReslt"   title="首页">&lt;&lt;</a>
							</li>
							<li>
								<a class="pageReslt" currentPage="${professionalPageResult.prePage}" title="上一页">&lt;</a>
							</li>
						</c:if>
						<c:forEach begin="${professionalPageResult.pageBar[0]}" end="${professionalPageResult.pageBar[1]}" varStatus="status">
							<c:if test="${professionalPageResult.currentPage eq status.index}">
								<li>
									<a class="pageReslt" currentPage="${status.index}" style="background-color: #dfdfdf;" class="cur">${status.index }</a>
								</li>
							</c:if>
							<c:if test="${professionalPageResult.currentPage != status.index}">
								<li>
									<a class="pageReslt" currentPage="${status.index}">${status.index }</a>
								</li>
							</c:if>
					</c:forEach>
					<c:if test="${professionalPageResult.currentPage lt professionalPageResult.allPages}">
						<li>
							<a class="pageReslt" currentPage="${professionalPageResult.nextPage}" title="下一页">&gt;</a>
						</li>
						<li>
							<a class="pageReslt" currentPage="${professionalPageResult.lastPage}" title="尾页">&gt;&gt;</a>
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
			if(currentPage == null){
				currentPage = 1;
			}
			// 相关证书异步请求
			$.post(base+"en/toProfessional_inc_en.htm",{"page":currentPage},function(data){
			 	$("#pageinc").html(data);
			});
		}) ;
	
	</script> 		 