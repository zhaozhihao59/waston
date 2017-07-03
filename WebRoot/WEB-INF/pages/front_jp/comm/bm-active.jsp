<%@ page language="java" contentType="text/html; charset=utf-8"%>
<script src="static/front/js/comm/pic-move.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="static/base/lib/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="static/front_jp/comm/bm-active.js"></script>

<%--在线留言 --%>
<div class="msg-box-content none" id="massageDiv" >
	<s:form id="regeisterForm" method="post" >
		<div class="modal-header">
			<button id="box-close-btn" type="button" data-dismiss="modal" aria-hidden="true" class="close" style="position:static;">
				×
			</button>
			<h3 id="titleH3">
				講演の申込
			</h3>
		</div>
		<div class="mb-content">
			<div style="color:#999">連絡先</div>
			<div class="scl-ljbm-box">
			<input type="hidden" id="activityId" name="item.activityId"/>
		<div class="form-item">
			<span class="item-title w80"><em>*</em>Eメールアドレス：</span>
			<input id="email"type="text" class="act-text w200 fl" name ="item.email"/>
			<span id="emailTip"></span>
		</div>
		<div class="form-item">
			<span class="item-title w80"><em>*</em>氏名：</span>
			<input id="name"type="text" class="act-text w200 fl" name="item.name" />
			<span id="nameTip"></span>
		</div>
		<div class="form-item">
			<span class="item-title w80"><em>*</em>携帯電話番号：</span>
			<input id="mobile" type="text" class="act-text w200 fl" name="item.mobile"/>
			<span id="mobileTip"></span>
		</div>
		<div class="form-item">
			<span class="item-title w80">職位：</span>
			<input id="positionName"type="text" class="act-text w200 fl" name = "item.positionName"/>
			<span id="positionNameTip"></span>
		</div>
		<div class="form-item">
			<span class="item-title w80"><em>*</em>会社：</span>
			<input id="companyName"type="text" class="act-text w200 fl" name = "item.companyName"/>
			<span id="companyNameTip"></span>
		</div>
		<div class="form-item">
			<span class="item-title w80">電話番号：</span>
			<input id ="positionNamepositionName" type="text" class="act-text w200 fl" name="item.tel"/>
		</div>
		
		<div class="form-item">
			<span class="item-title w80"></span>
			<a href="javaScript:void(0);" class="ljbm-btn fl "  onClick ="regeisterNow();">保存と確認</a>
			<a href="javaScript:void(0);" class="ljbm-btn fl ml10" onClick ="regeisterTongxingNow();return false;">確認して同行者を追加する</a>
		</div>
	</div>
		</div>
		</s:form>
	</div>
	<div class="msg-box none" ></div>
<%--在线留言END --%>
<script>
	$(document).ready(function() {
		var wh = $(window).height();
		$('.yjfk').click(function(){
		var data = $(this).attr("data");
			$("#activityId").val(data);
			$('.msg-box-content').show();
			$('.msg-box-content').addClass("check").animate({top:'20%',opacity:'1'}, 100);
			$('.msg-box').show().css("height",wh);
			$("body").addClass("modal-active");
		});
		$('#box-close-btn').click(function(){
			$('.msg-box-content').hide();
			$('.msg-box').hide();
			$("body").removeClass("modal-active");
			$('.msg-box-content').removeClass("check").animate({top:'25%',opacity:'0'}, 500);
		});
		$('.msg-box').click(function(){
			$('.msg-box-content').hide();
			$('.msg-box').hide();
			$("body").removeClass("modal-active");
			$('.msg-box-content').removeClass("check").animate({top:'25%',opacity:'0'}, 500);
		});
		$('#searchBth').click(function(){
			var keyword=$('#searchVal').val();
			window.location.href = 'news/search.htm?condition.keyword='+keyword;
		});
		//为所有类样式包含ui_input_text的input元素增加获得焦点样式
		$(".ui_input_text").focus(function(){
			$(this).addClass("ui_input_text_hover");
		});
		$(".ui_input_text").blur(function(){
			$(this).removeClass("ui_input_text_hover");
		});
		$(".ui_textarea").focus(function(){
			$(this).addClass("ui_input_text_hover");
		});
		$(".ui_textarea").blur(function(){
			$(this).removeClass("ui_input_text_hover");
		});
	});	
</script>

