$(document).ready(function(){
	// 判断是否愿意接受国郓的电子刊物服务等于1 1-是
	if (gydzkwfw==1) {
		$('#gydzkwfwYes').attr("checked", "true");
	}
	// 判断是否愿意接受国郓的电子刊物服务等于2 2-否
	if (gydzkwfw==2) {
		$('#gydzkwfwNo').attr("checked", "true");
	}
	// 订阅内容将发送位置等于1 1-注册邮箱
	if (dynrfsdz==1) {
		$('#dynrfsdzEmail').attr("checked", "true");
	}
	// 订阅内容将发送位置等于2 2-注册手机
	if (dynrfsdz==2) {
		$('#dynrfsdzPhone').attr("checked", "true");
	}
	$('#updMemberMessage').ajaxForm({
		dataType: 'json',
        success: function(data) {
        	if(data.status=="success"){
        		alert(data.message);
				location.reload();	
        	}else{
        		alert("修改失败");
        	}
        }
	});
	
	// 性别选择女
	$("#female").click(function(){
		$("#sexValue").val(0);
	});
	// 性别选择男
	$("#male").click(function(){
		$("#sexValue").val(1);
	});
	
});

function submitMemberMessage(){
	$('#updMemberMessage').attr("action","m/acc/updMemberMessage.htm");
	$('#updMemberMessage').submit();
}