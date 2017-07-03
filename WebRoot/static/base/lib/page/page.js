$(document).ready(function () {
	$('.J_loadPaging').each(function(i){
		var type = Number($(this).find('.page_type').val());
		var n = Number($(this).find('.page_n').val());
		var url = $(this).find('.page_url').val();
		var thisPage = Number($(this).find('.page_thisPage').val());
		var lastPage = Number($(this).find('.page_lastPage').val());
		/** type:用于判断是AJAX请求 还是同步请求   0：同步请求  1：AJAX请求 */
		if(type == 0){
			loadPage(n , url , thisPage , lastPage , this);
		}else if(type == 1){
			loadAjaxPage(n , url , thisPage , lastPage , this);
		}
	})
});
function loadPage(n , url , thisPage , lastPage , e){
	if(lastPage ==0){
		lastPage =1;
	}
	// 当前大页数		
	if((thisPage%n) == 0){
		var thisPageNum = (thisPage - (thisPage%n))/n ;
	}else{
		var thisPageNum = (thisPage - (thisPage%n))/n +1;
	}
	// 上一大页
	var firstPage = (thisPageNum -1)*n;
	// 下一大页
	var nextPage = thisPageNum*n +1;
	// 当前最大显示到第几页;
	var maxPage = nextPage;
	if( maxPage >= lastPage+1 ){
		maxPage = lastPage+1
	}
	var first = '<li><a href="'+url+'&page='+firstPage+' "><</a></li>'
	var next = '<li><a href="'+url+'&page='+nextPage+' ">></a></li>'
	
	var previous = '<li><a href="'+url+'&page=1 "><<</a></li>'
	var last = '<li><a href="'+url+'&page='+lastPage+' ">>></a></li>'
	var now = '';
	
	for( var i=firstPage+1; i<maxPage; i++){
		if(i!=thisPage){
			now = now + '<li><a href="'+url+'&page='+i+' ">'+i+'</a></li>'
		}else{
			now = now + '<li><a class="cur" href="'+url+'&page='+i+' ">'+i+'</a></li>'
		}
	}
	if(thisPage>10){
		$(e).find('ul').append(previous)	
		$(e).find('ul').append(first)	
	}
	$(e).find('ul').append(now);
	if(lastPage-10 > firstPage){
		$(e).find('ul').append(next)	
		$(e).find('ul').append(last)	
	}
}


function loadAjaxPage(n , url , thisPage , lastPage , e){
	if(lastPage ==0){
		lastPage =1;
	}
	// 当前大页数		
	if((thisPage%n) == 0){
		var thisPageNum = (thisPage - (thisPage%n))/n ;
	}else{
		var thisPageNum = (thisPage - (thisPage%n))/n +1;
	}
	// 上一大页
	var firstPage = (thisPageNum -1)*n;
	// 下一大页
	var nextPage = thisPageNum*n +1;
	// 当前最大显示到第几页;
	var maxPage = nextPage;
	
	if( maxPage >= lastPage+1 ){
		maxPage = lastPage+1
	}
	var first = '<li><a href="javascript:'+url+'('+firstPage+') "><</a></li>'
	var next = '<li><a href="javascript:'+url+'('+nextPage+') ">></a></li>'
	
	var previous = '<li><a href="javascript:'+url+'(1) "><<</a></li>'
	var last = '<li><a href="javascript:'+url+'('+lastPage+') ">>></a></li>'
	var now = '';
	for( var i=firstPage+1; i<maxPage; i++){
		if(i!=thisPage){
			now = now + '<li><a href="javascript:'+url+'('+i+') ">'+i+'</a></li>'
		}else{
			now = now + '<li><a class="cur" href="javascript:'+url+'('+i+'); ">'+i+'</a></li>'
		}
	}
	if(thisPage>10){
		$(e).find('ul').append(previous)	
		$(e).find('ul').append(first)	
	}
	$(e).find('ul').append(now);
	if(lastPage-10 > firstPage){
		$(e).find('ul').append(next)	
		$(e).find('ul').append(last)	
	}
}
