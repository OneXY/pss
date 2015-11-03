<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="/css/index.css" rel="stylesheet" type="text/css" />
<link href="/js/validation/css/screen.css" rel="stylesheet" type="text/css" media="screen"/>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/validation/jquery.validate.js"></script>
<script type="text/javascript" src="/js/validation/messages_cn.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/model/systemDictionaryType.js"></script>
<title>成都蓝源进销存系统(教学版)-产品编辑界面</title>
</head>

<body>
<s:form id="systemDictionaryTypeForm" action="systemDictionaryType_save.action" namespace="/">
<s:hidden id="id" name="id"/>
<s:hidden name="baseQuery.currentPage"/>
<s:hidden name="baseQuery.pageSize"/>
<s:hidden name="baseQuery.name"/>
<div class="content-right">
<div class="content-r-pic"><div style="margin:5px auto auto 12px;"><img src="images/canping.gif" width="138" height="17" /></div></div>
<div class="content-text">
<div class="square-order">
  <div style="border:1px solid #cecece;">
  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr bgcolor="#FFFFFF">
    <td><%@include file="/WEB-INF/views/message.jsp" %></td>
  </tr>
</table>
	<table width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr  bgcolor="#FFFFFF">
      <td width="22%" align="center" height="30">类型名称</td>
      <td width="29%">
        <s:textfield name="name" id="name"/>
      </td>
    </tr>
    <tr  bgcolor="#FFFFFF">
      <td height="30" align="center">sn</td>
      <td>
        <s:textfield name="sn"/>
      </td>
    </tr>
  </table>
<div class="order-botton">
<div style="margin:1px auto auto 1px;">
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><input type="image" src="images/order_tuo.gif" style="border: 0px"/></td>
    <td>&nbsp;</td>
    <td><img id="resetBtn" src="images/order_tuo.gif" border="0"  style="cursor: pointer"/></td>
    <td>&nbsp;</td>
    <td><img id="cancelBtn" src="images/order_tuo.gif" border="0" style="cursor: pointer"/></td>
  </tr>
</table></div>
</div>
</div><!--"square-order"end-->
</div><!--"content-text"end-->
<div class="content-bbg"><img src="images/content_bbg.jpg" /></div>
</div><!--"content-right"end-->
</div><!--"content"end-->
</s:form>
</body>
</html>