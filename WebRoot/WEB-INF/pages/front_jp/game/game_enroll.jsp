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
		<title>HEROS - 自行车赛 - 参赛报名</title>
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
			<div class="enroll-tip-div">
				<p class="enroll-tip bg-box-dotted" >如果您之前在此报过名，可用您的手机号码与初始密码<a class="link-a f12 fb ml5 mr5 btn-green-block" href="login/index_back.htm">登录</a>，登录后车队与队员可自动填充。</p>
				<p class="enroll-tip mt20 bg-box-dotted">
					<span class="fb f14 heros-tp-ico ico-gth mb5">填表注意：</span>
					1、车队人数：车队成员人数限制在4人-8人；<br/>
					2、团队优惠：有些比赛项目按车队报名可享受团队价，请参照不同比赛公告；<br/>
					3、当地优惠：注意填全表格信息，<b style="color:#f53131">注意滚动条</b>，有些比赛项目对当地人有优惠价格，请务必准确填写；<br/>
					4、帐号创建：以下队员以手机号作为本网站会员帐号，如果没有创建过，系统将自动创建帐号；
				</p>
			</div>
			<div class="geb-step1-box"></div>
			<s:form id="saveForm" action="game/confirm_enroll.htm" method="post">
				<div class="form-item" style="margin-top:30px;">
					<div class="item-title fl">
						<p class="item-title-zh"><em>*</em>赛事：</p>
						<p class="item-title-en"><em></em>Game</p>
					</div>
					<input id="gameName" name="gameEnroll.gameName" type="hidden" />
					<div class="select-div fl" style="width:400px;">
				 		<s:select list="gameList"
									headerKey=""
									headerValue="-- 请选择 --"
									listKey="id"
									listValue="gameName" 
									name=""
									id="gameId"
									cssClass="w">
						</s:select>
					</div>
					<span id="gameIdTip" class="fl"></span>
				</div>
				<div class="form-item">
					<div class="item-title fl">
						<p class="item-title-zh"><em>*</em>比赛项目： </p>
						<p class="item-title-en"><em></em>Game Item</p>
					</div>
					<div class="select-div fl" style="width:400px;">
						<input id="gameItemName" name="gameEnroll.gameItemName" type="hidden" />
						<select id="gameItemId" onchange="itemChangeFn()" name="gameEnroll.gameItemId" class="w">
							<option value="" fee="-1" feeTeam="-1" feeLocal="-1">-- 请选择 --</option>
						</select>
					</div>
					<span id="gameItemIdTip" class="fl"></span>
				</div>
				<div class="form-item">
					<div class="item-title fl">
						<p class="item-title-zh"><em></em>报名费：</p>
						<p class="item-title-en"><em></em>Registration Fee</p>
					</div>
					<div class="item-font2 fl">
						<span id="enrollFeeShow" class="">报名费会根据比赛项目自动显示</span>
					</div>
				</div>
				<div class="form-item">
					<div class="item-title fl">
						<p class="item-title-zh"><em>*</em>报名类型：</p>
						<p class="item-title-en"><em></em>Registrant Type</p>
					</div>
					<div class="item-font2 fl">
						<label><input id="typeTeam" name="enrollType" value="1" type="radio" checked class="vm"><span class="vm ml5">车队</span></label>
						<label class="ml10"><input id="typeIndividual" name="enrollType" value="0" type="radio" class="vm"><span class="vm ml5">个人</span></label>
						<input id="enrollType" name="gameEnroll.enrollType" value="1" type="hidden"/>
					</div>
				</div>
				<div id="teamProfession">
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em>*</em>车队名称： </p>
							<p class="item-title-en"><em></em>Team Name</p>
						</div>
						<c:if test="${user != null}">
							<input id="teamName" name="enrollTeam.teamName" class="ui-input-text fl teamName j-teamName" style="width:200px;" type="text">
							<input id="teamId" name="enrollTeam.teamId"  type="hidden" />
						</c:if>
						<c:if test="${user == null}">
							<input id="teamName" name="enrollTeam.teamName" class="ui-input-text fl" style="width:200px;" type="text">
						</c:if>
						<span id="teamNameTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em>*</em>经理姓名： </p>
							<p class="item-title-en"><em></em>Team Manager</p>
						</div>
						<c:if test="${user != null}">
							<input id="teamLeaderName" name="enrollTeam.teamLeaderName" class="ui-input-text fl teamName" style="width:200px;" type="text">
						</c:if>
						<c:if test="${user == null}">
							<input id="teamLeaderName" name="enrollTeam.teamLeaderName" class="ui-input-text fl" style="width:200px;" type="text">
						</c:if>
						<span id="teamLeaderNameTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em>*</em>手机号码： </p>
							<p class="item-title-en"><em></em>Mobile Phone</p>
						</div>
						<c:if test="${user != null}">
							<input id="teamLeaderMobile" name="enrollTeam.teamLeaderPhone" class="ui-input-text fl teamName" style="width:200px;" type="text">
						</c:if>
						<c:if test="${user == null}">
							<input id="teamLeaderMobile" name="enrollTeam.teamLeaderPhone" class="ui-input-text fl" style="width:200px;" type="text">
						</c:if>
						<span id="teamLeaderMobileTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title">
							<div style="padding-top:3px;">
								<p class="item-title-zh"><em>*</em>车队成员：</p>
								<p class="item-title-en"><em></em>Team Member</p>
							</div>
						</div>
						<div style="float:left;margin:5px 0 0 0px;width:920px;">
							<a id="addMember" class="btn-green-block heros-tp-ico ico-add"  href="javascript:void(0);"><c:if test="${user==null }">新增队员</c:if></a>
						</div>
					</div>
					<div class="form-item">
						<div class="table-div">
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
							<tr class="enroll-tr j-team-member">
								<td class="enroll-td tc j-num">1</td>
								<td class="enroll-td">
									<input id="name_0" name="enrollTeam.enrollList[0].memberName" class="ui-input-text fl w60 j-name" type="text"/>
								</td>
								<td class="enroll-td">
									<div class="select-div" style="width:40px;">
										<select id="sex_0" name="enrollTeam.enrollList[0].member.sex" class="w j-sex">
											<option value="1">男</option>
											<option value="0">女</option>
										</select>
									</div>
								</td>
								<td class="enroll-td">
									<div class="select-div" style="width:90px;">
										<select id="cardType_0" name="enrollTeam.enrollList[0].member.cardType" class="w j-cardType">
											<c:forEach items="${cardTypeList}" var="item">
											<option value="${item.id}">${item.name}</option>
											</c:forEach>
										</select>
									</div>
								</td>
								<td class="enroll-td">
									<input id="idCardNo_0" name="enrollTeam.enrollList[0].member.idCardNo" class="ui-input-text fl j-idCardNo" style="width:160px;" type="text"/>
								</td>
								<td class="enroll-td">
									<input id="mobile_0" name="enrollTeam.enrollList[0].memberPhone" class="ui-input-text fl j-mobile" style="width:100px;" type="text"/>
								</td>
								<td class="enroll-td">
									<input id="email_0" name="enrollTeam.enrollList[0].memberEmail"  class="ui-input-text fl j-email" style="width:160px;" type="text"/>
								</td>
								<td class="enroll-td">
									<div class="select-div" style="width:60px;">
										<select id="nation_0" name="enrollTeam.enrollList[0].memberNation" class="w j-nation">
											<c:forEach items="${nationList}" var="item">
											<option value="${item.id}">${item.name}</option>
											</c:forEach>
										</select>
									</div>
								</td>
								<td class="enroll-td"> 
									<input id="linkName_0" name="enrollTeam.enrollList[0].memberLinkName"  class="ui-input-text fl j-linkName" style="width:78px;" type="text"/>
								</td>
								<td class="enroll-td">
									<input id="linkPhone_0" name="enrollTeam.enrollList[0].memberLinkPhone"  class="ui-input-text fl j-linkPhone" style="width:100px;" type="text"/>
								</td>
								<td class="enroll-td">
									<div class="select-div" style="width:90px;">
										<select id="linkRelation_0" name="enrollTeam.enrollList[0].memberLinkRelation" class="w j-linkRelation">
											<c:forEach items="${relationList}" var="item">
											<option value="${item.id}">${item.name}</option>
											</c:forEach>
										</select>
									</div>
								</td>
								<td class="enroll-td">
									<input id="health_0" name="enrollTeam.enrollList[0].memberHealth"  class="ui-input-text fl j-health" style="width:160px;" type="text"/>
								</td>
								<td class="enroll-td">
									<div class="select-div" style="width:60px;">
										<select id="bloodType_0" name="enrollTeam.enrollList[0].memberBloodType" class="w j-bloodType">
											<c:forEach items="${bloodTypeList}" var="item">
											<option value="${item.id}">${item.name}</option>
											</c:forEach>
										</select>
									</div>
								</td>
								<td class="enroll-td">
									<div class="select-div" style="width:40px;">
										<select id="isLocal_0" name="enrollTeam.enrollList[0].isLocal" onchange="changeLocalFeeTeam(this);" class="w j-isLocal">
											<option value="0">否</option>
											<option value="1">是</option>
										</select>
									</div>
								</td>
								<td class="enroll-td tr j-enrollFee" fee="">
									
								</td>
								<td class="enroll-td">
									<a class="add-btn j-delete-member none" href="javascript:void(0);">删除</a>
								</td>
							</tr>
						</table>
						</div>
					</div>
					<div class="form-item" style="background-color: #eee;padding: 5px;border: 1px solid #ddd; ">
						<div class="item-title fl" style="width:110px;">
							<p class="item-title-zh"><em></em>报名人数： </p>
							<p class="item-title-en"><em></em>Number Total</p>
						</div>
						<div class="item-font2 fl">
							<span id="sumCount" class="mn-nub"></span>
						</div>
						
						<div class="item-title" style="width:110px;">
							<p class="item-title-zh"><em></em>报名费总计： </p>
							<p class="item-title-en"><em></em>Fee Total</p>
						</div>
						<div class="item-font2 fl">
							<span id="sumPrice" class="mn-nub"></span>
						</div>
					</div>
					<div id="errorDiv" class="form-item none" style="margin-top:20px;">
						<div id="errorList" class="fl error-list"></div>
					</div>
				</div>
				<div id="individual" class="none">
					<div class="form-item">
						<input name="gameEnroll.memberId" value="${user.id }" type="hidden">
						<div class="item-title fl">
							<p class="item-title-zh"><em>*</em>姓名：</p>
							<p class="item-title-en"><em></em>Name</p>
						</div>
						
						<input id="memberName" class="ui-input-text fl w250" name="gameEnroll.memberName" value="${user.name }"  <c:if test="${user != null}"> readonly="readonly" </c:if> type="text">
						
						<span id="memberNameTip" class="fl"></span>
					</div>
					<c:if test="${user == null}">
						<div class="form-item">
							<div class="item-title fl">
								<p class="item-title-zh"><em>*</em>性别：</p>
								<p class="item-title-en"><em></em>Gender</p>
							</div>
							<div class="item-font2 fl">
								<label><input name="gameEnroll.sex" value="1" type="radio" checked class="vm"><span class="vm ml5">男</span></label>
								<label class="ml10"><input name="gameEnroll.sex" value="0" type="radio" class="vm"><span class="vm ml5">女</span></label>
							</div>
						</div>
					 </c:if>
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em>*</em>证件类型：</p>
							<p class="item-title-en"><em></em>ID Type</p>
						</div>
						<select id="cardType" onchange="cancleCheck();" name="gameEnroll.member.cardType" class="ui-input-text fl w100">
							<c:forEach items="${cardTypeList}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach> 
						</select>
					</div>
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em>*</em>证件号码：</p>
							<p class="item-title-en"><em></em>ID No.</p>
						</div>
						<input id="idCardNo" class="ui-input-text fl w250" name="gameEnroll.idCardNo" value="${user.idCardNo }"  <c:if test="${user != null}"> readonly="readonly" </c:if> type="text">
						<span id="idCardNoTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em>*</em>手机号码：</span></p>
							<p class="item-title-en"><em></em>Mobile Phone</p>
						</div>
						<input <c:if test="${user == null}"> id="memberPhone" </c:if> class="ui-input-text fl w250" name="gameEnroll.memberPhone" value="${user.mobile }" type="text">
						<span id="memberPhoneTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em>*</em>电子邮箱：</p>
							<p class="item-title-en"><em></em>Email</p>
						</div>
						<input <c:if test="${user == null}"> id="memberEmail" </c:if> class="ui-input-text fl w250" name="gameEnroll.memberEmail" value="${user.email }" type="text">
						<span id="memberEmailTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em>*</em>国籍：</p>
							<p class="item-title-en"><em></em>Nationality</p>
						</div>
						<select id="memberNation" name="gameEnroll.memberNation" class="ui-input-text fl w100">
								<c:forEach items="${nationList}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
						</select>
