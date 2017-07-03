var editor,tagIds="";
$(document).ready(function(){
	
	initGrid();
	initAjaxForm();
	initFormValidator();
	initTeamType();
	
});

function initGrid(){
	var gh = $(window).height() - 330;
	//渲染表格骨架
	$("#table").jqGrid({
	    url:'admin/memTeamMember/listMemTeamMemberByPage.htm?condition.teamId='+id+'&&m='+Math.random(),
	    datatype:"json",
	    colNames:['操作','姓名','职称','手机号码','电子邮件',"会员ID","团队ID"],
		colModel:[ 
			{name:"id",index:"id",width:30,formatter:optFormater}, 
			{name:"name",index:'name',width:60,formatter:nameFormater},
			{name:"type",index:'type',width:60,formatter:typeFormater},
			{name:"mobile",index:'mobile',width:60},
			{name:'email',index:'email',width:100},
			{name:'memberId',index:'memberId',width:100,hidden:true},
			{name:'teamId',index:'teamId',width:100,hidden:true}
		], 
		caption:"团队队员列表",
		loadComplete: function(data){
			var width = $(window).width()-10;
			$(this).setGridWidth(width);
			fillEmptyRow('table',data);
	   	},
	   	height:gh,
		jsonReader:{id: "id",root:"resultList"}
  	});
  	$("#table").jqGrid("navGrid","#pagerBar");
		  
	// 随窗体大小改变而改变Grid宽度
	window.onresize = function _doResize(){
	 	var width = $(window).width()-10;
	 	$("#table").jqGrid('setGridWidth', width);
	}
}

function sexFormater(cellvalue,options,rowObject){
	if(cellvalue == 0){
		return "女";
	}else if(cellvalue == 1){
		return "男";
	}else{
		return "--";
	}
}

//格式化操作单元格
function optFormater(cellvalue,options,rowObject){
	var btns = [];
	var deleteBtn = "<a href='javascript:;' title='移除' class='no_unl' onclick=\"toDelete('"+rowObject.id+"');return false;\">移除</a>";
	btns.push(deleteBtn);
	return btns.join(" ");
}
//格式化操作单元格
function nameFormater(cellvalue,options,rowObject){
	var btns = [];
	var deleteBtn = "<a href='javascript:void(0);' title='' class='no_unl' onclick=\"toDetail('"+rowObject.memberId+"');return false;\">"+cellvalue+"</a>";
	btns.push(deleteBtn);
	return btns.join(" ");
}
//格式化操作单元格
function typeFormater(cellvalue,options,rowObject){
	var btns = [];
	var typeBtn;
	if(cellvalue==0){
		typeBtn='普通队员';
	}
	if(cellvalue==1){
		typeBtn='领队';
	}
	if(cellvalue==2){
		typeBtn='教练';
	}
	if(cellvalue==3){
		typeBtn='联系人';
	}
	btns.push(typeBtn);
	return btns.join(" ");
}
function ellipsis(cellvalue,options,rowObject){
	return '<div style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden;display:block; ">'+cellvalue+'</div>'
}

function initAjaxForm(){
	//异步表单提交设置
    $('#articleForm').ajaxForm({
        dataType: 'json',
        success: function(data) {
        	$("body",parent.document).unmask();
        	if(data.stauts = 'success'){
        		$.dialog.alert('保存成功');
        		closeCurrentTab();
        	}else if(data.stauts = 'error'){
        		$.dialog.alert('保存失败');
        	}
        }
    });
}

function initFormValidator(){
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorList",type:"error",messages:errorlist,time:"3000"});}});
    //$("#marketPrice").formValidator({onShow:'',onFocus:'',onCorrect:''}).regexValidator({regExp:'decmal1',dataType:"enum",onError:"默认市场价：请输入数字"});
    //$("#terracePrice").formValidator({onShow:'',onFocus:'',onCorrect:''}).regexValidator({regExp:'decmal1',dataType:"enum",onError:"默认平台价：请输入数字"});
    
    //$.formValidator.initConfig({validatorGroup:"2",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorList",type:"error",messages:errorlist,time:"3000"});}});
    //$("#name").formValidator({validatorGroup:2,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"产品名称不能为空",onErrorMax:"产品名称长度过长"})
    //.regexValidator({regExp:"notempty",dataType:"enum",onError:"产品名称不能是空格"});
}

