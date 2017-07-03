 /*
 * 保存类别
 */
function addRight() {
	var parentId = $("#parentId").val();
	$("#nameDiv").hide();
	$("#sortDiv").hide();
	var id = $("#parentId").val();
	var name = $("#name").val();
	var sort = $("#sort").val();
	var fuUrl = $("#url").val();
	var tip = $("#tip").val();
	var location = $("#location").val();
	//var remark = $('#remark').html();
	if (null == name || "" == name) {
		$("#nameDiv").show();
		return;
	}
	if (null == sort || "" == sort) {
		$("#sortDiv").show();
		return;
	}
	// 所有异步提交，必须都要加上遮罩，为了防止AJAX重复提交
	$("body",parent.document).mask("正在保存，请稍候...");
	$.post("admin/auth/right/doAddRight.htm", {
				"item.parentId" : id,
				"item.name" : name,
				//"remark" : remark,
				"item.tip":tip,
				"item.sort" : sort,
				"item.url" : fuUrl,
				"item.location" : location
			}, function(data) {
				// 回调函数中去除遮罩
				$("body",parent.document).unmask();
				if (data.status=='success'){
					$.dialog.close();
					var win = $.dialog.open.origin;
					if(undefined == id || id == 0){
						win.reloadTree();
					}else{
						win.loadNewNode(id,data);
					}
				} else {
					$.dialog.alert("保存失败");
				}
			});
}


/**
 * 修改部门类别
 */
function updateDepart() {
	var parentId = $("#parentId").val();
	var name = $("#name").val();
	var sort = $("#sort").val();
	var tip = $("#tip").val();
	var id = $("#id").val();
	var fuUrl = $("#url").val();
	//var location = $("#location").val();
	// 所有异步提交，必须都要加上遮罩，为了防止AJAX重复提交
	$("body",parent.document).mask("正在保存，请稍候...");
	$.post("admin/auth/right/doUpdateRight.htm", {
				"item.id" : id,
				"item.name" : name,
				"item.tip" : tip,
				"item.sort" : sort,
				"item.url" : fuUrl,
				"item.parentId" : parentId
				//"item.location" : location,
		}, function(data) {
			// 回调函数中去除遮罩
			$("body",parent.document).unmask();
			if (data.status=='success') {
				$.dialog.close();
				var win = $.dialog.open.origin;
				win.location.reload();
			} else {
				$.dialog.alert(data.message);
			}
		});
}
// 退出当前窗体
function exitWin() {
	$.dialog.close();
}