<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="${BASE_PATH}" />
		<title>邮件服务器配置</title>
		<jsp:include page="/WEB-INF/pages/admin/base/inc.jsp" />
	</head>
	<body class="iframe-body">
		<form id="configForm" action="admin/email/saveConfig.htm">
			<table class="form-table">
				<caption>
					邮件服务器配置
				</caption>
				<tr>
					<td class="form-title" width="12%">
						<em>*</em>主机：
					</td>
					<td class="form-content" width="30%">
						<input id="hostName" name="serverConfig.hostName" value="${serverConfig.hostName }" class="tc-input-text w" type="text"/>
					</td>
					<td class="form-content" width="58%">
						<span id="hostNameTip"></span>
					</td>
				</tr>
				<tr>
					<td class="form-title" width="12%">
						<em>*</em>账号：
					</td>
					<td class="form-content" width="30%">
						<input id="account" name="serverConfig.account" value="${serverConfig.account }" class="tc-input-text w" type="text"/>
					</td>
					<td class="form-content" width="58%">
						<span id="accountTip"></span>
					</td>
				</tr>
				<tr>
					<td class="form-title" width="12%">
						<em>*</em>密码：
					</td>
					<td class="form-content" width="30%">
						<input id="password" name="serverConfig.password" value="${serverConfig.password }" class="tc-input-text w" type="text"/>
					</td>
					<td class="form-content" width="58%">
						<span id="passwordTip"></span>
					</td>
				</tr>
			</table>
			<div class="oper mt10">
				<a class="ui_button ml200" href="javascript:void(0);" id="saveConfig">保存</a>
			</div>
		</form>
		<script type="text/javascript">
		//验证框架信息
		$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
		$("#hostName").formValidator({validatorGroup:1}).inputValidator({min:1,onErrorMin:"主机不能为空"});
		$("#account").formValidator({validatorGroup:1}).inputValidator({min:1,max:250,onErrorMax:"账号不能为空"});
		$("#password").formValidator().inputValidator({min:1,onError: "密码不能为空"}).defaultPassed();
		
		//异步表单提交设置
	    $('#configForm').ajaxForm({
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
	    	var result = $.formValidator.pageIsValid(1);	//手动调用验证框架进行验证
			if(!result){
				return;
			}
	    	$('#configForm').submit();
	    });
		</script>
	</body>
</html>