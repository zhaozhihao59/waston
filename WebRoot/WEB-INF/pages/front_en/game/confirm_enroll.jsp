<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dv" uri="/WEB-INF/tld/default_value.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html >
<html >
	<head>
		<base href="${BASE_PATH}" />
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<jsp:include page="/WEB-INF/pages/common/robots.jsp" />
		<meta name="baidu-site-verification" content="k66pxbcp2Z" />
		<title>HEROS - 自行车赛 - 确认报名内容</title>
		<meta name="Keywords" content="自行车赛" />
		<meta name="description" content="自行车赛" />
		<jsp:include page="/WEB-INF/pages/common/front_inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/front/css_v2/common.css" />
		<link rel="stylesheet" type="text/css" href="static/front/css/index.css" />
	</head>
	
	<body style="background:#e9e9e9;">
		<jsp:include page="/WEB-INF/pages/front/comm/front_header.jsp"></jsp:include>
		<div class="game-enroll-box">
			<div class="geb-csbm-tt">
				参赛报名
			</div>
			<div class="geb-step2-box"></div>
			<div class="form-item" style="margin-top:40px;">
				<table>
					<tr class="enroll-tr">
						<td  class="enroll-th tc" style="width:350px;">比赛项目</td>
						<td colspan="3"  class="enroll-th tc" style="width:2000px;">${gameEnroll.gameName}</td>
						<td class="enroll-th tc" style="width:300px;">报名项目</td>
						<td class="enroll-th tc" style="width:600px;">${gameEnroll.gameItemName }</td>
					</tr>
					<!-- 车队 -->
					<c:if test="${gameEnroll.enrollType == 0 }">
						<div >
							<tr class="enroll-tr">
								<td class="enroll-th tc" style="width:300px;">姓名</td>
								<td class="enroll-th tc" style="width:300px;">${gameEnroll.memberName }</td>
								<td class="enroll-th tc" style="width:300px;">性别</td>
								<td class="enroll-th tc" style="width:300px;">
									<s:if test="gameEnroll.sex == 1">男</s:if>
									<s:if test="gameEnroll.sex == 0">女</s:if>
								</td>
								<td class="enroll-th tc" style="width:300px;">国籍</td>
								<td class="enroll-th tc" style="width:300px;">
									<c:forEach items="${nationList}" var="item">
										<c:if test="${gameEnroll.memberNation ==item.id }">${item.name }</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr class="enroll-tr">
								<td class="enroll-th tc" style="width:300px;">证件类型</td>
								<td class="enroll-th tc" style="width:300px;">
									<c:forEach items="${cardTypeList}" var="item">
										<c:if test="${gameEnroll.member.cardType ==item.id }">${item.name }</c:if>
									</c:forEach> 
								</td>
								<td class="enroll-th tc" style="width:300px;">证件号码</td>
								<td class="enroll-th tc" style="width:300px;">${gameEnroll.idCardNo }</td>
								<td class="enroll-th tc" style="width:300px;">手机号码</td>
								<td class="enroll-th tc" style="width:300px;">${gameEnroll.memberPhone }</td>
							</tr>
							<tr class="enroll-tr">
								<td class="enroll-th tc" style="width:300px;">紧急联系人</td>
								<td class="enroll-th tc" style="width:300px;">${gameEnroll.memberLinkName }</td>
								<td class="enroll-th tc" style="width:300px;">紧急联系人电话</td>
								<td class="enroll-th tc" style="width:300px;">${gameEnroll.memberLinkPhone }</td>
								<td class="enroll-th tc" style="width:300px;">紧急联系人关系</td>
								<td class="enroll-th tc" style="width:300px;">
									<c:forEach items="${relationList}" var="item">
											<c:if test="${ gameEnroll.memberLinkRelation  == item.id}">${item.name}</c:if>
									</c:forEach>
								</td>
							</tr>
						</div>
					</c:if>
					<c:if test="${gameEnroll.enrollType ==1 }">
						<tr class="enroll-tr">
							<td class="enroll-th tc" style="width:300px;">经理姓名</td>
							<td class="enroll-th tc" style="width:300px;">${enrollTeam.teamLeaderName }</td>
							<td class="enroll-th tc" style="width:300px;">手机号码</td>
							<td class="enroll-th tc" style="width:300px;">${enrollTeam.teamLeaderPhone }</td>
							<td class="enroll-th tc" style="width:300px;">车队名称</td>
							<td class="enroll-th tc" style="width:300px;">${enrollTeam.teamName }</td>
						</tr>
					</c:if>
					<c:if test="${gameEnroll.enrollType == 0 }">
						<tr class="enroll-tr">
							<td class="enroll-th tc" style="width:300px;">健康注意事项</td>
							<td class="enroll-th tc" style="width:300px;">${gameEnroll.memberHealth }</td>
							<td class="enroll-th tc" style="width:300px;">血型</td>
							<td class="enroll-th tc" style="width:300px;">
									<c:forEach items="${bloodTypeList}" var="item">
											<c:if test="${gameEnroll.memberBloodType == item.id }">${item.name}</c:if>
									</c:forEach>
							</td>
							<td class="enroll-th tc" style="width:300px;">是否当地人</td>
							<td class="enroll-th tc" style="width:300px;">
								<s:if test="gameEnroll.isLocal == 0">否</s:if>
								<s:if test="gameEnroll.isLocal == 1">是</s:if>
							</td>
						</tr>
						<tr class="enroll-tr">
							<td class="enroll-th tc" style="width:300px;">手机号码</td>
							<td class="enroll-th tc" style="width:300px;">${gameEnroll.memberPhone }</td>
						</tr>
					</c:if>
					<tr class="enroll-tr">
						<td class="enroll-th tc" style="width:300px;">报名费</td>
						<td class="enroll-th tc" style="width:300px;">
							<fmt:formatNumber value="${commModel.productAmount/100}" type="currency"/>
						</td>
						<c:if test="${gameEnroll.enrollType ==1 }">
							<td class="enroll-th tc" style="width:300px;">车队成员</td>
						</c:if>
						<td class="enroll-th tc" style="width:300px;"></td>
						<td class="enroll-th tc" style="width:300px;"></td>
						<td class="enroll-th tc" style="width:300px;"></td>
					</tr>
				</table>
			</div>
			<c:if test="${gameEnroll.enrollType ==1 }">
				<div class="table-div ">
						<table id="teamTable" class="enroll-table">
							<tr class="enroll-tr">
								<th class="enroll-th tc" style="width:30px;">序号</th>
								<th class="enroll-th" style="width:72px;">姓名<em>*</em></th>
								<th class="enroll-th" style="width:52px;">性别<em>*</em></th>
								<th class="enroll-th" style="width:102px;">证件类型<em>*</em></th>
								<th class="enroll-th" style="width:172px;">证件号码<em>*</em></th>
								<th class="enroll-th" style="width:112px;">手机号码<em>*</em></th>
								<th class="enroll-th" style="width:172px;">电子邮箱<em>*</em></th>
								<th class="enroll-th" style="width:72px;">国籍<em>*</em></th>
								<th class="enroll-th" style="width:90px;">紧急联系人<em>*</em></th>
								<th class="enroll-th" style="width:112px;">紧急联系人电话<em>*</em></th>
								<th class="enroll-th" style="width:102px;">联系人关系</th>
								<th class="enroll-th" style="width:172px;">健康注意事项</th>
								<th class="enroll-th" style="width:72px;">血型</th>
								<th class="enroll-th" style="width:52px;">当地人</th>
								<th class="enroll-th tr" style="width:72px;">报名费</th>
								<th class="enroll-th" style="width:50px;"></th>
							</tr>
							<!-- 开始循环 -->
							<c:forEach items="${enrollTeam.enrollList }" var="obj" varStatus="index">
							
								<tr class="enroll-tr j-team-member">
									<td class="enroll-td tc j-num">${index.index+1 }</td>
									<td class="enroll-td">
										${obj.memberName}
