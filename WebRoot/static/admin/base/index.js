var northHeight,westWidth;
var fullScreenStatus=false;//false表示当前状态为：不是全屏
var basePath = null;
$(document).ready(function () {
	//解决IE6 7中iframe的height 100% 不起作用的问题
	if($.browser.version<8){
		window.setInterval(resizeIframe4IE,500);
	}
	
	basePath = $("base").attr("href");
	initMenu();
	$('.west-menu h2').live('click',function(){
		var state=$(this).next("ul").css('display');
		if(state=='none'){
			$(this).next("ul").show('fast');
			$(this).children('a').css('background-position','6px 7px');
		}else{
			$(this).next("ul").hide('fast');
			$(this).children('a').css('background-position','6px -32px');
		}
	})
	
	northHeight=$('#north').height();
	westWidth=$('#west').width();
	var speed=300;
	//向上缩起
	$("#north-seperate .icon-h").click(function(){
		// 验证当前 north-seperate 的 top 值
		var top=$("#north-seperate").offset().top;
		if(top>0){
			$(this).addClass("icon-bottom");
			$("#north").fadeOut(function(){
				$("#north-seperate").animate({top: 0}, speed );
				//减小左侧、左侧分隔栏、右侧、右侧标签页 top
				var westTop=$('#west').offset().top-northHeight;
				$('#west').animate({top: westTop}, speed );
				$("#west-seperate").animate({top: westTop}, speed );
				$('#menubar').animate({top: westTop}, speed );
				$('#east').animate({top: $('#east').offset().top-northHeight}, speed );
			});
		}else{
			$(this).removeClass("icon-bottom");
			$("#north-seperate").animate({top: northHeight}, speed );
			//减小左侧、左侧分隔栏、右侧、右侧标签页 top
			var westTop=$('#west').offset().top+northHeight;
			$('#west').animate({top: westTop}, speed );
			$("#west-seperate").animate({top: westTop}, speed );
			$('#menubar').animate({top: westTop}, speed );
			$('#east').animate({top: $('#east').offset().top+northHeight}, speed ,function(){
				$("#north").fadeIn();
			});
		}
	});
	//向左缩起
	$("#west-seperate .icon-v").click(function(){
		// 验证当前 north-seperate 的 top 值
		var left=$("#west-seperate").offset().left;
		
		if(left>0){
			$(this).addClass("icon-right");
			$("#west-seperate").animate({left: 0}, speed );
			var moveLeft=$('#east').offset().left-westWidth;
			$('#east').animate({left: moveLeft}, speed );
			$('#menubar').animate({left: moveLeft}, speed );
		}else{
			$(this).removeClass("icon-right");
			$("#west-seperate").animate({left: westWidth}, speed );
			var moveLeft=$('#east').offset().left+westWidth;
			$('#east').animate({left: moveLeft}, speed );
			$('#menubar').animate({left: moveLeft}, speed );
		}
	});
	
		//全屏控制
	$("#full_screen_btn").click(function(){
		var left=$("#west-seperate").offset().left;
		var top=$("#north-seperate").offset().top;
		if(fullScreenStatus){
			$(this).attr('title','全屏');
			if(!top>0){
				$("#north-seperate .icon-h").click();
			}
			if(!left>0){
				$("#west-seperate .icon-v").click();
			}
			fullScreenStatus=false;
		}else{
			$(this).attr('title','退出全屏');
			if(top>0){
				$("#north-seperate .icon-h").click();
			}
			if(left>0){
				$("#west-seperate .icon-v").click();
			}
			fullScreenStatus=true;
		}
	});
	
	$(".west-menu h2").toggle(function(){
		$(this).next("ul").hide();
		$(this).children("a").css("background-position","15px -33px");
	},function(){
		$(this).next("ul").show();
		$(this).children("a").css("background-position","15px 6px");
	});
	
	//tabs 分页控制
	$("#btn_tabs_prev").click(function(){
		//找到当前页
		var current;
		$("#menubar_tabs").find("a").each(function(i,n){
		
			var cls=$(this).attr("class");
			if(cls.indexOf("currenttab") != -1){
				current=$(this);
				return false;
			}
	    });
	   
	   	var prev=$(current).parent().prev();
	    //找上一页
	    var currentId=$(prev).children("a").attr("id");
	    if(currentId){
	    	//找到权限id
	    	//currentId=currentId.substring(6,currentId.length);
	    	//翻页
	    	changeContentTab(currentId);
	    }else{
	    	$.msgbox({msg:"已经是第一页了",icon:"warning"});
	    }
	
	});
	
	$("#btn_tabs_next").click(function(){
		//找到当前页
		var current;
		$("#menubar_tabs").find("a").each(function(){
			var cls=$(this).attr("class");
			if(cls.indexOf("currenttab") != -1){
				current=$(this);
				return false;
			}
	    });
	   var next=$(current).parent().next();
	    //找下一页
	    var currentId=$(next).children("a").attr("id");
	    if(currentId){
	    	//找到权限id
	    	//currentId=currentId.substring(6,currentId.length);
	    	//翻页
	    	changeContentTab(currentId);
	    }else{
	    	$.msgbox({msg:"已经是最后一页了",icon:"warning"});
	    }
	});
	
});
function resizeIframe4IE(){
	var h=$("#east").height();
	$("#east").find('iframe').height(h);
}
var menu;
var currTab = "tabli_0";
var manuMenuId="#nav_top";
//初始化系统菜单
function initMenu() {
	url = "admin/loadAdminRights.htm?m="+Math.random(); 
	
	$.post(url,null,function (data) {
		menu = $.parseJSON(data);
		initMainMenu();
	},"text");
}
			
