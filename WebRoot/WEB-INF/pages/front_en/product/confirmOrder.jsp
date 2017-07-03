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
		<title>Trademonster - confirmOrder</title>
		<meta name="Keywords" content="" />
		<meta name="description" content="" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
	</head>
  	<body>
  		<%--头部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header3.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/pages/front/comm/address-box.jsp"></jsp:include>
		<%--头部end--%>
		<div class="shopping-cart-box">
			<div class="shopping-step2"></div>
			<div class="step2-address-box"></div>
			<div class="step2-address-cc-box clb">
				<div class="front-form-item">
					<span class="front-item-title w130">Contact Name :</span>
					<div class="front-item-cc">shaocaile</div>
				</div>
				<div class="front-form-item">
					<span class="front-item-title w130">Address Line :</span>
					<div class="front-item-cc">Shanghai YangPu GuoDingRoad HanDanRoad 335</div>
				</div>
				<div class="front-form-item">
					<span class="front-item-title w130">State/Province :</span>
					<div class="front-item-cc">Shanghai</div>
				</div>
				<div class="front-form-item">
					<span class="front-item-title w130">City :</span>
					<div class="front-item-cc">Shanghai</div>
				</div>
				<div class="front-form-item">
					<span class="front-item-title w130">Zip/Postal Code :</span>
					<div class="front-item-cc">355002</div>
				</div>
				<div class="front-form-item">
					<span class="front-item-title w130">Mobile No./ Phone :</span>
					<div class="front-item-cc">13162755101</div>
				</div>
				<div class="cz-btn-box">
					<a class="atc-btn fl" href="javascript:void(0);" id="add-address">Add  Address</a>
					<a class="bin-btn fl ml10 xsbtn" href="javascript:void(0);">Choose other</a>
				</div>
			</div>
			
			
			<div class="step2-address-cc-box2 clb none">
				<div class="front-form-item">
					<span class="front-item-title w130"><label class="red-xx">*</label>Contact Name :</span>
					<input type="text" class="address-text w250">
				</div>
				<div class="front-form-item">
					<span class="front-item-title w130"><label class="red-xx">*</label>Address Line :</span>
					<input type="text" class="address-text w600">
				</div>
				<div class="front-form-item">
					<span class="front-item-title w130"><label class="red-xx">*</label>State/Province :</span>
					<div class="select_div w150">
						<select class="w" style="height:17px;"></select>
					</div>
				</div>
				<div class="front-form-item">
					<span class="front-item-title w130"><label class="red-xx">*</label>City :</span>
					<div class="select_div w150">
						<select class="w" style="height:17px;"></select>
					</div>
				</div>
				<div class="front-form-item">
					<span class="front-item-title w130"><label class="red-xx">*</label>Zip/Postal Code :</span>
					<input type="text" class="address-text w250">
				</div>
				<div class="front-form-item">
					<span class="front-item-title w130">Mobile No./ Phone :</span>
					<input type="text" class="address-text w80">
					<input type="text" class="address-text w250">
				</div>
				<div class="front-form-item" style="margin-top:20px;">
					<span class="front-item-title w130"></span>
					<a href="#" class="co-save-btn fl" style="margin:5px 0 0 10px;">Save</a>
					<a href="javascript:void(0);" class="co-close-btn fl" style="margin:5px 0 0 10px;">Close</a>
				</div>
			</div>
			
			
			<script>
				$(function(){
					$("#add-address").click(function(){
						$(".step2-address-cc-box").hide();
						$(".step2-address-cc-box2").fadeIn();
					});
					$(".co-close-btn").click(function(){
						$(".step2-address-cc-box").fadeIn();
						$(".step2-address-cc-box2").hide();
					});
				});
			</script>
			
			
			
			
			<div class="step2-od-box"></div>
			<ul class="sc-list-ul" style="margin-top:10px;">
				<li class="clb">
					<div class="fl">
						<div class="sc-img-box">
							<a href="#"><img src="static/front/css/images/gl-img2.png"></a>
						</div>
					</div>
					<div class="stb-cc-box fr">
						<table class="stb-table">
							<tr>
								<td width="645px">
									<div class="stb1-box">
										<a href="#">${dv:e("Wholesale - S5 5.1 Inch 1:1 i9600 Quad Core MTK6582 Android 4.4 USB 3.0 1.3GHZ Air Gesture 3G Dual Camera Cell Phone Smartphone 1280*1920",180)}</a> 
									</div>
								</td>
								<td width="130px" class="tc">
									<div class="stb1-price">$0.68/Piece</div>
								</td>
								<td width="70px" class="tc">
									<input type="text" class="spb-text vm">
								</td>
								<td width="40px">
									pieces
								</td>
								<td width="120px" class="tc">
									<div class="stb1-price-box" style="text-align:right;">$142.80</div>
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
										<div class="fr mr5">Shipping Cost:US $72.66</div>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<div class="stb2-textarea-box" style="width:1055px;">
										<textarea class="stb2-textarea"></textarea>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</li>
				<li class="clb">
					<div class="fl">
						<div class="sc-img-box">
							<a href="#"><img src="static/front/css/images/gl-img2.png"></a>
						</div>
					</div>
					<div class="stb-cc-box fr">
						<table class="stb-table">
							<tr>
								<td width="645px">
									<div class="stb1-box">
										<a href="#">${dv:e("Wholesale - S5 5.1 Inch 1:1 i9600 Quad Core MTK6582 Android 4.4 USB 3.0 1.3GHZ Air Gesture 3G Dual Camera Cell Phone Smartphone 1280*1920",180)}</a> 
									</div>
								</td>
								<td width="130px" class="tc">
									<div class="stb1-price">$0.68/Piece</div>
								</td>
								<td width="70px" class="tc">
									<input type="text" class="spb-text vm">
								</td>
								<td width="40px">
									pieces
								</td>
								<td width="120px" class="tc">
									<div class="stb1-price-box" style="text-align:right;">$142.80</div>
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
										<div class="fr mr5">Shipping Cost:US $72.66</div>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<div class="stb2-textarea-box" style="width:1055px;">
										<textarea class="stb2-textarea"></textarea>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</li>
				<li class="clb">
					<div class="fl">
						<div class="sc-img-box">
							<a href="#"><img src="static/front/css/images/gl-img2.png"></a>
						</div>
					</div>
					<div class="stb-cc-box fr">
						<table class="stb-table">
							<tr>
								<td width="645px">
									<div class="stb1-box">
										<a href="#">${dv:e("Wholesale - S5 5.1 Inch 1:1 i9600 Quad Core MTK6582 Android 4.4 USB 3.0 1.3GHZ Air Gesture 3G Dual Camera Cell Phone Smartphone 1280*1920",180)}</a> 
									</div>
								</td>
								<td width="130px" class="tc">
									<div class="stb1-price">$0.68/Piece</div>
								</td>
								<td width="70px" class="tc">
									<input type="text" class="spb-text vm">
								</td>
								<td width="40px">
									pieces
								</td>
								<td width="120px" class="tc">
									<div class="stb1-price-box" style="text-align:right;">$142.80</div>
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
										<div class="fr mr5">Shipping Cost:US $72.66</div>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<div class="stb2-textarea-box" style="width:1055px;">
										<textarea class="stb2-textarea"></textarea>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</li>
			</ul>
			<div class="grand-total-box">
				<span>Grand Total:</span>
				<label>US $ 8185.83</label>
			</div>
			<div class="fr mt30">
				<a href="product/toPay.htm" class="ptp-btn ml10">Proceed to Payment</a>
			</div>
			<div class="cb"></div>
		</div>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
		
  	</body>
</html>
