// 文本框提示文字
$('.show-text').live('click',function(){
	$(this).prev('input[class*="index-input"]').focus();
	$(this).addClass('show-text-focus');
});
var basePath = null;
$(document).ready(function(){
	goTopEx();
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

$(document).ready(function(){
	$('.roll_top').click(function(){$('html,body').animate({scrollTop: '0px'}, 800);}); 
	$('.ct').click(function(){$('html,body').animate({scrollTop:$('.ct').offset().top}, 800);});
	$('.fall').click(function(){$('html,body').animate({scrollTop:$('.footers').offset().top}, 800);});
});