$(document).ready(function(){
	calculateWidth();
	window.onresize = function _doResize(){
	 	calculateWidth();
	}
});

function calculateWidth(){
	var w = $(window).width();
	var wt = $(".msg-item-title").width();
	var wc = w - wt - 12 - 30 - 12;
	$(".msg-item-content").width(wc);
}

function readProduct(id){
	
}

//修改回复状态
function isReply(){
	var id = $('#sid').val();
	$.post('admin/cms/message/isReply.htm?m='+Math.random(),{'item.id':id},function(data){
		if(data.success){
			$.dialog.alert('操作成功');
		}else{
			$.dialog.alert('操作失败');
		}
	});
}

//删除
function doDel(){
	var id = $('#sid').val();
	$.post('admin/cms/message/doDel.htm?m='+Math.random(),{'ids':id},function(data){
		if(data.success){
			$.dialog.alert('删除成功');
		}else{
			$.dialog.alert('删除失败');
		};
	});
}

