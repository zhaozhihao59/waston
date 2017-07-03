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
		<title>我的评论 - 创始人平台 - 关注创业项目的发展</title>
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
						<li><a href="member/account/project.htm">我的项目</a></li>
						<li><a href="member/account/collect.htm">关注项目</a></li>
						<li><a href="member/account/comment.htm" class="account-nav-select">我的评论</a></li>
					</ul>
				</div>
				
				<div class="my-comment-box">
					<%--一个评论--%>
					<div class="subject-box mt20">
						<div class="comment-jt"></div>
						<div class="account-head-img fl">
							<a href="">
								<img class="img-border vm" width="61px" height="61px" src="static/front/css/images/index-img/test-img.png">
							</a>
						</div>
						<div class="my-comment-content fl ml15">
							<p class="cp-tf" style="line-height:15px;"><a href="" title="">${dv:e("关于面向校园学生的生生活服活服务面向校生的务平台",50)}</a></p>
							<p class="cp-cf mt10" style="line-height:20px;">
								${dv:e("一个小区生活移动平台，让家家户户通过手机购买生鲜百货和 生活服务。一个小区生活移动平户户通过手机购买生鲜百货和 生活服务。一个一个小区生活移动平台，让家家户户通过手机购买生鲜百货和 生活服务。一个小区生活移动平户户通过手机购买生鲜百货和 生活服务。一个",180)}
							</p>
						</div>
						<div class="fr">
							<div class="mc-lls-box fl">
								<p style="margin-top:2px;">浏览数</p>
								<p class="fb">1111</p>
							</div>
							<div class="mc-lls-box fl">
								<p style="margin-top:2px;">浏览数</p>
								<p class="fb">1111</p>
							</div>
							<div class="mc-lls-box fl">
								<p style="margin-top:2px;">浏览数</p>
								<p class="fb">1111</p>
							</div>
						</div>
						<div class="cb"></div>
					</div>
					<%--一个评论end--%>
					<%--一个回复 --%>
					<div class="reply-box">
						<div class="fl">
							<div class="pd-head-img">
								<a href="">
									<img class="img-border" width="38px" height="38px" src="static/front/css/images/index-img/test-img.png">
								</a>
							</div>
							<div class="mcu-name"><a href="" title="">哈哈哈</a></div>
						</div>
						<div class="mc-content-box fr">
							<div class="mc-content-font">“如果计划再做细点的话，我相信会引来更多的投资，这个细点的话，我“如果计划再做细点的话，我相信会引来更多的投资越来越多的互联网“如果计划再做细点的话，我相信会引来更多的投资，这个细点的话，我“如果计划再做细点的话，我相信会引来更多的投资越来越多的互联网</div>
							<div>
								<div class="mc-date fl">2012-12-12 12：12</div>
								<div class="fr favour-font">
									<a class="mr20" href="">赞同（320）</a>
									<a href="">踩（320）</a>
								</div>
							</div>
						</div>
						<div class="cb"></div>
					</div>
					<%--一个回复end --%>
					<%--一个回复 --%>
					<div class="reply-box">
						<div class="fl">
							<div class="pd-head-img">
								<a href="">
									<img class="img-border" width="38px" height="38px" src="static/front/css/images/index-img/test-img.png">
								</a>
							</div>
							<div class="mcu-name"><a href="" title="">哈哈哈</a></div>
						</div>
						<div class="mc-content-box fr">
							<div class="mc-content-font">“如果计划再做细点的话，我相信会引来更多的投资，这个细点的话，我“如果计划再做细点的话，我相信会引来更多的投资越来越多的互联网“如果计划再做细点的话，我相信会引来更多的投资，这个细点的话，我“如果计划再做细点的话，我相信会引来更多的投资越来越多的互联网</div>
							<div>
								<div class="mc-date fl">2012-12-12 12：12</div>
								<div class="fr favour-font">
									<a class="mr20" href="">赞同（320）</a>
									<a href="">踩（320）</a>
								</div>
							</div>
						</div>
						<div class="cb"></div>
					</div>
					<%--一个回复end --%>
					
					<%--一个评论--%>
					<div class="subject-box">
						<div class="comment-jt"></div>
						<div class="account-head-img fl">
							<a href="">
								<img class="img-border vm" width="61px" height="61px" src="static/front/css/images/index-img/test-img.png">
							</a>
						</div>
						<div class="my-comment-content fl ml15">
							<p class="cp-tf" style="line-height:15px;"><a href="" title="">${dv:e("关于面向校园学生的生生活服活服务面向校生的务平台",50)}</a></p>
							<p class="cp-cf mt10" style="line-height:20px;">
								${dv:e("一个小区生活移动平台，让家家户户通过手机购买生鲜百货和 生活服务。一个小区生活移动平户户通过手机购买生鲜百货和 生活服务。一个一个小区生活移动平台，让家家户户通过手机购买生鲜百货和 生活服务。一个小区生活移动平户户通过手机购买生鲜百货和 生活服务。一个",180)}
							</p>
						</div>
						<div class="fr">
							<div class="mc-lls-box fl">
								<p style="margin-top:2px;">浏览数</p>
								<p class="fb">1111</p>
							</div>
							<div class="mc-lls-box fl">
								<p style="margin-top:2px;">浏览数</p>
								<p class="fb">1111</p>
							</div>
							<div class="mc-lls-box fl">
								<p style="margin-top:2px;">浏览数</p>
								<p class="fb">1111</p>
							</div>
						</div>
						<div class="cb"></div>
					</div>
					<%--一个评论end--%>
					<%--一个回复 --%>
					<div class="reply-box">
						<div class="fl">
							<div class="pd-head-img">
								<a href="">
									<img class="img-border" width="38px" height="38px" src="static/front/css/images/index-img/test-img.png">
								</a>
							</div>
							<div class="mcu-name"><a href="" title="">哈哈哈</a></div>
						</div>
						<div class="mc-content-box fr">
							<div class="mc-content-font">“如果计划再做细点的话，我相信会引来更多的投资，这个细点的话，我“如果计划再做细点的话，我相信会引来更多的投资越来越多的互联网“如果计划再做细点的话，我相信会引来更多的投资，这个细点的话，我“如果计划再做细点的话，我相信会引来更多的投资越来越多的互联网</div>
							<div>
								<div class="mc-date fl">2012-12-12 12：12</div>
								<div class="fr favour-font">
									<a class="mr20" href="">赞同（320）</a>
									<a href="">踩（320）</a>
								</div>
							</div>
						</div>
						<div class="cb"></div>
					</div>
					<%--一个回复end --%>
					<%--一个回复 --%>
					<div class="reply-box">
						<div class="fl">
							<div class="pd-head-img">
								<a href="">
									<img class="img-border" width="38px" height="38px" src="static/front/css/images/index-img/test-img.png">
								</a>
							</div>
							<div class="mcu-name"><a href="" title="">哈哈哈</a></div>
						</div>
						<div class="mc-content-box fr">
							<div class="mc-content-font">“如果计划再做细点的话，我相信会引来更多的投资，这个细点的话，我“如果计划再做细点的话，我相信会引来更多的投资越来越多的互联网“如果计划再做细点的话，我相信会引来更多的投资，这个细点的话，我“如果计划再做细点的话，我相信会引来更多的投资越来越多的互联网</div>
							<div>
								<div class="mc-date fl">2012-12-12 12：12</div>
								<div class="fr favour-font">
									<a class="mr20" href="">赞同（320）</a>
									<a href="">踩（320）</a>
								</div>
							</div>
						</div>
						<div class="cb"></div>
					</div>
					<%--一个回复end --%>
				</div>
				
				
				
				
			</div>
		</div>
		<jsp:include page="/WEB-INF/pages/member/comm/front_footer.jsp"></jsp:include>
		<script>
			$(function(){
				var hoverBox=$(".aps-li").children(".account-state-detail");
				
				$(hoverBox).each(function(){
					var $this = $(this);
					$this.hover(function(){
						var obBox=$this.children(".operate-box");
						$(obBox).removeClass("none");
					},function(){
						var obBox=$this.children(".operate-box");
						$(obBox).addClass("none");
					});
				});
				
			});
		</script>
	</body>
</html>