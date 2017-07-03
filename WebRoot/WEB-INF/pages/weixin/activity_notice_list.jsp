<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="author" content="Tocersoft" />
		<meta content="telephone=no" name="format-detection" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<meta name="robots" content="all" />
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<title>活动列表</title>
		<jsp:include page="/WEB-INF/pages/weixin/wx_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/weixin/phone.css" />
	</head>
  	<body>
	  	<div class=ab-top-dz2>
			<select  id="selectStatus"  class="ui-select" name="item.state">
				<option value="-1" >全部</option>
				<option value="1">预告</option>
				<option value="2">正在报名</option>
				<option value="3">历届</option>
			</select>
	  	</div>
  		
	 	<div id="resultDiv" style="margin-top:50px;margin-bottom: 50px;">
			    <jsp:include page="/WEB-INF/pages/weixin/activity_notice_list_inc.jsp"></jsp:include>
	 	</div>
		<jsp:include page="/WEB-INF/pages/weixin/change_page_inc.jsp">
			<jsp:param value="searchForm" name="sendForm"/> 
			<jsp:param value="resultDiv" name="resultDiv"/> 
			<jsp:param name="currentPage" value="${activityPageResult.currentPage}"/> 
			<jsp:param name="lastPage" value="${activityPageResult.lastPage}"/> 
		</jsp:include> 
	 	<div class="none"> 
		<form id="searchForm" action="weixin/to_activity_list_inc.htm" method="post"> 
			<input id="currPage" name="page" class="J_currPage" value="${activityPageResult.currentPage}"/> 
		</form> 
		</div> 
  		
  		<div style="height:50px;"></div>
  		
  	<script>
  	
  //下拉框js
	$(document).ready(function(){
		$(".ui-select").selectWidget({
			change       : function (changes) {
			
			var type = $("#selectStatus").val();
			// 相关证书异步请求
			$.post(base+"weixin/to_activity_list_inc.htm",{"status":type},function(data){
			 	$("#resultDiv").html(data);
			});
			
				return changes;
			},
			effect       : "slide",
			keyControl   : true,
			speed        : 200,
			scrollHeight : 250
		});
		
		$(".hd-list-box").click(function (){
			var selId = $(this).attr("data");
			window.location.href = "weixin/to_activity_detail.htm?selIds="+selId;
		
		});
	});	
  	</script>

	</body>
</html>
