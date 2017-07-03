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
		<title>澳新邮易购 - 购物车</title>
		<meta name="Keywords" content="新西兰直邮商品,澳大利亚代购,新西兰代购" />
		<meta name="description" content="澳新邮易购 - 100%新西兰直邮商品,购买新西兰产品最优选择。" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
		<link rel="stylesheet" type="text/css" href="static/front/css/lethe.css" />
	</head>
  	<body>
  		<%--头部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header_cart.jsp"></jsp:include>
		<%--头部end--%>
		<div class="shopping-cart-box">
			<div class="shopping-step1"></div>
			<c:if test="${empty cartItemList}">
				<div class="ey-box">
					<div class="gwc-box2">
						<div class="mb10">购物车内暂时没有商品</div>
						<div><a href="#" class="blue-a-href">去首页</a>挑选喜欢的商品吧！</div>
					</div>
				</div>	
				
				<div class="favourite-goods-box clb" style="width:100%;">
					<div class="fl">
						您可能对下列产品感兴趣
					</div>
				</div>
				
				<ul class="favourite-goods-ul clb">
					<c:forEach items="${starProducts}" var="pro" end="4">
						<li>
							<div class="fgoods-img-box">
								<a href="product/detail.htm?id=${pro.id }"><img src="${pro.imageUrl}"></a>
							</div>
							<div class="fg-tt-box">
								<div class="fg-tt-font"><a href="product/detail.htm?id=${pro.id }" title="">${dv:e(pro.shortDesc,60)}</a></div>
								<div class="fg-price-col">￥${pro.unitPrice}</div>
							</div>
						</li>
					</c:forEach>
				</ul>
				
			</c:if>
			<c:if test="${not empty cartItemList}">
			<div class="clb">
				<div class="fl gwc-box">
					<div class="gwc-font">购物车</div>
					<div class="gwc-font2">您的购物车中的物品</div>
				</div>
				<div class="fr">
					<a href="javascript:void(0);" class="sc-remove-btn J-removeCartItem">移除</a>
				</div>
			</div>
			
			<table class="cart-list-table">
				<tr class="clt-tt-tr">
					<td style="border-left:1px solid #dadada;" width="150px;">
						产品图片
					</td>
					<td width="600px;" class="tl">
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
					<td style="border-right:1px solid #dadada;" width="70px;">
						<label><span class="vm">全选</span><input id="selectAll" type="checkbox" class="vm ml5 mr5"></label>
					</td>
				</tr>
				
				
				<c:forEach items="${cartItemList}" var="item">
				<tr class="clt-cc-tr">
					<td width="150px;">
						<div class="clt-cc-img">
							<a href="product/detail.htm?id=${item.product.id}"><img src="${item.product.imageUrl}"/></a>
						</div>
					</td>
					<td width="590px;" style="padding-right:10px;" class="tl">
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
						￥${item.unitPrice}
					</td>
					<td width="150px;">
						<ul class="num-ul" style="margin-left:35px;">
							<li>
								<a href="javascript:void(0);" class="js-box J-minusNum" data="${item.id}"></a>
							</li>
							<li>
								<input type="text" class="num-text J-cartNum" data="${item.id}" value="<fmt:formatNumber value="${item.num}" type="number"/>"/>
							</li>
							<li>
								<a href="javascript:void(0);" class="zj-box J-addNum" data="${item.id}"></a>
							</li>
						</ul>
					</td>
					<td width="100px;" class="tc">
						<label class="r-price-box J-sumPriceLabel">
							￥${item.sumPrice}
						</label>
					</td>
					<td width="70px;">
						<div class="check-box-box">
							<input class="J-sumPrice" type="hidden" value="${item.sumPrice}"/>
							<input class="J-checkbox" data="${item.id}" type="checkbox" style="margin-top:50px;"/>
						</div>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<div class="total-box">
				<label>总计</label>
				<span id="totalPrice">￥${totalPrice}</span>
			</div>
			
			<a id="buyNow" href="javascript:void(0);" class="buy-now-btn  ">立即购买</a>
			<div class="cb"></div>
			
			</c:if>
		</div>
		<%--
		<jsp:include page="/WEB-INF/pages/front/comm/login-box.jsp"></jsp:include>
		--%>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
		
		<script src="static/base/lib/jquery.formatCurrency-1.4.0.pack.js" type="text/javascript"></script>
		<script type="text/javascript" src="static/front/order/js/cart_list.js"></script>
		
  	</body>
</html>
