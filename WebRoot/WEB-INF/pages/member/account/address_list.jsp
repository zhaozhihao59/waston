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
		<title>澳新邮易购 - 收货地址</title>
		<meta name="Keywords" content="新西兰直邮商品,澳大利亚代购,新西兰代购" />
		<meta name="description" content="澳新邮易购 - 100%新西兰直邮商品,购买新西兰产品最优选择。" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
	</head>
		<script type="text/javascript">
  			var provinceList = <%=application.getAttribute("provinceList")%>;
  			var provinceCity = "${item.provinceCity}"
		</script>
  	<body>
  		<%--头部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<%--头部end--%>
		<div class="member-center-mmbox">
			<jsp:include page="/WEB-INF/pages/member/comm/member_left.jsp">
				<jsp:param name="menu" value="4" />
			</jsp:include>
			
			<div class="mcm-right-box fr pr">
				<div class="mrb-wel-font">收货地址</div>
				<div class="add-ags">
				</div>
				
				<form id="accountForm" action="member/address/doSave.htm" method="post">
				<input id="addressStatus" name="item.addressStatus" value="${item.addressStatus }" type="hidden">
				<input id="itemId" name="item.id" value="${item.id }" type="hidden">
				
					<div class="step2-address-cc-box2 clb">
					<div class="front-form-item">
						<span class="front-item-title w130"><label class="red-xx">*</label>收货人：</span>
						<input id="linkman" name="item.linkman" value="${item.linkman}" type="text" class="address-text w250 fl">
						<span id="linkmanTip"></span>
					</div>
					<div class="front-form-item">
						<span class="front-item-title w130"><label class="red-xx">*</label>省市：</span>
						<%-- <div class="select_div w120 fl">
							<select class="w"></select>
						</div>
						<div class="select_div fl" style="width:115px;">
							<select class="w"></select>
						</div> --%>
						<input id="addressInput" name="item.provinceCity" value="${item.provinceCity }" type="hidden" />
									<div class="select_div fl mr2">
										<input id="provinceCode" name="item.provinceId" type="hidden">
										<input id="provinces" name="item.province" type="hidden" value="<s:property value='item.province'/>">
										<select id="province" class="all-select w100 fl">
											<option value="-1">请选择省份</option>
										</select>
									</div>
									<div class="select_div fl mr2">
										<input id="cityCode" name="item.cityId" type="hidden">	
										<input id="citys" name="item.city" type="hidden" value="<s:property value='item.city'/>">
										<select id="city" class="all-select w100 fl">
											<option value="-1">请选择城市</option>
										</select>
									</div>
									<div class="select_div fl">
										<input id="addressCode" name="item.districtId" type="hidden">	
										<input id="districts" name="item.district" type="hidden" value="<s:property value='item.district'/>">
										<select id="codeAddress" class="all-select w100 fl">
											<option value="-1">请选择区县</option>
										</select>
									</div>
					</div>
					<div class="front-form-item">
						<span class="front-item-title w130"><label class="red-xx">*</label>街道地址：</span>
						<textarea id="address" name="item.address"  class="address-textarea ml10 mt5 fl mb10">${item.address}</textarea>
						<span id="addressTip"></span>
					</div>
					<div class="front-form-item">
						<span class="front-item-title w130"><label class="red-xx">*</label>邮政编码：</span>
						<input id="postCode" name="item.postCode" value="${item.postCode}" type="text" class="address-text w250 fl">
						<span id="postCodeTip"></span>
					</div>
					<div class="front-form-item">
						<span class="front-item-title w130"><label class="red-xx">*</label>电话/手机：</span>
						<input id="mobile" name="item.mobile" value="${item.mobile }"  type="text" class="address-text w250 fl">
						<span id="mobileTip"></span>
					</div>
					
					<div class="front-form-item" style="margin-top:20px;">
						<span class="front-item-title w130"></span>
						<a id="doSaveSubmit" href="javascript:void(0);"  class="co-save-btn fl" style="margin:5px 0 0 10px;">保存</a>
					</div>
				</div>
			</form>
				
				
				
			<table class="address-table" style="width:950px;">
				<tr class="at-tt-tr">
					<td width="40px" class="tc"><input id="all" type="checkbox"></td>
					<td width="80px" class="tc">收货人</td>
					<td width="80px" class="tc">所在地区/邮编</td>
					<td width="300px" class="tl">街道地址</td>
					<td width="155px" class="tc">电话/手机</td>
					<td width="115px" class="tc">操作</td>
				</tr>
					<s:iterator value="pageResult.result" var="address"> 
						<tr class="at-cc-tr">
							<td class="tc">
								<input name="checkbox1" type="checkbox" value="${id }">
							</td>
							<td class="tc">
								${linkman }
							</td>
							<td class="tc w200"> 
								${provinceCity }</br><span>${postCode }</span>
							</td>
							<td class="tl addr-dbxo clb">
								${address }
								<s:if test="#address.addressStatus == 1">
									<span class="ml15 ada fr " style="color:#ea5514;"> 默认地址</span>
								</s:if>
								<s:if test="#address.addressStatus == 0">
									<a defId="${id}" href="javascript:void(0);" class="ml15 ada defaultAddress fr">设为默认地址</a>
								</s:if>
							</td>
							<td class="tc">
								${mobile }
							</td>
							<td class="tc">
								<a href="member/address/address_list.htm?item.id=${id }">修改</a>
								<label>|</label>
								<a href="member/address/del.htm?selIds=${id }">删除</a>
							</td>
						</tr>
					</s:iterator>
			</table>
			<div class="clb mt20">
				<div class="fl">
					<a id="del" href="javascript:void(0);" class="co-save-btn">批量删除</a>
				</div>
