var edit,swfu2;
$(document).ready(function(){
	initTab();
	initTabSub();
	//加载日期控件
	$("#createDatefmt").calendar({ format:'yyyy-MM-dd HH:mm' });
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
	
/** 附件上传 */
	swfu2 = new SWFUpload({
		button_placeholder_id:"spanButtonPlaceholder2",	// 浏览按钮
		file_dialog_start_handler:fileDialogStartTwo,	 // 开始的方法
		file_queued_handler:fileQueuedTwo,
		upload_url:base+"upload.htm?m="+Math.random(),	// 上传地址 
		//最大上传文件大小
		file_size_limit : 10240,	//512KB
		//允许上传的文件类型 
		file_types : "*.*",	 // or you could use something like: "*.doc;*.wpd;*.pdf",
		//上传文件成功后调用的函数(参数中包含服务器端返回的数据)
		upload_success_handler : uploadSuccessTwo,
		button_image_url : basePath + "/static/admin/base/images/swfupload_browse.png",
		button_width : 70,
		button_height : 25,
		// 进度提示文字
		custom_settings:{
			progress_target : "fsUploadProgress2"
		}
	});
 	

	editor = UE.getEditor('artContent');
	editorEn = UE.getEditor('artEnContent');
	editorJp = UE.getEditor('artJpContent');
	
	// 调整KindEditor的宽度大小
	resize();
	window.onresize = function _doResize(){
	 	resize();
	}
	
	// 生成Ztree
	var setting = {
		async : {
			enable : true,
			url : treeLoadUrl,
			autoParam : ["id", "name", "level"]
		},
		callback : {
			// onClick: clickTreeFun
			onClick: onClickTree
		},
		view : {
		// fontCss: getFontCss
		}
	};
	$.fn.zTree.init($("#treeDemo"), setting);
	zTree = $.fn.zTree.getZTreeObj("treeDemo");
	
	$('#channelChoose').focus(function(){
		var cityObj = $("#channelChoose");
		var cityOffset = $("#channelChoose").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	});
	
	//验证框架信息
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#createDatefmt").formValidator({validatorGroup:1}).inputValidator({min:1,max:255,onErrorMin:"发布时间不能为空",onErrorMax:"发布时间长度过长"});
	$("#itemName").formValidator({validatorGroup:1}).inputValidator({min:1,onErrorMin:"文章标题不能为空"});
	$("#summary").formValidator({validatorGroup:1}).inputValidator({min:1,max:2000,onErrorMin:"文章摘要不能为空",onErrorMax:"文章摘要长度过长"});
	$("#channelName").formValidator().inputValidator({min:1,onError: "请选择栏目名称"}).defaultPassed();
	$("#itemContent").formValidator({validatorGroup:1}).inputValidator({min:1,max:255,onErrorMin:"内容不能为空",onErrorMax:"内容长度过长"});
	
	
	//异步表单提交设置
    $('#articleForm').ajaxForm({
        dataType: 'json',
        success: function(data) {
        	$("body",parent.document).unmask();
        	if(data.stauts = 'success'){
        		$("#itemId").val(data.message);
        		$.dialog.alert('保存成功');
        	}else if(data.stauts = 'error'){
        		$.dialog.alert('保存失败');
        	}
        }
    });
    initSwf();
});

function treeLoadUrl(treeId, treeNode) {
	return treeNode == null ? "admin/cms/channel/list_channel.htm?parentId=0" : "admin/cms/channel/list_channel.htm?parentId="+treeNode.id;
}

//zTree点击事件		
function onClickTree(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTree.getSelectedNodes();
	$('#channelChoose').val(nodes[0].name);
	$("#channelId").val(nodes[0].id);
	hideMenu();
}

function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}
function resize(){
	var w = $('.caption-div').width();
	var nw = w + 8;
	$('.art-content').width(nw);
}
//提交表单
function submitForm(frm){
	var result = $.formValidator.pageIsValid(1);	//手动调用验证框架进行验证
	if(!result){
		return;
	}
	$("body",parent.document).mask("正在保存，请稍候...");
	editor.sync();
	editorEn.sync();
	editorJp.sync();
   	var itemId  = $("#itemId").val();
   	if(null != itemId && "" != itemId){
   		$(frm).attr("action","admin/cms/article/updateArticle.htm");
    	$(frm).submit();
   	}else{
   		$(frm).attr("action","admin/cms/article/addArticle.htm");
   		$(frm).submit();
   	}
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
				//没有元素，创建
				var el = "<div class='yl-qy'> <img id='downloadImg' src='"+path+"' style='max-width: 200px;max-height: 100px'/></div>";
				$("#aTP").html(el);
			}else{
				$("#downloadImg").attr("src",path);
			}
		}
	} catch (e) {
		alert(e);
	}
}

