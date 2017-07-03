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
		<title>Trademonster - toPay</title>
		<meta name="Keywords" content="" />
		<meta name="description" content="" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
	</head>
  	<body>
  		<%--头部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header3.jsp"></jsp:include>
		<%--头部end--%>
		<div class="shopping-cart-box">
			<div class="topay-fbox">
				<div class="tf1-box">Your order has been placed successfully. Please pay for your order.</div>
				<div class="tf2-box mt10">
					<label>Order No.: 1656598895  |  The payable amount: NZD$34</label>
					<span class="ml20">Please pay within 24 hours after submitting the order, otherwise orders will be cancelled.</span>
				</div>
			</div>
			
			<div class="pay-to-box">
				<div class="pay-buy-box">
					Pay by<img src="static/front/css/images/visa-img.png" class="vm m10">
				</div>
				<div class="pay-to-tipbox">
					Your card number is kept 100% secure and only used for this transaction. Trademnster will never reveal your card details to any third party. Our payment system isPCI-DSS Level 1 compliant, which means
					 entire payment process follows the highest security standards and is the equivalent of bank-grade infrastructure.If encounting any issues during payment process, please refer to our credit card payment 
					troubleshooting. .
				</div>
				
				
				<div class="step2-address-cc-box2 clb" style="margin-top:40px;">
					<div class="should-pay">
						<span>You should pay</span> <label>USD 109.79</label>
					</div>
					<div class="front-form-item" style="margin-top:10px;">
						<span class="front-item-title w130"><label class="red-xx">*</label>Card Number :</span>
						<input type="text" class="address-text w250">
					</div>
					<div class="front-form-item">
						<span class="front-item-title w130"><label class="red-xx">*</label>Expiration Date :</span>
						<input type="text" class="address-text w40 vm">
						<label class="vm ml5">/</label>
						<input type="text" class="address-text w40 vm">
					</div>
					<div class="front-form-item">
						<span class="front-item-title w130"><img src="static/front/css/images/csc.png" class="vm"><label class="red-xx ml5">*</label>CSC :</span>
						<input type="text" class="address-text w40 vm">
						<a href="#"><img src="static/front/css/images/wh-ico.png" class="ml5 vm"></a>
					</div>
					<div class="front-form-item">
						<span class="front-item-title w130 fb">Billing Address:</span>
						<div class="front-item-cc">
							<a href="#">Edit</a>
						</div>
					</div>
					<div class="front-form-item">
						<span class="front-item-title w130">Contact Name:</span>
						<div class="front-item-cc">
							sss
						</div>
					</div>
					<div class="front-form-item">
						<span class="front-item-title w130">Postcode/zip:</span>
						<div class="front-item-cc">
							11111111111
						</div>
					</div>
					<div class="front-form-item">
						<span class="front-item-title w130">Phone Number:</span>
						<div class="front-item-cc">
							11111111111
						</div>
					</div>
					<div class="front-form-item">
						<span class="front-item-title w130">Address:</span>
						<div class="front-item-cc">
							2sssssss
						</div>
					</div>
					<div class="front-form-item" style="margin-top:10px;">
						<span class="front-item-title w130"></span>
						<a href="#" class="ptp-btn fl ml15">Pay for order</a>
					</div>
				</div>
				
				
				
				
			</div>
			
		</div>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
  	</body>
</html>
