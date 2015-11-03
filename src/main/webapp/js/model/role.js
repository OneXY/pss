$().ready(function() {
	$("#roleForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 1,
				maxlength : 8,
				remote : "role_checkName.action?id="+$("#id").val()
			}
		},
		messages : {
			name : {
				required : "请输入角色名",
				minlength : "最小长度为{0}",
				maxlength : "最大的长度为{0}",
				remote:"角色名已存在"
			}
		}
	});
});