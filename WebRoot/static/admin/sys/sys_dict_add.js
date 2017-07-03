$(document).ready(function(){
	//验证框架信息
	$.formValidator.initConfig({validatorGroup:"1",onError:function(msg,obj,errorlist){$.msg({wrapID:"errorlist",type:"error",messages:errorlist,time:"5000"});}});
	$("#name").formValidator({validatorGroup:1}).inputValidator({min:1,max:255,onErrorMin:"名称不能为空",onErrorMax:"长度过长"});
	$("#sort").formValidator({empty:true}).regexValidator({regExp:"^[1-9]\\d*$",onError:"排序输入错误，只能为正整数"});

	$('#addDictForm').ajaxForm({
		dataType:'json',
		success:function(data){
			$("body").unmask();
			if(data.status == 'success'){
				var win = $.dialog.open.origin;
				win.reloadDict(data.options);
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
		var dictId = $("#dictId").val();
		if(dictId != null && dictId !=''){
			$('#addDictForm').attr("action","admin/sys/dict/doUpdate.htm");
		}else{
			$('#addDictForm').attr("action","admin/sys/dict/doAdd.htm");
		}
		$("body").mask("保存中....");
		$('#addDictForm').submit();
	});
	
	$("#exitBtn").click(function(){
		$.dialog.close();
	});
});