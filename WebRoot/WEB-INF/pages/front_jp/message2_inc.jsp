<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%--在线留言 --%>

<div class="msg-box12 none" style="background:#fff;">
	<div class="msg-box-content12 none" id="massageDiv" >
		<div class="moviePanel">
			<video id="video1" class="video" style="width:848px;height:480px;padding:0;" type="video/mp4" src="static/front/comm/images/ydml.mp4" x-webkit-airplay="allow" controls="controls" poster="" bgcolor="white"></video>
		</div>
		<div class="ie78-box none">
			<img src="static/front/comm/images/jr9.jpg" style="margin-top:150px;">
			<div class="ml20 mt10">ブラウザのバージョンアップが必要。</div>
		</div>
		
		<a class="close"></a>
	</div>
</div>
<%--在线留言END --%>


<style>
/*在线留言*/
.ie78-box{
	font-size:18px;
	margin:0 auto;
	width:500px;
}
.close{
	background:url("static/front/comm/images/close.png") no-repeat;
	width:30px;
	height:30px;
	position:absolute;
	top:-40px;
	left:-180px;
	z-index:1000;
	cursor:pointer;
	
}
.msg-box-content12 {
     background:#FFFFFF;
    border-radius: 4px;
    height: 480px;
    margin: auto;
    position: relative;
    top:80px;
    width: 848px;
    z-index: 2100;
}

.msg-box12{
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
				
		var myVideo=document.getElementById("video1");
		
		
		
		$(".spbf").click(function(){
			$(".msg-box-content12").show();
			$(".msg-box12").show();
			$(".ts-b").css("overflow-y","hidden")
			  myVideo.play();
		});
		$(".close").click(function(){
			$(".msg-box-content12").hide();
			$(".msg-box12").hide();
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
				$(".moviePanel").hide();
				$(".ie78-box").show();
			} 
			</script>