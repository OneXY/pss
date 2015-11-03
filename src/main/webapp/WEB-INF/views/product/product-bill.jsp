<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="/css/index.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/js/lightbox/css/screen.css"/>
<link rel="stylesheet" href="/js/lightbox/css/lightbox.css"/>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/lightbox/lightbox.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript">
	function findProduct(json){
    if (!has_showModalDialog && window.opener != undefined) { //forchrome  
        window.opener.selectHotelChrome(json); //关闭前调用父窗口方法  
    }else {    
        window.returnValue = json;  
    }
    window.close();
	}
</script>
<title>成都蓝源进销存系统(教学版)-产品列表页</title>
</head>
<body>
<s:form id="domainForm" action="product_bill">
<div class="content-right" id="xx">
<div class="content-r-pic"><div style="margin:8px auto auto 12px;"><img src="images/ping.gif" width="138" height="17" /></div></div>
<div class="content-text">
<div class="square-o-top">
<table width="100%"  border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:"黑体";">
  <tr><td ><%@include file="/WEB-INF/views/message.jsp" %></td></tr>
  <tr>
    <td width="60" height="30">名称:</td>
    <td width="133"><s:textfield name="baseQuery.name" size="18"/></td>
    <td width="49">颜色:</td>
    <td width="142"><s:textfield name="baseQuery.color" size="18"/></td>
	<td width="85"><img src="images/can_b_01.gif" border="0" onclick="go(1);" style="cursor: pointer;"/></td>
	<td width="136"></td>
  </tr>
</table>
</div><!--"square-o-top"end-->
<div class="square-order">
  <table id="itemTable" width="100%"  border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
    <tr align="center" style="background:url(images/table_bg.gif) repeat-x;">
      <td width="10%" height="30">编号</td>
      <td width="12%">名称</td>
      <td width="14%">颜色</td>
      <td width="14%">图片</td>
      <td width="10%">成本价</td>
      <td width="16%">操作</td>
    </tr>
    <s:iterator value="pageResult.rows">
	    <tr align="center" bgcolor="#FFFFFF">
	      <td height="28">${id}</td>
	      <td>${name}</td>
	      <td>${color}</td>
	      <td>
		      <a class="example-image-link" href="${pic}" data-lightbox="example-1">
		          <img class="example-image" src="${smallPic}" alt="image-1">
		      </a>
	      </td>
	      <td>${costPrice}</td>
	      <td>
	      	<input class="wBox_close" type="button" onclick='findProduct({"id":${id},"name":"${name}","color":"${color}","costPrice":"${costPrice}"})' value="确定"/>
	      </td>
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