<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>添加或修改模板模板</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	</head>
	<body class="iframe-body">
		<form action="admin/email/saveOrUpdateTemplate.htm" id="templateForm" method="post">
			<input type="hidden" name="emailTemplate.id" id="emailTemplateId" value="${emailTemplate.id }" />
			<textarea id="content" name="emailTemplate.content" rows="20">${emailTemplate.content }</textarea>
			<div class="oper mt10">
				<a class="ui_button ml200" href="javascript:void(0);" id="saveConfig">保存</a>
			</div>
		</form>
		<script type="text/javascript" src="static/lib/kindeditor/kindeditor.js"></script>
		<script type="text/javascript">
			$(function(){
				editor = KindEditor.create('#content',{
					resizeMode :1,
					allowPreviewEmoticons : false,
					allowUpload : true,
					width:'100%',
					items: ['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
			        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
			        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
			        'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
			        'anchor', 'link', 'unlink', '|', 'about'], 
					uploadJson : base + 'editorUpload.htm?m='+Math.random()
				});
			});
			//异步表单提交设置
	    $('#templateForm').ajaxForm({
	        dataType: 'json',
	        success: function(data) {
	        	if(data.status = 'success'){
	        		$.dialog.alert('保存成功');
	        	}else if(data.status = 'error'){
	        		$.dialog.alert('保存失败');
	        	}
	        }
	    });
			$('#saveConfig').click(function(){
				editor.sync();
				$("#templateForm").submit();
		    });
		</script>
	</body>
</html>