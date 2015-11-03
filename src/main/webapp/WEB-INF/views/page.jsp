<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<div class="content-bbg">
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="3%">&nbsp;</td>
    <td width="10%">共<span id="totalCount">${pageResult.totalCount}</span>条记录</td>
    <td width="9%">当前第<span style="color:red;">${pageResult.currentPage}</span>/${pageResult.totalPage}</td>
    <td width="11%">每页${pageResult.pageSize}条记录</td>
    <td width="13%">分页：<a class="num" href="#">1</a>,<a class="num" href="#">2</a>,<a class="num" href="#">3</a>,<a class="num" href="#">4</a>,</td>
    <s:if test="pageResult.currentPage==1">
	    <td width="4%">首页</td>
	    <td width="6%">上一页</td>
	  </s:if>
	  <s:else>
      <td width="4%"><a href="#" onclick="go(1);">首页</a></td>
	    <td width="6%"><a class="sye" href="#" onclick="go(${pageResult.currentPage-1});">上一页</a></td>
    </s:else>
    <s:if test="pageResult.currentPage==pageResult.totalPage">
	    <td width="6%">下一页</td>
	    <td width="4%">末页</td>
    </s:if>
    <s:else>
    	<td width="6%"><a class="sye" href="#" onclick="go(${pageResult.currentPage+1});">下一页</a></td>
	    <td width="4%"><a href="#" onclick="go(${pageResult.totalPage});">末页</a></td>
    </s:else>
    <td>
    	<s:textfield id="pageNo" name="baseQuery.currentPage" value="%{pageResult.currentPage}" size="2"/>
    	<input type="submit" value="go"/>
    	<s:select list="{5,10,15,20}" name="baseQuery.pageSize" value="pageResult.pageSize" onchange="document.forms[0].submit();"/>
    </td>
  </tr>
</table>
<script type="text/javascript">
	function go(no){
		document.getElementById("pageNo").value=no;
		document.forms[0].submit();
	}
</script>
</div>