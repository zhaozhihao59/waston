<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>订阅名单</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	</head>
	<body class="iframe-body">
	 		<!-- <a class="ui_button mr5 vm" href="javascript:;" onclick="showSearchBar();return false;">更多查询</a> -->
		    <a class="ui_button vm" href="javascript:void(0);" onclick="toAddSubscribe();">新增活动</a>
		    <input id="key" name ="condition.key" class="tc-input-text w200 mr5 vm"  type="text" onkeypress="if(event.keyCode == 13){quickSearch();}" placeholder="请输入查询条件"/>
			<a class="ui_button mr5 vm" id="quickSearch" href="javascript:;" onclick="quickSearch();return false;">查询</a>
			<a class="ui_button mr5 vm" href="javascript:;" onclick="showSearchBar();return false;">更多查询</a>
			<a class="ui_button mr5 vm"  href="javascript:;" onclick="reloadGrid();return false;">刷新</a>
			
		<form id="searchForm">
		<div id="searchBar" class="hide">
			<table class="formTable mt10">
				<tr>
					<td class="form-title" width="7%">
						会议标题：
					</td>
					<td class="form-content" width="16%">
						<input name="condition.title" class="tc-input-text w" type="text"/>
					</td>
					<td class="form-title" width="7%">
						活动地址：
					</td>
					<td class="form-content" width="16%">
						<input name="condition.address" class="tc-input-text w" type="text"/>
					</td>
					<td class="form-title" width="7%">
						活动状态：
					</td>
					<td class="form-content" width="16%">
						<select name="condition.state">
		 					<option value="0">请选择</option>
							<option value="1">预告</option>
							<option value="2">正在报名</option>
							<option value="3">历届</option>
						</select>
					</td>
					
					<td class="form-title" width="7%">
						活动推荐：
					</td>
					<td class="form-content" width="18%">
					<select name="condition.isRecommend">
		 					<option value="-1">请选择</option>
							<option value="0">未推荐</option>
							<option value="1">推荐</option>
					</select>
						<!-- <input name="condition.position" class="tc-input-text w" type="text"/> -->
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
		
		
		<script type="text/javascript" src="static/admin/activity/activity_manager_list.js"></script>
	</body>
	<script>
	function showSearchBar() {
		$("#searchBar").slideToggle("normal");
	}
 </script>
</html>

