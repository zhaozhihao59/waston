<%@ page language="java" contentType="text/html; charset=utf-8"%>
<div class="hd-bg clb">
	<div class="hd-box">
		<span class="fr more-meau-list"></span>
		<span class="fr ml20 mr20 fgx" style="line-height:35px;">|</span>
		<span class="hd-fdj-ico fr"></span>
		<input type="text" placeholder="Search" class="search-text ac_input " name="key" id="heardAuto" autocomplete="off">
		<span class="hd-x-ico fr "></span>
	</div>	
</div>
<div class="top-second-bg">
	<div class="nav-layout">
		<div class="chance-logo fl">
			<a href="member/index.htm"><img src="static/front/css/images/chance-logo.png" width="196px" height="55px"></a>
		</div>
		<ul class="chance-nav-ul fl ml30">
			<li><a href="member/index.htm"><img src="static/member/css/images/index-ico.png" class="mr10">首页</a></li>
			<li><a href="project/index.htm">项目</a></li>
			<li><a href="">资源</a></li>
			<li><a href="">圈子</a></li>
			<li><a href="member/account/index.htm">本人</a></li>
			<li><a href="">文摘</a></li>
		</ul>
	</div>
</div>
<div class="meau-list">
	<div class="hd-mxcz"></div>
	<div>
		<a class="meau-bg" href="#"><span class="hy-ico">会员中心</span></a>
		<a class="meau-bg" href="#"><span class="pd-ico">我的项目</span></a>
		<a class="meau-bg" href="#"><span class="gz-ico">关注的项目</span></a>
		<a class="meau-bg clb" href="#"><span class="xi-ico fl">消息提醒</span><i class="fl"></i><span class="num-box fl">22</span></a>
	</div>
	<a class="out-login-box transition" href="#">退出登录</a>
</div>


<script>
	$(function(){
		// 文本框提示文字
		$('.search-show-text').live('click',function(){
			$(this).prev('input[class*="head-search-input"]').focus();
			$(this).addClass('show-text-focus');
		});
		$("input[class*='head-search-input']").live('focusin', function() {
			$(this).next('.search-show-text').addClass('show-text-focus');
			$(this).css("background-position","0px -34px");
		});
		$("input[class*='head-search-input']").live('focusout', function() {
			$(this).next('.search-show-text').removeClass('show-text-focus');
			$(this).css("background-position","0px 0px");
		});
		$("input[class*='head-search-input']").live('keydown', function() {
			$(this).css("background-position","0px -34px");
			$(this).next('.search-show-text').hide();
		});
		$("input[class*='head-search-input']").live('keyup', function() {
			var v = $(this).val();
			if($.trim(v) == ""){
				$(this).next('.search-show-text').show();
				$(this).css("background-position","0px 0px");
			}
		});
		
		// 头部放大镜动作
		$('.hd-fdj-ico').live('click',function(){
			$('.hd-fdj-ico, .fgx, .more-meau-list').animate({ marginTop:'-200%'}, 100,function(){
			$('.search-text').animate({ left:'0px'}, 300);
			$('.hd-x-ico').animate({ right:'0px'}, 300);
			});
		});
		$('.hd-x-ico').live('click',function(){
			$('.search-text').animate({ left:'-200%'}, 300);
			$('.hd-x-ico').animate({ right:'-200%'}, 300,function(){
			$('.hd-fdj-ico, .fgx, .more-meau-list').animate({ marginTop:'0%'}, 300);
			});
		});
		
		// 头部meau
		var check = 0;
		$('.more-meau-list').live('click',function(){
			if(check == 0){
				$('body').animate({ marginLeft:'-300px'}, 300);
				$('body').css('box-shadow','1px 3px 8px #000000');
			check = 1;
			}else{
				$('body').animate({ marginLeft:'0px'}, 300);
				$('body').css('box-shadow','none');
			check = 0;
			}
		});
	});
</script>