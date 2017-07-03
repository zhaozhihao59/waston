<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="mcm-left-box fl">
	<div class="mlb-tt"></div>
	<ul class="mlb-ul">
		<li>
			<a href="member/account/index.htm"  class="ma-a cur cc-menu" menu="1">我的帐户</a>
			<div class="mp-box">
				<a href="member/account/index.htm" class="zl-a ">我的资料</a>
			</div>
			<div class="mp-box">
				<a href="member/account/change_pwd.htm" class="mm-a ">修改密码</a>
			</div>
		</li>
		<li>
			<a href="member/account/my_order.htm"  class="mo-a cc-menu" menu="3">我的订单</a>
		</li>
		<li>
			<a href="member/account/address_list.htm"  class="da-a cc-menu" menu="4">收货地址</a>
		</li>
	</ul>
	<div class="mail-to-us-box">
		<div class="mtu-tt-box">24/7 Help</div>
		<div class="cuat-box">
			<div class="cb1-font">Call us any time</div>
			<div class="cb2-font">095504928</div>
		</div>
		<a href="mailto:info@nzue.com" class="mail-to-us">MAIL TO US</a>
	</div>
</div>


<script type="text/javascript">
$(document).ready(function () {
	var menu = '${param.menu}';
	if(menu && menu.length > 0){
		$('.cc-menu').removeClass("cur");
		$('.cc-menu[menu='+menu+']').addClass("cur");
	}
});
</script>