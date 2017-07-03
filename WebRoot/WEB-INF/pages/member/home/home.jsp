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
		<title>首页 - 创始人平台 - 关注创业项目的发展</title>
		<meta name="Keywords" content="" />
		<meta name="description" content="" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/member/css/member-index.css" />
		<link rel="stylesheet" type="text/css" href="static/member/comm/common.css" />
	</head>
	<body>
		<jsp:include page="/WEB-INF/pages/member/comm/front_header.jsp"></jsp:include>
		<div class="body-bg">
			<div class="home-index-layout">
				<div class="home-index-left fl">
					<%--一个--%>
					<div class="mb20">
						<div class="home-content-list">
							<div class="fl">
								<div class="hc-head-img pr z-index100">
									<a href=""><img width="61px" height="61px" class="img-border" src="static/front/css/images/index-img/test-img.png"></a>
									<div class="person-msg-box none">
										<div class="pmb-jt"></div>
										<div>
											<ul class="pmb-msg fl">
												<li>丁香园</li>
												<li>|</li>
												<li>女</li>
												<li>|</li>
												<li>23岁</li>
												<li>|</li>
												<li>上海 杨浦区</li>
											</ul>
											<a href="" class="fr attention-btn"></a>
											<div class="cb"></div>
										</div>
										<div class="autograph-box ml5">
											<div class="fl" style="color:#676767;line-height:20px;">签名</div>
											<div class="fl ml10" style="color:#959494;width:330px;line-height:20px;">我打江南走过，那等在季节里的容颜如莲花的开落，东风不来，三月的柳絮不飞，你的心如小小的寂寞的城！</div>
											<div class="cb"></div>
										</div>
										<div class="autograph-box ml5">
											<div class="fl" style="color:#676767;line-height:20px;">擅长</div>
											<div class="fl ml10" style="color:#959494;width:330px;line-height:20px;">JAVA JSP PHP JAVASCRIPT PHOTOSHOP </div>
											<div class="cb"></div>
										</div>
									</div>
								</div>
								<div class="satisfied-num">2260</div>
								<div class="satisfied-font">意愿度</div>
								<%--三种状态<a href="" class="wait-btn"></a>
								<div class="satisfied-font">取消关注</div> --%>
								<%--<a href="" class="sq-btn"></a>
								<div class="satisfied-font">申请关注</div>--%>
								<a href="" class="wait-sh-btn"></a>
								<div class="satisfied-font">等待审核</div>
							</div>
							<div class="hc-content-box fr">
								<div>
									<div class="hc-title">
										<a href="member/project/detail.htm" title="" class="hc-title-a fl">${dv:e("关于面向校园学生的生活服务平台关于面向校园学生的生活服务平台关于面向校园学生的生活服务平台关于面向校园学生的生活服务平台",80)}</a>
									</div>
									<div class="cb"></div>
								</div>
								<div class="hc-introduce mt10">
									${dv:e("一个小区生活移动平台，让家家户户通过手机购买生鲜百货和生活服务。一个小区生活移动平台，让家家户户通过手机购买生鲜百货和生活服务",150)}
								</div>
								
								<div class="mt10">
									<div class="">
										<div class="gd-bg fl"></div>
										<div class="fr gd-content">
											<div class="gd-color fl" style="width:140px;">
												已有：商业服务-场地
											</div>
											<div class="fl content-col" style="width:430px;line-height:22px;">
												我的同学张三表示他对这项目挺感兴趣，要加入我们的团队，张三他热情开 朗，善于言谈，对电子商务运营有一套他自己的见解，能很好管理
											</div>
										</div>
										<div class="cb"></div>
									</div>
									<div class="">
										<div class="gd-bg fl"></div>
										<div class="fr gd-content">
											<div class="gd-color fl" style="width:140px;">
												已有：商业服务-设计
											</div>
											<div class="fl content-col" style="width:430px;line-height:22px;">
												我的同学张三表示他对这项目挺感兴趣，要加入我们的团队，张三他热情开 朗，善于言谈，对电子商务运营有一套他自己的见解，能很好管理
											</div>
										</div>
										<div class="cb"></div>
									</div>
									<div class="">
										<div class="rd-bg fl"></div>
										<div class="fr gd-content">
											<div class="rd-color fl" style="width:140px;">
												缺少：项目启动资金
											</div>
											<div class="fl content-col" style="width:430px;line-height:22px;">
												我的同学张三表示他对这项目挺感兴趣，要加入我们的团队，张三他热情开 朗，善于言谈，对电子商务运营有一套他自己的见解，能很好管理
											</div>
										</div>
										<div class="cb"></div>
									</div>
								</div>
								<div class="hc-show-box mt15">
									<ul class="pd-scale">
										<li class="aps-li clb">
											<b class="fl">创始人</b>
											<div class="fr pd-state-detail">
												<div class="fl pdi-date-box ml5">2012-12-12 09:00</div>
												<div class="fl pdi-content">
													工作计划：制定面向大学生的调研报告
												</div>
											</div>
										</li>
										<li class="aps-li clb">
											<b class="fl">创始人</b>
											<div class="fr pd-state-detail">
												<div class="fl pdi-date-box ml5">2012-12-12 09:00</div>
												<div class="fl pdi-content">
													工作计划：制定面向大学生的调研报告
												</div>
											</div>
										</li>
										<li class="aps-li clb">
											<b class="fl">创始人</b>
											<div class="fr pd-state-detail">
												<div class="fl pdi-date-box ml5">2012-12-12 09:00</div>
												<div class="fl pdi-content">
													工作计划：制定面向大学生的调研报告
												</div>
											</div>
										</li>
									</ul>
								</div>
								<div class="cb"></div>
								<div class="mt15">
									<div class="bd-bg fl"></div>
									<div class="bd-color fl ml15">
										最新评论
									</div>
									<div class="cb"></div>
								</div>
								
								<div class="mt15">
									<div class="pd-head-img fl pr z-index100">
										<a href="">
											<img class="img-border" width="38px" height="38px" src="static/front/css/images/index-img/test-img.png">
										</a>
										<div class="mcu-name"><a href="" title="">哈哈哈</a></div>
									</div>
									<div class="fr">
										<div class="news-comment">
											我的同学张三表示他对这项目挺感兴趣，要加入我们的团队，张三他热情开 朗，善于言谈，对电子商务运营有一套他自己的见解，能很好管理
										</div>
										<div class="">
										<div class="author-date fl">2012-12-12 12：00</div>
											<div class="favour-font fr">
												<a href="" class="mr20">赞同（320）</a>
												<a href="">踩（320）</a>
											</div>
											<div class="cb"></div>
										</div>
									</div>
									<div class="cb"></div>
								</div>
							</div>
							<div class="cb"></div>
						</div>
						<div class="bg-bottom"></div>
					</div>
					<%--一个end--%>
					<%--一个--%>
					<div class="mb20">
						<div class="home-content-list">
							<div class="fl">
								<div class="hc-head-img pr z-index100">
									<a href=""><img width="61px" height="61px" class="img-border" src="static/front/css/images/index-img/test-img.png"></a>
									<div class="person-msg-box none">
										<div class="pmb-jt"></div>
										<div>
											<ul class="pmb-msg fl">
												<li>丁香园</li>
												<li>|</li>
												<li>女</li>
												<li>|</li>
												<li>23岁</li>
												<li>|</li>
												<li>上海 杨浦区</li>
											</ul>
											<a href="" class="fr attention-btn"></a>
											<div class="cb"></div>
										</div>
										<div class="autograph-box ml5">
											<div class="fl" style="color:#676767;line-height:20px;">签名</div>
											<div class="fl ml10" style="color:#959494;width:330px;line-height:20px;">我打江南走过，那等在季节里的容颜如莲花的开落，东风不来，三月的柳絮不飞，你的心如小小的寂寞的城！</div>
											<div class="cb"></div>
										</div>
										<div class="autograph-box ml5">
											<div class="fl" style="color:#676767;line-height:20px;">擅长</div>
											<div class="fl ml10" style="color:#959494;width:330px;line-height:20px;">JAVA JSP PHP JAVASCRIPT PHOTOSHOP </div>
											<div class="cb"></div>
										</div>
									</div>
								</div>
								<div class="satisfied-num">2260</div>
								<div class="satisfied-font">意愿度</div>
								<%--三种状态<a href="" class="wait-btn"></a>
								<div class="satisfied-font">取消关注</div> --%>
								<%--<a href="" class="sq-btn"></a>
								<div class="satisfied-font">申请关注</div>--%>
								<a href="" class="wait-sh-btn"></a>
								<div class="satisfied-font">等待审核</div>
							</div>
							<div class="hc-content-box fr">
								<div>
									<div class="hc-title">
										<a href="" title="" class="hc-title-a fl">${dv:e("关于面向校园学生的生活服务平台关于面向校园学生的生活服务平台关于面向校园学生的生活服务平台关于面向校园学生的生活服务平台",80)}</a>
									</div>
									<div class="cb"></div>
								</div>
								<div class="hc-introduce mt10">
									${dv:e("一个小区生活移动平台，让家家户户通过手机购买生鲜百货和生活服务。一个小区生活移动平台，让家家户户通过手机购买生鲜百货和生活服务",150)}
								</div>
								
								<div class="mt10">
									<div class="">
										<div class="gd-bg fl"></div>
										<div class="fr gd-content">
											<div class="gd-color fl" style="width:140px;">
												已有：商业服务-场地
											</div>
											<div class="fl content-col" style="width:430px;line-height:22px;">
												我的同学张三表示他对这项目挺感兴趣，要加入我们的团队，张三他热情开 朗，善于言谈，对电子商务运营有一套他自己的见解，能很好管理
											</div>
										</div>
										<div class="cb"></div>
									</div>
									<div class="">
										<div class="gd-bg fl"></div>
										<div class="fr gd-content">
											<div class="gd-color fl" style="width:140px;">
												已有：商业服务-设计
											</div>
											<div class="fl content-col" style="width:430px;line-height:22px;">
												我的同学张三表示他对这项目挺感兴趣，要加入我们的团队，张三他热情开 朗，善于言谈，对电子商务运营有一套他自己的见解，能很好管理
											</div>
										</div>
										<div class="cb"></div>
									</div>
									<div class="">
										<div class="rd-bg fl"></div>
										<div class="fr gd-content">
											<div class="rd-color fl" style="width:140px;">
												缺少：项目启动资金
											</div>
											<div class="fl content-col" style="width:430px;line-height:22px;">
												我的同学张三表示他对这项目挺感兴趣，要加入我们的团队，张三他热情开 朗，善于言谈，对电子商务运营有一套他自己的见解，能很好管理
											</div>
										</div>
										<div class="cb"></div>
									</div>
								</div>
								<div class="hc-show-box mt15">
									<ul class="pd-scale">
										<li class="aps-li clb">
											<b class="fl">创始人</b>
											<div class="fr pd-state-detail">
												<div class="fl pdi-date-box ml5">2012-12-12 09:00</div>
												<div class="fl pdi-content">
													工作计划：制定面向大学生的调研报告
												</div>
												<div class="cb"></div>
											</div>
										</li>
										<li class="aps-li clb">
											<b class="fl">创始人</b>
											<div class="fr pd-state-detail">
												<div class="fl pdi-date-box ml5">2012-12-12 09:00</div>
												<div class="fl pdi-content">
													工作计划：制定面向大学生的调研报告
												</div>
												<div class="cb"></div>
											</div>
										</li>
										<li class="aps-li clb">
											<b class="fl">创始人</b>
											<div class="fr pd-state-detail">
												<div class="fl pdi-date-box ml5">2012-12-12 09:00</div>
												<div class="fl pdi-content">
													工作计划：制定面向大学生的调研报告
												</div>
												<div class="cb"></div>
											</div>
										</li>
									</ul>
								</div>
								<div class="cb"></div>
								<div class="mt15">
									<div class="bd-bg fl"></div>
									<div class="bd-color fl ml15">
										最新评论
									</div>
									<div class="cb"></div>
								</div>
								
								<div class="mt15">
									<div class="pd-head-img fl pr z-index100">
										<a href="">
											<img class="img-border" width="38px" height="38px" src="static/front/css/images/index-img/test-img.png">
										</a>
										<div class="mcu-name"><a href="" title="">哈哈哈</a></div>
									</div>
									<div class="fr">
										<div class="news-comment">
											我的同学张三表示他对这项目挺感兴趣，要加入我们的团队，张三他热情开 朗，善于言谈，对电子商务运营有一套他自己的见解，能很好管理
										</div>
										<div class="">
										<div class="author-date fl">2012-12-12 12：00</div>
											<div class="favour-font fr">
												<a href="" class="mr20">赞同（320）</a>
												<a href="">踩（320）</a>
											</div>
											<div class="cb"></div>
										</div>
									</div>
									<div class="cb"></div>
								</div>
							</div>
							<div class="cb"></div>
						</div>
						<div class="bg-bottom"></div>
					</div>
					<%--一个end--%>
				</div>
				<div class="home-index-right fr">
					<a href="member/project/publish.htm" class="submit-idea-btn"></a>
					
					<div class="article-box">
						<div class="article-title clb">
							<div class="fl at-font">创业文摘</div>
							<div class="fr at-more"><a href="">更多</a></div>
						</div>
						
						<ul class="at-ul mt5">
							<li class="">
								<a href="" title="">
									<img src="static/member/css/images/img-01.png" width="230" height="115">
									<div class="opacity-bg"></div>
									<div class="tag1">
	                                    <b class="bg"></b>
	                                    <em>ChanceRunner</em> 
	                                    <strong>查看详情</strong>
                               		 </div>
								</a>
								<a href="" title="">
									${dv:e("你的企业管理可以没有管理者吗？",30)}
								</a>
							</li>
							<li class="">
								<a href="" title="" >
									<img src="static/member/css/images/img-02.png" width="230" height="115">
									<div class="opacity-bg"></div>
									<div class="tag1">
	                                    <b class="bg"></b>
	                                    <em>ChanceRunner</em> 
	                                    <strong>查看详情</strong>
                               		 </div>
								</a>
								<a href="" title="">
									${dv:e("你的企业管理可以没有管理者吗？",30)}
								</a>
							</li>
							<li class="">
								<a href="" title="">
									<img src="static/member/css/images/img-03.png" width="230" height="115">
									<div class="opacity-bg"></div>
									<div class="tag1">
	                                    <b class="bg"></b>
	                                    <em>ChanceRunner</em> 
	                                    <strong>查看详情</strong>
                               		 </div>
								</a>
								<a href="" title="">
									${dv:e("你的企业管理可以没有管理者吗？",30)}
								</a>
							</li>
						</ul>
						
						
						
						<div class="article-title clb mt30">
							<div class="fl at-font">活跃会员</div>
						</div>
						<%--一个--%>
						<div class="at-div">
							<div class="fl pr z-index100 head-image-box">
								<a href=""><img width="57px" height="57px" class="img-border" src="static/front/css/images/index-img/test-img.png"></a>
							</div>
							<div class="fr at-content">
								<p>
									<a href="">Tmallkenny</a>
								</p>
								<p class="at-p">
									<a href="">
										${dv:e("我认为应该要分这几步进行下面的工作",30)}
									</a>
								</p>
							</div>
						</div>
						<%--一个end--%>
						<%--一个--%>
						<div class="at-div">
							<div class="fl pr z-index100 head-image-box">
								<a href=""><img width="57px" height="57px" class="img-border" src="static/front/css/images/index-img/test-img.png"></a>
							</div>
							<div class="fr at-content">
								<p>
									<a href="">Tmallkenny</a>
								</p>
								<p class="at-p">
									<a href="">
										${dv:e("我认为应该要分这几步进行下面的工作",30)}
									</a>
								</p>
							</div>
						</div>
						<%--一个end--%>
						<%--一个--%>
						<div class="at-div">
							<div class="fl pr z-index100 head-image-box">
								<a href=""><img width="57px" height="57px" class="img-border" src="static/front/css/images/index-img/test-img.png"></a>
							</div>
							<div class="fr at-content">
								<p>
									<a href="">Tmallkenny</a>
								</p>
								<p class="at-p">
									<a href="">
										${dv:e("我认为应该要分这几步进行下面的工作",30)}
									</a>
								</p>
							</div>
						</div>
						<%--一个end--%>
						<%--一个--%>
						<div class="at-div">
							<div class="fl pr z-index100 head-image-box">
								<a href=""><img width="57px" height="57px" class="img-border" src="static/front/css/images/index-img/test-img.png"></a>
							</div>
							<div class="fr at-content">
								<p>
									<a href="">Tmallkenny</a>
								</p>
								<p class="at-p">
									<a href="">
										${dv:e("我认为应该要分这几步进行下面的工作",30)}
									</a>
								</p>
							</div>
						</div>
						<%--一个end--%>
						<div class="cb"></div>
					</div>
				</div>
				<div class="cb"></div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/pages/member/comm/front_footer.jsp"></jsp:include>
		<script>
			$(function(){
				$(".hc-head-img").hover(
					function(){
						var divSec = $(this).children(".person-msg-box");
						$(divSec).removeClass("none");
					},
					function(){
						var divSec = $(this).children(".person-msg-box");
						$(divSec).addClass("none");
					}
				);
			});
		</script>
		<script type="text/javascript">
			$(document).ready(function($){
				//幻灯片
				var bigpics=$(".picshow .picwrap img");
				var smallpics=$(".picshow .buttons li img.menu");
				var ptitles=$(".picshow .pic_titles li p");
				for(i=0;i<smallpics.length;i++){
					smallpics.eq(i).attr("src",bigpics.eq(i).attr("src")).attr("alt",bigpics.eq(i).attr("alt"));
					ptitles.eq(i).html(bigpics.eq(i).attr("alt"));
				}
				
				$(".picshow .buttons li").click(function(){
					if($(".picshow .picwrap li").is(":animated")||$(this).hasClass("on")) return;
					var indexNow=$(".picshow .buttons li").index(this);
					var currMenu=$(".picshow .buttons li").filter(".on");
					var currPic=$(".picshow .picwrap li").filter(":visible");
					nowPic=$(".picshow .picwrap li").eq(indexNow);
					currPic.fadeOut(800);
					nowPic.fadeIn(800);
					currMenu.removeClass("on");
					$(this).addClass("on");
				}).hover(function(){
					var ptitles=$(".picshow .pic_titles li p");
					var pindex=$(".picshow .buttons li").index($(this));
					ptitles.eq(pindex).show("slow");
				},function(){
					var ptitles=$(".picshow .pic_titles li p");
					var pindex=$(".picshow .buttons li").index($(this));
					ptitles.eq(pindex).hide("slow");
				});
				
				//hover效果
				$(".at-ul li").hover(
					 function () {
						$(this).addClass("hover");
					},
					function(){
						$(this).removeClass("hover");
					}
				);
					
				//自动轮换
				maxPiclist=$( ".picshow .buttons ul" ).children().length;
				currPiclist=0;
				$( ".picshow .buttons ul,.picshow .picwrap" ).mouseout( function(){
					var index=$(".picshow .buttons ul").children().index( $(".picshow .buttons ul").children(".on") );
					currPiclist=index;
					intPiclist=setInterval(function(){
						currPiclist++;
						if( currPiclist==maxPiclist ) currPiclist=0; 
						$(".picshow .buttons ul").children().eq( currPiclist ).one( "click",function(e){ e.stopPropagation(); });
						$(".picshow .buttons ul").children().eq( currPiclist ).click(); 
					},3500);
				});
				$( ".picshow .buttons ul,.picshow .picwrap" ).mouseover( function(){ if(intPiclist) clearInterval( intPiclist );});
				$( ".picshow .picwrap" ).mouseout();
			});
		</script>
	</body>
</html>