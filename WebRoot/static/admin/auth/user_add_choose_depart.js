$(document).ready(function() {
	// 生成Ztree
	var setting = {
		async : {
			enable : true,
			url : treeLoadUrl,
			autoParam : [ "id", "name", "isDelete", "parentId", "chkDisabled" ]
		},
		view : {
			nameIsHTML : true
		},
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "s",
				"N" : "s"
			}
		}
	};

	$.fn.zTree.init($("#departTree"), setting);
	zTree = $.fn.zTree.getZTreeObj("departTree");

});

function treeLoadUrl(treeId, treeNode) {
	return treeNode == null ? "admin/auth/depart/list_depart.htm"
			: "admin/auth/depart/list_depart.htm?parentId =" + treeNode.id;
}

function chooses() {
	var rows = [];
	zTree1 = $.fn.zTree.getZTreeObj("departTree");
	var nodes = zTree1.getCheckedNodes(true);
	for (var i = 0; i < nodes.length; i++) {
		rows.push(nodes[i]);
	}
	
	var win = art.dialog.open.origin;
	win.addDepartRow(rows);
	$.dialog.close();
}
