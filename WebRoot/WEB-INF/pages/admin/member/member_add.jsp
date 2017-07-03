<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>会员详细</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link rel="stylesheet" href="static/base/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script src="static/base/lib/ztree/js/jquery.ztree.all-3.5.js"></script>
		<script>
			var chooseTags = $.parseJSON('${matchJSON}');
		</script>
		<script type="text/javascript">
  			var provinceList = <%=application.getAttribute("provinceList")%>;
  			var provinceCity = "${item.provinceCity}"
		</script>
	</head>
	<body class="iframe-body">
		<s:form id="articleForm" action="doSave" namespace="/admin/member">
			<s:hidden name="item.id" id="itemId"/>
			<div id="login_info_bar">
				<div id="errorlist"></div>
				<table class="form-table ">
					<caption>基本信息</caption>
					<tr>
						<td style="width:12%;height:0"></td>
						<td style="width:53%;height:0"></td>
						<td style="width:35%;height:0"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							<em>*</em>会员帐号：
						</td>
						<td class="form-content">
							<input id="account" name="item.account" value="${item.account }" class="tc-input-text w" type="text"/>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							<em>*</em>初始密码：
						</td>
						<td class="form-content">
							<input  name="item.newPassword" value="${item.password }"  class="tc-input-text w" type="hidden"/>
							<input id="password" name="item.password"  class="tc-input-text w" type="password"/>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							<em>*</em>真实姓名：
						</td>
						<td class="form-content">
							<input id="name" name="item.name" value="${item.name }" class="tc-input-text w" type="text"/>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<%-- <tr>
						<td class="form-title w120">
							<em>*</em>团队技能：
						</td>
						<td class="form-content">
							<ul id="teamResourceTag" class="fl">
								
								<li class="resource-tag mr5">
									<span class="tag-name mr5">软件工程师</span>
									<a id="" href="javascript:void(0);" class="tag-del">X</a>
								</li>
								
							</ul>
							<input id="tagsHid" name="item.tagIds" type="hidden"/>
							<a id="chooseTagBtn" class="ui_button fl" href="javascript:void(0);" onclick="chooseTag();">选择标签</a>
							<div id="chooseContent">
							</div>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							性别：
						</td>
						<td class="form-content">
							<label class="fl">
								<input id="man" name="item.sex" value="1" <c:if test="${item.sex == 1 }">checked</c:if> class="fl mr5" style="margin-top:2px;" type="radio">
								<span class="fl">男</span>
							</label>
							<label class="fl ml10">
								<input id="female" name="item.sex" value="0" <c:if test="${item.sex == 0 }">checked</c:if> class="fl mr5" style="margin-top:2px;" type="radio">
								<span class="fl">女</span>
							</label>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							手机号码：
						</td>
						<td class="form-content">
							<input id="mobile" name="item.mobile" value="${item.mobile }" class="tc-input-text w" type="text"/>
						</td>
						<td class="form-content w250"></td>
					</tr> --%>
					<tr>
						<td class="form-title w120">
							电子邮箱：
						</td>
						<td class="form-content">
							<input id="email" name="item.email" value="${item.email }" class="tc-input-text w" type="text"/>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							省市区：
						</td>
						<td class="form-content">
						<input id="addressInput" name="item.provinceCity" value="${item.provinceCity }" type="hidden" />
								<div class="select_div fl mr5">
									<input id="provinceCode" name="item.provinceId" type="hidden">
									<input id="provinces" name="item.province" type="hidden" value="<s:property value='item.province'/>">
									<select id="province" class="all-select w100 fl">
										<option value="-1">请选择省份</option>
									</select>
								</div>
								<div class="select_div fl mr5">
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
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							邮编：
						</td>
						<td class="form-content">
							<input id="postCode" name="item.postCode" value="${item.postCode }" class="tc-input-text w" type="text"/>
						</td>
						<td class="form-content w250"></td>
					</tr>			
					<tr>
						<td class="form-title w120">
							手机号码：
						</td>
						<td class="form-content">
							<input id="mobile" name="item.mobile" value="${item.mobile }" class="tc-input-text w" type="text"/>
						</td>
						<td class="form-content w250"></td>
					</tr>
													
					<%-- <tr>
						<td class="form-title w120">
							QQ：
						</td>
						<td class="form-content">
							<input id="qq" name="item.qq" value="${item.qq }" class="tc-input-text w" type="text"/>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							微信：
						</td>
						<td class="form-content">
							<input id="wechat" name="item.wechat" value="${item.wechat }" class="tc-input-text w" type="text"/>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							地址：
						</td>
						<td class="form-content">
							<input id="address" name="item.address" value="${item.address }" class="tc-input-text w" type="text"/>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							行业：
						</td>
						<td class="form-content">
							<input id="industry" name="item.industry" value="${item.industry }" class="tc-input-text w" type="text"/>
						</td>
						<td class="form-content w250"></td>
					</tr> --%>
					<tr>
						<td class="form-title">
							个人简介：
						</td>
						<td class="form-content">
							<textarea id="brief" name="item.brief" class="tc-textarea w" style="height:80px;">${item.brief }</textarea>
						</td>
						<td class="form-content">
							<span id="briefTip"></span>
						</td>
					</tr>
				</table>
			</div>
<%-- 			<div class="caption-div"><span class="">详细介绍</span></div>
			<div class="art-content">
				<textarea id="artContent" name="item.desc" class="ui_textarea" style="height:400px;">${item.desc }</textarea>
			</div>  --%>
		</s:form>
		<div class="oper mt5 mb5">
			<a id="saveUserBtn" class="ui_button" style="width:100px;text-align:center;" href="javascript:;" onclick="submitForm('#articleForm');">保存会员</a>
		</div>
		<div class="mb50"></div>
		<script src="static/base/lib/swfupload/swfupload.js" type="text/javascript"></script>
		<script src="static/base/lib/swfupload/fileprogress.js" type="text/javascript"></script>
		<script src="static/base/lib/swfupload/handlers.js" type="text/javascript"></script>
		
		<script type="text/javascript" src="static/base/lib/kindeditor/kindeditor.js"></script>
		<script src="static/admin/member/member_add.js"></script>
	</body>
</html>