<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--[if lt IE 9]>
    <script src="static/front/js/comm/css3-mediaqueries.js"></script>
    <script src="static/front/js/comm/html5.js"></script>
<![endif]-->
<%--head start--%>
<div class="bg-head-box clb">˙
	<div class="w1170-mg-auto clb">
		<div class="header-logo">
			<a href="index.htm" ><img src="static/front/css/images/logo.png" width="450" height="67" alt="WATSON & BAND, 华诚律师事务所" /> </a>
		</div>
		<div class="header-profile fr">
			<ul class="head-rt-ul clb">
			<jsp:useBean id="now" class="java.util.Date" />
				<li class="date-wa">&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- 动态时间 -->
					
					 <div id="showDate" style="margin-left: 50px;"></div>
					<%--
						SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH);
						String date =sdf.format(new Date(System.currentTimeMillis()));
						out.print(date);
					 --%>
				</li>
				<li class="clb">
					<a href="jp/index.htm" class="watson-index-ico jp-ico">日本語</a>
					<a href="en/index.htm" class="watson-index-ico eng-ico">English</a>
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
					<input id="searchName" type="text" style="width:300px;" class="form-control" placeholder="搜索 &hellip;" onkeypress="if(event.keyCode == 13){quickSearch();}"  value="${searchName }">
				</div>
				<a class="wa-btn watson-index-ico w40 fl ml5" id="search"  href="javascript:void(0);"></a>
			</div>
			
		</div>
	</div>
</div>
<%--head end--%>
<%-- <script>
		$(function(){
			var ww=$(window).width();
			if (ww < 1170) {
				$('.bg-head-box').css('width', 1170);
			} else {
			}
		});
</script> --%>

<script>
 	setInterval("updateDate();",1000);
     /**动态时钟【开始】*/
     function updateDate()
     {
  	 $("#showDate").html("时间: "+(new Date()).toLocaleString());
  	}
 
   /**动态时钟【结束】*/
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
		
		//二维码显示
		$("#pageQrcode").hover(
		  function () {
		    $(".qrcode").removeClass("hide");
		  },
		  function () {
		    $(".qrcode").addClass("hide");
		  }
		); 
		//回到顶部 回到底部
		$(document).ready(function(){
		var wh = $("html").height();
			$('#goTop').click(function(){$('html,body').animate({scrollTop: '0px'}, 1000);});
			$('#goBottom').click(function(){$('html,body').animate({scrollTop:$('.footers').offset().top}, 1000);});
		});
		
			// 搜索
	$("#search").click(function (){
		var searchName = $("#searchName").val();
		window.location.href = base+"search.htm?searchName="+searchName;
	});
	//回车搜索
	function quickSearch(){
	var searchName = $("#searchName").val();
		window.location.href = base+"search.htm?searchName="+searchName;
	}
	</script>
	
	
<script type="text/javascript">
$(document).ready(function () {
	var menu = '${param.menu}';
	if(menu && menu.length > 0){
		$('.fl-li-menu').removeClass("cur");
		$('.fl-li-menu[menu='+menu+']').addClass("cur");
	}
});
</script>
