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
		<link rel="stylesheet" type="text/css" href="static/front_jp/comm/jp-wa-index.css" />
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
<body  class="ts-b">
	<div class="cur transition">
		<%--head start--%>
		<div class="bg-head-box clb">
			<div class="w1170-mg-auto clb">
				<div class="header-logo">
					<a href="jp/index.htm" ><img src="static/front/css/images/logo.png" width="450" height="67" alt="WATSON & BAND, 华诚律师事务所" /> </a>
				</div>
				<div class="header-profile fr">
					<ul class="head-rt-ul clb">
					<jsp:useBean id="now" class="java.util.Date" />
						<li class="date-wa">&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="showDate"> </span>
						<li class="clb">
							<a href="jp/index.htm" class="watson-index-ico jp-ico">日本語</a>
							<a href="en/index.htm" class="watson-index-ico eng-ico">英語</a>
							<a href="index.htm" class="watson-index-ico cha-ico">中国語</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="wa-bar">
				<div class="w1170-mg-auto clb pr" style="z-index:9000;">
					<ul class="clb fl" style="width:910px;">
						<li class="fl-li-menu cur"><a href="jp/index.htm" class="wa-father-menu">ホーム</a></li>
						<li class="fl-li-menu jt-ico">
							<a href="work_jp/about_jp.htm" class="wa-father-menu watson-index-ico about-pa">華誠について<i></i></a>
							<ul class="wa-dropdown-menu">
								<li><a href="work_jp/about_jp.htm" >華誠について</a></li>
								<li><a href="work_jp/property_agency_jp.htm">華誠知識産権代理有限公司</a></li>
								<li><a href="javascript:void(0)" >艾科玛知识产权管理有限公司</a></li>
							</ul>
						</li>
						<li class="fl-li-menu jt-ico">
							<a href="cms_jp/business_field_jp.htm" class="wa-father-menu watson-index-ico pd-pa">業務分野</a>
							<ul class="wa-dropdown-menu" >
								<li><a href="cms_jp/business_field_jp.htm?i=0" >企業商事</a></li>
								<li><a href="cms_jp/business_field_jp.htm?i=1" >知的財産権</a></li>
								<li><a href="cms_jp/business_field_jp.htm?i=2" >訴訟・仲裁</a></li>
								<li><a href="cms_jp/business_field_jp.htm?i=3" >ウェルス・マネジメント</a></li>
							</ul>	
						</li>
						<li class="fl-li-menu jt-ico">
							<a href="front/professional_jp/index.htm" class="wa-father-menu watson-index-ico pe-pa">メンバー紹介</a>
							<ul class="wa-dropdown-menu" >
								<li><a href="cms_jp/toJobOpprtunity_jp.htm" >採用情報</a></li>
							</ul>
						</li>
						<li class="fl-li-menu jt-ico2">
							<a href="jp/cms_ListPage_jp.htm?item.id=3d5ffe8b-e890-11e4-ae9b-00266c0e7760" class="wa-father-menu watson-index-ico ca-pa">ニュースと出版物</a>
							<ul class="wa-dropdown-menu" style="width:147px">
								<li><a href="jp/cms_ListPage_jp.htm?item.id=3d5ffe8b-e890-11e4-ae9b-00266c0e7760" >華誠の動向</a></li>
								<li><a href="jp/cms_ListPage_jp.htm?item.id=439c9076-e890-11e4-ae9b-00266c0e7760" >業界の動向</a></li>
								<li><a href="jp/pub_ListPage_jp.htm?item.id=5da5b951-e890-11e4-ae9b-00266c0e7760" >出版物</a></li>
								<li><a href="cms_jp/video_list_jp.htm" >映像</a></li>
							</ul>
						</li><li class="fl-li-menu"><a href="front/activity_jp/activities_ListPage_jp.htm" class="wa-father-menu watson-index-ico at-pa">イベント</a></li>
						</li><li class="fl-li-menu"><a href="work_jp/contact_us_jp.htm" class="wa-father-menu watson-index-ico at-pa">お問い合わせ</a></li>
					</ul>
					
					<div class="wa-search-bar fr">
						<div class="form-group fl">
							<input id='searchName' type="text" style="width:185px;" class="form-control" placeholder="検索 &hellip;" >
						</div>
						<a id="search" class="wa-btn watson-index-ico w40 fl ml5" href="javascript:void(0);"></a>
					</div>
					
					<div class="wa-fk-box">
						<ul class="wa-fk-ul clb">
							<li class="bg01-index" >
								<a href="cms_jp/business_field_jp.htm?i=0">
									<div class="wa-tt1-box">企業商事業務</div>
									<div class="wa-ccbox">会社商務部を多くの世界有数の多国籍企業専門の法律サービスを提供する…</div>
								</a>
							</li>
							<li class="bg02-index">
								<a href="cms_jp/business_field_jp.htm?i=1">
									<div class="wa-tt2-box">知的財産権業務</div>
									<div class="wa-ccbox">私たちが商標・外観、電子通信、光学物理、機械工学、化学…</div>
								</a>
							</li>
							<li class="bg03-index" >
								<a href="cms_jp/business_field_jp.htm?i=2">
									<div class="wa-tt3-box">訴訟・仲裁業務</div>
									<div class="wa-ccbox">華誠の弁護士が処理できる多くの分野においての国内と国際仲裁事件を含む知識産.....</div>
								</a>
							</li>
							<li class="bg04-index">
								<a href="cms_jp/business_field_jp.htm?i=3">
									<div class="wa-tt4-box">ウェルス・マネジメント業務</div>
									<div class="wa-ccbox">華誠本の個人の財産を持つ専門管理チームに集中する家族企業、名.....</div>
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
							<div class="block-title-font fl"><a target="_blank" class="block-title-a" href="jp/cms_ListPage_jp.htm?item.id=3d5ffe8b-e890-11e4-ae9b-00266c0e7760">華誠の動向</a></div>
							<%--跑马灯start --%>
							<div class="marqueeDv" class="fl" style="width:620px;">
								 <c:if test="${empty listCmsArticle}">
									 内容がない 
								 </c:if>
								 <ul class="clb">
								 	 <c:forEach var="articletop" items="${listCmsArticle}">
							        	 <li style="margin-top:-5px;">
							        	 	<a target="_blank" href="jp/cmsDetail_jp.htm?item.id=${articletop.id}" class="marquee">${articletop.jpName}</a>
							        	 </li>
							         </c:forEach>
							      </ul>
							</div> 
							<%--跑马灯end --%>
						</div>
						<div class="news">
						<c:if test="${empty ListArticleByComplex1}">
								<div class="no-sj">内容がない</div>
						</c:if>
							<c:forEach var="hc"  items="${ListArticleByComplex1}" varStatus="index">
							<div class="news-item">
								<div class="news-title">
									<a target="_blank" class="news-title-a" href="jp/cmsDetail_jp.htm?item.id=${hc.id}">${hc.jpName}</a>
								</div>
								<div class="news-brief clb">
									
										<c:if test="${empty hc.path}">
									<div class="news-content-image ">
										<div class="news-data">
											<span class="news-date"><fmt:formatDate value="${hc.createDate}" pattern="yyyy-MM-dd"/></span>
											<span class="news-publisher">発表者：${hc.authorJp}</span>
											<span class="news-count">閲覧回数：${hc.readNum}次</span>
										</div>
										<div class="news-remark ">
											<a target="_blank" class="news-remark-a" href="jp/cmsDetail_jp.htm?item.id=${hc.id}">
												${hc.jpSummary}
											</a>
										</div>
									</div>
									</c:if>
									<c:if test="${!empty hc.path}">
									<div class="news-content-image " >
										<div class="news-data">
											<span class="news-date"><fmt:formatDate value="${hc.createDate}" pattern="yyyy-MM-dd"/></span>
											<span class="news-publisher">発表者：${hc.authorJp}</span>
											<span class="news-count">閲覧回数：${hc.readNum}次</span>
										</div>
										<div class="news-remark fl">
											<a target="_blank" class="news-remark-a" href="jp/cmsDetail_jp.htm?item.id=${hc.id}">
												${hc.jpSummary}
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
								<div class="anli-box clb"><span class="fl">判例分析</span><a target="_blank" class="fr a-link f12 mt3 mr10" href="${BASE_PATH}jp/pub_ListPage_jp.htm" style="font-weight:normal;">更に詳しく</a></div>
								<c:if test="${empty ListArticleByComplex2}">
								<div class="no-sj">内容がない</div>
								</c:if>
 									<c:forEach var="al" items="${ListArticleByComplex2}">
 											<a target="_blank" class="anli-box-wz" href="jp/cmsDetail_jp.htm?item.id=${al.id}">${dv:e(al.jpName,46)}</a>
 									 </c:forEach>
							</div>
							<div class="left-box fr" style="height:270px;">
								<div class="anli-box clb" style="background-position:73px -1114px"><span class="fl">出版物</span><a target="_blank" class="fr a-link f12 mt3 mr10" href="${BASE_PATH}jp/pub_ListPage_jp.htm?item.id=5da5b951-e890-11e4-ae9b-00266c0e7760" style="font-weight:normal;">更に詳しく</a></div>
								<c:if test="${empty ListArticleByComplex3}">
								<div class="no-sj">内容がない</div>
								</c:if>
								<c:forEach var="cbw" items="${ListArticleByComplex3}">
									  <a  target="_blank" class="updaDown anli-box-wz" href="${cbw.jpAnnexPath }">${dv:e(cbw.jpAnnexFilename,46)}</a>
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
							<div class="block-title-font fl"><a class="block-title-a" href="cms_jp/video_list_jp.htm">映像</a></div>
						</div>
						<div class="video clb">
						 <c:forEach items="${videoArticleList}" var="val">
							<div class="video-item clb">
								<a class="video-image" href="javascript:void(0);">
									<img src="${val.path}" width="90px" height="50px" onclick="videoimghref('${val.id}');javascript:void(0);" />
								</a>
								<div class="fl ml10 w220">
									<a class="video-title" href="javascript:void(0);" title="" onclick="videoimghref('${val.id}');javascript:void(0);">${val.jpName}</a>
									<p class="video-remark" title="">${dv:e(val.jpSummary,70)}</p>
								</div>	
							</div>
							 </c:forEach>
						</div>
					</div>
					<div class="main-right mt20 clb">
						<div class="block-title clb">
							<div class="block-title-font fl"><a class="block-title-a" href="front/activity_jp/activities_ListPage_jp.htm">講演の申込</a></div>
							<a class="fr a-link f12 mt3" href="front/activity_jp/activities_ListPage_jp.htm">更に詳しく</a>
						</div>
						<c:if test="${empty listActivity}">
								<div class="no-sj">内容がない</div>
						</c:if>
						<c:forEach items="${listActivity}" var="activity">
						<div class="activity">
							<div class="act-title">
								<a class="act-title-a" href="front/activity_jp/activities_detail_jp.htm?selIds=${activity.id }">${activity.titleJp}</a>
							</div>
							<div class="act-brief clb">
								<div class="act-image fl">
									<a class="" href="front/activity_jp/activities_detail_jp.htm?selIds=${activity.id }"><img src="${activity.photo}" height="50px" width="80px"/></a>
								</div>
								<div class="act-content fl">
									<p>
										<span class="act-brief-title">開催日：</span><span class="act-brief-intro">${activity.dateStrJp}</span>
									</p>
									<p>
										<span class="act-brief-title">開催場所：</span><span class="act-brief-intro">${activity.addressJp}</span>
									</p>
									<a class="ljbm-btn" href="front/activity_jp/activities_detail_jp.htm?selIds=${activity.id }" data="${activity.id}">詳細情報</a>
								</div>
							</div>
						</div>
						</c:forEach>
					</div>
					<div class="mt20">
						<a href="cms_jp/to_common_problem_jp.htm" class="pw-wa clb"><span class="fl">よくある問題</span><i class="fl"></i></a>
						<jsp:include page="/WEB-INF/pages/front_jp/comm/news_dy.jsp"></jsp:include>
					</div>
				</div>
			<%--右侧 end--%>	
			</div>
		</div>
		<jsp:include page="/WEB-INF/pages/front_jp/comm/front_foot_v2.jsp"></jsp:include>
	</div>	
	<script>
	/**动态时钟【开始】*/
     function updateDate()
     {
   	 var d = new Date();
 $("#showDate").html("時間:"+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds()+" "+d.getDate()+"-"+(d.getMonth()+1)+"-"+ d.getFullYear());
  	 }
  	setInterval("updateDate();",1000);
		//视频点击进去
	function videoimghref(id){
		window.location.href = "${BASE_PATH}jp/cmsDetail_jp.htm?item.id="+id;
	}
	// 搜索
	$("#search").click(function (){
		var searchName = $("#searchName").val();
 		window.location.href = "${BASE_PATH}jp/search_jp.htm?searchName="+ encodeURI(searchName)+"&channelId=3d5ffe8b-e890-11e4-ae9b-00266c0e7760";
 	});
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