<!-- 				<div class="pagination" style="float:right;">
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
				    	<label class="vm">共5页 跳转至</label>
				    	<input type="text" class="gpb-text w40 ml10 vm">
				    	<a href="#" class="gpb-btn ml10 vm">Go</a>
				    </div>
				    <div class="cb"></div>
				</div> -->
				<%-- 分页 --%>
					    <c:if test="${not empty pageResult.result and pageResult.allPages gt 0}">
							<div class="pagination mt20">
								<ul>
									<c:if test="${pageResult.currentPage>1}">
										<li>
											<a href="member/address/address_list.htm" title="首页">&lt;&lt;</a>
										</li>
										<li>
											<a href="member/address/address_list.htm?pageResult.currentPage=${pageResult.prePage}" title="上一页">&lt;</a>
										</li>
									</c:if>
									<c:forEach begin="${pageResult.pageBar[0]}" end="${pageResult.pageBar[1]}" varStatus="status">
										<c:if test="${pageResult.currentPage eq status.index}">
											<li>
												<a href="member/address/address_list.htm?pageResult.currentPage=${status.index}" class="cur">${status.index }</a>
											</li>
										</c:if>
										<c:if test="${pageResult.currentPage != status.index}">
											<li>
												<a href="member/address/address_list.htm?pageResult.currentPage=${status.index}">${status.index }</a>
											</li>
										</c:if>
								</c:forEach>
								<c:if test="${pageResult.currentPage lt pageResult.allPages}">
									<li>
										<a href="member/address/address_list.htm?pageResult.currentPage=${pageResult.nextPage}" title="下一页">&gt;</a>
									</li>
									<li>
										<a href="member/address/address_list.htm?pageResult.currentPage=${pageResult.lastPage}" title="尾页">&gt;&gt;</a>
									</li>
								</c:if>
							</ul>
						</div>
					</c:if>
			</div>
			
		</div>	
			<div class="cb"></div>
		</div>
		<%--底部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot2.jsp"></jsp:include>
		<%--底部end--%>
  </body>
   <script src="static/member/account/address_list.js"></script>
</html>
