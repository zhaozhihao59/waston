$(document).ready(function(){
	//初始化验证表单
	initFormValidator();
	//初始化表单提交
	initAjaxForm();
	//初始化操作按钮
	initOperateBtn();
});	
	
function initFormValidator(){
	//验证框架信息
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#levelNum").formValidator({validatorGroup:1}).inputValidator({min:1,max:255,onErrorMin:"等级序号不能为空",onErrorMax:"等级序号长度过长"})
	.regexValidator({regExp:"intege1",dataType:"enum",onError:"等级序号格式输入错误"})
	.ajaxValidator({
		dataType:"json",
		type:"POST",
		url:basePath + "admin/member/level/checkMemLevelNumExists.htm?m="+Math.random(),
		data:{
				"item.id":function(){
					return $("#itemId").val();
				},
				"item.levelNum":function(){
					return $("#levelNum").val();
				}
		},
		buttons:$("#saveUserBtn"),
		onWait : "正在对等级序号进行重复性校验，请稍候...",
		onError : "等级序号已经存在",
		success:function(data){
			if(data.status == "success"){
				//验证通过
				return true;
			}
			return data.message;
		}	
	});
	$("#levelName").formValidator({validatorGroup:1}).inputValidator({min:1,max:255,onErrorMin:"等级名称不能为空",onErrorMax:"等级名称长度过长"});
	
	//提前验证
	var itemId = $("#itemId").val();
	if(itemId && itemId.length > 0){
		$.formValidator.pageIsValid(1);
	}
}

function initAjaxForm(){
	$('#levelForm').ajaxForm({
        dataType: 'json',
        success: function(data) {
			if(data.status == "success"){
				$("#id").val(data.message);
				$.dialog.close();
				reloadGrid2();
			}else{
				$.dialog.alert(data.message);
			}
        }
    });
}

function initOperateBtn(){
	// 初始化保存按钮
	$("#saveUserBtn").bind("click",function(){
		var result = $.formValidator.pageIsValid(1);	// 手动调用验证框架进行验证
		if(!result){
			return;
		}
		$("#levelForm").submit();
	});
}

function reloadGrid2(){
	//父页面
	var win = $.dialog.open.origin;
	win.reloadGrid();
}

