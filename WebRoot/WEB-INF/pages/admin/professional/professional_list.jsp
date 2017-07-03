<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
<title>专业人员</title>
<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
</head>
<body class="iframe-body">
 
	    <a id="addProfessional" class="ui_button vm" href="javascript:void(0);" onclick="toAddSubscribe();">新增人员</a>
 		 <input id="key" name ="condition.key" class="tc-input-text w200 mr5 vm"  type="text" onkeypress="if(event.keyCode == 13){quickSearch();}" placeholder="请输入查询条件"/>
			<a class="ui_button mr5 vm" id="quickSearch" href="javascript:;" onclick="quickSearch();return false;">查询</a>
<a class="ui_button mr5 vm" href="javascript:;" onclick="showSearchBar();return false;">更多查询</a>
<form id="searchForm">
<div id="searchBar" class="hide">
<table class="formTable mt10">
	<tr>
		<td class="form-title" width="5%">
			专业人员姓名：
		</td>
		<td class="form-content" width="6%">
			<input name="condition.name" class="tc-input-text w" type="text"/>
		</td>
		<td class="form-title" width="7%">
			专业人员邮箱：
		</td>
		<td class="form-content" width="10%">
			<input name="condition.email" class="tc-input-text w" type="text"/>
		</td>
		<td class="form-title" width="5%">
			资格类型：
		</td>
		<td class="form-content" width="6%">
			<select name="condition.qualification">
				<option value="">请选择</option>
				<c:forEach var="qfl" items="${qualificationList}">
						<option value="${qfl.id}">${qfl.name}</option>
				</c:forEach>
			</select>
			<!-- <input name="condition.qualification" class="tc-input-text w" type="text"/> -->
		</td>
		<td class="form-title" width="5%">
			工作语言：
		</td>
		<td class="form-content" width="6%">
				<select name="condition.language">
				<option value="">请选择</option>
				<c:forEach var="lgl" items="${languageList}">
						<option value="${lgl.id}">${lgl.name}</option>
				</c:forEach>
			</select>
			<!-- <input name="condition.language" class="tc-input-text w" type="text"/> -->
		</td>
		<td class="form-title" width="5%">
			专业人员类型：
		</td>
		<td class="form-content" width="6%">
		 	<select name="condition.type">
		 		<option value="">请选择</option>
				<option value="0">合伙人</option>
				<option value="1">高级顾问</option>
				<option value="2">专利代理人</option>
				<option value="3">律师</option>
			</select>
			<!-- <input name="condition.type" class="tc-input-text w" type="text"/> -->
		</td>
	</tr>
</table>
	<div class="">
		<a href="javascript:void(0);" onclick="moreSearch();return false;" class="ui_button w5 mr5 fl">查询</a>
		</div>
	</div>
	</form>
		
	<div class="mt10 fl">
		<table id="table"></table>
		<div id="pagerBar"></div>
	</div>
	
	
</body>
<script type="text/javascript" src="static/admin/professional/professional_list.js"></script>
<script>
function showSearchBar() {
	$("#searchBar").slideToggle("normal");
}
</script>
</html>

