<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<div class="content-right">
<%@include file="/WEB-INF/views/message.jsp" %>
<link href="/js/validation/css/screen.css" rel="stylesheet" type="text/css" media="screen"/>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/validation/jquery.validate.js"></script>
<script type="text/javascript" src="/js/validation/messages_cn.js"></script>
<script type="text/javascript">
<!--
$().ready(function() {
	$("#updatePasswordForm").validate({
		rules: {
				oldPassword: {
				required: true,
				minlength: 5
			},
			newPassword: {
				required: true,
				minlength: 5,
				equalNotTo:"#oldPassword"
			},
			confirmPassword: {
				required: true,
				minlength: 5,
				equalTo: "#newPassword"
			}
		},
		messages:{
			newPassword: {
				equalNotTo:"新密码和旧密码不同相同"
			},
			confirmPassword:{
				equalTo: "确认密码和新密码不一致"
			}
		}
	});	
});
//-->
</script>
<s:form id="updatePasswordForm" action="updatePassword_update">
旧密码：<s:textfield id="oldPassword" name="oldPassword"/><br/>
新密码：<s:textfield id="newPassword" name="newPassword"/><br/>
确认密码：<s:textfield name="confirmPassword"/><br/>
<s:submit  value="修改密码"/>
</s:form>
</div>