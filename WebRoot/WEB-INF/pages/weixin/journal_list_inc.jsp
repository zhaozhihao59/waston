<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
 <%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
 
 
    <c:if test="${empty pageResult}">
		<div class="no-sj">暂无内容</div>
	</c:if>
    <s:iterator value="pageResult.result" var="activity">
  		<a target="_blank" href="javascript:void(0);" onclick="readerPDF('${activity.annexPath}');return false;" class="touch-a" >
  			<div class="scl-cp-box2 clb mt15">
				<div class="fl scl-cp-left">
					<div class="scl-cp-tt f12" data="${activity.annexPath}" >
						${dv:e(activity.name,30)} 
					</div>
					<div>
						<label class="scl-ico"></label>
						<span class="scl-cp-font2 f12">${activity.type }</span>
					</div>
				</div>
				<div class="fr scl-cp-right" style="margin-top:3px;">
					<label class="scl-jt-ico2"></label>
				</div>
	  		</div>
  		</a>
  	</s:iterator>
<script>
	function readerPDF(path){
		window.open("static/base/lib/pdfjs-1.1.215-dist/web/viewer.html?file="+base+path);
	}
</script>