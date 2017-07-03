<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>网银在线支付 - 澳新邮易购</title>
		<meta name="Keywords" content="" />
		<meta name="description" content="" />
	</head>
  	<body onload="document.getElementById('submitForm').submit();return false;">
  		<form id="submitForm" action="http://59.151.64.18/web/payment/validate_certify_info" method="POST">
  			<input type="hidden" name="PReq" value="${payInfoEncodeStr}"/>
  			<input type="hidden" name="PAlg" value="md5"/>
  			<input type="hidden" name="PVer" value="6.0"/>
  			<input type="hidden" name="PSign" value="${payInfoSignStr}"/>
  		</form>
  	</body>
</html>
