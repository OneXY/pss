<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<title>成都蓝源进销存系统(教学版)-产品列表页</title>
</head>
<body>
<s:form id="domainForm" action="supplier">
<div class="content-right">
<div class="content-r-pic"><div style="margin:8px auto auto 12px;"><img src="images/ping.gif" width="138" height="17" /></div></div>
<div class="content-text">
<div class="square-o-top">
<table width="100%"  border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:"黑体";">
  <tr><td ><%@include file="/WEB-INF/views/message.jsp" %></td></tr>
  <tr>
    <td width="60" height="30">用户名:</td>
    <td width="133"><s:textfield name="baseQuery.name" size="14"/></td>
    <td width="49"></td>
    <td width="142"></td>
	<td width="85"><img src="images/can_b_01.gif" border="0" onclick="go(1);" style="cursor: pointer;"/></td>
	<td width="136"><a href="supplier_input.action"><img src="images/can_b_02.gif" border="0"/></a></td>
  </tr>
</table>
</div><!--"square-o-top"end-->
<div class="square-order">
  <table id="itemTable" width="100%"  border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
    <tr align="center" style="background:url(images/table_bg.gif) repeat-x;">
      <td width="10%" height="30">编号</td>
      <td width="12%">用户名</td>
      <td width="16%">操作</td>
    </tr>
    <s:iterator value="pageResult.rows">
	    <tr align="center" bgcolor="#FFFFFF">
	      <td height="28">${id}</td>
	      <td>${name}</td>
	      <td><img src="images/icon_3.gif" /> <span style="line-height:12px; text-align:center;"><s:a class="xiu" href="javascript:updateDomain('supplier',%{id})">修改</s:a></span> <img src="images/icon_04.gif" /> <span style="line-height:12px; text-align:center;"><s:a class="xiu" href="javascript:" onclick="deleteDomain('supplier_delete.action',%{id},this)">删除</s:a></span></td>
	    </tr>
    </s:iterator>
  </table>
</div><!--"square-order"end-->
</div><!--"content-text"end-->
<%@include file="/WEB-INF/views/page.jsp"%>
</div><!--"content-right"end-->
</s:form>
</body>
</html>