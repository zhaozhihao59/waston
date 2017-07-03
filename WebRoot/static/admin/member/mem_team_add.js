var editor,tagIds="";
$(document).ready(function(){
//	initRegin();
	initFormValidator();
	//异步表单提交设置
    $('#articleForm').ajaxForm({
        dataType: 'json',
        success: function(data) {
        	$("body",parent.document).unmask();
        	if(data.stauts = 'success'){
        		//关闭本窗口
        		$.dialog.close();
        		//获取父窗体对象
        		var win = $.dialog.open.origin;
        		//刷新jqGrid插件
        		win.reloadGrid();
        	}else if(data.stauts = 'error'){
        		$.dialog.alert('保存失败');
        	}
        }
    });
    
    
});

function initFormValidator(){
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorList",type:"error",messages:errorlist,time:"3000"});}});
	  $("#itemName").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"团队名称不能为空",onErrorMax:"团队名称长度过长"})
	  $("#teamType").formValidator({validatorGroup:1,onShow:'',onFocus:'',onCorrect:''}).inputValidator({min:1,max:255,onErrorMin:"团队类型不能为空",onErrorMax:"团队类型长度过长"})
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
//	teamType();
	var result = $.formValidator.pageIsValid(1);	//手动调用验证框架进行验证
	if(!result){
		return;
	}
   	//所有异步提交，必须都要加上遮罩，为了防止AJAX重复提交
	$("body",parent.document).mask("正在提交，请稍候...");
   	$(articleForm).submit();
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
 