//初始化主导航
function initMainMenu() {
	$.each(menu, function (k, v) {
		var item = $("<li><a href=\"javascript:;\" id=\"tab_" + v.id + "\">" + v.text + "</a></li>");
		item.children("a").click(function () {
			var tabName = this.id.substr(4);
			switchTab(tabName);
			$("#nav_top").find("a").removeClass("select");
			$(this).addClass("select");
			
			//触发第一个选中
			//$("#west").find("ul").children("li:eq(0)").children("a").click();
		});
		$(manuMenuId).append(item);
	});
	switchFirstTab();
}

function switchFirstTab(){
	for(var i=0;i<menu.length;i=i+1){
		currTab = menu[i].id;
		pickTab();
		initSubmenu();
		break;
	}
}
function switchTab(tabName) {
	currTab = tabName;
	pickTab();
	initSubmenu();
}
function pickTab() {
	var id = "#tab_" + currTab;
	$(manuMenuId).find("a").each(function () {
		$(this).removeClass("cur");
	});
	$(id).addClass("cur");
}

//初始化子导航
function initSubmenu() {
	var m = 0;
	for(var i=0;i<menu.length;i=i+1){
		if(currTab == menu[i].id){
			m = menu[i];
		}
	}	
	$("#west").empty();	
	$.each(m.children, function (k, v) {		
		var wElement=$("<div class=\"west-menu\"><h2><a href=\"javascript:void(0);\">" + v.text + "</a></h2><ul class=\"pt5 pb5\"></ul></div>");	
		$.each(v.children, function (tk, tv) {			
			var liElement=$("<li></li>");	
			var aElement=$("<a href=\"javascript:;\" id=\"nav_rights_"+tv.id+"\" >"+tv.text+"</a>");			
			$(aElement).click(function(){
				tv.linkId = $(this).attr("id");
				tv.tabId =  "tabli_"+tv.id;
				showTab(tv);
			});
			$(liElement).append(aElement);
			$(wElement).find("ul").append(liElement);
		});
		$("#west").append(wElement);
	});
}
//打开指定URL的页面
function showMyTab(url){
	for(var i=0;i<menu.length;i++){
		var m=menu[i];
		for(var x=0;x<m.children.length;x++){
			var mc=m.children[x];
			for(var y=0;y<mc.children.length;y++){
				var tmc=mc.children[y];
				if(tmc!=null&&tmc.url==url){
					tmc.tabId = "tabli_"+tmc.id;
					showTab(tmc);
					return;
				}
			}
		}
	}
}
//切换选项卡
function changeContentTab(tabId){
	//取消所有tab的选中状态
	$("#menubar_tabs").find("a").removeClass("currenttab");
	$("#menubar_tabs").find("a span").addClass("b_gray");
	
	$("#"+tabId).addClass("currenttab");
	//隐藏所有的div
	$("#east iframe").hide();
	$("#mainIframe_"+tabId).show();
	
	var cur=$("#"+tabId).parent();
    if($(cur).html()){
   		var ot=$(cur).attr("offsetTop");
		$("#menubar_tabs").attr("scrollTop",ot);
   	}
}

//关闭指定选项卡
function closeTab(tabId,event){
	//判断要关闭的tab是否是当前tab
	var tab = $("#"+tabId);
	var cls = tab.attr("class");
	if(cls == "currenttab"){
		//存在前一个,高亮它
		//存在后一个,高亮它
		var p=tab.parent().next();
		if(!p || p.length == 0){
			p = tab.parent().prev();	//存在后一个
		}
		
		if(p && p.length != 0){
			$(p).children("a").addClass("currenttab");
			
		//	$(p).find("span").removeClass("b_gray");
			var preId = $(p).children("a").attr("id");
			$("#mainIframe_"+preId).show();
			
			var ot=$(p).attr("offsetTop");
			$("#menubar_tabs").attr("scrollTop",ot);
		}
	}
	tab.parent().remove();
	$("#mainIframe_"+tabId).remove();
	if (event && event.stopPropagation){
      	event.stopPropagation();
    }else{
       	window.event.cancelBubble=true;
    }
}

// 点击施乐后台首页
function showIndex(){
	var tv = {};
	tv.url = "admin/welcome.htm";
	tv.text = "首&nbsp;&nbsp;页";
	tv.tabId = "tabli_0";
	tv.refresh = true;
	showTab(tv);
}

function showHome(){
	//显示首页旁边的一级菜单中的二级菜单内容
	$("#nav_top li:eq(1)").children("a").click();
	
	//隐藏所有的iframe
	$("#east iframe").hide();
	//取消所有tab的选中状态
	$("#menubar_tabs").find("a").removeClass("currenttab");
	
	//显示
	$("#tabli_0").addClass("currenttab");
	$("#tabli_0").find("span").removeClass("b_gray");
	$("#mainIframe_0").show();
	
	//定位到指定tab
	var ot=$("#tabli_0").parent().attr("offsetTop");
	$("#menubar_tabs").attr("scrollTop",ot);
	$("#nav_top").find("a").removeClass("select");
	$("#nav_top").find("a").first().addClass("select");
	currTab = "tabli_0";
}

//追加iframe
function appendIframe(element,iframe,url){
	$(element).append(iframe);
}
//显示iframe
function showIframe(iframeId,url){
	$("#"+iframeId).show();
	if(url){
		document.getElementById(iframeId).src = url; 
	}
}
//选中A标签
function selectLink(linkId){
	$("#east").find("ul").children("li").children("a").removeClass("select");
	$("#"+linkId).addClass("select");
}

function closeCurrentWin(){
	var iframe = $("#east").find("iframe:visible");
	var iframeId = iframe.attr("id");
	var tabId = iframeId.substring(11);
	closeTab(tabId);
}