<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html >
<html style="overflow-x:hidden;">
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
		<meta name="baidu-site-verification" content="k66pxbcp2Z" />
		<title>我的项目 - 创始人平台 - 关注创业项目的发展</title>
		<meta name="Keywords" content="" />
		<meta name="description" content="" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/member/comm/common.css" />
		<link rel="stylesheet" type="text/css" href="static/member/css/account.css" />
	</head>
	<body>
		<jsp:include page="/WEB-INF/pages/member/comm/front_header.jsp"></jsp:include>
		<div class="body-bg">
			<div class="account-index-layout">
				<%--个人信息--%>
				<div class="account-msg-box clb">
					<div class="account-head-img fl">
						<a href=""><img class="img-border vm" width="61px" height="61px" src="static/front/css/images/index-img/test-img.png"></a>
					</div>
					<div class="account-msg-detail fl">
						<div class="clb">
							<div class="an-col fl">丁香园</div>
							<ul class="an-ul fl ml30">
								<li>女</li>
								<li>|</li>
								<li>23岁</li>
								<li>|</li>
								<li>上海 杨浦区</li>
							</ul>
							<a href="" class="an-update-btn fl">修改</a>
							<div class="cb"></div>
						</div>
						<div class="mt5">
							<label class="qm-tcol f12">签名</label>
							<label class="qm-ccol f12">三分天注定，七分靠打拼</label>
						</div>
						<div class="mt5">
							<label class="qm-tcol f12">擅长</label>
							<label class="qm-ccol f12">收集情报</label>
							<label class="qm-ccol f12">收集情报</label>
							<label class="qm-ccol f12">收集情报</label>
							<label class="qm-ccol f12">收集情报</label>
							<label class="qm-ccol f12">收集情报</label>
						</div>
					</div>
					<div class="account-statistics-box fl">
						<div class="as-div fl">
							<a href="">
								<span class="as-num-font f22">2220</span><br/>
								<span class="as-font f12">浏览数</span>
							</a>
						</div>
						<div class="as-div fl">
							<a href="">
								<span class="as-num-font f22">2220</span><br/>
								<span class="as-font f12">粉丝</span>
							</a>
						</div>
						<div class="as-div fl">
							<a href="">
								<span class="as-num-font f22">2220</span><br/>
								<span class="as-font f12">关注了</span>
							</a>
						</div>
					</div>
				</div>
				<%--个人信息end--%>
				<div class="account-nav">
					<ul>
						<li class="pr">
							<a href="member/account/index.htm">我的动态</a>
							<div class="trends-ico">11</div>
						</li>
						<li><a href="member/account/project.htm" class="account-nav-select">我的项目</a></li>
						<li><a href="member/account/collect.htm">关注项目</a></li>
						<li><a href="member/account/comment.htm">我的评论</a></li>
					</ul>
				</div>
				
				<div class="my-project-box">
					<div>
						<div class="project-name fl">${dv:e("关于面向校园学生的生活服务平台关于面向校生的务平台",60)}</div>
						<div class="fr">
							<a href="member/project/progress.htm" class="c-name ml10 f12">新增动态</a>
							<label class="ml10 c-name f12">|</label>
							<a href="" class="c-name ml10 f12">编辑</a>
							<label class="ml10 c-name f12">|</label>
							<a href="" class="c-name ml10 f12">删除</a>
						</div>
						<div class="cb"></div>
					</div>
					
					<div class="pj-intro-box mt20">
						一个小区生活移动平台，让家家户户通过手机购买生鲜百货和生活服务。一个小区生活移动平台，让家家户户通过手机购买生鲜百货和生活服务
						一个小区生活移动平台，让家家户户通过手机购买生鲜百货和生活服务。一个小区生活移动平台，让家家户户通过手机购买生鲜百货和生活服务
						一个小区生活移动平台，让家家户户通过手机购买生鲜百货和生活服务。一个小区生活移动平台，让家家户户通过手机购买生鲜百货和生活服务
					</div>
					
					<div class="">
						<div class="gd-bg fl"></div>
						<div class="fr pj-content">
							<div class="gd-color fl" style="width:140px;">
								已有：商业服务-场地
							</div>
							<div class="fl content-col" style="width:775px;">
								我的同学张三表示他对这项目挺感兴趣，要加入我们的团队的团的团的团的团的团，张三他热情开 朗，善于言谈，对电子商务运营有一套他自己的见解，能很好管理
							</div>
						</div>
						<div class="cb"></div>
					</div>
					<div class="">
						<div class="gd-bg fl"></div>
						<div class="fr pj-content">
							<div class="gd-color fl" style="width:140px;">
								已有：商业服务-设计
							</div>
							<div class="fl content-col" style="width:775px;">
								我的同学张三表示他对这项目挺感兴趣，要加入我们的团队的团的团的团的团的团，张三他热情开 朗，善于言谈，对电子商务运营有一套他自己的见解，能很好管理
							</div>
						</div>
						<div class="cb"></div>
					</div>
					<div class="">
						<div class="rd-bg fl"></div>
						<div class="fr pj-content">
							<div class="rd-color fl" style="width:140px;">
								缺少：项目启动资金
							</div>
							<div class="fl content-col" style="width:775px;">
								我的同学张三表示他对这项目挺感兴趣，要加入我们的团队的团的团的团的团的团，张三他热情开 朗，善于言谈，对电子商务运营有一套他自己的见解，能很好管理
							</div>
						</div>
						<div class="cb"></div>
					</div>
					
					<ul class="account-pd-scale mt20" style="width:100%">
						<li class="aps-li">
							<b class="fl">创始人</b>
							<div class="fr account-pj-detail mb10">
								<div class="fl account-date-box ml5" style="margin-top:12px;">2012-12-12 09:00</div>
								<div class="box fr">
									<em class="to-left"></em>
									<div class="pj-title">工作计划：制定面向大学生的调研报告</div>
									<div class="pj-content-font">
										早餐后，驱车前往庞贝古城*（游览时间不少于2小时）,参观埋没地下两千多年的遗迹并远眺维苏威火山雄壮景观及美丽的地中海景致。 之
										早餐后，驱车前往庞贝古城*（游览时间不少于2小时）,参观埋没地下两千多年的遗迹并远眺维苏威火山雄壮景观及美丽的地中海景致。 之
									</div>
								</div>
							</div>
						</li>
						<li class="aps-li">
							<b class="fl">创始人</b>
							<div class="fr account-pj-detail mb10">
								<div class="fl account-date-box ml5" style="margin-top:12px;">2012-12-12 09:00</div>
								<div class="box fr">
									<em class="to-left"></em>
									<div class="pj-title">工作计划：制定面向大学生的调研报告</div>
									<div class="pj-content-font">
										早餐后，驱车前往庞贝古城*（游览时间不少于2小时）,参观埋没地下两千多年的遗迹并远眺维苏威火山雄壮景观及美丽的地中海景致。 之
										早餐后，驱车前往庞贝古城*（游览时间不少于2小时）,参观埋没地下两千多年的遗迹并远眺维苏威火山雄壮景观及美丽的地中海景致。 之
									</div>
								</div>
							</div>
						</li>
					</ul>
					
					
					<%--申请关注--%>
					<div class="apply-for-box">
						<div class="afb-title">申请关注</div>
						<%--一个--%>
						<div class="afb-content f9col">
							<div class="afb-l fl">
								<div class="account-head-img fl">
									<a href=""><img class="img-border vm" width="61px" height="61px" src="static/front/css/images/index-img/test-img.png"></a>
								</div>
								<div class="pj-msg-detail fr">
									<div class="clb">
										<div class="an-col fl">丁香园</div>
										<ul class="an-ul fl ml30">
											<li>女</li>
											<li>|</li>
											<li>23岁</li>
											<li>|</li>
											<li>上海 杨浦区</li>
										</ul>
										<div class="cb"></div>
									</div>
									<div class="mt10">
										<a href="" class="c-name f12">同意</a>
										<a href="" class="c-name f12 ml30">拒绝</a>
										<a href="" class="c-name f12 ml30">删除</a>
									</div>
								</div>
							</div>
							<div class="afb-r fr">
								<div class="account-head-img fl">
									<a href=""><img class="img-border vm" width="61px" height="61px" src="static/front/css/images/index-img/test-img.png"></a>
								</div>
								<div class="pj-msg-detail fr">
									<div class="clb">
										<div class="an-col fl">丁香园</div>
										<ul class="an-ul fl ml30">
											<li>女</li>
											<li>|</li>
											<li>23岁</li>
											<li>|</li>
											<li>上海 杨浦区</li>
										</ul>
										<div class="cb"></div>
									</div>
									<div class="mt10">
										<a href="" class="c-name f12">同意</a>
										<a href="" class="c-name f12 ml30">拒绝</a>
										<a href="" class="c-name f12 ml30">删除</a>
									</div>
								</div>
							</div>
							<div class="cb"></div>
						</div>
						<%--一个end--%>
						
						<%--一个--%>
						<div class="afb-content">
							<div class="afb-l fl">
								<div class="account-head-img fl">
									<a href=""><img class="img-border vm" width="61px" height="61px" src="static/front/css/images/index-img/test-img.png"></a>
								</div>
								<div class="pj-msg-detail fr">
									<div class="clb">
										<div class="an-col fl">丁香园</div>
										<ul class="an-ul fl ml30">
											<li>女</li>
											<li>|</li>
											<li>23岁</li>
											<li>|</li>
											<li>上海 杨浦区</li>
										</ul>
										<div class="cb"></div>
									</div>
									<div class="mt10">
										<a href="" class="c-name f12">同意</a>
										<a href="" class="c-name f12 ml30">拒绝</a>
										<a href="" class="c-name f12 ml30">删除</a>
									</div>
								</div>
							</div>
							<div class="afb-r fr">
								<div class="account-head-img fl">
									<a href=""><img class="img-border vm" width="61px" height="61px" src="static/front/css/images/index-img/test-img.png"></a>
								</div>
								<div class="pj-msg-detail fr">
									<div class="clb">
										<div class="an-col fl">丁香园</div>
										<ul class="an-ul fl ml30">
											<li>女</li>
											<li>|</li>
											<li>23岁</li>
											<li>|</li>
											<li>上海 杨浦区</li>
										</ul>
										<div class="cb"></div>
									</div>
									<div class="mt10">
										<a href="" class="c-name f12">同意</a>
										<a href="" class="c-name f12 ml30">拒绝</a>
										<a href="" class="c-name f12 ml30">删除</a>
									</div>
								</div>
							</div>
							<div class="cb"></div>
						</div>
						<%--一个end--%>
						<%--一个--%>
						<div class="afb-content f9col">
							<div class="afb-l fl">
								<div class="account-head-img fl">
									<a href=""><img class="img-border vm" width="61px" height="61px" src="static/front/css/images/index-img/test-img.png"></a>
								</div>
								<div class="pj-msg-detail fr">
									<div class="clb">
										<div class="an-col fl">丁香园</div>
										<ul class="an-ul fl ml30">
											<li>女</li>
											<li>|</li>
											<li>23岁</li>
											<li>|</li>
											<li>上海 杨浦区</li>
										</ul>
										<div class="cb"></div>
									</div>
									<div class="mt10">
										<a href="" class="c-name f12">同意</a>
										<a href="" class="c-name f12 ml30">拒绝</a>
										<a href="" class="c-name f12 ml30">删除</a>
									</div>
								</div>
							</div>
							<div class="afb-r fr">
								<div class="account-head-img fl">
									<a href=""><img class="img-border vm" width="61px" height="61px" src="static/front/css/images/index-img/test-img.png"></a>
								</div>
								<div class="pj-msg-detail fr">
									<div class="clb">
										<div class="an-col fl">丁香园</div>
										<ul class="an-ul fl ml30">
											<li>女</li>
											<li>|</li>
											<li>23岁</li>
											<li>|</li>
											<li>上海 杨浦区</li>
										</ul>
										<div class="cb"></div>
									</div>
									<div class="mt10">
										<a href="" class="c-name f12">同意</a>
										<a href="" class="c-name f12 ml30">拒绝</a>
										<a href="" class="c-name f12 ml30">删除</a>
									</div>
								</div>
							</div>
							<div class="cb"></div>
						</div>
						<%--一个end--%>
					</div>
					<%--申请关注end--%>
					
					<%--新增项目--%>
					<div class="add-project">
						<a href="" class="add-pj-btn">新增项目</a>
					</div>
					<%--新增项目end--%>
					
					
				</div>
				
			</div>
		</div>
		<jsp:include page="/WEB-INF/pages/member/comm/front_footer.jsp"></jsp:include>
	</body>
</html>