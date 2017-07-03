$(document).ready(function(){
	$(".frontSearchBtn").live('click',function(){
		var keyword= $(".searchFormContent").val();
		if(keyword != undefined && keyword != ''){
			$("#searchFrom").submit();
		}else{
			var oldword=$("#keywrodSearch").attr("placeholder");
			$("#keywrodSearch").attr("placeholder","请输入文章标题！");
			setTimeout(function(){
				$("#keywrodSearch").attr("placeholder",oldword);
			},3000);
		}
	});
});