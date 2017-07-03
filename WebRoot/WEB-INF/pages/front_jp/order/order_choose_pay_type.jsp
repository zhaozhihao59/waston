<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
		<meta name="baidu-site-verification" content="k66pxbcp2Z" />
		<title>请选择付款方式 - 澳新邮易购</title>
		<meta name="Keywords" content="" />
		<meta name="description" content="" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
	</head>
  	<body>
  		<%--头部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<%--头部end--%>
		<div class="shopping-cart-box">
			<div class="pay-to-box">
				<div class="tab-bar-ptb">
					<span>您的订单号：${item.orderNo}</span>
					|
					<span>应付金额：￥${item.amountTotal}</span>
					<span class="ml10">汇率：1人民币=${exchangeRate.nzdRate}新西兰币</span>
					<span class="ml10">$${item.exchangeRateAmountTotal} NZD</span>
				</div>
				<ul class="mot-tt-ul clb mt15">
					<li style="width:100%"><span style="margin-left:10px;">选择付款方式</span></li>
				</ul>
				<table style="margin-top:10px;width:800px;" class="my-order-table">
					<tbody>
					<tr>
						<td width="130px" class="tc">
							<input type="radio" name="payType" checked="checked" value="1" style="vertical-align: middle;"/>
							<img src="static/member/css/images/paypal_logo.png" />
						</td>
						<td width="140px" class="tc">
							<input type="radio" name="payType" value="2"/>
							<img src="static/member/css/images/chinabank_logo.gif" />
						</td>
						<td width="140px" class="tc">
							<input type="radio" name="payType" value="3"/>
							<img src="static/member/css/images/alipay_logo.jpg" />
						</td>
						<td width="140px" class="tc"></td>
					</tr>
					</tbody>
				</table>
				
				<div class="step2-address-cc-box2 clb" style="margin-top:20px;width:100%;">
					<div class="front-form-item" style="margin-top:0px;">
						<a href="javascript:void(0);" onclick="payOrder('${item.id}');return false;" class="bin-btn2 fl ml15" id="payOrderBtn">立即付款</a>
					</div>
				</div>
			</div>
		</div>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
		<script type="text/javascript" src="static/member/order/order_choose_pay_type.js"></script>
  	</body>
</html>
