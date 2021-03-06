<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html >
<html >
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
		<meta name="baidu-site-verification" content="k66pxbcp2Z" />
		<title>HEROS - 自行车赛 - 会员中心</title>
		<meta name="Keywords" content="自行车赛" />
		<meta name="description" content="自行车赛" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css_v2/common.css" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
	</head>
	
	<body>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<div class="account-top-bg"></div>
			<div class="jck-account-box clb">
				<%--左边--%>
				<div class="m-left-box fl">
					<div class="m-center-f clb">
						<div class="ico fl"></div>
						会员中心
					</div>
					<ul class="m-menu-box clb mt10">
						<li>
							<a class="m-patient " href="member/account/index.htm">我的报名</a>
						</li>
						<li>
							<a class="m-team" href="member/account/team.htm">我的团队</a>
						</li>
						<li>
							<a class="m-account cur" href="javascript:void(0);">帐户管理</a>
							<div class="ma-box">
								<a class="ma-zl " href="member/account/my_data.htm">我的资料</a>
							</div>
							<div class="ma-box">
								<a class="ma-mm cur" href="member/account/update_pwd.htm">修改密码</a>
							</div>
						</li>
					</ul>
					<div class="m-service-box mt20">
						<div class="up tc">客服中心</div>
						<div class="down">
							<div class="scl-dh-ico">
								<div class="qbd-font">请拨打</div>
								<div class="tel-font">400-025-2560</div>
							</div>
						</div>
						<a href="#" class="fyj-btn">发邮件给我们</a>
					</div>
				</div>
				<%--左边end--%>
				<%--右边--%>
				<div class="m-right-box fr">
					<div class="add-team-box">
						<div class="xzcy-font">修改密码</div>
						<div class="mt10" style="color:#b3b3b3;">您需要在下方输入您当前密码，通过验证后，即可设置您的新密码。</div>
						<div class="ml50">
							<div class="form-item" style="margin-top:20px;">
								<span class="item-title w100"><em>*</em>当前密码： </span>
								<input class="ui-input-text w150" type="password">
							</div>
							<div class="form-item">
								<span class="item-title w100"><em>*</em>新密码： </span>
								<input class="ui-input-text w250" type="password">
							</div>
							<div class="form-item">
								<span class="item-title w100"></span>
								<ul class="state-ul" style="margin-top:7px;">
									<li class="select-state">弱</li>
									<li>中</li>
									<li>强</li>
								</ul>
							</div>
							<div class="form-item">
								<span class="item-title w100"><em>*</em>确认密码： </span>
								<input class="ui-input-text w250" type="password">
							</div>
							<div class="form-item">
								<span class="item-title w100"></span>
								<a href="#" class="save-btn fl">保存</a>
							</div>
						</div>
					</div>
					
					
					
				</div>
				<%--右边end--%>
			</div>
		
		
		
		<%--尾部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
		<%--尾部end--%>
	</body>
</html>