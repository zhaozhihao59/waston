<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				<%--预约弹出层--%>
				<div id="massageDiv" class="msg-box-content none">
					<div class="od-box-tbg">
						<div class="od-box-title"></div>
						<a href="javascript:void(0);" class="od-close-btn"></a>
					</div>
					
					<div class="od-box-select">
						<form id="leftMessageForm" action="message/save.htm" method="post">
							<s:hidden id="itemCreateBy" name="item.createBy" ></s:hidden>
							<s:hidden id="itemType" name="item.messageType"></s:hidden>
							<s:hidden id="itemHopeKnow" name="item.hopeKnow"></s:hidden>
							<s:hidden id="itemOther" name="item.other"></s:hidden>
							<s:hidden id="itemOtherContent" name="item.otherContent"></s:hidden>
							<s:hidden id="startTime" name="item.replyDateStart"></s:hidden>
							<s:hidden id="endTime" name="item.replyDateEnd"></s:hidden>
							<div>
								<div class="od-t fl">省市：</div>
								<div class="fl od-type">
									<div>
										<select id="pro" class="w100 vm">
											<option>请选择</option>
										</select>
										<select id="cit" class="w70 vm ml5">
											<option>请选择</option>
										</select>
									</div>
									<div class="mt5"><input id="otherAddress" type="text" class="w300 vm  od-text"></div>
								</div>
								<div class="cb"></div>
							</div>
							
							<div class="od-explain mt7">
								说明：请您提供拜访地址以，便我们提供更好的理财服务；
							</div>
							<div class="mt7">
								<div class="od-t fl">希望交流的时间：</div>
								<div class="fl od-type" style="position:z-index:200">
									<label><input id="hopeTime" name="hopeTime" val="1" checked="checked" type="radio" class="vm"><span class="vm ml10">越快越好</span></label>
									<label><input id="hopeTime" name="hopeTime" val="2" type="radio" class="vm ml20"><span class="vm ml10">具体时间段</span></label>
									<input id="replyDateStart"  readonly="readonly" type="text" class="w100 vm ml5 od-text timeInput">
									<span class="vm ml5">时间</span>
									<input id="replyDateEnd"  type="text" readonly="readonly" class="w100 vm ml5 od-text timeInput">
								</div>
								<div class="cb"></div>
							</div>
							<div class="od-explain mt7">
								说明：我们将尽快按您的预约时间与您通话，但不排除因特殊情况与您预约的时间不符，请您谅解；
							</div>
							<div class="mt7">
								<div class="od-t fl">您想了解哪方面：</div>
								<div class="fl od-type">
									<div id="checkboxForm">
									</div>
									<div>
										<label><input name="knowArray" type="checkbox" val="1" class="vm otherVm"><span class="vm ml10">其他</span></label>
										<input id="otherContentHiden" type="text" class="w350 vm ml10 od-text">
									</div>
								</div>
								<div class="cb"></div>
							</div>
							<div class="mt7">
								<div class="od-t fl">其他说明：</div>
								<div class="fl od-type">
									<textarea id="itemContent" name="item.content" class="od-textarea h50"  style="width:400px;*width:402px;padding:5px;"></textarea>
								</div>
								<div class="cb"></div>
							</div>
							
							<div class="od-btn-bg">
								<a href="javascript:void(0);" class="commit-btn fl saveMessageBtn">提交</a>
								<a href="javascript:void(0);" class="commit-btn fl ml10 c-btn">取消</a>
							</div>
							<div class="cb"></div>
						</form>
					</div>	
				</div>
				<div class="msg-box none">
				</div>
				<%--预约弹出层end--%>


<div class="member-center-left-layout fl">
	<div class="mc-font">
		<label class="mc-c-font ml15">会员中心</label>
		<label class="mc-e-font">MEMBER CENTER</label>
	</div>
	<div class="mc-list">
	<%--选中三角尖mc-ico 背景样式后+ -hover --%>
		<ul>
			<li>
				<a menu="1" href="m/acc/index.htm" class="left-menu basic-information">基本资料<span menu="1" class="mc-ico"></span></a>
			</li>
			<li>
				<a menu="7" href="m/explan/detail.htm?id=95b51b8b11f44cdf8294bc52b56c4b94" class="left-menu pro-jlb">俱乐部介绍<span menu="7" class="mc-ico"></span></a>
			</li>
			<li>
				<a menu="4" href="m/event/index.htm" class="left-menu pro-vip">会员独享活动<span menu="4" class="mc-ico"></span></a>
			</li>
			<li>
				<a menu="2" href="m/pro/index.htm" class="left-menu pro-tj">精选产品推荐<span menu="2" class="mc-ico"></span></a>
			</li>
			<li>
				<a menu="8" href="http://wpa.qq.com/msgrd?v=3&uin=1826326977&site=qq&menu=yes" class="left-menu online-pro-lcs">在线理财师<span menu="8" class="mc-ico"></span></a>
			</li>
			<li>
				<a menu="3" href="javascript:void(0);" class="left-menu pro-lcs yjfk">预约理财师<span menu="3" class="mc-ico"></span></a>
			</li>
			<li>
				<a menu="5" href="m/trade/index.htm" class="left-menu pro-jy">账户交易明细<span menu="5" class="mc-ico"></span></a>
			</li>
			<li>
				<a menu="6" href="m/sec/password.htm" class="left-menu pro-upwd">密码修改<span menu="6" class="mc-ico"></span></a>
			</li>
		</ul>
	</div>
</div>
<script>
	var provinceList = <%=application.getAttribute("provinceList")%>
</script>
<script type="text/javascript" src="static/front/js/member/left_message.js"></script>
<%--账户信息end--%>
<script type="text/javascript">
$(document).ready(function () {
	var menu = '${param.menu}';
	if(menu && menu.length > 0){
		$('.left-menu').removeClass("cur");
		$('.left-menu[menu='+menu+']').addClass("cur");
		$('.mc-ico').removeClass("cur");
		$('.mc-ico[menu='+menu+']').addClass("cur");
	}
});
</script>
<script>
	var h = $(window).height();
	$(".od-close-btn").click(function(){
		$(".msg-box-content").hide();
		$(".msg-box").hide();
	});
	$(".c-btn").click(function(){
		$(".msg-box-content").hide();
		$(".msg-box").hide();
	});
		
	$(window).scroll(function() {
		var y = $(window).scrollTop();
			$('.msg-box').css('margin-top', y + 'px');
	});

</script>
