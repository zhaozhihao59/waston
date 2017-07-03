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
		<title>${product.name } - 澳新邮易购 - 100%新西兰与澳大利亚直邮商品</title>
		<meta name="Keywords" content="" />
		<meta name="description" content="" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
		<script type="text/javascript" src="static/front/js/fdj/jquery.jqzoom.js"></script>
		<script type="text/javascript" src="static/front/js/fdj/fdj.js"></script>
		<link href="static/front/css/fdj.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="static/front/css/lethe.css" />
		<script src="static/base/lib/artdialog/jquery.artDialog.source.js?t=${sysVersion}&skin=default"></script>
		<script src="static/base/lib/artdialog/plugins/iframeTools.source.js?t=${sysVersion}"></script>
	</head>
  	<body>
  		<%--头部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<%--头部end--%>
		<jsp:include page="/WEB-INF/pages/front/comm/add_cart-box.jsp"></jsp:include>
		<div class="w1200-mg-auto">
			<div class="deail-nav-box">
				<input id="flag" value="${flag}" type="hidden"/>
				<input id="categoryAndBrandName" value="${categoryAndBrandName}" type="hidden"/>
				<label>首页</label>
				<c:if test="${not empty categoryAndBrandName}">
					<label>&nbsp;&gt;&nbsp;</label>
					<label>${categoryAndBrandName}</label>
				</c:if>
				<label>&nbsp;&gt;&nbsp;</label>
				<label class="fb">${product.name}</label>
			</div>
		
			<div class="detail-left-box fl">
				<div class="fl">
					<div id="preview" class="spec-preview">
						<span class="jqzoom"><img jqimg="${product.imageUrl }" src="${product.imageUrl }" /></span>
					</div>
			        <!--缩图开始-->
			        <div class="spec-scroll">
			        	<a class="prev">&lt;</a>
			            <a class="next">&gt;</a>
			            <div class="items">
			            	<ul>
			            		<li><img alt="${product.name}" bimg="${product.imageUrl }" src="${product.imageUrl}" class="cur" onmousemove="preview(this);"></li>
			            		<c:forEach items="${sysUploadFiles}" var="sysUploadFile">
			            			<li><img alt="${sysUploadFile.name}" bimg="${sysUploadFile.url}" src="${sysUploadFile.url}" onmousemove="preview(this);"></li>
			                	</c:forEach>
			               </ul>
			            </div>
			        </div>
				</div>
				
				<div class="detail-content-box fr">
					<input id="productId" value="${product.id }" type="hidden"/>
					<input id="productSkuId" type="hidden"/>
					<div class="clb mt10">
						<div class="dcb-tt-box fl">${product.name }</div>
					</div>
					<div class="dcb-l2-box"> ${product.nameEn }</div>
					<div class="pl-box mt10">
						<div class="fl gray-xx-box">
							<div style="width:${markSum/(fn:length(productComments) * pcPageResult.lastPage)*15}px" class="og-xx-box"></div>
						</div>
						<label class="vm ml10">${fn:length(productComments) * pcPageResult.lastPage}条评论</label>
					</div>
					<ul class="dcb-cs-ul clb">
					<c:forEach items="${product.prodAttrList }" var="proAttr">
						<c:forEach items="${proAttr.prodAttrValueList }" var="proAttrValue">
							<c:if test="${not empty proAttrValue.attrName}">
								<li>${proAttrValue.attrName }：${proAttrValue.lineAttrvalNameCn }</li>
							</c:if>
						</c:forEach>
					</c:forEach>
<!-- 						<li>商品规格：30ml</li> -->
<!-- 						<li>邮寄重量：198g</li> -->
						<li>产品品牌：${productBrand.name }</li>
