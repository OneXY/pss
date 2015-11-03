$().ready(function() {
	$("#departmentForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 0,
				maxlength : 12,
				remote : "department_checkName.action?id="+$("#id").val()
			}
		},
		messages : {
			name : {
				required : "请输入部门名称",
				minlength : "最小长度为{0}",
				maxlength : "最大的长度为{0}",
				remote :"部门名称已存在"
			}
		}
	});
});