$(document).ready(function(){
	
//	swfu = new SWFUpload({
//		post_params:{
//			"directory":"maintainorder"
//		},
//		upload_url:base+"upload.htm?m="+Math.random(),
//		//最大上传文件大小
//		file_size_limit : 1024,	//512KB
//		//允许上传的文件类型 
//		file_types : "*.*",			// or you could use something like: "*.doc;*.wpd;*.pdf",
//		//文件类型描述
//		file_types_description : "任何文件",
//		//上传文件成功后调用的函数(参数中包含服务器端返回的数据)
//		upload_success_handler : uploadMaintainOrderAttachmentSuccess,
//		
//		custom_settings:{
//			progress_target : "fsUploadProgress"
//		},
//		button_image_url : basePath + "/static/admin/css/images/swfupload_browse.png",
//		button_width : 63,
//		button_height : 25
//	});
	
	// 验证表单
//	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
//	$("#txtFileName").formValidator({validatorGroup:1}).inputValidator({min:1,onErrorMin:"上传的图片不能为空"});
	
	$('#cancelBtn').live('click',function(){
		$.dialog.close();
	});
	
	$('#saveBtn').live('click',save);
	
});

function doUploadFile(){
	$.ajaxFileUpload({
		url:'ajaxFileUpload.htm',//用于文件上传的服务器端请求地址
		secureuri:false,//一般设置为false
		fileElementId:'fileInput',//文件上传空间的id属性 <input type="file" id="file" name="file" />
		dataType: 'json',//返回值类型一般设置为json
		success: function (data, status){ //服务器成功响应处理函数
			$('#showImg').attr('src',data.url);
			$("#imgPath").val(data.url);
		},
		error: function (data, status, e){//服务器响应失败处理函数
		}
	});
	return false;
}

// 保存广告图片
function save(){
//	var result = $.formValidator.pageIsValid("1");	// 手动调用验证框架进行验证
//	if(!result){
//		return;
//	}
	wait = getBusyOverlay();
	var itemId  = $("#itemId").val();
   	if(null != itemId && "" != itemId){
   		$('#advPhotoForm').attr('action','admin/cms/advPhoto/update.htm');
   		$('#advPhotoForm').ajaxSubmit(function(data){
   			var win = $.dialog.open.origin;
   			win.loadAdvPhotos();
   			$.dialog.close();
   		});
   		
   	}else{
   		$('#advPhotoForm').ajaxSubmit(function(data){
   			var win = $.dialog.open.origin;
   			win.loadAdvPhotos();
   			$.dialog.close();
   		});
   	}
}

// 文件上传
function uploadMaintainOrderAttachmentSuccess(file, serverData) {
	try {
		file.id = "singlefile";	// This makes it so FileProgress only makes a single UI element, instead of one for each file
		var progress = new FileProgress(file, this.customSettings.progress_target);
		progress.setComplete();
		progress.setStatus("上传完成.");
		progress.toggleCancel(false);
		if (!serverData || serverData.length == 0) {
			this.customSettings.upload_successful = false;
		} else {
			this.customSettings.upload_successful = true;
			var json = eval("("+serverData+")");
			
			var path = json[0].path;	//路径
			$("#path").val(path);
			//显示上传后的图片
			if($("#imgTd").children().length == 0){
				 
				//没有元素，创建
				var el = "<img src='"+path+"' id='invoicePathImg' style='width:200px;height:100px;' class='magnify' rel='magnify[sarah]' />";
				
				$("#imgTd").append(el);
				//$("#invoicePathImg").imageMagnify({magnifyby:5});
			}else{
				$("#invoicePathImg").attr("src",path);
				//$("#invoicePathImg").imageMagnify({magnifyby:5});
			}
		}
	} catch (e) {
	
	}
}
