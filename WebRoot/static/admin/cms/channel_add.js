/**
 * 保存类别
 */
function addCmsChannel(parentId) {
	$("#nameDiv").hide();
	$("#sortDiv").hide();
	var id = $("#parentId").val();
	var name = $("#name").val();
	var sort = $("#sortNum").val();
	var remark = $('#remark').html();
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
	$.post("admin/cms/channel/doAddCmsChannel.htm", {
				"parentId" : id,
				"name" : name,
				"remark" : remark,
				"sortNum" : sort
			}, function(data) {
				// 回调函数中去除遮罩
				$("body",parent.document).unmask();
				if (data.status=='success'){
					$.dialog.close();
					var win = $.dialog.open.origin;
					if(undefined == id || id == 0){
						win.reloadTree();
					}else{
						win.reloadNodes(id);
					}
				} else {
					$.dialog.alert("保存失败");
				}
			});
}


/**
 * 修改栏目类别
 */
function updateCmsChannel() {
	var id = $("#nodeId").val();
	var name = $("#name").val();
	var sort = $("#sortNum").val();
	// 所有异步提交，必须都要加上遮罩，为了防止AJAX重复提交
	$("body",parent.document).mask("正在保存，请稍候...");
	$.post("admin/cms/channel/doUpdateCmsChannel.htm", {
				"nodeid" : id,
				"name" : name,
				"sortNum" : sort
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