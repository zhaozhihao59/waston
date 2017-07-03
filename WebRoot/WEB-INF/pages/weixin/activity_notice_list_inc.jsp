<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
 
   
   <c:if test="${empty activityPageResult}">
		<div class="no-sj">暂无内容</div>
	</c:if>
    <s:iterator value="activityPageResult.result" var="activity">
		<div class="hd-list-box" data = ${activity.id }>
			<c:if test="${activity.state == 1 }">
				<div class="anl-lico2"></div>
			</c:if>
			<c:if test="${activity.state == 2 }">
				<div class="anl-lico"></div>
			</c:if>
			<c:if test="${activity.state == 3 }">
				<div class="anl-lico3"></div>
			</c:if>
			
			<div class="anl-hbd" >
				<div class="anl-hmain">
					<div class="anl-ttfont"><a href="weixin/to_activity_detail.htm?selIds=${activity.id }">${dv:e(activity.title,35)}</a></div>
					<div class="hdrq-font">活动日期：${activity.dateStr }</div>
				</div>
				<div class="anl-hleft">
					<div class="anl-imgbox">
						<a href="weixin/to_activity_detail.htm?selIds=${activity.id }"><img src="${activity.photo }"></a>
					</div>
				</div>
			</div>
			<div class="anl-ccbox">
				${dv:e(activity.prom,90)}
			</div>
		</div>
  	</s:iterator>
  		 