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
<div class="bg-head-box clb">
	<div class="w1170-mg-auto clb">
		<div class="header-logo">
			<a href="en/index.htm" ><img src="static/front/css/images/logo.png" width="450" height="67" alt="WATSON & BAND, 华诚律师事务所" /> </a>
		</div>
		<div class="header-profile fr">
			<ul class="head-rt-ul clb">
			<jsp:useBean id="now" class="java.util.Date" />
				<li class="date-wa">&nbsp;&nbsp;&nbsp;&nbsp;
					<div  id="showDate" style="margin-left:50px;"></div>
				</li>
				<li class="clb">
					<a href="jp/index.htm" class="watson-index-ico jp-ico">Japanese</a>
					<a href="en/index.htm" class="watson-index-ico eng-ico">English</a>
					<a href="index.htm" class="watson-index-ico cha-ico">Chinese</a>	
				</li>
			</ul>
		</div>
	</div> 	
	<div class="wa-bar">
		<div class="w1170-mg-auto clb pr" style="z-index:9000;">
			<ul class="clb fl" style="width:942px;">
						<li class="fl-li-menu cur"><a href="en/index.htm" class="wa-father-menu"> HOME</a></li>
						<li class="fl-li-menu jt-ico">
							<a href="work_en/about_en.htm" class="wa-father-menu watson-index-ico about-pa"> ABOUT US<i></i></a>
							<ul class="wa-dropdown-menu">
								<li><a href="work_en/about_en.htm" > ABOUT US</a></li>
								<li><a href="work_en/property_agency_en.htm" > Watson & Band Intellectual Property Agent Ltd</a></li>
								<li><a href="work_en/about_en.htm" > 艾科玛知识产权管理有限公司</a></li>
							</ul>	
						</li>
						<li class="fl-li-menu jt-ico">
							<a href="cms_en/business_field_en.htm" class="wa-father-menu watson-index-ico pd-pa">PRACTICE AREAS</a>
							<ul class="wa-dropdown-menu" >
								<li><a href="cms_en/business_field_en.htm?i=0" >Corporate & Commercial Services</a></li>
								<li><a href="cms_en/business_field_en.htm?i=1" >Intellectual Property</a></li>
								<li><a href="cms_en/business_field_en.htm?i=2" >Litigation & Arbitration</a></li>
								<li><a href="cms_en/business_field_en.htm?i=3" >Wealth Management Practice</a></li>
							</ul>	
						</li>
						<li class="fl-li-menu jt-ico">
							<a href="front/professional_en/index.htm" class="wa-father-menu watson-index-ico pe-pa">PROFESSIONALS</a>
							<ul class="wa-dropdown-menu" >
								<li><a href="cms_en/toJobOpprtunity_en.htm" >JOIN US</a></li>
							</ul>
						</li>
						<li class="fl-li-menu jt-ico2">
							<a href="en/cms_ListPage_en.htm?item.id=3d5ffe8b-e890-11e4-ae9b-00266c0e7760" class="wa-father-menu watson-index-ico ca-pa">NEWS & PUBLICATIONS</a>
							<ul class="wa-dropdown-menu" style="width:214px">
								<li><a href="en/cms_ListPage_en.htm?item.id=3d5ffe8b-e890-11e4-ae9b-00266c0e7760" >Latest on Watson & Band</a></li>
								<li><a href="en/cms_ListPage_en.htm?item.id=439c9076-e890-11e4-ae9b-00266c0e7760" >Latest Legal News</a></li>
								<li><a href="en/pub_ListPage_en.htm?item.id=5da5b951-e890-11e4-ae9b-00266c0e7760" >Publications</a></li>
								<li><a href="cms_en/video_list_en.htm" >Videos</a></li>
							</ul>
						</li><!--
						<li class="fl-li-menu"><a href="#" class="wa-father-menu watson-index-ico cs-pa">案例分析</a></li>
						--><li class="fl-li-menu"><a href="front/activity_en/activities_ListPage_en.htm" class="wa-father-menu watson-index-ico at-pa">SEMINARS & EVENTS</a></li>
						<li class="fl-li-menu"><a href="work_en/contact_us_en.htm" class="wa-father-menu watson-index-ico cs-pa">CONTACT US</a></li>
					</ul>
					
			
			<div class="wa-search-bar fr">
				<div class="form-group fl">
					<input id="searchName" type="text" style="width:155px;" class="form-control" placeholder="Search&hellip;" onkeypress="if(event.keyCode == 13){quickSearch();}"  value="${searchName}">
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
	/**动态时钟【开始】*/
     function updateDate()
     {
   	 var d = new Date();
 $("#showDate").html("Time:"+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds()+" "+d.getDate()+"-"+(d.getMonth()+1)+"-"+ d.getFullYear());
  	 }
  	setInterval("updateDate();",1000);
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
		window.location.href = "${BASE_PATH}en/search_en.htm?searchName="+searchName;
	});
	//回车搜索
	function quickSearch(){
	var searchName = $("#searchName").val();
		window.location.href = "${BASE_PATH}en/search_en.htm?searchName="+searchName;
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