<!-- 						<li>产地：新西兰</li> -->
					</ul>
					<div class="ml10">
						<div class="mt20 clb">
							<div class="total-price-box fl">
								￥${product.unitPrice}
							</div>
							<div class="dcb-jj-box fl">
								<ul class="dcb-jj-ul">
									<li><a id="minusNum" class="change-num" href="javascript:void(0);"><img src="static/front/css/images/j-bg.png"></a></li>
									<li>
										<input id="cartNum" value="1" type="text" class="aj-input"/>
									</li>
									<li><a id="addNum" class="change-num" href="javascript:void(0);"><img src="static/front/css/images/ad-bg.png"></a></li>
								</ul>
							</div>
						</div>
						<div class="cb"></div>
						<div class="detail-cd-btn mt20">
							<a class="atc-btn2 fl J-addCart" href="javascript:void(0);">添加到购物车</a>
							<a class="bin-btn2 fl ml10" onclick="buy('${product.id}')" href="javascript:void(0);">立即购买</a>
						</div>
						<div class="cb"></div>
						<div class="fxd-font">分享到：</div>
						<div class="mt10">
							<!-- 自定义分享图标 -->
							<jsp:include page="/WEB-INF/pages/front/comm/fx-ico.jsp"></jsp:include>
							<!-- 自定义分享图标 END -->
						</div>
						<div class="mt20">
							<img src="static/front/css/images/hzhb-bg.png">
						</div>
					</div>
				</div>
				<div class="cb"></div>
				<div class="detail-nav-box">
					<ul class="dnb-ul">
						<li class="cur">产品介绍</li>
						<li style="margin-left:-1px;">使用评价</li>
					</ul>
				</div>
				
				<div class="dnb-content-box" style="padding:25px;">
					${product.htmlContent }
				</div>
				<div class="dnb-content-box">
					<div class="dnb-pf-box">
						<div>
							<div id="star" class="fl">
								<span>评分：</span>
								<ul>
									<li><a href="javascript:;">1</a></li>
									<li><a href="javascript:;">2</a></li>
									<li><a href="javascript:;">3</a></li>
									<li><a href="javascript:;">4</a></li>
									<li><a href="javascript:;">5</a></li>
								</ul>
								<span></span>
								<input id="mark" type="hidden"/>
								<p></p>
							</div>
							<div class="fr t-num-font">
								您还可以填写<label id="charNum">225</label>字
							</div>
							<div class="cb"></div>
						</div>
						<div class="dnb-textarea-box mt15">
							<textarea id="content" maxlength="255" onkeyup="changeCharNum()"></textarea>
						</div>
						<div id="errorlist" style="color:red;"></div>
						<a href="javascript:void(0)" onclick="publish()" class="bin-btn2 fr mt15 mr10">发布</a>
						<div class="cb"></div>
						
						
						<div class="history-pj-box">
							历史评价记录
						</div>
						
						
						
						<ul class="hpb-ul">
							<c:forEach items="${productComments}" var="comment">
								<li>
									<div>
										<div class="fl">${comment.memberName}</div>
										<div class="fl gray-xx-box">
											<div style="width:${comment.mark * 15}px" class="og-xx-box"></div>
										</div>
										<div class="fl">
											<fmt:formatDate value="${comment.createDate}" pattern="yyyy/MM/dd"/>
										</div>
										<div class="cb"></div>
									</div>
									<div class="hpb-pj-box">
										${comment.content}
									</div>
								</li>
							</c:forEach>
						</ul>
						
						<div class="pagination mt20 J_loadPaging">
							<ul></ul>
							<input class="page_type" value="0" type="hidden">
							<input class="page_n" value="5" type="hidden">
							<input class="page_url" value="product/detail.htm?id=${product.id}&categoryAndBrandName=${categoryAndBrandName}&flag=0" type="hidden">
							<input id="currentPage" class="page_thisPage" value="${pcPageResult.currentPage }" type="hidden">
							<input class="page_lastPage" value="${pcPageResult.lastPage}" type="hidden">
						</div>
						<input id="allPages" value="${pcPageResult.allPages }" type="hidden">
					</div>
				</div>
				
				<div class="favourite-goods-box clb">
					<div class="fl">
						您可能对下列产品感兴趣
					</div>
					<div class="fr">
						<a href="javascript:void(0);" class="lback-btn fl"></a>
						<a href="javascript:void(0);" class="lnext-btn fl ml5"></a>
					</div>
				</div>
				
				<ul class="favourite-goods-ul clb">
					<c:forEach items="${similarProduct}" var="pro" end="4">
						<c:if test="${pro.id != product.id }">
							<li>
								<div class="fgoods-img-box">
									<a href="product/detail.htm?id=${pro.id }"><img src="${pro.imageUrl}"></a>
								</div>
								<div class="fg-tt-box">
									<div class="fg-tt-font"><a href="product/detail.htm?id=${pro.id }" title="">${dv:e(pro.shortDesc,60)}</a></div>
									<div class="fg-price-col">￥${pro.unitPrice}</div>
								</div>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="detail-right-box fr">
				<div class="drb1-box">
					<div class="drb1-logo">
						<a href="product/list.htm?brandId=${productBrand.id}"><img width="228" height="88" src="${productBrand.imagePath}"></a>
					</div>
					<div class="ml5">
						<div class="drb1-tt">澳新邮易购服务保障</div>
						<div class="clb mt15 pr">
							<div class="jh-ico-bg"></div>
							<div class="fl index-ico tips-ps01 pl10">直邮商品</div>
							<div class="fl index-ico tips-ps02 pl10 ml40">按时发货</div>
							<div class="fl index-ico tips-ps03 pl10">按时到货</div>
							<div class="fl index-ico tips-ps04 pl10 ml40">送货上门</div>
						</div>
					</div>
				</div>
				<div class="drb2-box">
					<div class="drb2-tt">100% 正品保证</div>
					<c:forEach items="${foot_cmsChannels}" var="cmsChannel">
						<c:if test="${cmsChannel.isRight == 1}">
							<c:forEach items="${cmsChannel.cmsArticles}" var="cmsArticle">
								<div class="mt15 drb2-cc">
									${dv:e(cmsArticle.summary,120)}
								</div>
								<a href="cms/cmsDetail.htm?id=${cmsArticle.id}" class="rel-more">了解更多</a>
							</c:forEach>
						</c:if>
					</c:forEach>
				</div>
				
				<div class="drb3-box">
					<div class="drb3-tt">
						<div class="drb3-line-box">热销产品</div>
					</div>
					<ul class="drb3-ul">
						<c:forEach items="${sellWellProducts}" var="product">
							<li>
								<dl>
									<dt><a href="product/detail.htm?id=${product.id }"><img src="${product.imageUrl}"></a></dt>
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
			</div>
		</div>
		<div class="cb"></div>
		<%--底部--%>
			<jsp:include page="/WEB-INF/pages/front/comm/front_foot.jsp"></jsp:include>
		<%--底部end--%>
		<script type="text/javascript" src="static/front/js/page.js"></script>
		<script src="static/base/lib/jquery.formatCurrency-1.4.0.pack.js" type="text/javascript"></script>
		<script type="text/javascript" src="static/front/product/js/product_detail.js"></script>
  	</body>
</html>
