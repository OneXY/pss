$().ready(function() {
	$("#resourceForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 1,
				maxlength : 8,
				remote : "resource_checkName.action?id="+$("#id").val()
			}
		},
		messages : {
			name : {
				required : "请输人资源名称",
				minlength : "最小长度为{0}",
				maxlength : "最大的长度为{0}",
				remote:"资源名称已存在"
			}
		}
	});
});