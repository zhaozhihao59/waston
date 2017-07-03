$(document).ready(function($){

	//幻灯片
	var bigpics=$(".picshow .picwrap img");
	var smallpics=$(".picshow .buttons li img.menu");
	var ptitles=$(".picshow .pic_titles li p");
	for(i=0;i<smallpics.length;i++){
		smallpics.eq(i).attr("src",bigpics.eq(i).attr("src")).attr("alt",bigpics.eq(i).attr("alt"));
		ptitles.eq(i).html(bigpics.eq(i).attr("alt"));
	}
	
	$(".picshow .buttons li").click(function(){
		if($(".picshow .picwrap li").is(":animated")||$(this).hasClass("on")) return;
		var indexNow=$(".picshow .buttons li").index(this);
		var currMenu=$(".picshow .buttons li").filter(".on");
		var currPic=$(".picshow .picwrap li").filter(":visible");
		nowPic=$(".picshow .picwrap li").eq(indexNow);
		currPic.fadeOut(800);
		nowPic.fadeIn(800);
		currMenu.removeClass("on");
		$(this).addClass("on");
	}).hover(function(){
		var ptitles=$(".picshow .pic_titles li p");
		var pindex=$(".picshow .buttons li").index($(this));
		ptitles.eq(pindex).show("slow");
	},function(){
		var ptitles=$(".picshow .pic_titles li p");
		var pindex=$(".picshow .buttons li").index($(this));
		ptitles.eq(pindex).hide("slow");
	});
	
		
	//自动轮换
	maxPiclist=$( ".picshow .buttons ul" ).children().length;
	currPiclist=0;
	$( ".picshow .buttons ul,.picshow .picwrap" ).mouseout( function(){
		var index=$(".picshow .buttons ul").children().index( $(".picshow .buttons ul").children(".on") );
		currPiclist=index;
		intPiclist=setInterval(function(){
			currPiclist++;
			if( currPiclist==maxPiclist ) currPiclist=0; 
			$(".picshow .buttons ul").children().eq( currPiclist ).one( "click",function(e){ e.stopPropagation(); });
			$(".picshow .buttons ul").children().eq( currPiclist ).click(); 
		},3500);
	});
	$( ".picshow .buttons ul,.picshow .picwrap" ).mouseover( function(){ if(intPiclist) clearInterval( intPiclist );});
	$( ".picshow .picwrap" ).mouseout();

});