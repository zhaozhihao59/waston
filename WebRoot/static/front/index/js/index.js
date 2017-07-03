$(document).ready(function() {
	
});
		
$(window).scroll(function(){
	var y = $(window).scrollTop();
	if(y > 420){
		$("#header").show();
		$("#header").addClass("fix-top");
	}else{
		$("#header").hide();
		$("#header").removeClass("fix-top");
	}
});
function addCookie(objName,objValue,objHours){
	var str = objName + "=" + escape(objValue);
	if(objHours > 0){
	var date = new Date();
	var ms = objHours*3600*1000;
	date.setTime(date.getTime() + ms);
	str += "; expires=" + date.toGMTString();
	}
	document.cookie = str;
}
	
function getCookie(objName){
	var arrStr = document.cookie.split("; ");
	for(var i = 0;i < arrStr.length;i ++){
		var temp = arrStr[i].split("=");
		if(temp[0] == objName) return unescape(temp[1]);
	}
}
	
function delCookie(name){
	date.setTime(date.getTime() - 10000);
	document.cookie = name + "=a; expires=" + date.toGMTString();
}