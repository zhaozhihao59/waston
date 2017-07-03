$(document).ready(function(){
	getPage();
	getSearchPage();
});

function getPage(){
	// 大页显示的数量
	var n = 5;
	// 当前页
	var thisPage = Number( $('#thisPage').val() );
	// 最后一页
	var lastPage = Number( $('#lastPage').val() );
	// 当前大页数
	var thisPageNum = (thisPage - (thisPage%n))/n +1;
	// 上一大页
	var firstPage = (thisPageNum -1)*n;
	// 下一大页
	var nextPage = thisPageNum*n +1;
	// 当前最大显示到第几页;
	var maxPage = nextPage;
	if( maxPage >= lastPage+1 ){
		maxPage = lastPage+1
	}
	var previous = '<li><a href="news/index.htm?pageResult.currentPage='+firstPage+'"><</a></li>'
	var next = '<li><a href="news/index.htm?pageResult.currentPage='+lastPage+'">></a></li>'
	var now = '';
	for( var i=firstPage+1; i<maxPage; i++){
		if(i!=thisPage){
			now = now + '<li><a href="news/index.htm?pageResult.currentPage='+i+'">'+i+'</a></li>'
		}else{
			now = now + '<li><a class="cur" href="news/index.htm?pageResult.currentPage='+i+'">'+i+'</a></li>'
		}
	}
	if(thisPage>10){
		$('#page').append(previous)	
	}
	$('#page').append(now);
	if(lastPage-10 > firstPage){
		$('#page').append(next)	
	}
}

function getSearchPage(){
	// 关键字
	var keyword = $('#searchVal').val();
	// 大页显示的数量
	var n = 5;
	// 当前页
	var thisPage = Number( $('#thisPage').val() );
	// 最后一页
	var lastPage = Number( $('#lastPage').val() );
	// 当前大页数
	var thisPageNum = (thisPage - (thisPage%n))/n +1;
	// 上一大页
	var firstPage = (thisPageNum -1)*n;
	// 下一大页
	var nextPage = thisPageNum*n +1;
	// 当前最大显示到第几页;
	var maxPage = nextPage;
	if( maxPage >= lastPage+1 ){
		maxPage = lastPage+1
	}
	var previous = '<li><a href="news/search.htm?pageResult.currentPage='+firstPage+'&condition.keyword='+keyword+' "><</a></li>'
	var next = '<li><a href="news/search.htm?pageResult.currentPage='+lastPage+'&condition.keyword='+keyword+' ">></a></li>'
	var now = '';
	for( var i=firstPage+1; i<maxPage; i++){
		if(i!=thisPage){
			now = now + '<li><a href="news/search.htm?pageResult.currentPage='+i+'&condition.keyword='+keyword+' ">'+i+'</a></li>'
		}else{
			now = now + '<li><a class="cur" href="news/search.htm?pageResult.currentPage='+i+'&condition.keyword='+keyword+' ">'+i+'</a></li>'
		}
	}
	if(thisPage>10){
		$('#searchPage').append(previous)	
	}
	$('#searchPage').append(now);
	if(lastPage-10 > firstPage){
		$('#searchPage').append(next)	
	}
}