<!-- 						<input <c:if test="${user == null}"> id="memberNation" </c:if> class="ui-input-text fl w250" name="gameEnroll.memberNation" value="${user.nation}" type="text"> -->
						<span id="memberNationTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em>*</em>紧急联系人：</p>
							<p class="item-title-en"><em></em>Emergency Contact Name</p>
						</div>
						<input <c:if test="${user == null}"> id="memberLinkName" </c:if> class="ui-input-text fl w250" name="gameEnroll.memberLinkName" value="${user.linkName}" type="text">
						<span id="memberLinkNameTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em>*</em>紧急联系人电话：</p>
							<p class="item-title-en"><em></em>Emergency Contact Phone</p>
						</div>
						<input <c:if test="${user == null}"> id="memberLinkPhone" </c:if> class="ui-input-text fl w250" name="gameEnroll.memberLinkPhone" value="${user.linkPhone}" type="text">
						<span id="memberLinkPhoneTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em>*</em>紧急联系人关系：</p>
							<p class="item-title-en"><em></em>Emergency Contact Relation</p>
						</div>
						<select id="memberLinkRelation" name="gameEnroll.memberLinkRelation" class="ui-input-text fl w100">
								<c:forEach items="${relationList}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
						</select>
<!-- 						<input <c:if test="${user == null}"> id="memberLinkRelation" </c:if> class="ui-input-text fl w250" name="gameEnroll.memberLinkRelation" value="${user.linkRelation}" type="text"> -->
						<span id="memberLinkRelationTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em></em>健康注意事项：</p>
							<p class="item-title-en"><em></em>Healthy Attention</p>
						</div>
						<input <c:if test="${user == null}"> id="memberHealth" </c:if> class="ui-input-text fl w250" name="gameEnroll.memberHealth" value="${user.health}" type="text">
						<span id="memberHealthTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em></em>血型：</p>
							<p class="item-title-en"><em></em>Blood Type</p>
						</div>
						<select id="memberBloodType" name="gameEnroll.memberBloodType" class="ui-input-text fl w100">
							<c:forEach items="${bloodTypeList}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select>
