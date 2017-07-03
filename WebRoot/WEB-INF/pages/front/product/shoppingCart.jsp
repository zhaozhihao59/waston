<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
		<meta name="baidu-site-verification" content="k66pxbcp2Z" />
		<title>Trademonster - resetPassword</title>
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
			<div class="shopping-step1"></div>
			<div class="sc-nav-box clb">
				<div class="fl" style="line-height:40px;">
					<label>Shopping Cart </label>
					<span>Items in your shopping cart</span>
				</div>
				<a href="#" class="sc-remove-btn fr">
					Remove
				</a>
			</div>
			
			<ul class="sc-list-ul">
			<c:forEach items="${cartItemList }" var="cart">
				<li class="clb">
					<div class="fl">
						<div class="sc-img-box">
							<a href="#"><img src="${cart.imgPath }"></a>
						</div>
					</div>
					<div class="stb-cc-box fr">
						<table class="stb-table">
							<tr>
								<td width="645px">
									<div class="stb1-box">
										<a href="#">${dv:e(cart.shortDescEn,180)}</a> 
									</div>
								</td>
								<td width="130px" class="tc">
									<div class="stb1-price">$ ${cart.price }/Piece</div>
								</td>
								<td width="70px" class="tc">
									<input type="text" class="spb-text vm" value="${cart.amount }" id="amount">
								</td>
								<td width="40px">
									pieces
								</td>
								<td width="120px" class="tc">
									<div class="stb1-price-box">$ ${cart.productMoney }</div>
								</td>
								<td width="50px" class="tc" style="background:#fbfbfb;vertical-align:middle;" rowspan="5">
									<input type="checkbox">
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<div class="stb2-box mt10">
										Customized options:S5 1:1 i9600 Quad Core MTK6582
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<div class="stb2-box mt10">
										Color:Black
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<div class="clb stb2-as-box mt10">
										<div class="fl">Add remark to seller</div>
										<div class="fr mr20">Shipping Cost:US $ ${cart.productMoney }</div>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<div class="stb2-textarea-box">
										<textarea class="stb2-textarea"></textarea>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</li>
			</c:forEach>
			</ul>
			<div class="grand-total-box">
				<span>Grand Total:</span>
				<label>US $ ${shopMoney }</label>
			</div>
			<div class="fr mt10">
				<a href="#" class="cts-btn fl">Continue Shopping</a>
				<a href="product/confirmOrder.htm" class="ptc-btn fl ml10">Proceed to Checkout</a>
			</div>
			<div class="cb"></div>
		</div>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
		
  	</body>
  	
  	<script type="text/javascript">
  		$(document).ready(function(){
  			var proCount = $("#amount").val();
  			var proPrice = $("#").val();
		});
  	</script>
</html>
