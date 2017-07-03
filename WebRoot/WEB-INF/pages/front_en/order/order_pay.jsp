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
		<title>确认支付 - 澳新邮易购</title>
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
			<div class="shopping-step3 mb20"></div>
			<div class="pay-to-box">
				<div class="tab-bar-ptb">您的订单号：${item.orderNo}</div>
				<ul class="mot-tt-ul clb mt15">
					<li style="width:864px;">&nbsp;&nbsp;&nbsp;&nbsp;产品</li>
					<li class="tc" style="width:121px;">状态</li>
					<li class="tc" style="width:82px;">价格</li>
					<li class="tc" style="width:133px;">操作</li>
				</ul>
				<table style="margin-top:1px;width:1200px;" class="my-order-table">
					<tbody>
					<tr class="mot-cc-tr">
						<td width="870px">
							<c:forEach items="${item.orderSellItems}" var="orderSellItem" varStatus="st">
								<div class="mot-project" <c:if test="${st.index != 0}">style="padding-top:20px;border-top:1px solid #dadada;margin-top:20px;"</c:if>>
									<div class="motp-box fl">
										<div class="motp-img-box">
											<a href="product/detail.htm?id=${orderSellItem.productId}"><img src="${orderSellItem.productPhoto}"></a>
										</div>
									</div>
									<div class="ml10 fl motp-cc-box" style="width:730px;">
										<div class="mcb-tt mt5">
											<a style="color:#666;font-weight:bold;font-size:16px;" href="product/detail.htm?id=${orderSellItem.productId}">${dv:e(orderSellItem.productName,95)}</a>
										</div>
										<div class="clb mt10">
											<div class="fl fs-num-font">￥${orderSellItem.unitPrice} x ${orderSellItem.num}</div>
										</div>
									</div>
									<div class="cb"></div>
								</div>
							</c:forEach>
						</td>
						<td width="130px" class="tc">
							<div class="orange-col">${item.instantOrderState}</div>
						</td>
						<td width="85px" class="tc">
							<label class="num-col">￥${item.amountTotal}</label>
						</td>
						<td width="140px" class="tc">
							<div class="operation-a"><a href="javascript:void(0);" onclick="payOrder('${item.id}')" class="">立即付款</a></div>
						</td>
					</tr>
				</tbody></table>
				
				
				
				<div class="step2-address-cc-box2 clb" style="margin-top:20px;width:100%;">
					<div class="front-form-item" style="margin-top:0px;">
						<div class="total-box" style="margin-top:0px;margin-bottom:5px;">
							<s:if test="item.discountCode != null && item.discountCode == 1">
							<%--<label>邮费</label>
							<span>￥${postage}</span>--%>
							<label>原价</label>
							<span>￥<fmt:formatNumber value="${item.amountTotal / 0.9}" pattern="#.0"/></span>
							<label class="mr10" style="background:#d0191e;padding:2px 5px;color:#fff;">折扣10%</label>
							<label>总计</label>
							<span>￥${item.amountTotal}</span>
							</s:if>
							<s:else>
							<%--<label>邮费</label>
							<span>￥${postage}</span>--%>
							<label>总计</label>
							<span>￥${item.amountTotal}</span>
							</s:else>
							<label>汇率：1人民币=${exchangeRate.nzdRate}新西兰币</label>
							<span style="margin-right:25px;">$${item.exchangeRateAmountTotal} NZD</span>
						</div>
						<a href="javascript:void(0);" onclick="payOrder('${item.id}');return false;" class="bin-btn2 fr mr15" id="payOrderBtn">立即付款</a>
					</div>
				</div>
			</div>
		</div>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
		<script type="text/javascript" src="static/member/order/order_pay.js"></script>
  	</body>
</html>
