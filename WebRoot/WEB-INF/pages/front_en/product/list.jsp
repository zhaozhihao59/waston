<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
		<meta name="baidu-site-verification" content="k66pxbcp2Z" />
		<title>${categoryAndBrandName } - 澳新邮易购 - 100%新西兰与澳大利亚直邮商品</title>
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
		<jsp:include page="/WEB-INF/pages/front/comm/add_cart-box.jsp"></jsp:include>
		<div class="w1200-mg-auto clb">
			<div class="fl">
				<%--左边--%>
				<jsp:include page="/WEB-INF/pages/front/comm/left-menu.jsp"></jsp:include>
				<div class="cb"></div>
				<h1 style="font-size:14px;color:#000;font-weight:bold;">热销商品</h1>
				<div>
					<ul class="drb3-ul" style="width:150px;">
						<c:forEach items="${sellWellProducts}" var="product">
							<li style="width:150px;border:none;">
								<dl>
									<dt>
										<a href="product/detail.htm?id=${product.id }"><img src="${product.imageUrl}"></a>
									</dt>
									<dd>
										<div class="mt5"><a href="product/detail.htm?id=${product.id }">${product.name}</a></div>
										<div class="mt5">
											￥${product.unitPrice}
										</div>
									</dd>
								</dl>
							</li>
						</c:forEach>
					</ul>
				</div>
				<%--左边end--%>
			</div>
			<div class="list-right-box fr">
				<div class="list-nav-box">
					<a href="#">首页</a>
					<label>&nbsp;&gt;&nbsp;</label>
					<label>${categoryAndBrandName}</label>
				</div>
				<div class="list-gg-box">
					<a href="#"><img src="static/front/css/images/list-gg.png" class="vm"></a>
				</div>
				
				<%--<table class="lis-type-table">
				<c:forEach items="${productCategoryAttrList }" var="proAttr">
					<tr>
						<td class="gray-bg-td tc" width="115px">${proAttr.lineAttrNameCn }</td>
						
						<td class="list-type-td">
							<a href="javascript:void(0)" class="cur">全部</a>
							<c:forEach items="${proAttr.proCatAttrValList }" var="proAttrVal">
						 		<a href="javascript:void(0)">${proAttrVal.lineAttrvalNameCn }</a>
						 	</c:forEach>
						</td>
						
					</tr>
				</c:forEach>
				</table>--%>
				<form id="form" method="post" action="product/list.htm">
					<input id="category" name="condition.categoryId" value="${condition.categoryId}" type="hidden"/>
					<input id="brandId" name="condition.brandId" value="${condition.brandId}" type="hidden"/>
					<div class="list-search-box clb">
						<div class="fl mt8">
							<input id="sort" name="condition.sort" class="list-text w120 vm" type="hidden">
							<a id="priceSort" class="sb-price vm ml10" href="javascript:void(0)">价格</a>
							<a id="salesSort" class="sb-price vm ml5" href="javascript:void(0)">销量</a>
							<label class="ml15 vm">关键词：</label>
							<input name="condition.name" class="list-text w120 vm" type="text"/>
							<label class="ml15 vm">价格：</label>
							<input id="minPrice" name="condition.minPrice" class="list-text w40 vm" type="text"/>
							<label class="vm">-</label>
							<input id="maxPrice" name="condition.maxPrice" class="list-text w40 vm" type="text"/>
							<a id="search" class="sb-search-btn ml5 vm" href="javascript:void(0)">搜索</a>
						</div>		
						<div class="fr mt8 mr10">
							<label class="ml50 vm">1<c:if test="${pageResult.lastPage > 1}">-${pageResult.lastPage}</c:if> 页 ${fn:length(productList) * pageResult.lastPage}件商品</label>
							<a id="upPage" class="sb-search-btn ml20 vm" href="javascript:void(0)">上一页</a>
							<a id="downPage" class="sb-search-btn ml5 vm" href="javascript:void(0)">下一页</a>
						</div>	
					</div>
				</form>
				
				<ul class="product-list-ul">
				
				<c:forEach items="${productList }" var="prod">
					<li class="clb">
						<div class="fl pr l-img-box">
							<div class="product-list-img">
								<img src="${prod.imageUrl }" width="128" height="128"/>
							</div>
						</div>
						<div class="product-content-box fl">
							<div class="list-goods-name">
								<a href="product/detail.htm?id=${prod.id }&categoryAndBrandName=${categoryAndBrandName}">${dv:e(prod.name,70)}</a>
							</div>
							<div class="list-goods-detail">
								${dv:e(prod.shortDesc,220)}
							</div>
							<div class="mt15 clb">
								<div class="pp-box fl">品牌：${prod.brandName }</div>
								<c:if test="${not empty prod.origin}">
									<div class="address-box fl ml50">产地：${prod.origin}</div>
								</c:if>
								<div class="xl-box fl ml50">销量：${prod.sales}</div>
							</div>
						</div>
						<div class="list-cz-box fr">
							 <div class="list-price-box mt10">￥${prod.unitPrice}</div>
							 <input type="hidden" value="${prod.id }"/>
							 <a class="atc-btn fr mt20 J-addCart" href="javascript:void(0);">添加到购物车</a>
							 <a class="bin-btn fr mt10" href="member/order/sell/buy.htm?productId=${prod.id}&num=1">立即购买</a>
						</div>
					</li>
				</c:forEach>
				</ul>
				<input id="url" value="product/list.htm?brandId=${condition.brandId }&categoryId=${condition.categoryId}" type="hidden"/>
				<div class="pagination mt20 J_loadPaging">
					 <ul></ul>
					 <input class="page_type" value="0" type="hidden">
					 <input class="page_n" value="5" type="hidden">
					 <input class="page_url" value="product/list.htm?brandId=${condition.brandId }&categoryId=${condition.categoryId}" type="hidden">
					 <input id="currentPage" class="page_thisPage" value="${pageResult.currentPage }" type="hidden">
					 <input class="page_lastPage" value="${pageResult.lastPage }" type="hidden">
				</div>
			 	<input id="allPages" value="${pageResult.allPages }" type="hidden">
			</div>
		</div>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot.jsp"></jsp:include>
		<%--底部end--%>
		
		<script src="static/base/lib/jquery.formatCurrency-1.4.0.pack.js" type="text/javascript"></script>
		<script type="text/javascript" src="static/front/js/page.js"></script>
		<script type="text/javascript" src="static/front/product/js/product_list.js"></script>
  	</body>
</html>
