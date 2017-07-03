<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>添加订单</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
		<script type="text/javascript">var currentTabIndex = '${currentTabIndex}';</script>
	<body class="iframe-body">
		<%-- 基本信息 --%>
		<div class="oper-bar mt5 mb5">
			<a id="updateBtn" class="ui_button" href="javascript:void(0);" onclick="saveSubscribe();">保存</a>
		</div>
		<s:form id="subscriptForm" action="doSave" namespace="/admin/professional">
		<input id="itemId" name="item.id" value="${item.id }" type="hidden">
		<table class="form-table">
			<caption>
				专业人员名单
			</caption>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em> 姓名【中文】：
				</td>
				<td class="form-content" width="30%">
					<input id="name" name="item.name" value="${item.name }"  class="tc-input-text w250" type="text"/>
				</td>
				<td class="form-content" width="58%">
					<span id="nameTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em> 姓名【英文】：
				</td>
				<td class="form-content" width="30%">
					<input id="nameEn" name="item.nameEn" value="${item.nameEn}"  class="tc-input-text w250" type="text"/>
				</td>
				<td class="form-content" width="58%">
					<span id="nameEnTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em> 姓名【日文】：
				</td>
				<td class="form-content" width="30%">
					<input id="nameJp" name="item.nameJp" value="${item.nameJp}"  class="tc-input-text w250" type="text"/>
				</td>
				<td class="form-content" width="58%">
					<span id="nameJpTip"></span>
				</td>
			</tr>
			
			<tr>
				<td class="form-title">
					<em>*</em>邮箱：
				</td>
				<td class="form-content">
					<input id="email" name="item.email" value="${item.email }" class="tc-input-text w250" type="text" />
				</td>
				<td class="form-content">
					<span id="emailTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					<em>*</em>类型：
				</td>
				<td class="form-content">
					<SELECT id="type" name="item.type" class="w400" multiple="true" data-placeholder="选择人员类型">
						<option value="4" <s:if test="item.type.lastIndexOf('4')>=0">selected</s:if>>资深高级合伙人</option>
						<option value="5" <s:if test="item.type.lastIndexOf('5')>=0">selected</s:if>>主管合伙人</option>
						<option value="6" <s:if test="item.type.lastIndexOf('6')>=0">selected</s:if>>高级合伙人</option>
						<option value="1" <s:if test="item.type.lastIndexOf('1')>=0">selected</s:if>>高级顾问</option>
						<option value="0" <s:if test="item.type.lastIndexOf('0')>=0">selected</s:if>>合伙人</option>
						<option value="3" <s:if test="item.type.lastIndexOf('3')>=0">selected</s:if>>律师</option>
						<option value="2" <s:if test="item.type.lastIndexOf('2')>=0">selected</s:if>>专利代理人</option>
						<option value="7" <s:if test="item.type.lastIndexOf('7')>=0">selected</s:if>>执行主管合伙人</option>
					</SELECT>
				</td>
				<td class="form-content">
					<span id="typeTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					<em>*</em>业务【中文】：
				</td>
				<td class="form-content">
						<SELECT id="business" name="item.business" multiple="true" class="w400" data-placeholder="选择业务类型【中文】">
						<s:iterator value="businessList" var="lgl" >
						<option value="${id}" <s:if test="item.business.indexOf(#lgl.id) != -1">selected</s:if>>${name}</option>
						</s:iterator>
						</SELECT>
				</td>
				<td class="form-content">
					<span id="businessTip"></span>
				</td>
			</tr>
			<tr>
			<tr>
				<td class="form-title">
					<em>*</em>业务【英文】：
				</td>
				<td class="form-content">
						<SELECT id="businessEn" name="item.businessEn" multiple="true" class="w400" data-placeholder="选择业务类型【英文】">
						<s:iterator value="businessListEn" var="lgl" >
						<option value="${id}" <s:if test="item.businessEn.indexOf(#lgl.id) != -1">selected</s:if>>${name}</option>
						</s:iterator>
						</SELECT>
				</td>
				<td class="form-content">
					<span id="businessTip"></span>
				</td>
			</tr>
			<tr>
			<tr>
				<td class="form-title">
					<em>*</em>业务【日文】：
				</td>
				<td class="form-content">
						<SELECT id="businessJp" name="item.businessJp" multiple="true" class="w400" data-placeholder="选择业务类型【日文】">
						<s:iterator value="businessListJp" var="lgl" >
						<option value="${id}" <s:if test="item.businessJp.indexOf(#lgl.id) != -1">selected</s:if>>${name}</option>
						</s:iterator>
						</SELECT>
				</td>
				<td class="form-content">
					<span id="businessJpTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					<em>*</em>头衔【中文】：
				</td>
				<td class="form-content">
						<SELECT id="qualification" name="item.qualification" multiple="true" class="w400" data-placeholder="选择头衔类型【中文】">
						<s:iterator value="qualificationList" var="lgl" >
						<option value="${id}" <s:if test="item.qualification.indexOf(#lgl.id) != -1">selected="selected"</s:if> >${name}</option>
						</s:iterator>
						</SELECT>
				</td>
				<td class="form-content">
					<span id="qualificationTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					<em>*</em>头衔【英文】：
				</td>
				<td class="form-content">
						<SELECT id="qualificationEn" name="item.qualificationEn" multiple="true" class="w400" data-placeholder="选择头衔类型【英文】">
						<s:iterator value="qualificationListEn" var="lgl" >
						<option value="${id}" <s:if test="item.qualificationEn.indexOf(#lgl.id) != -1">selected</s:if>>${name}</option>
						</s:iterator>
						</SELECT>
				</td>
				<td class="form-content">
					<span id="qualificationEnTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					<em>*</em>头衔【日文】：
				</td>
				<td class="form-content">
						<SELECT id="qualificationJp" name="item.qualificationJp" multiple="true" class="w400" data-placeholder="选择头衔类型【日文】">
						<s:iterator value="qualificationListJp" var="lgl" >
						<option value="${id}" <s:if test="item.qualificationJp.indexOf(#lgl.id) != -1">selected</s:if>>${name}</option>
						</s:iterator>
						</SELECT>
				</td>
				<td class="form-content">
					<span id="qualificationJpTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					<em>*</em>工作语言【中文】：
				</td>
				<td class="form-content">
						<SELECT id="language" name="item.language" multiple="true" class="w400" data-placeholder="选择工作语言类型【中文】">
						<s:iterator value="languageList" var="lgl" >
						<option value="${id}" <s:if test="item.language.indexOf(#lgl.id) != -1">selected</s:if>>${name}</option>
							</s:iterator>
						</SELECT>
				</td>
				<td class="form-content">
					<span id="languageTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					<em>*</em>工作语言【英文】：
				</td>
				<td class="form-content">
						<SELECT id="languageEn" name="item.languageEn" multiple="true" class="w400" data-placeholder="选择工作语言类型【英文】">
						<s:iterator value="languageListEn" var="lgl" >
						<option value="${id}" <s:if test="item.languageEn.indexOf(#lgl.id) != -1">selected</s:if>>${name}</option>
							</s:iterator>
						</SELECT>
				</td>
				<td class="form-content">
					<span id="languageEnTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					<em>*</em>工作语言【日文】：
				</td>
				<td class="form-content">
						<SELECT id="languageJp" name="item.languageJp" multiple="true" class="w400" data-placeholder="选择工作语言类型【日文】">
						<s:iterator value="languageListJp" var="lgl" >
						<option value="${id}" <s:if test="item.languageJp.indexOf(#lgl.id) != -1">selected</s:if>>${name}</option>
							</s:iterator>
						</SELECT>
				</td>
				<td class="form-content">
					<span id="languageJpTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em> 排序【数值小靠前】：
				</td>
				<td class="form-content" width="30%">
					<input id="sort" name="item.sort" value="${item.sort}"  class="tc-input-text w250" type="text"/>
				</td>
				<td class="form-content" width="58%">
					<span id="sortTip"></span>
				</td>
			</tr>
			<tr>
				<td class="form-title">
					上传图片：
				</td>
				<td class="form-content" colspan="2">
					<div class="fl">
						<input id="txtFileName"  value="${item.photo }"   class="tc-input-text w250 sindex-input fl" type="text">
						<input id="path" name="item.photo" value="${item.photo }" type="hidden" />
					</div>	
					<div class="fl ml5">
						<span id="spanButtonPlaceholder" class="fr"></span>
					</div>
					<a id="upload" class="ui_button fl" href="javascript:void(0);" onclick="doUploadFile();return false;">上传</a>
					<span class="ml10">温馨提示：用于网页显示，尺寸请勿过大</span>
					<div class="ml5" id="fsUploadProgress"></div>
				</td>
			</tr>
			<tr>
				<td class="form-title" width="12%">
					<em>*</em>头像：
				</td>
				<td id="aTP" class="form-content" colspan="2">
					<div class="yl-qy">
							<img id="downloadImg" src="${item.photo }" alt="图片预览区域" style="max-width: 200px;max-height: 100px"/>
						</div>
				</td>
			</tr>
		</table>
		<div class="tab-div mt3 mb3">
				<a id="gameItem" class="tab-btn J-tab-sub" href="javascript:void(0);">专业人员简介【中文】</a>
				<a id="gameEnroll" class="tab-btn J-tab-sub" href="javascript:void(0);">专业人员简介【英文】</a>
				<a id="webDetail" class="tab-btn J-tab-sub" href="javascript:void(0);">专业人员简介【日文】</a>
		</div>
		<div class="tab-sub-content-div">
				<div class="tab-btn-content J-tab-sub-content">
					<div class="caption-div"><span class="">专业人员简介【中文】</span></div>
					<div class="art-content">
						<textarea id="artContent" name="item.desc" class="ui_textarea ">${item.desc}</textarea> 
					</div>
				</div>
				<div class="tab-btn-content J-tab-sub-content" style="overflow:hidden">
					<div class="caption-div"><span class="">专业人员简介【英文】</span></div>
					<div class="art-contentEn">
						<textarea id="artContentEn" name="item.descEn" class="ui_textarea ">${item.descEn}</textarea> 
					</div>
				</div>
				<div class="tab-btn-content J-tab-sub-content" style="overflow:hidden">
					<div class="caption-div"><span class="">专业人员简介【日文】</span></div>
					<div class="art-contentJp">
						<textarea id="artContentJp" name="item.descJp" class="ui_textarea ">${item.descJp}</textarea> 
					</div>
				</div>
			</div>
		<div id="errorlist" class="mt5 mb5"></div>
		
		</s:form>
		<script src="static/base/lib/swfupload/swfupload.js" type="text/javascript"></script>
		<script src="static/base/lib/swfupload/handlers.js" type="text/javascript"></script>
		<script src="static/base/lib/swfupload/fileprogress.js" type="text/javascript"></script>
		<script src="static/base/lib/lhgcalendar/lhgcalendar.min.js" type="text/javascript"></script>
 		<script src="static/base/lib/ueditor/ueditor.config.js"></script>
		<script src="static/base/lib/ueditor/ueditor.all.min.js"></script>
		<script src="static/base/lib/ueditor/lang/zh-cn/zh-cn.js"></script>
		<script type="text/javascript" src="static/admin/professional/professional_add.js"></script>
	</body>
</html>