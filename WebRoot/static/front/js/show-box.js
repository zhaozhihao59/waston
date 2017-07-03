$(document).ready(function() {
	$("input[class*='ui_input_text']").focus(function(){
		$(this).addClass("login-input-hover");
	});
$("input[class*='ui_input_text']").blur(function(){
		$(this).removeClass("login-input-hover");
});



	var h = $(window).height();
	$(".yjfk").click(function(){
			$(".msg-box-content").show();
			$(".msg-box").show();
			$('.msg-box').css('height', h + 'px');
	});
	$("#box-close-btn").click(function(){
		$(".msg-box-content").hide();
		$(".msg-box").hide();
	});
	$("#btn-close-btn").click(function(){
		$(".msg-box-content").hide();
		$(".msg-box").hide();
	});
});
		
	$(window).scroll(function() {
		var y = $(window).scrollTop();
			$('.msg-box').css('margin-top', y + 'px');
	});

			
