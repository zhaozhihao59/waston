
	$(document).ready(function() {
		initRegin();
		// 登录表单验证
		$.formValidator.initConfig({theme:"TocerDesign",validatorGroup:"1",onError:function(msg,obj,errorlist){}});
		$("#name").formValidator({validatorGroup:"1",onFocus:'请输入姓名'}).inputValidator({min:1,max:255,onErrorMin: "请输入姓名"});
		$("#postCode").formValidator({validatorGroup:"1",onFocus:'请输入邮编'}).inputValidator({min:1,max:255,onErrorMin: "请输入邮编"});
		$("#mobile").formValidator({validatorGroup:"1",onFocus:'请输入手机号码'}).inputValidator({min:1,max:255,onErrorMin: "请输入手机号码"});
		$("#address").formValidator({validatorGroup:"1",onFocus:'请输入详细地址'}).inputValidator({min:1,max:255,onErrorMin: "请输入详细地址"});

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
	        		//success();
	        		$.dialog.tips("修改成功!!");
					window.location.href = base + "member/account/index.htm?m="+Math.random();
	        	}else{
	        		$.dialog.tips(data.message);
	        	}
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