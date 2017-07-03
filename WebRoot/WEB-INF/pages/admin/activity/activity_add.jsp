<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>活动编辑</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<link rel="stylesheet" href="static/base/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script src="static/base/lib/ztree/js/jquery.ztree.all-3.5.js"></script>
		<script type="text/javascript">var currentTabIndex = '${currentTabIndex}';</script>
	</head>
	<body class="iframe-body">
		<s:form id="articleForm" action="/admin/activity/doSave.htm">
			<div class="oper-bar mb5">
				<a id="saveUserBtn" class="ui_button" style="padding:0 40px;" href="javascript:;" onclick="submitForm('#articleForm');">保存</a>
			</div>
			<s:hidden name="item.id" id="itemId"/>
			<input type="hidden" name="item.isRecommend" id="isRecommend" value="${item.isRecommend}"/>
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
							<span class="ml10">注意：数字越小越靠前</span>
						</td>
						<td class="form-content "></td>
					</tr>
					 <tr>
						<td class="form-title w120">
							活动状态：
						</td>
						<td class="form-content">
							<div class="tc-select-div w300 fl">
								<select id="status" class="w" name="item.state">
									<option <c:if test='${item.state == "-1"}'>  selected='selected'  </c:if> 	value="-1">全部</option>
									<option <c:if test='${item.state == "1"}'>  selected='selected'  </c:if>   value="1">预告</option>
									<option <c:if test='${item.state == "2"}'>  selected='selected'  </c:if>   value="2">正在报名</option>
									<option <c:if test='${item.state == "6"}'>  selected='selected'   </c:if>  value="3">历届</option>
								</select>
							</div>
							<span id="statusTip"></span>
						</td>
						<td class="form-content "></td>
					</tr>
					<tr>
						<td class="form-title w120">
							会议时间：
						</td>
						<td class="form-content w250">
							<input id="dateStr" name="item.dateStr" value="${item.dateStr}" type="text" class="tc-input-text fl w300"/>
							<span id="dateStrTip"></span>
						</td>
						<td class="form-content "></td>
					</tr>
					<tr>
						<td class="form-title w120">
							会议时间【英文】：
						</td>
						<td class="form-content w250">
							<input id="dateStrEn" name="item.dateStrEn" value="${item.dateStrEn}" type="text" class="tc-input-text fl w300"/>
							<span id="dateStrEnTip"></span>
						</td>
						<td class="form-content "></td>
					</tr>
					<tr>
						<td class="form-title w120">
							会议时间【日文】：
						</td>
						<td class="form-content w250">
							<input id="dateStrJp" name="item.dateStrJp" value="${item.dateStrJp}" type="text" class="tc-input-text fl w300"/>
							<span id="dateStrJpTip"></span>
						</td>
						<td class="form-content "></td>
					</tr>
					<tr>
						<td class="form-title">
							上传图片：
						</td>
						<td class="form-content" >
							<div class="fl">
								<input id="txtFileName"  value="${item.photo }"   class="tc-input-text w300 sindex-input fl" type="text">
								<input id="path" name="item.photo" value="${item.photo }" type="hidden" />
							</div>	
							<div class="fl ml5">
								<span id="spanButtonPlaceholder" class="fr"></span>
							</div>
							<a id="upload" class="ui_button fl" href="javascript:void(0);" onclick="doUploadFile();return false;">上传</a>
							<span class="ml10">温馨提示：活动图片尺寸240x150</span>
							<div class="ml5" id="fsUploadProgress"></div>
						</td>
						<td class="form-content "></td>
					</tr>
					<tr>
						<td class="form-title">
							图片预览：
						</td>
						<td id="aTP" class="form-content" >
							<div class="yl-qy">
								<img id="downloadImg" src="${item.photo }" alt="图片预览区域"  style="max-width: 200px;max-height: 100px"/>
							</div>
						</td>
						<td class="form-content "></td>
					</tr>
					
					<tr>
						<td class="form-title w120">
							会议标题【中文】：
						</td>
						<td class="form-content w250">
							<input id="title" name="item.title" value="${item.title}" type="text" class="tc-input-text fl w600"/>
							<span id="titleTip"></span>
						</td>
						<td class="form-content "></td>
					</tr>
					<tr>
						<td class="form-title w120">
							会议标题【英文】：
						</td>
						<td class="form-content w250">
							<input id="titleEn" name="item.titleEn" value="${item.titleEn}" type="text" class="tc-input-text fl w600"/>
							<span id="titleEnTip"></span>
						</td>
						<td class="form-content "></td>
					</tr>
					<tr>
						<td class="form-title w120">
							会议标题【日文】：
						</td>
						<td class="form-content w250">
							<input id="titleJp" name="item.titleJp" value="${item.titleJp}" type="text" class="tc-input-text fl w600"/>
							<span id="titleEnTip"></span>
						</td>
						<td class="form-content "></td>
					</tr>
					
					<tr>
					<td class="form-title w120">
							活动地址【中文】：
						</td>
						<td class="form-content">
							<s:if test="item.address != null">
								<input id="address" name="item.address" value="${item.address}" type="text" class="tc-input-text fl w600"/>
							</s:if>
							<s:else>
								<input id="address" name="item.address" value="上海市威海路755号文新报业大厦26楼" type="text"  class="tc-input-text fl w600" />
							</s:else>
							<span id="addressTip"></span>
						</td>
						<td class="form-content "></td>
					</tr>
					<tr>
					<td class="form-title w120">
							活动地址【英文】：
						</td>
						<td class="form-content">
							<s:if test="item.addressEn != null">
								<input id="addressEn" name="item.addressEn" value="${item.addressEn}" type="text" class="tc-input-text fl w600"/>
							</s:if>
							<s:else>
								<input id="addressEn" name="item.addressEn" value="上海市威海路755号文新报业大厦26楼" type="text"  class="tc-input-text fl w600" />
							</s:else>
							<span id="addressEnTip"></span>
						</td>
						<td class="form-content "></td>
					</tr>
					<tr>
					<td class="form-title w120">
							活动地址【日文】：
						</td>
						<td class="form-content">
							<s:if test="item.addressJp != null">
								<input id="addressJp" name="item.addressJp" value="${item.addressJp}" type="text" class="tc-input-text fl w600"/>
							</s:if>
							<s:else>
								<input id="addressJp" name="item.addressJp" value="上海市威海路755号文新报业大厦26楼" type="text"  class="tc-input-text fl w600" />
							</s:else>
							<span id="addressJpTip"></span>
						</td>
						<td class="form-content "></td>
					</tr>
					<tr>
						<td class="form-title w120">
							摘要【中文】：
						</td>
						<td class="form-content w250">
							<textarea rows="2" cols="48" id="prom" name="item.prom" style="height:100px;width:600px;">${item.prom }</textarea>
						</td>
						<td class="form-content "></td>
					</tr>
					<tr>
						<td class="form-title w120">
							摘要【英文】：
						</td>
						<td class="form-content w250">
							<textarea rows="2" cols="48" id="promEn" name="item.promEn" style="height:100px;width:600px;">${item.promEn}</textarea>
						</td>
						<td class="form-content "></td>
					</tr>
					<tr>
						<td class="form-title w120">
							摘要【日文】：
						</td>
						<td class="form-content w250">
							<textarea rows="2" cols="48" id="prom" name="item.promJp" style="height:100px;width:600px;">${item.promJp}</textarea>
						</td>
						<td class="form-content "></td>
					</tr>
				</table>
				
				<div class="tab-div mt3 mb3">
						<a id="gameItem" class="tab-btn J-tab-sub" href="javascript:void(0);">会议简介【中文】</a>
						<a id="gameEnroll" class="tab-btn J-tab-sub" href="javascript:void(0);">会议简介【英文】</a>
						<a id="webDetail" class="tab-btn J-tab-sub" href="javascript:void(0);">会议简介【日文】</a>
				</div>
				<div class="tab-sub-content-div">
						<div class="tab-btn-content J-tab-sub-content">
							<div class="caption-div"><span class="">会议简介【中文】</span></div>
							<div class="art-content">
								<textarea id="artContent" name="item.desc" class="ui_textarea">${item.desc }</textarea>
							</div>
						</div>
						<div class="tab-btn-content J-tab-sub-content" style="overflow:hidden">
								<div class="caption-div"><span class="">会议简介【英文】</span></div>
								<div class="art-content">
									<textarea id="artContentEn" name="item.descEn" class="ui_textarea">${item.descEn }</textarea>
								</div>
						</div>
						<div class="tab-btn-content J-tab-sub-content" style="overflow:hidden">
							<div class="caption-div"><span class="">会议简介【日文】</span></div>
							<div class="art-content">
								<textarea id="artContentJp" name="item.descJp" class="ui_textarea">${item.descJp }</textarea>
							</div>
						</div>
					</div>
			</div>
		</s:form>
		<div id="errorlist"></div>
		<div class="oper-bar mt5 mb5">
			<a id="saveUserBtn" class="ui_button" style="padding:0 40px;" href="javascript:;" onclick="submitForm('#articleForm');">保存</a>
		</div>
		<div class="mb50"></div>
		<script src="static/base/lib/swfupload/swfupload.js" type="text/javascript"></script>
		<script src="static/base/lib/swfupload/handlers.js" type="text/javascript"></script>
		<script src="static/base/lib/swfupload/fileprogress.js" type="text/javascript"></script>
		
		<script src="static/base/lib/ueditor/ueditor.config.js?t=${sysVersion}"></script>
		<script src="static/base/lib/ueditor/ueditor.all.min.js?t=${sysVersion}"></script>
		<script src="static/base/lib/ueditor/lang/zh-cn/zh-cn.js?t=${sysVersion}"></script>
		<script src="static/admin/activity/activity_add.js"></script>
	</body>
</html>