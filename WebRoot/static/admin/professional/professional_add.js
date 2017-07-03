var edit,swfu;
$(document).ready(function(){
	initTab();
	initTabSub();
	initFormValidator();
	ajaxForm();
	// 上传
	initSwfu();
	//类型选择框
	$("#type").chosen();
	//编辑器
	editor = UE.getEditor('artContent');
	//选择框
	$("#language").chosen();
	//选择框
	$("#qualification").chosen();
	//选择框
	$("#business").chosen();
	
	//选择框
	$("#languageEn").chosen();
	//选择框
	$("#qualificationEn").chosen();
	//选择框
	$("#businessEn").chosen();
	
	//选择框
	$("#languageJp").chosen();
	//选择框
	$("#qualificationJp").chosen();
	//选择框
	$("#businessJp").chosen();
	editorEn = UE.getEditor('artContentEn');
	editorJp = UE.getEditor('artContentJp');
});

function initSwfu(){
	swfu = new SWFUpload({
		upload_url:base+"uploadImg.htm?m="+Math.random(),
		button_placeholder_id:"spanButtonPlaceholder",	// 浏览按钮
		//最大上传文件大小
		file_size_limit : 1024,//512KB
		//允许上传的文件类型 
//		file_types : "*.png;.PNG;*.doc;*.xls;*.zip;*.rar", // or you could use something like: "*.doc;*.wpd;*.pdf",
		//文件类型描述
		file_types_description : "只能上传png图片",
		//上传文件成功后调用的函数(参数中包含服务器端返回的数据)
		upload_success_handler : uploadPicSuccess,
		custom_settings:{progress_target : "fsUploadProgress"},
		button_image_url : basePath + "/static/admin/base/images/swfupload_browse.png",
		button_width : 70,
		button_height : 25
	});
	
}


//文件上传
function uploadPicSuccess(file, serverData) {
	try {
		file.id = "singlefile";	// This makes it so FileProgress only makes a single UI element, instead of one for each file
		var progress = new FileProgress(file, this.customSettings.progress_target);
		progress.setComplete();
		progress.setStatus("上传完成。");
		progress.toggleCancel(false);
		if (!serverData || serverData.length == 0) {
			this.customSettings.upload_successful = false;
		} else {
			this.customSettings.upload_successful = true;
			var json = eval("("+serverData+")");
			var result = json[0];
			if(result.type&&result.type=="error"){
				progress.setStatus('<span class="status-red">图片验证不合格</span>');
				$.dialog.alert(result.msg);
				return;
			}
			
			var path = json[0].path;	//路径
			$("#path").val(path);
			var filename = $('#txtFileName').val();
			//显示上传后的图片
			if($("#aTP").children('img').length == 0){
				$("#downloadImg").attr("src",path);
			}else{
				$("#downloadImg").attr("src",path);
			}
		}
	} catch (e) {
		alert(e);
	}
}

function initFormValidator(){
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#name").formValidator({validatorGroup:"1",onText:'请输入姓名',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"姓名不能为空",onErrorMax:"姓名长度过长"});
	$("#desc").formValidator({validatorGroup:"1",onText:'请输入人员简介',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"人员简介不能为空",onErrorMax:"人员简介长度过长"});
	$("#qualification").formValidator({validatorGroup:"1",onText:'请选择资格类型',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"请选择资格类型"});
	$("#language").formValidator({validatorGroup:"1",onText:'请输入工作语言',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"工作语言不能为空",onErrorMax:"工作语言长度过长"});
	$("#type").formValidator({validatorGroup:"1",onText:'请输入专业人员类型',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"专业人员类型不能为空"});
	$("#email").formValidator({validatorGroup:"1",onText:'请输邮箱地址',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"邮箱地址不能为空",onErrorMax:"邮箱长度过长"})
	.regexValidator({regExp:"email",dataType:"enum",onError:"邮箱号格式不正确"});
	$("#companyName").formValidator({validatorGroup:"1",onText:'请输入公司名称',onShow:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"公司名称不能为空",onErrorMax:"公司名称长度过长"});
}

function saveSubscribe(){
	var result = $.formValidator.pageIsValid("1");
	if(!result){
		return false;
	}
	editor.sync();
	$("body").mask("保存中，请稍后...");
	$("#subscriptForm").submit();
}
function ajaxForm(){
	$('#subscriptForm').ajaxForm({
		dataType:'json',
		success:function(data){
			$("body").unmask();
			if(data.status == 'success'){
				var win = top.document.frames? top.document.frames("mainIframe_tabli_1dd6db57ed5b11e4aa0400266c0e7760"): top.document.getElementById("mainIframe_tabli_1dd6db57ed5b11e4aa0400266c0e7760").contentWindow;
				win.reloadGrid();
				parent.closeCurrentWin();
			}else{
			 	$.dialog({ content: '保存失败',okVal:"确定",ok: function(){}});
			}
		}
	});
}
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}
//初始化页签
function initTab(){
	// 默认状态
	$('.J-tab-content').eq(0).siblings().hide();
	// 切换tab选项
	$(".J-tab").eq(0).addClass('no-bg').siblings().addClass('yes-bg').removeClass('no-bg');
	$(".J-tab").each(function(i){
		$(this).click(function(){
			$(this).addClass('no-bg').removeClass('yes-bg');
			$(this).siblings().addClass('yes-bg').removeClass('no-bg');
			$('.J-tab-content').eq(i).show().siblings().hide();
		});
	});
	
	$(".J-tab").eq(parseInt(currentTabIndex)).trigger("click");
}

//初始化页签
function initTabSub(){
	// 默认状态
	$('.J-tab-sub-content').eq(0).siblings().hide();
	
	// 切换tab选项
	$(".J-tab-sub").eq(0).addClass('no-bg').siblings().addClass('yes-bg').removeClass('no-bg');
	$(".J-tab-sub").each(function(i){
		$(this).click(function(){
			if(!$(this).is(".no-click")){
				$(this).addClass('no-bg').removeClass('yes-bg');
				$(this).siblings().addClass('yes-bg').removeClass('no-bg');
				$('.J-tab-sub-content').eq(i).show().siblings().hide();
			}
		});
	});
}