<!-- 										<input id="name_0" name="enrollTeam.enrollList[0].memberName" class="ui-input-text fl w60 j-name" type="text"/> -->
									</td>
									<td class="enroll-td " >
										<c:if test="${obj.member.sex ==0}">女</c:if>
										<c:if test="${obj.member.sex ==1}">男</c:if>
									</td>
									<td class="enroll-td " >
										<c:forEach items="${cardTypeList}" var="item">
													<c:if test="${gameEnroll.member.cardType ==item.id }">${item.name }</c:if>
												</c:forEach> 
									</td>
									<td class="enroll-td">
										${obj.member.idCardNo }
									</td>
									<td class="enroll-td">
										${obj.memberPhone }
									</td>
									<td class="enroll-td">
										${obj.memberEmail }
									</td>
									<td class="enroll-td">
										<c:forEach items="${nationList}" var="item">
											<c:if test="${gameEnroll.memberNation ==item.id }">${item.name }</c:if>
										</c:forEach>
									</td>
									<td class="enroll-td"> 
										${obj.memberLinkName }
									</td>
									<td class="enroll-td">
										${obj.memberLinkPhone }
									</td>
									<td class="enroll-td">
										<c:forEach items="${relationList}" var="item">
												<c:if test="${ obj.memberLinkRelation  == item.id}">${item.name}</c:if>
										</c:forEach>
									</td>
									<td class="enroll-td">
										${obj.memberHealth }
									</td>
									<td class="enroll-td">
										<c:forEach items="${bloodTypeList}" var="item">
												<c:if test="${obj.memberBloodType == item.id }">${item.name}</c:if>
										</c:forEach>
									</td>
									<td class="enroll-td">
										<c:if test="${obj.isLocal  eq 0}">否</c:if>
										<c:if test="${obj.isLocal  eq 1}">是</c:if>
									</td>
									<td class="enroll-td tr j-enrollFee" fee="">
										${obj.enrollFee }
									</td>
								</tr>
							</c:forEach>
						</table>
						</div>
			</c:if>
			<!-- 
			<div class="form-item" style="">
				<span class="item-title w100">比赛项目： </span>
				<div class="item-font2 fl"></div>
			</div>
			<div class="form-item" style="">
				<span class="item-title w100">报名类型： </span>
				<div class="item-font2 fl">
					个人
					<%-- 
					<c:if test="${gameEnroll.enrollType==0 }">个人</c:if>
					<c:if test="${gameEnroll.enrollType==1 }">团队（专业）</c:if>
					<c:if test="${gameEnroll.enrollType==2 }">团队（业余）</c:if>
					--%>
				</div>
			</div>
			<%--<s:if test="gameEnroll.enrollType==0">--%>
			
					
				
				<div class="form-item">
					<span class="item-title w100">姓名： </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.memberName }--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">性别： </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">证件类型： </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">证件号码： </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">手机号码： </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.memberPhone }--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">电子邮箱： </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">国籍： </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">紧急联系人： </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">紧急联系人电话：  </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">紧急联系人关系：  </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">健康注意事项：  </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">血型：  </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">是否当地人：  </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">报名费：  </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
			<%--</s:if>--%>
			<%--<s:else>--%>
				<div class="form-item">
					<span class="item-title w100">车队名称：  </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">经理姓名：  </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">手机号码：  </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">车队成员：  </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
				<table>
					<tr class="enroll-tr">
						<th class="enroll-th tc" style="width:30px;">序号</th>
						<th class="enroll-th" style="width:100px;">姓名</th>
						<th class="enroll-th" style="width:50px;">性别</th>
						<th class="enroll-th" style="width:200px;">证件类型</th>
						<th class="enroll-th" style="width:200px;">证件号码</th>
						<th class="enroll-th" style="width:150px;">手机号码</th>
						<th class="enroll-th" style="width:150px;">电子邮箱</th>
						<th class="enroll-th" style="width:150px;">国籍</th>
						<th class="enroll-th" style="width:150px;">紧急联系人</th>
						<th class="enroll-th" style="width:150px;">紧急联系人电话</th>
						<th class="enroll-th" style="width:150px;">联系人关系</th>
						<th class="enroll-th" style="width:150px;">健康注意事项</th>
						<th class="enroll-th" style="width:150px;">血型</th>
						<th class="enroll-th" style="width:150px;">当地人</th>
						<th class="enroll-th" style="width:150px;">报名费</th>
					</tr>
					<%-- 
					<c:forEach items="${gameEnrollItem.enrollList}" var="curMember" varStatus="cur"  >
						<tr class="enroll-tr ">
							<td class="enroll-td tc">${cur.index+1}</td>
							<td class="enroll-td tc">
								<c:if test="${ curMember.teamCaptain==1}">
									经理
								</c:if>
								<c:if test="${curMember.teamCaptain != 1}">
									队员
								</c:if>
							</td>
							<td class="enroll-td">
								${curMember.memberName}
							</td>
							<td class="enroll-td">
								<c:if test="${curMember.sex==1}" >
									男
								</c:if>
								<c:if test="${curMember.sex==0 }">
									女
								</c:if>
							</td>
							<td class="enroll-td">
								${curMember.idCardNo}
							</td>
							<td class="enroll-td">
								${curMember.memberPhone }
							</td>
						</tr>
					</c:forEach>
					--%>
				</table>
				</div>
				<div class="form-item">
					<span class="item-title w100">报名人数：  </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w100">报名费总计：  </span>
					<div class="item-font2 fl">
						<%-- ${gameEnroll.idCardNo}--%>
					</div>
				</div>
				-->
			<%--</s:else>--%>
			
			<div class="form-item" style="margin-top:20px;">
				<span class="item-title w100"></span>
				
				<!-- 支付表单，仅供3月12日测试使用 -->
				<form hidden="true" id="payForm" name="cardform" method="post" action="https://webpaywg.bestpay.com.cn/payWeb.do">
					商户ID：
					<input name="MERCHANTID" type="text"  value="02310109012159000"
						class="normal_input" size="60" maxlength="60" tabIndex='1'><br/>
					商户子商户ID：
					<input name="SUBMERCHANTID"  value="02310109012159000" type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'/><br/>
					订单号：
					<input name="ORDERSEQ"   value="${commModel.orderID}" type="text" class="normal_input" size="60" maxlength="60" tabIndex='1'><br/>
					订单请求交易流水号：
					<input name="ORDERREQTRANSEQ"  readonly value="${commModel.orderReqTranSeq}" type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'/><br/>
					订单日期：
					<input name="ORDERDATE"  value="${commModel.orderDate}" type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'/><br/>
					产品金额(分)：
					<input name="PRODUCTAMOUNT"  type="text" value="${commModel.productAmount}"
						class="normal_input" size="60" maxlength="60" tabIndex='1'/><br/>
					附加金额(分)：
					<input name="ATTACHAMOUNT" type="text" value="${commModel.attachAmount}"
						class="normal_input" size="60" maxlength="60" tabIndex='1'/><br/>
					订单总金额(分)：
					<input name="ORDERAMOUNT"  type="text" value="${commModel.transAmount}"
						class="normal_input" size="60" maxlength="60" tabIndex='1'/><br/>
					币种：
				<input name="CURTYPE"  value ="${commModel.curType}" type="text" 
						class="normal_input" size="60" maxlength="60" tabIndex='1'/>
				<br/>
					加密方式：
					<input name="ENCODETYPE"   value ="${commModel.encodeType}" type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'><br/>
					交易返回的url地址：
					<input name="MERCHANTURL"
						 readonly value="${commModel.merchantUrl}"
						type="text" class="normal_input" size="60" maxlength="60"
						tabIndex='1'/><br/>
					交易后台返回的url地址：
					<input name="BACKMERCHANTURL"readonly value="${commModel.backMerchantUrl}"
						type="text" class="normal_input" size="60" maxlength="60"
						tabIndex='1'/><br/>
					商家附加信息：
					<input name="ATTACH"  readonly value ="${commModel.attach}" type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'><br/>
					业务类型：
					<input name="BUSICODE" value ="${commModel.busiCode}" type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'><br/>
					业务标识：
					<input name="PRODUCTID"  readonly value ="${commModel.productId}" type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'><br/>
					终端号码：
					<input name="TMNUM"  readonly value ="${commModel.tmNum}" type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'><br/>
					客户标识：
					<input name="CUSTOMERID"  readonly value ="${commModel.customerId}" type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'><br/>
					产品描述：
					<input name="PRODUCTDESC"  readonly value ="${commModel.productDesc}" type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'><br/>
					分账明细：
					<input name="DIVDETAILS" value='' type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'><br/>
					分期数：
					<input name="PEDCNT" value='' type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'><br/>
					客户端IP：
					<input name="CLIENTIP" readonly value ="${commModel.ipRemote}" type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'><br/>
					MAC：
					<input name="MAC"  readonly value ="${commModel.mac}" type="text"
						class="normal_input" size="60" maxlength="60" tabIndex='1'><br/>
					<input name='paymentSumbit' type="submit"
						value=" 提交 " class="normal_input"
						tabIndex='4'>
			</form>
			<!-- 支付表单，仅供3月12日测试使用 -->
				
				<!-- 临时用下面的进行支付  <a class="register-btn fl" href="game/success_enroll.htm">确认报名并在线支付</a> -->
				 <a class="register-btn fl" id="gotoPay" href="javascript:void(0);">确认报名并在线支付 </a> 
				 <script type="text/javascript">
				 	$("#gotoPay").click(function(){
			 		    $("#payForm").submit();
				 	});
				 </script>
				 <!-- 暂时应付一下3月12日测试 -->
			</div>
		</div>
			
		<%--尾部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
		<%--尾部end--%>
	</body>
</html>