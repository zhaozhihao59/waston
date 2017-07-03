<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
		<meta name="baidu-site-verification" content="k66pxbcp2Z" />
		<meta property="qc:admins" content="422176645761667301356375636" />
		<meta property="wb:webmaster" content="9fe48f4d2c9e0fa7" />
		<title>∷WATSON & BAND∷</title>
		<meta name="Description" content="WATSON & BAND, 华诚律师事务所" />
		<meta name="Keywords" content="WATSON & BAND, 华诚律师事务所" />
		<meta name="author" content="华诚律师事务所" />
		<meta name="copyright" content="WATSON & BAND" />
		<meta name="company" content="WATSON & BAND, 华诚律师事务所" />
		<link rel="stylesheet" type="text/css" href="static/front/css/wa-index.css" />
		<link rel="shortcut icon" type="image/x-icon">
		<link href="static/base/base.css" rel="stylesheet" />
		<link href="static/front/comm/common.css" rel="stylesheet" />
		<link href="static/base/lib/loadmask/jquery.loadmask.css" rel="stylesheet" />
		<script src="static/front/js/img-move/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="static/front/comm/common_front.js"></script>
		<script src="static/base/lib/artdialog/jquery.artDialog.source.js?t=${sysVersion}&skin=idialog"></script>
		<script src="static/base/lib/artdialog/plugins/iframeTools.source.js?t=${sysVersion}"></script>
		<script src="static/base/lib/jquery.form.3.5.js"></script>
		<script src="static/base/lib/loadmask/jquery.loadmask.min.js"></script>
		<script src="static/base/lib/loadmask/jquery.loadmask.js"></script>
		<script src="static/base/lib/formvalidator/formValidator-4.1.1.js"></script>
		<script src="static/base/lib/formvalidator/formValidatorRegex.js"></script>
		<meta id="viewport" name="viewport" content="width=device-width, initial-scale=0.8, maximum-scale=1.0" />
		<script type="text/javascript" src="static/base/lib/autocomplete/jquery.autocomplete.js"></script>
		<link rel="Stylesheet" href="static/base/lib/autocomplete/jquery.autocomplete.css" />
		<script type="text/javascript" src="static/front/js/img-move/jquery.banner.revolution.min.js"></script>
		<script type="text/javascript" src="static/front/js/img-move/banner.js"></script>
		<script type="text/javascript" src="static/base/lib/kxbdMarquee/jquery.kxbdMarquee.js"></script>
		<!--[if lt IE 9]>
		    <script src="static/front/js/comm/css3-mediaqueries.js"></script>
		    <script src="static/front/js/comm/html5.js"></script>
		<![endif]-->
		<script>
		var _hmt = _hmt || [];
		(function() {
		  var hm = document.createElement("script");
		  hm.src = "https://hm.baidu.com/hm.js?05de43de0e2e5c3ad73287bd38651429";
		  var s = document.getElementsByTagName("script")[0]; 
		  s.parentNode.insertBefore(hm, s);
		})();
		</script>
				
		<style>
			.news-content-image{
				width:100%;
			}
		</style>
	</head>
