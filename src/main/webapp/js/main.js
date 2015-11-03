$(document).ready(function() {
	$('#updatePassword').click(function() {
		  $("#sign_up").lightbox_me({
					  centered: true, 
					  preventScroll: true, 
					  onLoad: function() {
							$("#sign_up").find("input:first").focus();
						}
			});
	});
	
	$('#updatePwd').click(function() {
		var flag = false;
		
		if(!flag && $("#oldPassword").val()==""){
			flag =false;
			$("#msg").html("原始密码不能为空");
			return;
		}
		if(!flag&&$("#newPassword").val()==""){
			flag=true;
			$("#msg").html("新密码不能为空");
			return;
		}
		if(!flag&&$("#renewPassword").val()==""){
			flag=true;
			$("#msg").html("确认新密码不能为空");
			return;
		}
		if(!flag&&$("#oldPassword").val().length<5){
			flag=true;
			$("#msg").html("原始密码长度不能小于5");
			return;
		}
		if(!flag&&$("#newPassword").val().length<5){
			flag=true;
			$("#msg").html("新密码长度不能小于5");
			return;
		}
		if(!flag&&$("#renewPassword").val().length<5){
			flag=true;
			$("#msg").html("确认新密码长度不能小于5");
			return;
		}
		if(!flag&&$("#oldPassword").val()==($("#newPassword").val())){
			flag=true;
			$("#msg").html("新密码和原始密码不能相等");
			return;
		}
		if(!flag&&$("#renewPassword").val()!=($("#newPassword").val())){
			flag=true;
			$("#msg").html("新密码和确认新密码不相等");
			return;
		}
		
		if(!flag){
			
			$.get("updatePassword_update.action",{oldPassword:$("#oldPassword").val(),newPassword:$("#newPassword").val(),renewPassword:$("#renewPassword").val()},
					function(data){
				if(data.times<1){
					alert(data.msg);
					window.location.href="login.action";
				}else{
					$("#msg").html(data.msg);
				}
			});
		}
	});
});
	