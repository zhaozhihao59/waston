$(document).ready(function(){
	// 绑定日期控件
	$('#replyDateEnd').datepicker({
		changeMonth: true,
		changeYear: true,
		onClose: function(selectedDate){
			$(this).css('color','#333');
		} 
	}); 
	// 绑定日期控件
	$('#replyDateStart').datepicker({
		changeMonth: true,
		changeYear: true,
		onClose: function(selectedDate){
			$(this).css('color','#333');
		} 
	}); 
	
	// 加载分类
	$.post("product/productType.htm",function(data){
		var result='';
		$.each(data,function(index,val){
			if(index==0){
				result += '<label><input name="knowArray" val="'+val.name+'" type="checkbox"  class="vm"><span class="vm ml10">'+val.name+'</span></label>';
			}else{
				result +='<label><input name="knowArray" val="'+val.name+'" type="checkbox" class="vm ml20"><span class="vm ml10">'+val.name+'</span></label>';
			}
			
			$("input[index='"+index+"']").val(val.name);
		});
		$("#checkboxForm").html(result);
	},"json");
	
	// 异步表单
	$("#leftMessageForm").ajaxForm({
		dataType:'json',
		success:function(data){
			$("#massageDiv").unmask();
			if(data.status=="success"){
				$(".msg-box-content").hide();
				$(".msg-box").hide();
				$.dialog({
					title:'消息',
					icon:'succeed',
					content:'提交成功！',
					ok:true
				});
			}else{
				$.dialog.alert("提交失败！");
			}
		}
	});
	
	// 打开信息
	$(".yjfk").click(function(){
		$(".msg-box-content").hide();
		$(".msg-box-content").hide();
		$.post("product/checkUser.htm",{},function(data){
			if(data.status=="success"){
				$('.msg-box-content').find('input').val('');
				$(".msg-box-content").show();
				$(".msg-box").show();
				$('.msg-box').css('height', h + 'px');
				
				$("#itemCreateBy").val(data.message);
				$("#itemType").val(1);
				$("#itemContent").val("");
			}else{
				window.location.href=base+"m/acc/index.htm";
			}
		});
	});
	
	// 保存表单
	$(".saveMessageBtn").live('click',submitMessageForm);
	
	// 加载省市
	initProvinceAndCity();
	
	// 多选
	$("input[name='knowArray']").live('change',getCheckBoxVal);
	
	// 单选
	$("input[name='hopeTime']").live('change',function(){
		var i=$("input:radio[name='hopeTime']:checked").attr("val");
		if(i==1){
			$("#startTime").val("");
			$("#endTime").val("");
		}else{
			var start = $("#replyDateStart").val();
			var end = $("#replyDateEnd").val();
			if(start != undefined){
				$("#startTime").val(start);
			}
			if(end != undefined){
				$("#endTime").val(end);
			}
		}
	});
});

// 检查
function check(){
	var pro= $("#pro").val();
	var cit= $("#cit").val();
	if(pro.indexOf("请选择")>-1 || cit.indexOf("请选择")>-1){
		alert("请选择省市！");
		return true;
	}
	
	if($("input[name='knowArray']:checked").size()<1){
		alert("请选择想要了解的方面！");
		return true;
	}
	
	if($(".otherVm").is(":checked")){
		if($("#otherContentHiden").val().length<1){
			alert("请填写内容！");
			return true;
		}
	}
	
	return false;
}

// 加载省市
function initProvinceAndCity(){
//	provinceList

	// 加载省份
	$.each(provinceList,function(i,p){
		var opt='<option value="'+p.province+'" val="'+p.idx+'">'+p.province+'</option>';
		$("#pro").append(opt);
	});
	
	// 选择省份加载城市
	$("#pro").live('change',function(){
		var idsSel=$("#pro").find("option:selected").attr("val");
		
		$("#cit").html("");
		$("#cit").append('<option>请选择</option>');
		$.each(provinceList,function(i,p){
			if(p.idx == idsSel){
				var cityList=p.cityList;
				$.each(cityList,function(y,c){
					var city='<option value="'+c.city+'">'+c.city+'</option>';
					$("#cit").append(city);
				});
			}
		});
	});
}

// 提交表单
function submitMessageForm(){
	if(check()){
		return;
	}
	$("#massageDiv").mask("正在提交,请稍候...");
	// 添加多选值
	getCheckBoxVal();
	// 添加地址
	getAddress();
	
	
	$("#leftMessageForm").submit();
}

// 获取多选框的值
function getCheckBoxVal(){
	var str='';
	$("input[name='knowArray']:checked").each(function(){
		var res=$(this).attr("val");
		var other = $("#otherContentHiden").val();
		if(res != undefined){
			if(res != 1){
				str=str.length==0?str+=res:str+=","+res;
				$("#itemOtherContent").val("");
			}else{
				$("#itemOtherContent").val(other);
			}
		}
	});
	$("#itemHopeKnow").val(str);
}

// 地址
function getAddress(){
	var province=$("#pro").val();
	var city = $("#cit").val();
	var otherAddress = $("#otherAddress").val();
	
	var res=province+' '+city+' ';
	if(undefined != otherAddress){
		res += otherAddress;
	}
	
	$("#itemOther").val(res);
}
