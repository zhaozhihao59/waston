<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<a class="dy-wa clb" href="javascript:void(0);"><span class="fl">W&B Legal Practice Review Subscription</span><i class="fl"></i></a>
<script type="text/javascript" src="static/base/lib/ajaxfileupload/ajaxfileupload.js"></script>
<!--<script src="static/base/lib/artdialog/jquery.artDialog.source.js?t=${sysVersion}&skin=default"></script>
<script src="static/base/lib/artdialog/plugins/iframeTools.js?t=${sysVersion}"></script>
-->
<form id="saveForm" method="post">
	<div class="box-jb hide">
		<span style="color:#444;">Hi~&nbsp;You are welcome to subscribe the W&B Newsletter. We will send you the latest news on a monthly basis.</span>
		<div class="mt10 clb"> 
			<span class="item-title-index w100">Your preferred subscription…</span>
			<select id="channelId" name="item.channelId" class="w240 mt5" >
				<option>Please select type of periodical…</option>
				<option value="bba13349-e891-11e4-ae9b-00266c0e7760">W&B Newsletter</option>
				<option value="c25f9c04-e891-11e4-ae9b-00266c0e7760">W&B Compliance</option>
			</select>
		</div>
		<div class="mt10 clb">
			<span class="item-title-index w100">Your email address：</span>
			<input id="email" name="item.email" type="text" class="act-text-index w240 display-block">
		</div>
		<div class="mt10 clb">
			<span class="item-title-index w100">Your name：</span>
			<input id="name" name="item.name" type="text" class="act-text-index w240 display-block">
		</div>
		<div class="mt10 clb">
			<span class="item-title-index w100">Your mobile phone number：</span>
			<input id="mobile" name="item.mobile" type="text" class="act-text-index w240 display-block">
		</div>
		<div class="mt10 clb">
			<span class="item-title-index w100">Your company：</span><input id="companyname" name="item.companyName" type="text" class="act-text-index w240 display-block">
		</div>
		<div class="mt10 clb">
			<span class="item-title-index w100">Your job title：</span><input id="position" name="item.position" type="text" class="act-text-index w240 display-block">
		</div>
		<div class="mt10 clb">
			<div class="item-title-index w100"></div>
			<span style="width:100px" class="btn-dy fl mr10" onclick="doSubmit();">Subscribe Now</span>
			<span class="btn-dy fl gb-btn-index">Cancel</span>
		</div>
	</div>
</form>
<script type="text/javascript" src="static/front_en/comm/news_dy.js"></script>
<script language="JavaScript">

	//订阅
	$(".dy-wa").click(function(){
		$(".box-jb").slideToggle();
		$(".dy-wa").toggleClass("cur");
		var i = $(this).children("i");
		$(i).toggleClass("cur-i");
	});	
	
	$(".gb-btn-index").click(function(){
		$(".box-jb").slideToggle();
		$(".dy-wa").toggleClass("cur");
		var i = $(".dy-wa").children("i");
		$(i).toggleClass("cur-i");
	});	
</script>