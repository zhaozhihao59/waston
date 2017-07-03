<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html >
<html >
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
		<meta name="baidu-site-verification" content="k66pxbcp2Z" />
		<title>澳新邮易购 - 会员中心首页</title>
		<meta name="Keywords" content="新西兰直邮商品,澳大利亚代购,新西兰代购" />
		<meta name="description" content="澳新邮易购 - 100%新西兰直邮商品,购买新西兰产品最优选择。" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
	</head>
  	<body>
  		<%--头部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<%--头部end--%>
		<div class="member-center-mmbox">
			<jsp:include page="/WEB-INF/pages/member/comm/member_left.jsp"></jsp:include>
			<form id="passwordForm" action="member/account/doUpdatePwd.htm" method="post">
				<div class="mcm-right-box fr">
					<div class="mrb-wel-font">修改密码</div>
					<div class="mrb-content-layout clb" style="height:265px;">
						<div class="front-form-item">
							<span class="front-item-title w130 mr10"><label class="red-xx">*</label>旧密码：</span>
							<input id="passworded" name="passworded" type="password"  class="address-text w250 fl">
							<span id="passwordedTip"></span>
						</div>
						<div class="front-form-item">
							<span class="front-item-title w130 mr10"><label class="red-xx">*</label>新密码：</span>
							<input id="password" type="password"  name="item.password" class="address-text w250 fl">
							<span id="passwordTip"></span>
						</div>
						<div class="front-form-item">
							<span class="front-item-title w130 mr10"><label class="red-xx">*</label>确认密码：</span>
							<input id="rePassword" type="password"   class="address-text w250 fl">
							<span id="rePasswordTip"></span>
						</div>
						<div class="front-form-item" style="margin-top:20px;">
							<span class="front-item-title w130 mr10"></span>
							<a  id="passwordBtn"  href="javascript:void(0)" class="co-save-btn fl" style="margin:5px 0 0 10px;">保存</a>
						</div>
					</div>
				</div>
			</form>
			<div class="cb"></div>
		</div>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
  </body>
  	<script src="static/member/account/change_pwd.js"></script>
  	<script>
  		$(".mm-a").addClass("cur");
  	</script>
</html>
