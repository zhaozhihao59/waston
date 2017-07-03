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
			<a class="ui_button mr5 vm" id="quickSearch" href="javascript:;" onclick="selectRole();return false;">批量选择</a>
			<select id="selectChannelId" class="tc-select-div vm" style="height:25px">
				<option value="-1" selected="selected">请选择</option>
				<option value="c25f9c04-e891-11e4-ae9b-00266c0e7760">华诚合规</option>
				<option value="bba13349-e891-11e4-ae9b-00266c0e7760">华诚通讯</option>
			</select>
		    <input id="key" name ="condition.key" class="tc-input-text w200 mr5 vm"  type="text" onkeypress="if(event.keyCode == 13){quickSearch();}" placeholder="请输入查询条件"/>
			<a class="ui_button mr5 vm" id="quickSearch" href="javascript:;" onclick="quickSearch();return false;">查询</a>
			<a class="ui_button mr5 vm" href="javascript:;" onclick="showSearchBar();return false;">更多查询</a>
			<a class="ui_button mr5 vm" href="javascript:;" onclick="reloadGrid();return false;">刷新</a>
		<form id="searchForm">
		<div id="searchBar" class="hide">
			<table class="formTable mt10">
				<tr>
					<td class="form-title" width="7%">
						订阅者姓名：
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
						职务：
					</td>
					<td class="form-content" width="18%">
						<input name="condition.position" class="tc-input-text w" type="text"/>
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
		
		<script type="text/javascript" src="static/admin/subscribe/subscribe_list_select.js"></script>
	</body>
	<script>
	function showSearchBar() {
		$("#searchBar").slideToggle("normal");
	}
 </script>
</html>

