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
		<title>华诚- 会员中心</title>
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
							<a class="m-patient cur" href="member/account/index.htm">我的报名</a>
						</li>
						<li>
							<a class="m-team" href="member/account/team.htm">我的团队</a>
						</li>
						<li>
							<a class="m-account" href="javascript:void(0);">帐户管理</a>
							<div class="ma-box">
								<a class="ma-zl" href="member/account/my_data.htm">我的资料</a>
							</div>
							<div class="ma-box">
								<a class="ma-mm" href="member/account/update_pwd.htm">修改密码</a>
							</div>
						</li>
					</ul>
					<div class="m-service-box mt20">
						<div class="up tc">客服中心</div>
						<!-- <div class="down">
							<div class="scl-dh-ico">
								<div class="qbd-font">请拨打</div>
								<div class="tel-font">400-025-2560</div>
							</div>
						</div> -->
						<a href="mailto:info@heros.org.cn" class="fyj-btn">发邮件给我们</a>
						<div class="gfwx">官方邮箱：info@heros.org.cn</div>
						<div class="gfwx">微信公众账号：HEROS</div>
					</div>
				</div>
				<%--左边end--%>
				<%--右边--%>
				<div class="m-right-box fr">
					<table class="account-table">
						<tr class="account-tt-tr">
							<td width="300px" style="padding-left:20px;">赛事名称</td>
							<td width="150px" class="tc">开赛时间</td>
							<td width="200px" class="tc">地点</td>
							<td width="120px" class="tc">状态</td>
							<td width="" class="tc">操作</td>
						</tr>
						
						
						<tr class="account-cc-tr gray-bg">
							<td width="300px" style="padding-left:20px;">
								中国·成都第五届自行车车迷健身节（锦江站）
							</td>
							<td width="150px" class="tc">2012-12-12</td>
							<td width="200px" class="tc">上海市杨浦区国定路335号2号楼下</td>
							<td width="120px" class="tc">已支付</td>
							<td width="" class="tc">
								<a href="#">立即支付</a>
								<a href="#" class="ml10">取消报名</a>
							</td>
						</tr>
						<tr class="account-cc-tr">
							<td width="300px" style="padding-left:20px;">
								中国·成都第五届自行车车迷健身节（锦江站）
							</td>
							<td width="150px" class="tc">2012-12-12</td>
							<td width="200px" class="tc">上海市杨浦区国定路335号2号楼下</td>
							<td width="120px" class="tc">未支付</td>
							<td width="" class="tc">
								<a href="#">立即支付</a>
								<a href="#" class="ml10">取消报名</a>
							</td>
						</tr>
						<tr class="account-cc-tr gray-bg">
							<td width="300px" style="padding-left:20px;">
								中国·成都第五届自行车车迷健身节（锦江站）
							</td>
							<td width="150px" class="tc">2012-12-12</td>
							<td width="200px" class="tc">上海市杨浦区国定路335号2号楼下</td>
							<td width="120px" class="tc">已支付</td>
							<td width="" class="tc">
								<a href="#">立即支付</a>
								<a href="#" class="ml10">取消报名</a>
							</td>
						</tr>
						<tr class="account-cc-tr">
							<td width="300px" style="padding-left:20px;">
								中国·成都第五届自行车车迷健身节（锦江站）
							</td>
							<td width="150px" class="tc">2012-12-12</td>
							<td width="200px" class="tc">上海市杨浦区国定路335号2号楼下</td>
							<td width="120px" class="tc">未支付</td>
							<td width="" class="tc">
								<a href="#">立即支付</a>
								<a href="#" class="ml10">取消报名</a>
							</td>
						</tr>
						<tr class="account-cc-tr gray-bg">
							<td width="300px" style="padding-left:20px;">
								中国·成都第五届自行车车迷健身节（锦江站）
							</td>
							<td width="150px" class="tc">2012-12-12</td>
							<td width="200px" class="tc">上海市杨浦区国定路335号2号楼下</td>
							<td width="120px" class="tc">已支付</td>
							<td width="" class="tc">
								<a href="#">立即支付</a>
								<a href="#" class="ml10">取消报名</a>
							</td>
						</tr>
						<tr class="account-cc-tr">
							<td width="300px" style="padding-left:20px;">
								中国·成都第五届自行车车迷健身节（锦江站）
							</td>
							<td width="150px" class="tc">2012-12-12</td>
							<td width="200px" class="tc">上海市杨浦区国定路335号2号楼下</td>
							<td width="120px" class="tc">未支付</td>
							<td width="" class="tc">
								<a href="#">立即支付</a>
								<a href="#" class="ml10">取消报名</a>
							</td>
						</tr>
						<tr class="account-cc-tr gray-bg">
							<td width="300px" style="padding-left:20px;">
								中国·成都第五届自行车车迷健身节（锦江站）
							</td>
							<td width="150px" class="tc">2012-12-12</td>
							<td width="200px" class="tc">上海市杨浦区国定路335号2号楼下</td>
							<td width="120px" class="tc">已支付</td>
							<td width="" class="tc">
								<a href="#">立即支付</a>
								<a href="#" class="ml10">取消报名</a>
							</td>
						</tr>
						<tr class="account-cc-tr">
							<td width="300px" style="padding-left:20px;">
								中国·成都第五届自行车车迷健身节（锦江站）
							</td>
							<td width="150px" class="tc">2012-12-12</td>
							<td width="200px" class="tc">上海市杨浦区国定路335号2号楼下</td>
							<td width="120px" class="tc">未支付</td>
							<td width="" class="tc">
								<a href="#">查看报名信息</a>
							</td>
						</tr>
					</table>
					<div class="grs-bline"></div>
					<div class="mt20 clb">
						<div class="news-pager fr pl5 clb">
							<div class="fl mr10">
								<span class="news-page-font mr3">当前第</span>
								<span class="news-page-no mr3"> 1 </span>
								<span class="news-page-font mr3">页 / 总共</span>
								<span class="news-page-no mr3">88</span>
								<span class="news-page-font">页</span>
							</div>
							<div class="fl">
								<a class="news-pager-btn" href="#">首页</a>
								<a class="news-pager-btn" href="#">上一页</a>
								<a class="news-pager-btn" href="#">下一页</a>
								<a class="news-pager-btn" href="#">尾页</a>
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