/** 第二个上传用到的方法 */
function fileQueuedTwo(file) {
	try {
		var txtFileName = document.getElementById("txtFileName2");
		txtFileName.value = file.name;
	} catch (e) {
	}
}
 
function fileDialogStartTwo() {
	var txtFileName = document.getElementById("txtFileName2");
	txtFileName.value = "";
	this.cancelUpload();
}

function fileQueuedEn(file) {
	try {
		var txtFileName = document.getElementById("txtFileNameEn");
		txtFileName.value = file.name;
	} catch (e) {
	}
}
 
function fileDialogStartEn() {
	var txtFileName = document.getElementById("txtFileNameEn");
	txtFileName.value = "";
	this.cancelUpload();
}
function fileQueuedJp(file) {
	try {
		var txtFileName = document.getElementById("txtFileNameJp");
		txtFileName.value = file.name;
	} catch (e) {
	}
}
 
function fileDialogStartJp() {
	var txtFileName = document.getElementById("txtFileNameJp");
	txtFileName.value = "";
	this.cancelUpload();
}
// 开始上传按钮
function doUploadFile2(e) {
	try {
		if(swfu2.getFile() != null){
			swfu2.startUpload();
		}else{
			$.dialog({title: '上传失败',content: '请先选择文件',ok: function(){}});
		}
	} catch (ex) {
	}
	return false;
}

function doUploadFileEn(e) {
	try {
		if(swfuEn.getFile() != null){
			swfuEn.startUpload();
		}else{
			$.dialog({title: '上传失败',content: '请先选择文件',ok: function(){}});
		}
	} catch (ex) {
	}
	return false;
}
function doUploadFileJp(e) {
	try {
		if(swfuJp.getFile() != null){
			swfuJp.startUpload();
		}else{
			$.dialog({title: '上传失败',content: '请先选择文件',ok: function(){}});
		}
	} catch (ex) {
	}
	return false;
}

// 文件上传
function uploadSuccessTwo(file, serverData) {
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
			$("#path2").val(path);
		}
	} catch (e) {
	
	}
}

//文件上传
function uploadSuccessEn(file, serverData) {
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
			$("#pathEn").val(path);
		}
	} catch (e) {
	
	}
}
function uploadSuccessJp(file, serverData) {
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
			$("#pathJp").val(path);
		}
	} catch (e) {
	
	}
}
//初始化上传
function initSwf(){
	swfuEn = new SWFUpload({
		button_placeholder_id:"spanButtonPlaceholderEn",	// 浏览按钮
		file_dialog_start_handler:fileDialogStartEn,	 // 开始的方法
		file_queued_handler:fileQueuedEn,
		upload_url:base+"upload.htm?m="+Math.random(),	// 上传地址 
		//最大上传文件大小
		file_size_limit : 10240,	//512KB
		//允许上传的文件类型 
		file_types : "*.*",	 // or you could use something like: "*.doc;*.wpd;*.pdf",
		//上传文件成功后调用的函数(参数中包含服务器端返回的数据)
		upload_success_handler : uploadSuccessEn,
		button_image_url : basePath + "/static/admin/base/images/swfupload_browse.png",
		button_width : 70,
		button_height : 25,
		// 进度提示文字
		custom_settings:{
			progress_target : "fsUploadProgressEn"
		}
	});
	swfuJp = new SWFUpload({
		button_placeholder_id:"spanButtonPlaceholderJp",	// 浏览按钮
		file_dialog_start_handler:fileDialogStartJp,	 // 开始的方法
		file_queued_handler:fileQueuedJp,
		upload_url:base+"upload.htm?m="+Math.random(),	// 上传地址 
		//最大上传文件大小
		file_size_limit : 10240,	//512KB
		//允许上传的文件类型 
		file_types : "*.*",	 // or you could use something like: "*.doc;*.wpd;*.pdf",
		//上传文件成功后调用的函数(参数中包含服务器端返回的数据)
		upload_success_handler : uploadSuccessJp,
		button_image_url : basePath + "/static/admin/base/images/swfupload_browse.png",
		button_width : 70,
		button_height : 25,
		// 进度提示文字
		custom_settings:{
			progress_target : "fsUploadProgressJp"
		}
	});
}

/** 关闭当前页 */
function closeCurrentTab(){
	var parent_tabli = "tabli_22";
	var cur_tabli = "cms_article_update";
	//刷新之前的列表页
     try{
    	 var viewId = "mainIframe_"+parent_tabli;
			var parentTab = top.document.getElementById(viewId);
			var win;
			// 退货单页面存在
			if (null != parentTab) {
			var win = parentTab.contentWindow;
			win.reloadGrid();
			$("#" + parent_tabli, parent.document).click();
			$("#"+cur_tabli,parent.document).remove();
			}
     }catch(e){
     }
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