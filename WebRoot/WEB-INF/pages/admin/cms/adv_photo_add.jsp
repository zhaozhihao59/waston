<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>广告管理 - 管理广告图片</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<script type="text/javascript">var swfu;var basePath = '<%=request.getContextPath()%>';</script>
	</head>
	<body class="iframe-body">
		<form id="advPhotoForm" action="admin/cms/advPhoto/add.htm" method="post">
			<s:hidden name="item.advId" id="advId" value="%{item.advId}"/>
			<s:hidden name="item.id" id="itemId" value="%{item.id}"/>
			<div id="login_info_bar">
				<table class="form-table ">
					<tr>
						<td class="form-title w150">
							广告图片名称：
						</td>
						<td class="form-content">
							<input id="name" name="item.name" value="${item.name }" type="text" class="tc-input-text w" />
						</td>
					</tr>
					<tr>
						<td class="form-title w150">
							图片超链接：
						</td>
						<td class="form-content">
							<c:if test="${item.linkUrl == null}">
							<input id="linkUrl" name="item.linkUrl" value="http://${item.linkUrl }" type="text" class="tc-input-text w" />
							</c:if>
							<c:if test="${item.linkUrl != null}">
							<input id="linkUrl" name="item.linkUrl" value="${item.linkUrl }" type="text" class="tc-input-text w" />
							</c:if>
						</td>
					</tr>
					<tr>
						<td class="form-title w150">
							图片说明：
						</td>
						<td class="form-content">
							<input id="note" name="item.note" value="${item.note }" type="text" class="tc-input-text w" />
						</td>
					</tr>
					<tr>
						<td class="form-title w150">
							排序：
						</td>
						<td class="form-content">
							<c:if test="${item.sort == null}">
							<input id="sort" name="item.sort" value="1" type="text" class="tc-input-text w80 mr10" />
							</c:if>
							<c:if test="${item.sort != null}">
							<input id="sort" name="item.sort" value="${item.sort }" type="text" class="tc-input-text w80 mr10" />
							</c:if>
							注意：数字越小，排序越靠前
						</td>
					</tr>
					<tr>
						<td class="form-title w120">
							图片：
						</td>
						<td class="form-content">
							<div class="photo-add-div mr20 fl">
								<a href="javascript:void(0);" class="image-upload-a">
									<img id="showImg" alt="选择图片" src="${item.path}" class="adv-photo-add"/>
									<input id="fileInput" name="file" type="file" class="image-upload" onchange="doUploadFile();"/>
								</a>
								<input id="imgPath" name="item.path" value="${item.path }" type="hidden"/>
							</div>
						</td>
					</tr>
					
					<%--<tr>
						<td class="form-title w150">
							选择上传图片：
						</td>
						<td class="form-content">
							<input id="txtFileName" name="item.fileName" value="${item.fileName }" type="text" class="tc-input-text w230 fl mr5"/>
							<input type="hidden" id="path" value="${item.path }" name="item.path"/>
							<span id="spanButtonPlaceholder"></span>
							<a class="ui_button" href="javascript:void(0);" onclick="uploadMaintainOrderAttachmentSuccess();return false;">上传</a>
						</td>
					</tr>
					<tr>
						<td class="form-title w150">
						</td>
						<td class="form-content">
							<div class="flash" id="fsUploadProgress"> 
							<div class="img-show" id="imgTd"></div>
						</td>
					</tr>
				--%>
				</table>
			</div>
			<div class="oper mt5 fr">
				<a id="saveBtn" class="ui_button" href="javascript:void(0);">保存</a>
				<a id="cancelBtn" class="ui_button" href="javascript:void(0);">取消</a>
			</div>
			<div class="cb mt10" id="errorlist"></div>
		</form>
		<div class="mb50"></div>
		<script src="static/base/lib/ajaxfileupload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="static/base/lib/swfupload/swfupload.js"></script>
		<script type="text/javascript" src="static/base/lib/swfupload/fileprogress.js"></script>
		<script type="text/javascript" src="static/base/lib/swfupload/handlers.js"></script>
		<script type="text/javascript" src="static/admin/cms/adv_add.js"></script>
	</body>
</html>