<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<a class="dy-wa clb" href="javascript:void(0);"><span class="fl">ニュースレターの定期購読</span><i class="fl"></i></a>
<script type="text/javascript" src="static/base/lib/ajaxfileupload/ajaxfileupload.js"></script>
<!--<script src="static/base/lib/artdialog/jquery.artDialog.source.js?t=${sysVersion}&skin=default"></script>
<script src="static/base/lib/artdialog/plugins/iframeTools.js?t=${sysVersion}"></script>
-->
<form id="saveForm" method="post">
	<div class="box-jb hide">
		<span style="color:#444;">Hi~&nbsp;ニュースレターの定期購読を大歓迎。　月毎に最新のニュースを送付</span>
		<div class="mt10 clb"> 
			<span class="item-title-index w100">購読の類型：</span>
			<select id="channelId" name="item.channelId" class="w240 mt5" >
				<option>購読の類型をご記入ください。</option>
				<option value="bba13349-e891-11e4-ae9b-00266c0e7760">ニュースレター</option>
				<option value="c25f9c04-e891-11e4-ae9b-00266c0e7760">コンプライアンス</option>
			</select>
		</div>
		<div class="mt10 clb">
			<span class="item-title-index w100">電子メール：</span>
			<input id="email" name="item.email" type="text" class="act-text-index w240 display-block">
		</div>
		<div class="mt10 clb">
			<span class="item-title-index w100">氏名：</span>
			<input id="name" name="item.name" type="text" class="act-text-index w240 display-block">
		</div>
		<div class="mt10 clb">
			<span class="item-title-index w100">携帯番号：</span>
			<input id="mobile" name="item.mobile" type="text" class="act-text-index w240 display-block">
		</div>
		<div class="mt10 clb">
			<span class="item-title-index w100">会社名：</span><input id="companyname" name="item.companyName" type="text" class="act-text-index w240 display-block">
		</div>
		<div class="mt10 clb">
			<span class="item-title-index w100">職務：</span><input id="position" name="item.position" type="text" class="act-text-index w240 display-block">
		</div>
		<div class="mt10 clb">
			<div class="item-title-index w100"></div>
			<span style="width:110px" class="btn-dy fl mr10" onclick="doSubmit();">購読</span>
			<span class="btn-dy fl gb-btn-index">閉じる</span>
		</div>
	</div>
</form>
<script type="text/javascript" src="static/front_jp/comm/news_dy.js"></script>
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