<style>
.msg-box-content {
	-webkit-transition:-webkit-all 0.3s ease-in 0s;
	-moz-transition:-moz-all 0.3s ease-in 0s;
	-o-transition:-moz-all 0.3s ease-in 0s;
	transition:all 0.3s ease-in 0s;
    background: none repeat scroll 0 0 #FFFFFF;
    border-radius: 4px;
    box-shadow: 3px 3px 40px 0 #333333;
    height: 440px;
    left: -450px;
    position: fixed;
    top: 25%;
    width: 900px;
    z-index: 9999;
    margin-left:50%;
    opacity:0;
	filter:alpha(opacity=0);
	-moz-opacity: 0;
	-khtml-opacity: 0;
}
.form-item input{height:31px;}
.msg-box-content1 {
    background: none repeat scroll 0 0 #333333;
    height: 100%;
    left: 0;
    position: fixed;
    top: 0;
    width: 100%;
    z-index: 2222;
}
.website-img {
    height: 100%;
    text-align: center;
    width: 100%;
}
.modal-header {
    border-bottom: 1px solid #EEEEEE;
    padding: 9px 15px;
}
.modal-header .close {
    margin-top: 2px;
}
button.close {
    background: none repeat scroll 0 0;
    border: 0 none;
    cursor: pointer;
    padding: 0;
}
.close {
    color: #000000;
    float: right;
    font-size: 20px;
    font-weight: bold;
    line-height: 20px;
    opacity: 0.2;
    text-shadow: 0 1px 0 #FFFFFF;
}
.modal-header h3 {
    font-size: 24px;
    line-height: 30px;
    margin: 0;
}
.mb-content {
    padding: 15px;
}
.ui_textarea {
    border: 1px solid #CCCCCC;
    padding: 5px 3px;
    resize: none;
}
.vt {
    vertical-align: top;
}
.ui_input_text {
    border: 1px solid #CCCCCC;
    height: 17px;
    line-height: 17px;
    padding: 3px;
}
.modal-footer {
    background-color: #F5F5F5;
    border-radius: 0 0 6px 6px;
    border-top: 1px solid #DDDDDD;
    box-shadow: 0 1px 0 #FFFFFF inset;
    margin-bottom: 0;
    padding: 14px 15px 15px;
    text-align: right;
}
.modal-footer:before, .modal-footer:after {
    content: "";
    display: table;
    line-height: 0;
}
.btn {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    background-color: #F5F5F5;
    background-image: linear-gradient(to bottom, #FFFFFF, #E6E6E6);
    background-repeat: repeat-x;
    border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) #B3B3B3;
    border-image: none;
    border-radius: 4px;
    border-style: solid;
    border-width: 1px;
    box-shadow: 0 1px 0 rgba(255, 255, 255, 0.2) inset, 0 1px 2px rgba(0, 0, 0, 0.05);
    color: #333333;
    cursor: pointer;
    display: inline-block;
    font-size: 14px;
    line-height: 20px;
    margin-bottom: 0;
    padding: 4px 12px;
    text-align: center;
    text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
    vertical-align: middle;
}
.modal-footer .btn + .btn {
    margin-bottom: 0;
    margin-left: 5px;
}
.btn-primary {
    background-color: #006DCC;
    background-image: linear-gradient(to bottom, #0088CC, #0044CC);
    background-repeat: repeat-x;
    border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
    color: #FFFFFF;
    text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
}
.modal-footer a {
    text-decoration: none;
}
.btn:hover, .btn:focus, .btn:active, .btn.active, .btn.disabled, .btn[disabled] {
    background-color: #E6E6E6;
    color: #333333;
}
.btn:hover, .btn:focus {
    background-position: 0 -15px;
    color: #333333;
    text-decoration: none;
    transition: background-position 0.1s linear 0s;
}
.btn-primary:hover, .btn-primary:focus, .btn-primary:active, .btn-primary.active, .btn-primary.disabled, .btn-primary[disabled] {
    background-color: #0044CC;
    color: #FFFFFF;
}
.ui_input_text_hover {
    background-color: #F9F7D6;
    border-color: #FEB54C;
}
.msg-box {
    background: url("static/front/css/images/box-vo.png") repeat scroll;
     left: 0;
    opacity: 0.5;
    position: fixed;
    top: 0;
    width: 100%;
    z-index: 9909;
}
</style>