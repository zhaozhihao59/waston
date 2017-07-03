$(document).ready(function() {
	$('#sendEmail').click(function(){
		$.post('login/provingSendTime.htm?m='+Math.random(),function(data){
			if(data.status == "error"){
				$.dialog.alert("在60秒内请不要重复提交")
			}else if(data.status == "success"){
				var email = $('#memberEmail').val()
				window.location.href = base + 'login/forget_send.htm?member.email='+email;
			}
		})
	})
});