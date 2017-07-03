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
		<title>澳新邮易购 - 订单详情</title>
		<meta name="Keywords" content="新西兰直邮商品,澳大利亚代购,新西兰代购" />
		<meta name="description" content="澳新邮易购 - 100%新西兰直邮商品,购买新西兰产品最优选择。" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
	</head>
  	<body>
  		<%--头部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<%--头部end--%>
		<div class="member-center-mmbox">
			<jsp:include page="/WEB-INF/pages/member/comm/member_left.jsp">
				<jsp:param name="menu" value="3" />
			</jsp:include>
			
			<div class="mcm-right-box fr">
			
				<div class="mrb-wel-font">
					订单详情
					<a href="member/account/my_order.htm">返回我的订单</a>
				</div>
			
				<ul class="mot-tt-ul clb mt15">
					<li style="width:490px;padding-left:20px;">产品</li>
					<li style="width:165px;" class="tc">状态</li>
					<li style="width:105px;" class="tc">价格</li>
					<li style="width:170px;" class="tc">操作</li>
				</ul>
				<table class="my-order-table" style="margin-top:1px;">
					<tr>
						<td width="175px" class="tl tb-title-ord" colspan="4">
							<label class="num-col ml10">订单号：${orderSell.orderNo}</label>
							<label class="num-col ml10">下单时间：<fmt:formatDate value="${orderSell.createDate}" pattern="yyyy-MM-dd HH:mm"/></label>
							<label class="num-col ml10">邮费：￥${orderSell.amountFreight}</label>
						</td>
					</tr>
					<tr class="mot-cc-tr">
						<td width="470px">
							<c:forEach items="${orderSell.orderSellItems}" var="orderSellItem" varStatus="st">
								<div class="mot-project" <c:if test="${st.index != 0}">style="padding-top:20px;border-top:1px solid #dadada;margin-top:20px;"</c:if>>
									<div class="motp-box fl">
										<div class="motp-img-box">
											<a href="product/detail.htm?id=${orderSellItem.productId}"><img src="${orderSellItem.productPhoto}"></a>
										</div>
									</div>
									<div class="fl motp-cc-box" style="width:380px;">
										<div class="mcb-tt mt5 ml10">
											<a href="product/detail.htm?id=${orderSellItem.productId}">${dv:e(orderSellItem.productName,95)}</a>
										</div>
										<div class="clb mt10 ml10">
											<div class="fl fs-num-font">￥${orderSellItem.unitPrice} x ${orderSellItem.num}</div>
										</div>
									</div>
									<div class="cb"></div>
								</div>
							</c:forEach>
						</td>
						<td width="130px" class="tc">
							<div class="gray-col">${orderSell.instantOrderState}</div>
						</td>
						<td width="85px" class="tc">
							<label class="num-col">$${orderSell.exchangeRateAmountTotal}</label>
						</td>
						<td width="140px" class="tc">
							<c:if test="${orderSell.orderState == 0}">
								<div class="operation-a"><a href="javascript:void(0)" onclick="payOrder('${orderSell.id}')">现在支付</a></div>
								<div class="operation-a"><a href="javascript:void(0)" onclick="cancelOrder('${orderSell.id}')">取消订单</a></div>
							</c:if>
							<c:if test="${orderSell.orderState == 1}">
								<div class="operation-a"><a href="member/account/my_order_detail.htm?orderId=${orderSell.id}">--</a></div>
							</c:if>
							<c:if test="${orderSell.orderState == 2}">
								<div class="operation-a"><a href="javascript:void(0)" onclick="comfirmBusiness('${orderSell.id}')">确认收货</a></div>
							</c:if>
							<c:if test="${orderSell.orderState == 3}">
								<div class="operation-a"><a href="member/account/my_order_detail.htm?orderId=${orderSell.id}">--</a></div>
							</c:if>
							<c:if test="${orderSell.orderState == 4}">
								<div class="operation-a"><a href="javascript:void(0)" onclick="clearOrder('${orderSell.id}')">移除订单</a></div>
							</c:if>
						</td>
					</tr>
				</table>
				<c:if test="${not empty orderSell.words}">
					<div class="mt20">
						<div class="fl mo-addr-font">买家留言</div>
						<div class="fr mo-addr-line">
						</div>
						<div class="cb"></div>
					</div>
					
					<div class="mo-addr-cbox mt20">
						<div>${orderSell.words}</div>
					</div>
				</c:if>
				
				<div class="mt20">
					<div class="fl mo-addr-font">地址</div>
					<div class="fr mo-addr-line">
					</div>
					<div class="cb"></div>
				</div>
				
				<div class="mo-addr-cbox mt20">
					<div>收货人姓名：${orderSell.sendLinkman}</div>
					<div class="mt10">
						<div class="fl">收货地址：</div>
						<div class="fl " style="width:330px;">${orderSell.sendCountry}${orderSell.sendProvince}${orderSell.sendCity}${orderSell.sendAddress}</div>
						<div class="cb"></div>
					</div>
					<div class="mt10">邮政编码：${orderSell.sendPostCode}</div>
					<div class="mt10">电话/手机：${orderSell.sendContact}</div>
				</div>
			</div>	
			<div class="cb"></div>
		</div>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
		<script type="text/javascript" src="static/member/order/order_pay.js"></script>
  </body>
</html>