function showChooseTags(t){
	tagIds = "";
	chooseTags = t;
	$("#teamResourceTag").html("");
	for(var i=0;i<chooseTags.length;i++){
		var chooseTag = chooseTags[i];
		
		var tagHtml = '<li class="resource-tag mr5">';
		tagHtml += '<span class="tag-name mr5">'+chooseTag.name+'</span>';
		tagHtml += '<a id="'+chooseTag.id+'" href="javascript:void(0);" class="tag-del">X</a>';
		
		$("#teamResourceTag").append(tagHtml);
		tagIds += chooseTag.id + ",";
	}
	tagIds = tagIds.substring(0,tagIds.length-1);
	$("#tagsHid").val(tagIds);
}

function resize(){
	var w = $('.caption-div').width();
	var nw = w + 8;
	$('.art-content').width(nw);
}
//提交表单
function submitForm(articleForm){
	/*editor.sync();*/
	teamType();
   	//所有异步提交，必须都要加上遮罩，为了防止AJAX重复提交
	$("body",parent.document).mask("正在提交，请稍候...");
   	$(articleForm).submit();
}
//团队类型
function initTeamType(){
	var type=$("#teamType").val();
	var radioType = $('.form-content').find("input[name='teamType']");
	radioType.each(function(i,cur){
		if($(cur).val()==type){
			$(cur).attr("checked","checked")
		}
	});
}
//团队类型
function teamType(){
	$("#teamType")
	var radioType = $('.form-content').find("input[name='teamType']");
	radioType.each(function(i,cur){
		if($(cur).is(':checked')){
			$("#teamType").val($(cur).val());
		}
		
	});
}
function chooseTag(){
	$.dialog.data("chooseTags",chooseTags);
	$.dialog.open("admin/member/choose_tag.htm", {
		title: '选择标签',
		width:'500px',
		height:'400px',
		lock:true
	});
}

