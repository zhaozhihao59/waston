<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="s" uri="/struts-tags" %>
<%-- 该页面为片段页面 --%>
<style type="text/css">
	li.mr10 input {
		margin-right:2px;
	}
</style>
<div class="p10" id="roleListDv">
<ul style="width:330px;">
	<s:iterator value="roleList" var="role">
		<li class="fl mr10" style="width:100px">
			<input type="checkbox" value="<s:property value='#role.id'/>" />
			<s:property value="#role.name" />
		</li>
	</s:iterator>
</ul>
</div>