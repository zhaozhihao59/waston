<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>广告管理 - 管理广告图片</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	</head>
	<body class="iframe-body">
		<s:form id="articleForm">
			<s:hidden name="item.id" id="itemId" value="%{item.id}"/>
			<div id="login_info_bar">
				<table class="form-table ">
					<caption>广告位信息</caption>
					<tr>
						<td class="form-title w100">
							广告位名称：
						</td>
						<td class="form-content">
							${item.name }
						</td>
						<td class="form-title w200">
							图片尺寸（像素宽×像素高）：
						</td>
						<td class="form-content">
							${item.photoWidth }PX × ${item.photoHeight }PX
						</td>
						<td class="form-title w200">
							最多上传图片（张）：
						</td>
						<td class="form-content">
							${item.maxPhotoNum }
						</td>
					</tr>
					<tr>
						<td class="form-title w120">
							广告位描述：
						</td>
						<td class="form-content" colspan="5">
							${item.note }
						</td>
					</tr>
				</table>
				<div class="caption-div"><span class="">广告图片管理</span></div>
				<div id="advPhotoList" class="adv-photo-list">
					<%-- 此处AJAX请求获取图片列表HTML片段 --%>
					<span class="status-wait">正在加载，请稍候...</span>
				</div>
			</div>
		</s:form>
		<div class="oper mt10">
			<a id="refreshBtn" class="ui_button" href="javascript:void(0);">刷新</a>
		</div>
		<div class="mb50"></div>
		<script type="text/javascript" src="static/admin/cms/adv_manage.js"></script>
	</body>
</html>