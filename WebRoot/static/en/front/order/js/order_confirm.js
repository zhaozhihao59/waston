$(document).ready(function() {
	$('table').each(function(){
			$(this).find('tr:even').addClass("act-gray");
		});
	initRegin();
	// 登录表单验证
	$.formValidator.initConfig({theme:"TocerDesign",validatorGroup:"1",onError:function(msg,obj,errorlist){}});
	$("#linkman").formValidator({validatorGroup:"1",onFocus:'Please input the consignee name!'}).inputValidator({min:1,max:255,onErrorMin: "Please input the consignee name!"});
	$("#postCode").formValidator({validatorGroup:"1",onFocus:'Please enter the zip code!'}).inputValidator({min:1,max:255,onErrorMin: "Please enter the zip code!"});
	$("#mobile").formValidator({validatorGroup:"1",onFocus:'Please enter your cell phone number!'}).inputValidator({min:1,max:255,onErrorMin: "Please enter your cell phone number!"});
	$("#address").formValidator({validatorGroup:"1",onFocus:'Please enter your primary address!'}).inputValidator({min:1,max:255,onErrorMin: "Please enter your primary address!"});

	accountForm();
	
	// 重写密码处敲击回车
	$("#mobile").live("keydown",function(e){
		if(e.keyCode == '13'){
			$('#doSaveSubmit').click();
			return false;
		}
	});
	// 登录按钮
	$('#doSaveSubmit').click(function(){
		doAccount();
	});
	
	// 设置默认地址 以便于请求
	$("#selectBtn").click(function(){
		selectBtnAddress();
	});
	
	//提交表单
	$("#submitOrderBtn").bind("click",submitOrder);
});

//全部选择
var a = 0;
 $("#all").click(function(){
	 if(a == 0){
		 $("input[name='checkbox1']").each(function(){
			   $(this).attr("checked",true);
			  });  
		 a=1;
	 }else{
		  $("input[name='checkbox1']").each(function(){
			   $(this).attr("checked",false);
			  });  
		  a=0;
	 }
 });
 
 /** 选取选中的复选框的值 */
$("#del").click(function (){
	var chkvalue = "";
	var selIds = [];
	 $("input[name='checkbox1']").each(function(){
		   if ( $(this).attr("checked")) {
			   selIds.push($(this).val());
			   }
		  });  
	 window.location.href = basePath + "en/member/address/del.htm?selIds=" + selIds.join(",");
 });
 
/**
 * 
 * 提交表单设置
 */
function accountForm(){
	$("#accountForm").ajaxForm({
		dataType:'json',
		success: function(data) {
        	$("body",document).unmask();
        	if(data.status == 'success'){
        		location.reload();
        	}else{
        		$.dialog.tips(data.message);
        	}
        }
	});
}

function selectBtnAddress(){
	// 1.获取选择的ID
	var selCheckBox = $("input[name='checkbox1']:checked");
	if(selCheckBox.length == 0){
		$.dialog.tips("Please select a shipping address!!!");
		return;
	}
	if(selCheckBox.length > 1){
		$.dialog.tips("Can only check a shipping address!!!");
		return;
	}

	var defId = selCheckBox.val();
	$.post("en/member/address/updaAddressDefault.htm",{"item.addressStatus":1,"item.id":defId},function(data){
		if(data.status == 'success'){
			location.reload() ;
		}else {
			$.dialog.tips(data.message);
			return;
		}
	});
}

// 修改密码提交
function doAccount(){
	var result = $.formValidator.pageIsValid(1);// 手动调用验证框架进行验证
	if(!result){
		return;
	}
	$("#accountForm").submit();
	
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
				city.append("<option value='-1'>City...</option>");
				$('#cityCode').val("-1");
				codeAddress.empty();
				codeAddress.append("<option value='-1'>District...</option>");
				$('#addressCode').val("-1");
				var provinceName = $("#province option:selected").text();
				if (provinceName == "Province...") {
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
		codeAddress.append("<option value='-1'>District...</option>");
		$('#addressCode').val("-1");
		var provinceName = $("#province option:selected").text();
		var cityName = $("#city option:selected").text();
		if (cityName == "City...") {
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
				if (addreName == "District...") {
					$('#addressInput').val(provinceName + "-" + cityName);
				} else {
					$('#districts').val(addreName);
					$('#addressInput').val(provinceName + "-" + cityName + "-"
							+ addreName);
					
				}
			});
} 

//提交订单
function submitOrder(){
	var memAddress = $("#memAddress").val();
	if(memAddress == ''){
		$.dialog.alert("Please select a shipping address!");
		return;
	}
	//判断代码
	var discountCode = $("#discountCode").val();
	var freeShippingCode = $("#freeShippingCode").val();
	
	if((discountCode && discountCode.length > 0) || (freeShippingCode && freeShippingCode.length > 0)){
		$("body").mask("Are validation discount code, please later...");
		$.post(basePath + "en/member/order/sell/checkDiscountCode.htm?m="+Math.random(),{"discountCode":discountCode,"freeShippingCode":freeShippingCode},function(data){
			$("body").unmask();
			if(data.status != "success"){
				$.dialog.alert(data.message);
				return;
			}
			$("body").mask("Being generated orders, please later...");
			$("#submitOrderForm").submit();
		});
	}else{
		$("body").mask("Being generated orders, please later...");
		$("#submitOrderForm").submit();
	}
}