<body class="ts-b">
	<div class="cur transition">
		<%--head start--%>
		<div class="bg-head-box clb">
			<div class="w1170-mg-auto clb">
				<div class="header-logo">
					<a href="#" ><img src="static/front/css/images/logo.png" width="450" height="67" alt="WATSON & BAND, 华诚律师事务所" /> </a>
				</div>
				<div class="header-profile fr">
					<ul class="head-rt-ul clb">
					<jsp:useBean id="now" class="java.util.Date" />
						<li class="date-wa"><div id="showDate" style="margin-left: 20px;"></div></li>
						<li class="clb">
							<a href="jp/index.htm" class="watson-index-ico jp-ico">日本語</a>
							<a href="en/index.htm" class="watson-index-ico eng-ico">英语</a>
							<a href="index.htm" class="watson-index-ico cha-ico">中文</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="wa-bar">
				<div class="w1170-mg-auto clb pr" style="z-index:9000;">
					<ul class="clb fl" style="width:770px;">
						<li class="fl-li-menu cur"><a href="#" class="wa-father-menu">首页</a></li>
						<li class="fl-li-menu jt-ico">
							<a href="work/about.htm" class="wa-father-menu watson-index-ico about-pa">关于华诚<i></i></a>
							<ul class="wa-dropdown-menu">
								<li><a href="work/about.htm" >华诚律师事务所</a></li>
								<li><a href="work/property_agency.htm" >华诚知识产权代理有限公司</a></li>
								<li><a href="javascript:void(0)" >艾科玛知识产权管理有限公司</a></li>
							</ul>	
						</li>
						<li class="fl-li-menu jt-ico">
							<a href="cms/business_field.htm" class="wa-father-menu watson-index-ico pd-pa">业务领域</a>
							<ul class="wa-dropdown-menu" >
								<li><a href="cms/business_field.htm?i=0" >公司商务业务</a></li>
								<li><a href="cms/business_field.htm?i=1" >知识产权</a></li>
								<li><a href="cms/business_field.htm?i=2" >诉讼仲裁业务</a></li>
								<li><a href="cms/business_field.htm?i=3" >财富管理</a></li>
							</ul>	
						</li>
						<li class="fl-li-menu jt-ico">
							<a href="front/professional/index.htm" class="wa-father-menu watson-index-ico pe-pa">专业人员</a>
							<ul class="wa-dropdown-menu" >
								<li><a href="cms/toJobOpprtunity.htm" >工作机会</a></li>
							</ul>
						</li>
						<li class="fl-li-menu jt-ico2">
							<a href="cms_ListPage.htm?item.id=3d5ffe8b-e890-11e4-ae9b-00266c0e7760" class="wa-father-menu watson-index-ico ca-pa">新闻与出版物</a>
							<ul class="wa-dropdown-menu" style="width:147px">
								<li><a href="cms_ListPage.htm?item.id=3d5ffe8b-e890-11e4-ae9b-00266c0e7760" >华诚动态</a></li>
								<li><a href="cms_ListPage.htm?item.id=439c9076-e890-11e4-ae9b-00266c0e7760" >业界动态</a></li>
								<li><a href="pub_ListPage.htm?item.id=5da5b951-e890-11e4-ae9b-00266c0e7760" >出版物</a></li>
								<li><a href="cms/video_list.htm" >精彩视频</a></li>
							</ul>
						</li><!--
						<li class="fl-li-menu"><a href="#" class="wa-father-menu watson-index-ico cs-pa">案例分析</a></li>
						-->
						<li class="fl-li-menu"><a href="front/activity/activities_ListPage.htm" class="wa-father-menu watson-index-ico at-pa">讲座活动</a></li>
						<li class="fl-li-menu"><a href="work/contact_us.htm" class="wa-father-menu watson-index-ico cs-pa">联系华诚</a></li>
					</ul>
					
					<div class="wa-search-bar fr">
						<div class="form-group fl">
							<input id='searchName' type="text" style="width:300px;" class="form-control" onkeypress="if(event.keyCode == 13){quickSearch();}" placeholder="搜索 &hellip;" >
						</div>
						<a id="search" class="wa-btn watson-index-ico w40 fl ml5" href="javascript:void(0);"></a>
					</div>
					
					<div class="wa-fk-box">
						<ul class="wa-fk-ul clb">
							<li class="bg01-index" >
								<a href="cms/business_field.htm?i=0">
									<div class="wa-tt1-box">公司商务业务</div>
									<div class="wa-ccbox">公司商务部为众多世界领先的跨国公司提供专业的法律服务...</div>
								</a>
							</li>
							<li class="bg02-index">
								<a href="cms/business_field.htm?i=1">
									<div class="wa-tt2-box">知识产权</div>
									<div class="wa-ccbox">我们擅长商标·外观、电子通信、光学物理、机械工程、化学....</div>
								</a>
							</li>
							<li class="bg03-index" >
								<a href="cms/business_field.htm?i=2">
									<div class="wa-tt3-box">诉讼仲裁业务</div>
									<div class="wa-ccbox">华诚的律师能够处理多个领域的国内和国际仲裁案包括知识产.....</div>
								</a>
							</li>
							<li class="bg04-index">
								<a href="cms/business_field.htm?i=3">
									<div class="wa-tt4-box">财富管理</div>
									<div class="wa-ccbox">华诚拥有一支专业的私人财富管理团队，专注于家族企业、名.....</div>
								</a>
							</li>
						</ul>
					</div>		
				</div>
			</div>
		</div>
		<%--head end--%>
		<%--img-move start--%>
		<div id="wrapper">
			<div class="fullwidthbanner-container">
				<div class="fullwidthbanner">
					<ul>
						<c:forEach var="cap" items="${cmsAdvPhotos1}">
						<li data-transition="3dcurtain-horizontal" data-slotamount="15" data-masterspeed="300">
							<img src="${cap.path}" alt="" />									
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<%--img-move end--%>
		<%-- <script>
			$(function(){
				var ww=$(window).width();
				if (ww < 1170) {
					$('.bg-head-box').css('width', 1170);
					$('#wrapper').css('width', 1170);
				} else {
				}
			});
		</script> --%>
		<div class="main-content">
			<div class="main-center clb pr" style="z-index:100;">
			<%--左侧 --%>
				<div class="fl">
					<%--华诚新闻--%>
					<div class="main-left">
						<div class="block-title clb">
							<div class="block-title-font fl"><a target="_blank" class="block-title-a" href="cms_ListPage.htm?item.id=3d5ffe8b-e890-11e4-ae9b-00266c0e7760">华诚动态</a></div>
							<%--跑马灯start --%>
							<div class="marqueeDv" class="fl" style="width:626px;">
								 <c:if test="${empty listCmsArticle}">
									暂无内容 
								 </c:if>
								 <ul class="clb">
								 	 <c:forEach var="articletop" items="${listCmsArticle}">
							        	 <li style="margin-top:-5px;">
							        	 	<a target="_blank" href="cmsDetail.htm?item.id=${articletop.id}" class="marquee">${articletop.name}</a>
							        	 </li>
							         </c:forEach>
							      </ul>
							</div> 
							<%--跑马灯end --%>
						</div>
						<div class="news">
						<c:if test="${empty ListArticleByComplex1}">
							<div class="no-sj">暂无内容</div>
						</c:if>
							<c:forEach var="hc"  items="${ListArticleByComplex1}" varStatus="index">
							<div class="news-item">
								<div class="news-title">
									<a target="_blank" class="news-title-a" href="cmsDetail.htm?item.id=${hc.id}">${hc.name}</a>
								</div>
								<div class="news-brief clb">
									
										<c:if test="${empty hc.path}">
									<div class="news-content-image">
										<div class="news-data">
											<span class="news-date"><fmt:formatDate value="${hc.createDate}" pattern="yyyy年MM月dd日"/></span>
											<span class="news-publisher">发布人：${hc.author}</span>
											<span class="news-count">阅读量：${hc.readNum}次</span>
										</div>
										<div class="news-remark">
											<a target="_blank" class="news-remark-a" href="cmsDetail.htm?item.id=${hc.id}">
												${hc.summary}
											</a>
										</div>
									</div>
									</c:if>
									<c:if test="${!empty hc.path}">
									<div class="news-content-image">
										<div class="news-data">
											<span class="news-date"><fmt:formatDate value="${hc.createDate}" pattern="yyyy年MM月dd日"/></span>
											<span class="news-publisher">发布人：${hc.author}</span>
											<span class="news-count">阅读量：${hc.readNum}次</span>
										</div>
										<div class="news-remark fl">
											<a target="_blank" class="news-remark-a" href="cmsDetail.htm?item.id=${hc.id}">
												${hc.summary}
											</a>
										</div>
									</div>
									</c:if>
								</div>
								<div class="news-line"></div>
							</div>
							</c:forEach>
						</div>
						</div>
					<%--华诚新闻 end--%>
					<%--案例分析 --%>		
						<div class="al-left mt20 clb ">
							<div class="left-box  fl" style="height:270px;">
								<div class="anli-box clb"><span class="fl">案例分析</span><a target="_blank" class="fr a-link f12 mt3 mr10" href="${BASE_PATH}pub_ListPage.htm" style="font-weight:normal;">更多</a></div>
								<c:if test="${empty ListArticleByComplex2}">
								<div class="no-sj">暂无内容</div>
								</c:if>
 									<c:forEach var="al" items="${ListArticleByComplex2}">
 											<a target="_blank" class="anli-box-wz" href="cmsDetail.htm?item.id=${al.id}">${dv:e(al.name,46)}</a>
 									 </c:forEach>
							</div>
							<div class="left-box fr" style="height:270px;">
								<div class="anli-box clb" style="background-position:73px -1114px"><span class="fl">出版物</span><a target="_blank" class="fr a-link f12 mt3 mr10" href="${BASE_PATH}pub_ListPage.htm?item.id=5da5b951-e890-11e4-ae9b-00266c0e7760" style="font-weight:normal;">更多</a></div>
								<c:if test="${empty ListArticleByComplex3}">
								<div class="no-sj">暂无内容</div>
								</c:if>
								<c:forEach var="cbw" items="${ListArticleByComplex3}">
									   <a  target="_blank" class="updaDown anli-box-wz" href="${cbw.annexPath }">${dv:e(cbw.annexFilename,46)}</a> 
								</c:forEach>
							</div>
						</div>
					<%--案例分析 end--%>
			</div>	
			<%--左侧 end--%>	
			<%--右侧 --%>	
				<div class="fr right-main">
					<div class="main-right" style="padding-bottom:0px;">
						<div class="block-title clb">
							<div class="block-title-font fl"><a class="block-title-a" href="cms/video_list.htm">精彩视频</a></div>
						</div>
						<div class="video clb">
								<div class="video clb">
								 <c:forEach items="${videoArticleList}" var="val">
									<div class="video-item clb">
										<a class="video-image" href="javascript:void(0);">
											<img src="${val.path}" width="90px" height="50px" onclick="videoimghref('${val.id}');javascript:void(0);" />
										</a>
										<div class="fl ml10 w220">
											<a class="video-title" href="javascript:void(0);" title="" onclick="videoimghref('${val.id}');javascript:void(0);">${val.name}</a>
											<p class="video-remark" title="">${dv:e(val.summary,70)}</p>
										</div>	
								</div>
								 </c:forEach>
							</div>
						</div>
					</div>
					<div class="main-right mt20 clb">
						<div class="block-title clb">
							<div class="block-title-font fl"><a class="block-title-a" href="front/activity/activities_ListPage.htm">活动报名</a></div>
							<a class="fr a-link f12 mt3" href="front/activity/activities_ListPage.htm">更多</a>
						</div>
						<c:if test="${empty listActivity}">
								<div class="no-sj">暂无内容</div>
						</c:if>
						<c:forEach items="${listActivity}" var="activity">
						<div class="activity">
							<div class="act-title">
								<a class="act-title-a" href="front/activity/activities_detail.htm?selIds=${activity.id }">${activity.title}</a>
							</div>
							<div class="act-brief clb">
								<div class="act-image fl">
									<a class="" href="front/activity/activities_detail.htm?selIds=${activity.id }"><img src="${activity.photo}" height="50px" width="80px"/></a>
								</div>
								<div class="act-content fl">
									<p>
										<span class="act-brief-title">活动日期：</span><span class="act-brief-intro">${activity.dateStr}</span>
									</p>
									<p>
										<span class="act-brief-title">活动地点：</span><span class="act-brief-intro">${activity.address}</span>
									</p>
									<a class="ljbm-btn" href="front/activity/activities_detail.htm?selIds=${activity.id }" data="${activity.id}">了解详情</a>
								</div>
							</div>
						</div>
						</c:forEach>
					</div>
					<div class="mt20" >
						<a href="cms/to_common_problem.htm" class="pw-wa clb"><span class="fl">常见问题</span><i class="fl"></i></a>
						<jsp:include page="/WEB-INF/pages/front/comm/news_dy.jsp"></jsp:include>
					</div>
				</div>
			<%--右侧 end--%>	
			</div>
		</div>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
	</div>	
	<script>
	function readerPDF(path){
		window.open("static/base/lib/pdfjs-1.1.215-dist/web/viewer.html?file=${BASE_PATH}"+path);
	}
		//视频点击进去
	function videoimghref(id){
		window.location.href = "${BASE_PATH}cmsDetail.htm?item.id="+id;
	}
	     /**动态时钟【开始】*/
     function updateDate()
     {
    $("#showDate").html("时间: "+(new Date()).toLocaleString());
   }
  setInterval("updateDate();",1000);
   /**动态时钟【结束】*/
	// 搜索
	$("#search").click(function (){
		var searchName = $("#searchName").val();
 		window.location.href = "${BASE_PATH}search.htm?searchName="+ encodeURI(searchName)+"&channelId=3d5ffe8b-e890-11e4-ae9b-00266c0e7760";
 	});
 	function quickSearch(){
 	var searchName = $("#searchName").val();
 		window.location.href = "${BASE_PATH}search.htm?searchName="+ encodeURI(searchName)+"&channelId=3d5ffe8b-e890-11e4-ae9b-00266c0e7760";
 	}
		//导航菜单下拉
		$(".fl-li-menu").hover(
		  function () { 
		  	var dm =$(this).children(".wa-dropdown-menu");
		  	var wm =$(this).children(".wa-father-menu");
		    $(dm).show();
		    $(wm).addClass("cur");
		  },
		  function () {
		  	 var dm =$(this).children(".wa-dropdown-menu");
		  	var wm =$(this).children(".wa-father-menu");
		    $(dm).hide();
		    $(wm).removeClass("cur");
		  }
		); 
	</script>
	<script language="JavaScript">
		$(".marqueeDv").kxbdMarquee({
		  isEqual:false,         	//所有滚动的元素长宽是否相等,true,false 
		  loop:0,                 //循环滚动次数，0时无限         
		  direction:"left",       //滚动方向，"left","right","up","down"         
		  scrollAmount:1,     //步长         
		  scrollDelay:20        //时长
		});
	</script>
</body>
</html>
