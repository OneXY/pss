<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%-- <s:actionmessage cssStyle="color:blue;width: 100px;"/> --%>
<%-- <s:fielderror cssStyle="color:red"/> --%>
<%-- <s:actionerror cssStyle="color:red"/> --%>
<%-- <script type="text/javascript">
	 	$(function(){
	 		$(".actionMessage").hide(2000);
	 	});
 </script> --%>
<s:if test="hasActionMessages()">
	<div id="actionMessage" style="color: blue; border: solid 1px blue"><s:actionmessage
			cssStyle="width: 100px" /></div>
</s:if>
<s:if test="hasFieldErrors()">
	<span style="color: red"><s:fielderror /></span>
</s:if>
<s:if test="hasErrors()">
	<span style="color: red"><s:actionerror /></span>
</s:if>