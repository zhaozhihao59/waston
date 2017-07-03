/*
 * 设置jqgrid默认属性
 * Copyright (c) 2011,Zhang Qiang, james.zhangq@gmail.com
 * http://www.tocersoft.com
 * Date: 2011-04-28
 */
(function($){
	//设置全局默认属性
	$.extend($.jgrid.defaults,{
		ajaxGridOptions : {type:"POST"},
		viewrecords: true, 
		datatype: "json", 
		autowidth:true,
		rowNum:10, 
		height:'100%',
		//scrollrows : true,
		rowList:[10,20,30,50,100,150,200], 
		pager: '#pagerBar',
		sortorder: "desc", 
		altRows:true,
		altclass:'ui-alt-row',
		hoverrows:true,
		multiselect: true,
		multiboxonly:true,
		multiselectWidth:25,
		jsonReader: {
			page: "model.pageResult.currentPage",
			total: "model.pageResult.allPages",
			records: "model.pageResult.rows",
			repeatitems : false,
			cell:null
		}, 
		prmNames:{
			rows:"model.pageResult.pageSize",
			page:"model.pageResult.currentPage",
			sort:"model.pageResult.orderBy",
			order:"model.pageResult.sort"
		},
		onRightClickRow:function(rowid,iRow,iCol,e){
			e = e || window.event; 
	        if (e.stopPropagation) 
	            e.stopPropagation(); 
	        e.cancelBubble = true; 
	        return false; 
		},
		loadComplete:function(){
			//var winHeight = $(window).height()-70;
			//$(this).setGridHeight(winHeight);
		},
		loadError:function(xhr,status,error){
			var msg = xhr.responseText;
			if(msg.indexOf("top.location.href") != -1){	//会话超时
				alert("登录超时，请重新登录");
				top.location.href=basePath + '/admin';
			}else{
				alert("与服务器通信发生异常，请与管理员联系");
			}
		}
	});
	
	//不显示分页栏上的工具按钮
	$.extend($.jgrid.nav,{
		edit:false,
		add:false,
		del:false,
		search:false,
		refresh:true
	});
	
})(jQuery);

/** 格式化表格中的空值 **/
function nullFormater(cellvalue,options,rowObject ){
	if(cellvalue=="null"){   
		return "无";   
	}else if(!cellvalue || cellvalue == ""){
		return "&nbsp;";
	}else{
		return cellvalue;
	}
}

/** 刷新表格 */
function refreshGrid(grid){
	$(grid).trigger("reloadGrid",[{page:1}]);
}
