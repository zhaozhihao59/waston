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
		<title>澳新邮易购 - 我的订单</title>
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
			
				<div class="mrb-wel-font">我的订单</div>
			
				<div class="mrb-search-box">
					<table class="msb-table">
						<tr>
							<td width="60px;">订单号：</td>
							<td width="200px;"><input id="orderNo" name="orderSellCondition.orderNo" type="text" class="address-text w150" style="margin:0 0 0 10px;"></td>
							<td width="80px">订单状态：</td>
							<td width="170px;">
								<div class="select_div w150 fl" style="margin:0 0 0 10px;">
									<select class="w" id="orderState" name="orderSellCondition.orderState">
										<option value="">全部</option>
										<option value="0">未付款</option>
										<option value="1">待发货</option>
										<option value="2">已发货</option>
										<option value="3">交易成功</option>
										<option value="4">订单作废</option>
									</select>
								</div>
							</td>
							<td>
								<a href="javascript:void(0)" onclick="searchOrder12();" class="co-save-btn fl" style="margin:1px 0 0 10px;">查123询</a>
							</td>
						</tr>
					</table>
				</div>
				
				<ul class="mot-tt-ul clb mt15" style="margin-bottom:1px;">
					<li style="width:490px;padding-left:20px;">产品</li>
					<li style="width:165px;" class="tc">状态</li>
					<li style="width:105px;" class="tc">价格</li>
					<li style="width:170px;" class="tc">操作</li>
				</ul>
				<table class="my-order-table" style="margin-top:1px;">
					<c:forEach items="${orderSells}" var="orderSell">
						<tr>
							<td width="175px" class="tl tb-title-ord" colspan="4">
								<label class="num-col ml10">订单号：${orderSell.orderNo}</label>
								<label class="num-col ml10">下单时间：<fmt:formatDate value="${orderSell.createDate}" pattern="yyyy-MM-dd HH:mm"/></label>
								<label class="num-col ml10">邮费：￥${orderSell.amountFreight}</label>
							</td>
						</tr>
						<tr class="mot-cc-tr">
							<td width="420px">
								<c:forEach items="${orderSell.orderSellItems}" var="orderSellItem" varStatus="st">
									<div class="mot-project" <c:if test="${st.index != 0}">style="padding-top:20px;border-top:1px solid #dadada;margin-top:20px;"</c:if>>
										<div class="motp-box fl">
											<div class="motp-img-box">
												<a href="product/detail.htm?id=${orderSellItem.productId}"><img src="${orderSellItem.productPhoto}"></a>
											</div>
										</div>
										<div class="fl motp-cc-box" style="width:380px;">
											<div class="mcb-tt mt5 ml10">
												<a style="font-size:14px;" href="product/detail.htm?id=${orderSellItem.productId}">${dv:e(orderSellItem.productName,95)}</a>
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
							<label class="num-col">$${orderSell.exchangeRateAmountTotal} NZD</label>
						</td>
						<td width="140px" class="tc">
							<c:if test="${orderSell.orderState == 0}">
								<div class="operation-a"><a href="javascript:void(0)" onclick="payOrder('${orderSell.id}')">现在支付</a></div>
								<div class="operation-a"><a href="member/account/my_order_detail.htm?orderId=${orderSell.id}">订单详情</a></div>
								<div class="operation-a"><a href="javascript:void(0)" onclick="cancelOrder('${orderSell.id}')">取消订单</a></div>
							</c:if>
							<c:if test="${orderSell.orderState == 1}">
								<div class="operation-a"><a href="member/account/my_order_detail.htm?orderId=${orderSell.id}">订单详情</a></div>
							</c:if>
							<c:if test="${orderSell.orderState == 2}">
								<div class="operation-a"><a href="javascript:void(0)" onclick="comfirmBusiness('${orderSell.id}')">确认收货</a></div>
							</c:if>
							<c:if test="${orderSell.orderState == 3}">
								<div class="operation-a"><a href="member/account/my_order_detail.htm?orderId=${orderSell.id}">订单详情</a></div>
							</c:if>
							<c:if test="${orderSell.orderState == 4}">
								<div class="operation-a"><a href="javascript:void(0)" onclick="clearOrder('${orderSell.id}')">移除订单</a></div>
							</c:if>
						</td>
					</tr>
					</c:forEach>
				</table>
				
				<div class="pagination mt20 J_loadPaging">
					 <ul></ul>
					 <input class="page_type" value="0" type="hidden">
					 <input class="page_n" value="5" type="hidden">
					 <input class="page_url" value="member/account/my_order.htm?m=1" type="hidden">
					 <input id="currentPage" class="page_thisPage" value="${pageResult1.currentPage }" type="hidden">
					 <input class="page_lastPage" value="${pageResult1.lastPage }" type="hidden">
				</div>
			 	<input id="allPages" value="${pageResult1.allPages }" type="hidden">
			</div>	
			<div class="cb"></div>
		</div>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
		<script type="text/javascript" src="static/member/order/order_pay.js"></script>
		<script type="text/javascript" src="static/front/js/page.js"></script>
  </body>
</html>
