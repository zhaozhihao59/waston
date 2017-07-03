<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>新增角色 / 修改角色</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link rel="stylesheet" type="text/css" href="static/base/lib/ztree/css/zTreeStyle/zTreeStyle.css"/>
		<link rel="stylesheet" type="text/css" href="static/base/lib/tc_checkbox/tc_checkbox.css"/>
		<script type="text/javascript" src="static/base/lib/ztree/js/jquery.ztree.all-3.5.js"></script>
	</head>
	<body class="iframe-body">
		<%-- 基本信息 --%>
		<s:form id="roleForm" action="saveCurrentUser" namespace="/admin/user/account">
		<input type="hidden" id="itemId" name="item.id" value="${item.id }" />
		<table class="form-table">
			<caption>
				基本信息
			</caption>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em>角色名称：
				</td>
				<td class="form-content" width="40%">
					<input id="name" name="item.name" value="${item.name }" class="tc-input-text w" type="text"/>
				</td>
				<td class="form-content" width="48%">
					<span id="nameTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					角色描述：
				</td>
				<td class="form-content">
					<textarea id="remark" name="item.remark" class="tc-textarea w h50">${item.remark }</textarea>
				</td>
				<td class="form-content">
				</td>
			</tr>
		</table>
		<div class="caption-div mt3">
			权限设置
			<input type="hidden" id="rightCheckedList" name="permissionIds" />
		</div>
		<div class="caption-div-border">
			<ul class="right-big-class w100 fl">
				<s:iterator value="rights" var="right" status="sta">
					<li class="rbc-item mb5">
						<a class="rbc-item-a" data="${right.id }" href="javascript:void(0);">
							<s:if test="#right.id == 4">
								<span rightId="${right.id }" class="button chk checkbox_true_disable"></span>
								<!-- 
								<span  class="button chk checkbox_true_part"></span>
								<span  class="button chk checkbox_true_full"></span>
								<span  class="button chk checkbox_true_disable"></span>
								<span  class="button chk checkbox_false_part"></span>
								<span  class="button chk checkbox_false_full"></span>
								<span  class="button chk checkbox_false_disable"></span>
								 -->
							</s:if>
							<s:else>
								<span  class="button chk checkbox_false_full"></span>
							</s:else>
							<span class="rbc-item-span">${right.name}</span>
						</a>
					</li>
				</s:iterator>
			</ul>
			<div class="right-content fl" id="rightContent">
			</div>
			<div class="cb"></div>
		</div>
		<div class="oper mt10">
			<a class="ui_button fl mr5" href="javascript:void(0);" id="saveNewRoleBtn" onclick="saveNewRole();return false;">保存</a>
			<a class="ui_button fl mr5" href="javascript:void(0);" onclick="top.closeTab('tabli_addrole',event);return false;">取消</a>
		</div>
		</s:form>
		<script type="text/javascript" src="static/base/lib/lhgcalendar/lhgcalendar.min.js"></script>
		<script type="text/javascript" src="static/admin/auth/role_add_update.js"></script>
		<script type="text/javascript" src="static/base/lib/tc_checkbox/tc_checkbox.js"></script>
	</body>
</html>