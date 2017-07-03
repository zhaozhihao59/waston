var basePath = null;
$(document).ready(function(){
	goTopEx();
	bgFixed();
});
/**
 * 返回顶部
 */
function goTopEx() {
	var obj = document.getElementById("goTopBtn");
	if (null != obj) {
		function getScrollTop() {
			return document.documentElement.scrollTop;
		}
		function setScrollTop(value) {
			document.documentElement.scrollTop = value;
		}
		window.onscroll = function() {
			getScrollTop() > 0
					? obj.style.display = ""
					: obj.style.display = "none";
		}
		obj.onclick = function() {
			var goTop = setInterval(scrollMove, 10);
			function scrollMove() {
				setScrollTop(getScrollTop() / 1.1);
				if (getScrollTop() < 1)
					clearInterval(goTop);
			}
		}
	}
}
function bgFixed(){
	
}
$(window).scroll(function(){
	var y = $(window).scrollTop();
	var h = $(".register-box").height();
	if(y <= 211){
		$(".inner-bg").css("top","211px");
	}else if(y > 211 && y < h+30){
		$(".inner-bg").css("top",y+"px");
	}
});

$(document).ready(function() {
	// 文本框提示文字
		$('.show-text').live('click',function(){
			$(this).prev('input[class*="index-input"]').focus();
			$(this).addClass('show-text-focus');
		});
		$("input[class*='index-input']").live('focusout', function() {
			$(this).next('.show-text').removeClass('show-text-focus');
		});
		$("input[class*='index-input']").live('keydown', function() {
			$(this).next('.show-text').hide();
		});
		$("input[class*='index-input']").live('keyup', function() {
			var v = $(this).val();
			if($.trim(v) == ""){
				$(this).next('.show-text').show();
			}
		});
		
		
		$('.show-text').live('click',function(){
			$(this).prev('input[class*="pp-input"]').focus();
			$(this).addClass('show-text-focus');
		});
		$("input[class*='pp-input']").live('focusout', function() {
			$(this).next('.show-text').removeClass('show-text-focus');
		});
		$("input[class*='pp-input']").live('keydown', function() {
			$(this).next('.show-text').hide();
		});
		$("input[class*='pp-input']").live('keyup', function() {
			var v = $(this).val();
			if($.trim(v) == ""){
				$(this).next('.show-text').show();
			}
		});
		
		
		// 文本域提示文字
		$('.show-text').live('click',function(){
			$(this).prev('textarea[class*="index-input"]').focus();
			$(this).addClass('show-text-focus');
		});
		$("textarea[class*='index-input']").live('focusout', function() {
			$(this).next('.show-text').removeClass('show-text-focus');
		});
		$("textarea[class*='index-input']").live('keydown', function() {
			$(this).next('.show-text').hide();
		});
		$("textarea[class*='index-input']").live('keyup', function() {
			var v = $(this).val();
			if($.trim(v) == ""){
				$(this).next('.show-text').show();
			}
		});
		
		
			// 文本域提示文字
		$('.show-text').live('click',function(){
			$(this).prev('textarea[class*="textarea-style"]').focus();
			$(this).addClass('show-text-focus');
		});
		$("textarea[class*='textarea-style']").live('focusout', function() {
			$(this).next('.show-text').removeClass('show-text-focus');
		});
		$("textarea[class*='textarea-style']").live('keydown', function() {
			$(this).next('.show-text').hide();
		});
		$("textarea[class*='textarea-style']").live('keyup', function() {
			var v = $(this).val();
			if($.trim(v) == ""){
				$(this).next('.show-text').show();
			}
		});
		
		
	});
$(document).ready(function(){
	$('.roll_top').click(function(){$('html,body').animate({scrollTop: '0px'}, 800);}); 
	$('.ct').click(function(){$('html,body').animate({scrollTop:$('.ct').offset().top}, 800);});
	$('.fall').click(function(){$('html,body').animate({scrollTop:$('.footers').offset().top}, 800);});
	
	initInputHover();
});

function initInputHover(){
	$(".ui-input-text").live("focus",function(){
		$(this).addClass("ui-input-text-hover");
	});
	$(".ui-input-text").live("blur",function(){
		$(this).removeClass("ui-input-text-hover");
	});
	$(".select-div").find("select").live("focus",function(){
		$(this).parent().addClass("ui-input-text-hover");
	});
	$(".select-div").find("select").live("blur",function(){
		$(this).parent().removeClass("ui-input-text-hover");
	});
}