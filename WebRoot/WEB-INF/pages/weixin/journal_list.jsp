<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
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
		<title>华诚期刊</title>
		<jsp:include page="/WEB-INF/pages/weixin/wx_inc.jsp" />
		<script type="text/javascript" src="static/front/js/pdfobject.js"></script>
		<link rel="stylesheet" type="text/css" href="static/weixin/phone.css" />
	</head>
  	<body>
			<s:select
			  list="cmsChannels" 
			  listKey="id"
			  listValue="name"
			  headerKey=""
			  headerValue="--全部期刊--"
			  cssClass="ui-select" 
			  name="drop1" 
			  id="selectStatus"
			/>
  		<div id="resultDiv" style="margin-top:10px;margin-bottom: 50px;">
			    <jsp:include page="/WEB-INF/pages/weixin/journal_list_inc.jsp"></jsp:include>
	 	</div>
		<jsp:include page="/WEB-INF/pages/weixin/change_page_inc.jsp">
			<jsp:param value="searchForm" name="sendForm"/> 
			<jsp:param value="resultDiv" name="resultDiv"/> 
			<jsp:param name="currentPage" value="${pageResult.currentPage}"/> 
			<jsp:param name="lastPage" value="${pageResult.lastPage}"/> 
		</jsp:include> 
	 	<div class="none"> 
		<form id="searchForm" action="weixin/journal_list_inc.htm" method="post"> 
			<input id="currPage" name="page" class="J_currPage" value="${pageResult.currentPage}"/> 
		</form> 
		</div> 
  		 
  		
  		
  	<script>
  	$(document).ready(function(){  
  	$(".updaDown").click(
		function dowloadFile(){
			var path = $(this).attr("data");
			if(path == ''){
				$.dialog.tips("没有获取文件路径!");
				return false;
			}
			window.open("static/base/lib/pdfjs-1.1.215-dist/web/viewer.html?file="+base+path);
			 //var myPDF = new PDFObject({ url:path  }).embed();
		}
	);
  	});
 
	/**
	$(".updaDown").click(
		function dowloadFile(){
		
			var path = $(this).attr("data");
			if(path == ''){
				$.dialog.tips("没有获取文件路径!");
				return false;
			}
			window.location.href = base + "dowloadFile.htm?directory="+path;
		}
	);
	**/
  //下拉框js
	$(document).ready(function(){		
		$(".ui-select").selectWidget({
			change       : function (changes) {
			
				var channelId = $("#selectStatus").val();
				// 相关证书异步请求
				$.post(base+"weixin/journal_list_inc.htm",{"channelId":channelId},function(data){
				 	$("#resultDiv").html(data);
				});
			
			
				return changes;
			},
			effect       : "slide",
			keyControl   : true,
			speed        : 200,
			scrollHeight : 250
		});
		
	});	
  	</script>
  		
	</body>
</html>
