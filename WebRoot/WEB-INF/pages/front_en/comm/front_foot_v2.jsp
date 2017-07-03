<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<div class="foot-box w1170-mg-auto" style="margin:20px auto;">
	<div style="padding:20px 20px 10px;">
		<p>
The Watson & Band website is intended for informational purposes only. Nothing in this site is to be construed as creating an attorney-client relationship between the reader and Watson & Band or as offering legal advice on any specific matter. Since we are not providing legal advice through this website, you should not act upon any information that you might receive here without first seeking professional counsel. No client or other reader should act or refrain from acting on the basis of any information contained in the Watson & Band website without seeking appropriate legal or other professional advice based on the particular facts and circumstances at issue.
		</p>
		<div class="link-frd clb">
			<div class="fl link-fd f14">Links</div>
			<div class="fl w1000 ml20">
				<table>
					<tr>
						<!-- <td><a class="a-link mr20 f10 mt5" href="http://www.sutherland.com">Sutherland-Watson Joint Website</a></td> -->
						<td><a class="a-link mr20 f10 mt5" href="http://www.gov.cn/">The Central People’s Government of the People’s Republic of China</a></td>
						<td><a class="a-link mr20 f10 mt5" href="http://www.moj.gov.cn/">Ministry of Justice P.R.C</a></td>
						
					</tr>
					<tr>
						<td><a class="a-link mr20 f10 mt5" href="http://sbj.saic.gov.cn/">Trademark Office of The State Administration For Industry & Commerce of the People’s Republic of China</a></td>
						<td><a class="a-link mr20 f10 mt5" href="http://www.sipo.gov.cn/">STATE INTELLECTUAL PROPERTY OFFICE OF THE P.R.C</a></td>
						<td><a class="a-link mr20 f10 mt5" href="http://www.mofcom.gov.cn/">MINISTRY OF COMMERCE PEOPLE’S REPUBLIC OF CHINA</a></td>
						
					</tr>
					<tr>
						<td><a class="a-link mr20 f10 mt5" href="http://www.most.gov.cn/">Ministry of Science and Technology of the People’s Republic of China</a></td>
						<td><a class="a-link mr20 f10 mt5" href="http://www.chinatax.gov.cn/index.html">State Administration of Taxation</a></td>
					</tr>
					<tr>
						<td><a class="a-link mr20 f10 mt5" href="http://www.ndrc.gov.cn/">National Development and Reform Commission</a></td>
						<td><a class="a-link mr20 f10 mt5" href="http://www.gapp.gov.cn/">State Administration of Press，Publication，Radio，Film and Television of The People’s Republic of China</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>	
	<div class="foot-bottom clb">
		<span class="fl">© Copyright 2000-2015 All Rights Reserved | Shanghai ICP for 15028801<a href="privacy_protect.htm" class="a-link">Privacy Policy</a> | <a href="user_feedback.htm"  class="a-link">User Feedback</a></span>
	</div>
</div>
<div style="width:300px;margin:0 auto; padding:20px 0;">
	<a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=31010402001317" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="static/front/css/images/ghs.png" style="float:left;"/><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">沪公网安备 31010402001317号</p></a>
</div>

<div class="wa-go-tb " >
		<div id="goTop" title="去顶部"  class="btn-bottom-box" style="border-radius:4px 4px 0px 0px;"></div>
		<a id="refresh" title="刷新"  href="javascript:;" class="btn-bottom-box"></a>
		<div  class="btn-bottom-box" id="pageQrcode" title="微信二维码">
			<img class="qrcode hide" width="120" height="120" src="static/front/css/images/wx-ewm-img.jpg">
		</div>
		<div  class="btn-bottom-box" id="Linked" title="Linked in" style="background:#fff;">
			<span class="fb f12" style="margin-left:-3px;">Lin</span>
			<img class="qrcode2 hide" width="120" height="120" src="static/front/css/images/LinkedIn-WB.png">
		</div>
		<div  class="btn-bottom-box" id="goBottom"title="去底部"  style="border-radius:0px 0px 4px 4px;"></div>
	</div>
	<div class="footers"></div>
	
<script>
		//二维码显示
		$("#pageQrcode").hover(
		  function () {
		    $(".qrcode").removeClass("hide");
		  },
		  function () {
		    $(".qrcode").addClass("hide");
		  }
		); 
		$("#Linked").hover(
		  function () {
		    $(".qrcode2").removeClass("hide");
		  },
		  function () {
		    $(".qrcode2").addClass("hide");
		  }
		); 
		$("#refresh").click(function(){
			window.location.reload();
		});
		//回到顶部 回到底部
		$(document).ready(function(){
		var wh = $("html").height();
			$('#goTop').click(function(){$('html,body').animate({scrollTop: '0px'}, 1000);});
			$('#goBottom').click(function(){$('html,body').animate({scrollTop:$('.footers').offset().top}, 1000);});
		});
</script>	
	