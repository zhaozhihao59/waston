$(document).ready(function () {
	doPage();
});
function doPage(){
	var n = $('#n').val();
	var url = $('#url').val();
	// 当前页
	var thisPage = Number( $('#thisPage').val() );
	// 最后一页
	var lastPage = Number( $('#lastPage').val() );
	if(lastPage ==0){
		lastPage =1;
	}
	// 当前大页数
	var thisPageNum = (thisPage - (thisPage%n))/n +1;
	// 上一大页
	var firstPage = (thisPageNum -1)*n;
	// 下一大页
	var nextPage = thisPageNum*n +1;
	// 当前最大显示到第几页;
	var maxPage = nextPage;
	if( maxPage >= lastPage+1 ){
		maxPage = lastPage+1;
	}
	var first = '<li><a class="page_a_bar" page="'+firstPage+'" class="page_a_bar" href="javascript:void(0);"><</a></li>';
	var next = '<li><a class="page_a_bar" page="'+nextPage+'" href="javascript:void(0);">></a></li>';
	
	var previous = '<li><a class="page_a_bar" page="1" href="javascript:void(1);"><<</a></li>';
	var last = '<li><a class="page_a_bar" page="'+lastPage+'" href="javascript:void(1);">>></a></li>';
	var now = '';
	for( var i=firstPage+1; i<maxPage; i++){
		if(i!=thisPage){
			now = now + '<li><a class="page_a_bar" page="'+i+'" href="javascript:void(0);">'+i+'</a></li>';
		}else{
			now = now + '<li><a class="page_a_bar" class="cur" page="'+i+'"  href="javascript:void(0);">'+i+'</a></li>';
		}
	}
	if(thisPage>10){
		$('#page').append(previous);
		$('#page').append(first);	
	}
	$('#page').append(now);
	if(lastPage-10 > firstPage){
		$('#page').append(next);
		$('#page').append(last);
	}
	$("a[page='"+thisPage+"']").addClass("cur");
	
	$(".page_a_bar").live('click',function(){
		$("#serach_page").val($(this).attr("page"));
		$("#searchFromPG").submit();
	});
}
