<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>文章管理</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link rel="stylesheet" href="static/base/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script src="static/base/lib/ztree/js/jquery.ztree.all-3.5.js"></script>
		<script type="text/javascript">var currentTabIndex = '${currentTabIndex}';</script>
	</head>
	<body class="iframe-body">
		<s:form id="articleForm">
			<s:hidden name="item.id" id="itemId"/>
			<div id="errorlist"></div>
			<div class="oper-bar mb5">
				<a id="saveUserBtn" class="ui_button" style="padding:0 40px;" href="javascript:;" onclick="submitForm('#articleForm');">保存</a>
			</div>
			<div id="login_info_bar">
				<table class="form-table ">
					<caption>基本信息</caption>
					<tr>
						<td class="w100" style="height:0"></td>
						<td class="w500" style="height:0"></td>
						<td class="w200" style="height:0"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							排序：
						</td>
						<td class="form-content">
							<c:if test="${item.sort != null}">
							<input type="text" class="tc-input-text w60 fl" name="item.sort" value="${item.sort}"/>
							</c:if>
							<c:if test="${item.sort == null}">
							<input type="text" class="tc-input-text w60 fl" name="item.sort" value="1"/>
							</c:if>
							<span class="ml15">注意：数字越小越靠前</span>
						</td>
						<td class="form-content w250">
						</td>
					</tr>
					<tr>
						<td class="form-title w120">
							栏目名称：
						</td>
						<td class="form-content">
							<input id="channelChoose" class="tc-input-text w300" type="text" value="${item.channelName }"/>
							<input id="channelId" name="item.channelId" type="hidden" value="${item.channelId }"/>
							<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
								<ul id="treeDemo" class="ztree ztree-select" style="margin-top:0; width:160px;"></ul>
								<div class="mb100"></div>
							</div>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
					<td class="form-title w120">
							发布时间：
						</td>
							<td class="form-content" style="width:28%;">
							<input id="createDatefmt" class="tc-input-text fl w300" name="item.createDate"  type="text" value="<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${item.createDate }" />"/>
					</td>
					</tr>
					<tr>
						<td class="form-title w120">
							发布人：
						</td>
						<td class="form-content">
						<s:if test="item.author != null">
								<input id="author" name="item.author" value="${item.author}" type="text" class="tc-input-text fl w300"/>
							</s:if>
							<s:else>
								<input id="author" name="item.author" value="华诚小编" type="text"  class="tc-input-text fl w300" />
							</s:else>
							<%-- <input type="text" class="tc-input-text fl w300" id="author" name="item.author" value="${item.author}"/> --%>
						</td>
						<td class="form-content w250"></td>
					</tr>
						<tr>
						<td class="form-title w120">
							发布人【英文】：
						</td>
						<td class="form-content">
						<s:if test="item.author != null">
								<input id="authorEn" name="item.authorEn" value="${item.authorEn}" type="text" class="tc-input-text fl w300"/>
							</s:if>
							<s:else>
								<input id="authorEn" name="item.authorEn" value="Editor" type="text"  class="tc-input-text fl w300" />
							</s:else>
						</td>
						<td class="form-content w250"></td>
					</tr>
										<tr>
						<td class="form-title w120">
							发布人【日文】：
						</td>
						<td class="form-content">
						<s:if test="item.author != null">
								<input id="authorJp" name="item.authorJp" value="${item.authorJp}" type="text" class="tc-input-text fl w300"/>
							</s:if>
							<s:else>
								<input id="authorJp" name="item.authorJp" value="华诚小編" type="text"  class="tc-input-text fl w300" />
							</s:else>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							文章标题【中文】：
						</td>
						<td class="form-content">
							<input id="itemName" name="item.name" value="${item.name}" type="text" class="tc-input-text fl w300"/>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							文章标题【英文】：
						</td>
						<td class="form-content">
							<input id="itemEnName" name="item.enName" value="${item.enName}" type="text" class="tc-input-text fl w300"/>
						</td>
						<td class="form-content w250"></td>
					</tr>
										<tr>
						<td class="form-title w120">
							文章标题【日文】：
						</td>
						<td class="form-content">
							<input id="itemJpName" name="item.jpName" value="${item.jpName}" type="text" class="tc-input-text fl w300"/>
						</td>
						<td class="form-content w250"></td>
					</tr>
					
					<tr>
						<td class="form-title w120">
							文章摘要【中文】：
						</td>
						<td class="form-content">
							<textarea id="summary" name="item.summary" class="tc-textarea fl w1000 h100">${item.summary }</textarea>
						</td>
						<td class="form-content w250"></td>
					</tr>
						<tr>
						<td class="form-title w120">
							文章摘要【英文】：
						</td>
						<td class="form-content">
							<textarea id="enSummary" name="item.enSummary" class="tc-textarea fl w1000 h100">${item.enSummary }</textarea>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
						<td class="form-title w120">
							文章摘要【日文】：
						</td>
						<td class="form-content">
							<textarea id="jpSummary" name="item.jpSummary" class="tc-textarea fl w1000 h100">${item.jpSummary }</textarea>
						</td>
						<td class="form-content w250"></td>
					</tr>
					<tr>
					<td class="form-title">
							上传附件【中文】：
						</td>
						<td class="form-content" colspan="2">
							<div class="fl">
								<input id="txtFileName2" name="item.annexFilename" value="${item.annexFilename }" class="tc-input-text w300 sindex-input fl" type="text">
								<input id="path2" name="item.annexPath" value="${item.annexPath }" type="hidden" />
							</div>	
							<div class="fl ml5">
								<span id="spanButtonPlaceholder2" class="fr"></span>
							</div>
							<a id="" class="ui_button fl" href="javascript:void(0);" onclick="doUploadFile2();return false;">上传</a>
							<span class="ml10">温馨提示：华诚期刊请在此上传PDF文件</span>
							<div class="ml5" id="fsUploadProgress2"></div>
						</td>
					</tr>
					
					<tr>
					<td class="form-title">
							上传附件【英文】：
						</td>
						<td class="form-content" colspan="2">
							<div class="fl">
								<input id="txtFileNameEn" name="item.enAnnexFilename" value="${item.enAnnexFilename }" class="tc-input-text w300 sindex-input fl" type="text">
								<input id="pathEn" name="item.enAnnexPath" value="${item.enAnnexPath }" type="hidden" />
							</div>	
							<div class="fl ml5">
								<span id="spanButtonPlaceholderEn" class="fr"></span>
							</div>
							<a id="" class="ui_button fl" href="javascript:void(0);" onclick="doUploadFileEn();return false;">上传</a>
							<span class="ml10">温馨提示：华诚期刊请在此上传PDF文件</span>
							<div class="ml5" id="fsUploadProgressEn"></div>
						</td>
					</tr>
					<tr>
					<td class="form-title">
							上传附件【日文】：
						</td>
						<td class="form-content" colspan="2">
							<div class="fl">
								<input id="txtFileNameJp" name="item.jpAnnexFilename" value="${item.jpAnnexFilename}" class="tc-input-text w300 sindex-input fl" type="text">
								<input id="pathJp" name="item.jpAnnexPath" value="${item.jpAnnexPath}" type="hidden" />
							</div>	
							<div class="fl ml5">
								<span id="spanButtonPlaceholderJp" class="fr"></span>
							</div>
							<a id="" class="ui_button fl" href="javascript:void(0);" onclick="doUploadFileJp();return false;">上传</a>
							<span class="ml10">温馨提示：华诚期刊请在此上传PDF文件</span>
							<div class="ml5" id="fsUploadProgressJp"></div>
						</td>
					</tr>
					<tr>
						<td class="form-title">
							上传标题图片：
						</td>
						<td class="form-content" colspan="2">
							<div class="fl">
								<input id="txtFileName" name="item.filename" value="${item.filename }" class="tc-input-text w300 sindex-input fl" type="text">
								<input id="path" name="item.path" value="${item.path }" type="hidden" />
							</div>	
							<div class="fl ml5">
								<span id="spanButtonPlaceholder" class="fr"></span>
							</div>
							<a id="upload" class="ui_button fl" href="javascript:void(0);" onclick="doUploadFile();return false;">上传</a>
							<span class="ml10">温馨提示：预览图片尺寸为160x110 （著作的封面尺寸为110x145)</span>
							<div class="ml5" id="fsUploadProgress"></div>
						</td>
					</tr>
					<tr>
						<td class="form-title">
							图片预览：
						</td>
						
						<td id="aTP" class="form-content" colspan="2">
							<div class="yl-qy">
								<img id="downloadImg" src="${item.path }" alt="图片预览区域" style="max-width: 200px;max-height: 100px"/>
							</div>
						</td>
					</tr>
				</table>
				<div class="tab-div mt3 mb3">
						<a id="gameItem" class="tab-btn J-tab-sub" href="javascript:void(0);">文章内容【中文】</a>
						<a id="gameEnroll" class="tab-btn J-tab-sub" href="javascript:void(0);">文章内容【英文】</a>
						<a id="webDetail" class="tab-btn J-tab-sub" href="javascript:void(0);">文章内容【日文】</a>
				</div>
				
				<div class="tab-sub-content-div">
					<div class="tab-btn-content J-tab-sub-content">
						<div class="caption-div"><span class="">文章内容【中文】</span></div>
						<div class="art-content">
							<textarea id="artContent" name="item.content" class="ui_textarea ">${item.content }</textarea>
						</div>
					</div>
					<div class="tab-btn-content J-tab-sub-content" style="overflow:hidden">
						<div class="caption-div"><span class="">文章内容【英文】</span></div>
							<div class="art-content">
								<textarea id="artEnContent" name="item.enContent" class="ui_textarea ">${item.enContent }</textarea>
							</div>
					</div>
					<div class="tab-btn-content J-tab-sub-content" style="overflow:hidden">
							<div class="caption-div"><span class="">文章内容【日文】</span></div>
							<div class="art-content">
								<textarea id="artJpContent" name="item.jpContent" class="ui_textarea ">${item.jpContent }</textarea>
							</div>
					</div>
				</div>
			</div>
		</s:form>
		<div class="mb50"></div>
		<script src="static/base/lib/swfupload/swfupload.js" type="text/javascript"></script>
		<script src="static/base/lib/swfupload/handlers.js" type="text/javascript"></script>
		<script src="static/base/lib/swfupload/fileprogress.js" type="text/javascript"></script>
		<script src="static/base/lib/lhgcalendar/lhgcalendar.min.js" type="text/javascript"></script>
 		<script src="static/base/lib/ueditor/ueditor.config.js"></script>
		<script src="static/base/lib/ueditor/ueditor.all.min.js"></script>
		<script src="static/base/lib/ueditor/lang/zh-cn/zh-cn.js"></script>
		<script src="static/admin/cms/article_add.js"></script>
	</body>
</html>