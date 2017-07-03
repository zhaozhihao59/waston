<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="act-index-news">
	<div class="fl zxzx-ico"></div>
	<div class="fr zxzx-cbox" >
		<%--跑马灯start --%>
		<div class="marqueeDv" style="width: 1050px;">
			<ul>
				<c:forEach var="lab" items="${session.ListArticleByComplex1}">
					<li>
						<a href="cmsDetail.htm?item.id=${lab.id}" class="marquee">${lab.name}</a>
					</li>
				</c:forEach>
			</ul>
		</div> 
		<div class="cb"></div> 
		<%--跑马灯end --%>
	</div>
	<div class="cb"></div>
</div>
<script language="JavaScript">
	$(".marqueeDv").kxbdMarquee({
		  isEqual:false,         	//所有滚动的元素长宽是否相等,true,false 
		  loop:0,                 //循环滚动次数，0时无限         
		  direction:"left",       //滚动方向，"left","right","up","down"         
		  scrollAmount:1,     //步长         
		  scrollDelay:20        //时长
	});
</script>