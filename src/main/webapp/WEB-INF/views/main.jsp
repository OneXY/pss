<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="/css/index.css" rel="stylesheet" type="text/css" />
<title>成都蓝源进销存系统(教学版)-系统主页</title>
</head>

<body>
<div class="container">
<div class="head">
<div class="head-left"><span style="font-weight:bold; color:#1f4906">欢迎您-</span><br><span style="color:#4a940d;">${USER_IN_SESSION.name}</span></div>
<div class="head-right">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="32%"><a href="updatePassword.action"><img src="images/head-l.gif" border="0"></a></td>
<!--     <td id="updatePassword" width="32%"><a href="#"><img src="images/head-l.gif" border="0"></a></td> -->
    <td width="26%"><a href="logout.action"><img src="images/head-m.gif" border="0"></a></td>
    <td width="7%">&nbsp;</td>
    <td width="35%"><a href="#"><img src="images/head-r.gif" border="0"></a></td>
  </tr>
</table>

</div>
</div><!--"head"end-->

<div class="content">
<%@include file="/WEB-INF/views/left.jsp"%>
<iframe src="right.action" style="width:848px;float:right;height:530px" scrolling="no" name="main" frameborder="0"></iframe>
<!--"content-right"end-->
</div><!--"content"end-->
<div class="footer">
<div style="margin-top:5px;">
<table width="98%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td width="82%"><img src="images/icon_1.gif">&nbsp;<a class="lanyo" href="www.lanyotech.com">蓝源信息技术 2009</a></td>
    <td width="18%" valign="middle"><img src="images/icon_2.gif">&nbsp;<a class="lanyo" href="#">如有疑问请与技术人员联系</a></td>
  </tr>
</table></div>

</div><!--"footer"end-->
</div><!--"container"end-->
<%--<s:form id="pwdForm"> --%>
<%-- <div id="footer"> --%>
<%-- 	<div id="sign_up"> --%>
<%-- 		<span>修改密码</span> --%>
<%-- 		<span id="msg" style="color:red" ></span> --%>
<%-- 		<div id="sign_up_form"> --%>
<%-- 			<label><strong>原密码:</strong> --%>
<%-- 			  <s:textfield id="oldPassword" name="oldPassword" size="10" cssClass="sprited"/> --%>
<%-- 			</label> --%>
<%-- 			<label><strong>新密码:</strong> --%>
<%-- 			  <s:textfield id="newPassword" name="newPassword" size="10" cssClass="sprited"/> --%>
<%-- 			</label> --%>
<%-- 			<label><strong>确认新密码:</strong> --%>
<%-- 			  <s:textfield id="renewPassword" name="renewPassword" size="10" cssClass="sprited"/> --%>
<%-- 			</label> --%>
<%-- 			<div id="actions"> --%>
<%-- 				<a class="form_button sprited" id="updatePwd">修改</a> --%>
<%-- 				<a class="close form_button sprited" id="cancel" >关闭</a> --%>
<%-- 			</div> --%>
<%-- 		</div> --%>
<%-- 	</div> --%>
<%-- </div> --%>
<%-- </s:form> --%>
<%-- <script type="text/javascript" src="/js/lightbox2/jquery.lightbox_me.js"></script> --%>
<%-- <script type="text/javascript" src="/js/main.js"></script> --%>

</body>
</html>
