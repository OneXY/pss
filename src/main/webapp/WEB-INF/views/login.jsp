<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	if(window != top){
		top.location.href = window.location.href;
	}
	
	window.onload = function() {
		var verifyObj = document.getElementById("Verify");
		verifyObj.onclick = function() {
			this.src = "image.action?time="
					+ new Date().getTime();
		};
	}
	
	window.onkeypress = function(event) {
		if(event.keyCode==13){
			document.loginFrom.submit();
		}
	}
</script>
<% request.getSession().invalidate(); %>
<title>蓝源进销存(教学版)-系统登录页</title>
</head>

<body>
<%@include file="/WEB-INF/views/message.jsp" %>
<s:form action="login_check">
<div class="container-login">
<div class="login-pic">
<div class="login-text">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr>
		<td width="19%" height="28">用户名：</td>
		<td colspan="2"><input name="username" value="admin" type="text" size="15" /></td>
	  </tr>
	  <tr>
		<td height="31">密&nbsp;码：</td>
		<td colspan="2"><input name="password" value="admin" type="password" size="15" /></td>
	  </tr>
	  <tr>
		<td height="30">验证码：</td>
		<td width="43%"><input name="captcha" type="password" size="6"/></td>
		<td width="32%"><img id="Verify" src="image.action" width="50px" height="22" style="cursor: pointer;" alt="看不清,换一张"/></td>
	  </tr>
	  <tr>
		<td height="30">&nbsp;</td>
		<td colspan="2">
				<input type="image" style="cursor: pointer;" src="images/denglu_bg_03.gif" name="Image1" width="40" height="22" border="0" id="Image1"/>
				<img src="images/giveup_bg_03.gif" name="Image2" width="40" height="22" border="0" id="Image2"/>
		</td>
	  </tr>
	</table>

</div>
</div><!--"login-pic"end-->
</div><!--"container-login"end-->
</s:form>
</body>
</html>