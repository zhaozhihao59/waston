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
		<title>Trademonster - list</title>
		<meta name="Keywords" content="" />
		<meta name="description" content="" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
		<link rel="stylesheet" type="text/css" href="static/front/css/lethe.css" />
	</head>
  	<body>
  		<%--头部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<%--头部end--%>
		
		<div class="w1200-mg-auto clb">
			<%--左边--%>
			<jsp:include page="/WEB-INF/pages/front/comm/left-menu.jsp"></jsp:include>
			<%--左边end--%>
			<div class="list-right-box fr">
				<div class="list-nav-box">
					<a href="#">首页</a>
					<label>&nbsp;&gt;&nbsp;</label>
					<label>健康营养</label>
				</div>
				<div class="list-gg-box">
					<a href="#"><img src="static/front/css/images/list-gg.png" class="vm"></a>
				</div>
				
				<table class="lis-type-table">
					<tr>
						<td class="gray-bg-td tc" width="115px">分类</td>
						<td class="list-type-td">
							<a href="#" class="cur">全部（20111）</a>
							<a href="#">减肥瘦身（20111）</a>
							<a href="#">调节分泌（20111）</a>
							<a href="#">补气养血（20111）</a>
							<a href="#">排毒（20111）</a>
						</td>
					</tr>
					<tr>
						<td class="gray-bg-td tc" width="115px">品牌</td>
						<td class="list-type-td">
							<a href="#" class="cur">苹果（20111）</a>
							<a href="#">三星（20111）</a>
							<a href="#">华为（20111）</a>
							<a href="#">诺基亚（20111）</a>
							<a href="#">魅族（20111）</a>
							<a href="#">谷歌（20111）</a>
							<a href="#">联想（20111）</a>
							<a href="#">小米（20111）</a>
							<a href="#">步步高（20111）</a>
							<a href="#">oppo（20111）</a>
							<a href="#">摩托罗拉（20111）</a>
						</td>
					</tr>
					<tr>
						<td class="gray-bg-td tc" width="115px">产地</td>
						<td class="list-type-td">
							<a href="#" class="cur">全部</a>
							<a href="#">新西兰（2000）</a>
							<a href="#">河南（20111）</a>
							<a href="#">荷兰（20111）</a>
							<a href="#">中国（20111）</a>
						</td>
					</tr>
				</table>
				
				<div class="list-search-box">
					<a class="sb-price vm ml10" href="#">价格</a>
					<a class="sb-price vm ml5" href="#">销量</a>
					<label class="ml15 vm">关键词：</label>
					<input class="list-text w120 vm" type="text">
					<label class="ml15 vm">价格：</label>
					<input class="list-text w40 vm" type="text">
					<label class="vm">-</label>
					<input class="list-text w40 vm" type="text">
					<a class="sb-search-btn ml5 vm" href="#">搜索</a>
					<label class="ml50 vm">1-24 页 40,853 件商品</label>
					<a class="sb-search-btn ml20 vm" href="#">上一页</a>
					<a class="sb-search-btn ml5 vm" href="#">下一页</a>
				</div>
				
				<ul class="product-list-ul">
				
				
					<li class="clb">
						<div class="fl pr l-img-box">
							<div class="sc-ico none"></div>
							<div class="product-list-img">
								<img src="static/front/css/images/goods-img.png">
							</div>
						</div>
						<div class="product-content-box fl">
							<div class="list-goods-name">
								<a href="product/detail.htm">${dv:e("芡欧鼠尾草种子",70)}</a>
							</div>
							<div class="list-goods-detail">
								${dv:e("健康芡欧鼠尾草种子是一个易于消化的蛋白质来源,omega - 3脂肪酸,纤维和维生素b .芡欧鼠尾草种子是一个古老的超级食物,
								被认为是玛雅人,阿兹台克和印加作为他们的健康的医学医学和珍贵的增强特性。",220)}
							</div>
							<div class="mt15 clb">
								<div class="pp-box fl">品牌：妞乐</div>
								<div class="address-box fl ml50">产地：新西兰</div>
								<div class="xl-box fl ml50">销量：200</div>
							</div>
						</div>
						<div class="list-cz-box fr">
							 <div class="list-price-box mt10">￥300.00</div>
							 <a class="atc-btn fr mt20" href="product/shoppingCart.htm">添加到购物车</a>
							 <a class="bin-btn fr mt10" href="product/confirmOrder.htm">立即购买</a>
						</div>
					</li>
					
					
					<%-- <li class="clb">
						<div class="fl pr l-img-box">
							<div class="sc-ico none"></div>
							<div class="product-list-img">
								<img src="static/front/css/images/goods-img.png">
							</div>
						</div>
						<div class="product-content-box fl">
							<div class="list-goods-name">
								<a href="#">${dv:e("芡欧鼠尾草种子",70)}</a>
							</div>
							<div class="list-goods-detail">
								${dv:e("健康芡欧鼠尾草种子是一个易于消化的蛋白质来源,omega - 3脂肪酸,纤维和维生素b .芡欧鼠尾草种子是一个古老的超级食物,
								被认为是玛雅人,阿兹台克和印加作为他们的健康的医学医学和珍贵的增强特性。",220)}
							</div>
							<div class="mt15 clb">
								<div class="pp-box fl">品牌：妞乐</div>
								<div class="address-box fl ml50">产地：新西兰</div>
								<div class="xl-box fl ml50">销量：200</div>
							</div>
						</div>
						<div class="list-cz-box fr">
							 <div class="list-price-box mt10">￥300.00</div>
							 <a class="atc-btn fr mt20" href="product/shoppingCart.htm">添加到购物车</a>
							 <a class="bin-btn fr mt10" href="product/confirmOrder.htm">立即购买</a>
						</div>
					</li>
					<li class="clb">
						<div class="fl pr l-img-box">
							<div class="sc-ico none"></div>
							<div class="product-list-img">
								<img src="static/front/css/images/goods-img.png">
							</div>
						</div>
						<div class="product-content-box fl">
							<div class="list-goods-name">
								<a href="#">${dv:e("芡欧鼠尾草种子",70)}</a>
							</div>
							<div class="list-goods-detail">
								${dv:e("健康芡欧鼠尾草种子是一个易于消化的蛋白质来源,omega - 3脂肪酸,纤维和维生素b .芡欧鼠尾草种子是一个古老的超级食物,
								被认为是玛雅人,阿兹台克和印加作为他们的健康的医学医学和珍贵的增强特性。",220)}
							</div>
							<div class="mt15 clb">
								<div class="pp-box fl">品牌：妞乐</div>
								<div class="address-box fl ml50">产地：新西兰</div>
								<div class="xl-box fl ml50">销量：200</div>
							</div>
						</div>
						<div class="list-cz-box fr">
							 <div class="list-price-box mt10">￥300.00</div>
							 <a class="atc-btn fr mt20" href="product/shoppingCart.htm">添加到购物车</a>
							 <a class="bin-btn fr mt10" href="product/confirmOrder.htm">立即购买</a>
						</div>
					</li>
					<li class="clb">
						<div class="fl pr l-img-box">
							<div class="sc-ico none"></div>
							<div class="product-list-img">
								<img src="static/front/css/images/goods-img.png">
							</div>
						</div>
						<div class="product-content-box fl">
							<div class="list-goods-name">
								<a href="#">${dv:e("芡欧鼠尾草种子",70)}</a>
							</div>
							<div class="list-goods-detail">
								${dv:e("健康芡欧鼠尾草种子是一个易于消化的蛋白质来源,omega - 3脂肪酸,纤维和维生素b .芡欧鼠尾草种子是一个古老的超级食物,
								被认为是玛雅人,阿兹台克和印加作为他们的健康的医学医学和珍贵的增强特性。",220)}
							</div>
							<div class="mt15 clb">
								<div class="pp-box fl">品牌：妞乐</div>
								<div class="address-box fl ml50">产地：新西兰</div>
								<div class="xl-box fl ml50">销量：200</div>
							</div>
						</div>
						<div class="list-cz-box fr">
							 <div class="list-price-box mt10">￥300.00</div>
							 <a class="atc-btn fr mt20" href="product/shoppingCart.htm">添加到购物车</a>
							 <a class="bin-btn fr mt10" href="product/confirmOrder.htm">立即购买</a>
						</div>
					</li> --%>
				</ul>
				
				<div class="pagination mt20">
				    <ul class="fl">
					    <li><a href="#">《</a></li>
					    <li><a href="#">1</a></li>
					    <li><a href="#">2</a></li>
					    <li><a href="#">3</a></li>
					    <li><a href="#">4</a></li>
					    <li><a href="#">5</a></li>
					    <li><a href="#">》</a></li>
				    </ul>
				    <div class="fl go-page-box ml20">
				    	<label class="vm">总共33页，跳转至</label>
				    	<input type="text" class="gpb-text w40 ml10 vm">
				    	<a href="#" class="gpb-btn ml10 vm">Go</a>
				    </div>
				    <div class="cb"></div>
				</div>
				
			</div>
		</div>
		<script type="text/javascript">
			$(function(){
				$(".l-img-box").hover(function(){
					var divSec = $(this).children(".sc-ico");
					$(divSec).removeClass("none");
				},function(){
					var divSec = $(this).children(".sc-ico");
					$(divSec).addClass("none");
				});
			});
		</script>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot.jsp"></jsp:include>
		<%--底部end--%>
  	</body>
</html>
