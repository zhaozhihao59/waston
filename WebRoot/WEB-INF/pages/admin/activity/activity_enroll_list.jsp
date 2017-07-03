<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>活动列表</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	</head>
	<body class="iframe-body">
	 
	 		<a class="ui_button mr5 vm"   href="javascript:;" onclick="doBatchDel();return false;">批量删除</a>
		    <!--<a class="ui_button vm" href="javascript:void(0);" onclick="toAddActivity();">新增活动</a>
		    --><input id="key" name ="condition.key" class="tc-input-text w200 mr5 vm"  type="text" onkeypress="if(event.keyCode == 13){quickSearch();}" placeholder="请输入查询条件"/>
			<a class="ui_button mr5 vm" id="quickSearch" href="javascript:;" onclick="quickSearch();return false;">查询</a>
			<a class="ui_button mr5 vm" href="javascript:;" onclick="showSearchBar();return false;">更多查询</a>
			<a id="exportEnroll" class="ui_button mr5" href="javascript:void(0);">导出报名清单</a>
		<form id="searchForm">
		<div id="searchBar" class="hide">
			<table class="formTable mt10">
				<tr>
				<td class="form-title" width="7%">
						活动名称：
					</td>
					<td class="form-content" width="18%">
						<input name="condition.activityName" class="tc-input-text w" type="text"/>
					</td>
					<td class="form-title" width="7%">
						姓名：
					</td>
					<td class="form-content" width="18%">
						<input name="condition.name" class="tc-input-text w" type="text"/>
					</td>
					<td class="form-title" width="7%">
						手机号码：
					</td>
					<td class="form-content" width="18%">
						<input name="condition.mobile" class="tc-input-text w" type="text"/>
					</td>
					<td class="form-title" width="5%">
						邮箱：
					</td>
					<td class="form-content" width="18%">
						<input name="condition.email" class="tc-input-text w" type="text"/>
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
		
		<script type="text/javascript" src="static/admin/activity/activity_enroll_list.js"></script>
	</body>
	<script>
	function showSearchBar() {
		$("#searchBar").slideToggle("normal");
	}
 </script>
</html>

