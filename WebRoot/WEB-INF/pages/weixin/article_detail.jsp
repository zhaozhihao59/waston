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
		<title>华诚 - 工作机会</title>
		<jsp:include page="/WEB-INF/pages/weixin/wx_inc.jsp" />
		<script type="text/javascript" src="static/front/js/pdfobject.js"></script>
		<link rel="stylesheet" type="text/css" href="static/weixin/phone.css" />
	</head>
  	<body>
			<div class="and-showbox">
	  		<div class="hbd">
				<div class="hmain" style="font-weight:normal;color:#555555;">工作机会</div>
				<div class="hleft"  onclick="history.go(-1);">
					<a href="javascript:void(0);" class="scl-goback-btn"></a>
				</div>
				<div class="hright">
				</div>
			</div>
			<c:forEach items="${listCmsArticle }" var="cmsArticle">
				<div class="and-list-box">
				<div class="anl-ttfont2" style="font-size:20px;">${cmsChannel.name }</div>
				<div class="hdrq-font" style="font-size:12px;">${cmsArticle.summary }</div>
			</div>
				<div class="scl-bj-box">
	  			<div class="scl-bjccbox">
					${cmsArticle.content }
	  			</div>
	  		</div>
			</c:forEach>
  		</div>
	</body>
</html>
