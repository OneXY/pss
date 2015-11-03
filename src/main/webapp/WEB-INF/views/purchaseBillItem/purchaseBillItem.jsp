<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="/css/index.css" rel="stylesheet" type="text/css" />
<link href="/js/wbox/css/wbox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/wbox/wbox.js"></script>
<script type="text/javascript">
$(function(){
	$("#FusionCharts").wBox({
		requestType:"iframe",
		iframeWH:{
			width:800,
			height:400
		},
		target:"purchaseBillItem_chart1.action?baseQuery.beginDate="+$("#beginDate").val()
				+"&baseQuery.endDate="+$("#endDate").val()
				+"&baseQuery.status="+$("#status").val()
				+"&baseQuery.groupBy="+$("#groupBy").val()
	});
	
	$("#HighCharts").wBox({
		requestType:"iframe",
		iframeWH:{
			width:800,
			height:400
		},
		target:"purchaseBillItem_chart3.action?baseQuery.beginDate="+$("#beginDate").val()
				+"&baseQuery.endDate="+$("#endDate").val()
				+"&baseQuery.status="+$("#status").val()
				+"&baseQuery.groupBy="+$("#groupBy").val()
	});
	
});
// function findChart(src){
// 	if(src.value=="--请选择--"){
		
// 	}else{
// 		if(src.value=="FusionCharts"){
// 			$("#charts").wBox({
// 				requestType:"iframe",
// 				iframeWH:{
// 					width:800,
// 					height:400
// 				},
// 				target:"purchaseBillItem_chart1.action?baseQuery.beginDate="+$("#beginDate").val()
// 						+"&baseQuery.endDate="+$("#endDate").val()
// 						+"&baseQuery.status="+$("#status").val()
// 						+"&baseQuery.groupBy="+$("#groupBy").val()
// // 						+"&time="+new Date().getTime()
// 			});
// 		}else{
			
// 		}
// 	}
// 	$("#charts").unbind("click");
// 	console.debug(src.value);
// }
</script>
<title>成都蓝源进销存系统(教学版)-产品列表页</title>
</head>
<body>
<s:form id="domainForm" action="purchaseBillItem_query">
<div class="content-right">
<div class="content-r-pic"><div style="margin:8px auto auto 12px;"><img src="images/ping.gif" width="138" height="17" /></div></div>
<div class="content-text">
<div class="square-o-top">
<table width="100%"  border="0" cellpadding="0" cellspacing="0" style="font-size:14px; font-weight:bold; font-family:"黑体";">
  <tr><td colspan="6" style="width: 250px;"><%@include file="/WEB-INF/views/message.jsp" %></td></tr>
  <tr>
    <td width="80" height="30">交易时间从:</td>
    <td>
    	<s:date name="baseQuery.beginDate" format="yyyy-MM-dd" var="bDate"/>
    	<s:textfield id="beginDate" name="baseQuery.beginDate" value="%{bDate}" size="12" onclick="WdatePicker({maxDate:new Date()})" cssClass="Wdate"/>到
    	<s:date name="baseQuery.endDate" format="yyyy-MM-dd" var="eDate"/>
    	<s:textfield id="endDate" name="baseQuery.endDate" value="%{eDate}" size="12" onclick="WdatePicker({maxDate:new Date()})" cssClass="Wdate"/>
    </td>
<!--     <td>状态:</td> -->
    <td>
    	<s:select id="status" list="#{-2:'--请选择--',0:'待审',1:'已审',-1:'作废'}" name="baseQuery.status"/>
    	<s:select id="groupBy" list="#{'o.bill.supplier.name':'供应商分组','o.bill.buyer.name':'采购员分组','month(o.bill.vdate)':'月份'}" name="baseQuery.groupBy"/>
    	<input type="button" id="FusionCharts" value="FusionCharts"/>
    	<input type="button" id="HighCharts" value="HighCharts"/>
<%-- 			<s:select id="charts" list="{'--请选择--','FusionCharts','HighCharts'}" onchange="findChart(this);"/> --%>
    </td>
		<td width="85"><img src="images/can_b_01.gif" border="0" onclick="go(1);" style="cursor: pointer;"/></td>
  </tr>
</table>
</div><!--"square-o-top"end-->
<div class="square-order">
  <table id="itemTable" width="100%"  border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
    <tr align="center" style="background:url(images/table_bg.gif) repeat-x;">
      <td>明细编号</td>
      <td>供应商名称</td>
      <td>采购员</td>
      <td>产品名称</td>
      <td>交易时间</td>
      <td>采购数量</td>
      <td>采购价格</td>
      <td>小计</td>
      <td>产品类别</td>
      <td>状态</td>
    </tr>
    <s:iterator value="#list" var="objects">
     	<tr style="color: blue">
	      <td colspan="10">${objects[0]}-记录数${objects[1]}条</td>
	   	</tr>
	   	<s:set var="totalAmount" value="0"/>
	   	<s:set var="totalNum" value="0"/>
	   	<s:iterator value="findItems(#objects[0])">
	      <tr align="center" style="background:url(images/table_bg.gif) repeat-x;">
		      <td>${id}</td>
		      <td>${bill.supplier.name}</td>
		      <td>${bill.buyer.name}</td>
		      <td>${product.name}</td>
		      <td>${bill.vdate}</td>
		      <td>${num}</td>
		      <td>${price}</td>
		      <td>${amount}</td>
		      <td>${product.types.name}</td>
		      <td>
			      <s:if test="bill.status==1">
		          <font color="green">已审</font>
		        </s:if>
		        <s:elseif test="bill.status==-1">
		          <font color="gray">作废</font>
		        </s:elseif>
		        <s:else>待审</s:else>
		      </td>
		    </tr>
		    <s:set var="totalAmount" value="#totalAmount+amount"/>
	      <s:set var="totalNum" value="#totalNum+num"/>
	   </s:iterator>
	   <tr align="center" style="background:url(images/table_bg.gif) repeat-x;color: red">
	      <td align="left" colspan="5">总计:</td>
	      <td>${totalNum}</td>
	      <td></td>
	      <td>${totalAmount}</td>
	      <td></td>
	      <td></td>
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