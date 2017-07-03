<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html >
<html>
	<head>
    	<base href="${BASE_PATH}"/>
    	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    	<title>会员后台-基本资料</title>
    	<jsp:include page="/WEB-INF/pages/common/member_inc.jsp" />
    	<link rel="stylesheet" href="static/member/product/css/product.css">
    	<script src="static/lib/jquery.form.2.2.7.js?t=${sysVersion}"></script>
    	<script>
    		var gydzkwfw ="${member.gydzkwfw}";
    		var dynrfsdz ="${member.dynrfsdz}";
    	</script>
  	</head>
  	<body>
  		<%--头部 --%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<%--头部end --%>
			
			<div class="member-center-layout">
				<jsp:include page="/WEB-INF/pages/common/left-menu.jsp">
					<jsp:param name="menu" value="1" />
				</jsp:include>
				<div class="member-center-right-layout fl">
					<div class="mcr-lbg"></div>
					<div class="index-content-bg">
						<div class="pwd-title-bg ">基本资料</div>
						<div class="pt10 ml5">
						<s:form id="updMemberMessage">
							<s:hidden id="memberId" name="member.id"/>
							<div class="form-item">
								<span class="item-title w60">姓名：</span>
								<input id="name" name="member.name" value="<s:property value='member.name'/>" class="ui-input-text w150" type="text">
							</div>
							<div class="form-item">
								<span class="item-title w60">性别：</span>
								<label class="fl mr20" style="line-height:27px;font-size:14px;">
									<input id="female" name="sex" value="0" class="fl mr10" style="margin-top:8px;" <c:if test="${member.sex == 0}">checked</c:if> type="radio"/>
									<span class="fl">女</span>
									<div class="cb"></div>
								</label>
								<label class="fl" style="line-height:27px;font-size:14px;">
									<input id="male" name="sex" value="1" class="fl mr10" style="margin-top:8px;" <c:if test="${member.sex == 1}">checked</c:if> type="radio"/>
									<span class="fl">男</span>
									<div class="cb"></div>
								</label>
								<input id="sexValue" name="member.sex" value="1" type="hidden"/>
							</div>
							<div class="form-item">
								<span class="item-title w60">QQ：</span>
								<input id="qq" name="member.qq" value="<s:property value='member.qq'/>" class="ui-input-text w300" type="text">
							</div>
							<div class="form-item">
								<span class="item-title w60">地址：</span>
								<input id="address" name="member.address" value="<s:property value='member.address'/>" class="ui-input-text w300" type="text">
							</div>
							<div class="form-item">
								<span class="item-title w60">行业：</span>
								<input id="industry" name="member.industry" value="<s:property value='member.industry'/>" class="ui-input-text w300" type="text">
							</div>
							<div class="form-item">
								<span class="item-title w60">年收入：</span>
								<input id="annualIncome" name="member.annualIncome" value="<s:property value='member.annualIncome'/>" class="ui-input-text w150" type="text">
							</div>
							<div class="index-select-box">
								<div>
									<label>您是否愿意接受国郓的电子刊物服务</label>
									<label class="ml20">
										<input id="gydzkwfwYes" type="radio" name="member.gydzkwfw" value="1" class="vm">
										<span class="vm ml5">是</span>
									</label>
									<label style="margin-left:62px;">
										<input id="gydzkwfwNo" type="radio" name="member.gydzkwfw" value="2" class="vm">
										<span class="vm ml5">否</span>
									</label>
								</div>
								<div class="mt10">
									<label>订阅内容将发送到您的</label>
									<label style="margin-left: 104px;">
										<input id="dynrfsdzEmail" type="radio" name="member.dynrfsdz" value="1" class="vm">
										<span class="vm ml5">注册邮箱</span>
									</label>
									<label class="ml20">
										<input id="dynrfsdzPhone" type="radio" name="member.dynrfsdz" value="2" class="vm">
										<span class="vm ml5">注册手机</span>
									</label>
								</div>
							</div>
							</s:form>
							<div class="form-item">
								<span class="item-title w100"></span>
								<a href="javascript:void(0);" onclick="submitMemberMessage();" class="red-btn fl">保存</a>
							</div>
						</div>
						<div class="pro-bottom-bg"></div>
					</div>
				</div>
				<div class="cb"></div>
				</div>
		<%--底部 --%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot.jsp"></jsp:include>
		<%--底部end --%>
  	</body>
  	<script src="static/member/js/account_index.js"></script>
</html>