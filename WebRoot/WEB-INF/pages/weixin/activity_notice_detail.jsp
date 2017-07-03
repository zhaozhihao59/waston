<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="author" content="Tocersoft" />
		<meta content="telephone=no" name="format-detection" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<meta name="robots" content="all" />
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<title>活动列表</title>
		<jsp:include page="/WEB-INF/pages/weixin/wx_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/weixin/phone.css" />
	</head>
  	<body>
  		<div class="and-showbox">
	  		<div class="hbd">
				<div class="hmain" style="font-weight:normal;color:#555555;">活动信息</div>
				<div class="hleft"  onclick="history.go(-1);">
					<a href="javascript:void(0);" class="scl-goback-btn"></a>
				</div>
				<div class="hright">
				</div>
			</div>
			<div class="and-list-box">
				<c:if test="${activity.state == 1 }">
				<div class="anl-lico2"></div>
			</c:if>
			<c:if test="${activity.state == 2 }">
				<div class="anl-lico"></div>
			</c:if>
			<c:if test="${activity.state == 3 }">
				<div class="anl-lico3"></div>
			</c:if>
				<div class="anl-ttfont2">${activity.title }</div>
				<div class="hdrq-font">活动日期：${activity.dateStr }</div>
				<div class="hdrq-font">活动地点：${activity.address }</div>
			</div>
	  		<div class="scl-bj-box">
	  			<div class="scl-bjccbox">
	  	 				${activity.desc }
	  			</div>
	  		</div>
	  		<div style="height:50px;"></div>
	  		<s:if test="activity.state == 2">
	  			<a href="javascript:void(0);" class="scl-ljbm-btn" id="and-ljbm"  >立即报名</a>
	  		</s:if>
  		</div>
  		
  	<s:form id="registerForm" method="post">
  		<div class="and-showbox2" style="display:none;">
	  		<div class="hbd">
				<div class="hmain" style="font-weight:normal;color:#555555;">报名</div>
				<div class="hleft">
				</div>
				<div class="hright">
				</div>
			</div>
			<input id="activityId" name="item.activityId" type="hidden" value="${activity.id}"/>
			<div class="bm-hbd">
	  			<div class="bm-hmain"><input id="email" type="text" name = "item.email" class="bm-text" /></div>
	  			<div class="bm-hleft"><label class="xx-ico">*&nbsp;</label>邮箱</div>
	  		</div>
			<div class="bm-hbd">
	  			<div class="bm-hmain"><input id="name" type ="text" name="item.name" class="bm-text" /></div>
	  			<div class="bm-hleft"><label class="xx-ico">*&nbsp;</label>姓名</div>
	  		</div>
			 <div class="bm-hbd">
	  			<div class="bm-hmain"><input id="companyName" type="text" name="item.companyName" class="bm-text" /></div>
	  			<div class="bm-hleft"><label class="xx-ico">*&nbsp;</label>公司</div>
	  		</div>
			<div class="bm-hbd">
	  			<div class="bm-hmain"><input id="mobile" type="text" name="item.mobile" class="bm-text" /></div>
	  			<div class="bm-hleft"><label class="xx-ico">*&nbsp;</label>手机</div>
	  		</div>
			<div class="bm-hbd">
	  			<div class="bm-hmain"><input id="positionName" type="text" name="item.positionName" class="bm-text" /></div>
	  			<div class="bm-hleft">职务</div>
	  		</div>
	  		<div class="bm-hbd">
	  			<div class="bm-hmain"><input id="tel" type="text" name="item.tel" class="bm-text" /></div>
	  			<div class="bm-hleft">电话</div>
	  		</div>
	  		<!-- <div class="bm-hbd" style="height:80px;">
	  			<div class="bm-hmain" style="height:80px;">
	  				<textarea class="bm-textarea"></textarea>
	  			</div>
	  			<div class="bm-hleft" style="height:80px;">备注</div>
	  		</div>
	  		<div style="height:150px;background:#fff;"></div> -->
	  		<div class="scl-confirm-btn-box2">
  				<a href="javascript:void(0);" class="scl-ljbm-btn2" id="bm-btn" >保存并提交</a>
  				<a href="javascript:void(0);" class="scl-ljbm-btn2 " style="margin-top: 10px;" id="bm-btn-tongxingren" >提交并添加同行人</a>
	  			<a href="javascript:void(0);" class="scl-qx-btn2" id="qx-btn" style="margin-top:10px;">取消</a>
	  		</div>
  		</div>
  		<%-- <div>
  			<span id="emailTip"></span>
  			<span id="nameTip"></span>
  			<span id="companyNameTip"></span>
  			<span id="mobileTip"></span>
  			<span id = "positionNameTip"></span>
  		</div> --%>
  	</s:form>
  		<div class="qd-success-box"  style="display:none;">
  			<div class="qd-sb-btn"></div>
  			<div class="qd-wfont">恭喜您报名成功</div>
  			<div class="qd-time">进取 敬业 思远 诚信</div>
  			<a href="weixin/to_activity_list.htm" class="scl-ljbm-btn" id="and-ljbm">返回</a>
  		</div>
  		<script>
  			$(function(){
  				$("#and-ljbm").click(function(){
  					//var data = $(this).attr("data");
					//$("#activityId").val(data);
  					$(".and-showbox").fadeOut("fast");
  					$(".and-showbox2").fadeIn("slow");
  				});
  				$("#qx-btn").click(function(){
  					$(".and-showbox").show();
  					$(".and-showbox2").hide();
  				});
  				/*  $("#bm-btn").click(function(){
  					 $(".qd-success-box").fadeIn("slow"); 
  					 $(".and-showbox2").fadeOut("slow"); 
  				});  */
  			});
  		</script>
  		<script type="text/javascript" src="static/weixin/activity_notice_detail.js"></script>
	</body>
</html>
