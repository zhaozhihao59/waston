 /*
 * 保存类别
 */
function addDepart() {
	var parentId = $("#parentId").val();
	$("#nameDiv").hide();
	$("#sortDiv").hide();
	var id = $("#parentId").val();
	var name = $("#name").val();
	var sort = $("#sort").val();
	var note = $("#note").val();
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
	$.post("admin/auth/depart/doAddDepart.htm", {
				"item.parentId" : id,
				"item.name" : name,
				//"remark" : remark,
				"item.note":note,
				"item.sort" : sort
			}, function(data) {
				// 回调函数中去除遮罩
				$("body",parent.document).unmask();
				if (data.status=='success'){
					var win = $.dialog.open.origin;
					if(undefined == id || id == 0){
						win.reloadTree();
					}else{
						
						win.refreshNodes(id,data);
					}
					$.dialog.close();
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
	var note = $("#note").val();
	var id = $("#id").val();
	// 所有异步提交，必须都要加上遮罩，为了防止AJAX重复提交
	$("body",parent.document).mask("正在保存，请稍候...");
	$.post("admin/auth/depart/doUpdateDepart.htm", {
				"item.id" : id,
				"item.name" : name,
				"item.note" : note,
				"item.sort" : sort,
				"item.name" : name,
				"item.parentId" : parentId
		}, function(data) {
			// 回调函数中去除遮罩
			$("body",parent.document).unmask();
			if (data.status=='success') {
				
				var win = $.dialog.open.origin;
				win.reloadNodes(parentId);
				$.dialog.close();
			} else {
				$.dialog.alert(data.message);
			}
		});
}
// 退出当前窗体
function exitWin() {
	$.dialog.close();
}