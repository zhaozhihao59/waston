$(document).ready(function(){
	resizeLeft();
	
	$(window).resize(function(){
		resizeLeft();
	});
});

function resizeLeft(){
	var ww = $(window).width();
	var rw = $('#indexRight').width();
	var lw = ww - rw - 50;
	$('#indexLeft').width(lw);
}

function doquery(id){
	var tv = {};
	tv.text="信息内容";
	tv.url = "mainstation/information/information/viewInfo.do?id="+id+"&m="+Math.random();
	tv.tabId = "tabli_information_doquery_"+id;
	tv.doc = parent.document;
	showTab(tv);
}

function operate(id){
	$.post("mainstation/information/information/operate.do",{"id":id},function(data){
		if(data.success){
			//type == 1 机器注册审核  result == 审核通过
			if( data.result && data.type == 1){
				$.dialog({
				    title: '消息',
				    content: '是否直接进行维修开单',
				    okVal:"直接开单",
				    cancelVal:"取消",
				    ok:function(){
				    	createNewMaintainOrder(data);
				    },
				    cancel:function(){
				    	return;
				    }
	    		 });
			}else{
				//机器注册 打回修改
				var tv = {};
				tv.text=data.text;
				tv.url = data.URL;
				tv.tabId = "tabli_update_machine";
				tv.doc = parent.document;
				tv.refresh = true;
				showTab(tv);
			}
			// type 维修单 
			if(data.type == 0){
				var tv = {};
				tv.text=data.text;
				tv.url = data.URL;
				tv.tabId = "tabli_operate_maintainOrder";
				tv.doc = parent.document;
				tv.refresh = true;
				showTab(tv);
			}
		}else{
			$.dialog({content:data.msg,ok: function (){
					return true;					
				},width:'200px'});
		}
	});
}
//直接开单
function createNewMaintainOrder(data){
	var tv = {};
	tv.text="新建开单";
	tv.url = "mainstation/mainservice/maintainreport/toAddMaintainOrder.do?macType="+data.mactype+"&serialNumber="+data.serialNumber;
	tv.tabId = "tabli_maintainOrder_create";
	tv.doc = parent.document;
	tv.refresh = true;
	showTab(tv);
}

function moreInfo(type){
	var tv = {};
	if(type == "toList"){
		tv.text="维修站通知";
	}
	if(type == "doList"){
		tv.text="政策信息";
	}
	if(type == "getList"){
		tv.text="技术通告";
	}
	if(type == "download"){
		tv.text="下载中心";
	}
	tv.url = "mainstation/information/information/"+type+".do?m="+ Math.random();
	tv.doc = parent.document;
	showTab(tv);
} 