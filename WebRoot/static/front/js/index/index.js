$(document).ready(function() {
	//默认状态
	var lis = $('.paion-ul li');
	var liNum =lis.length;
	// 当前显示li所在位置
	var curIndex = 0;
	$(".pa-right-btn").live("click",function(){
		if(liNum - curIndex >5){
			$(lis).eq(curIndex).animate({marginLeft: '-187px'}, "slow");
			curIndex = curIndex+1;
		}
	});
	$(".pa-left-btn").live("click",function(){
		if(curIndex >0){
			$(lis).eq(curIndex-1).animate({marginLeft: '57px'}, "slow");
			curIndex = curIndex-1;
		}
	});
	
	$(".mmb-big-pd").hover(function(){
		$(this).addClass("cur");
		},function(){
		$(this).removeClass("cur");
	});
	$(".mmb-small-pd").hover(function(){
		$(this).addClass("cur");
		},function(){
		$(this).removeClass("cur");
	});
	$(".brank-paion-ul li a").hover(function(){
		$(this).children("i").show();
		},function(){
		$(this).children("i").hide();
	});
});
$(document).ready(function() {
	//默认状态
	var blis = $('.brank-paion-ul li');
	var bliNum =blis.length;
	// 当前显示li所在位置
	var bcurIndex = 0;
	$(".brank-right-btn").live("click",function(){
		if(bliNum - bcurIndex >2){
			$(blis).eq(bcurIndex).animate({marginLeft: '-303px'}, "slow");
			bcurIndex = bcurIndex+1;
		}
	});
	$(".brank-left-btn").live("click",function(){
		if(bcurIndex >0){
			$(blis).eq(bcurIndex-1).animate({marginLeft: '0px'}, "slow");
			bcurIndex = bcurIndex-1;
		}
	});
});

$(document).ready(function() {
	//默认状态
	var tlis = $('.type-paion-ul li');
	var tliNum =tlis.length;
	// 当前显示li所在位置
	var tcurIndex = 0;
	$(".type-right-btn").live("click",function(){
		if(tliNum - tcurIndex >5){
			$(tlis).eq(tcurIndex).animate({marginLeft: '-303px'}, "slow");
			tcurIndex = tcurIndex+1;
		}
	});
	$(".type-left-btn").live("click",function(){
		if(tcurIndex >0){
			$(tlis).eq(tcurIndex-1).animate({marginLeft: '0px'}, "slow");
			tcurIndex = tcurIndex-1;
		}
	});
});
$(document).ready(function() {
	//默认状态
	var tlis2 = $('.type-paion-ul2 li');
	var tliNum2 =tlis2.length;
	// 当前显示li所在位置
	var tcurIndex2 = 0;
	$(".type-right-btn2").live("click",function(){
		if(tliNum2 - tcurIndex2 >5){
			$(tlis2).eq(tcurIndex2).animate({marginLeft: '-303px'}, "slow");
			tcurIndex2 = tcurIndex2+1;
		}
	});
	$(".type-left-btn2").live("click",function(){
		if(tcurIndex2 >0){
			$(tlis2).eq(tcurIndex2-1).animate({marginLeft: '0px'}, "slow");
			tcurIndex2 = tcurIndex2-1;
		}
	});
});
$(document).ready(function() {
	//默认状态
	var tlis3 = $('.type-paion-ul3 li');
	var tliNum3 =tlis3.length;
	// 当前显示li所在位置
	var tcurIndex3 = 0;
	$(".type-right-btn3").live("click",function(){
		if(tliNum3 - tcurIndex3 >5){
			$(tlis3).eq(tcurIndex3).animate({marginLeft: '-303px'}, "slow");
			tcurIndex3 = tcurIndex3+1;
		}
	});
	$(".type-left-btn3").live("click",function(){
		if(tcurIndex3 >0){
			$(tlis3).eq(tcurIndex3-1).animate({marginLeft: '0px'}, "slow");
			tcurIndex3 = tcurIndex3-1;
		}
	});
});
$(document).ready(function() {
	//默认状态
	var tlis4 = $('.type-paion-ul4 li');
	var tliNum4 =tlis4.length;
	// 当前显示li所在位置
	var tcurIndex4 = 0;
	$(".type-right-btn4").live("click",function(){
		if(tliNum4 - tcurIndex4 >5){
			$(tlis4).eq(tcurIndex4).animate({marginLeft: '-303px'}, "slow");
			tcurIndex4 = tcurIndex4+1;
		}
	});
	$(".type-left-btn4").live("click",function(){
		if(tcurIndex4 >0){
			$(tlis4).eq(tcurIndex4-1).animate({marginLeft: '0px'}, "slow");
			tcurIndex4 = tcurIndex4-1;
		}
	});
});
$(document).ready(function() {
	//默认状态
	var tlis5 = $('.type-paion-ul5 li');
	var tliNum5 =tlis5.length;
	// 当前显示li所在位置
	var tcurIndex5 = 0;
	$(".type-right-btn5").live("click",function(){
		if(tliNum5 - tcurIndex5 >5){
			$(tlis5).eq(tcurIndex5).animate({marginLeft: '-303px'}, "slow");
			tcurIndex5 = tcurIndex5+1;
		}
	});
	$(".type-left-btn5").live("click",function(){
		if(tcurIndex5 >0){
			$(tlis5).eq(tcurIndex5-1).animate({marginLeft: '0px'}, "slow");
			tcurIndex5 = tcurIndex5-1;
		}
	});
});

$(function(){
				function navSlider(){
					var $nav = $('.navs'),
					$cur = $('.navs li.cur a'),
					$navLine = $('.nav-line'),
					$anchor = $('a',$nav.children()),
					curPosL = $cur.position().left,
					curW = $cur.outerWidth(true),
					curIdx = $('li.cur',$nav).index();
					$navLine.css({'width':curW,'left':curPosL});
					$anchor.not('li.last a',$nav).each(function(index){
						var posL = $(this).position().left,
						w = $(this).outerWidth(true);
						$(this).click(function(){
							$navLine.animate({'width':w,'left':posL},550);
							$(this).parent().addClass('cur').siblings().removeClass('cur');
						});
					});
					
				};
			navSlider();
			});
			$(".cpm-ul li").eq(0).addClass("cur");
			$(".mmb-cont").eq(0).show().siblings(".mmb-cont").hide();
			$(".cpm-ul li").each(function(i){
				$(this).click(function(){
					$(this).addClass("cur").siblings().removeClass("cur");
					$(".mmb-cont").eq(i).show().siblings(".mmb-cont").hide();
				});
			});
			
