<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="/css/index.css" rel="stylesheet" type="text/css" />
<link href="/js/validation/css/screen.css" rel="stylesheet" type="text/css" media="screen"/>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/validation/jquery.validate.js"></script>
<script type="text/javascript" src="/js/validation/messages_cn.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/model/purchaseBill.js"></script>
<title>成都蓝源进销存系统(教学版)-产品编辑界面</title>
</head>

<body>
<s:form id="purchaseBillForm" action="purchaseBill_save.action" namespace="/">
<s:hidden id="id" name="id"/>
<s:hidden name="baseQuery.currentPage"/>
<s:hidden name="baseQuery.pageSize"/>
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
      <td width="22%" align="center" height="30">交易时间</td>
      <td width="29%">
        <s:date name="vdate" format="yyyy-MM-dd" var="date"/>
    		<s:textfield name="vdate" value="%{date}" size="12" onclick="WdatePicker({maxDate:new Date()})" cssClass="Wdate"/>
      </td>
    </tr>
    <tr  bgcolor="#FFFFFF">
      <td height="30" align="center">供应商</td>
      <td>
        <s:select name="supplier.id" list="#allSuppliers" listValue="name" listKey="id"/>
      </td>
    </tr>
    <tr  bgcolor="#FFFFFF">
      <td height="30" align="center">采购员</td>
      <td>
        <s:select name="buyer.id" list="#allBuyers" listValue="name" listKey="id"/>
      </td>
    </tr>
  </table>
  <table id="itemTable" width="100%"  border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
  	<tr>
  		<th>产品名称</th>
  		<th>产品颜色</th>
  		<th>采购价格</th>
  		<th>采购数量</th>
  		<th>采购小计</th>
  		<th>采购备注</th>
  		<th>
  			<input type="button" id="addItem" value="添加明细" style="cursor: pointer"/>
			</th>
  	</tr>
  	<s:if test="items.size()==0">
	  	<tr align="center">
	  		<td>
	  			<s:hidden code="productId" name="items[0].product.id"/>
	  			<s:textfield code="productName" disabled="true" size="12"/>
	  			<img code="searchProduct" src="/images/search.png" alt="查找" />
	  		</td>
	  		<td code="productColor"></td>
	  		<td>
	  			<s:textfield code="itemPrice" name="items[0].price" size="12"/>
	  		</td>
	  		<td>
	  			<s:textfield code="itemNum" name="items[0].num" size="12"/>
	  		</td>
	  		<td code="itemAmount"></td>
	  		<td>
	  			<s:textfield code="itemDescs" name="items[0].descs" size="12"/>
	  		</td>
	  		<td>
	  			<input type="button" code="deleteItem" value="删除" style="cursor: pointer"/>
	  		</td>
	  	</tr>
  	</s:if>
  	<s:else>
  		<s:iterator value="items">
	  		<tr align="center">
		  		<td>
		  			<s:hidden code="productId" name="product.id"/>
		  			<s:textfield name="product.name" code="productName" disabled="true" size="12"/>
		  			<img code="searchProduct" src="/images/search.png" alt="查找" />
		  		</td>
		  		<td code="productColor">${product.color}</td>
		  		<td>
		  			<s:textfield code="itemPrice" name="price" size="12"/>
		  		</td>
		  		<td>
		  			<s:textfield code="itemNum" name="num" size="12"/>
		  		</td>
		  		<td code="itemAmount">${amount}</td>
		  		<td>
		  			<s:textfield code="itemDescs" name="descs" size="12"/>
		  		</td>
		  		<td>
		  			<input type="button" code="deleteItem" value="删除" style="cursor: pointer"/>
		  		</td>
		  	</tr>
	  	</s:iterator>
  	</s:else>
  </table>
<div class="order-botton">
<div style="margin:1px auto auto 1px;">
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><img id="sumbitBtn" src="images/order_tuo.gif" border="0"  style="cursor: pointer"/></td>
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