//加载省市区
function initRegin() {
	var province = $('#province'); // 获得省份选择框
	var city = $('#city'); // 获得城市选择框
	var codeAddress = $('#codeAddress'); // 获得县区选择框
	var ch = new Array;
	ch = provinceCity.split("-");
	$.each(provinceList, function(i, n) {
				var opt1 = $('<option />');
				opt1.val(n.idx);
				opt1.html(n.province);
				$(province).append(opt1);
				if (n.province == ch[0]) { // 默认选中原省份
					opt1.attr('selected', 'selected');
					$.each(n.cityList, function(x, y) { // 继续加载城市选择框
								var opt2 = $('<option />');
								opt2.val(y.idx);
								opt2.html(y.city);
								$(city).append(opt2);
								if (y.city == ch[1]) { // 默认选中原城市
									opt2.attr('selected', 'selected');
									$.each(y.addrList, function(o, h) { // 继续加载区县选择框
												var opt3 = $('<option />');
												opt3.val(h.idx);
												opt3.html(h.dong);
												$(codeAddress).append(opt3);
												if (h.dong == ch[2]) {
													opt3.attr('selected',
															'selected');
												}
											});
								}
							});
				}
			});

	province.change(function() {
				var c = $(this).val();
				$('#provinceCode').val(c);
				city.empty();
				city.append("<option value='-1'>请选择城市</option>");
				$('#cityCode').val("-1");
				codeAddress.empty();
				codeAddress.append("<option value='-1'>请选择区县</option>");
				$('#addressCode').val("-1");
				var provinceName = $("#province option:selected").text();
				if (provinceName == "请选择省份") {
					$('#addressInput').val("");
				} else {
					$('#addressInput').val(provinceName);
					$('#provinces').val(provinceName);

				}
				$.each(provinceList, function(i, n) {
							if (n.idx == c) {
								$.each(n.cityList, function(x, y) {
											var opt = $('<option />');
											opt.val(y.idx);
											opt.html(y.city);
											city.append(opt);
										});
							}
						});
			});
	city.change(function() {
		var p = $('#province').val();
		var c = $(this).val();
		$('#cityCode').val(c);
		codeAddress.empty();
		codeAddress.append("<option value='-1'>请选择区县</option>");
		$('#addressCode').val("-1");
		var provinceName = $("#province option:selected").text();
		var cityName = $("#city option:selected").text();
		if (cityName == "请选择城市") {
			$('#addressInput').val(provinceName);
		} else {
			$('#addressInput').val(provinceName + "-" + cityName);
			$('#citys').val(cityName);
		}
		$.each(provinceList, function(i, n) {
					if (n.idx == p) {
						$.each(n.cityList, function(x, y) {
									if (y.idx == c) {
										$.each(y.addrList, function(o, h) {
													var opt = $('<option />');
													opt.val(h.idx);
													opt.html(h.dong);
													if (h.dong != "default") {
														codeAddress.append(opt);
													}
												});
									}
								});
					}
				});
	});
	codeAddress.change(function() {
				var city = $('#city').val();
				var c = $(this).val();
				$('#addressCode').val(c);
				var provinceName = $("#province option:selected").text();
				var cityName = $("#city option:selected").text();
				var addreName = $("#codeAddress option:selected").text();
				if (addreName == "请选择区县") {
					$('#addressInput').val(provinceName + "-" + cityName);
				} else {
					$('#districts').val(addreName);
					$('#addressInput').val(provinceName + "-" + cityName + "-"
							+ addreName);
					
				}
			});
}
//添加队员
function addTeamMember(teamId){
	$.dialog.open('admin/memTeamMember/addTeamMeber.htm?item.teamId='+teamId,{
		title : '新增团队',
		width : '1000px',
		height : '600px',
		lock : true
	});
}
//查看详情
function toDetail(id){
	var tv = {};
	tv.text="会员详细";
	tv.url = "admin/member/detail.htm?item.id=" + id;
	tv.tabId = "member_detail_" + id;
	tv.doc = parent.document;
	showTab(tv);
}

function toDelete(id){
	// 确认提示
	var dialog = $.dialog({
				title : '确认删除',
				content : '确认要删除吗？',
				okVal : "删除",
				cancelVal : "取消",
				ok : function() {
					$.post("admin/memTeamMember/del.htm?selIds=" + id
							, function(data) {
								// $.dialog.alert(data.message);
								reloadGrid();
							});
				},
				cancel : function() {
					this.close();
				}
			});
}


//批量删除
function delArticle(){
	//确认提示
	var selIds = $("#table").jqGrid("getGridParam", "selarrrow");
	$.dialog.confirm("确认要删除此项数据吗？",function(){
		$.post("admin/memTeamMember/del.htm?m="+Math.random(),{"ids":selIds.toString()},function(data){
			if(data.success){
				$.dialog.alert('删除成功');
				reloadGrid();
			}else{
				$.dialog.alert('删除失败');
			}
		});
	});
}
//重新加载
function reloadGrid(){
	$('#table').trigger('reloadGrid',[{page:1}]);
}

/** 关闭当前页 */
function closeCurrentTab(){
	var parent_tabli = "tabli_74";
	var cur_tabli = "tabli_add_member";
	//刷新之前的列表页
     try{
    	 var viewId = "mainIframe_"+parent_tabli;
			var parentTab = top.document.getElementById(viewId);
			var win;
			// 退货单页面存在
			if (null != parentTab) {
			var win = parentTab.contentWindow;
			win.reloadGrid();
			$("#" + parent_tabli, parent.document).click();
			$("#"+cur_tabli,parent.document).remove();
			}
     }catch(e){
     }
}
 