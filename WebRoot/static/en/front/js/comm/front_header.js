$(document).ready(function() {
	hHotNews(1);
	hHotNews(2);
	hHotNews(3);
	hHotNews(4);
	hNotice();
	
	$('.yjfk').click(function(){
		$('#massageDiv').show();
		$('.msg-box').show();
	});
	$('#box-close-btn').click(function(){
		$('#massageDiv').hide();
		$('.msg-box').hide();
	});
	$('#btn-close-btn').click(function(){
		$('#massageDiv').hide();
		$('.msg-box').hide();
	});
	$('#searchBth').click(function(){
		var keyword=$('#searchVal').val();
		window.location.href = 'news/search.htm?condition.keyword='+keyword;
	})
/**	
	//异步表单提交设置
    $('#massageForm').ajaxForm({
        dataType: 'json',
        success: function(data) {
        	if(data.success){
        		$.dialog.alert('提交成功');
        	}else{
        		$.dialog.alert('提交失败');
        	}
        }
    });
*/	
});

function doForm(){
	var content = $('#content').val();
	var email=$('#email').val();
	var other=$('#other').val();
	$.post('massage/addMassage.htm',{'item.content':content,'item.email':email,'item.other':other},function(data){
		if(data.success){
			alert('保存成功');
			$('#massageDiv').hide();
			$('.msg-box').hide();
		}else{
			alert('保存失败');
		}
	});
	
	$('#massageForm').submit();
}

function hHotNews(id){
	$.post('head.htm',{'channelId':id,'pageSize':5},function(data){
		var listHtml = '';
		for(var i =0;i<data.length;i++){
			var news = data[i];
			listHtml = listHtml+'<div class="news-list mt10"><div class="technique-dian fl"></div><a class="news-title fl" href="news/detail.htm?item.id='+news.id+'">'+news.name+'</a><div class=" fr">'+news.date+'</div><div class="cb"></div></div>';
		}
		if(id == 1){
			$('#headHotNews').append(listHtml);
		}
		if(id == 2){
			$('#headViewpoint').append(listHtml);
		}
		if(id == 3){
			$('#headProfession').append(listHtml);
		}
		if(id == 4){
			$('#headDownload').append(listHtml);
		}
		
	});
}

function hNotice(){
	$.post('head.htm',{'channelId':5,'pageSize':5},function(data){
		var listHtml = '';
		for(var i =0;i<data.length;i++){
			var news = data[i];
			listHtml = listHtml+'<li><a href="news/detail.htm?item.id='+news.id+'" target="_blank">'+news.name+'</a></li>';
		}
		$('#fontlist').append(listHtml);
		
	});
}
		

			
