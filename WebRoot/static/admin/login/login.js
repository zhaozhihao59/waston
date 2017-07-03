$(document).ready(function(){
	// 页面加载完后，即自适应浏览器的高度
	resize();
	// 当窗体改变后，改变背景蓝色的高度
	window.onresize = function _doResize(){
		resize();
	}
	
	// 刷新验证码
	$("#captchaImage").click( function() {
		var timestamp = (new Date()).valueOf();
		var imageSrc = $(this).attr("src");
		if(imageSrc.indexOf("?") >= 0) {
			imageSrc = imageSrc.substring(0, imageSrc.indexOf("?"));
		}
		imageSrc = imageSrc + "?timestamp=" + timestamp;
		$(this).attr("src", imageSrc);
	});
	
});

function resize(){
	// 初始化背景蓝色的高度
	$('.login-bg').height($(window).height());
}
function login(){
	var uname = $("#j_username").val();
	var pwd = $("#j_password").val();
	if(uname.length == 0){	
		$.dialog.alert("用户名不能为空");
		return;
	}
	if(pwd.length == 0){
		$.dialog.alert("密码不能为空");
		return;
	}
	document.forms[0].submit();
}