<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html >
<html >
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
		<meta name="baidu-site-verification" content="k66pxbcp2Z" />
		<title>确认订单 - 澳新邮易购</title>
		<meta name="Keywords" content="新西兰直邮商品,澳大利亚代购,新西兰代购" />
		<meta name="description" content="澳新邮易购 - 100%新西兰直邮商品,购买新西兰产品最优选择。" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
	</head>
		<script type="text/javascript">
  			var provinceList = <%=application.getAttribute("provinceList")%>;
  			var provinceCity = '${item.provinceCity}';
		</script>
  	<body>
  		<%--头部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<%--头部end--%>
		<div id="">
			<jsp:include page="/WEB-INF/pages/front/comm/address-box.jsp"></jsp:include>
		</div>
		<div class="shopping-cart-box">
			<div class="shopping-step2"></div>
			<div class="step2-address-box"></div>
				<div class="step2-address-cc-box clb">
						<div class="front-form-item">
							<span class="front-item-title w130">收货人姓名：</span>
							<div class="front-item-cc">${memaddress.linkman}</div>
						</div>
						<div class="front-form-item">
							<span class="front-item-title w130">收货地址：</span>
							<div class="front-item-cc">${memaddress.provinceCity }${memaddress.address }</div>
						</div>
						<div class="front-form-item">
							<span class="front-item-title w130">邮编：</span>
							<div class="front-item-cc">${memaddress.postCode }</div>
						</div>
						<div class="front-form-item">
							<span class="front-item-title w130">手机号码：</span>
							<div class="front-item-cc">${memaddress.mobile }</div>
						</div>
					<div class="cz-btn-box">
						<a class="atc-btn fl" href="javascript:void(0);" id="add-address">添加收货地址</a>
						<a class="bin-btn fl ml10 xsbtn" href="javascript:void(0);">选择其他地址</a>
					</div>
				</div>
			<form id="accountForm" action="member/address/doSave.htm" method="post">
				<input id="itemId" name="memaddress.linkman" value="${memaddress.linkman }" type="hidden">
					<div class="step2-address-cc-box2 clb none">
						<div class="front-form-item">
							<span class="front-item-title w130"><label class="red-xx">*</label>收货人：</span>
							<input id="linkman" name="item.linkman"  type="text" class="address-text w250 fl">
							<span id="linkmanTip"></span>
						</div>
						<div class="front-form-item">
							<span class="front-item-title w130"><label class="red-xx">*</label>省市：</span>
		<%-- 					<div class="select_div w120 fl">
								<select class="w"></select>
							</div>
							<div class="select_div fl" style="width:115px;">
								<select class="w"></select>
							</div> --%>
								<input id="addressInput" name="item.provinceCity"  type="hidden" />
									<div class="select_div fl mr2">
										<input id="provinceCode" name="item.provinceId" type="hidden">
										<input id="provinces" name="item.province" type="hidden" >
										<select id="province" class="all-select w100 fl">
											<option value="-1">请选择省份</option>
										</select>
									</div>
									<div class="select_div fl mr2">
										<input id="cityCode" name="item.cityId" type="hidden">	
										<input id="citys" name="item.city" type="hidden" >
										<select id="city" class="all-select w100 fl">
											<option value="-1">请选择城市</option>
										</select>
									</div>
									<div class="select_div fl">
										<input id="addressCode" name="item.districtId" type="hidden">	
										<input id="districts" name="item.district" type="hidden">
										<select id="codeAddress" class="all-select w100 fl">
											<option value="-1">请选择区县</option>
										</select>
									</div>
						</div>
						<div class="front-form-item">
							<span class="front-item-title w130"><label class="red-xx">*</label>街道地址：</span>
							<textarea id="address" name="item.address"    class="address-textarea ml10 mt5 fl mb10"> </textarea>
						<span id="addressTip"></span>
						</div>
						<div class="front-form-item">
							<span class="front-item-title w130"><label class="red-xx">*</label>邮政编码：</span>
							<input id="postCode" name="item.postCode"  type="text" class="address-text w250 fl">
						<span id="postCodeTip"></span>
						</div>
						<div class="front-form-item">
							<span class="front-item-title w130"><label class="red-xx">*</label>电话/手机：</span>
							<input id="mobile" name="item.mobile"   type="text" class="address-text w250 fl">
							<span id="mobileTip"></span> 
						</div>
						
						<div class="front-form-item" style="margin-top:20px;">
							<span class="front-item-title w130"></span>
							<a id="doSaveSubmit" href="javascript:void(0);" class="co-save-btn fl" style="margin:5px 0 0 10px;">保存</a>
							<a href="javascript:void(0);" class="co-close-btn fl" style="margin:5px 0 0 10px;">关闭</a>
						</div>
					</div>
			</form>
			
			<script>
				$(function(){
					if($("#itemId").val() == ''){
						$(".step2-address-cc-box").hide();
						$(".step2-address-cc-box2").fadeIn();
						}
				
					$("#add-address").click(function(){
						$(".step2-address-cc-box").hide();
						$(".step2-address-cc-box2").fadeIn();
					});
					$(".co-close-btn").click(function(){
						$(".step2-address-cc-box").fadeIn();
						$(".step2-address-cc-box2").hide();
					});
				});
				
			</script>
			
			<div class="step2-od-box"></div>
			
			<table class="cart-list-table">
				<tr class="clt-tt-tr">
					<td style="border-left:1px solid #dadada;" width="150px;">
						产品图片
					</td>
					<td width="600px;" class="tl" style="text-align:left;">
						产品名
					</td>
					<td width="130px;">
						单价
					</td>
					<td width="150px;">
						数量
					</td>
					<td width="100px;">
						金额
					</td>
				</tr>
				
				
				<c:if test="${not empty orderSellItemList}">
				<c:forEach items="${orderSellItemList}" var="item">
				<tr class="clt-cc-tr">
					<td width="150px;">
						<div class="clt-cc-img">
							<a href="product/detail.htm?id=${item.product.id}"><img src="${item.product.imageUrl}"/></a>
						</div>
					</td>
					<td width="590px;" style="padding-right:10px;text-align:left;" class="tl"> 
						<div class="goods-name-box">
							<a href="product/detail.htm?id=${item.product.id}">${item.product.name}</a>
						</div>
						<div class="mt30 goods-gg-box">
							<label>产品品牌：${item.product.brandName }</label>
							<%-- <label class="ml15">规格：30ml</label>--%>
							<%-- <label class="ml15">邮寄重量：60g</label>--%>
						</div>
					</td>
					<td width="130px;" class="tc">
						￥${item.product.unitPrice}
					</td>
					<td width="150px;" class="tc">
						<fmt:formatNumber value="${item.num}" type="number"/>
					</td>
					<td width="100px;" class="tc">
						<label class="r-price-box">￥${item.sumPrice}</label>
					</td>
				</tr>
				</c:forEach>
				</c:if>
			</table>
			<s:form id="submitOrderForm" namespace="/member/order/sell" action="confirm">
			<input name="item.sendLinkman" value="${memaddress.linkman}" type="hidden"/>
			<input name="item.sendAddress" value="${memaddress.address}" type="hidden"/>
			<input name="item.sendPostCode" value="${memaddress.postCode}" type="hidden"/>
			<input name="item.sendContact" value="${memaddress.mobile}" type="hidden"/>
			<input name="item.amountFreight" value="${postage}" type="hidden"/>
			<div class="mrb-content-layout clb" style="width:auto;">
				<div class=" fl" style="width:500px;">
					<span class="front-item-title  mr10">买家留言：</span>
					<textarea class="address-textarea  mt5 ml10 mb10" name="item.words" style="width:335px;height:65px;"></textarea>
				</div>
				<div class=" fl"  style="width:500px;">
					<div class="front-form-item">
						<span class="front-item-title w130 mr10">折扣代码：</span>
						<input type="text" class="address-text w250 fl" id="discountCode" name="discountCode" />
					</div>
					<div class="front-form-item">
						<span class="front-item-title w130 mr10">免邮费代码：</span>
						<input type="text" class="address-text w250 fl" id="freeShippingCode"  name="freeShippingCode"/>
					</div>
				</div>	
			</div>
			</s:form>
			<div class="total-box">
				<label>邮费</label>
				<span>￥${postage}</span>
				<label>总计</label>
				<span>￥${orderSellTotalPrice}</span>
				<label>汇率：1人民币=${exchangeRate.nzdRate}新西兰币</label>
				<span>$${orderSellTotalPriceNzd} NZD</span>
			</div>
			<a id="submitOrderBtn" href="javascript:void(0);" class="buy-now-btn">确认订单，并立即支付</a>
			<div class="cb"></div>
		</div>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
  	</body>
  	  <script src="static/front/order/js/order_confirm.js?t=${sysVersion}"></script>
</html>
