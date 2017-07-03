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
			<div class="geb-csbm-tt">参赛报名</div>
			<div class="geb-step1-box"></div>
			<s:form id="saveForm" action="game/confirm_enroll.htm" method="post">
				<div class="form-item" style="margin-top:30px;">
					<span class="item-title w120"><em>*</em>赛事： </span>
					<input id="gameName" name="gameEnroll.gameName"  type="hidden" />
					<div class="select-div" style="width:400px;">
				 		<s:select list="gameList"
									headerKey=""
									headerValue="-- 请选择 --"
									listKey="id"
									listValue="gameName" 
									name=""
									id="gmaeId"
									cssClass="w">
						</s:select>  
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w120"><em>*</em>比赛项目： </span>
					<div class="select-div fl" style="width:400px;">
						<input id="gameItemName" name="gameEnroll.gameItemName"  type="hidden" />
						<select id="gameItemId" onchange="itemChangeFn()" name="gameEnroll.gameItemId" class="w">
							<option value="" fee="-1">-- 请选择 --</option>
						</select>
					</div>
					<span id="gameItemIdTip" class="fl"></span>
				</div>
				<div class="form-item">
					<span class="item-title w120"><em></em>报名费： </span>
					<div class="item-font2 fl">
						<span id="enrollFeeShow" class="">报名费会根据比赛项目自动显示</span>
						<input id="enrollFee" name="gameEnroll.enrollFee" value="" type="hidden" />
					</div>
				</div>
				<div class="form-item">
					<span class="item-title w120"><em>*</em>报名类型： </span>
					<div class="item-font2 fl">
						<label><input id="typeTeam" name="gameEnroll.enrollType" value="1" type="radio" checked class="vm"><span class="vm ml5">车队</span></label>
						<label class="ml10"><input id="typeIndividual" name="gameEnroll.enrollType" value="0" type="radio" class="vm"><span class="vm ml5">个人</span></label>
					</div>
				</div>
				<div id="teamProfession">
					<div class="form-item">
						<span class="item-title w120"><em>*</em>车队名称： </span>
						<c:if test="${user != null}">
							<input id="teamName" name="gameEnroll.teamName" class="ui-input-text fl teamName"  style="width:320px;" type="text">
							<input id="teamId" name="gameEnroll.teamId"  type="hidden" />
						</c:if>
						<c:if test="${user == null}">
							<input name="gameEnroll.teamName" class="ui-input-text fl" style="width:400px;" type="text">
						</c:if>
						<span id="teamNameTip" class="fl"></span>
					</div>
					<div class="form-item">
						<div class="item-title w120">
							<div style="padding-top:3px;"><em>*</em>车队成员：</div>
						</div>
						<div style="float:left;margin-top:10px;"><em></em><a id="addMember" class="add-btn" href="javascript:void(0);"><c:if test="${user==null }">新增队员</c:if></a></div>
					</div>
					<div class="form-item">
						<div class="table-div-fix fl">
						<table id="teamTable" class="enroll-table" style="width:400px;">
							<tr class="enroll-tr">
								<th class="enroll-th tc" style="width:30px;">序号</th>
								<th class="enroll-th tc" style="width:30px;">类型</th>
								<th class="enroll-th" style="width:72px;">姓名</th>
								<th class="enroll-th" style="width:52px;">性别</th>
								<th class="enroll-th" style="width:172px;">身份证号</th>
							</tr>
							<tr class="enroll-tr teamCaptain">
								<td class="enroll-td tc">1</td>
								<td class="enroll-td tc">经理</td>
								<td class="enroll-td">
									<input name="gameEnroll.memberList[0].name" class="ui-input-text fl w60 j-name" type="text"/>
									<input name="gameEnroll.memberList[0].teamCaptain" value="1" class="ui-input-text fl w100 " type="hidden"/>
									<input name="gameEnroll.memberList[0].id"  class="j-memberId " type="hidden"/>
								</td>
								<td class="enroll-td">
									<div class="select-div" style="width:40px;">
										<select name="gameEnroll.memberList[0].sex" class="w j-sex">
											<option value="1">男</option>
											<option value="0">女</option>
										</select>
									</div>
								</td>
								<td class="enroll-td">
									<input name="gameEnroll.memberList[0].idCardNo" class="ui-input-text fl j-idCardNo" style="width:160px;" type="text"/>
								</td>
							</tr>
							<tr class="enroll-tr j-team-member">
								<td class="enroll-td tc j-num">2</td>
								<td class="enroll-td tc">队员</td>
								<td class="enroll-td">
									<input name="gameEnroll.memberList[1].name" class="ui-input-text fl w60 j-name" type="text"/>
									<input name="gameEnroll.memberList[1].id" class="j-memberId " type="hidden"/>
								</td>
								<td class="enroll-td">
									<div class="select-div" style="width:40px;">
										<select name="gameEnroll.memberList[1].sex" class="w j-sex">
											<option value="1">男</option>
											<option value="0">女</option>
										</select>
									</div>
								</td>
								<td class="enroll-td">
									<input name="gameEnroll.memberList[1].idCardNo" class="ui-input-text fl j-idCardNo" style="width:160px;" type="text"/>
								</td>
							</tr>
						</table>
						</div>
						<div class="table-div fl">
						<table id="teamTable" class="enroll-table" style="width:1000px;">
							<tr class="enroll-tr">
								<th class="enroll-th" style="width:112px;">手机号码</th>
								<th class="enroll-th" style="width:150px;">电子邮箱</th>
								<th class="enroll-th" style="width:60px;">国籍</th>
								<th class="enroll-th" style="width:150px;">紧急联系人</th>
								<th class="enroll-th" style="width:150px;">紧急联系人电话</th>
								<th class="enroll-th" style="width:150px;">紧急联系人关系</th>
								<th class="enroll-th" style="width:150px;">健康注意事项</th>
								<th class="enroll-th" style="width:50px;">血型</th>
								<th class="enroll-th" style="width:50px;"></th>
							</tr>
							<tr class="enroll-tr teamCaptain">
								<td class="enroll-td">
									<input name="gameEnroll.memberList[0].mobile"  class="ui-input-text fl j-mobile" style="width:100px;" type="text"/>
								</td>
								<td class="enroll-td"></td>
								<td class="enroll-td"></td>
								<td class="enroll-td"></td>
								<td class="enroll-td"></td>
								<td class="enroll-td"></td>
								<td class="enroll-td"></td>
								<td class="enroll-td"></td>
								<td class="enroll-td"></td>
							</tr>
							<tr class="enroll-tr j-team-member">
								<td class="enroll-td">
									<input name="gameEnroll.memberList[1].mobile" class="ui-input-text fl j-mobile" style="width:100px;" type="text"/>
								</td>
								<td class="enroll-td"></td>
								<td class="enroll-td"></td>
								<td class="enroll-td"></td>
								<td class="enroll-td"></td>
								<td class="enroll-td"></td>
								<td class="enroll-td"></td>
								<td class="enroll-td"></td>
								<td class="enroll-td">
									<a class="add-btn j-delete-member none" href="javascript:void(0);">删除</a>
								</td>
							</tr>
						</table>
						</div>
					</div>
				</div>
				<div id="individual" class="none">
					<div class="form-item">
						<input  name="gameEnroll.memberId" value="${user.id }" type="hidden">
						<span class="item-title w120"><em>*</em>姓名： </span>
						
						<input id="memberName" class="ui-input-text fl w250" name="gameEnroll.memberName" value="${user.name }"  <c:if test="${user != null}"> readonly="readonly" </c:if> type="text">
						
						<span id="memberNameTip" class="fl"></span>
					</div>
					<div class="form-item">
						<span class="item-title w120"><em>*</em>手机： </span>
						<input 	<c:if test="${user == null}"> id="memberPhone"</c:if> class="ui-input-text fl w250" name="gameEnroll.memberPhone" value="${user.mobile }" type="text">
						<span id="memberPhoneTip" class="fl"></span>
					</div>
					<div class="form-item">
						<span class="item-title w120"><em>*</em>身份证： </span>
						<input id="idCardNo" class="ui-input-text fl w250" name="gameEnroll.idCardNo" value="${user.idCardNo }"  <c:if test="${user != null}"> readonly="readonly" </c:if> type="text">
						<span id="idCardNoTip" class="fl"></span>
					</div>
					<c:if test="${user == null}">
						<div class="form-item">
							<span class="item-title w120"><em>*</em>性别：</span>
							<div class="item-font2 fl">
								<label><input name="gameEnroll.sex" value="1" type="radio" checked class="vm"><span class="vm ml5">男</span></label>
								<label class="ml10"><input name="gameEnroll.sex" value="0" type="radio" class="vm"><span class="vm ml5">女</span></label>
							</div>
						</div>
					 </c:if>
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
			<div class="enroll-fbg"></div>
		</div>
		<%--尾部--%>
		<jsp:include page="/WEB-INF/pages/front/comm/front_foot_v2.jsp"></jsp:include>
		<%--尾部end--%>

		<script type="text/javascript" src="static/front/game/js/game_enroll.js"></script>	
	</body>
</html>