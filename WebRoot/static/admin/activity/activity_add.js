var edit,swfu;
$(document).ready(function(){
	initTab();
	initTabSub();
	// 验证
	initFormValidator();
	// 上传
	initSwfu();
	initForm();
	editor = UE.getEditor('artContent');
	editorEn = UE.getEditor('artContentEn');
	editorJP = UE.getEditor('artContentJp');
});

function initForm(){
	//异步表单提交设置
    $('#articleForm').ajaxForm({
        dataType: 'json',
        success: function(data) {
        	$("body",parent.document).unmask;
        	if(data.stauts = 'success'){
        		$("#itemId").val(data.message);
        		$.dialog.alert('保存成功');
        		closeCurrentTab();
        	}else if(data.stauts = 'error'){
        		$.dialog.alert('保存失败');
        	}
        }
    });
}
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
// 验证
function initFormValidator(){
	//验证框架信息
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#status").formValidator({validatorGroup:1}).inputValidator({min:1,max:2000,onErrorMin:"会议状态不能选择全部"});
	$("#title").formValidator({validatorGroup:1}).inputValidator({min:1,max:2000,onErrorMin:"会议标题不能为空",onErrorMax:"会议标题长度过长"});
	$("#dateStr").formValidator({validatorGroup:1}).inputValidator({min:1,max:2000,onErrorMin:"会议时间不能为空",onErrorMax:"会议时间长度过长"});
	$("#address").formValidator({validatorGroup:1}).inputValidator({min:1,max:255,onErrorMin:"活动地址不能为空",onErrorMax:"活动地址长度过长"});
	
}
 
//提交表单
function submitForm(frm){
	var result = $.formValidator.pageIsValid(1);	//手动调用验证框架进行验证
	if(!result){
		return;
	}
	editor.sync();
   	var itemId  = $("#itemId").val();
	$(frm).submit();
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
				var el = " <div class='yl-qy'> <img id='downloadImg' src='"+path+"' style='max-width: 200px;max-height: 100px'/></div>";
				$("#aTP").html(el);
			}else{
				$("#downloadImg").attr("src",path);
			}
		}
	} catch (e) {
		alert(e);
	}
}

/** 关闭当前页 */
function closeCurrentTab(){
	var parent_tabli = "tabli_a088da68e87b11e4ae9b00266c0e7760";
	var cur_tabli = "cms_article_add";
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