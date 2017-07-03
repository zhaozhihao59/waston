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
	</head>
  	<body>
  		<%--头部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<%--头部end--%>
		<div class="shopping-cart-box">
			<div class="shopping-step1"></div>
			<div class="clb">
				<div class="fl gwc-ico">
					<div class="gwc-font">购物车</div>
					<div class="gwc-font2">您的购物车中的商品</div>
				</div>
				<div class="fr">
					<a href="#" class="sc-remove-btn">移除</a>
				</div>
			</div>
			
			
			<table class="cart-list-table">
				<tr class="clt-tt-tr">
					<td style="border-left:1px solid #dadada;" width="150px;">
						产品图片
					</td>
					<td width="600px;">
						产品名
					</td>
					<td width="130px;">
						单价
					</td>
					<td width="150px;">
						数量
					</td>
					<td width="100px;">
						
					</td>
					<td style="border-right:1px solid #dadada;" width="70px;">
						<label><span class="vm">全选</span><input type="checkbox" class="vm ml5 mr5"></label>
					</td>
				</tr>
				
				<tr class="clt-cc-tr">
					<td width="150px;">
						<div class="clt-cc-img">
							<a href="#"><img src="static/front/css/images/test-gd.png"></a>
						</div>
					</td>
					<td width="590px;" style="padding-right:10px;">
						<div class="goods-name-box">
							<a href="#">Antipodes 奇异果籽油滋养修复眼霜30ml Kiwi Seed Oil Eye Cream</a>
						</div>
						<div class="mt30 goods-gg-box">
							<label>产品品牌：Antipodes</label>
							<label class="ml15">规格：30ml</label>
							<label class="ml15">邮寄重量：60g</label>
						</div>
					</td>
					<td width="130px;" class="tc">
						￥299
					</td>
					<td width="150px;">
						<ul class="num-ul" style="margin-left:35px;">
							<li>
								<a href="#" class="js-box"></a>
							</li>
							<li>
								<input type="text" class="num-text">
							</li>
							<li>
								<a href="#" class="zj-box"></a>
							</li>
						</ul>
					</td>
					<td width="100px;">
						<label class="r-price-box">￥142</label>
					</td>
					<td width="70px;">
						<div class="check-box-box">
							<input type="checkbox" style="margin-top:50px;">
						</div>
					</td>
				</tr>
				
				<tr class="clt-cc-tr">
					<td width="150px;">
						<div class="clt-cc-img">
							<a href="#"><img src="static/front/css/images/test-gd.png"></a>
						</div>
					</td>
					<td width="590px;" style="padding-right:10px;">
						<div class="goods-name-box">
							<a href="#">Antipodes 奇异果籽油滋养修复眼霜30ml Kiwi Seed Oil Eye Cream</a>
						</div>
						<div class="mt30 goods-gg-box">
							<label>产品品牌：Antipodes</label>
							<label class="ml15">规格：30ml</label>
							<label class="ml15">邮寄重量：60g</label>
						</div>
					</td>
					<td width="130px;" class="tc">
						￥299
					</td>
					<td width="150px;">
						<ul class="num-ul" style="margin-left:35px;">
							<li>
								<a href="#" class="js-box"></a>
							</li>
							<li>
								<input type="text" class="num-text">
							</li>
							<li>
								<a href="#" class="zj-box"></a>
							</li>
						</ul>
					</td>
					<td width="100px;">
						<label class="r-price-box">￥142</label>
					</td>
					<td width="70px;">
						<div class="check-box-box">
							<input type="checkbox" style="margin-top:50px;">
						</div>
					</td>
				</tr>
				<tr class="clt-cc-tr">
					<td width="150px;">
						<div class="clt-cc-img">
							<a href="#"><img src="static/front/css/images/test-gd.png"></a>
						</div>
					</td>
					<td width="590px;" style="padding-right:10px;">
						<div class="goods-name-box">
							<a href="#">Antipodes 奇异果籽油滋养修复眼霜30ml Kiwi Seed Oil Eye Cream</a>
						</div>
						<div class="mt30 goods-gg-box">
							<label>产品品牌：Antipodes</label>
							<label class="ml15">规格：30ml</label>
							<label class="ml15">邮寄重量：60g</label>
						</div>
					</td>
					<td width="130px;" class="tc">
						￥299
					</td>
					<td width="150px;">
						<ul class="num-ul" style="margin-left:35px;">
							<li>
								<a href="#" class="js-box"></a>
							</li>
							<li>
								<input type="text" class="num-text">
							</li>
							<li>
								<a href="#" class="zj-box"></a>
							</li>
						</ul>
					</td>
					<td width="100px;">
						<label class="r-price-box">￥142</label>
					</td>
					<td width="70px;">
						<div class="check-box-box">
							<input type="checkbox" style="margin-top:50px;">
						</div>
					</td>
				</tr>
			</table>
			
			<div class="total-box">
				<label>总计</label>
				<span>￥1234.00</span>
			</div>
			
			<a href="member/order/sell/buy.htm" class="buy-now-btn">立即购买</a>
			
			<div class="cb"></div>
		</div>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
		
  	</body>
</html>
