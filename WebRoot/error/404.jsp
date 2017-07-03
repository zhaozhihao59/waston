<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>404</title>
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
    	<link rel="stylesheet" type="text/css"href="static/front/css/index.css" />  
	</head>
	<body>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp">
  			<jsp:param name="menu" value="hotel_index" />
  		</jsp:include>
  		<div class="content">
  			<div class="404-bg"></div>
  		</div>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot.jsp"></jsp:include>
	</body>
</html>

