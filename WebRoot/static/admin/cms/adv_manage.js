var refreshBtn = null;
$(document).ready(function(){
	$("#advPhotoAdd").live('click',function(){
		var index = $(this).attr('position');
		var sort = $(this).attr('sort');
		toAdd(index,sort);
	});
	
	// 默认加载当前广告位下的广告图
	loadAdvPhotos();
	
	// 单击广告图片的时候
	$('.adv-photo-item').live('click',function(){
		var e = $(this);
		var btn = $(e).find('.photo-btn');
		if(e.is('.select')){
			$(e).removeClass('select');
			$(btn).addClass('hide');
		}else{
			$(e).addClass('select');
			$(btn).removeClass('hide');
		}
	});
	
	// 点击图片中的编辑按钮
	$('.J-ap-edit').live('click',function(){
		var id = $(this).attr('data');
		var index = $(this).attr('position');
		toUpdate(id,index);
	});
	
	// 点击图片中的删除按钮
	$('.J-ap-remove').live('click',function(){
		var id = $(this).attr('data');
		remove(id);
	});
	
	// 点击刷新按钮刷新广告图
	$('#refreshBtn').live('click',function(){
		loadAdvPhotos();
	});
});

// 修改
function manageFN(id){
	var tv = {};
	tv.text="管理广告图片";
	tv.url = "admin/cms/adv/to_manage.htm?item.id=" + id;
	tv.tabId = "cms_adv_manage";
	tv.doc = parent.document;
	showTab(tv);
}

// 跳转到添加广告图
function toAdd(index,sort){
	var advId = $('#itemId').val();
	refreshBtn = $('#refreshBtn');
	$.dialog.data('refreshBtn',refreshBtn);
	$.dialog.open(base+'admin/cms/advPhoto/to_add.htm?m='+Math.random()+'&item.advId='+advId,{
		title:'新增图片',
		width:'600px',
		height:'420px'
	});
}

// 跳转到修改广告图
function toUpdate(id,index){
	var advId = $('#itemId').val();
	$.dialog.open(base+'admin/cms/advPhoto/to_update.htm?m='+Math.random()+'&item.id='+id,{
		title:'修改图片',
		width:'600px',
		height:'420px'
	});
}

// 删除广告图
function remove(id){
	$.dialog.confirm("确认要删除吗？",function(){
		var wait = getBusyOverlay();
		$.post(base+'admin/cms/advPhoto/remove.htm?m='+Math.random(),{"item.id":id},function(data){
			wait.remove();
			$.dialog.alert('删除成功');
			loadAdvPhotos();
		});
	});
}

// 加载广告图片
function loadAdvPhotos(){
	var advId = $('#itemId').val();
	$.post(base+'admin/cms/advPhoto/list.htm?m='+Math.random(),{'advId':advId},function(data){
		$('#advPhotoList').html(data);
	});
}