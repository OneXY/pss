$().ready(function() {
	$("#purchaseBillItemForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 5,
				maxlength : 12,
				remote : "purchaseBillItem_checkName.action?id="+$("#id").val()
			},
			password : {
				required : true,
				minlength : 5,
				maxlength : 12
			},
			confirm_password : {
				required : true,
				minlength : 5,
				maxlength : 12,
				equalTo : "#password"
			},
			age : {
				digits : true,
				required : true,
				range : [ 18, 75 ]
			},
			email : {
				email : "email"
			}
		},
		messages : {
			name : {
				required : "请输入用户名",
				minlength : "最小长度为{0}",
				maxlength : "最大的长度为{0}",
				remote:"用户名已存在"
			},
			password : {
				required : "请输入密码",
				minlength : "最小长度为{0}",
				maxlength : "最大的长度为{0}"
			},
			confirm_password : {
				required : "请输入确认密码",
				equalTo : "两次输入的密码不同",
				minlength : "最小长度为{0}",
				maxlength : "最大的长度为{0}"
			},
			age : {
				required : "请输入年龄",
				range : "年龄范围为{0}~{1}岁"
			}
		}
	});
});