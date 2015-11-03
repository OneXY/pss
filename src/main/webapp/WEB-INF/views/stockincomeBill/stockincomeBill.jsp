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
<script type="text/javascript">
	function auditor(id,src){
		$.get("stockincomeBill_auditor.action",{"id":id},function(data){
			if(data=="此单成功审核"){
				var tr = $(src).closest("tr");
				tr.find("td[code=status]").html("已审").css("color","green");
				tr.find("td[code=opreat]").html("");
			}else{
				alert(data);
			}
		});
	}
</script>
<title>成都蓝源进销存系统(教学版)-产品列表页</title>
</head>
<body>
<s:form id="domainForm" action="stockincomeBill">
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
    	<s:textfield name="baseQuery.beginDate" value="%{bDate}" size="12" onclick="WdatePicker({maxDate:new Date()})" cssClass="Wdate"/>到
    	<s:date name="baseQuery.endDate" format="yyyy-MM-dd" var="eDate"/>
    	<s:textfield name="baseQuery.endDate" value="%{eDate}" size="12" onclick="WdatePicker({maxDate:new Date()})" cssClass="Wdate"/>
    </td>
    <td>状态:</td>
    <td>
    	<s:select list="#{-2:'--请选择--',0:'待审',1:'已审',-1:'作废'}" name="baseQuery.status" cssClass="kuan"/>
    </td>
	<td width="85"><img src="images/can_b_01.gif" border="0" onclick="go(1);" style="cursor: pointer;"/></td>
	<td width="136"><a href="stockincomeBill_input.action"><img src="images/can_b_02.gif" border="0"/></a></td>
  </tr>
</table>
</div><!--"square-o-top"end-->
<div class="square-order">
  <table id="itemTable" width="100%"  border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
    <tr align="center" style="background:url(images/table_bg.gif) repeat-x;">
      <td width="10%" height="30">编号</td>
      <td width="12%">交易时间</td>
      <td width="14%">总金额</td>
      <td width="14%">供应商</td>
      <td width="10%">状态</td>
      <td width="16%">操作</td>
    </tr>
    <s:iterator value="pageResult.rows">
	    <tr align="center" bgcolor="#FFFFFF">
	      <td height="28">${id}</td>
	      <td>${vdate}</td>
	      <td>${totalAmount} </td>
	      <td>${supplier.name}</td>
	      <td code="status">
	      <s:if test="status==1">
	      	<span style="color:green">已审</span>
        </s:if>
        <s:elseif test="status==-1">
        	<span style="color:gray">作废</span>
        </s:elseif>
        <s:else>待审</s:else>
        </td>
	      <td code="opreat">
	      	<s:if test="status==0">
			      <img src="images/icon_3.gif" />
			      <span style="line-height:12px; text-align:center;">
			      	<s:a class="xiu" href="javascript:updateDomain('stockincomeBill',%{id})">修改</s:a>
			      </span>
						<img src="images/icon_04.gif" />
						<span style="line-height:12px; text-align:center;">
							<s:a class="xiu" href="javascript:" onclick="deleteDomain('stockincomeBill_delete.action',%{id},this)">删除</s:a>
						</span>
<%-- 						<a href="purchaseBill_auditor.action?id=${id}">审核</a> --%>
						<a href="javascript:" onclick="auditor(${id},this)">审核</a>
<%-- 						<a href="javascript:auditor(${id},this);" onclick="auditor(${id},this)">审核</a> --%>
					</s:if>
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