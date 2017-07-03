<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%--在线留言 --%>

<div class="msg-box2 none" style="background:#fff;">
	<div class="msg-box-content2 none" id="massageDiv" >
	
		<div class="moviePane2">
			<video id="video2" class="video" style="width:848px;height:480px;padding:0;" type="video/mp4" src="static/front/comm/images/hc.mp4" x-webkit-airplay="allow" controls="controls" poster="" bgcolor="white"></video>
		</div>
		<div class="ie78-box2 none">
			<img src="static/front/comm/images/jr9.jpg" style="margin-top:150px;">
			<div class="ml20 mt10">浏览器版本过低，请升级高版本浏览器！</div>
		</div>
		
		<a class="close2"></a>
	</div>
</div>
<%--在线留言END --%>


<style>
/*在线留言*/
.ie78-box2{
	font-size:18px;
	margin:0 auto;
	width:500px;
}
.close2{
	background:url("static/front/comm/images/close.png") no-repeat;
	width:30px;
	height:30px;
	position:absolute;
	top:-40px;
	left:-180px;
	z-index:1000;
	cursor:pointer;
	
}
.msg-box-content2 {
     background:#FFFFFF;
    border-radius: 4px;
    height: 480px;
    margin: auto;
    position: relative;
    top:80px;
    width: 848px;
    z-index: 2100;
}

.msg-box2{
	height: 100%;
    left: 0;
    position: fixed;
    top: 0;
    width: 100%;
    z-index:9999;
    background:#fff;
}
</style>

<script>
	$(function(){
		var h = $(window).height();
				
		var myVideo=document.getElementById("video2");
		
		
		
		$(".spbf2").click(function(){
			$(".msg-box-content2").show();
			$(".msg-box2").show();
			$(".ts-b").css("overflow-y","hidden")
			  myVideo.play();
		});
		$(".close2").click(function(){
			$(".msg-box-content2").hide();
			$(".msg-box2").hide();
			$(".ts-b").css("overflow-y","auto")
			myVideo.pause();
		});
	});
	
</script>
<script>
var browser=navigator.appName 
var b_version=navigator.appVersion 
var version=b_version.split(";"); 
var trim_Version=version[1].replace(/[ ]/g,""); 
if(browser=="Microsoft Internet Explorer" && (trim_Version=="MSIE7.0" || trim_Version=="MSIE8.0") ) 
			{ 
				$(".moviePane2").hide();
				$(".ie78-box2").show();
			} 
			</script>