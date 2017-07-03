<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>团队详细</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link rel="stylesheet" href="static/base/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script src="static/base/lib/ztree/js/jquery.ztree.all-3.5.js"></script>
	</head>
	<body class="iframe-body">
		<div class="oper mb3">
			<a id="saveUserBtn" class="ui_button fl mr5" style="width:100px;text-align:center;" href="javascript:;" onclick="submitForm('#articleForm');">保存基本信息</a>
			<div id="errorList" class="fl"></div>
			<div class="cb"></div>
		</div>
		<s:form id="articleForm" action="doSave" namespace="/admin/mem_team" >
			<s:hidden name="item.id" id="itemId"/>
			<div id="login_info_bar">
				<table class="form-table ">
					<caption>团队基本信息</caption>
					<tr>
						<td style="width:12%;height:0"></td>
						<td style="width:48%;height:0"></td>
						<td style="width:40%;height:0"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							<em>*</em>团队名称：
						</td>
						<td class="form-content">
							<input id="account" name="item.name" value="${item.name }" class="tc-input-text w" type="text"/>
						</td>
						<td class="form-content"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							<em>*</em>团队类型：
						</td>
						<td class="form-content">
							<input id="teamType" name="item.type" value="${item.type }" type="hidden"/>
							<label class="fl mr10">
								<input id="" name="teamType" value="1" class="fl mt4" type="radio" />
								<span class="fl ml5">专业团队</span>
							</label>
							<label class="fl">
								<input id="" name="teamType" value="0" class="fl mt4" type="radio"/>
								<span class="fl ml5">业余团队</span>
							</label>
						</td>
						<td class="form-content"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							团队描述：
						</td>
						<td class="form-content">
							<textarea class="tc-textarea w h80"  name="item.desc">${item.desc}</textarea>
						</td>
						<td class="form-content"></td>
					</tr>
				</table>
			</div>
		</s:form>
		<div class="oper mt3 mb3">
			<a id="saveUserBtn" class="ui_button fl mr5" style="width:100px;text-align:center;" href="javascript:;" onclick="addTeamMember('${item.id}')">新增队员</a>
			<a id="saveUserBtn" class="ui_button fl mr5" href="javascript:;" onclick="delArticle();">批量移除</a>
			<div class="cb"></div>
		</div>
		<table id="table"></table>
		<div id="pagerBar"></div>
		<script type="text/javascript"> var id='${item.id}'</script>
		<script src="static/admin/member/mem_team_detail.js"></script>
	</body>
</html>