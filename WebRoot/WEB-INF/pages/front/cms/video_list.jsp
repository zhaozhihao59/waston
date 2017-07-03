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
<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
<link rel="stylesheet" type="text/css" href="static/front/css/wa-index.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"><jsp:param name="menu" value="5" /></jsp:include>
	<div class="act-index-news">
	<div class="fl zxzx-ico"></div>
	<div class="fr zxzx-cbox" >
		<%--跑马灯start --%>
		<div class="marqueeDv" style="width: 1050px;">
			<ul>
				<c:forEach var="lab" items="${session.ListArticleByComplex1}">
					<li>
						<a href="cmsDetail.htm?item.id=${lab.id}" class="marquee">${lab.name}</a>
					</li>
				</c:forEach>
			</ul>
		</div> 
		<div class="cb"></div> 
		<%--跑马灯end --%>
	</div>
	<div class="cb"></div>
</div>
<script language="JavaScript">
	$(".marqueeDv").kxbdMarquee({
		  isEqual:false,         	//所有滚动的元素长宽是否相等,true,false 
		  loop:0,                 //循环滚动次数，0时无限         
		  direction:"left",       //滚动方向，"left","right","up","down"         
		  scrollAmount:1,     //步长         
		  scrollDelay:20        //时长
	});
</script>
	<div class="act-index-middlebox">
		<div class="fl act-left-box">
			<div class="zz-img"></div>
			<div class="act-tt-box">讲座活动</div>
			<ul class="act-left-ul">
				<li >
					<a href="cms_ListPage.htm?item.id=3d5ffe8b-e890-11e4-ae9b-00266c0e7760" " class="xw-ico">
						华诚动态
					</a>
				</li>
				<li>
					<a href="cms_ListPage.htm?item.id=439c9076-e890-11e4-ae9b-00266c0e7760" class="cbw-ico">
						业界动态
					</a>
				</li>
				<li>
					<a href="pub_ListPage.htm?item.id=5da5b951-e890-11e4-ae9b-00266c0e7760" class="hcjb-ico">
						出版物
					</a>
				</li>
				<li class="cur">
					<a href="cms/video_list.htm" class="yew02-ico">
						精彩视频
					</a>
				</li>
			</ul>
			<div class="mt10">
				<a class="pw-wa" href="cms/to_common_problem.htm">
					常见问题
					<i></i>
				</a>
				<!-- 
				<a class="dy-wa" href="#">
					华诚通讯订阅 -->
					<i></i>
				</a>
			</div>
		</div>
		<div class="fr actright-box">
			<div class="sctr-right-box">
				<div class="srb-nav-box fl">精彩视频</div>
				<div class="fr srb-ncbox mr15">
					<a href="#">首页</a>
					<label>></label>
					<span>精彩视频</span>
				</div>
				<div class="cb"></div>
			</div>
			<div class="article-right-ep clb">
				
				  <ul class="ar-ul-ep clb fl">
				   <c:forEach items="${videoArticleList}" var="val">
					<li style="width:285px;">
						<div class="boxgrid caption videoDiv">
							<a href="#">
									<img src="${val.path}" width="290px" height="180px">
							</a>
							<div class="cover boxcaption" >
								<h3>${val.name}</h3>
								<p style="color:#f1f1f1;font-size:12px;">${val.summary}</p>
								<p class="mt10"><a class="bofan-btn mt5" href="cmsDetail.htm?item.id=${val.id}" target="_BLANK"></a></p>
							</div>
						</div>
					</li>
					 </c:forEach>
				 </ul>
				
			</div>
		</div>
		<div class="cb"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
</body>
</html>