<!-- 						<input <c:if test="${user == null}"> id="memberBloodType" </c:if> class="ui-input-text fl w250" name="gameEnroll.memberBloodType" value="${user.bloodType }" type="text"> -->
						<span id="memberBloodTypeTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title fl">
							<p class="item-title-zh"><em></em>当地人证件：</p>
							<p class="item-title-en"><em></em>Local ID</p>
						</div>
						<select id="isLocal" name="gameEnroll.isLocal"  onchange="changeLocalFee(this);" class="ui-input-text fl w100">
							<option value="0">否</option>
							<option value="1">是</option>
						</select>
<!-- 						<input <c:if test="${user == null}"> id="isLocal" </c:if> class="ui-input-text fl w250" name="gameEnroll.isLocal" value="" type="text"> -->
						<span id="isLocalTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title">
							<p class="item-title-zh"><em></em>报名费总计： </p>
							<p class="item-title-en"><em></em>Fee</p>
						</div>
						<div class="item-font2 fl">
							<span id="sumUnitPrice" class="mn-nub"></span>
						</div>
					</div>
				</div>
				
				<%-- 
				<div class="form-item">
					<span class="item-title w120"><em></em>选择套餐：</span>
					<div class="item-font2 fl">
						<label><input name="" value="1" type="radio" checked class="vm"><span class="vm ml5">A套餐</span></label>
						<label class="ml10"><input name="gameEnroll.setMeal" value="2" type="radio" class="vm"><span class="vm ml5">B套餐</span></label>
						<label class="ml10"><input name="gameEnroll.setMeal" value="3" type="radio" class="vm"><span class="vm ml5">C套餐</span></label>
						<label class="ml10"><input name="gameEnroll.setMeal" value="0" type="radio" class="vm"><span class="vm ml5">不需要</span></label>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w120"><em></em>选择交通：</span>
					<div class="item-font2 fl">
						<label class="fl"><input name="gameEnroll.vehicle" value="1" type="radio" checked class="vm"><span class="vm ml5">大巴接送</span></label>
						<label class="fl ml10"><input name="gameEnroll.vehicle" value="2" type="radio" class="vm"><span class="vm ml5">车辆运输</span></label>
						<label class="fl ml10"><input name="gameEnroll.vehicle" value="0" type="radio" class="vm"><span class="vm ml5">不需要</span></label>
						<label class="fl ml10 mr10"><input name="gameEnroll.vehicle" value="3" type="radio" class="vm"><span class="vm ml5">其他</span></label>
						<input id="elseVehicle"  class="ui-input-text fl w100" name="elseVehicleName" type="text">
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w120"><em></em>是否需要住宿：</span>
					<div class="item-font2 fl">
						<label><input name="gameEnroll.lodge" value="1" type="radio" checked class="vm"><span class="vm ml5">需要</span></label>
						<label class="ml10"><input name="gameEnroll.lodge" value="0" type="radio" class="vm"><span class="vm ml5">不需要</span></label>
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w120"><em></em>是否需要保险：</span>
					<div class="item-font2 fl">
						<label><input name="gameEnroll.insure" value="1" type="radio" checked class="vm"><span class="vm ml5">需要</span></label>
						<label class="ml10"><input name="gameEnroll.insure" value="0" type="radio" class="vm"><span class="vm ml5">不需要</span></label>
					</div>
				</div>
				--%>
			</s:form>
			<div class="form-item" style="margin-top:20px;">
				<span class="item-title w120"></span>
				<a class="register-btn fl" style="width:262px;" href="javascript:void(0)">提交报名</a>
			</div>
		</div>
		<%--尾部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
		<%--尾部end--%>

		<script type="text/javascript" src="static/front/game/js/game_enroll.js"></script>	
	</body>
</html>