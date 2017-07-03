$(document).ready(function(){
	//验证框架信息
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#dictItemName").formValidator({validatorGroup:1}).inputValidator({min:1,max:255,onErrorMin:"数值不能为空",onErrorMax:"数值长度过长"});
	$("#dictItemSort").formValidator({empty:true}).regexValidator({regExp:"^[1-9]\\d*$",onError:"排序输入错误，只能为正整数"});

	$('#addDictForm').ajaxForm({
			dataType:'json',
			success:function(data){
				$("body").unmask();
				if(data.status == 'success'){
					var win = art.dialog.open.origin;
					win.changeSelect($("#globalDictId").val());
					$.dialog.close();
				}else{
				 	$.dialog({ content: '保存失败',okVal:"确定",ok: function(){}});
				}
			}
		});

	$('#saveBtn').click(function(){
		var result = $.formValidator.pageIsValid('1');
		if(!result){
			return;
		}
		$("body").mask("保存中....");
		$('#addDictForm').submit();
	});
	
	$("#exitBtn").click(function(){
		$.dialog.close();
	});
});