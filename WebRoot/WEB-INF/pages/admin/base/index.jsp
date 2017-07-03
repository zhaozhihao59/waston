<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
<security:authentication property="principal" var="authentication" />
<!doctype html >
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>华诚网站后台管理系统</title>
		<link href="static/base/base.css" rel="stylesheet" />
		<link href="static/admin/base/common.css?t=${sysVersion}" rel="stylesheet" />
		<link href="static/admin/base/index.css" rel="stylesheet"/>
	</head>
	<body>
		<div id="north">
			<div id="siteInfo">
				<!-- <div id="logo"></div> -->
				<div style="text-align:left;font-size:18px;line-height:28px;margin-left:5px;font-family:'微软雅黑';">华诚网站后台管理系统</div>
				<div class="cb"></div>
				<div id="north_right">
					&nbsp;&nbsp;当前用户：${authentication.name} &nbsp;&nbsp;角色：
					<c:if test="${authentication.account == 'admin' }">系统管理员</c:if>
					<c:if test="${authentication.account != 'admin' }">
						<c:forEach items="${authentication.roles}" var="curRole">
		    				${curRole.name}&nbsp;
		    			</c:forEach>
					</c:if>
					&nbsp;&nbsp;[
					<a href="admin/logout.htm">退出</a> ] &nbsp;&nbsp;
				<%-- 					
					<a href="javascript:void(0);" id="clearOscacheBtn"> 清除网站缓存 </a> ]
				--%>
				</div>
			</div>
			<!-- 导航 -->
			<ul id="nav_top">
				<li>
					<a href="javascript:void(0);" class="select"
						onclick="showIndex();return false;">首&nbsp;&nbsp;页</a>
				</li>
			</ul>
			<!-- 导航 end -->
		</div>
		<div id="north-seperate">
			<div class="icon-h icon-top">
				图标
			</div>
		</div>
		<div id="west">
			<div class="west-menu"></div>
		</div>
		<div id="west-seperate">
			<div class="icon-v icon-left">
				图标
			</div>
		</div>
		<div id="menubar">
			<a href="javascript:void(0);" id="full_screen_btn" title="全屏">全屏</a>
			<a href="javascript:;" id="btn_tabs_prev" title="后退"></a>
			<a href="javascript:;" id="btn_tabs_next" title="前进"></a>

			<div id="menubar_tabs" class="menubar_tabs">
				<h3 id="tabh3_0">
					<a id="tabli_0" href="javascript:void(0);"
						onclick="showIndex();return false;"
						class="index-nav currenttab">首&nbsp;&nbsp;页
						&nbsp; </a>
				</h3>
			</div>
		</div>
		<div id="east">
			<iframe id="mainIframe_tabli_0" marginheight="10px"
				marginwidth="10px" class="mainIframe" src="admin/welcome.htm"
				scrolling="auto" frameborder="0">
			</iframe>
		</div>
		<script src="static/base/lib/jquery-1.5.2.min.js"></script>
		<script src="static/base/lib/artdialog/jquery.artDialog.source.js?t=${sysVersion}&skin=default"></script>
		<script src="static/base/lib/artdialog/plugins/iframeTools.source.js?t=${sysVersion}"></script>
		<script src="static/admin/base/common.js"></script>
		<script src="static/admin/base/index.js"></script>
	</body>
</html>